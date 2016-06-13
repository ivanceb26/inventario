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

/**
 *
 * @author Wilson Salamanca
 */
public class Eliminaciones {
    private UsuarioDAO usuarioDao;
    private ClienteDAO clienteDao;
    private PedidoProductoDAO pedProductoDao;
    private ProductoDAO productoDao;
    private RefClienteDAO refClienteDao;
    private PedidoDAO pedidoDao;
    private ReservaDAO reservaDao;
    private ReservaProductoDAO resProductoDao;
    private RefClienteClienteDAO refClienteClienteDAO;
    
    
    public Eliminaciones(){
        this.usuarioDao = new UsuarioDAO();
        this.clienteDao = new ClienteDAO();
        this.pedProductoDao = new PedidoProductoDAO();
        this.productoDao = new ProductoDAO();
        this.refClienteDao = new RefClienteDAO();
        this.reservaDao = new ReservaDAO();
        this.resProductoDao = new ReservaProductoDAO();
        this.pedidoDao = new PedidoDAO();
        refClienteClienteDAO = new RefClienteClienteDAO();
    }
    
    public void eliminarUsuario(Usuario us) throws SQLException{
        usuarioDao.setUsuario(us);
        usuarioDao.eliminarUsuario();
    }
    
    public void eliminarRefClienteCliente(RefClienteCliente refcc) throws SQLException{
        refClienteClienteDAO.setRefClienteCliente(refcc);
        refClienteClienteDAO.eliminarRefCliente();
    }
    
    public void eliminarProducto(Producto prod) throws SQLException{
        productoDao.setProducto(prod);
        productoDao.eliminarProducto();
    }
    
    public void eliminarProductoReserva(ReservaProducto resProd) throws SQLException{
        resProductoDao.setResProducto(resProd);
        resProductoDao.eliminarReservaProducto();
    }
    
}
