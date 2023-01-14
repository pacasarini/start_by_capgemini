
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import util.ConnectionFactory;
import model.Task;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;


/**
 *
 * @author Paula
 */
public class TaskController {
    
    // criação de metodos para save, insert, delete, etc:
    
    public void save(Task task){                    //quando o campo for "auto-increment" nao precisa informa-lo no "insert"
        String sql = "INSERT INTO tasks (idProject,"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;   //essa conexao nao eh no try por conta do escopo poder ser acessado no finally
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));      //eh necessario converter pq o date do sql eh diferente do java date (java.util)
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa. " 
                    + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
     
    public void update(Task task){
        String sql = "UPDATE tasks SET "
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "status = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadline = ?, "
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
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            
            //executando a query
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa. " 
                    + ex.getMessage(), ex);
        } 
    }
    
    public void removeById(int taskId) throws SQLException{
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            //Estabelecendo a conexao com o banco de dados
            connection = ConnectionFactory.getConnection();
            
            //preparando a query
            statement = connection.prepareStatement(sql);     //o statment ajuda a preparar o comando pra conexão
            
            //setando os valores
            statement.setInt(1, taskId); //requerimento pra mudar o primeiro caractere ("?") substituindo pelo taskId recebido 
            
            //executando a query
            statement.execute();
            
        } catch (Exception ex) { 
            throw new RuntimeException("Erro ao deletar a tarefa.");
        } finally {     //o bloco finally sempre será executado, independente de ter acontecido o erro ou não
            ConnectionFactory.closeConnection(connection, statement);  //conexoes abertas sempre devem ser fechadas
        }
    }
    
    public List<Tasks> getAll(int idProject){
        String sql = "SELECT * FROM tasks idProject = ?";
        
        Connection connection = null; //eh preciso estalecer uma conexao sempre q precisar se conectar a um banco de dados
        PreparedStatement statement = null;
        ResultSet resultSet = null; //classe que representa o retorno do banco de dados, onde estará a resposta do BD
        
        // Lista de tarefas que sera devolvida qdo a chamada do metodo acontecer
        // estrutura q funciona como se fosse um vetor para facilitar trabalhar com coleçoes (conjunto de valores)
        List<Task> tasks = new ArrayList<Task>();
        
        try {
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(sql);
           
        // setando um valor que corresponde ao filtro de busca
           statement.setInt(1, idProject);
           
           resultSet = statement.executeQuery();    //vai me devolver o valor de resposta daquele Select que aconteceu no BD
           
           //Enquanto houverem valores a serem percorridos no meu resultset
           while(resultSet.next()) {
               Task task = new Task();
               task.setId(resultSet.getInt("id"));
               task.setIdProject(resultSet.getInt("idProject"));
               task.setName(resultSet.getString("name"));
               task.setDescription(resultSet.getString("description"));
               task.setNotes(resultSet.getString("notes"));
               task.setIsCompleted(resultSet.getBoolean("completed"));
               task.setDeadline(resultSet.getDate("deadline"));
               task.setCreatedAt(resultSet.getDate("createdAt"));
               task.setUpdatedAt(resultSet.getDate("updatedAt"));
               
               tasks.add(task); //assim coloco na lista de tarefas
            }   
        } catch (Exception ex) { 
            throw new RuntimeException("Erro ao inserir a tarefa.");
        } finally {     //o bloco finally sempre será executado, independente de ter acontecido o erro ou não
            ConnectionFactory.closeConnection(connection, statement, resultSet);  //conexoes abertas sempre devem ser fechadas
        }
        
        
        //Lista de tarefas que foi criada e carregada do banco de dados:
        return tasks;
    }
}
