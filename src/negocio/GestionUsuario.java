/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.ClienteDAO;
import datos.PedidoDAO;
import datos.PedidoProductoDAO;
import datos.ProductoDAO;
import datos.RefClienteDAO;
import datos.ReservaDAO;
import java.sql.SQLException;

/**
 *
 * @author Wilson Salamanca
 */
public class GestionUsuario {
    
    private ClienteDAO clienteDao;
    private Historial historial;
    private Cliente cliente;
      
    public GestionUsuario(){
        clienteDao = new ClienteDAO();
    }        
    
    public void crearCliente() throws SQLException{
        clienteDao.insertarCliente();
    }
    
    public void modificarCliente() throws SQLException{
        clienteDao.actualizarCliente();
    }
    
    public Cliente getCliente(){
        return this.clienteDao.getCliente();
    }
    
    
}
