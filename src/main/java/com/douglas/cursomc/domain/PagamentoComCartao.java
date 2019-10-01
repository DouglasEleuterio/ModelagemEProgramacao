package com.douglas.cursomc.domain;

import com.douglas.cursomc.domain.Enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

/**
 * SubClasse de Pagamento.
 * <br/>Classe responsável por representar os pagamentos com cartão, 
 *  sendo persistidos no banco de dados.
 * <br/>Em nosso sistema, cada pagamento (especialização) terá sua tabela no banco.
 * <br/>Nome da Tabela no Banco de Dados: pagamentoComCartao.
 * <br/>Atributos: 
 *  <br/> &nbsp Integer : Número de Parcelas
 * <br/>Metodos: Getters and Setters, HashCode e Equals.
 * @see Pagamento
 * @author douglas eleuterio
 * @version 0.2.0
 */

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento{
    //Estrategia de salvamento definido na superclasse
    private static final long servialVersionUID = 1L;
    
    private Integer numeroDeParcelas;

    public PagamentoComCartao() {
    }
    //Construtor de subclasse já buscando os atributos da superclasse no construtor.
    public PagamentoComCartao(Integer numeroDeParcelas, Integer id, EstadoPagamento estado, Pedido pedido) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
