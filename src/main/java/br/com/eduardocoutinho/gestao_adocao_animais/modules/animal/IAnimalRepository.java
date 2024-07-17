package br.com.eduardocoutinho.gestao_adocao_animais.modules.animal;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IAnimalRepository extends JpaRepository<AnimalEntity, UUID> {
    Optional<AnimalEntity> findByNameOrId(String name, UUID id);
}
