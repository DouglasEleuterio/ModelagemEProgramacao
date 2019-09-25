package com.douglas.cursomc.domain.Enums;

/**
 * Tipo de Cliente.
 * <br/>Classe responsável por representar os Tipos de Clientes que nossos
 * clientes podem assumir.
 * <br/> &nbsp PESSOAFISICA = 1
 * <br/> &nbsp PESSOAJURIDICA = 2
 * <br/>Atributos:
 * <br/> &nbsp cod : Integer
 * <br/> &nbsp descricao : String Metodos: Getters and Setters
 *
 * @see Cliente
 * @author douglas eleuterio
 * @version 0.2.0
 */

public enum TipoCliente {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int cod;
    private String descricao;

    private TipoCliente(int cod, String descricao) {
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
     * Metodo que retorna o tipo do Clienteno formato String. 
     * O tipo do cliente é convertido e retornado na forma textual.
     * @param cod
     * @return tipoCliente (String)
     * @exception Id Inválido
     */
    //Recebendo o codigo e o objeto do tipo cliente.
    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (TipoCliente x : TipoCliente.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido:" + cod);
    }
}
