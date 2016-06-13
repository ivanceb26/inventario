/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson Salamanca
 */
public class ControlUsuario extends Control{

    private Consultas consulta;
    private Inserciones insercion;
    private Historial historial;

    public ControlUsuario() {
        consulta = new Consultas();
        insercion = new Inserciones();
        historial = new Historial();
    }

    @Override
    public void insertarProducto(Producto prod) throws SQLException{
        JOptionPane.showMessageDialog(null, "No tiene permisos para esto");
    }
    
    @Override
    public void registrarUsuario(Usuario u){
        JOptionPane.showMessageDialog(null, "No tiene permisos para esto");
    }
}
