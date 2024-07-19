package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardocoutinho.gestao_adocao_animais.exceptions.AnimalNotFoundException;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.IAnimalRepository;

@Service
public class UpdateAnimalUseCase {
    @Autowired
    private IAnimalRepository animalRepository;

    public Optional<AnimalEntity> execute(UUID id, AnimalEntity animalEntity) {
        AnimalEntity animal = this.animalRepository.findById(id)
            .orElseThrow(AnimalNotFoundException::new);
        
            animal.setName(animalEntity.getName());
            animal.setAge(animalEntity.getAge());
            animal.setDescription(animalEntity.getDescription());
            animal.setStatus(animalEntity.getStatus());
            animal.setImageUrl(animalEntity.getImageUrl());
        
        return Optional.of(this.animalRepository.save(animal));
    }
}
