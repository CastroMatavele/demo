package com.example.demo.controller;

import com.example.demo.config.Parametros;
import com.example.demo.model.DiasNaoUteis;
import com.example.demo.repository.DiasNaoUteisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class DiasNaoUteisController {
    @Autowired
    private DiasNaoUteisRepository diasNaoUteisRepository;
    public static final Logger logger = LoggerFactory.getLogger(DiasNaoUteisController.class);

    @PostMapping("/diasnaouteis")
    public ResponseEntity<DiasNaoUteis> saveDiasNaoUteis(@RequestBody DiasNaoUteis diasNaoUteis){

        try{
            DiasNaoUteis _diasNaoUteis = diasNaoUteisRepository.
                    save(new DiasNaoUteis(diasNaoUteis.getId(),
                            diasNaoUteis.getDataNaoUtil(),
                            diasNaoUteis.getDataCriacao(),
                            diasNaoUteis.getUserCriacao()));
            logger.info(Parametros.MESSAGEMSUCESSO + "informacao registada com sucesso");
            new ResponseEntity<>(_diasNaoUteis, HttpStatus.ACCEPTED);

        }catch (Exception e){
            logger.error(Parametros.MESSAGEMERRO + "ao registar a informacao " + e.getMessage());
        }
    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/diasnaouteis")
    public ResponseEntity<List<DiasNaoUteis>> getDiasNaoUteis(){
        List<DiasNaoUteis> diasNaoUteisList = new ArrayList<>();
            try{
                diasNaoUteisRepository.findAll().forEach(diasNaoUteisList::add);
                    if(diasNaoUteisList == null){
                        logger.info(Parametros.MESSAGEMINFO + "de dias uteis");
                        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
                    }else{
                        return new ResponseEntity<>(diasNaoUteisList, HttpStatus.ACCEPTED);
                    }

            }catch (Exception e){
                logger.error(Parametros.MESSAGEMERRO + "ao registar os dias nao uteis" + e.getMessage());
            }

        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }




}
