package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardocoutinho.gestao_adocao_animais.exceptions.AnimalNotFoundException;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.IAnimalRepository;

@Service
public class FindAnimalByIdUseCase {
    @Autowired
    private IAnimalRepository animalRepository;

    public AnimalEntity execute(UUID id) {
        AnimalEntity animal = this.animalRepository.findById(id)
            .orElseThrow(AnimalNotFoundException::new);
        
        return animal;
    }
}
