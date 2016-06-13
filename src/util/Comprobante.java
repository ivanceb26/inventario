/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import negocio.Cliente;
import negocio.Pedido;
import negocio.Producto;
import negocio.Reserva;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Wilson Salamanca
 */
public class Comprobante implements JRDataSource {

    private ArrayList<Producto> productos;
    private Cliente cliente;
    private Reserva reserva;
    private int indiceActual;
    private int tiempoMul;
    private int porcentajeMul;
    private String imagePath;

    public Comprobante() {
        productos = new ArrayList<Producto>();
        indiceActual = -1;
        imagePath = System.getProperties().getProperty("user.dir") + "\\Facturas\\Logodanzas.gif";
        //System.out.println(imagePath+"       image path");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Reserva getPedido() {
        return reserva;
    }

    public void setPedido(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getIndiceActual() {
        return indiceActual;
    }

    public void setIndiceActual(int indiceActual) {
        this.indiceActual = indiceActual;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public int getProcentajeMult() {
        return porcentajeMul;
    }

    public void setProcentajeMult(int procentajeMulta) {
        this.porcentajeMul = procentajeMulta;
    }

    public int getTiempoMult() {
        return tiempoMul;
    }

    public void setTiempoMult(int tiempoMulta) {
        this.tiempoMul = tiempoMulta;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceActual < productos.size();
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        Object valor = null;
        if ("codigo".equals(jrf.getName())) {
            valor = productos.get(indiceActual).getCod_individual();
        } else if ("articulo".equals(jrf.getName())) {
            valor = productos.get(indiceActual).getNombre();
        } else if ("valor".equals(jrf.getName())) {
            valor = productos.get(indiceActual).getPrecio();
        } else if ("cliente".equals(jrf.getName())) {
            valor = "" + cliente.getNombre() + " " + cliente.getApellido();
        } else if ("direccion".equals(jrf.getName())) {
            valor = "" + cliente.getDireccion();
        } else if ("id".equals(jrf.getName())) {
            valor = "" + cliente.getCedula();
        } else if ("tel".equals(jrf.getName())) {
            valor = "" + cliente.getTelefono();
        } else if ("numFactura".equals(jrf.getName())) {
            valor = "" + reserva.getId();
        } else if ("tiempo".equals(jrf.getName())) {
            valor = "" + Opciones.DIAS_MULTA;
        } else if ("porcentaje".equals(jrf.getName())) {
            valor = "" + Opciones.PORCENTAJE_MULTA;
        } else if ("total".equals(jrf.getName())) {
            valor = "" + reserva.getTotal();
        } else if ("abono".equals(jrf.getName())) {
            valor = "" + reserva.getAbono();
        } else if ("saldo".equals(jrf.getName())) {
            valor = "" + (reserva.getTotal() - reserva.getAbono());
        } else if ("apppath".equals(jrf.getName())) {
            valor = "" + this.imagePath;
        } else if ("fReserva".equals(jrf.getName())) {
            valor = "" + reserva.getFechaReservaIni();
        }

        return valor;
    }
}
