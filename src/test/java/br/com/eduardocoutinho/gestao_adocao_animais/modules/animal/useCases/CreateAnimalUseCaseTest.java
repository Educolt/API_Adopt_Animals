package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.IAnimalRepository;

@ExtendWith(MockitoExtension.class)
public class CreateAnimalUseCaseTest {

    @InjectMocks
    private CreateAnimalUseCase createAnimalUseCase;

    @Mock
    private IAnimalRepository animalRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Configuração do ObjectMapper
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @DisplayName("Should be able to create an animal")
    public void should_be_able_to_create_an_animal() {
        // Criação do AnimalEntity usando o builder, se disponível
        var idAnimal = UUID.randomUUID();
        AnimalEntity animalEntity = AnimalEntity.builder()
                .id(idAnimal)
                .name("Nick")
                .description("A brown dachshund.")
                .imageUrl("image.jpg")
                .category("dog")
                .bornAt(LocalDateTime.parse("2024-07-16T17:45:30"))
                .age(14)
                .status(true)
                .build();

        when(animalRepository.save(any(AnimalEntity.class))).thenReturn(animalEntity);

        var result = createAnimalUseCase.execute(animalEntity);

        assertInstanceOf(AnimalEntity.class, result);

        assertNotNull(result.getId());
        assertEquals(animalEntity.getName(), result.getName());
        assertEquals(animalEntity.getDescription(), result.getDescription());
        assertEquals(animalEntity.getImageUrl(), result.getImageUrl());
        assertEquals(animalEntity.getCategory(), result.getCategory());
        assertEquals(animalEntity.getBornAt(), result.getBornAt());
        assertEquals(animalEntity.getAge(), result.getAge());
        assertEquals(animalEntity.getStatus(), result.getStatus());

        verify(animalRepository).save(any(AnimalEntity.class));
    }
}