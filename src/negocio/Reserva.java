/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Date;


/**
 *
 * @author Wilson Salamanca
 */
public class Reserva {
    private String id;
    private String idCliente;
    private Date fechaRealizada;
    private Date fechaReservaini;
    private Date fechaReservafin;
    private String usuario;
    private double abono;
    private double total;
    private int eliminado;
    private int activo;
    private double pendiente;
    private String pedido;
    private Date fechamod;
    private Date diasReserva;
    
    
      public Reserva(){
        
    }

    public Date getDiasReserva() {
        return diasReserva;
    }

    public void setDiasReserva(Date diasReserva) {
        this.diasReserva = diasReserva;
    }
    

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getEliminado() {
        return eliminado;
    }

    public void setEliminado(int eliminado) {
        this.eliminado = eliminado;
    }

    public Date getFechamod() {
        return fechamod;
    }

    public void setFechamod(Date fechamod) {
        this.fechamod = fechamod;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String idpedido) {
        this.pedido = idpedido;
    }

    public double getPendiente() {
        return pendiente;
    }

    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }
    
    
  
    public Date getFechaReservafin() {
        return fechaReservafin;
    }

    public void setFechaReservafin(Date fechaReservafin) {
        this.fechaReservafin = fechaReservafin;
    }
    
    public Date getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(Date fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public Date getFechaReservaIni() {
        return fechaReservaini;
    }

    public void setFechaReservaini(Date fechaReserva) {
        this.fechaReservaini = fechaReserva;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
