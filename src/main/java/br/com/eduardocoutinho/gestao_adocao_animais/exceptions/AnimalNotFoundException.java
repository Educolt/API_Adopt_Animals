package br.com.eduardocoutinho.gestao_adocao_animais.exceptions;

public class AnimalNotFoundException extends RuntimeException {

    public AnimalNotFoundException() {
        super("Animal não encontrado !");
    }
    
}
