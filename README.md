[![Build Status](https://travis-ci.org/DouglasEleuterio/ModelagemEProgramacao.svg?branch=MySQL)](https://travis-ci.org/DouglasEleuterio/ModelagemEProgramacao)
# Sistema de Pedidos

Sistema de Pedidos implementado para atender os requisitos aprensentados no Curso de Modelagem conceitual do Prof. Dr. Nélio Alves.
<h3>Deseja-se fazer um sistema de pedidos.</h3>
<br>
<ul>
  
  <h2>Requisitos</h2>
  <li>Um ou mais produtos podem ser vendidos em cada pedido, sendo que a cada produto pode ser dado um desconto diferente, e cada
    produto pode ser vendido em uma ou mais unidades.</li>
  <li>Cada produto possui nome e preço, e pode pertencer a várias categorias.</li>
  <li>Cada pedido é feito por um cliente, que deve ter em seu cadastro nome, telefones, email, cpf ou cnpj, e um ou mais endereços,           sendo que o cliente deve especificar um endereço para entrega na hora de comprar.</li>
  <li>Para um pedido, deve ser registrado o instante em que é realizado e o endereço de entrega.</li>
  <li>Um pedido deve ser pago ou por boleto, ou por cartão de crédito.</li>
  <li>No caso de boleto, deve-se armazenar a data de vencimento e a data de pagamento.</li>
  <li>No caso de cartão de crédito, deve-se armazenar o número de parcelas.</li>
  <li>Todo pagamento possui um estado (pendente, quitado ou cancelado).</li>
</ul>

## Arquitetura da Aplicação.
![image](https://user-images.githubusercontent.com/28332522/62130909-6e897080-b2b0-11e9-934e-e298217e94c8.png)

## Diagrama de Classes 
https://github.com/DouglasEleuterio/ModelagemEProgramacao/files/3446856/Diagrama.de.Classes.pdf

## Diagrama de Objetos Instanciados (Simulação)
[Instancias.pdf](https://github.com/DouglasEleuterio/ModelagemEProgramacao/files/3446871/Instancias.pdf)



## Getting Started
Para realizar o Download do projeto acesse: https://github.com/DouglasEleuterio/ModelagemEProgramacao.git ou clone através do seu gerenciador GIT.

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Requisitos
<ul>
  <li>JDK 1.8 ou Superior</li>
<li>Maven 3.5.0 ou Superior.</li>
<li>Banco de dados H2 Será configurado automaticamente caso deixe as configuraços da forma que está.
    Caso queira alterar as configuraçoes, acesse o arquivo application.properties na pasta resources.</li>
<li>IDE de sua preferencia. Foi utilizado Spring Tools Suite e Netbeans durante o desenvolvimento.</li>
</ul>


### Instalando

Após realizado o Download ou Clone do projeto os seguintes passos devem ser efetuados.
<ol>

<li> Empacotar o código compilado. </li>
  
Acesse a pasta do projeto "ModelagemEProgramacao" e execute o código (Necessita do Maven instalado).
  
```
mvn -package
``` 
  
Após realizar o procedimento, será criado o arquivo JAR do projeto.
  
Acesse a pasta que foi criada, que por padrão, estará em "~/ModelagemEProgramacao/target/".

<li> Executar o Projeto </li>

Dentro da pasta "target" execute o seguinte comando.

```
java -jar cursomc-0.0.1-SNAPSHOT.jar
```
</ol> 

### Acessando os EndPoints

Após realizar os procedimentos mencionados acima os EndPoints estarão dispoíveis.

São 3 EndPoints disponíveis:

<ul> 
  <li> Categorias </li>
  
    http://localhost:8080/categorias/1
    
  <li> Clientes </li>  
  
    http://localhost:8080/clientes/1
    
  <li> Pedidos </li>
  
    http://localhost:8080/pedidos/1
    
</ul>



## Desenvolvimento
Construido com base no curso do Prof. Acelino Alves 

Douglas Eleutério Ferreira
Graduando em Engenharia de Software pela UniCesumar 2019 - 2022


## Authors

* **Douglas Eleutério** - *Curso de Modelagem Conceitual* - [PurpleBooth](https://github.com/DouglasEleuterio)


## License

Este Projeto está licenciado nos termos da MIT License - veja sobre em [LICENSE.md](LICENSE.md) 


Template Adaptado do modelo disponível em
```
https://gist.github.com/PurpleBooth/109311bb0361f32d87a2
```
