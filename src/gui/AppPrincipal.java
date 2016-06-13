/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.internal.FabricaPanel;
import java.awt.Dimension;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import util.Opciones;

/**
 *
 * @author Ivan C
 */
public class AppPrincipal extends JPanel {

    private javax.swing.JButton ClientesButton;
    private javax.swing.JButton ContabilidadButton;
    private javax.swing.JButton HistorialesButton;
    private javax.swing.JButton InicioButton;
    private javax.swing.JButton PedidosButton;
    private javax.swing.JButton PedidosPendientesButton;
    private javax.swing.JButton ProductosButton;
    private javax.swing.JButton ReservasButton;
    private javax.swing.JButton MultasButton;
    private JPanel internalPanel;
    private int buttonsizewidth;

    public AppPrincipal() throws Exception {

        initComponents(1);
    }

    private void initComponents(int panelnum) throws Exception {
        
        Opciones.Opciones();
        int marginxb = 5;
        int marginyb = 80;
        this.buttonsizewidth = 80;
        int buttonsizeheight = 30;
        int spacebuttons = 10;


        InicioButton = new javax.swing.JButton();
        ProductosButton = new javax.swing.JButton();
        ClientesButton = new javax.swing.JButton();
        ReservasButton = new javax.swing.JButton();
        PedidosButton = new javax.swing.JButton();
        PedidosPendientesButton = new javax.swing.JButton();
        HistorialesButton = new javax.swing.JButton();
        ContabilidadButton = new javax.swing.JButton();
        MultasButton = new javax.swing.JButton();


        internalPanel = FabricaPanel.cargarInternalPanel(panelnum);


        InicioButton.setText("Inicio");
        ProductosButton.setText("Productos");
        ClientesButton.setText("Clientes");
        ReservasButton.setText("Reservas");
        PedidosButton.setText("Pedidos");
        PedidosPendientesButton.setText("Pedidos Pendientes");
        HistorialesButton.setText("Historiales");
        ContabilidadButton.setText("Contabilidad");
        MultasButton.setText("Multas");


        InicioButton.setMargin(new Insets(0, 0, 0, 0));
        ProductosButton.setMargin(new Insets(0, 0, 0, 0));
        ClientesButton.setMargin(new Insets(0, 0, 0, 0));
        ReservasButton.setMargin(new Insets(0, 0, 0, 0));
        PedidosButton.setMargin(new Insets(0, 0, 0, 0));
        PedidosPendientesButton.setMargin(new Insets(0, 0, 0, 0));
        HistorialesButton.setMargin(new Insets(0, 0, 0, 0));
        ContabilidadButton.setMargin(new Insets(0, 0, 0, 0));
        MultasButton.setMargin(new Insets(0, 0, 0, 0));








        /* internalPanel.setMaximumSize(new java.awt.Dimension(980, 720));
        internalPanel.setMinimumSize(new java.awt.Dimension(980, 720));
        internalPanel.setPreferredSize(new java.awt.Dimension(980, 720));*/


        this.setLayout(null);


        this.add(ClientesButton);
        this.add(ContabilidadButton);
        this.add(InicioButton);
        this.add(HistorialesButton);
        this.add(ReservasButton);
        this.add(PedidosPendientesButton);
        this.add(PedidosButton);
        this.add(ProductosButton);
        this.add(MultasButton);


        this.add(internalPanel);

        //EventsListener
        InicioButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    InicioButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ClientesButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    ClientesButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ProductosButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    ProductosButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        PedidosButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    PedidosButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        PedidosPendientesButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    PedidosPendientesButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ReservasButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    ReservasButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        HistorialesButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    HistorialesButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        ContabilidadButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    ContabilidadButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        MultasButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    MultasButtonActionPerformed(evt);
                } catch (Exception ex) {
                    Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });



        //buttons layout 
        Insets insets = this.getInsets();
        Dimension size = ClientesButton.getPreferredSize();



        InicioButton.setBounds(marginxb + insets.left, marginyb + insets.top + size.height + spacebuttons,
                buttonsizewidth, buttonsizeheight);


        ClientesButton.setBounds(marginxb + insets.left, marginyb + insets.top + (spacebuttons + size.height) * 2,
                buttonsizewidth, buttonsizeheight);


        ProductosButton.setBounds(marginxb + insets.left, marginyb + insets.top + (spacebuttons + size.height) * 3,
                buttonsizewidth, buttonsizeheight);


        PedidosButton.setBounds(marginxb + insets.left, marginyb + insets.top + (spacebuttons + size.height) * 4,
                buttonsizewidth, buttonsizeheight);

        PedidosPendientesButton.setBounds(marginxb + insets.left, marginyb + insets.top + (spacebuttons + size.height) * 5,
                buttonsizewidth, buttonsizeheight);


        ReservasButton.setBounds(marginxb + insets.left, marginyb + insets.top + (spacebuttons + size.height) * 6,
                buttonsizewidth, buttonsizeheight);

        HistorialesButton.setBounds(marginxb + insets.left, marginyb + insets.top + (spacebuttons + size.height) * 7,
                buttonsizewidth, buttonsizeheight);


        ContabilidadButton.setBounds(marginxb + insets.left, marginyb + insets.top + (spacebuttons + size.height) * 8,
                buttonsizewidth, buttonsizeheight);

        MultasButton.setBounds(marginxb + insets.left, marginyb + insets.top + (spacebuttons + size.height) * 9,
                buttonsizewidth, buttonsizeheight);



        //InternalJPanel Layout

        internalPanel.setBounds(buttonsizewidth + insets.left + 5, 10 + insets.top, 980, 720);
        MultasButton.setVisible(false);

    }

    //Eventos Code
    private void InicioButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(1);
    }

    private void ClientesButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(2);
        /* Insets insets = this.getInsets();
        this.remove(internalPanel);
        JPanel clientespan=new ClientesUI();
        clientespan.setMaximumSize(new java.awt.Dimension(860, 600));
        clientespan.setMinimumSize(new java.awt.Dimension(400, 300));
        clientespan.setPreferredSize(new java.awt.Dimension(400, 400));
        
        this.add(clientespan);
        
        
        clientespan.setBounds(buttonsizewidth + insets.left + 5, 10 + insets.top, 800, 600);
        this.updateUI();*/

    }

    private void ProductosButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(3);
    }

    private void PedidosButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(4);
    }

    private void PedidosPendientesButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(5);
    }

    private void ReservasButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(6);
    }

    private void HistorialesButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(7);
    }

    private void ContabilidadButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(8);
    }

    private void MultasButtonActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
        cargarPanel(9);
    }

    public void cargarPanel(int num) {
        Insets insets = this.getInsets();
        /*internalPanel.setMaximumSize(new java.awt.Dimension(0, 0));
        internalPanel.setMinimumSize(new java.awt.Dimension(0, 0));
        internalPanel.setPreferredSize(new java.awt.Dimension(0, 0));
        internalPanel.setBounds(buttonsizewidth + insets.left + 4, 9 + insets.top, 800, 600);
        this.remove(internalPanel);
        
        internalPanelUpdate = FabricaPanel.cargarInternalPanel(num);
        
        internalPanelUpdate.setMaximumSize(new java.awt.Dimension(860, 600));
        internalPanelUpdate.setMinimumSize(new java.awt.Dimension(400, 300));
        internalPanelUpdate.setPreferredSize(new java.awt.Dimension(400, 400));
        
        this.add(internalPanelUpdate);
        
        
        internalPanelUpdate.setBounds(buttonsizewidth + insets.left + 5, 10 + insets.top, 800, 600);
        
        this.updateUI();
         */

        this.remove(internalPanel);
        this.validate();
        try {
            internalPanel = FabricaPanel.cargarInternalPanel(num);
        } catch (Exception ex) {
            Logger.getLogger(AppPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("interpanel no carga en appPrincipal");
        }


        /*
        internalPanel.setMaximumSize(new java.awt.Dimension(960, 720));
        internalPanel.setMinimumSize(new java.awt.Dimension(960, 720));
        internalPanel.setPreferredSize(new java.awt.Dimension(960, 720));
         */


        this.add(internalPanel);


        internalPanel.setBounds(buttonsizewidth + 5, 10 + insets.top, 960, 720);

        this.updateUI();

    }
}
