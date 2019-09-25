package com.douglas.cursomc.domain.Enums;

/**
 * Estado de Pagamento. 
 * <br/>Classe responsável por representar os Estados que 
 *  um pagamento pode assumir dentro do sistema, são eles: 
 * <br/> &nbsp PENDENTE = 1
 * <br/> &nbsp QUITADO = 2 
 * <br/> &nbsp CANCELADO =3
 * <br/>Atributos:
 *  <br/> &nbsp cod : Integer
 *  <br/> &nbsp descricao : String
 * Metodos: Getters and Setters
 *
 * @see Pagamento
 * @author douglas eleuterio
 * @version 0.2.0
 */

public enum EstadoPagamento {
    
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3,"Cancelado");
    
    private int cod;
    private String descricao;

    private EstadoPagamento(int cod, String descricao) {
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
     * Metodo que retorna o estado do pagamento no formato String.
     * O estado do pagamento é convertido e retornado na forma textual.
     * @param cod
     * @return tipoPagamento (String)
     * @exception Id Inválido
     */
    //Recebendo o codigo e o objeto do tipo Pagamento.
    public static EstadoPagamento toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for (EstadoPagamento x : EstadoPagamento.values()){
            if (cod.equals(x.getCod())){
                return x;
            }
        }
        throw new IllegalArgumentException("Id inválido:" + cod);
    }
}
