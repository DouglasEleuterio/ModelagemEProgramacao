package com.douglas.cursomc;

import com.douglas.cursomc.domain.Categoria;
import com.douglas.cursomc.domain.Cidade;
import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.domain.Endereco;
import com.douglas.cursomc.domain.Enums.EstadoPagamento;
import com.douglas.cursomc.domain.Estado;
import com.douglas.cursomc.domain.Produto;
import com.douglas.cursomc.domain.Enums.TipoCliente;
import com.douglas.cursomc.domain.ItemPedido;
import com.douglas.cursomc.domain.Pagamento;
import com.douglas.cursomc.domain.PagamentoComBoleto;
import com.douglas.cursomc.domain.PagamentoComCartao;
import com.douglas.cursomc.domain.Pedido;
import com.douglas.cursomc.repositories.CategoriaRepository;
import com.douglas.cursomc.repositories.CidadeRepository;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.repositories.EnderecoRepository;
import com.douglas.cursomc.repositories.EstadoRepository;
import com.douglas.cursomc.repositories.ItemPedidoRepository;
import com.douglas.cursomc.repositories.PagamentoRepository;
import com.douglas.cursomc.repositories.PedidoRepository;
import com.douglas.cursomc.repositories.ProdutoRepository;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * Classe principal do Spring.
 * Tal class é responsável por inicializar o projeto no Tomcat
 * Foi implementado CommandLineRunner para executar comandos assim que a aplição for insntanciada.
 *
 * @author douglas
 * @since 31/07/2019
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {


    /**
     * Metodo responsável por iniciar a aplicação.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    /**
     * Instanciacao dos dados Categoria ao iniciar a aplicacao.
     * Inicializado as categorias Informatica e Escrittório conforme modelo conceitual.
     *
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

    }
}
