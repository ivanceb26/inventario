/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.PreparedStatement;
import conexion.ConexionBD;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Pedido;
import negocio.PedidoProducto;
import negocio.Producto;

/**
 *
 * @author Wilson Salamanca
 */
public class PedidoProductoDAO {

    private PedidoProducto pedProducto;
    private ConexionBD conexion;
    private ArrayList<PedidoProducto> pediprods;

    public PedidoProductoDAO() {
        pedProducto = new PedidoProducto();
    }

    public void insertarPedidoProducto() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO PEDIDO_PRODUCTO(ID_PEDIDO, ID_PRODUCTO) VALUES (?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, pedProducto.getIdPedido());
        pstm.setString(2, pedProducto.getIdProducto());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void actualizarPedidoProducto() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "UPDATE PEDIDO_PRODUCTO SET FECHA_VENCIMIENTO = ?, "
                + "FECHA_REGRESO = ? WHERE ID_PRODUCTO = ? AND ID_PEDIDO = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setDate(1, pedProducto.getFechaVencimiento());
        pstm.setDate(2, pedProducto.getFechaRegreso());
        pstm.setString(3, pedProducto.getIdPedido());
        pstm.setString(4, pedProducto.getIdProducto());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void eliminarPedidoProducto() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM PEDIDO_PRODUCTO WHERE ID_PRODUCTO = ?"
                + "AND ID_PEDIDO = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, pedProducto.getIdProducto());
        pstm.setString(2, pedProducto.getIdPedido());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public ArrayList<PedidoProducto> consultarPorIdPedido(String idPedido) throws SQLException {
        pediprods = new ArrayList<PedidoProducto>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDO_PRODUCTO WHERE ID_PEDIDO = ?";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            pstm.setString(1, idPedido);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PedidoProducto pedprod = new PedidoProducto();
                pedprod.setIdPedido(rs.getString("ID_PEDIDO"));
                pedprod.setIdProducto(rs.getString("ID_PRODUCTO"));
                pediprods.add(pedprod);
            }
            pstm.close();

        } catch (Exception e) {
            conexion.rollback();
        } finally {
            conexion.desconectar();
        }
        return pediprods;
    }
    
    public ArrayList<PedidoProducto> consultarPorIdProductos(String idProducto) throws SQLException {
        pediprods = new ArrayList<PedidoProducto>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDO_PRODUCTO WHERE ID_PRODUCTO = ?";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            pstm.setString(1, idProducto);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PedidoProducto pedprod = new PedidoProducto();
                pedprod.setIdPedido(rs.getString("ID_PEDIDO"));
                pedprod.setIdProducto(rs.getString("ID_PRODUCTO"));
                pediprods.add(pedprod);
            }
            pstm.close();

        } catch (Exception e) {
            conexion.rollback();
        } finally {
            conexion.desconectar();
        }
        return pediprods;
    }
    
    public ArrayList<PedidoProducto> consultarPorFechaVencimiento(Date fecha) throws SQLException {
        pediprods = new ArrayList<PedidoProducto>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDO_PRODUCTO WHERE FECHA_VENCIMIENTO = ?";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            //NO ESTOY SEGURO DE QUE ESTE BIEN CONVERTIR DATE A STRING
            pstm.setString(1, fecha.toString() );
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PedidoProducto pedprod = new PedidoProducto();
                pedprod.setIdPedido(rs.getString("ID_PEDIDO"));
                pedprod.setIdProducto(rs.getString("ID_PRODUCTO"));
                pediprods.add(pedprod);
            }
            pstm.close();

        } catch (Exception e) {
            conexion.rollback();
        } finally {
            conexion.desconectar();
        }
        return pediprods;
    }
    
    public ArrayList<PedidoProducto> consultarPorFechaRegreso(Date fecha) throws SQLException {
        pediprods = new ArrayList<PedidoProducto>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDO_PRODUCTO WHERE FECHA_REGRESO = ?";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            
            //NO ESTOY SEGURO DE QUE ESTE BIEN CONVERTIR DATE A STRING
            pstm.setString(1, fecha.toString() );
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                PedidoProducto pedprod = new PedidoProducto();
                pedprod.setIdPedido(rs.getString("ID_PEDIDO"));
                pedprod.setIdProducto(rs.getString("ID_PRODUCTO"));
                pediprods.add(pedprod);
            }
            pstm.close();

        } catch (Exception e) {
            conexion.rollback();
        } finally {
            conexion.desconectar();
        }
        return pediprods;
    }
    
    public ArrayList<Producto> consultarPruductosDePedido(String idPed) throws SQLException, Exception {
        ArrayList<Producto> prods = new ArrayList<Producto>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT A.* FROM PRODUCTOS A, PEDIDO_PRODUCTO B "
                    + "WHERE B.ID_PEDIDO=? AND A.COD_INDIVIDUAL=B.ID_PRODUCTO;";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
            pstm.setString(1, idPed);
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
                prods.add(prod);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return prods;
    }
    

    public PedidoProducto getPedProducto() {
        return this.pedProducto;
    }

    

    public void setPedProducto(PedidoProducto pedProducto) {
        this.pedProducto = pedProducto;
    }
}
