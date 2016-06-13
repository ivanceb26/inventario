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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Wilson Salamanca
 */
public class Inserciones {

    private ClienteDAO clienteDao;
    private PedidoDAO pedidoDao;
    private PedidoProductoDAO pedidoProductoDao;
    private ProductoDAO productoDao;
    private RefClienteDAO refClienteDao;
    private ReservaDAO reservaDao;
    private ReservaProductoDAO reservaProductoDao;
    private UsuarioDAO usuarioDao;
    private RefClienteClienteDAO refClienteCliente;

    public Inserciones() {
        clienteDao = new ClienteDAO();
        pedidoDao = new PedidoDAO();
        pedidoProductoDao = new PedidoProductoDAO();
        productoDao = new ProductoDAO();
        refClienteDao = new RefClienteDAO();
        reservaDao = new ReservaDAO();
        reservaProductoDao = new ReservaProductoDAO();
        usuarioDao = new UsuarioDAO();
        refClienteCliente = new RefClienteClienteDAO();
    }

    public void insertarCliente(Cliente c) throws SQLException, Exception {
        clienteDao.setCliente(c);
        clienteDao.insertarCliente();
    }

    public void actualizarCliente(Cliente c) throws SQLException {
        clienteDao.setCliente(c);
        clienteDao.actualizarCliente();
    }

    public void insertarPedido(Pedido p) throws SQLException, Exception {
        pedidoDao.setPedido(p);
        
        
        
        
        //System.out.println(p.getId()+ "id pedido");
        pedidoDao.insertarPedido();

    }

    public void actualizarPedido(Pedido p) throws SQLException {
        pedidoDao.setPedido(p);
        pedidoDao.actualizarPedido();
    }

    public void insertarPedidoProducto(PedidoProducto p) throws SQLException {
        pedidoProductoDao.setPedProducto(p);
        pedidoProductoDao.insertarPedidoProducto();
    }

    public void actualizarPedidoProducto(PedidoProducto p) throws SQLException {
        pedidoProductoDao.setPedProducto(p);
        pedidoProductoDao.actualizarPedidoProducto();
    }

    public void insertarProducto(Producto p) throws SQLException {
        p.setDisponibilidad("DISPONIBLE");
        productoDao.setProducto(p);
        productoDao.insertarProducto();
    }

    public void actualizarProducto(Producto p) throws SQLException {
        productoDao.setProducto(p);
        productoDao.actualizarProducto();
    }

    public void insertarReserva(Reserva r) throws SQLException {
        reservaDao.setReserva(r);
        reservaDao.insertarReserva();
    }

    public void actualizarReserva(Reserva r) throws SQLException {
        reservaDao.setReserva(r);
        reservaDao.actualizarReserva();
    }

    public void insertarReservaProducto(ReservaProducto r) throws SQLException {
        reservaProductoDao.setResProducto(r);
        reservaProductoDao.insertarReservaProducto();
    }

    public void insertarUsuario(Usuario u) throws Exception {
        usuarioDao.setUsuario(u);
        usuarioDao.insertarUsuario();
    }

    public void actualizarUsuario(Usuario u) throws SQLException {
        usuarioDao.setUsuario(u);
        usuarioDao.actualizarUsuario();
    }

    public void insertarRefCliente(RefCliente ref) throws SQLException {
        refClienteDao.setRefcliente(ref);
        refClienteDao.insertarRefCliente();
    }

    public void actualizarRefCliente(RefCliente ref) throws SQLException {
        refClienteDao.setRefcliente(ref);
        refClienteDao.actualizarRefCliente();
    }

    public void insertarRefClienteCliente(RefClienteCliente refC) throws SQLException {
        refClienteCliente.setRefClienteCliente(refC);
        refClienteCliente.insertarRefClienteCliente();
    }
}
