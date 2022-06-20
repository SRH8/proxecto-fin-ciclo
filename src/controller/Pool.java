package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Pool de conexiones
 * 
 * @author Sergio Fraga
 */
public class Pool {

    static Connection Con;
    static BasicDataSource basicdatasource= new BasicDataSource();

    /**
     * Lanza el pool de conexiones
     * 
     * @throws SQLException
     */
    public static Connection launchPool() throws SQLException {
        try {
        	ResourceBundle rb = ResourceBundle.getBundle("preferences.db");
        	
            basicdatasource.setDriverClassName("org.mariadb.jdbc.Driver");
            basicdatasource.setUsername(rb.getString("user"));
            basicdatasource.setPassword(rb.getString("password"));
            basicdatasource.setUrl("jdbc:mariadb://" + rb.getString("ip") + ":"+ rb.getString("port") + rb.getString("db"));
            basicdatasource.setValidationQuery("Select 1");
            basicdatasource.setMaxTotal(1);
            basicdatasource.setMinIdle(50);
            basicdatasource.setMaxIdle(100); 
            basicdatasource.setMaxWaitMillis(1000); 
        
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error en la conexión a la base de datos (Pool)");
        }
        return null;
     }

   /**
    * Permite obtener conexiones sobre las que realizar operaciones
    * 
    * @return conexión
    * @throws SQLException
    */
    public static Connection getConection() throws SQLException {
        Con = basicdatasource.getConnection();
        Con.setAutoCommit(false);
        
        return Con;    
    }
    
    /**
     * Cierra la conexión
     */
    public static void closeConnection(){
        try {
            Con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Pool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

}
