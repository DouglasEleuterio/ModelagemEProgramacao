package com.douglas.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.domain.Enums.TipoCliente;
import com.douglas.cursomc.dto.ClienteNewDTO;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.resources.exceptions.FieldMessage;
import com.douglas.cursomc.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteRepository repo;

    /**
     * Pode-se colocar algumas regras de inicialização.
     * @param ann
     */
    @Override
    public void initialize(ClienteInsert ann) {
    }

    /**
     * Metodo que verifica se nosso Tipo-Objeto recebido é válido ou não.
     * @param objDto - Recebe um Objeto ClienteNewDTO
     * @param context
     * @return true caso seja válido, ou false caso constrário.
     */
    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

        /** Cria uma lista vazia contendo nome do campo e mensagem de erro.
         * @see FieldMessage
         */
        List<FieldMessage> list = new ArrayList<>();

        /**
         * Realizando comparações.
         * Caso o tipo de Pessoa seja física, faremos validação de CPF.
         * Caso o tipo de Pessoa seja juridica, faremos validação de CNPJ.
         */

        //Validando CPF
        if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CPF Inválido"));
        }
        //Validando CNPJ
        if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("cpfOuCnpj","CNPJ Inválido"));
        }

        /**
         * Realizando validação de E-mail
         */

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if (aux != null){
            list.add(new FieldMessage("email","E-mail já existente"));
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