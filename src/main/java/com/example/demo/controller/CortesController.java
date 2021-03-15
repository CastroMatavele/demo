package com.example.demo.controller;

import com.example.demo.config.Parametros;
import com.example.demo.model.Cortes;
import com.example.demo.repository.CorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api")
public class CortesController {
    @Autowired
    private CorteRepository corteRepository;
    private final static Logger logger = LoggerFactory.getLogger(Cortes.class);

    @GetMapping("/cortes")
    public ResponseEntity<List<Cortes>> getCortes(){
        List<Cortes> cortesList = new ArrayList<>();
            try{
                corteRepository.findAll().forEach(cortesList::add);
                if( cortesList == null){
                    logger.info(Parametros.MESSAGEMSUCESSO + "lista de cortes");
                    return new ResponseEntity<>(cortesList, HttpStatus.ACCEPTED);
                }

            }catch (Exception e){
                logger.error(Parametros.MESSAGEMERRO + "ao salvar o corte" + e.getMessage());
            }
        return  new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }

    // public Cortes(long id, String nome, String descricao, Categoria categoria,
    // Timestamp dataCriacao, boolean situacao, Double valor)

    @PostMapping("/cortes")
    public ResponseEntity<Cortes> saveCorte(@RequestBody Cortes cortes){
        try{
            Cortes _cortes = corteRepository.
                    save(new Cortes(
                            cortes.getId(),
                            cortes.getNome(),
                            cortes.getDescricao(),
                            cortes.getCategoria(),
                            cortes.getDataCriacao(),
                            cortes.isSituacao(),
                            cortes.getValor()));
            logger.info(Parametros.MESSAGEMSUCESSO + "ao registar a informacao do corte");
            return new ResponseEntity<>(_cortes,HttpStatus.ACCEPTED);
        }catch (Exception e){
            logger.error(Parametros.MESSAGEMERRO + "ao registar um corte" + e.getMessage());
        }
    return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

    }








}
