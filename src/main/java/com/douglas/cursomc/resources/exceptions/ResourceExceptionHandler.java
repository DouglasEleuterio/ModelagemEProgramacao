package com.douglas.cursomc.resources.exceptions;

import com.douglas.cursomc.service.exceptions.DataIntegrityException;
import com.douglas.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Para gerar a resposta com informações de CódigoHTTP
 * Mensagem e erro e instante que ocorreu o erro,
 * criaremos um obj auxiliar.
 * Classe auxiliar : StandardError
 * @see StandardError
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound (ObjectNotFoundException e, HttpServletRequest request){
        //Stanciado o standard erro
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity (DataIntegrityException e, HttpServletRequest request){
        //Stanciado o standard erro
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
    
    
}
