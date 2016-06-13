/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.PreparedStatement;
import conexion.ConexionBD;
import gui.internal.ProductosUI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Producto;

/**
 *
 * @author Wilson Salamanca
 */
public class ProductoDAO {

    private ArrayList<Producto> productos;
    private Producto producto;
    private ConexionBD conexion;

    public ProductoDAO() {
        producto = new Producto();
    }

    public void insertarProducto() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO PRODUCTOS(COD_INDIVIDUAL, COD_BOLSA, NOMBRE,"
                + "COLOR, TEXTURA, CARACTERISTICAS, ESTADO, TALLA, SEXO,"
                + "PAIS_REPRES, PRECIO, IMAGEN_PROD, DISPONIBILIDAD)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, producto.getCod_individual());
        pstm.setString(2, producto.getCod_bolsa());
        pstm.setString(3, producto.getNombre());
        pstm.setString(4, producto.getColor());
        pstm.setString(5, producto.getTextura());
        pstm.setString(6, producto.getCaract());
        pstm.setString(7, producto.getEstado());
        pstm.setString(8, producto.getTalla());
        pstm.setString(9, producto.getSexo());
        pstm.setString(10, producto.getPais());
        pstm.setInt(11, producto.getPrecio());
        pstm.setString(12, producto.getImagen());
        pstm.setString(13, producto.getDisponibilidad());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void actualizarProducto() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "UPDATE PRODUCTOS SET COD_BOLSA = ?, NOMBRE = ?,"
                + "COLOR = ?, TEXTURA = ?, CARACTERISTICAS = ?, ESTADO = ?, TALLA = ?, SEXO = ?,"
                + "PAIS_REPRES = ?, PRECIO = ?, IMAGEN_PROD = ?, DISPONIBILIDAD = ? "
                + "WHERE COD_INDIVIDUAL = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, producto.getCod_bolsa());
        pstm.setString(2, producto.getNombre());
        pstm.setString(3, producto.getColor());
        pstm.setString(4, producto.getTextura());
        pstm.setString(5, producto.getCaract());
        pstm.setString(6, producto.getEstado());
        pstm.setString(7, producto.getTalla());
        pstm.setString(8, producto.getSexo());
        pstm.setString(9, producto.getPais());
        pstm.setInt(10, producto.getPrecio());
        pstm.setString(11, producto.getImagen());
        pstm.setString(12, producto.getDisponibilidad());
        pstm.setString(13, producto.getCod_individual());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void eliminarProducto() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM CLIENTES WHERE COD_INDIVIDUAL = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, producto.getCod_individual());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public Producto getProducto() {
        return this.producto;
    }

    public ArrayList<Producto> buscarProductoPorCodIndividual(String codind) throws Exception {

        productos = new ArrayList();


        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PRODUCTOS WHERE COD_INDIVIDUAL LIKE '" + codind + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {

                Producto prod = new Producto();

                prod.setCod_individual(rs.getString("COD_INDIVIDUAL"));
                prod.setCod_bolsa(rs.getString("COD_BOLSA"));
                prod.setNombre(rs.getString("NOMBRE"));
                prod.setColor(rs.getString("COLOR"));
                prod.setTextura(rs.getString("TEXTURA"));
                prod.setCaract(rs.getString("CARACTERISTICAS"));
                prod.setEstado(rs.getString("ESTADO"));
                prod.setTalla(rs.getString("TALLA"));
                prod.setSexo(rs.getString("SEXO"));
                prod.setPais(rs.getString("PAIS_REPRES"));
                prod.setPrecio(rs.getInt("PRECIO"));
                prod.setImagen(rs.getString("IMAGEN_PROD"));
                prod.setDisponibilidad(rs.getString("DISPONIBILIDAD"));
                productos.add(prod);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return productos;
    }

    public ArrayList<Producto> buscarProductoPorCodBolsa(String codbolsa) throws Exception {

        productos = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PRODUCTOS WHERE COD_BOLSA LIKE '" + codbolsa + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Producto prod = new Producto();

                prod.setCod_individual(rs.getString("COD_INDIVIDUAL"));
                prod.setCod_bolsa(rs.getString("COD_BOLSA"));
                prod.setNombre(rs.getString("NOMBRE"));
                prod.setColor(rs.getString("COLOR"));
                prod.setTextura(rs.getString("TEXTURA"));
                prod.setCaract(rs.getString("CARACTERISTICAS"));
                prod.setEstado(rs.getString("ESTADO"));
                prod.setTalla(rs.getString("TALLA"));
                prod.setSexo(rs.getString("SEXO"));
                prod.setPais(rs.getString("PAIS_REPRES"));
                prod.setPrecio(rs.getInt("PRECIO"));
                prod.setImagen(rs.getString("IMAGEN_PROD"));
                prod.setDisponibilidad(rs.getString("DISPONIBILIDAD"));

                productos.add(prod);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return productos;
    }

    public ArrayList<Producto> buscarProductoPorNombre(String nombre) throws Exception {


        productos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PRODUCTOS WHERE NOMBRE LIKE '%" + nombre + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //  pstm.executeQuery();
            //  conexion.commit();
            while (rs.next()) {
                Producto prod = new Producto();

                prod.setCod_individual(rs.getString("COD_INDIVIDUAL"));
                prod.setCod_bolsa(rs.getString("COD_BOLSA"));
                prod.setNombre(rs.getString("NOMBRE"));
                prod.setColor(rs.getString("COLOR"));
                prod.setTextura(rs.getString("TEXTURA"));
                prod.setCaract(rs.getString("CARACTERISTICAS"));
                prod.setEstado(rs.getString("ESTADO"));
                prod.setTalla(rs.getString("TALLA"));
                prod.setSexo(rs.getString("SEXO"));
                prod.setPais(rs.getString("PAIS_REPRES"));
                prod.setPrecio(rs.getInt("PRECIO"));
                prod.setImagen(rs.getString("IMAGEN_PROD"));
                prod.setDisponibilidad(rs.getString("DISPONIBILIDAD"));

                productos.add(prod);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return productos;
    }

    public ArrayList<Producto> buscarProductoPorPais(String pais) throws Exception {


        productos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PRODUCTOS WHERE PAIS_REPRES LIKE '%" + pais + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Producto prod = new Producto();

                prod.setCod_individual(rs.getString("COD_INDIVIDUAL"));
                prod.setCod_bolsa(rs.getString("COD_BOLSA"));
                prod.setNombre(rs.getString("NOMBRE"));
                prod.setColor(rs.getString("COLOR"));
                prod.setTextura(rs.getString("TEXTURA"));
                prod.setCaract(rs.getString("CARACTERISTICAS"));
                prod.setEstado(rs.getString("ESTADO"));
                prod.setTalla(rs.getString("TALLA"));
                prod.setSexo(rs.getString("SEXO"));
                prod.setPais(rs.getString("PAIS_REPRES"));
                prod.setPrecio(rs.getInt("PRECIO"));
                prod.setImagen(rs.getString("IMAGEN_PROD"));
                prod.setDisponibilidad(rs.getString("DISPONIBILIDAD"));

                productos.add(prod);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return productos;
    }

    public ArrayList<Producto> buscarProductoPorDisponibilidad(String disponible) throws Exception {


        productos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PRODUCTOS WHERE DISPONIBILIDAD LIKE '" + disponible + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {

                Producto prod = new Producto();

                prod.setCod_individual(rs.getString("COD_INDIVIDUAL"));
                prod.setCod_bolsa(rs.getString("COD_BOLSA"));
                prod.setNombre(rs.getString("NOMBRE"));
                prod.setColor(rs.getString("COLOR"));
                prod.setTextura(rs.getString("TEXTURA"));
                prod.setCaract(rs.getString("CARACTERISTICAS"));
                prod.setEstado(rs.getString("ESTADO"));
                prod.setTalla(rs.getString("TALLA"));
                prod.setSexo(rs.getString("SEXO"));
                prod.setPais(rs.getString("PAIS_REPRES"));
                prod.setPrecio(rs.getInt("PRECIO"));
                prod.setImagen(rs.getString("IMAGEN_PROD"));
                prod.setDisponibilidad(rs.getString("DISPONIBILIDAD"));

                productos.add(prod);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return productos;
    }

    public ArrayList<Producto> getProductos() throws Exception {


        productos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PRODUCTOS";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                Producto prod = new Producto();

                prod.setCod_individual(rs.getString("COD_INDIVIDUAL"));
                prod.setCod_bolsa(rs.getString("COD_BOLSA"));
                prod.setNombre(rs.getString("NOMBRE"));
                prod.setColor(rs.getString("COLOR"));
                prod.setTextura(rs.getString("TEXTURA"));
                prod.setCaract(rs.getString("CARACTERISTICAS"));
                prod.setEstado(rs.getString("ESTADO"));
                prod.setTalla(rs.getString("TALLA"));
                prod.setSexo(rs.getString("SEXO"));
                prod.setPais(rs.getString("PAIS_REPRES"));
                prod.setPrecio(rs.getInt("PRECIO"));
                prod.setImagen(rs.getString("IMAGEN_PROD"));
                prod.setDisponibilidad(rs.getString("DISPONIBILIDAD"));

                productos.add(prod);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return productos;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
