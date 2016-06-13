/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.ClienteDAO;
import datos.PedidoDAO;
import datos.PedidoProductoDAO;
import datos.ProductoDAO;
import datos.RefClienteClienteDAO;
import datos.RefClienteDAO;
import datos.ReservaDAO;
import datos.ReservaProductoDAO;
import datos.UsuarioDAO;
import datos.ContabilidadDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilson Salamanca
 */
public class Consultas {

    private UsuarioDAO usuarioDao;
    private ClienteDAO clienteDao;
    private PedidoProductoDAO pedProductoDao;
    private ProductoDAO productoDao;
    private RefClienteDAO refClienteDao;
    private PedidoDAO pedidoDao;
    private ReservaDAO reservaDao;
    private ReservaProductoDAO resProductoDao;
    private RefClienteClienteDAO refClienteClienteDAO;
    private ContabilidadDAO contabilidadDao;

    public Consultas() {
        this.usuarioDao = new UsuarioDAO();
        this.clienteDao = new ClienteDAO();
        this.pedProductoDao = new PedidoProductoDAO();
        this.productoDao = new ProductoDAO();
        this.refClienteDao = new RefClienteDAO();
        this.reservaDao = new ReservaDAO();
        this.resProductoDao = new ReservaProductoDAO();
        this.pedidoDao = new PedidoDAO();
        refClienteClienteDAO = new RefClienteClienteDAO();
        contabilidadDao = new ContabilidadDAO();
    }

    public ArrayList<RefCliente> verReferenciasPorCliente(String cedula) throws SQLException, Exception {
        return refClienteClienteDAO.buscarReferencias(cedula);
    }

    public ArrayList<Producto> verProductosPorReserva(String id) throws Exception {
        return resProductoDao.consultarProductosDeReserva(id);
    }

    public int obtenerCodigoReserva() throws SQLException, Exception {


        ArrayList<Reserva> arr = reservaDao.buscarReservaPorIdReserva("");
        Reserva resp = new Reserva();
        resp.setId("0");

        if (arr.size() > 0) {
            resp.setId("" + (arr.size()));
        }

        return Integer.parseInt(resp.getId()) + 1;
    }

    public int obtenerCodigoPedido() throws SQLException, Exception {


        ArrayList<Pedido> arr = pedidoDao.buscarPedidoPorId("");
        Pedido resp = new Pedido();
        resp.setId("0");

        if (arr.size() > 0) {
            resp.setId("" + (arr.size()));
        }

        return Integer.parseInt(resp.getId()) + 1;
    }

    public ArrayList<Pedido> verPedidosPorCliente(String idCliente) throws Exception {
        return pedidoDao.buscarPedidoPorUsuario(idCliente);
    }

    /**
     * Retorna un pedido por ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<Pedido> verPedidoPorID(String id) throws Exception {
        return pedidoDao.buscarPedidoPorId(id);
    }

    /**
     * Retorna todos los pedidos realizados por determinado usuario
     *
     * @param user
     * @return
     * @throws Exception
     */
    public ArrayList<Pedido> verPedidosPorUsuario(String user) throws Exception {
        return pedidoDao.buscarPedidoPorUsuario(user);
    }

    /**
     * Retorna todos los pedidos que hay en la base de datos
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Pedido> verPedidosTodos() throws SQLException, Exception {
        return pedidoDao.getPedidos();
    }

    /**
     * Retorna los pedidos realizados en determinada fecha
     *
     * @param date
     * @return
     * @throws Exception
     */
    public ArrayList<Pedido> verPedidosPorFecha(Date date) throws Exception {
        return pedidoDao.buscarPedidoPorFecha(date);
    }

    /**
     * Retorna el cliente con determinada cédula
     *
     * @param cedula
     * @return
     * @throws Exception
     */
    public ArrayList<Cliente> verClientePorCedula(String cedula) throws Exception {
        return clienteDao.buscarClientePorCedula(cedula);
    }
    
    public ArrayList<Cliente> verClientePorNombre(String nombre) throws Exception {
        return clienteDao.buscarClientePorNombre(nombre);
    }
    public ArrayList<Cliente> verClientePorApellido(String apellido) throws Exception {
        return clienteDao.buscarClientePorApellido(apellido);
    }

    public Cliente verClientePorCedulaUnico(String cedula) throws Exception {
        
        return clienteDao.buscarUnicoClientePorCedula(cedula);
    }

    /**
     * Retorna todos los clientes registrados
     *
     * @return
     * @throws Exception
     */
    public ArrayList<Cliente> verClientesTodos() throws Exception {
        return clienteDao.getClientes();
    }

    /**
     * Retorna un producto por código undividual
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<Producto> verProductoPorID(String id) throws Exception {
        return productoDao.buscarProductoPorCodIndividual(id);
    }

    public Producto verProductoPorIDUnico(String id) throws Exception {
        ArrayList<Producto> arr = productoDao.buscarProductoPorCodIndividual(id);
        for (Iterator<Producto> it = arr.iterator(); it.hasNext();) {
            Producto producto = it.next();
            if (producto.getCod_individual().equals(id)) {
                return producto;
            }

        }
        System.out.println("no se encuentra ningun objeto producto, en la consulta por id unico");
        return null;
    }

    /**
     * Retorna todos los productos con determinado código de bolsa
     *
     * @param cod
     * @return
     * @throws Exception
     */
    public ArrayList<Producto> verProductoPorCodBolsa(String cod) throws Exception {
        return productoDao.buscarProductoPorCodBolsa(cod);
    }

    /**
     * Retorna todos los productos con determinado nombre
     *
     * @param nom
     * @return
     * @throws Exception
     */
    public ArrayList<Producto> verProductosPorNombre(String nom) throws Exception {
        return productoDao.buscarProductoPorNombre(nom);
    }

    /**
     * Retorna todos los productos pertenecientes a determinado país
     *
     * @param pais
     * @return
     * @throws Exception
     */
    public ArrayList<Producto> verProductosPorPais(String pais) throws Exception {
        return productoDao.buscarProductoPorPais(pais);
    }

    /**
     * Retorna los productos dependiendo disponibilidad
     *
     * @param disp
     * @return
     * @throws Exception
     */
    public ArrayList<Producto> verProductosPorDisponibilidad(String disp) throws Exception {
        return productoDao.buscarProductoPorDisponibilidad(disp);
    }

    public ArrayList<Producto> verProductosTodos() throws Exception {
        return productoDao.getProductos();
    }

    /**
     * Retorna una recerva con determinado ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<Reserva> verReservaPorID(String id) throws Exception {
        return reservaDao.buscarReservaPorIdReserva(id);
    }

    /**
     * Retorna todas las reservas registradas
     *
     * @return
     * @throws Exception
     */
    public ArrayList<Reserva> verTodasReservas() throws Exception {
        return reservaDao.getReservas();
    }

    /**
     * Retorna todos los productos de determinada reserva
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<Producto> verProductosDePedido(String idPedido) throws SQLException, Exception {
        return pedProductoDao.consultarPruductosDePedido(idPedido);
    }

    /**
     * Retorna las reservas realizadas por determinado cliente
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ArrayList<Reserva> verReservaPorCliente(String id) throws Exception {
        return reservaDao.buscarReservaPorIdCliente(id);
    }

    /**
     * Retorna las reservas realizadas por determinado usuario
     *
     * @param user
     * @return
     * @throws Exception
     */
    public ArrayList<Reserva> verReservaPorUsuario(String user) throws Exception {
        return reservaDao.buscarReservaPorUsuario(user);
    }

    public ArrayList<Reserva> verReservaPorFechaReserva(Date date) throws Exception {
        return reservaDao.buscarReservaPorfechaReserva(date);
    }

    public ArrayList<Reserva> verReservaPorFechaRealizada(Date date) throws Exception {
        return reservaDao.buscarReservaPorfechaRealizada(date);
    }

    //Para admin
    public ArrayList<Usuario> verUsuariosTodos() throws Exception {
        return usuarioDao.getUsuarios();
    }

    public ArrayList<Usuario> verUsuarioPorCedula(String cedula) throws Exception {
        return usuarioDao.buscarPorCedula(cedula);
    }

    public ArrayList<Usuario> verUsuarioPorRol(String rol) throws Exception {
        return usuarioDao.buscarPorRol(rol);
    }

    public ArrayList<Usuario> verUsuarioPorUser(String user) throws Exception {
        return usuarioDao.buscarPorUsuario(user);
    }

    public ArrayList<RefCliente> verReferenciaPorCedula(String cedula) throws Exception {
        return refClienteDao.buscarRefClientePorCedula(cedula);
    }

    public ArrayList<Reserva> verReservasPorRangoDeFechaRealizadaYCedulaCliente(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return reservaDao.buscarReservaPorRangoDeFechaRealizadaPorCedula(dateini, datefin, cedula);
    }

    public ArrayList<Reserva> verReservasPorRangoDeFechaRealizadaYCodigoReserva(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return reservaDao.buscarReservaPorRangoDeFechaRealizadaPorCodReserva(dateini, datefin, cedula);
    }

    public ArrayList<Reserva> verReservasPorRangoDeFechaRealizadaYUsuario(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return reservaDao.buscarReservaPorRangoDeFechaRealizadaPorUsuario(dateini, datefin, cedula);
    }

    public ArrayList<Reserva> verReservasPorRangoDeFechaReservaYCedulaCliente(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return reservaDao.buscarReservaPorRangoDeFechaReservaPorCedula(dateini, datefin, cedula);
    }

    public ArrayList<Reserva> verReservasPorRangoDeFechaReservaYCodigoReserva(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return reservaDao.buscarReservaPorRangoDeFechaReservaPorCodReserva(dateini, datefin, cedula);
    }

    public ArrayList<Reserva> verReservasPorRangoDeFechaReservaYUsuario(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return reservaDao.buscarReservaPorRangoDeFechaReservaPorUsuario(dateini, datefin, cedula);
    }

    public ArrayList<Pedido> verPedidosPorRangoDeFechaYCedulaCliente(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return pedidoDao.buscarPedidoPorRangoDeFechaPorCedCliente(dateini, datefin, cedula);
    }

    public ArrayList<Pedido> verPedidosPorRangoDeFechaYCodigoPedido(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return pedidoDao.buscarPedidoPorRangoDeFechaPorIdPedido(dateini, datefin, cedula);
    }

    public ArrayList<Pedido> verPedidosPorRangoDeFechaYUsuario(java.util.Date dateini,
            java.util.Date datefin, String cedula) throws Exception {
        return pedidoDao.buscarPedidoPorRangoDeFechaPorUsuario(dateini, datefin, cedula);
    }

    public ArrayList<Pedido> verPedidosPorPendienteDePagoODeEntregaYCedula(String txt) throws Exception {
        return pedidoDao.buscarPedidoPorFaltaDeEntregaODePagoYCedula(txt);
    }

    public ArrayList<Pedido> verPedidosPorPendienteDePagoODeEntregaYIdPedido(String txt) throws Exception {
        return pedidoDao.buscarPedidoPorFaltaDeEntregaODePagoYIdPedido(txt);
    }

    public ArrayList<Pedido> verPedidosPorPendienteDePagoODeEntregaYUsuario(String txt) throws Exception {
        return pedidoDao.buscarPedidoPorFaltaDeEntregaODePagoYUsuario(txt);
    }

    public ArrayList<Pedido> verPedidosPorRangoDeFechayIdyPagos(java.util.Date dateini,
            java.util.Date datefin, String id) throws Exception {
        return pedidoDao.buscarPedidoPorRangoDeFechaPorPago(dateini, datefin, id);
    }

    public ArrayList<Contabilidad> verContabilidadPedidosPorRangoDeFechaDia(java.util.Date dateini) throws Exception {
        return contabilidadDao.consultaContabilidadPedidosPorDiaRangoDeFecha(dateini);
    }

    public ArrayList<Contabilidad> verContabilidadPedidosPorRangoDeFechaMes(java.util.Date dateini) throws Exception {
        return contabilidadDao.consultaContabilidadPedidosPorMesRangoDeFecha(dateini);
    }

    public ArrayList<Contabilidad> verContabilidadPedidosPorRangoDeFechaAno(java.util.Date dateini) throws Exception {
        return contabilidadDao.consultaContabilidadPedidosPorAno();
    }

    public ArrayList<Contabilidad> verContabilidadPedidosPorUnicoDia(java.util.Date dateini) throws SQLException {
        ArrayList<Contabilidad> contabilidads = new ArrayList<Contabilidad>();

        contabilidads = contabilidadDao.consultaContabilidadPedidosPorUnicoDia(dateini);
        System.out.println("CONSULTA BIEN");

        return contabilidads;
    }

    public ArrayList<Reserva> verReservaPorProducto(String id) throws SQLException, Exception {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();
        ArrayList<ReservaProducto> resPro = new ArrayList<ReservaProducto>();
        resPro = resProductoDao.consultarPorIdProducto(id);
        for (Iterator<ReservaProducto> it = resPro.iterator(); it.hasNext();) {
            ReservaProducto reservaProducto = it.next();
            reservas.add(verReservaPorID(reservaProducto.getIdReserva()).get(0));
        }
        return reservas;
    }
}
