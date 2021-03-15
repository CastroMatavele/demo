package com.example.demo.controller;
import com.example.demo.config.Parametros;
import com.example.demo.model.Servico;
import com.example.demo.repository.ServicosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ServicosController {

    @Autowired
    private ServicosRepository servicosRepository;

    private static final Logger logger = LoggerFactory.getLogger(ServicosController.class);

    @GetMapping("servicos")
    public ResponseEntity<List<Servico>> getAllServicos(){
        List<Servico> servicoList = new ArrayList<>();
            try{
                servicosRepository.findAll().forEach(servicoList::add);
                    if(servicoList == null){
                        logger.info(Parametros.MESSAGEMINFO + "de servicos");
                        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                    }else{
                        return new ResponseEntity<>(servicoList,HttpStatus.ACCEPTED);
                    }
            }catch (Exception e){
                    logger.error(Parametros.MESSAGEMERRO + "ao listar o servico" + e.getMessage());
            }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    @PostMapping("servicos")
    public ResponseEntity<Servico> saveServico(@RequestBody Servico servico){
        try {
            Servico _servico = servicosRepository.
                    save(new Servico(servico.getId(),
                            servico.getUtilizador(),
                            servico.getAgenda(),
                            servico.getTotalServico()));
            logger.info(Parametros.MESSAGEMSUCESSO + "ao registar o servico");
            return new ResponseEntity<>(_servico, HttpStatus.ACCEPTED);

        }catch (Exception e){
            logger.error(Parametros.MESSAGEMERRO + "ao registar servico" + e.getMessage());
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


}
