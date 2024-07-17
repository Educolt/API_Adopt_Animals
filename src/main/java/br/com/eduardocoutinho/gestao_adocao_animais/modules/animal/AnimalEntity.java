package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AnimalEntity {
    
    private UUID id;

    @NotBlank(message = "O campo não deve ser vazio !")
    private String name;

    private String description;

    private String image_url;

    private String category;

    private Date born_at;

    private Integer age;

    private Boolean status;
}
