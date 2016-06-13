/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TomarFotico.java
 *
 * Created on 21-mar-2013, 16:33:03
 */
package gui.dialog;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import util.camara.CamaraWeb;

/**
 *
 * @author Ivan C
 */
public class TomarFotico extends javax.swing.JDialog {

    /**
     * Creates new form TomarFotico
     */
    private JLabel fotolabel;
    private CamaraWeb camara;

    public TomarFotico() {
        initComponents();
        /* camara = new CamaraWeb();
         this.add(camara.VerCamara(10, 10, 320, 240));*/


    }

    public TomarFotico(JLabel lab) {
        initComponents();
        camara = new CamaraWeb();
        this.add(camara.VerCamara(30, 15, 240, 180));
        this.setLocationRelativeTo(null);
        fotolabel = lab;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tomarfotoBut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        tomarfotoBut.setText("Tomar Foto");
        tomarfotoBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomarfotoButActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(tomarfotoBut)
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(tomarfotoBut)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void tomarfotoButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomarfotoButActionPerformed
    fotolabel.setText(camara.Fotografiar("images", true, "foto"));
    Image img2 = Toolkit.getDefaultToolkit().getImage(fotolabel.getText());
    Image newimg2 = img2.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
    Icon newIcon = new ImageIcon(newimg2);
// Code adding the component to the parent container - not shown here
    
    fotolabel.setIcon(newIcon);
// TODO add your handling code here:
    this.dispose();

}//GEN-LAST:event_tomarfotoButActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TomarFotico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TomarFotico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TomarFotico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TomarFotico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TomarFotico().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton tomarfotoBut;
    // End of variables declaration//GEN-END:variables
}