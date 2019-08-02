package com.douglas.cursomc;

import com.douglas.cursomc.domain.Categoria;
import com.douglas.cursomc.domain.Produto;
import com.douglas.cursomc.repositories.CategoriaRepository;
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

    }
}
