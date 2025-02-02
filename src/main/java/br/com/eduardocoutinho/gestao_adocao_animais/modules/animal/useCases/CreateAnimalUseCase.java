package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.IAnimalRepository;

@Service
public class CreateAnimalUseCase {
    
    @Autowired
    private IAnimalRepository animalRepository;

    public AnimalEntity execute(AnimalEntity animalEntity) {
        return this.animalRepository.save(animalEntity);
    }
}
