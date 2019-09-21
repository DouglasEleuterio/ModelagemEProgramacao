/*
 * Classe filha de Pagamento.
 * Especializada em pagamento com Cartão.
 * 
 */
package com.douglas.cursomc.domain;

import com.douglas.cursomc.domain.Enums.EstadoPagamento;
import javax.persistence.Entity;
/**
 *
 * @author douglas
 * @see Pagamento
 */
@Entity
public class PagamentoComCartao extends Pagamento{
    
    //Estrategia de salvamento definido na superclasse
    private static final long servialVersionUID = 1L;
    private Integer numeroDeParcelas;

    /**
     * Construtor
     */
    public PagamentoComCartao() {
    }
    //Construtor de subclasse já buscando os atributos da superclasse no construtor.
    public PagamentoComCartao(Integer numeroDeParcelas, Integer id, EstadoPagamento estado, Pedido pedido) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
    
    //Getters and Setters

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
