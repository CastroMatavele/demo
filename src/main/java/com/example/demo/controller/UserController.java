package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = new ArrayList<>();
            try{
                userRepository.findAll().forEach(userList::add);
                    if(userList==null){
                        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
                    }
                    else
                    {
                        return new ResponseEntity<>(userList,HttpStatus.ACCEPTED);
                    }
            }catch (Exception e){
                return new ResponseEntity<>(userList,HttpStatus.ACCEPTED);
            }
    }

    @PostMapping("/user")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        try{
            User _user = userRepository.save(new User(user.getId(),
                    user.getNome(),
                    user.getEmail(),
                    user.getTelfone(),
                    user.isSituacao(),
                    user.getDataCriacao(),
                    user.getPerfil(),
                    user.getUsername(),
                    user.getPassword()));
            return new ResponseEntity<>(_user, HttpStatus.ACCEPTED);

        }catch(Exception e){

            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        }






    }



}
