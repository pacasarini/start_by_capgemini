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


/**
 *
 * @author Paula
 */
public class TaskController {
    
    // criação de metodos para save, insert, delete, etc:
    public void save(Task task){
        
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
