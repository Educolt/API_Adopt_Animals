package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.AnimalEntity;
import br.com.eduardocoutinho.gestao_adocao_animais.modules.animal.IAnimalRepository;

@Service
public class ListAnimalUseCase {

    @Autowired
    private IAnimalRepository animalRepository;

    public List<AnimalEntity> execute() {
        return this.animalRepository.findAll();
    }
}
