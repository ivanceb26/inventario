/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientesConsultaReferD.java
 *
 * Created on 17-mar-2013, 23:07:39
 */
package gui.dialog;

import java.awt.Dialog;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import negocio.Cliente;
import negocio.Consultas;
import negocio.Control;
import negocio.ControlUsuario;
import negocio.Eliminaciones;
import negocio.Producto;
import negocio.RefCliente;
import negocio.RefClienteCliente;
import util.GestorTabla;

/**
 *
 * @author Ivan C
 */
public class ClientesConsultaReferD extends javax.swing.JDialog {

    /** Creates new form ClientesConsultaReferD */
    private Consultas consulta;
    private DefaultTableModel modelo;
    private JTable tabla;
    private GestorTabla gTabla;
    private ArrayList<RefCliente> arraylocal;
    private ArrayList<String> cols;
    private ArrayList<String> row;
    private ArrayList<ArrayList> rows;
    private int anchocolumna[];
    private String nombrecolumna[];
    private int widthtable;
    JScrollPane pane;
    Cliente cliente;
    RefCliente refCliente;
    private ArrayList<RefCliente> referencias;
    private Eliminaciones elim;

    public ClientesConsultaReferD(String cod) throws Exception {
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        widthtable = 3;
        anchocolumna = new int[6];
        nombrecolumna = new String[6];
        referencias = new ArrayList<RefCliente>();
        elim = new Eliminaciones();

        anchocolumna[0] = 100;
        anchocolumna[1] = 100;
        anchocolumna[2] = 100;
        anchocolumna[3] = 120;
        anchocolumna[4] = 40;
        anchocolumna[5] = 40;


        for (int i = 0; i < anchocolumna.length; i++) {
            widthtable = widthtable + anchocolumna[i];
        }


        nombrecolumna[0] = "cedula";
        nombrecolumna[1] = "Nombres";
        nombrecolumna[2] = "Apellidos";
        nombrecolumna[3] = "Direccion";
        nombrecolumna[4] = "Telefono";
        nombrecolumna[5] = "Fotos";






        consulta = new Consultas();
        //ESTA CONSULTA ESTA MAL, DEBE IR A LA TABLA REF_CLIENTES_CLIENTE
        cliente = consulta.verClientePorCedula(cod).get(0);
        referencias = consulta.verReferenciasPorCliente(cliente.getCedula());
        verTabla(referencias);


        initComponents();
        this.setSize(widthtable + 80, 400);
        this.setPreferredSize(new Dimension(widthtable + 80, 400));
        this.setResizable(false);
        this.setFocusableWindowState(true);
        this.setLocationRelativeTo(null);
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

        for (Iterator<RefCliente> it = cons.iterator(); it.hasNext();) {
            RefCliente clien = it.next();
            row = new ArrayList<String>();
            row.add(clien.getCedula());
            row.add(clien.getNombre());
            row.add(clien.getApellido());
            row.add(clien.getDireccion());
            row.add(clien.getTelefono());
            row.add(clien.getFoto());
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
            pane.setSize(widthtable, 470);
            pane.setViewportView(tabla);

            pane.setBounds(35, 120, widthtable, 200);
        } else {
            pane.setSize(980, 470);
            pane.setViewportView(tabla);

            pane.setBounds(35, 30, 880, 450);
        }
        //jScrollPane1 = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(pane);

    }

    public ClientesConsultaReferD() {
    }

    public void eliminarTabla() {

        try {
            pane.remove(tabla);
            this.remove(pane);
        } catch (Exception e) {
            System.out.print("no elimina tabla");
        }


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel1.setText("Referencias");

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Agregar Referencia");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)))
                .addGap(6, 6, 6)
                .addComponent(jButton2)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
    refCliente = new RefCliente();

    try {
        referencias = consulta.verReferenciasPorCliente(cliente.getCedula());
        ReferenceNewD refNew = new ReferenceNewD(refCliente, referencias, null, cliente, this);
        refNew.setVisible(true);
    } catch (SQLException ex) {
        Logger.getLogger(ClientesConsultaReferD.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
        Logger.getLogger(ClientesConsultaReferD.class.getName()).log(Level.SEVERE, null, ex);
    }
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    if (tabla.getSelectedRowCount() >= 1) {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar la referencia?");
        if (opcion == JOptionPane.YES_OPTION) {
                try {
                    for (Iterator<RefCliente> it = referencias.iterator(); it.hasNext();) {
                        RefCliente ref = it.next();
                        if (tabla.getValueAt(tabla.getSelectedRow(), 0).equals(ref.getCedula())) {
                            refCliente = new RefCliente();
                            refCliente = ref;
                        }
                    }
                    referencias.remove(refCliente);
                    RefClienteCliente rff = new RefClienteCliente();
                    rff.setIdCliente(cliente.getCedula());
                    rff.setIdRef(refCliente.getCedula());
                    elim.eliminarRefClienteCliente(rff);
                    eliminarTabla();
                    verTabla(referencias);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientesConsultaReferD.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Ningún elemento seleccionado");
    }
}//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ClientesConsultaReferD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientesConsultaReferD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientesConsultaReferD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientesConsultaReferD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new ClientesConsultaReferD().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
