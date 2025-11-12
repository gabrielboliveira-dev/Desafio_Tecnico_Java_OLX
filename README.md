# Desafio Técnico: API de Classificados (OLX)

## Contexto

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot, simulando o backend de um sistema de classificados (como a OLX).

O foco principal do desafio é implementar a **autenticação de usuários** e a lógica de **autorização (propriedade)**. A API deve gerenciar usuários e anúncios, garantindo que um usuário só possa modificar ou deletar os anúncios que ele mesmo criou.

## 🚀 Requisitos Funcionais

A API deve permitir as seguintes operações:

1.  **Gerenciamento de Usuário:**
    * `POST /auth/register`: Registrar um novo usuário (com e-mail e senha).
    * `POST /auth/login`: Autenticar um usuário e retornar um token (ex: JWT).
2.  **Gerenciamento de Anúncios (Endpoints Protegidos):**
    * `POST /ads`: Criar um novo anúncio (associado ao usuário autenticado).
    * `PUT /ads/{id}`: Editar um anúncio. (Permitido **apenas** se o usuário for o dono).
    * `DELETE /ads/{id}`: Deletar um anúncio. (Permitido **apenas** se o usuário for o dono).
    * `GET /ads/my`: Listar todos os anúncios *do usuário atualmente logado*.
3.  **Visualização Pública:**
    * `GET /ads`: Listar todos os anúncios (de todos os usuários).
    * `GET /ads/{id}`: Ver o detalhe de um anúncio.

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3** (Spring Web, Spring Data JPA)
* **Spring Security 6** (Para autenticação e proteção de endpoints)
* **JWT (JSON Web Tokens)**: Para gerenciamento de sessão stateless (dependências `jjwt`).
* **H2 Database** (Banco de dados em memória)
* **Lombok**
* **Spring Validation**

## 🎯 Objetivos de Aprendizado (Clean Code & SOLID)

* **Autenticação vs. Autorização:** Entender e implementar a diferença.
    * **Autenticação:** Provar *quem você é* (Login com Spring Security).
    * **Autorização:** Decidir *o que você pode fazer* (Verificar se o usuário é o dono do anúncio).
* **Spring Security:** Configurar o `SecurityFilterChain` para proteger endpoints (`/ads/**`) e deixar outros públicos (`/auth/**`, `GET /ads`).
* **Modelagem de Relacionamento (Propriedade):** Implementar o relacionamento `User` (One) -> `Ad` (Many) no JPA.
* **Lógica de Serviço com "Propriedade":** Implementar métodos de serviço (ex: `updateAd`) que verificam a identidade do usuário (`Principal`) antes de executar a ação.
* **Padrão DTO:** Continuar usando DTOs para dissociar a API das entidades de domínio.
