package com.douglas.cursomc.domain;

import com.douglas.cursomc.domain.Enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Entity;

/**
 * SubClasse de Pagamento.
 * <br/>Classe responsável por representar os pagamentos com boleto, 
 *  sendo persistidos no banco de dados.
 * <br/>Em nosso sistema, cada pagamento (especialização) terá sua tabela no banco.
 * <br/>Nome da Tabela no Banco de Dados: pagamentoComBoleto.
 * <br/>Atributos: 
 *  <br/> &nbsp id : Definido na SuperClasse 
 *  <br/> &nbsp Date : dataVencimento
 *  <br/> &nbsp Date : dataPagamento
 * <br/>Metodos: Getters and Setters, HashCode e Equals.
 * @see Pagamento
 * @author douglas eleuterio
 * @version 0.2.0
 */

@Entity(name = "pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento{
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