/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

/**
 *
 * @author Ivan C
 */
import com.mysql.jdbc.PreparedStatement;
import conexion.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Contabilidad;
import util.CalendarUtil;

/**
 *
 * @author Wilson Salamanca
 */
public class ContabilidadDAO {

    private ArrayList<Contabilidad> contabs;
    private Contabilidad contab;
    private ConexionBD conexion;
    private java.util.Date fecha;

    public Contabilidad getContabilidad() {
        return contab;
    }

    public void setContabilidad(Contabilidad contab) {
        this.contab = contab;
    }

    public ContabilidadDAO() {
        contab = new Contabilidad();
    }

    //PARA ALMACENAR EN ARRAYLIST
    public ArrayList<Contabilidad> consultaContabilidadPedidosPorUnicoDia(java.util.Date fecha2) throws SQLException {

        fecha = new Date(fecha2.getTime());
        java.sql.Date sqlDateini = new java.sql.Date(fecha.getTime());
        /*java.sql.Date sqlDatefin = new java.sql.Date(fecha.getTime());
        System.out.print("\nfechaini: " + CalendarUtil.cambiarFecha(fecha, -1, 0, 0) + " fechafin: " + fecha + "   ");*/

        contabs = new ArrayList();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT id_pedido, fecha_emision, num_productos, "
                    + "multa, valor_total,descuento, saldo,subtotal FROM PEDIDOS "
                    + "WHERE fecha_emision BETWEEN '"
                    + "" + sqlDateini + " 00:00:00' AND '" + sqlDateini + " 23:59:59'";


            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Contabilidad cont = new Contabilidad();
                cont.setFecha(rs.getDate("fecha_emision"));
                cont.setFechafin(rs.getDate("fecha_emision"));
                cont.setNum_pedidos(/*rs.getInt("num_pedidos")*/0);
                cont.setNum_productos(rs.getInt("num_productos"));
                cont.setMultas(rs.getDouble("multa"));
                cont.setTotal(rs.getDouble("valor_total"));
                cont.setPendiente(rs.getDouble("saldo"));
                cont.setIdop(rs.getString("id_pedido"));
                cont.setDescuentos(rs.getInt("descuento"));
                cont.setSubtotal(rs.getDouble("subtotal"));
                contabs.add(cont);
            }
        } catch (Exception ex) {

            conexion.rollback();

        } finally {
            try {
                conexion.desconectar();
            } catch (SQLException ex) {
                Logger.getLogger(ContabilidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contabs;
    }

    public ArrayList<Contabilidad> consultaContabilidadPedidosPorDiaRangoDeFecha(java.util.Date fecha2) throws Exception {

        fecha = new Date(fecha2.getTime());


        fecha.setDate(CalendarUtil.getPrimerDiaDelMes(fecha).getDate());
        java.sql.Date sqlDateini = new java.sql.Date(CalendarUtil.getPrimerDiaDelMes(fecha).getTime());
        System.out.print("\nfechaini: " + fecha + "   ");


        fecha.setDate(CalendarUtil.getUltimoDiaDelMes(fecha).getDate());
        java.sql.Date sqlDatefin = new java.sql.Date(CalendarUtil.getUltimoDiaDelMes(fecha).getTime());
        System.out.println("fechafin: " + fecha);

        contabs = new ArrayList<Contabilidad>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT fecha_emision as fecha,"
                    + "SUM(num_productos) as num_productos, COUNT(*) as num_pedidos,"
                    + "SUM(multa) as multas ,SUM(valor_total) as total,"
                    + "SUM(descuento) as descuentos, SUM(subtotal) as subtotales,"
                    + "SUM(saldo) as pendiente "
                    + "FROM pedidos WHERE FECHA_EMISION BETWEEN '"
                    + "" + sqlDateini + " 00:00:00' AND '" + sqlDatefin
                    + " 23:59:59' GROUP BY DAY(fecha_emision),MONTH(fecha_emision)";


            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Contabilidad cont = new Contabilidad();
                cont.setFecha(rs.getDate("fecha"));
                cont.setFechafin(rs.getDate("fecha"));
                cont.setNum_pedidos(rs.getInt("num_pedidos"));
                cont.setNum_productos(rs.getInt("num_productos"));
                cont.setMultas(rs.getDouble("multas"));
                cont.setTotal(rs.getDouble("total"));
                cont.setPendiente(rs.getDouble("pendiente"));
                cont.setIdop(/*rs.getString("id_pedido")*/"0");
                cont.setDescuentos(rs.getInt("descuentos"));
                cont.setSubtotal(rs.getDouble("subtotales"));
                contabs.add(cont);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return contabs;
    }

    public ArrayList<Contabilidad> consultaContabilidadPedidosPorMesRangoDeFecha(java.util.Date fecha2) throws Exception {

        fecha = new Date(fecha2.getTime());

        fecha.setMonth(Calendar.JANUARY);
        fecha.setDate(CalendarUtil.getPrimerDiaDelMes(fecha).getDate());
        java.sql.Date sqlDateini = new java.sql.Date(CalendarUtil.getPrimerDiaDelMes(fecha).getTime());
        System.out.print("\nfechaini: " + fecha + "   ");

        fecha.setMonth(Calendar.DECEMBER);
        fecha.setDate(CalendarUtil.getUltimoDiaDelMes(fecha).getDate());
        java.sql.Date sqlDatefin = new java.sql.Date(CalendarUtil.getUltimoDiaDelMes(fecha).getTime());

        System.out.println("fechafin: " + fecha);


        contabs = new ArrayList<Contabilidad>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT fecha_emision as fecha,"
                    + "SUM(num_productos) as num_productos, COUNT(*) as num_pedidos,"
                    + "SUM(multa) as multas ,SUM(valor_total) as total,"
                    + "SUM(descuento) as descuentos, SUM(subtotal) as subtotales,"
                    + "SUM(saldo) as pendiente "
                    + "FROM pedidos WHERE FECHA_EMISION BETWEEN '"
                    + "" + sqlDateini + " 00:00:00' AND '" + sqlDatefin
                    + " 23:59:59' GROUP BY MONTH(fecha_emision),YEAR(fecha_emision)";


            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            //  pstm.executeQuery();
            // conexion.commit();
            while (rs.next()) {
                Contabilidad cont = new Contabilidad();
                cont.setFecha(rs.getDate("fecha"));
                cont.setFechafin(rs.getDate("fecha"));
                cont.setNum_pedidos(rs.getInt("num_pedidos"));
                cont.setNum_productos(rs.getInt("num_productos"));
                cont.setMultas(rs.getDouble("multas"));
                cont.setTotal(rs.getDouble("total"));
                cont.setPendiente(rs.getDouble("pendiente"));
                cont.setIdop(/*rs.getString("id_pedido")*/"0");
                cont.setDescuentos(rs.getInt("descuentos"));
                cont.setSubtotal(rs.getDouble("subtotales"));
                contabs.add(cont);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return contabs;
    }

    public ArrayList<Contabilidad> consultaContabilidadPedidosPorAno() throws Exception {

        contabs = new ArrayList<Contabilidad>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT fecha_emision as fecha,"
                    + "SUM(num_productos) as num_productos, COUNT(*) as num_pedidos,"
                    + "SUM(multa) as multas ,SUM(valor_total) as total,"
                    + "SUM(descuento) as descuentos, SUM(subtotal) as subtotales,"
                    + "SUM(saldo) as pendiente "
                    + "FROM pedidos GROUP BY YEAR(fecha_emision)";


            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Contabilidad cont = new Contabilidad();
                cont.setFecha(rs.getDate("fecha"));
                cont.setFechafin(rs.getDate("fecha"));
                cont.setNum_pedidos(rs.getInt("num_pedidos"));
                cont.setNum_productos(rs.getInt("num_productos"));
                cont.setMultas(rs.getDouble("multas"));
                cont.setTotal(rs.getDouble("total"));
                cont.setPendiente(rs.getDouble("pendiente"));
                cont.setIdop(/*rs.getString("id_pedido")*/"0");
                cont.setDescuentos(rs.getInt("descuentos"));
                cont.setSubtotal(rs.getDouble("subtotales"));
                contabs.add(cont);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return contabs;
    }
    
    public ArrayList<Contabilidad> consultaContabilidadReservasPorAno() throws Exception {

        contabs = new ArrayList<Contabilidad>();
        conexion = new ConexionBD();
        try {
            String sqlCommand = "SELECT fecha_emision as fecha,"
                    + "SUM(num_productos) as num_productos, COUNT(*) as num_pedidos,"
                    + "SUM(multa) as multas ,SUM(valor_total) as total,"
                    + "SUM(descuento) as descuentos, SUM(subtotal) as subtotales,"
                    + "SUM(saldo) as pendiente "
                    + "FROM reservas GROUP BY YEAR(fecha_emision)";


            PreparedStatement pstm = (PreparedStatement) conexion.getConexion().prepareStatement(sqlCommand,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Contabilidad cont = new Contabilidad();
                cont.setFecha(rs.getDate("fecha"));
                cont.setFechafin(rs.getDate("fecha"));
                cont.setNum_pedidos(rs.getInt("num_pedidos"));
                cont.setNum_productos(rs.getInt("num_productos"));
                cont.setMultas(rs.getDouble("multas"));
                cont.setTotal(rs.getDouble("total"));
                cont.setPendiente(rs.getDouble("pendiente"));
                cont.setIdop(/*rs.getString("id_pedido")*/"0");
                cont.setDescuentos(rs.getInt("descuentos"));
                cont.setSubtotal(rs.getDouble("subtotales"));
                contabs.add(cont);
            }
        } catch (Exception ex) {
            conexion.rollback();
            throw ex;
        } finally {

            conexion.desconectar();
        }
        return contabs;
    }
}
