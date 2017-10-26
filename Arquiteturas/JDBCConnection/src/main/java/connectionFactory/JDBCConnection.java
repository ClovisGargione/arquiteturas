/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clovis
 */
public class JDBCConnection {
    private final String driver = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String user = "clovis";
    private static final String password = "123456";
	
	public JDBCConnection(){}
	
	public Connection getConnection() throws ClassNotFoundException{
            System.out.println("Conectando ao banco!");
            Class.forName(driver);
            try {
                return DriverManager.getConnection(url, user, password);
            } catch (SQLException ex) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
                return null;
            }
        
        public static void main(String[]args) throws ClassNotFoundException, SQLException{
        try (Connection connection = new JDBCConnection().getConnection()) {
            System.out.println("Conexão aberta!");
            Statement stm = connection.createStatement();
            stm.close();
            System.out.println("Conexão Fechada!");
            }
        }
}
