package upc.edu.pe.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Miguel Cardoso
 */
public class ConexionBD {
        public static Connection conexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/bd_android","root","mysql");
        } catch (Exception ex) {
            conexion = null;
            System.out.println("Excepcion Interna : " + ex.getMessage());
        }
        return conexion;
    }
}
