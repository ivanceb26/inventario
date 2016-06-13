/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Wilson Salamanca
 */
public class FabricaUsuario {
    
    public static Control cargarUsuario(String tipo){
        if(tipo.equals("admin")){
            return new ControlUsuario();
        }
        if(tipo.equals("usuario")){
            return new ControlAdmin();
        }
        return null;
    }
}
