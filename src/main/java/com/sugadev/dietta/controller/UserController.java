package com.sugadev.dietta.controller;

import com.sugadev.dietta.model.User;
import com.sugadev.dietta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addUser (@RequestParam String username, @RequestParam String password,
                                              @RequestParam String nama, @RequestParam String email,
                                         @RequestParam int noTelp, @RequestParam int tinggiBadan,
                                         @RequestParam int beratBadan){

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNama(nama);
        user.setEmail(email);
        user.setNoTelp(noTelp);
        user.setTinggiBadan(tinggiBadan);
        user.setBeratBadan(beratBadan);
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUser(){
        return userRepository.findAll();
    }
}
