package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.IAnimalRepository;

@ExtendWith(MockitoExtension.class)
public class CreateAnimalUseCaseTest {

    @InjectMocks
    private CreateAnimalUseCase createAnimalUseCase;

    @Mock
    private IAnimalRepository animalRepository;

    @Test
    @DisplayName("Should be able to create an animal")
    public void should_be_able_to_create_an_animal() {

        var idAnimal = UUID.randomUUID();
        AnimalEntity animalEntity = new AnimalEntity();

        animalEntity.setId(idAnimal);
        animalEntity.setName("Nick");
        animalEntity.setDescription("A brown dachshund.");
        animalEntity.setImageUrl("image.jpg");
        animalEntity.setCategory("dog");
        animalEntity.setBornAt("2024-07-16T17:45:30");
        animalEntity.setAge(14);
        animalEntity.setStatus(true);

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