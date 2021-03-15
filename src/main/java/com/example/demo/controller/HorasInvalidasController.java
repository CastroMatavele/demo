package com.example.demo.controller;

import com.example.demo.config.Parametros;
import com.example.demo.model.HorasInvalidas;
import com.example.demo.repository.HorasInvalidasRepository;
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
public class HorasInvalidasController {
    @Autowired
    private HorasInvalidasRepository horasInvalidasRepository;

    public static final Logger logger = LoggerFactory.getLogger(HorasInvalidasController.class);

    @GetMapping("horasinvalidas")
    public ResponseEntity<List<HorasInvalidas>> getHorasInvalidas(){
        List<HorasInvalidas> horasInvalidasList = new ArrayList<>();
            try{
                horasInvalidasRepository.findAll().forEach(horasInvalidasList::add);
                if(horasInvalidasList == null){
                    logger.info(Parametros.MESSAGEMINFO + "de registo de horas invalidas");
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }else{
                    return new ResponseEntity<>(horasInvalidasList,HttpStatus.ACCEPTED);
                }
            }catch (Exception e){
                logger.error(Parametros.MESSAGEMERRO + "ao registar as horas invalidas" + e.getMessage());
            }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


    @PostMapping("horasinvalidas")

    public ResponseEntity<HorasInvalidas> saveHorasInvalidas(@RequestBody HorasInvalidas horasInvalidas){
        try{
            HorasInvalidas _horasInvalidas = horasInvalidasRepository.
                    save(new HorasInvalidas());
            logger.info(Parametros.MESSAGEMSUCESSO + "ao registar as horas invalidas");
            return new ResponseEntity<>(_horasInvalidas,HttpStatus.ACCEPTED);

        }catch (Exception e){
            logger.error(Parametros.MESSAGEMERRO + "ao registar as horas invalidas" + e.getMessage());
        }

        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }


}
