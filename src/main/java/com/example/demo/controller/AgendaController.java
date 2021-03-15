package com.example.demo.controller;

import com.example.demo.config.Parametros;
import com.example.demo.model.Agenda;
import com.example.demo.model.DiasNaoUteis;
import com.example.demo.model.HorasInvalidas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.repository.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class AgendaController {
    @Autowired
    private AgendaRepository agendaRepository;

    private static final Logger logger = LoggerFactory.getLogger(AgendaController.class);

    @GetMapping("/agenda")
    public ResponseEntity<List<Agenda>> getAllAgendas(){
        List<Agenda> agendaList = new ArrayList<>();
            try {
                agendaRepository.findAll().forEach(agendaList::add);
                    if( agendaList == null ){
                        logger.warn(Parametros.MESSAGEMINFO + "de agendas");
                        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

                    }else{
                        logger.info(Parametros.MESSAGEMSUCESSO + "ao registar agenda");
                        return new ResponseEntity<>(agendaList, HttpStatus.ACCEPTED);

                    }
            }catch (Exception e){
                logger.error(Parametros.MESSAGEMERRO + e.getMessage());
            }
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

    }

    public boolean validarHora(Time horasx){
        List<HorasInvalidas> horasInvalidasList = new ArrayList<>();
        if(horasInvalidasList.contains(horasx)){
            return false;
            }
      return true;
    }

    public boolean validarData(Date data){
        List<DiasNaoUteis> diasNaoUteisList = new ArrayList<>();
            if(diasNaoUteisList.contains(data)){
                return false;
            }
        return true;
    }

    @PostMapping("agenda")
    public ResponseEntity<Agenda> saveAgenda(@RequestBody Agenda agenda) {
        Agenda _agendaSave = new Agenda(agenda.getId(),
                agenda.getDataCriacao(),
                agenda.getCortes(),
                agenda.getDataCorte(),
                agenda.getHoraCorte(),
                agenda.isSituacaoCorte(),
                agenda.getUtilizador());
        if(validarHora(agenda.getHoraCorte()) && validarData(agenda.getDataCorte())){

            try{
                agendaRepository.save(_agendaSave);
                logger.info(Parametros.MESSAGEMSUCESSO + "ao cadastar a agenda");
                return new ResponseEntity<>(_agendaSave, HttpStatus.ACCEPTED);

            }catch (Exception e){
                logger.error(Parametros.MESSAGEMERRO + e.getMessage());
            }

        }else{
            logger.error(Parametros.MESSAGETIME);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }




}
