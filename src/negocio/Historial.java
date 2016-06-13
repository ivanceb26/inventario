/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.PedidoDAO;
import datos.ReservaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Wilson Salamanca
 */
public class Historial {
 
    private PedidoDAO pedidoDao;
    private ReservaDAO reservaDao;
    
    private ArrayList<Pedido> pedidos;
    
    public void Historial(){
        pedidoDao = new PedidoDAO();
        reservaDao = new ReservaDAO();
    }
    
    public ArrayList<Pedido> verTodosPedidos() throws SQLException, Exception{
        return pedidoDao.getPedidos();
    }
    
    public ArrayList<Pedido> verPedidoCliente(String idCliente) throws Exception{
        return pedidoDao.buscarPedidoPorIdCliente(idCliente);
    }
    
    public ArrayList<Reserva> verTodasReservas() throws Exception{
        return reservaDao.getReservas();
    }
    
    public ArrayList<Reserva> verReservaCliente(String idCliente) throws Exception{
        return reservaDao.buscarReservaPorIdCliente(idCliente);
    }
    
    public ArrayList<Pedido> verPedidoUsuario(String user) throws Exception{
        return pedidoDao.buscarPedidoPorUsuario(user);
    }
    
    public ArrayList<Reserva> verReservaUsuario(String user) throws Exception{
        return reservaDao.buscarReservaPorUsuario(user);
    }
}
