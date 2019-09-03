package com.douglas.cursomc;

import com.douglas.cursomc.domain.Categoria;
import com.douglas.cursomc.domain.Cidade;
import com.douglas.cursomc.domain.Cliente;
import com.douglas.cursomc.domain.Endereco;
import com.douglas.cursomc.domain.Estado;
import com.douglas.cursomc.domain.Produto;
import com.douglas.cursomc.domain.TipoCliente;
import com.douglas.cursomc.repositories.CategoriaRepository;
import com.douglas.cursomc.repositories.CidadeRepository;
import com.douglas.cursomc.repositories.ClienteRepository;
import com.douglas.cursomc.repositories.EnderecoRepository;
import com.douglas.cursomc.repositories.EstadoRepository;
import com.douglas.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
/**
 * Classe principal do Spring.
 * Tal class é responsável por inicializar o projeto no Tomcat
 * Foi implementado CommandLineRunner para executar comandos assim que a aplição for insntanciada.
 * @author douglas
 * @since 31/07/2019
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

    @Autowired
    CategoriaRepository categoriaRepository; //Injeção do repository para salvar os dados.
    @Autowired 
    ProdutoRepository produtoRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    
    
    /**
     * Metodo responsável por iniciar a aplicação.
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    /**
     * Instanciacao dos dados Categoria ao iniciar a aplicacao.
     * Inicializado as categorias Informatica e Escrittório conforme modelo conceitual.
     * @param args
     * @throws Exception 
     */
    @Override
    public void run(String... args) throws Exception {
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritório");
        
        
        Produto p1 = new Produto(null, "Computador",2000.00);
        Produto p2 = new Produto(null, "Ipressora",800.00);
        Produto p3 = new Produto(null, "Mouse",80.00);
        
        //Relacionando categorias aos produtos
        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));//Relacionando produtos as categorias
        cat2.getProdutos().addAll(Arrays.asList(p2));//Relacionando a impressora com escritório
        
	//Relacionando os produtos as categorias.
	p1.getCategorias().addAll(Arrays.asList(cat1));
       	p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
	p3.getCategorias().addAll(Arrays.asList(cat1));

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
	produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
        
        //Instanciando as Cidades e estados
        //Estados
        Estado est1 = new Estado(null, "São Paulo");
        Estado est2 = new Estado(null, "Minas Gerais");
        //Cidades
        Cidade cid1 = new Cidade(null, "Uberlandia", est2);
        Cidade cid2 = new Cidade(null, "São Paulo", est1);
        Cidade cid3 = new Cidade(null, "Campinas", est1);
        //Informando ao estado, as cidades que o compoem.    
        est1.getCidades().addAll(Arrays.asList(cid1));
        est2.getCidades().addAll(Arrays.asList(cid2,cid3));
        //Salvando Estado e Cidade no banco conforme lista
        estadoRepository.saveAll(Arrays.asList(est1,est2));
        cidadeRepository.saveAll(Arrays.asList(cid1,cid2,cid3));
        
        Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "03555759140", TipoCliente.PESSOAFISICA);
        cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
        
        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cid1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);
        
        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        
        clienteRepository.saveAll(Arrays.asList(cli1));
        enderecoRepository.saveAll(Arrays.asList(e1,e2));
    }
}
