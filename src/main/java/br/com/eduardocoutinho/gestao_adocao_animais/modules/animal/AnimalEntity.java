package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal;

import java.util.Date;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class AnimalEntity {
    
    private UUID id;

    @Length(min = 4, max = 180, message = "O campo [name] deve conter no minimo 4 a 180 caracteres ")
    private String name;
    private String description;
    private String image_url;
    private String category;
    private Date born_at;
    private Integer age;
    private Boolean status;
}
