/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.security.MessageDigest;

/**
 *
 * @author Wilson Salamanca
 */
public class Usuario {

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    private String user;
    private String password;
    private String nombre;
    private String apellido;
    private String rol;
    private String cedula;
    private String telefono;
    private String direccion;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    private String foto;

    public Usuario(){
        
    }
    
   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    

    public String getUser() {
        return user;
    }

    public void setUser(String usser) {
        this.user = usser;
    }
    
    
    
}
