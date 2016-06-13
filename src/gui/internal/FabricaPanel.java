/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.internal;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Ivan C
 */
public class FabricaPanel {
    
    public static JPanel cargarInternalPanel(int numPanel) throws Exception {
        switch (numPanel) {
            case 1:
                return new InicioUI();
            case 2:
                return new ClientesUI();
            case 3:
        try {
            return new ProductosUI();
        } catch (Exception ex) {
            Logger.getLogger(FabricaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            case 4:
                return new PedidosUI();
            case 5:
                return new PedidosPendientesUI();
            case 6:
                return new ReservasUI();
            case 7:
                return new HistorialesUI();
            case 8:
                return new ContabilidadUI();
          /*  case 9:
                return new PedidosPendientesMultas();*/
            
        }
        return null;
    }

    
}
