/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Wilson Salamanca
 */
public class PedidoProducto {
    private String idProducto;
    private String idPedido;
    private Date fechaVencimiento;
    private Date fechaRegreso;
    
    public PedidoProducto(){
        
    }

    public Date getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(Date fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }
    

}
