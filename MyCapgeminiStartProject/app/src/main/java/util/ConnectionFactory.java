/*
 * esta classe será necessária para se conectar com o BD
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Paula
 */

public class ConnectionFactory {
    
    public static final String DRIVER = "com.mysql.jbdc.Driver";        //DRIVER é a ponte entre o app e o BD
    public static final String URL = "jbdc:mysql://localhost:3306/todoapp";     //caminho de conexão até o BD - colocar a dependencia no build.gradle
    public static final String USER = "root";   
    public static final String PASS = "";
    
        public static Connection getConnection() {  //o static diz que vc pode chamar o metodo sem chamar a instancia
            try {       //try faz tratamento de exceção (tratamento de erro)
                Class.forName(DRIVER);
                return DriverManager.getConnection(URL, USER, PASS);
            } catch (Exception ex) {
                throw new RuntimeException("Erro na conexão com o bando de dados.", ex);     //se ocorrer o erro, esse eh o tratamento
            }
        }
        
        //para encerrar a conexão:
        public static void closeConnection(Connection connection) {
            try {
                if (connection != null) {   //"se ela existir, feche a conexao"
                    connection.close();
                }
            }
        }
        
        


}
