/*
 * Classe filha de Pagamento.
 * Especializada em pagamento em Boleto.
 * 
 */
package com.douglas.cursomc.domain;

import com.douglas.cursomc.domain.Enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Entity;
/**
 *
 * @author douglas
 * @see Pagamento
 */
@Entity
public class PagamentoComBoleto extends Pagamento{
    
    //Estrategia de salvamento definido na superclasse
    private static final long servialVersionUID = 1L;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataVencimento;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dataPagamento;

    /**
     * Construtores
     */
    public PagamentoComBoleto() {
    }
    //Construtor de subclasse já buscando os atributos da superclasse no construtor.
    public PagamentoComBoleto(Date dataVencimento, Date dataPagamento, Integer id, EstadoPagamento estado, Pedido pedido) {
        super(id, estado, pedido);
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
    }
    
    //Getters and Setters
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}

