# design-patters

## DIO BootCamp

# Sistema de Gestão de Clientes

## Visão Geral

O **Sistema de Gestão de Clientes** é uma aplicação baseada em Java projetada para gerenciar informações de clientes e seus endereços. Este projeto utiliza o Spring Framework para injeção de dependências e gerenciamento de transações e integra-se ao serviço ViaCEP para lidar com códigos postais brasileiros.

## Padrões de Design

- **Singleton**: Utiliza o padrão Singleton para garantir que o Spring gerencie uma única instância de cada componente (por exemplo, `ClienteRepository`, `EnderecoRepository` e `ViaCepService`). Isso é alcançado usando a anotação `@Autowired` do Spring para injeção de dependências, garantindo que apenas uma instância de cada componente seja criada e compartilhada em toda a aplicação.

- **Strategy**: Implementa o padrão Strategy ao definir métodos em interfaces (por exemplo, `ClienteService`) e fornecer implementações específicas (por exemplo, `ClienteServiceImpl`). Esse padrão permite que diferentes algoritmos ou operações sejam definidos e utilizados de forma intercambiável, tornando o sistema mais flexível e extensível.

- **Facade**: Aplica o padrão Facade para fornecer uma interface simplificada para subsistemas complexos. Por exemplo, `ClienteServiceImpl` abstrai as integrações com subsistemas como `EnderecoRepository` e `ViaCepService`, oferecendo uma interface simples para operações com clientes, enquanto oculta a complexidade das interações subjacentes.

## Funcionalidades

- **Operações CRUD**: Criar, Ler, Atualizar e Excluir informações de clientes.
- **Gerenciamento de Endereços**: Busca e armazena automaticamente informações de endereços usando códigos postais.
- **Integração com ViaCEP**: Usa a API ViaCEP para buscar detalhes de endereço com base em códigos postais.
- **Documentação da API**: Documentação interativa da API com Swagger.

## Tecnologias Utilizadas

- **Java**: Linguagem de programação
- **Spring Framework**: Injeção de dependências e gerenciamento de transações
- **Spring Data JPA**: Persistência de dados e gerenciamento de repositórios
- **Banco de Dados H2**: Banco de dados em memória para desenvolvimento e testes
- **API ViaCEP**: Serviço externo para consulta de endereços
- **Swagger**: Documentação da API
- **Maven**: Gerenciamento de builds e dependências

## Instalação

### Pré-requisitos

- **Java Development Kit (JDK)** 11 ou superior
- **Apache Maven** 3.6 ou superior

### Banco de Dados

O H2 é um banco de dados em memória usado para desenvolvimento e testes. Não é necessário criar ou configurar um banco de dados separadamente. O H2 será inicializado automaticamente quando a aplicação for executada.

## Uso

### Endpoints da API

A aplicação expõe vários endpoints RESTful para gerenciar informações de clientes:

- **GET /clientes**: Recuperar todos os clientes.
- **GET /clientes/{id}**: Recuperar um cliente pelo ID.
- **POST /clientes**: Adicionar um novo cliente.
- **PUT /clientes/{id}**: Atualizar um cliente existente pelo ID.
- **DELETE /clientes/{id}**: Deletar um cliente pelo ID.
- **GET /clientes/cep/{cep}**: Busca endereço pelo cep.

### Documentação da API

A aplicação fornece documentação interativa da API via Swagger. Você pode acessá-la em: [Swagger UI](http://localhost:8080/swagger-ui/index.html)

## Tratamento de Erros

A aplicação lida com vários erros, incluindo:

- Cliente não encontrado
- Endereço não encontrado
- Problemas de conexão com o banco de dados

Os erros são relatados com códigos de status HTTP apropriados e mensagens de erro.

## Contribuindo

Aceitamos contribuições para melhorar o projeto. Por favor, faça um fork do repositório e envie um pull request com suas alterações. Certifique-se de que seu código siga os padrões de codificação do projeto e inclua cobertura de teste adequada.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

## Contato

Para dúvidas ou suporte, entre em contato com:

- **Autor**: [falvojr](https://github.com/falvojr)
- **Projeto Original**: [digitalinnovationone/lab-padroes-projeto-spring](https://github.com/digitalinnovationone/lab-padroes-projeto-spring)
