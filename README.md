<h2 align="center"> Tradutor </h2>
<p align="center">Tradução de Textos consumindo LLM (08/2024) </p>

<hr>

### Arquitetura do Serviço de Tradução:
<p align="center">
    <img src="assets/translator-architecture.png" alt="Software Architecture">
</p>


## Requisição: /translate
<p align="center">
    <img src="assets/postman.png" alt="Request">
</p>



## Detalhes:
    0.0: Configuração: 
        0.1: .env no diretório /ai/src
                0.1.2: API_KEY="chave de api do groq" 

    1.0: Validação de dados de input são realizadas

    2.0: Assincronismo presente no processamento da tradução
        - @Async
        - Classe Genérica: CompletableFuture<T>

    3.0: Dados armazenados em memória (Redis) possuem TTL

## Linkedin e Gmail
<p align="center">

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/reidner-adnan-b19377210) 	[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:rdn.adn00@gmail.com)

</p>


