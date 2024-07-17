package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardocoutinho.gestao_adocao_animais.exceptions.AnimalNotFoundException;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.IAnimalRepository;

@Service
public class UpdateAnimalStatusUseCase {
    @Autowired
    private IAnimalRepository animalRepository;

    public Optional<AnimalEntity> execute(UUID id, Boolean status) {
        Optional<AnimalEntity> optionalAnimal = this.animalRepository.findById(id);

        if (optionalAnimal.isPresent()) {
            AnimalEntity animal = optionalAnimal.get();
            if (status != null) {
                animal.setStatus(status);
                this.animalRepository.save(animal);
            }
            return Optional.of(animal);
        } else {
            throw new AnimalNotFoundException();
        }
    }
}
