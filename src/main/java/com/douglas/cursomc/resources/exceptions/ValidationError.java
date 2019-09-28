package com.douglas.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe auxiliar de Validação.
 * A ideia é criar uma lista de erros personalizados para entregar para o Resource.
 *
 */
public class ValidationError extends StandardError {
    private static final long servialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String erro, Long timeStamp) {
        super(status, erro, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    /**
     * Acresecentando um erro por vez na lista.
     * Erro contem fildName e message
     * @param filfName - Nome do campo
     * @param message - Mensagem de erro.
     */
    public void addError(String filfName, String message){
        errors.add(new FieldMessage(filfName,message));
    }
}
