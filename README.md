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
1 - Empacotar o código compilado. 
    Acesse a pasta do projeto "ModelagemEProgramacao" e execute o código (Necessita do Maven instalado)
    ```
    mvn -package
    ``` 
    Após realizar o procedimento, será criado o arquivo JAR do projeto.
    Acesse a pasta que foi criada que por padrão estará em "ModelagemEProgramacao/target/"

2 - Instalar o pacote no repositório local.
    Dentro da pasta "target" execute o seguinte comando.
    ```
    java -jar cursomc-0.0.1-SNAPSHOT.jar
    ```
  

### Acessando os EndPoints

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc


Template Adaptado do modelo disponível em
```
https://gist.github.com/PurpleBooth/109311bb0361f32d87a2
```
