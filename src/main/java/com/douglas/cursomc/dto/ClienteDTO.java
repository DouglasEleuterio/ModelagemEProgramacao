package com.douglas.cursomc.dto;

import com.douglas.cursomc.domain.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * Classe do tipo ClienteDTO.
 * Nosso ClienteDTO não terá os mesmos atributos que nossa classe de dominio.
 * Isso por que, NOSSO CLIENTE NÃO PODE ALTERAR O CPFOUCNPJ.
 * Será incluido apenas id, nome e email do Cliente.
 * Validação: nome - maior que 5 e menor que 120, não pode ser nulo.
 *            email - e-mail válido, não pode se nulo
 */
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Preenchimento Obrigatório")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;
    @NotEmpty(message = "E-mail obrigatorio")
    @Email(message = "E-mail invalido")
    private String email;

    public ClienteDTO(){}

    /**
     * Construtor que recebe um cliente e gera o DTO.
     * @return
     */
    public ClienteDTO(Cliente obj){
        id = obj.getId();
        nome = obj.getNome();
        email = obj.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
