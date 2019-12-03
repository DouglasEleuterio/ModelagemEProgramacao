package com.douglas.cursomc.domain.Enums;

import javax.persistence.Id;

/**
 * <h3>Enum com perfis de Clientes</h3>.
 * <br/>Classe responsável por representar os Perfis que
 *      os usuarios podem pertencer.
 * <br/> &nbsp ADMIN = 1
 * <br/> &nbsp CLIENTE = 2
 * <br/>Atributos:
 *  <br/> &nbsp cod : Integer
 *  <br/> &nbsp descricao : String
 * Metodos: Getters and Setters
 *
 * @author douglas eleuterio
 * @version 0.2.0
 * @since 03/12/2019
 */

public enum Perfil {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private int cod;
    private String descricao;

    private Perfil(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    /**
     * <h3>Metodo que retorna o Perfil do usuario no formato String.</h3>
     * O perfil do usuário é convertido e retornado na forma textual.
     * @param cod
     * @return tipoPagamento (String)
     * @exception Id Inválido
     */
    public static Perfil toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (Perfil x : Perfil.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido:" + cod);
    }
}
