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

/**
 *
 * @author Wilson Salamanca
 */
public class PedidoDAO {

    private ArrayList pedidos;
    private Pedido pedido;
    private ConexionBD conexion;

    public PedidoDAO() {
        pedido = new Pedido();
    }

    public void insertarPedido() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO PEDIDOS(ID_PEDIDO, FECHA_EMISION,"
                + "MULTA, NUM_PRODUCTOS, VALOR_TOTAL, ID_CLIENTE, PAGADO, SALDO,"
                + "NOM_USUARIO, DESCUENTO, FECHA_ENTREGA, FECHA_DEVOLUCION, FECHA_MOD,SUBTOTAL"
                + ", PENDIENTE_PAGO,PENDIENTE_ENTREGA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, pedido.getId());
        pstm.setDate(2, pedido.getEmision());
        pstm.setDouble(3, pedido.getMulta());
        pstm.setInt(4, pedido.getNumProductos());
        pstm.setDouble(5, pedido.getTotal());
        pstm.setString(6, pedido.getIdCliente());
        pstm.setDouble(7, pedido.getPagado());
        pstm.setDouble(8, pedido.getSaldo());
        pstm.setString(9, pedido.getUsuario());
        pstm.setDouble(10, pedido.getDescuento());
        pstm.setDate(11, pedido.getFechaentrega());
        pstm.setDate(12, pedido.getFechadevolucion());
        pstm.setDate(13, pedido.getFechamod());
        pstm.setDouble(14, pedido.getSubtotal());
        pstm.setInt(15, pedido.getPendiente_pago());
        pstm.setInt(16, pedido.getPendiente_entrega());

        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void actualizarPedido() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "UPDATE PEDIDOS SET FECHA_EMISION = ?,"
                + "MULTA = ?, NUM_PRODUCTOS = ?, VALOR_TOTAL = ?, ID_CLIENTE = ?, "
                + "PAGADO = ?, SALDO = ?,NOM_USUARIO = ?, DESCUENTO = ? , FECHA_ENTREGA = ?, FECHA_DEVOLUCION = ?, FECHA_MOD = ?,"
                + "PENDIENTE_PAGO = ?, PENDIENTE_ENTREGA = ?, SUBTOTAL = ?"
                + " WHERE ID_PEDIDO = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setDate(1, pedido.getEmision());
        pstm.setDouble(2, pedido.getMulta());
        pstm.setInt(3, pedido.getNumProductos());
        pstm.setDouble(4, pedido.getTotal());
        pstm.setString(5, pedido.getIdCliente());
        pstm.setDouble(6, pedido.getPagado());
        pstm.setDouble(7, pedido.getSaldo());
        pstm.setString(8, pedido.getUsuario());
        pstm.setDouble(9, pedido.getDescuento());
        pstm.setDate(10, pedido.getFechaentrega());
        pstm.setDate(11, pedido.getFechadevolucion());
        pstm.setDate(12, pedido.getFechamod());
        pstm.setInt(13, pedido.getPendiente_pago());
        pstm.setInt(14, pedido.getPendiente_entrega());
        pstm.setDouble(15, pedido.getSubtotal());
        pstm.setString(16, pedido.getId());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void eliminarPedido() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM PEDIDOS WHERE ID_PEDIDO = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, pedido.getId());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public Pedido getPedido() {
        return this.pedido;
    }

    public ArrayList<Pedido> buscarPedidoPorId(String idpedi) throws Exception {

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE ID_PEDIDO LIKE '" + idpedi + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorIdCliente(String idclient) throws Exception {

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE ID_CLIENTE LIKE '" + idclient + "%' ORDER BY ID_PEDIDO+0 ASC";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList<Pedido> buscarPedidoPorUsuario(String user) throws Exception {

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE NOM_USUARIO LIKE '" + user + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //  conexion.commit();
            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
            pstm.close();
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList<Pedido> getPedidos() throws SQLException, Exception {

        pedidos = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList<Pedido> buscarPedidoPorFecha(Date fecha) throws Exception {

        pedidos = new ArrayList();

        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE FECHA_EMISION LIKE '" + fecha + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {

                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorFaltaDePago() throws Exception {

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE PENDIENTE_PAGO = '1'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorFaltaDeEntrega() throws Exception {

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE PENDIENTE_ENTREGA = '1'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorFaltaDeEntregaODePagoYCedula(String idclient) throws Exception {

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE ID_CLIENTE LIKE '" + idclient + "%' AND (PENDIENTE_ENTREGA = '1' OR PENDIENTE_PAGO = '1')";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorFaltaDeEntregaODePagoYIdPedido(String idpedido) throws Exception {

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE ID_PEDIDO LIKE '" + idpedido + "%' AND (PENDIENTE_ENTREGA = '1' OR PENDIENTE_PAGO = '1')";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorFaltaDeEntregaODePagoYUsuario(String usuario) throws Exception {

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE NOM_USUARIO LIKE '" + usuario + "%' AND (PENDIENTE_ENTREGA = '1' OR PENDIENTE_PAGO = '1')";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Pedido pedi = new Pedido();
                pedi.setId(rs.getString("ID_PEDIDO"));
                pedi.setEmision(rs.getDate("FECHA_EMISION"));
                pedi.setMulta(rs.getDouble("MULTA"));
                pedi.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                pedi.setTotal(rs.getDouble("VALOR_TOTAL"));
                pedi.setIdCliente(rs.getString("ID_CLIENTE"));
                pedi.setPagado(rs.getDouble("PAGADO"));
                pedi.setSaldo(rs.getDouble("SALDO"));
                pedi.setUsuario(rs.getString("NOM_USUARIO"));
                pedi.setDescuento(rs.getDouble("DESCUENTO"));
                pedi.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                pedi.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                pedi.setFechamod(rs.getDate("FECHA_MOD"));
                pedi.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                pedi.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                pedi.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(pedi);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorRangoDeFechaPorUsuario(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE NOM_USUARIO LIKE '" + txt + "%' AND FECHA_EMISION BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId(rs.getString("ID_PEDIDO"));
                ped.setEmision(rs.getDate("FECHA_EMISION"));
                ped.setMulta(rs.getDouble("MULTA"));
                ped.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                ped.setTotal(rs.getDouble("VALOR_TOTAL"));
                ped.setIdCliente(rs.getString("ID_CLIENTE"));
                ped.setPagado(rs.getDouble("PAGADO"));
                ped.setSaldo(rs.getDouble("SALDO"));
                ped.setUsuario(rs.getString("NOM_USUARIO"));
                ped.setDescuento(rs.getDouble("DESCUENTO"));
                ped.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                ped.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                ped.setFechamod(rs.getDate("FECHA_MOD"));
                ped.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                ped.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                ped.setSubtotal(rs.getDouble("SUBTOTAL"));
                

                pedidos.add(ped);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorRangoDeFechaPorIdPedido(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE ID_PEDIDO LIKE '" + txt + "%' AND FECHA_EMISION BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId(rs.getString("ID_PEDIDO"));
                ped.setEmision(rs.getDate("FECHA_EMISION"));
                ped.setMulta(rs.getDouble("MULTA"));
                ped.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                ped.setTotal(rs.getDouble("VALOR_TOTAL"));
                ped.setIdCliente(rs.getString("ID_CLIENTE"));
                ped.setPagado(rs.getDouble("PAGADO"));
                ped.setSaldo(rs.getDouble("SALDO"));
                ped.setUsuario(rs.getString("NOM_USUARIO"));
                ped.setDescuento(rs.getDouble("DESCUENTO"));
                ped.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                ped.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                ped.setFechamod(rs.getDate("FECHA_MOD"));
                ped.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                ped.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                ped.setSubtotal(rs.getDouble("SUBTOTAL"));


                pedidos.add(ped);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorRangoDeFechaPorCedCliente(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE ID_CLIENTE LIKE '" + txt + "%' AND FECHA_EMISION BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId(rs.getString("ID_PEDIDO"));
                ped.setEmision(rs.getDate("FECHA_EMISION"));
                ped.setMulta(rs.getDouble("MULTA"));
                ped.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                ped.setTotal(rs.getDouble("VALOR_TOTAL"));
                ped.setIdCliente(rs.getString("ID_CLIENTE"));
                ped.setPagado(rs.getDouble("PAGADO"));
                ped.setSaldo(rs.getDouble("SALDO"));
                ped.setUsuario(rs.getString("NOM_USUARIO"));
                ped.setDescuento(rs.getDouble("DESCUENTO"));
                ped.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                ped.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                ped.setFechamod(rs.getDate("FECHA_MOD"));
                ped.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                ped.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                ped.setSubtotal(rs.getDouble("SUBTOTAL"));

                pedidos.add(ped);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorRangoDeFecha(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE FECHA_EMISION BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId(rs.getString("ID_PEDIDO"));
                ped.setEmision(rs.getDate("FECHA_EMISION"));
                ped.setMulta(rs.getDouble("MULTA"));
                ped.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                ped.setTotal(rs.getDouble("VALOR_TOTAL"));
                ped.setIdCliente(rs.getString("ID_CLIENTE"));
                ped.setPagado(rs.getDouble("PAGADO"));
                ped.setSaldo(rs.getDouble("SALDO"));
                ped.setUsuario(rs.getString("NOM_USUARIO"));
                ped.setDescuento(rs.getDouble("DESCUENTO"));
                ped.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                ped.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                ped.setFechamod(rs.getDate("FECHA_MOD"));
                ped.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                ped.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                ped.setSubtotal(rs.getDouble("SUBTOTAL"));
                pedidos.add(ped);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public ArrayList buscarPedidoPorRangoDeFechaPorPago(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        pedidos = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM PEDIDOS WHERE ID_PEDIDO LIKE '" + txt + "%' AND PENDIENTE_PAGO='0'  AND FECHA_EMISION BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId(rs.getString("ID_PEDIDO"));
                ped.setEmision(rs.getDate("FECHA_EMISION"));
                ped.setMulta(rs.getDouble("MULTA"));
                ped.setNumProductos(rs.getInt("NUM_PRODUCTOS"));
                ped.setTotal(rs.getDouble("VALOR_TOTAL"));
                ped.setIdCliente(rs.getString("ID_CLIENTE"));
                ped.setPagado(rs.getDouble("PAGADO"));
                ped.setSaldo(rs.getDouble("SALDO"));
                ped.setUsuario(rs.getString("NOM_USUARIO"));
                ped.setDescuento(rs.getDouble("DESCUENTO"));
                ped.setFechaentrega(rs.getDate("FECHA_ENTREGA"));
                ped.setFechadevolucion(rs.getDate("FECHA_DEVOLUCION"));
                ped.setFechamod(rs.getDate("FECHA_MOD"));
                ped.setPendiente_entrega(rs.getInt("PENDIENTE_ENTREGA"));
                ped.setPendiente_pago(rs.getInt("PENDIENTE_PAGO"));
                ped.setSubtotal(rs.getDouble("SUBTOTAL"));

                pedidos.add(ped);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return pedidos;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
