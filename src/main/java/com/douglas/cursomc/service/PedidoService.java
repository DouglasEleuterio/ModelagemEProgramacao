package com.douglas.cursomc.service;

import java.util.Date;
import java.util.Optional;

import com.douglas.cursomc.domain.Enums.EstadoPagamento;
import com.douglas.cursomc.domain.ItemPedido;
import com.douglas.cursomc.domain.PagamentoComBoleto;
import com.douglas.cursomc.repositories.ItemPedidoRepository;
import com.douglas.cursomc.repositories.PagamentoRepository;
import com.douglas.cursomc.repositories.ProdutoRepository;
import com.douglas.cursomc.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.cursomc.domain.Pedido;
import com.douglas.cursomc.repositories.PedidoRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de serviço responsável por intermediar as transações entre Repository(BD) e Controladores.
 * Tal classe invoca os metodos da classe Repository.
 * Os tratamentos de erros, filtros, controle de acesso e demais opções são de responsabilidade desta classe.
 * A classe de acesso aos dados, ficará responsável apenas por acessar aos dados.
 * A Classe de Controladores, ficará responsável apenas por "conversar" com o "FRONT-END".
 *
 * @author Douglas Eleutério
 */
@Service
public class PedidoService {

    @Autowired //Injeção de Dependencia do Spring
    private PedidoRepository repo; // repo passa a ser o objeto instanciado, injetado e controlado pelo Spring para acessar a classe "Repository" para prover os dados.

    @Autowired
    BoletoService boletoService;

    @Autowired
    PagamentoRepository pagamentoRepository;

    @Autowired
	ProdutoService produtoService;

    @Autowired
	ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    /**
     * Nova implementação do JAVA 8
     * Caso o objeto com id não seja encontrado, será lançada nossa exceção personalizada.
     * É de responsabilidade da camada de Controle enviar o erro, cabendo a camada de serviço a implementação.
     *
     * @param id - Receberá um id para realizar a busca do objeto.
     * @return Objeto do tipo pedido
     * @throws ObjectNotFoundException - Caso não localize o ID
     * @throws ObjectNotFoundException - Será lançado caso não localize o obj com id informado.
     * @see ObjectNotFoundException
     */
    @Transactional
    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto Não Encontrado!	Id: " + id + " , Tipo: " + PedidoService.class.getName()
        ));
    }

    /**
     * Inserindo novo Pedido no sistema.
     *
     * @param obj
     * @return
     */
    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if (obj.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
        }
        obj = repo.save(obj);
        pagamentoRepository.save(obj.getPagamento());
        for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
        }
		itemPedidoRepository.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }
}