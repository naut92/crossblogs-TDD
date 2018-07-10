package com.crossover.techtrial.controller;

import com.crossover.techtrial.model.Author;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class AuthorController {
    @GetMapping(path = "/authors")
    public List<Author> getAllNames(){
        return Collections.emptyList();
    }
}
