/*
 * Projeto final do programa "Capgemini Start". - Curso Lógica de Programação
*
Nome: Todo App
*
Descricao: Aplicação para o gerenciamento de projetos e as tarefas envolvidas
nesses projetos.
*
Objetivo: resolver a questão de organização de tarefas de projetos.
*
Entidades: 
* Projeto;
    - Nome
    - Descrição
    - Data de Criação
    - Data de Atualização
* Tarefa;
    - Nome
    - Descrição
    - Status (concluida ou não)
    - Observações
    - Prazo
    - Data de Criação
    - Data de Atualização
*
Funcionalidades/Requisitos:
* Permitir o cadastro de um projeto;
* Permitir o cadastro de uma tarefa;
* Permitir edição de um projeto;
* Permitir edição de uma tarefa;
* Permitir excluir um projeto;
* Permitir excluir uma tarefa;
*
Regras de Negócio:
* Não irá conter um sistema de login;
* Não haverá o conceito de usuário (1 usuário só);
* Toda tarefa deverá pertencer a um projeto obrigatoriamente;
*
Tecnologias envolvidas:
* Java;
* MySQL

*/


package CapgeminiStartFinalProject;

import util.ConnectionFactory;

public class Main {
    public static void main(String [] args) {
      
        Connection c = ConnectionFactory.getConnection();
        
        ConnectionFactory.closeConnection(c);
        
    }
    
}
