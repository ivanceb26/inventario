/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import de.javasoft.plaf.synthetica.*;
import gui.InicioSesion;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Ivan C
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Synthetica
        try {
            //UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaBlueIceLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaMauveMetallicLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaSilverMoonLookAndFeel());
            // ERROR //UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
            
            UIManager.put("Synthetica.window.decoration", Boolean.FALSE);
            UIManager.setLookAndFeel(new SyntheticaSkyMetallicLookAndFeel());
           // UIManager.put("Synthetica.menuBar.background.horizontalTiled", Boolean.TRUE);
           // UIManager.put("Synthetica.menuBar.background.horizontalTiled", Boolean.TRUE);
           // UIManager.put("Synthetica.menuBar.background.horizontalTiled", Boolean.TRUE);
            
            //UIManager.setLookAndFeel(new SyntheticaWhiteVisionLookAndFeel());
            

 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }




        //AppFrame w = new AppFrame();
        InicioSesion w;
        try {
//            w = new App();
//            w.setSize(1024, 720);
//            w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            w.setLocationRelativeTo(null);
//            w.setVisible(true);
            w = new InicioSesion();
//            w.setSize(1024, 720);
            //w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
            w.setVisible(true);


            // w.setExtendedState(6);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }



//********************************************************************   

//Substance 6.1
          /*  
         JFrame.setDefaultLookAndFeelDecorated(true);
         SwingUtilities.invokeLater(new Runnable() {
         public void run() {
        
         JFrame.setDefaultLookAndFeelDecorated(true);    
         try {
        
         UIManager.setLookAndFeel(new SubstanceAutumnLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceBusinessBlueSteelLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceChallengerDeepLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceCremeCoffeeLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceDustCoffeeLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceEmeraldDuskLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceGeminiLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceGraphiteAquaLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceGraphiteGlassLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceMagellanLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceMarinerLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceMistSilverLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceModerateLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceNebulaBrickWallLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceNebulaLookAndFeel());      
         //UIManager.setLookAndFeel(new SubstanceOfficeBlack2007LookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceSaharaLookAndFeel());
         //UIManager.setLookAndFeel(new SubstanceTwilightLookAndFeel());
        
        
        
        
         } catch (Exception e) {
         System.out.println("Substance Raven Graphite failed to initialize");
         }
        
         NewJFrame w = new NewJFrame();
         w.setVisible(true);
         }
         });
        
         */
    }
}
