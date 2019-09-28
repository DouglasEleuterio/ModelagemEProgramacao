package com.douglas.cursomc.resources.exceptions;

import com.douglas.cursomc.service.exceptions.DataIntegrityException;
import com.douglas.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Para gerar a resposta com informações de CódigoHTTP
 * Mensagem e erro e instante que ocorreu o erro,
 * criaremos um obj auxiliar.
 * Classe auxiliar : StandardError
 *
 * @see StandardError
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        //Stanciado o standard erro
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        //Stanciado o standard erro
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    /**
     * Metodo que nos auxilia a gerar uma mensagem de erro de validação customizada.
     * Pegamos o obeto que é gerado e extraimos dele as mensagem que achamos relevantes, nesse caso:
     * fildName - Nome do Campo
     * defaultMessage - Erro 'padrão'
     * @param e - Recebemos {@link MethodArgumentNotValidException} que é gerado.
     * @param request - {@link HttpServletRequest} Objeto de comunicação do Java.
     * @return Mensagem de erro.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        //Stanciado o standard erro
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
        //Percorrendo a lista de erros e pegando os erros que queremos trabalhar.
        for (FieldError x : e.getBindingResult().getFieldErrors())  //Acessando todos os campos de erros da exceção
        {
            err.addError(x.getField(), x.getDefaultMessage()); //Pega os campos de erro que desejamos e inserimos no nosso objeto.
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}