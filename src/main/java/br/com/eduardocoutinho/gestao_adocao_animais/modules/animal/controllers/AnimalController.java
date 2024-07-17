package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/animal")
public class AnimalController {
    

    @PostMapping("/")
    public void create( @Valid @RequestBody AnimalEntity animaEntity) {
        System.out.println(animaEntity.getName());
    }
}
