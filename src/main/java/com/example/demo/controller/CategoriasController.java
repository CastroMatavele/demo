package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.repository.CateogoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class CategoriasController {

    @Autowired
   private CateogoriaRepository cateogoriaRepository;

    @GetMapping("/categorias")
   public ResponseEntity<List<Categoria>> getAllCategorias(){
       List<Categoria> categorias = new ArrayList<>();
       try{
           cateogoriaRepository.findAll().forEach(categorias::add);
           if(categorias == null){
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
       }catch (Exception e){

       }
    return new ResponseEntity<>(categorias,HttpStatus.ACCEPTED);

   }
//(long id, String nome, boolean situacao, Timestamp dataCriacao, Image image)
    @PostMapping("/categorias")
   public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria){
       Categoria _categoria = cateogoriaRepository.
               save(new Categoria(categoria.getId(),
                       categoria.getNome(),
                       categoria.isSituacao(),
                       categoria.getDataCriacao(),
                       categoria.getImage()));
        return new ResponseEntity<>(_categoria,HttpStatus.ACCEPTED);
   }
@GetMapping("/categorias/{id}")
   public ResponseEntity<Categoria> findCategoria(@PathVariable("id") long id){
        Optional<Categoria> categoriaList = cateogoriaRepository.findById(id);
            if(categoriaList.isPresent()){
                return  new ResponseEntity<>(categoriaList.get(),HttpStatus.ACCEPTED);
            }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

   }
/*
   //update name;
    @PutMapping("/categorias/{nome}")
    public ResponseEntity<Categoria> updateCategoriaName(@PathVariable("nome") String nome, @RequestBody Categoria categoria){
        List<Categoria> optionalCategoria = cateogoriaRepository.findByName(nome);
        if(optionalCategoria.()){
            Categoria _categoria = optionalCategoria.get();
            _categoria.setNome(categoria.getNome());
            _categoria.setDataCriacao(categoria.getDataCriacao());
            _categoria.setSituacao(categoria.isSituacao());
            return new ResponseEntity<Categoria>(cateogoriaRepository.save(_categoria),HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }


    }

 */







}
