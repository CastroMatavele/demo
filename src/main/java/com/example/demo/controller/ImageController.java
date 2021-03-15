package com.example.demo.controller;

import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @GetMapping("/imagensL")
    public ResponseEntity<List<Image>> getAllImages(){
        List<Image> imageList = new ArrayList<>();
        try{
            imageRepository.findAll().forEach(imageList::add);
            if(imageList !=null){
                return new ResponseEntity<>(imageList, HttpStatus.ACCEPTED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/imagens")
    public ResponseEntity<Image> saveImage(@RequestBody(required = true) MultipartFile file){
        try{
            Image _image = imageRepository.
                    save(new Image(file.getOriginalFilename(),file.getContentType(),file.getBytes()));
            return new ResponseEntity<>(_image, HttpStatus.ACCEPTED);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
    }










}
