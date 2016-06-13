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
import negocio.Reserva;

/**
 *
 * @author Wilson Salamanca
 */
public class ReservaDAO {

    private ArrayList reservas;
    private Reserva reserva;
    private ConexionBD conexion;

    public ReservaDAO() {
        reserva = new Reserva();
    }

    public void insertarReserva() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "INSERT INTO RESERVAS(ID_RESERVA, ID_CLIENTE, "
                + "FECHA_REALIZADA, FECHA_RESERVA_INI, USUARIO, ABONO, TOTAL, ELIMINADO,"
                + "ACTIVO, PENDIENTE, PEDIDO, FECHA_MOD, FECHA_RESERVA_FIN, DIAS_RESERVA) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, reserva.getId());
        pstm.setString(2, reserva.getIdCliente());
        pstm.setDate(3, reserva.getFechaRealizada());
        pstm.setDate(4, reserva.getFechaReservaIni());
        pstm.setString(5, reserva.getUsuario());
        pstm.setDouble(6, reserva.getAbono());
        pstm.setDouble(7, reserva.getTotal());
        pstm.setInt(8, reserva.getEliminado());
        pstm.setInt(9, reserva.getActivo());
        pstm.setDouble(10, reserva.getPendiente());
        pstm.setString(11, reserva.getPedido());
        pstm.setDate(12, reserva.getFechamod());
        pstm.setDate(13, reserva.getFechaReservafin());
        pstm.setDate(14, reserva.getDiasReserva());
        
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void actualizarReserva() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "UPDATE RESERVAS SET ID_CLIENTE = ?, "
                + "FECHA_REALIZADA = ?, FECHA_RESERVA_INI = ?, USUARIO = ?,"
                + " ABONO = ?, TOTAL = ?, ELIMINADO = ?,"
                + "ACTIVO = ?, PENDIENTE = ?, PEDIDO = ?, FECHA_MOD = ?, "
                + "FECHA_RESERVA_FIN = ?, DIAS_RESERVA = ? WHERE ID_RESERVA =?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, reserva.getIdCliente());
        pstm.setDate(2, reserva.getFechaRealizada());
        pstm.setDate(3, reserva.getFechaReservaIni());
        pstm.setString(4, reserva.getUsuario());
        pstm.setDouble(5, reserva.getAbono());
        pstm.setDouble(6, reserva.getTotal());
        pstm.setInt(7, reserva.getEliminado());
        pstm.setInt(8, reserva.getActivo());
        pstm.setDouble(9, reserva.getPendiente());
        pstm.setString(10, reserva.getPedido());
        pstm.setDate(11, reserva.getFechamod());
        pstm.setDate(12, reserva.getFechaReservafin());
        pstm.setDate(13, reserva.getDiasReserva());
        pstm.setString(14, reserva.getId());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public void eliminarReserva() throws SQLException {
        conexion = new ConexionBD();
        String sqlCommand = "DELETE FROM RESERVAS WHERE ID_RESERVA = ?";
        PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand);
        pstm.setString(1, reserva.getId());
        pstm.executeUpdate();
        pstm.close();
        conexion.commit();
        conexion.desconectar();
    }

    public ArrayList<Reserva> buscarReservaPorIdReserva(String idrese) throws Exception {

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE ID_RESERVA LIKE '" + idrese + "%' ORDER BY ID_RESERVA+0 ASC";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            // pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
            
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList<Reserva> buscarReservaPorIdCliente(String idcli) throws Exception {

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE ID_CLIENTE LIKE '" + idcli + "%' ORDER BY ID_RESERVA";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {
            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorfechaReserva(Date fechare) throws Exception {

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE FECHA_RESERVA_INI LIKE '" + fechare + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
            
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorfechaRealizada(Date fecharea) throws Exception {

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE FECHA_REALIZADA LIKE '" + fecharea + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorUsuario(String user) throws Exception {

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE USUARIO LIKE '" + user + "%'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorRangoDeFechaRealizadaPorCedula(java.util.Date dateini, java.util.Date datefin, String Cedula) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE ID_CLIENTE LIKE '" + Cedula + "%' AND FECHA_REALIZADA BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorRangoDeFechaRealizadaPorCodReserva(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE ID_RESERVA LIKE '" + txt + "%' AND FECHA_REALIZADA BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorRangoDeFechaRealizadaPorUsuario(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE USUARIO LIKE '" + txt + "%' AND FECHA_REALIZADA BETWEEN  '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorRangoDeFechaReservaPorUsuario(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE USUARIO LIKE '" + txt + "%' AND FECHA_RESERVA_INI BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
               res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorRangoDeFechaReservaPorCodReserva(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE ID_RESERVA LIKE '" + txt + "%' AND FECHA_RESERVA_INI BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            System.out.println("ErrorConsultaReservaDAO");
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList buscarReservaPorRangoDeFechaReservaPorCedula(java.util.Date dateini, java.util.Date datefin, String txt) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        reservas = new ArrayList();
        conexion = new ConexionBD();

        
        try {
            System.out.println("" + sqlDateini + " " + sqlDatefin + "  " + dateini.getTime());
            String sqlCommand = "SELECT * FROM RESERVAS WHERE ID_CLIENTE LIKE '" + txt + "%' AND FECHA_RESERVA_INI BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            
            
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
                
            }
        } catch (Exception ex) {
            System.out.println("ErrorConsultaReservaDAO");
            conexion.rollback();
            
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList<Reserva> buscarReservaPorRangoDeFechaReserva(java.util.Date dateini, java.util.Date datefin) throws Exception {

        java.sql.Date sqlDateini = new java.sql.Date(dateini.getTime());
        java.sql.Date sqlDatefin = new java.sql.Date(datefin.getTime());

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS WHERE FECHA_RESERVA_INI BETWEEN '" + sqlDateini + " 00:00:00' AND '" + sqlDatefin + " 23:59:59'";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public ArrayList getReservas() throws Exception {

        reservas = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT * FROM RESERVAS";
            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //pstm.executeQuery();
            //conexion.commit();
            while (rs.next()) {
                Reserva res = new Reserva();
                res.setId(rs.getString("ID_RESERVA"));
                res.setIdCliente(rs.getString("ID_CLIENTE"));
                res.setFechaRealizada(rs.getDate("FECHA_REALIZADA"));
                res.setFechaReservaini(rs.getDate("FECHA_RESERVA_INI"));
                res.setUsuario(rs.getString("USUARIO"));
                res.setAbono(rs.getDouble("ABONO"));
                res.setTotal(rs.getDouble("TOTAL"));
                res.setEliminado(rs.getInt("ELIMINADO"));
                res.setActivo(rs.getInt("ACTIVO"));
                res.setPendiente(rs.getDouble("PENDIENTE"));
                res.setPedido(rs.getString("PEDIDO"));
                res.setFechamod(rs.getDate("FECHA_MOD"));
                res.setFechaReservafin(rs.getDate("FECHA_RESERVA_FIN"));
                res.setDiasReserva(rs.getDate("DIAS_RESERVA"));
                reservas.add(res);
            }
        } catch (Exception ex) {
            conexion.rollback();
            System.out.println("ErrorConsultaReservaDAO");
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return reservas;
    }

    public Reserva getReserva() {
        return this.reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}
