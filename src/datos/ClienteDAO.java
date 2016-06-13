/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.PreparedStatement;
import conexion.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Cliente;

/**
 *
 * @author Wilson Salamanca
 */
public class ClienteDAO {

    private ArrayList<Cliente> clientes;
    private Cliente cliente;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    private ConexionBD conexion;

    public ClienteDAO() {
        cliente = new Cliente();
    }

    public void insertarCliente() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO CLIENTES(CEDULA, NOMBRE,"
                + "APELLIDO, DIRECCION, TELEFONO, FOTO, PUNTOS)"
                + "VALUES(?,?,?,?,?,?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, cliente.getCedula());
        pstm.setString(2, cliente.getNombre());
        pstm.setString(3, cliente.getApellido());
        pstm.setString(4, cliente.getDireccion());
        pstm.setString(5, cliente.getTelefono());
        pstm.setString(6, cliente.getFoto());
        pstm.setInt(7, 0);
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void actualizarCliente() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "UPDATE CLIENTES SET NOMBRE = ?,"
                + "APELLIDO = ?, DIRECCION = ?, TELEFONO = ?,"
                + "FOTO = ?, PUNTOS = ? WHERE CEDULA = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, cliente.getNombre());
        pstm.setString(2, cliente.getApellido());
        pstm.setString(3, cliente.getDireccion());
        pstm.setString(4, cliente.getTelefono());
        pstm.setString(5, cliente.getFoto());
        pstm.setInt(6, cliente.getPuntos());
        pstm.setString(7, cliente.getCedula());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void eliminarCliente() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM CLIENTES WHERE CEDULA = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, cliente.getCedula());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    //PARA ALMACENAR EN ARRAYLIST
    public ArrayList<Cliente> buscarClientePorCedula(String cedula) throws Exception {

        clientes = new ArrayList<Cliente>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM CLIENTES WHERE CEDULA LIKE '" + cedula + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //  pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Cliente client = new Cliente();
                client.setCedula(rs.getString("cedula"));
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setDireccion(rs.getString("direccion"));
                client.setTelefono(rs.getString("telefono"));
                client.setFoto(rs.getString("foto"));
                client.setPuntos(rs.getInt("puntos"));
                clientes.add(client);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return clientes;
    }
    
    public ArrayList<Cliente> buscarClientePorNombre(String nombre) throws Exception {

        clientes = new ArrayList<Cliente>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM CLIENTES WHERE NOMBRE LIKE '%" + nombre + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //  pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Cliente client = new Cliente();
                client.setCedula(rs.getString("cedula"));
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setDireccion(rs.getString("direccion"));
                client.setTelefono(rs.getString("telefono"));
                client.setFoto(rs.getString("foto"));
                client.setPuntos(rs.getInt("puntos"));
                clientes.add(client);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return clientes;
    }
    
    public ArrayList<Cliente> buscarClientePorApellido(String apellido) throws Exception {

        clientes = new ArrayList<Cliente>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM CLIENTES WHERE APELLIDO LIKE '%" + apellido + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //  pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Cliente client = new Cliente();
                client.setCedula(rs.getString("cedula"));
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setDireccion(rs.getString("direccion"));
                client.setTelefono(rs.getString("telefono"));
                client.setFoto(rs.getString("foto"));
                client.setPuntos(rs.getInt("puntos"));
                clientes.add(client);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return clientes;
    }
    
    
    public Cliente buscarUnicoClientePorCedula(String cedula) throws Exception {

        clientes = new ArrayList<Cliente>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM CLIENTES WHERE CEDULA='" + cedula + "'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //  pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Cliente client = new Cliente();
                client.setCedula(rs.getString("cedula"));
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setDireccion(rs.getString("direccion"));
                client.setTelefono(rs.getString("telefono"));
                client.setFoto(rs.getString("foto"));
                client.setPuntos(rs.getInt("puntos"));
                this.cliente = client;
            }
            
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return cliente;
    }
    

    public ArrayList<Cliente> getClientes() throws Exception {

        clientes = new ArrayList<Cliente>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM CLIENTES";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //  pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Cliente client = new Cliente();
                client.setCedula(rs.getString("cedula"));
                client.setNombre(rs.getString("nombre"));
                client.setApellido(rs.getString("apellido"));
                client.setDireccion(rs.getString("direccion"));
                client.setTelefono(rs.getString("telefono"));
                client.setFoto(rs.getString("foto"));
                client.setPuntos(rs.getInt("puntos"));
                clientes.add(client);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return clientes;
    }
}
