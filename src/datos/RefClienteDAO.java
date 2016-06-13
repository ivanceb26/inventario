/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.PreparedStatement;
import com.sun.org.apache.bcel.internal.generic.NEW;
import conexion.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import negocio.RefCliente;

/**
 *
 * @author Wilson Salamanca
 */
public class RefClienteDAO {

    private ArrayList<RefCliente> refclientes;
    private RefCliente refcliente;
    private ConexionBD conexion;

    public RefClienteDAO() {
        refcliente = new RefCliente();
    }

    public void insertarRefCliente() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO REF_CLIENTES(CEDULA, NOMBRE, "
                + "APELLIDO, DIRECCION, TELEFONO, FOTO)"
                + "VALUES(?,?,?,?,?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, refcliente.getCedula());
        pstm.setString(2, refcliente.getNombre());
        pstm.setString(3, refcliente.getApellido());
        pstm.setString(4, refcliente.getDireccion());
        pstm.setString(5, refcliente.getTelefono());
        pstm.setString(6, refcliente.getFoto());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void actualizarRefCliente() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "UPDATE REF_CLIENTES SET P_NOMBRE = ?, S_NOMBRE = ?"
                + "P_APELLIDO = ?, S_APELLIDO = ?, DIRECCION = ?, TELEFONO = ?"
                + "FOTO = ?, PUNTOS = ? WHERE CEDULA = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(2, refcliente.getNombre());
        pstm.setString(4, refcliente.getApellido());
        pstm.setString(5, refcliente.getDireccion());
        pstm.setString(6, refcliente.getTelefono());
        pstm.setString(7, refcliente.getFoto());
        pstm.setString(8, refcliente.getCedula());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void eliminarRefCliente() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM REF_CLIENTES WHERE CEDULA = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, refcliente.getCedula());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public RefCliente getRefCliente() {
        return this.refcliente;
    }

    //PARA ALMACENAR EN ARRAYLIST
    public ArrayList<RefCliente> buscarRefClientePorCedula(String cedula) throws Exception {

        refclientes = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM REF_CLIENTES WHERE CEDULA LIKE '" + cedula + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                RefCliente client = new RefCliente();
                client.setCedula(rs.getString("cedula"));
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setDireccion(rs.getString("direccion"));
                client.setTelefono(rs.getString("telefono"));
                client.setFoto(rs.getString("foto"));
                refclientes.add(client);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return refclientes;
    }

    public void setRefcliente(RefCliente refcliente) {
        this.refcliente = refcliente;
    }

    public ArrayList<RefCliente> getRefClientes() throws Exception {

        refclientes = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM REF_CLIENTES";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                RefCliente client = new RefCliente();
                client.setCedula(rs.getString("cedula"));
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setDireccion(rs.getString("direccion"));
                client.setTelefono(rs.getString("telefono"));
                client.setFoto(rs.getString("foto"));
                refclientes.add(client);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return refclientes;
    }
}
