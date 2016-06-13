/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InicioUI.java
 *
 * Created on 09-mar-2013, 15:38:04
 */
package gui.internal;

import gui.dialog.ClientNewD;
import gui.dialog.PedNewD;
import gui.dialog.ReserNewD;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Ivan C
 */
public class InicioUI extends javax.swing.JPanel {

    String logopath;

    /** Creates new form InicioUI */
    public InicioUI() {
        logopath = "images/Logodanzas.gif";
        initComponents();





        // Se mete la imagen en el label 

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NewPedidoBut = new javax.swing.JButton();
        NewReservaBut = new javax.swing.JButton();
        newclientbut = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(871, 680));
        setMinimumSize(new java.awt.Dimension(871, 680));

        NewPedidoBut.setText("Nuevo Pedido");
        NewPedidoBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPedidoButActionPerformed(evt);
            }
        });

        NewReservaBut.setText("Nueva Reserva");
        NewReservaBut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewReservaButActionPerformed(evt);
            }
        });

        newclientbut.setText("Nuevo Cliente");
        newclientbut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newclientbutActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36));
        jLabel1.setText("HUNÁP-KU");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36));
        jLabel2.setText("ALQUILER");

        logo.setMaximumSize(new java.awt.Dimension(20, 20));
        logo.setMinimumSize(new java.awt.Dimension(20, 20));
        Image img = Toolkit.getDefaultToolkit().getImage(logopath);
        Image newimg = img.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH);
        Icon newIcon = new ImageIcon(newimg);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(newclientbut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(NewReservaBut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addComponent(NewPedidoBut, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                            .addGap(540, 540, 540)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(94, 94, 94)
                        .addComponent(NewPedidoBut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NewReservaBut, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newclientbut, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(343, Short.MAX_VALUE))
        );

        logo.setIcon(newIcon);
    }// </editor-fold>//GEN-END:initComponents

private void NewPedidoButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPedidoButActionPerformed
// TODO add your handling code here:
    PedNewD newPed = new PedNewD();
    newPed.setSize(900, 560);
    newPed.setLocationRelativeTo(null);
    newPed.setVisible(true);
    newPed.pack();
    
  //  newPed.pack();
}//GEN-LAST:event_NewPedidoButActionPerformed

private void NewReservaButActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewReservaButActionPerformed
// TODO add your handling code here:
    ReserNewD wres = new ReserNewD();
    wres.setSize(740,520);
     wres.setLocationRelativeTo(null);
    wres.setVisible(true);
   
  //  wres.pack();
}//GEN-LAST:event_NewReservaButActionPerformed

private void newclientbutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newclientbutActionPerformed
// TODO add your handling code here:
    ClientNewD clientedialog = new ClientNewD();
    clientedialog.setVisible(true);
   // clientedialog.pack();
}//GEN-LAST:event_newclientbutActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton NewPedidoBut;
    private javax.swing.JButton NewReservaBut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel logo;
    private javax.swing.JButton newclientbut;
    // End of variables declaration//GEN-END:variables
}
