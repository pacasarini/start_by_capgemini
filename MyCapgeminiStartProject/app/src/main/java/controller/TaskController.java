/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import util.ConnectionFactory;
import model.Task;
import java.sql.Date;


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
            throw new RuntimeException("Erro ao salvar a tarefa. " + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
        
    }
     
    public void update(Task task){
        
    }
    
    public void removeById(int taskId) throws SQLException{
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);     //o statment ajuda a preparar o comando pra conexão
            statement.setInt(1, taskId); //requerimento pra mudar o primeiro caractere ("?") substituindo pelo taskId recebido 
            statement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa.");
        } finally {     //o bloco finally sempre será executado, independente de ter acontecido o erro ou não
            ConnectionFactory.closeConnection(conn);  //conexoes abertas sempre devem ser fechadas
        }
    }
    
    public List<Tasks> getAll(int idProject){
        return null;
    }
}
