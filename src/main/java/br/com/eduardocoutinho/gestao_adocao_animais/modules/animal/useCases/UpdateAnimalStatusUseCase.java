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
        AnimalEntity animal = this.animalRepository.findById(id)
            .orElseThrow(AnimalNotFoundException::new);

        if (status != null) {
            animal.setStatus(status);
        }
        
        return Optional.of(this.animalRepository.save(animal));
    }
}
