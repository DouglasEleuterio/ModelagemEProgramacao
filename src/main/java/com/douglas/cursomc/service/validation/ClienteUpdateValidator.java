package com.douglas.cursomc.service.validation;

import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.domain.Enums.TipoCliente;
import com.douglas.cursomc.dto.ClienteDTO;
import com.douglas.cursomc.dto.ClienteNewDTO;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.resources.exceptions.FieldMessage;
import com.douglas.cursomc.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    /**
     * Para pegar o id do cliente que vem na URI.
     */
    @Autowired
    HttpServletRequest request;

    @Autowired
    ClienteRepository repo;

    /**
     * Pode-se colocar algumas regras de inicialização.
     *
     * @param ann
     */
    @Override
    public void initialize(ClienteUpdate ann) {
    }

    /**
     * Metodo que verifica se nosso Tipo-Objeto recebido é válido ou não.
     *
     * @param objDto  - Recebe um Objeto ClienteNewDTO
     * @param context
     * @return true caso seja válido, ou false caso constrário.
     */
    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        /**
         * Pegando o "id" que virá na URI da requisição.
         */
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); //Percorrendo a estrutua que contém os dados da URI.
        Integer uriId = Integer.parseInt(map.get("id"));

        /** Cria uma lista vazia contendo nome do campo e mensagem de erro.
         * @see FieldMessage
         */
        List<FieldMessage> list = new ArrayList<>();


        /**
         * Realizando validação de E-mail quando atualizamos o cliente
         * No Metodo tradicional, o erro sempre seria retornado caso o
         *  cliente atualizase outro campo e mantivesse o mesmo email.
         * Temos que controlar esse tipo de verificação.
         */

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "E-mail já existente"));
        }


        /**
         * O Framework não trabaha com FielMessade (Nós criamos)
         * Portanto, estamos percorrendo a lista de fieldmessage e para cara objeto que tiver na lista,
         *  vamos adicionar um erro correspondente na lista de erros do Framework.
         * Em miúdos, estamos pegando os objetos do tipo fieldMessage e inserindo erro correspondente, pegando a
         *  mensagem e o nome do campo.
         * Resumindo: Os comando abaixo permite transporta nossos erros personalizados para a lista de erros do Framework.
         */
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        /**
         * Caso não possia erro, o metodo retorna true.
         * Caso haja erro, noss lista NÃO estará VAZIA e retornará false.
         */
        return list.isEmpty();
    }


}