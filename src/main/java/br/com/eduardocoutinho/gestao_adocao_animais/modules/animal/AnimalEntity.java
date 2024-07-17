package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name = "animal")
public class AnimalEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Schema(example = "Nick", requiredMode = RequiredMode.REQUIRED)
    @NotBlank(message = "O campo não deve ser vazio !")
    private String name;

    @Schema(example = "A brown dachshund.")
    private String description;

    @Schema(example = "image.jpg")
    private String imageUrl;

    @Schema(example = "dog")
    private String category;

    @Schema(example = "2024-07-16T17:45:30")
    private LocalDateTime bornAt;

    @Schema(example = "14")
    private Integer age;

    @NotNull()
    @Schema(example = "true", requiredMode = RequiredMode.REQUIRED)
    private Boolean status;

    @CreationTimestamp
    @Schema(example = "Nick", requiredMode = RequiredMode.NOT_REQUIRED)
    private LocalDateTime createdAt;
}
