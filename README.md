# Implementando REST API para Gerenciamento de Registro de Heróis e Vilões do Universo Marvel e DC
> Este projeto consiste em uma API de gerenciamento de membros dos universos Marvel e DC, na qual foram implementados o Spring Webflux, que possibilita interagir de modo reativo.

[![Spring Badge](https://img.shields.io/badge/-Spring-brightgreen?style=flat-square&logo=Spring&logoColor=white&link=https://spring.io/)](https://spring.io/)
[![Maven Badge](https://img.shields.io/badge/-MAVEN-000?style=flat-square&logo=MAVEN&logoColor=white&link=https://maven.apache.org/)](https://maven.apache.org/)
[![MySQL Badge](https://img.shields.io/badge/-MySQL-blue?style=flat-square&logo=MySQL&logoColor=white&link=https://www.mysql.com/)](https://www.mysql.com/)


<img align="right" width="400" height="300" src="https://matheuspcarvalhoblog.files.wordpress.com/2018/05/spring-framework.png">

## Descrição da Aplicação
A aplicação consiste em operações que permitem gerenciar os registros de heróis e vilões (Marvel/DC), realizando funções como:
* Criar o cadastro de um membro (herói/vilão);
* Alterar o cadastro de um membro (herói/vilão);
* Localizar um membro por nome;
* Localizar um membro por seu ID de cadatro;
* Excluir o cadastro de um membro (herói/vilão);
* Listar todos os membros de todos os universos (Marvel/DC);

No projeto foi utilizado o framework Spring Reative Web, também conhecido como ``Webflux``, ele é um módulo que possibilita aplicações web com Spring trabalhem de forma reativa. Isto significa que este framework tem a caracteristica de ser ``assíncrono``, permitindo que ele inicie outro processo mesmo enquanto analisa outro, possibilitando uma maior interação com o cliente.

Para entender melhor esta dinâmica, vou apresentar como funciona um processo ``sincrôno``, na qual um cliente envia uma requisição para o servidor, que requisita um volume considerável de informações. Logo, o servidor levará um tempo para retornar uma devolutiva para o cliente, mas o cliente impaciente acabou enviando mais três requisições, na quais aguardarão o processamento da primeira requisição enviada até a primeira delas serem processadas, ficando desta forma bloqueadas.

Já no processo do WebFlux (*assíncrono*) as requisições chegam ao servidor e já são tratadas em tempo real, desta forma, utilizando o exemplo anterior, se uma requisição inicia no servidor, na qual requer uma grande quantidade de informações, e o cliente acaba enviando mais trẽs requisições, o servidor trabalhará de forma paralela tentando atendê-las o mais rápido possível. Isto significa, que qual terminar primeiro será devolvida ao cliente, evitando gargálos.

Para possibilitar que o framework Spring trabalha-se de forma reativa o ``Spring Data`` obteve modificações em sua estrutura, deste mode, ele permite utilizar os tipos Flux e Mono para serem mapeados como resultado. Na programação reativa é preciso utilizar os bancos de dados com suporte para a programação reativa, para que todo o fluxo seja assíncrono e não bloqueante. Desta forma, para este projeto estamos utilizando o MongoDB Atlas...

Quando é utilizado este tipo de banco, ao se fazer uma persistência de um dado, por exemplo, o driver inicia a transação e ao término, cria uma nova instancia desse objeto para ser retornado. 

Utilizando um banco de dados SQL tradicional, a transação de uma persistência fica bloqueada até que seja finalizada para a mesma referencia do objeto ser retornada, gerando assim, um ponto de bloqueio na aplicação. Por isso, para garantir que a aplicação seja não bloqueante em todos os pontos de processamento, é preciso utilizar drivers já adaptados para esse tipo de programação reativa.

Para utilizar o Spring Data com MongoDB, por exemplo, basta adicionar sua dependência no arquivo pom.xml.

E assim como no JpaRepository, é possivel criar um repository e estender o ReactiveMongoRepository para desfrutar de vários métodos prontos para transações com banco de dados, como por exemplo findAll, findById, entre outros. E também, é possível criar métodos customizados e obter como retorno um tipo Flux ou Mono.

Quando utilizar o modelo síncrono com Spring MVC ou assíncrono com Spring Webflux

O Spring Webflux não veio para substituir o Spring MVC. São maneiras diferentes de se construir aplicações web e cada uma tem seu propósito. Por isso, ao escolher entre uma ou outra, é preciso conhecer e definir o objetivo e necessidades da aplicação, para que assim, o melhor módulo seja utilizado.
Para quem se interessar…

Para quem gostou deste tema e gostaria de aprender mais, segue o link de um curso no Youtube onde há o passo a passo de como construir uma API REST reativa com Spring Webflux e MongoDB: 

https://medium.com/@michellibrito/spring-webflux-f611c8256c53

Já a API (*Application Programming Interface*) se trata de um conjunto de rotinas documentado que disponibiliza meios para que as requisições recebidas sejam devidamente tratadas utilizando do estilo de arquitetura computacional para implementações de serviços web, denominado REST (*Representational State Transfer*), possibilitando integrações entre aplicações e também para estruturas cliente e servidor em páginas web e aplicações.

Conceitualmente, o REST tem como premissa utilizar os métodos HTTP para definir a operação que está sendo efetuada e fornece como retorno a requisição protocolada, um código de operação, que identifica qual foi o procedimento realizado no lado do servidor, além de uma mensagem, que se trata do conteúdo (ou corpo) do protocolo de retorno.

Resumindo, a API REST trabalha como mensageiro, levando informações de um ponto a outro utilizando os requerimentos do protocolo HTTP para formatá-las.

No decorrer deste documento é apresentado com mais detalhes sua implementação, descrevendo como foi desenvolvida a estrutura da API, suas dependências e como foi colocado em prática o TDD para a realização dos testes unitários dos metodos na camada de negócio. Como implementamos do Spring Boot, para agilizar a análise do código e configurá-lo conforme nossas necessidades por meio dos *starters* agrupando as dependências, além do Spring Data JPA, que nos dá diversas funcionalidades permitindo uma melhor dinâmica nas operações com bancos de dados e sua manutenção.

## Setting Up the Project
O Apache Maven é uma ferramenta de apoio a equipes que trabalham com projeto Java (mas não se restringe somente a Java), possibilitando a desenvolvedores a automatizar, gerenciar e padronizar a construção e publicação de suas aplicações, permitindo maior agilidade e qualidade ao produto.
Abaixo são apresentadas as etapas para importá-lo a IDE IntelliJ, mas também é possível trabalhar com outras IDE's como Eclipsse, NetBeans, entre outras, podendo ser diferente os procedimentos realizados.

1. No menu principal, acesse File >> New >> Project from Existing Sources;
2. Selecione o diretório onde está projeto e clique "OK";
3. Selecione o arquivo pom.xml e clique em "Next";
4. Deixe as configurações padrões e clique em "Next";
5. Selecione a versão do seu JDK e clique em "Next";
6. Para finalizar, clique no botão "Finish".
7. Para o projeto e localize o arquivo ``ApiMemberApplication`` em src/main/br.com.supernova.apimembers;
8. Clique com o botão direito do *mouse* e selecione Run >> Spring Boot App, ou simplesmente *Run*.

![](/src/images/beerStock.png?w=400) 

Se tudo der certo a aplicação Spring será executada pelo seguinte endereço [http://localhost:8080/api/v1/members](http://localhost:8080/api/v1/members).


## O Maven
O Apache Maven é uma ferramenta que auxilia a equipes a trabalharem com projetos de desenvolvimento de software, possibilitando automatizar e padronizar a construção e publicação de aplicações. Ela é uma ferramenta de gerenciamento e automação de construção de projetos na qual estimula a adoção de boas práticas por utilizar o conceito de programação orientada a convenção. Isto permite uma melhor estruturação dos diretórios que constituí o projeto, desta forma, todos os integrantes do projeto possuíram a mesma estrutura padronizada, incluindo dependências, plugins e anotações.

Abaixo segue tabela que apresenta todas as dependẽncias e plugins utilizados neste projeto que estão presentes no arquivo ``pom.xml``:

|	Grupo		 |	Artefato				|	Versão		|
|----------------|--------------------------|---------------|
| Spring Boot 	 | Webflux					| 3.0.7 		|
| Spring Boot 	 | Data JPA					| 2.4.4 		|
| Spring Boot 	 | Validation				| 2.4.4 		|
| Spring Boot 	 | Web						| 2.4.4 		|
| Spring Boot 	 | DevTools					| 2.4.4 		|
| H2Database  	 | H2						| 1.4.200 		|
| Project Lombok | Lombok					| 1.18.18 		|
| MapStruct		 | MapStruct				| 1.4.1.Final 	|
| Spring Fox	 | Swagger					| 2.9.2 		|
| JUNIT			 | Jupiter					| 5.7.1 		|
| JUNIT			 | Mockito					| 3.6.28 		|
| JUNIT			 | Hamcrest					| 2.2 			|
| MySQL			 | MySQL					| 8.0.23 		|
| Hibernate		 | Hibernate Core 			| 5.4.30.Final 	|
| Hibernate		 | Hibernate EntityManager  | 5.4.30.Final 	|

Além de gerenciar dependências, o Maven permite acompanharmos o ciclo de vida do projeto até a concepção do software concluído. Isto inclui as seguintes funções:
* Facilitar a compilação do código, o empacotamento ([JAR], [WAR], [EAR]), a execução de testes unitários, etc;
* Unificar e automatizar o processo de geração do sistema;
* Centralizar informações organizadas do projeto, incluindo suas dependências, resultados de testes, documentação, etc;
* Reforçar boas práticas de desenvolvimento, tais como: separação de classes de teste das classes do sistema, uso de convenções de nomes e diretórios, etc;
* Ajudar no controle das versões geradas (*releases*) dos seus projetos.

## Spring FOX - Swagger
O Spring Fox é o framework que possibilita a importação do Swagger como dependência ao projeto e a integrá-la ao framework do Spring, desta forma, permitindo utilizar suas anotações para uso de suas funcionalidades para o contexto de documentação do projeto.

Para isso, se faz necessário criar uma classe de configuração com o nome SwaggerConfig (o nome padrão do framework), contendo a anotação @Configuration em conjunto com o anotação @EnableSwagger2 com o seguinte conteúdo.
```sh
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(constructorApiInfo());
	}
	
	private Predicate<RequestHandler> apis(){
		return RequestHandlerSelectors.basePackage("br.com.supernova.apimembers");
	}

	public ApiInfo constructorApiInfo() {
		return new ApiInfoBuilder()
				.title(API_TITLE)
				.description(API_DESCRIPTION)
				.version("1.0.0")
				.contact(new Contact(CONTACT_NAME, CONTACT_GITHUB, CONTACT_EMAIL))
				.build();		
	}
}
```

O método ``api`` é o responsável por estruturar o documento através das anotações que são incluídas nas classes que possibilita inserir uma nota de explicação sobre o parâmetro ou função. Ele também é um Bean para o Spring desta forma encontrasse anotado com [@Bean]. Já os dois restantes, são complementares ao método principal, na qual o método ``apis`` restringe a qual projeto deve ser coletadas as anotações e o método ``constructorApiInfo`` permite que parametrizamos com informações de identificação do projeto.

Esta classe (SwaggerConfig) precisa ficar na pasta raiz do projeto, na qual podesse criar uma subpasta denominada como ``config/`` para hospedá-la no projeto Spring.

> NOTA: Para visualizar a documentação em seu ambiente local, acesse o seguinte link após subir a aplicação: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## Estrutura do Projeto Spring
O projeto segue uma estrutura pré moldada pelo Maven quando parametrizamos o Spring Boot pelo Initializr (ou pelo STS), praticamente com as auto configurações que o framework implementa, já temos um projeto funcional para "subir" a aplicação. Por exemplo, ao informar no Initializr que utilizaremos o Spring Data JPA, automaticamente é configurado um *datasource*. Mas no decorrer do desenvolvimento, surgiram necessidades nas quais inserimos conforme necessário. Abaixo segue a estrutura de pastas do projeto com a descrição de sua finalidade.

* config/ - é onde fica a classe de configuração do Swagger, na qual passa os parâmetros para o framework elaborar a documentação do projeto;
* controller/ - é onde fica as classes responsáveis pela preparação dos dados a serem exibidos (view) e sua forma de exibição, na qual controla as requisições indicando quem deve tratá-las e que deve responde-las;
* dto/ - é onde fica as classes que são responsáveis por transferir dados de uma camada para outra camada do ciclo de requisição, desta forma, ocultando a camada de persistência;
* enums/ - é onde estão as classes que são tipos de campos que consistem em um conjunto de constantes, sendo como um objeto (Array) que contém valores pré-definidos para um campo de um objeto.
* exception/ - é onde fica as classes de exceção (Exception) na qual foram implementadas para tratar eventuais lançamentos de situações em que a aplicação necessita de uma resposta para uma ação excepcional ou de algum processo sistêmico;
* mapper/ - é onde fica a interface que mapeia as declarações e possuí a constante que é uma instância de Mapper. Ela pertence ao framework [MapStruct] para conversão de objetos DTO em objtos Entity (JPA) (e vice-versa), possibilitando gerar código automaticamente, onde é avaliada a compatibilidade entre tipos e nomes de variáveis dos objetos.  
* model/ - é onde fica as classes responsáveis pelas interfaces com as entidades (Entity) do banco de dados, são representantes das tabelas nas quais são representadas por classes e mapeadas pelO Hibernate/JPA;
* repository/ - é onde fica a interface que é responsável por reduzir a quantidade de código necessário para implementar a camada de persistência de dados, nela que estão centradas as operações realizadas pela JPA por estender de ``JpaRepository``;
* service/ - é onde fica a classe que possui as regras de negócio da aplicação, na qual interage com as camadas de persistência e controle para obter os dados a serem validados pelas lógicas implementadas em suas funcionalidades.

### Teste Unitários (JUnit, Mockito e Hamcrest)
Para o processo de testes o projeto contou com o framework JUnit para realização de testes unitários. O conceito de teste é integrado ao ciclo de vida do Maven, na qual existem dois estágios dentro do ciclo de vida do _build_, o teste unitário e o teste de integração, mas foi focado o desenvolvimento em testes unitários para testar individualmente as funcionalidades da API.
Foi utilizado o novo padrão, o JUnit 5, esta versão integra recursos do ``Java 8``, como expressões stream e lambda. O JUnit 5 empacota seus componentes no grupo [org.junit.jupiter], desta forma para utilizá-lo, foi excluída a dependência do JUnit Vintage (JUnit 4) e o próprio Spring Boot se encarrega de implementar o JUnit5, junto com o Mockito e o Hamcrest.
```sh
<dependency>
  <groupId>org.hamcrest</groupId>
  <artifactId>hamcrest</artifactId>
  <version>2.2</version>
  <scope>compile</scope>
</dependency>
<dependency>
  <groupId>org.junit.jupiter</groupId>
  <artifactId>junit-jupiter</artifactId>
  <version>5.7.1</version>
  <scope>compile</scope>
</dependency>
<dependency>
  <groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <version>3.6.28</version>
  <scope>compile</scope>
</dependency>
```

Para criar os testes foram utilizados os conceitos do TDD (*Test Driven Development / Desenvolvimento orientado a teste*) que faz parte da metodologia XP, mas nada impede que seja utilizado individualmente ou por outras metodologias. 
Sua prática preconiza que deve-se primeiro escrever os testes, antes de implementar o sistema. Os testes são utilizados para facilitar o entendimento da funcionalidade a ser desenvolvida. A idéia principal é criar um teste unitário para uma funcionalidade constituisse em três passos simples:

1. Implementar um teste para uma funcionalidade, antes mesmo de desenvolver a funcionalidade, desta forma, sua execução retornará ``FAIL``;
2. Implementar uma solução, do modo mais simples possível, para fazê-la passar no teste;
3. Refatorar a solução aplicando as melhores práticas no desenvolvimento da lógica de programação a fim de refiná-lo.

Desta forma, com a aplicação de testes o processo de desenvolvimento torna-se mais confiável, também com o auxilio dos testes é possível verificar se as regras de negócios foram bem assimiladas. 

Abaixo segue as etapas atribuídas para a execução de um teste unitário a uma funcionalidade em Service, na qual o Maven cria uma estrutura propicia para sua execução em ``src/test/java/pastaprojeto``.

1. Criado o teste implementando a regra de negócio para funcionalidade mais sem implementar o método responsável pela funcionalidade;
```sh
@Test
void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
    Beer inspectBeer = new Beer(1L, "Heinecken", "FEMSA", 60, 10, "LAGER");

    Beer createBeer = beerService.createBeer(inspectBeer);

    // HAMCREST
    MatcherAssert.assertThat(createBeer.getId(), Matchers.is(Matchers.equalTo(inspectBeer.getId())));
    MatcherAssert.assertThat(createBeer.getName(), Matchers.is(Matchers.equalTo(inspectBeer.getName())));
    MatcherAssert.assertThat(createBeer.getQuantity(), Matchers.is(Matchers.equalTo(inspectBeer.getQuantity())));

}
```

Desta forma, através da biblioteca do Hamcrest, podemos utilizar seus métodos para deixar a sintaxe na verificação dos valores retornados de modo mais legível, utilizando os métodos estáticos.
```sh
	// HAMCREST - IMPORT STATIC
    assertThat(createBeer.getId(), is(equalTo(inspectBeer.getId())));
    assertThat(createBeer.getName(), is(equalTo(inspectBeer.getName())));
    assertThat(createBeer.getQuantity(), is(equalTo(inspectBeer.getQuantity())));
```

Também poderiamos utilizar os métodos do JUnit 5 para realizar esta verificação conforme o código apresentado abaixo:
```sh
// JUNIT JUPITER
   assertEquals(inspectBeerDTO.getId(), createBeerDTO.getId());
   assertEquals(inspectBeerDTO.getName(), createBeerDTO.getName());
```
Mas a sintaxe do Hamcrest apresenta melhor clareza do que é avaliado.

2. Depois é criado o método implementando a funcionalidade mais simples para permitir que o teste seja validado;
```sh
public Beer createBeer(Beer beer) {
		Beer savedBeer = beerRepository.save(beer);
		return bsavedBeer;
	}
```

3. Refatorar a lógica ao implementar uma verificação se não existe objeto já criado com o mesmo nome para permitir salvá-lo no banco de dados utilizando a verificação do Mockito (when/then);
```sh
@Test
void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
    BeerDTO inspectBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
    Beer inspectBeer = beerMapper.toModel(inspectBeerDTO);

    // WHEN - MOCKITO
    when(beerRepository.findByName(inspectBeerDTO.getName())).thenReturn(Optional.empty());
    when(beerRepository.save(inspectBeer)).thenReturn(inspectBeer);

    // THEN
    BeerDTO createBeerDTO = beerService.createBeer(inspectBeerDTO);

    // HAMCREST - IMPORT STATIC
    assertThat(createBeerDTO.getId(), is(equalTo(inspectBeerDTO.getId())));
    assertThat(createBeerDTO.getName(), is(equalTo(inspectBeerDTO.getName())));
    assertThat(createBeerDTO.getQuantity(), is(equalTo(inspectBeerDTO.getQuantity())));

}
```
Já o método com a lógica refatorada ficará da seguinte forma:
```sh
public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
	checkIfThereIsaRecord(beerDTO.getName());
	Beer beer = beerMapper.toModel(beerDTO);
	Beer savedBeer = beerRepository.save(beer);
	return beerMapper.toDTO(savedBeer);
}
```

Agora não é mais passado um objeto Entidade (Entity), mas sim, um objeto *Data Transfer Object* (DTO) que é associado a tranferência de dados entre camadas na aplicação, na qual é passado como parâmetro para um método auxiliar (checkIfThereIsaRecord) para verificar se existe um objeto persistido com o nome do objeto a persistir, na qual, lançará uma exceção (BeerAlreadyRegisteredException) caso encontre o objeto persistido. Caso contrário, segue para ser convertido em um objeto *Entity* através de um [MapStruct], que é um gerador de código que mapeia tipos de [beans Java] convertindo para outro objeto, neste caso, de DTO para Entity. Desta forma, é possível passá-lo como parâmetro para classe JPA responsável por realizar a persistência do objeto, através do método [save]. Para finalizar, novamente é utilizado o [MapStruct] para converter a Entity para DTO para retornar o método.

Este processo de testes unitários garantiu que a implementação da API REST esta assegurada de falhas unitárias e permitiu observar pontos de risco mais claramente, permitindo um melhor estratégia para o seu desenvolvimento.

### O Uso do MySQL
Ao utilizar o JPA (através do Spring Data) podemos utilizar para configurar e até mesmo trocar o banco de dados, no ínicio do projeto foi incluso o H2, que é um banco que utiliza a memória para instanciar dados, mas foi utilizado somente para testes. Para a aplicação foi selecionado o SGBD MySQL, mas poderia ser qualquer outro como o PostgreSQL, para isso, foi implementado o arquivo pom.xml com a dependẽncia do driver JDBC abaixo:
```sh
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
```

Para concluir, foi implementado o arquivo application.properties informado a URL de conexão do JDBC, usuário e senha, além de parametrizar o hibernate a criar e excluir o banco após reiniciar a aplicação. O caminho do arquivo encontra-se em ``src/main/resources/``: 
```sh
spring.datasource.url=jdbc:mysql://localhost/_nome_bancodados
spring.datasource.username=root
spring.datasource.password=_senha_banco
spring.jpa.hibernate.ddl-auto=create-drop
```

Utilizamos o banco ``beerstock`` criado no MySQL, sem tabelas ou qualquer outro objeto do banco. Quanto ao usuário, foi utilizado o root e a senha que foi atribuído a ele. Além disso, foi configurado a propriedade ``ddl-auto``, para recriar o banco de dados todas as vezes que o projeto se iniciar. 
> NOTA: Esta configuração não pode ser implementada em Ambiente de Produção


## Agradecimentos

Obrigado por ter acompanhado aos meus esforços para desenvolver este Projeto utilizando o Maven e a estrutura do Spring para criação de uma API REST! :octocat:

Como diria um antigo mestre:
> *"Cedo ou tarde, você vai aprender, assim como eu aprendi, que existe uma diferença entre CONHECER o caminho e TRILHAR o caminho."*
>
> *Morpheus - The Matrix*