package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.CreateAnimalUseCase;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.ListAnimalUseCase;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.UpdateAnimalStatusUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @PostMapping("/register")
    @Tag(name = "Register",  description = "Create")
    @Operation(summary = "Create register animals on database.", description = "Create an animal register with the passed data.")
    public ResponseEntity<Object> create( @Valid @RequestBody AnimalEntity animalEntity) {
        try {
            var result = this.createAnimalUseCase.execute(animalEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show")
    @Tag(name = "Show",  description = "Information")
    @Operation(summary = "List all animals registered.", description = "Show all the data from all the registered animals.")
    public ResponseEntity<Object> list() {
        try {
            var result = this.listAnimalUseCase.execute();
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/status/{id}")
    @Tag(name = "Update",  description = "Update")
    @Operation(summary = "Update animal status.", description = "Update an animal register status by id.")
    public ResponseEntity<Object> updateStatus(@RequestBody AnimalEntity animalEntity, HttpServletRequest request, @PathVariable UUID id) {
        try {
            var result = this.updateAnimalStatusUseCase.execute(id, animalEntity.getStatus());
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
