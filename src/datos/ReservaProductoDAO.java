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
import negocio.PedidoProducto;
import negocio.Producto;
import negocio.ReservaProducto;

/**
 *
 * @author Wilson Salamanca
 */
public class ReservaProductoDAO {

    private ReservaProducto resProducto;
    private ConexionBD conexion;
    private ArrayList<ReservaProducto> resers;
    

    public ReservaProductoDAO() {
        resProducto = new ReservaProducto();
    }

    public void insertarReservaProducto() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO RESERVA_PRODUCTO(ID_RESERVA, ID_PRODUCTO)"
                + " VALUES (?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, resProducto.getIdReserva());
        pstm.setString(2, resProducto.getIdProducto());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }
    
    public void eliminarReservaProducto() throws SQLException{
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM RESERVA_PRODUCTO WHERE ID_RESERVA = ?"
                + "AND ID_PRODUCTO = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, resProducto.getIdReserva());
        pstm.setString(2, resProducto.getIdProducto());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }
    
    public ArrayList<ReservaProducto> consultarPorIdReserva(String idReserva) throws SQLException{
        resers = new ArrayList<ReservaProducto>();
        conexion = new ConexionBD();
        try{
            String sqlCommand = "SELECT * FROM RESERVA_PRODUCTO WHERE ID_RESERVA LIKE ? ORDER BY ID_RESERVA ASC ";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, idReserva);
            ResultSet rs = pstm.executeQuery();
                         
            while(rs.next()){
                ReservaProducto reserva = new ReservaProducto();
                reserva.setIdProducto(rs.getString("ID_RESERVA"));
                reserva.setIdReserva(rs.getString("ID_PRODUCTO"));
                resers.add(reserva);
            }
            pstm.close();
            
        }catch(Exception e){
            conexion.rollback();
        }finally{
            conexion.desconectar();
        }
        return resers;
    }
    
    public ArrayList<ReservaProducto> consultarPorIdProducto(String idProducto) throws SQLException{
        resers = new ArrayList<ReservaProducto>();
        conexion = new ConexionBD();
        try{
            String sqlCommand = "SELECT * FROM RESERVA_PRODUCTO WHERE ID_PRODUCTO = ?";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, idProducto);
            ResultSet rs = pstm.executeQuery();
                         
            while(rs.next()){
                ReservaProducto reserva = new ReservaProducto();
                reserva.setIdReserva(rs.getString("ID_RESERVA"));
                reserva.setIdProducto(rs.getString("ID_PRODUCTO"));
                resers.add(reserva);
            }
            pstm.close();
            
        }catch(Exception e){
            conexion.rollback();
        }finally{
            conexion.desconectar();
        }
        return resers;
    }
    
    public ArrayList<Producto> consultarProductosDeReserva(String idRes) throws SQLException, Exception {
        ArrayList<Producto> prods = new ArrayList<Producto>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT A.* FROM PRODUCTOS A, RESERVA_PRODUCTO B "
                    + "WHERE B.ID_RESERVA=? AND A.COD_INDIVIDUAL=B.ID_PRODUCTO;";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
            pstm.setString(1, idRes);
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

    public ReservaProducto getResProducto() {
        return this.resProducto;
    }

    public void setResProducto(ReservaProducto resProducto) {
        this.resProducto = resProducto;
    }
    
    
}
