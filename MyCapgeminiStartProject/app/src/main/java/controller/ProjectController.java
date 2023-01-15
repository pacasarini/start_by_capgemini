
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import util.ConnectionFactory;
import model.Project;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Paula
 */
public class ProjectController {
    
    public void save(Project project){                    //quando o campo for "auto-increment" nao precisa informa-lo no comando "insert"
        String sql = "INSERT INTO tasks (name,"  //nao precisa fazer "id" pq eh auto-incremetada
                + "description,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?)";
        
        Connection connection = null;   //essa conexao nao eh no try por conta do escopo poder ser acessado no finally
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar o projeto. " 
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void update(Project project){
        String sql = "UPDATE tasks SET "
                + "name = ?, "
                + "description = ?, "
                + "createdAt = ?, "
                + "updatedAt = ?, "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // estabelecendo a conexao com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            // preparando a query
            statement = connection.prepareStatement(sql);
            
            // setando os valores no statement 
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
            
            //executando a query
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar o projeto. " 
                    + ex.getMessage(), ex);
        } 
    }
    
     public List<Project> getAll(){     //nao precisa ter filtro pq queremos a lista toda do projeto do BD
        String sql = "SELECT * FROM projects";
        
        List<Project> projects = new ArrayList<>();
        
        Connection connection = null; //eh preciso estalecer uma conexao sempre q precisar se conectar a um banco de dados
        PreparedStatement statement = null;
        ResultSet resultSet = null; //classe que representa o retorno do banco de dados, onde estará a resposta do BD
        
        // Lista de tarefas que sera devolvida qdo a chamada do metodo acontecer
        // estrutura q funciona como se fosse um vetor para facilitar trabalhar com coleçoes (conjunto de valores)

        
        try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           
        // setando um valor que corresponde ao filtro de busca
           
           resultSet = statement.executeQuery();    //vai me devolver o valor de resposta daquele Select que aconteceu no BD
           
           //Enquanto houverem valores a serem percorridos no meu resultset
           while(resultSet.next()) {
               Project project = new Project();
               
               project.setId(resultSet.getInt("id"));
               project.setName(resultSet.getString("name"));
               project.setDescription(resultSet.getString("description"));
               project.setCreatedAt(resultSet.getDate("createdAt"));
               project.setUpdatedAt(resultSet.getDate("updatedAt"));
               
               projects.add(project); //assim coloco na lista de tarefas
            }   
        } catch (SQLException ex) { 
            throw new RuntimeException("Erro ao buscar os projetos.");
        } finally {     //o bloco finally sempre será executado, independente de ter acontecido o erro ou não
            ConnectionFactory.closeConnection(connection, statement, resultSet);  //conexoes abertas sempre devem ser fechadas
        }
        
        return projects;
     }    
     
     public void removeById(int idProject) {
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conexao com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = connection.prepareStatement(sql);     //o statment ajuda a preparar o comando pra conexão
            
            //setando os valores
            statement.setInt(1, idProject); //requerimento pra mudar o primeiro caractere ("?") substituindo pelo taskId recebido 
            
            //executando a query
            statement.execute();
            
        } catch (SQLException ex) { 
            throw new RuntimeException("Erro ao deletar o projeto.");
        } finally {     //o bloco finally sempre será executado, independente de ter acontecido o erro ou não
            ConnectionFactory.closeConnection(connection, statement);  //conexoes abertas sempre devem ser fechadas
        }
    }
}
