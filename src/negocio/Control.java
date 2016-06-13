/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.UsuarioDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson Salamanca
 */
public class Control {

    private Consultas consulta;
    private Inserciones insercion;
    private Historial historial;
    private Usuario usuario;

    public Control() {
        consulta = new Consultas();
        insercion = new Inserciones();
        historial = new Historial();
        usuario = new Usuario();
    }
    
    public void setUsuario(Usuario u){
        this.usuario = u;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }
    
    public ArrayList<RefCliente> consultarRefereciasPorCliente(String cedula) throws Exception {
        return consulta.verReferenciasPorCliente(cedula);
    }

    public ArrayList<Producto> consultarPorductosReserva(String idRes) throws Exception {
        return consulta.verProductosPorReserva(idRes);
    }

    public ArrayList<Producto> consultarProductosPorPedido(String idPedido) throws SQLException, Exception {
        return consulta.verProductosDePedido(idPedido);
    }

    public ArrayList<Pedido> consultarPedidosPorCliente(String idCliente) throws Exception {
        return consulta.verPedidosPorCliente(idCliente);
    }

    public ArrayList<Pedido> consultarPedidoPorID(String id) throws Exception {
        return consulta.verPedidoPorID(id);
    }

    public ArrayList<Pedido> consultarPedidosPorUsuario(String user) throws Exception {
        return consulta.verPedidosPorUsuario(user);
    }

    public ArrayList<Pedido> verTodosPedidos() throws SQLException, Exception {
        return consulta.verPedidosTodos();
    }

    public ArrayList<Pedido> consultarPedidoPorfecha(Date date) throws Exception {
        return consulta.verPedidosPorFecha(date);
    }

    public ArrayList<Cliente> consultarClientePorCedula(String cedula) throws Exception {
        return consulta.verClientePorCedula(cedula);
    }

    public ArrayList<Cliente> consultarTodosClientes() throws Exception {
        return consulta.verClientesTodos();
    }

    public ArrayList<Producto> consultarProductoPorID(String id) throws Exception {
        return consulta.verProductoPorID(id);
    }

    public ArrayList<Producto> consultarProductoPorCodBolsa(String cod) throws Exception {
        return consulta.verProductoPorCodBolsa(cod);
    }

    public ArrayList<Producto> buscarProductoPorNombre(String nombre) throws Exception {
        return consulta.verProductosPorNombre(nombre);
    }

    public ArrayList<Producto> consultarProductoPorPais(String pais) throws Exception {
        return consulta.verProductosPorPais(pais);
    }

    public ArrayList<Producto> consultarProductoPorDisponibilidad(String dis) throws Exception {
        return consulta.verProductosPorDisponibilidad(dis);
    }

    public ArrayList<Reserva> consultarReservaPorID(String id) throws Exception {
        return consulta.verReservaPorID(id);
    }

    public ArrayList<Reserva> consultarTodasReservas() throws Exception {
        return consulta.verTodasReservas();
    }

    public ArrayList<Reserva> consultarReservaPorCliente(String id) throws Exception {
        return consulta.verReservaPorCliente(id);
    }

    public ArrayList<Reserva> consultarReservaPorUsuario(String id) throws Exception {
        return consulta.verReservaPorUsuario(id);
    }

    public ArrayList<Reserva> consultarReservaPorFechaReser(Date date) throws Exception {
        return consulta.verReservaPorFechaReserva(date);
    }

    public ArrayList<Reserva> consultarReservaPorFechaRealiz(Date date) throws Exception {
        return consulta.verReservaPorFechaRealizada(date);
    }

    public ArrayList<RefCliente> consultarReferenciaPorCedula(String cedula) throws Exception {
        return consulta.verReferenciaPorCedula(cedula);
    }

    public ArrayList<Producto> verTodosProductos() throws SQLException, Exception {
        return consulta.verProductosTodos();
    }
    
    public ArrayList<Pedido> consultarPedidosPorPendienteYCliente(String idCliente) throws Exception {
        return consulta.verPedidosPorPendienteDePagoODeEntregaYCedula(idCliente);
    }
    public ArrayList<Pedido> consultarPedidosPorPendienteYIdPedido(String idCliente) throws Exception {
        
        return consulta.verPedidosPorPendienteDePagoODeEntregaYIdPedido(idCliente);
    }
    public ArrayList<Pedido> consultarPedidosPorPendienteYUsuario(String idCliente) throws Exception {
        return consulta.verPedidosPorPendienteDePagoODeEntregaYUsuario(idCliente);
    }
    
    //Inserciones

    public void registrarCliente(Cliente c, ArrayList<RefCliente> refs) throws SQLException, Exception {

        insercion.insertarCliente(c);



        for (Iterator<RefCliente> it = refs.iterator(); it.hasNext();) {
            Boolean existe = false;
            RefCliente refCliente = it.next();
            RefClienteCliente rfCC = new RefClienteCliente();
            rfCC.setIdCliente(c.getCedula());
            rfCC.setIdRef(refCliente.getCedula());

            for (Iterator<RefCliente> it2 = consulta.verReferenciaPorCedula(refCliente.getCedula()).iterator(); it2.hasNext();) {
                RefCliente refCliente2 = it2.next();
                System.out.println(refCliente2.getCedula() + "   " + refCliente.getCedula());

                if (refCliente2.getCedula().equals(refCliente.getCedula())) {
                    existe = true;
                }
            }
            System.out.println("hasta aqui bien");

            if (!existe) {
                insercion.insertarRefCliente(refCliente);
            }

            insercion.insertarRefClienteCliente(rfCC);
        }

    }

    public void actualizarCliente(Cliente c) throws SQLException {
        insercion.actualizarCliente(c);
    }

    public void registrarPedido(Pedido pedido, ArrayList<Producto> productos,Cliente client) throws SQLException, Exception {
        PedidoProducto pedProd = new PedidoProducto();
        pedido.setId(consulta.obtenerCodigoPedido()+"");
        if(pedido.getSaldo()==0){
            pedido.setPendiente_pago(0);
        }else {
            pedido.setPendiente_pago(1);
        }
        insercion.insertarPedido(pedido);
        insercion.actualizarCliente(client);
        pedProd.setIdPedido(pedido.getId());
        for (Iterator<Producto> it = productos.iterator(); it.hasNext();) {
            Producto producto = it.next();
            producto.setDisponibilidad("ALQUILADO");
            insercion.actualizarProducto(producto);
            pedProd.setIdProducto(producto.getCod_individual());
            insercion.insertarPedidoProducto(pedProd);
        }
    }

    public void actualizarPedido(Pedido p) throws SQLException {
        insercion.actualizarPedido(p);
    }

    public void registrarReserva(Reserva res, ArrayList<Producto> productos) throws SQLException, Exception {
        res.setId(consulta.obtenerCodigoReserva()+"");
        ReservaProducto resProd;
        insercion.insertarReserva(res);
        for (Iterator<Producto> it = productos.iterator(); it.hasNext();) {
            Producto producto = it.next();
            insercion.actualizarProducto(producto);
            resProd = new ReservaProducto();
            resProd.setIdReserva(res.getId());
            resProd.setIdProducto(producto.getCod_individual());
            insercion.insertarReservaProducto(resProd);
        }
    }

    public void actualizarReserva(Reserva res) throws SQLException {
        insercion.actualizarReserva(res);
    }

    public void actualizarRefCliente(RefCliente ref) throws SQLException {
        insercion.actualizarRefCliente(ref);
    }

    public void insertarProducto(Producto prod) throws SQLException {
        insercion.insertarProducto(prod);
        JOptionPane.showMessageDialog(null, "Producto registrado satisfactoriamente");
    }
    
    public void actualizarProducto(Producto prod) throws SQLException {
        insercion.actualizarProducto(prod);
        JOptionPane.showMessageDialog(null, "Producto Actualizado satisfactoriamente");
    }
    
    public void agregarReferenciaCliente(RefClienteCliente refcc) throws SQLException, Exception{
        insercion.insertarRefClienteCliente(refcc);
    }
    
    public void insertarReferencia(RefCliente rf) throws SQLException{
        insercion.insertarRefCliente(rf);
    }
    
    public void registrarUsuario(Usuario u) throws Exception{
        insercion.insertarUsuario(u);
    }
    
    public ArrayList<Usuario> consultarUsuarioPorUser(String user) throws Exception{
        return consulta.verUsuarioPorUser(user);
    }
    
    public void modificarReserva(Reserva reserva, ArrayList<Producto> productos) throws SQLException, Exception{
        Eliminaciones eliminacion = new Eliminaciones();
        ReservaProducto resProd;
        insercion.actualizarReserva(reserva);
        for (Iterator<Producto> it = consulta.verProductosPorReserva(reserva.getId()).iterator(); it.hasNext();) {
            Producto producto = it.next();
            producto.setDisponibilidad("DISPONIBLE");
            resProd = new ReservaProducto();
            resProd.setIdProducto(producto.getCod_individual());
            resProd.setIdReserva(reserva.getId());
            eliminacion.eliminarProductoReserva(resProd);
        }
        for (Iterator<Producto> it = productos.iterator(); it.hasNext();) {
            Producto producto = it.next();
            producto.setDisponibilidad("RESERVADO");
            insercion.actualizarProducto(producto);
            resProd = new ReservaProducto();
            resProd.setIdReserva(reserva.getId());
            resProd.setIdProducto(producto.getCod_individual());
            insercion.insertarReservaProducto(resProd);
        }
    }
    
    public void desactivarReserva(Reserva reserva) throws SQLException{
        reserva.setActivo(0);
        reserva.setEliminado(1);
        insercion.actualizarReserva(reserva);
    }
    
    public void desactivarPedido(Pedido pedido) throws SQLException, Exception{
        pedido.setPendiente_entrega(0);
        pedido.setPendiente_pago(0);
        pedido.setPagado(pedido.getTotal());
        pedido.setSaldo(0);
        insercion.actualizarPedido(pedido);
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos = consultarProductosPorPedido(pedido.getId());
        for (Iterator<Producto> it = productos.iterator(); it.hasNext();) {
            Producto producto = it.next();
            producto.setDisponibilidad("DISPONIBLE");
            insercion.actualizarProducto(producto);
        }
    }
}
