package br.com.eduardocoutinho.gestao_adocao_animais.exceptions;

public class AnimalFoundException extends RuntimeException {

    public AnimalFoundException() {
        super("Animal já cadastrado !");
    }
    
}
