package com.douglas.cursomc;

import com.douglas.cursomc.categoria.Categoria;
import com.douglas.cursomc.repositories.CategoriaRepository;
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
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    }

}
