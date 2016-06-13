/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Wilson Salamanca
 */
public class Cliente {
    private String cedula;
    private String Nombre;
    
    private String Apellido;
    
    private String direccion;
    private String telefono;
    private String foto;
    private int puntos;
    
    public Cliente(){
        
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getApellido() {
        return Apellido;
    }



    public String getNombre() {
        return Nombre;
    }

   

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    

    public void setApellido(String sApellido) {
        this.Apellido = sApellido;
    }

   

    public void setNombre(String sNombre) {
        this.Nombre = sNombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
