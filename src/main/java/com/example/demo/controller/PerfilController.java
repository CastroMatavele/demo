package com.example.demo.controller;

import com.example.demo.DemoApplication;
import com.example.demo.config.Parametros;
import com.example.demo.model.Perfil;
import com.example.demo.repository.PerfilRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;

    private static final Logger logger = LoggerFactory.getLogger(PerfilController.class);

    @GetMapping("perfil")
    public ResponseEntity<List<Perfil>> getPerfilUser(){
        List<Perfil> perfilList = new ArrayList<>();
        try{
            perfilRepository.findAll().forEach(perfilList::add);
                if(perfilList == null){
                    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                }
                logger.info(Parametros.MESSAGEMSUCESSO);

        }catch (Exception e){
            logger.error(Parametros.MESSAGEMERRO + e.getMessage());
        }
     return new ResponseEntity<>(perfilList,HttpStatus.ACCEPTED);


    }

    @PostMapping("perfil")
    public ResponseEntity<Perfil> savePerfil(@RequestBody Perfil perfil){

        try {
            Perfil _perfil = perfilRepository.
                    save(new Perfil(perfil.getId(),
                            perfil.getNome(),
                            perfil.getDescricao(),
                            perfil.getDataCriacao()
                    ));

            logger.info(Parametros.MESSAGEMSUCESSO + "Cadastrar o perfil");
            return new ResponseEntity<>(_perfil, HttpStatus.ACCEPTED);

        }catch (Exception e){
            logger.error(Parametros.MESSAGEMERRO + "ao registar o perfil" + e.getMessage());
        }

    return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }




}
