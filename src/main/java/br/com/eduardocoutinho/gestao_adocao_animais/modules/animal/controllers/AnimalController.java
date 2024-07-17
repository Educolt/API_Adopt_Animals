package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.CreateAnimalUseCase;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.ListAnimalUseCase;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.UpdateAnimalStatusUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private CreateAnimalUseCase createAnimalUseCase;

    @Autowired
    private ListAnimalUseCase listAnimalUseCase;

    @Autowired
    private UpdateAnimalStatusUseCase updateAnimalStatusUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create( @Valid @RequestBody AnimalEntity animalEntity) {
        try {
            var result = this.createAnimalUseCase.execute(animalEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> list() {
        try {
            var result = this.listAnimalUseCase.execute();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStatus(@RequestBody AnimalEntity animalEntity, HttpServletRequest request, @PathVariable UUID id) {
        try {
            var result = this.updateAnimalStatusUseCase.execute(id, animalEntity.getStatus());
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
