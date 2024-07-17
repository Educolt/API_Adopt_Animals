package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardocoutinho.gestao_adocao_animais.exceptions.AnimalFoundException;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalRepository;

@Service
public class CreateAnimalUseCase {
    
    @Autowired
    private AnimalRepository animalRepository;

    public AnimalEntity execute(AnimalEntity animalEntity) {
        /* this.animalRepository.findByNameOrId(animalEntity.getName(), animalEntity.getId()).ifPresent((animal) -> {
            throw new AnimalFoundException();
        }); */
        return this.animalRepository.save(animalEntity);
    }
}
