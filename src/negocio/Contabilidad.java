/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.Date;

/**
 *
 * @author Ivan C
 */



public class Contabilidad {
    private Date fecha;
    private Date fechafin;
    private int num_pedidos;
    private double subtotal;
    private double multas;
    private double total;
    private double pendiente;
    private int num_productos;
    private double descuentos;

    public double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }
    private String idop;

    public String getIdop() {
        return idop;
    }

    public void setIdop(String idop) {
        this.idop = idop;
    }
    
    public Contabilidad(){
    
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public int getNum_pedidos() {
        return num_pedidos;
    }

    public void setNum_pedidos(int num_pedidos) {
        this.num_pedidos = num_pedidos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getMultas() {
        return multas;
    }

    public void setMultas(double multas) {
        this.multas = multas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPendiente() {
        return pendiente;
    }

    public void setPendiente(double pendiente) {
        this.pendiente = pendiente;
    }

    public int getNum_productos() {
        return num_productos;
    }

    public void setNum_productos(int num_productos) {
        this.num_productos = num_productos;
    }
    
    
    
}
