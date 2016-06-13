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
public class Pedido {
    private String id;
    private Date emision;
    private double multa;
    private int numProductos;
    private double total;
    private String idCliente;
    private double pagado;
    private double saldo;
    private String usuario;
    private double descuento;
    private Date fechaentrega;
    private Date fechadevolucion;
    private int pendiente_pago;
    private int pendiente_entrega;
    private double subtotal;

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getPendiente_entrega() {
        return pendiente_entrega;
    }

    public void setPendiente_entrega(int pendiente_entrega) {
        this.pendiente_entrega = pendiente_entrega;
    }

    public int getPendiente_pago() {
        return pendiente_pago;
    }

    public void setPendiente_pago(int pendiente_pago) {
        this.pendiente_pago = pendiente_pago;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }

    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public Date getFechamod() {
        return fechamod;
    }

    public void setFechamod(Date fechamod) {
        this.fechamod = fechamod;
    }
    private Date fechamod;
    
    
    public Pedido(){
        
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Date getEmision() {
        return emision;
    }

    public void setEmision(Date emision) {
        this.emision = emision;
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

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public int getNumProductos() {
        return numProductos;
    }

    public void setNumProductos(int numProductos) {
        this.numProductos = numProductos;
    }

    public double getPagado() {
        return pagado;
    }

    public void setPagado(double pagado) {
        this.pagado = pagado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
