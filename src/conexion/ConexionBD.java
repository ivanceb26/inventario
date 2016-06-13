/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

/**
 *
 * @author Wilson Salamanca
 */
import java.sql.*;

public class ConexionBD {

    static String baseName = "inventariobd";
    static String mysqlUser = "root";
    static String password = "paque";
    static String url = "jdbc:mysql://localhost/" + baseName;
    Connection conexion = null;

    /**
     * Crea la conexion con la base de datos;
     */
    public ConexionBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url, mysqlUser, password);
            conexion.setAutoCommit(false);
            if (conexion != null) {
                //System.out.println("conectado a: " + baseName);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void commit() throws SQLException {
        conexion.commit();
    }

    public void desconectar() throws SQLException {
        conexion.close();
    }

    public void rollback() throws SQLException {
        conexion.rollback();
    }
}
