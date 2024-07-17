package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.CreateAnimalUseCase;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private CreateAnimalUseCase createAnimalUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create( @Valid @RequestBody AnimalEntity animalEntity) {
        try {
            var result = this.createAnimalUseCase.execute(animalEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
