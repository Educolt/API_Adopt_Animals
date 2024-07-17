package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;

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

    @NotBlank(message = "O campo não deve ser vazio !")
    private String name;

    private String description;

    private String imageUrl;

    private String category;

    private LocalDateTime bornAt;

    private Integer age;

    @NotNull()
    private Boolean status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
