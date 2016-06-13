/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Wilson Salamanca
 */
public class Producto {

    private String cod_individual;
    private String cod_bolsa;
    private String nombre;
    private String color;
    private String textura;
    private String caract;
    private String estado;
    private String talla;
    private String sexo;
    private String pais;
    private String imagen;
    private String disponibilidad;
    private int precio;

    public Producto() {
    }

    public String getCaract() {
        return caract;
    }

    public void setCaract(String caract) {
        this.caract = caract;
    }

    public String getCod_bolsa() {
        return cod_bolsa;
    }

    public void setCod_bolsa(String cod_bolsa) {
        this.cod_bolsa = cod_bolsa;
    }

    public String getCod_individual() {
        return cod_individual;
    }

    public void setCod_individual(String cod_individual) {
        this.cod_individual = cod_individual;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getTextura() {
        return textura;
    }

    public void setTextura(String textura) {
        this.textura = textura;
    }
}