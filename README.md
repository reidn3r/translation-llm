<h2 align="center"> Translator </h2>
<p align="center">Text Translation Using LLM (08/2024)</p>

<hr>

### Translation Service Architecture:
<p align="center">
    <img src="assets/translator-architecture.png" alt="Software Architecture">
</p>

## Request: /translate
<p align="center">
    <img src="assets/postman.png" alt="Request">
</p>

## Details:
    0.0: Configuration: 
        0.1: .env in the /ai/src directory
                0.1.2: API_KEY="groq API key" 

    1.0: Input data validation is performed

    2.0: Asynchronous translation processing
        - @Async
        - Generic Class: CompletableFuture<T>

    3.0: Data stored in memory (Redis) has TTL

## LinkedIn and Gmail
<p align="center">

[![LinkedIn](https://img.shields.io/badge/linkedin-%230077B5.svg?style=for-the-badge&logo=linkedin&logoColor=white)](https://linkedin.com/in/reidner-adnan-b19377210) 	[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:rdn.adn00@gmail.com)

</p>
