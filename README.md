# MyFinanceAPI

## Descrição do Projeto 💡

A MyFinanceAPI é uma API RESTful desenvolvida em Java para gerenciamento de finanças pessoais. Ela permite que usuários realizem o controle de suas transações financeiras de forma prática e organizada, oferecendo recursos como:

- Cadastro de receitas e despesas;
- Filtros por tipo (receita ou despesa) e por categoria;
- Resumo financeiro completo com saldo atual, totais e agrupamentos por categoria;
- Suporte à paginação de resultados.

A API foi construída com foco em boas práticas de arquitetura Java EE, utilizando o padrão Front Controller + Factory + Command, persistência via JDBC com JNDI, e serialização JSON com Gson.

### Autor 👤

O projeto do Diário Inteligente foi criado pelo aluno do curso de Análise e Desenvolvimento de Sistemas no IFSP - Campus Araraquara abaixo:
- **Gabriel de Pauli Santos**

### Tecnologias utilizadas 👩‍💻

- **Java EE (Jakarta):** Utilizado para a criação dos Servlets e estrutura da API RESTful;
- **Gson:** Serialização e desserialização de objetos Java para JSON;
- **JDBC com JNDI:** Acesso ao banco de dados;
- **Padrão Front Controller + Factory + Command:** Organização e separação de responsabilidades;
- **SQL:** Utilizado para armazenar os dados no Banco de Dados;
- **Tomcat:** Srvidor de aplicação para execução da API;
- **Bootstrap:** Utilizado para estilizar as página JSP (ClientAPI);
- **CSS:** Utilizado para fazer a estilização das páginas utilizadas (ClientAPI);
- **Postman:** Ferramenta de testes das requisições HTTP;
- **Aplicativos:** Eclipse, MySQL Workbench, Postman.

## Sobre o projeto ℹ️

A MyFinanceAPI permite que o cliente (frontend ou sistema externo) envie requisições HTTP para:

- Cadastrar, listar, filtrar, atualizar e deletar transações;
- Obter um resumo financeiro (total de receitas, despesas, saldo e agrupamento por categorias);
- Paginar os resultados de listagem para melhor desempenho e organização.
  
A documentação completa com os endpoints, métodos, parâmetros e exemplos pode ser encontrada no documento Documentacao_MyFinanceAPI.pdf.

## Vídeo Demonstrativo (Youtube) 🎥

Clique na imagem para assistir!

[![Assista ao vídeo](https://img.youtube.com/vi/G6K-r6ayrNA/maxresdefault.jpg)](https://youtu.be/G6K-r6ayrNA)
