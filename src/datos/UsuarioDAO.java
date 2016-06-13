/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import conexion.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Usuario;

/**
 *
 * @author Wilson Salamanca
 */
public class UsuarioDAO {

    ArrayList usuarios;
    Usuario usuario;
    ConexionBD conexion;

    public UsuarioDAO() {
        usuario = new Usuario();
    }

    /**
     * Inserta un usuario en la base de datos
     * @throws Exception 
     */
    public void insertarUsuario() throws Exception {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO USUARIOS(NOM_USUARIO,"
                + " PASSWORD, NOMBRE,"
                + "APELLIDO, ROL, CEDULA, TELEFONO, DIRECCION , FOTO) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, usuario.getUser());
        pstm.setString(2, usuario.getPassword());
        pstm.setString(3, usuario.getNombre());
        pstm.setString(4, usuario.getApellido());
        pstm.setString(5, usuario.getRol());
        pstm.setString(6, usuario.getCedula());
        pstm.setString(7, usuario.getTelefono());
        pstm.setString(8, usuario.getDireccion());
        pstm.setString(9, usuario.getDireccion());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void actualizarUsuario() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "UPDATE USUARIOS SET PASSWORD = ?, NOMBRE = ?, "
                + "APELLIDO = ?, ROL = ?, CEDULA = ?, TELEFONO = ?, DIRECCION = ? , FOTO = ?"
                + "WHERE NOM_USUARIO = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, usuario.getPassword());
        pstm.setString(2, usuario.getNombre());
        pstm.setString(3, usuario.getApellido());
        pstm.setString(4, usuario.getRol());
        pstm.setString(5, usuario.getCedula());
        pstm.setString(6, usuario.getTelefono());
        pstm.setString(7, usuario.getDireccion());
        pstm.setString(8, usuario.getDireccion());
        pstm.setString(9, usuario.getUser());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public Usuario buscarUnicoUsuario(String us) throws Exception {
        usuario = new Usuario();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM USUARIOS WHERE NOM_USUARIO  = '" + us + "'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {

                Usuario usu = new Usuario();
                usu.setCedula(rs.getString("CEDULA"));
                usu.setNombre(rs.getString("NOMBRE"));
                usu.setApellido(rs.getString("APELLIDO"));
                usu.setDireccion(rs.getString("DIRECCION"));
                usu.setTelefono(rs.getString("TELEFONO"));
                usu.setFoto(rs.getString("FOTO"));
                usu.setUser(rs.getString("NOM_USUARIO"));
                usu.setPassword(rs.getString("PASSWORD"));
                usu.setRol(rs.getString("ROL"));
                usuario = usu;
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return usuario;
    }
    
    public void eliminarUsuario() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM USUARIOS WHERE NOM_USUARIO = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, usuario.getUser());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public ArrayList<Usuario> buscarPorCedula(String cedula) throws Exception {
        usuarios = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM USUARIOS WHERE LIKE '" + cedula + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setCedula(rs.getString("CEDULA"));
                usu.setNombre(rs.getString("NOMBRE"));
                usu.setApellido(rs.getString("APELLIDO"));
                usu.setDireccion(rs.getString("DIRECCION"));
                usu.setTelefono(rs.getString("TELEFONO"));
                usu.setFoto(rs.getString("FOTO"));
                usu.setUser(rs.getString("NOM_USUARIO"));
                usu.setPassword(rs.getString("PASSWORD"));
                usu.setRol(rs.getString("ROL"));
                usuarios.add(usu);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return usuarios;
    }

    public ArrayList buscarPorRol(String ro) throws Exception {
        usuarios = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM USUARIOS WHERE ROL LIKE '" + ro + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setCedula(rs.getString("CEDULA"));
                usu.setNombre(rs.getString("NOMBRE"));
                usu.setApellido(rs.getString("APELLIDO"));
                usu.setDireccion(rs.getString("DIRECCION"));
                usu.setTelefono(rs.getString("TELEFONO"));
                usu.setFoto(rs.getString("FOTO"));
                usu.setUser(rs.getString("NOM_USUARIO"));
                usu.setPassword(rs.getString("PASSWORD"));
                usu.setRol(rs.getString("ROL"));
                usuarios.add(usu);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return usuarios;
    }

    public ArrayList<Usuario> buscarPorUsuario(String us) throws Exception {
        usuarios = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM USUARIOS WHERE NOM_USUARIO LIKE '%" + us + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {

                Usuario usu = new Usuario();
                usu.setCedula(rs.getString("CEDULA"));
                usu.setNombre(rs.getString("NOMBRE"));
                usu.setApellido(rs.getString("APELLIDO"));
                usu.setDireccion(rs.getString("DIRECCION"));
                usu.setTelefono(rs.getString("TELEFONO"));
                usu.setFoto(rs.getString("FOTO"));
                usu.setUser(rs.getString("NOM_USUARIO"));
                usu.setPassword(rs.getString("PASSWORD"));
                usu.setRol(rs.getString("ROL"));
                usuarios.add(usu);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return usuarios;
    }

    public ArrayList<Usuario> getUsuarios() throws Exception {
        usuarios = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM USUARIOS";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Usuario usu = new Usuario();
                usu.setCedula(rs.getString("CEDULA"));
                usu.setNombre(rs.getString("NOMBRE"));
                usu.setApellido(rs.getString("APELLIDO"));
                usu.setDireccion(rs.getString("DIRECCION"));
                usu.setTelefono(rs.getString("TELEFONO"));
                usu.setFoto(rs.getString("FOTO"));
                usu.setUser(rs.getString("NOM_USUARIO"));
                usu.setPassword(rs.getString("PASSWORD"));
                usu.setRol(rs.getString("ROL"));
                usuarios.add(usu);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return usuarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
