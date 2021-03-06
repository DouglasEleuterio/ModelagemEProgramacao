package com.douglas.cursomc.service.exceptions;

/**
 * Classe de exceção personalizada.
 * É chamada pelas classes de serviço e retorna a exceção.
 */
public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Construtor
     * O Construtor recebe a mensagem de erro e envia para a super classe estendida, no caso, RuntimeException.
     * @param menssagem - Mensagem de erro
     */
    public ObjectNotFoundException(String menssagem) {
        super(menssagem);
    }

    /**
     * Construtor sobrescrito
     * @param mensagem - Mensagem de erro.
     * @param cause - Causa do erro.
     */
    public ObjectNotFoundException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
