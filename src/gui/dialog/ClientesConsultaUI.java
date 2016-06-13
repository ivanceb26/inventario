/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Inicio.java
 *
 * Created on 09-mar-2013, 15:38:04
 */
package gui.dialog;

import gui.internal.*;
import java.awt.Dialog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import negocio.Cliente;
import negocio.Consultas;
import util.GestorTabla;

/**
 *
 * @author Ivan C
 */
public class ClientesConsultaUI extends javax.swing.JPanel {

    /**
     * Creates new form Inicio
     */
    private Consultas consulta;
    private DefaultTableModel modelo;
    private JTable tabla;
    private GestorTabla gTabla;
    private ArrayList<Cliente> arraylocal;
    private ArrayList<String> cols;
    private ArrayList<String> row;
    private ArrayList<ArrayList> rows;
    private int anchocolumna[];
    private String nombrecolumna[];
    private int widthtable;
    Cliente cliente;
    JScrollPane pane;
    private JDialog auxiliar;
    private JLabel label;
    private JButton bu1, bu2;
    private PedNewD auxPed;
    private ReserNewD auxRes;

    public ClientesConsultaUI(int ind, String cotxt) throws Exception {
        widthtable = 3;
        anchocolumna = new int[7];
        nombrecolumna = new String[7];

        anchocolumna[0] = 120;
        anchocolumna[1] = 130;
        anchocolumna[2] = 130;
        anchocolumna[3] = 120;
        anchocolumna[4] = 120;
        anchocolumna[5] = 60;
        anchocolumna[6] = 60;

        for (int i = 0; i < anchocolumna.length; i++) {
            widthtable = widthtable + anchocolumna[i];
        }


        nombrecolumna[0] = "cedula";
        nombrecolumna[1] = "Nombres";
        nombrecolumna[2] = "Apellidos";
        nombrecolumna[3] = "Direccion";
        nombrecolumna[4] = "Telefono";
        nombrecolumna[5] = "Fotos";
        nombrecolumna[6] = "Puntos";





        consulta = new Consultas();
        search(ind, cotxt);
        initComponents();


//        dibujarTabla();
        seleccionar.setVisible(false);
        jButtonModificar.setVisible(false);

    }

    public ClientesConsultaUI(int ind, String cotxt, JDialog a, JLabel l, PedNewD ped, ReserNewD re) throws Exception {
        this(ind, cotxt);
        refBoton.setVisible(false);
        historialBoton.setVisible(false);
        jButtonModificar.setVisible(false);
        seleccionar.setVisible(true);
        auxiliar = a;
        label = l;
        auxPed = ped;
        auxRes = re;
        auxiliar.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

    }

    public ClientesConsultaUI(boolean mod) throws Exception {

        this(1, "");
        if (mod) {

            refBoton.setVisible(false);
            historialBoton.setVisible(false);
            jButtonModificar.setVisible(false);
            seleccionar.setVisible(false);
            jButtonModificar.setVisible(true);
        }

    }

    public void verTabla(ArrayList cons) {

        pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        /*try {
         pane.remove(tabla);
         this.remove(pane);
         } catch (Exception e) {
         System.out.print("no elimina tabla");
         }*/
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };//Evito la edición de celdas
        cols = new ArrayList<String>();
        row = new ArrayList<String>();
        rows = new ArrayList<ArrayList>();
        gTabla = new GestorTabla(modelo);
        tabla = new JTable(modelo);

        for (int i = 0; i < nombrecolumna.length; i++) {
            cols.add(nombrecolumna[i]);
        }

        gTabla.cuadrarColumnas(cols);

        for (Iterator<Cliente> it = cons.iterator(); it.hasNext();) {
            Cliente clien = it.next();
            row = new ArrayList<String>();
            row.add(clien.getCedula());
            row.add(clien.getNombre());
            row.add(clien.getApellido());
            row.add(clien.getDireccion());
            row.add(clien.getTelefono());
            row.add(clien.getFoto());
            row.add(clien.getPuntos() + "");
            rows.add(row);
        }

        gTabla.agregarFilas(rows);

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//permite seleccionar solo una fila
        tabla.getTableHeader().setReorderingAllowed(false); //Evito el cambio de columnas
        tabla.getTableHeader().setResizingAllowed(true); //Evito la redimensión de las columnas por el usuario
        tabla.setBorder(BorderFactory.createEmptyBorder());
        //lo que tenía en el método anterior
        tabla.setRowHeight(30);

        // tabla.setMaximumSize(new Dimension(920, 400));
        tabla.setRowMargin(3);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        for (int i = 0; i < anchocolumna.length; i++) {

            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchocolumna[i]);
        }

        if (widthtable < 980) {
            pane.setSize(widthtable, 420);
            pane.setViewportView(tabla);

            pane.setBounds(35, 120, widthtable, 400);
        } else {
            pane.setSize(980, 420);
            pane.setViewportView(tabla);

            pane.setBounds(35, 120, 880, 400);
        }
        //jScrollPane1 = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        refBoton = new javax.swing.JButton();
        historialBoton = new javax.swing.JButton();
        seleccionar = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(871, 710));
        setMinimumSize(new java.awt.Dimension(871, 710));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cedula", "Nombres", "Apellidos" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Buscar Por:");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setText("Clientes");

        refBoton.setText("Referencias");
        refBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refBotonActionPerformed(evt);
            }
        });

        historialBoton.setText("Historial");
        historialBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialBotonActionPerformed(evt);
            }
        });

        seleccionar.setText("Seleccionar");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(363, 363, 363))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(refBoton)
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(historialBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(seleccionar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 450, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refBoton)
                    .addComponent(historialBoton)
                    .addComponent(seleccionar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonModificar)
                .addGap(106, 106, 106))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);
    search(jComboBox1.getSelectedIndex(), textff);





// TODO add your handling code here:
}//GEN-LAST:event_jComboBox1ActionPerformed

private void refBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refBotonActionPerformed
    try {
        if (tabla.getSelectedRowCount() >= 1) {
            System.out.println(tabla.getSelectedRow());
            System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0));

            ClientesConsultaReferD wRef = new ClientesConsultaReferD(tabla.getValueAt(tabla.getSelectedRow(), 0) + "");
            wRef.setVisible(true);
            wRef.pack();
        } else {
            JOptionPane.showMessageDialog(this, "Ningún elemento seleccionado");
        }

    } catch (Exception e) {
        System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0) + "ERROR");
    }
    // TODO add your handling code here:
}//GEN-LAST:event_refBotonActionPerformed

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);
    search(jComboBox1.getSelectedIndex(), textff);
}//GEN-LAST:event_jTextField1KeyReleased

private void seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarActionPerformed
// TODO add your handling code here:
    try {
        cliente = consulta.verClientePorCedula("" + tabla.getValueAt(tabla.getSelectedRow(), 0)).get(0);
        if (auxPed != null) {
            auxPed.setCliente(cliente);
        } else if (auxRes != null) {
            auxRes.setCliente(cliente);
        }
        auxiliar.dispose();
        label.setText(cliente.getNombre() + " " + cliente.getApellido());
        bu1.setVisible(false);
        bu2.hide();

    } catch (Exception e) {
    }
}//GEN-LAST:event_seleccionarActionPerformed

    private void historialBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialBotonActionPerformed
        try {
            if (tabla.getSelectedRowCount() >= 1) {
                //    System.out.println(tabla.getSelectedRow());
                //    System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0));

                ClientConsultaHistoryD history = new ClientConsultaHistoryD(0, tabla.getValueAt(tabla.getSelectedRow(), 0) + "");
                history.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

                history.setVisible(true);
                history.pack();

            } else {
                JOptionPane.showMessageDialog(this, "Ningún elemento seleccionado");
            }

        } catch (Exception e) {
            System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0) + "ERROR");
        }

    }//GEN-LAST:event_historialBotonActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        Cliente clienttemp = new Cliente();
        try {
            clienttemp = consulta.verClientePorCedulaUnico(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());

        } catch (Exception ex) {
            Logger.getLogger(ProductosUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "No ha seleccionado un cliente, intente de nuevo");
        }
        ClientNewD clientmodD;
        // System.out.println(clienttemp.getCod_bolsa()+"");
        clientmodD = new ClientNewD(clienttemp);
        clientmodD.setLocationRelativeTo(null);
        clientmodD.setVisible(true);
        clientmodD.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonModificarActionPerformed

    public Cliente getCliente() {
        return cliente;
    }

    private void search(int index, String textf) {


        switch (index) {

            case 0: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verClientePorCedula(textf));
                    validate();
                    this.updateUI();

                    /*for (Iterator<Producto> it = consulta.verProductoPorID(textf).iterator(); it.hasNext();) {
                     Producto prod = it.next();
                     System.out.print(""+prod.getCod_individual()+"\n");
                     }*/
                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }
            case 1: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verClientePorNombre(textf));
                    validate();
                    this.updateUI();

                    /*for (Iterator<Producto> it = consulta.verProductoPorID(textf).iterator(); it.hasNext();) {
                     Producto prod = it.next();
                     System.out.print(""+prod.getCod_individual()+"\n");
                     }*/
                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }
            case 2: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verClientePorApellido(textf));
                    validate();
                    this.updateUI();

                    /*for (Iterator<Producto> it = consulta.verProductoPorID(textf).iterator(); it.hasNext();) {
                     Producto prod = it.next();
                     System.out.print(""+prod.getCod_individual()+"\n");
                     }*/
                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }



            default: {
                System.out.print("no cambia * no hay opcion");
            }
        }

    }

    private void eliminarTabla() {

        try {
            pane.remove(tabla);
            this.remove(pane);
        } catch (Exception e) {
            System.out.print("no elimina tabla");
        }


    }
    private JScrollPane jScrollPane1;
//    private final JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton historialBoton;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton refBoton;
    private javax.swing.JButton seleccionar;
    // End of variables declaration//GEN-END:variables
}
