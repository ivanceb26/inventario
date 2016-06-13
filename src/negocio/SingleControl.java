/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Wilson Salamanca
 */
public class SingleControl {
    
    private static Control control;

    public SingleControl(){
        
    }
    
    public static Control sesion(){
        return control;
    }
    
    public Control getSesion(String rol){
        if(rol.equals("Admin")){
            control = new ControlAdmin();
            return control;
        }else if(rol.equals("Usuario")){
            control = new ControlUsuario();
            return control;
        }
        return this.control;
    }
}
