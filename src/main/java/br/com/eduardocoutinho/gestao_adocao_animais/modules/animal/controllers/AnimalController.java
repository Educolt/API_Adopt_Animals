package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.CreateAnimalUseCase;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.FindAnimalByIdUseCase;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.ListAnimalUseCase;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.UpdateAnimalStatusUseCase;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases.UpdateAnimalUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Autowired
    private CreateAnimalUseCase createAnimalUseCase;

    @Autowired
    private ListAnimalUseCase listAnimalUseCase;

    @Autowired
    private UpdateAnimalStatusUseCase updateAnimalStatusUseCase;

    @Autowired
    private UpdateAnimalUseCase updateAnimalUseCase;

    @Autowired
    private FindAnimalByIdUseCase findAnimalByIdUseCase;

    @Value("${file.upload-dir}")
    private String uploadDir;

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

    @PostMapping("/upload/{id}")
    @Tag(name = "Upload", description = "Upload Files")
    @Operation(summary = "Upload profile image for an animal", description = "Upload profile image and get its URL.")
    public ResponseEntity<Object> uploadAnimalProfile(
        @PathVariable UUID id,
        @RequestParam("animalProfile") MultipartFile animalProfile) {
        try {
            String animalProfileFileName = saveFile(animalProfile);
            String animalProfileUrl = generateFileUrl(animalProfileFileName);

            AnimalEntity animal = this.findAnimalByIdUseCase.execute(id);
            animal.setImageUrl(animalProfileUrl);

            Optional<AnimalEntity> updatedAnimal = this.updateAnimalUseCase.execute(id, animal);

            return ResponseEntity.ok().body(Map.of(
                    "imageUrl", updatedAnimal.map(AnimalEntity::getImageUrl).orElse(animalProfileUrl)
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());
        return fileName;
    }

    private String generateFileUrl(String fileName) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(fileName)
                .toUriString();
    }
}
