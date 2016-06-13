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
import negocio.RefCliente;
import negocio.RefClienteCliente;

/**
 *
 * @author Wilson Salamanca
 */
public class RefClienteClienteDAO {

    private ConexionBD conexion;
    private RefClienteCliente refClienteCliente;
    private ArrayList<RefClienteCliente> refs;

    public RefClienteClienteDAO() {
    }

    public void insertarRefClienteCliente() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO REF_CLIENTES_CLIENTE(ID_CLIENTE, ID_REF)"
                + "VALUES(?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, refClienteCliente.getIdCliente());
        pstm.setString(2, refClienteCliente.getIdRef());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void eliminarRefCliente() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM REF_CLIENTES_CLIENTE WHERE ID_CLIENTE = ? "
                + "AND ID_REF = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, refClienteCliente.getIdCliente());
        pstm.setString(2, refClienteCliente.getIdRef());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public RefClienteCliente getRefClienteCliente() {
        return this.refClienteCliente;
    }
    
    public void setRefClienteCliente(RefClienteCliente refClienteCliente) {
        this.refClienteCliente = refClienteCliente;
    }

    //PARA ALMACENAR EN ARRAYLIST
    public ArrayList<RefClienteCliente> buscarReferenciaPorCedCliente(String cedula) throws Exception {
        refs = new ArrayList<RefClienteCliente>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM REF_CLIENTES_CLIENTE WHERE ID_CLIENTE='" + cedula + "'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
           
            while (rs.next()) {
                RefClienteCliente client = new RefClienteCliente();
                client.setIdCliente(rs.getString("ID_CLIENTE"));
                client.setIdRef(rs.getString("ID_REF"));
                refs.add(client);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return refs;
    }
    
    public ArrayList<RefClienteCliente> buscarClientesPorCedRefCliente(String cedula) throws Exception {
        
        refs = new ArrayList<RefClienteCliente>();
        
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM REF_CLIENTES_CLIENTE WHERE ID_REF='" + cedula + "'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            
           
            while (rs.next()) {
                RefClienteCliente client = new RefClienteCliente();
                client.setIdCliente(rs.getString("ID_CLIENTE"));
                client.setIdRef(rs.getString("ID_REF"));
                refs.add(client);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return refs;
    }
    
    public ArrayList<RefCliente> buscarReferencias(String idCliente) throws SQLException, Exception {
        ArrayList<RefCliente> refs = new ArrayList<RefCliente>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT A.* FROM REF_CLIENTES A, REF_CLIENTES_CLIENTE B "
                    + "WHERE B.ID_CLIENTE = ? AND A.CEDULA = B.ID_REF;";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
            pstm.setString(1, idCliente);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {                
                RefCliente ref = new RefCliente();
                ref.setCedula(rs.getString("cedula"));
                ref.setNombre(rs.getString("nombre"));
                ref.setApellido(rs.getString("apellido"));
                ref.setDireccion(rs.getString("direccion"));
                ref.setTelefono(rs.getString("telefono"));
                ref.setFoto(rs.getString("foto"));
                refs.add(ref);
            }

        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return refs;
    }

    
}
