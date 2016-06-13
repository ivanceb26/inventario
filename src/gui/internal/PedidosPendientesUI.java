/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Inicio.java
 *
 * Created on 09-mar-2013, 15:38:04
 */
package gui.internal;

import gui.dialog.PedConsultaDetallesD;
import gui.dialog.ReserConsultaUI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import negocio.Cliente;
import negocio.Consultas;
import util.GestorTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import negocio.Pedido;

/**
 *
 * @author Ivan C
 */
public class PedidosPendientesUI extends javax.swing.JPanel {

    /**
     * Creates new form Inicio
     */
    private Consultas consulta;
    private DefaultTableModel modelo;
    private JTable tabla;
    private GestorTabla gTabla;
    private ArrayList<String> cols;
    private ArrayList<String> row;
    private ArrayList<ArrayList> rows;
    private int anchocolumna[];
    private String nombrecolumna[];
    private int widthtable;
    private Cliente cliente;
    JScrollPane pane;
    private JFrame auxiliar;
    private JLabel label;
    private JButton bu1, bu2;
    private java.util.Date date1;
    private int indice;
    private String parameter;
    private int viewwidthtable;

    public PedidosPendientesUI() throws Exception {
        indice = 0;
        parameter = "";

        viewwidthtable = 870;

        widthtable = 3;
        anchocolumna = new int[12];
        nombrecolumna = new String[12];

        anchocolumna[0] = 40;
        anchocolumna[1] = 80;
        anchocolumna[2] = 80;
        anchocolumna[3] = 80;
        anchocolumna[4] = 40;
        anchocolumna[5] = 40;
        anchocolumna[6] = 80;
        anchocolumna[7] = 80;
        anchocolumna[8] = 90;
        anchocolumna[9] = 90;
        anchocolumna[10] = 40;
        anchocolumna[11] = 40;

        for (int i = 0; i < anchocolumna.length; i++) {
            widthtable = widthtable + anchocolumna[i];
        }


        nombrecolumna[0] = "Cod Pedido";
        nombrecolumna[1] = "Ced Cliente";
        nombrecolumna[2] = "Num Objetos";
        nombrecolumna[3] = "Usuario";
        nombrecolumna[4] = "Abono";
        nombrecolumna[5] = "Saldo";
        nombrecolumna[6] = "Multa";
        nombrecolumna[7] = "Total";
        nombrecolumna[8] = "Fecha Emision";
        nombrecolumna[9] = "Fecha Devolucion";
        nombrecolumna[10] = "Pago Pendiente";
        nombrecolumna[11] = "Entrega Pendiente";




        initComponents();
        consulta = new Consultas();
        search(indice, parameter);


    }

    /*  public PedidosPendientesUI(int ind, String cotxt, Cliente c, JFrame a, JLabel l, JButton b1, JButton b2) throws Exception {
     this(ind, cotxt);
    
     seleccionar.setVisible(true);
     cliente = c;
     auxiliar = a;
     label = l;
     bu1 = b1;
     bu2 = b1;
    
     }*/

    /*
     * Método reemplazo del que falló que por cierto está sin borrar
     */
    public void verTabla(ArrayList cons) {

        pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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

        for (Iterator<Pedido> it = cons.iterator(); it.hasNext();) {
            Pedido pedid = it.next();
            row = new ArrayList<String>();
            row.add(pedid.getId());
            row.add(pedid.getIdCliente());
            row.add(pedid.getNumProductos() + "");
            row.add(pedid.getUsuario() + "");
            row.add(pedid.getPagado() + "");
            row.add(pedid.getSaldo() + "");
            row.add(pedid.getMulta() + "");
            row.add(pedid.getTotal() + "");
            DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            row.add(dateFormat2.format(pedid.getEmision()) + "");
            row.add(dateFormat2.format(pedid.getFechadevolucion()) + "");
            if (pedid.getPendiente_pago() == 1) {
                row.add("  X");
            } else {
                row.add("");
            }
            if (pedid.getPendiente_entrega() == 1) {
                row.add("  X");
            } else {
                row.add("");
            }

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

// cuadrando automaticamente el ancho de columnas
        int defaultwidth = 0;
        for (int i = 0; i < anchocolumna.length; i++) {

            defaultwidth = defaultwidth + tabla.getColumnModel().getColumn(i).getPreferredWidth();
        }
        System.out.println(defaultwidth + "  view: " + viewwidthtable + "  widthtable: " + widthtable);
        if (defaultwidth > viewwidthtable) {
            for (int i = 0; i < anchocolumna.length; i++) {

                tabla.getColumnModel().getColumn(i).setPreferredWidth(anchocolumna[i]);
            }
            tabla.getColumnModel().getColumn(10).setPreferredWidth(anchocolumna[10]);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(anchocolumna[11]);

            if (widthtable < viewwidthtable) {
                pane.setSize(widthtable+80, 470);
                pane.setViewportView(tabla);

                pane.setBounds(38, 140, widthtable+80, 450);
            } else {
                pane.setSize(viewwidthtable+80, 470);
                pane.setViewportView(tabla);

                pane.setBounds(38, 140, viewwidthtable+80, 450);
            }

        } else {
            tabla.getColumnModel().getColumn(10).setPreferredWidth(anchocolumna[10]);
            tabla.getColumnModel().getColumn(11).setPreferredWidth(anchocolumna[11]);
            pane.setSize(viewwidthtable+80, 470);
            pane.setViewportView(tabla);

            pane.setBounds(38, 140, viewwidthtable+80, 450);


        }
        tabla.getColumnModel().getColumn(10).setPreferredWidth(anchocolumna[10]);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(anchocolumna[11]);
//          
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
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(871, 680));
        setMinimumSize(new java.awt.Dimension(871, 680));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cedula", "Codigo Pedido", "Usuario" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Buscar Por:");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        jLabel2.setText("PEDIDOS PENDIENTES");

        jButton2.setText("Ver Detalles");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(jButton2)))
                .addContainerGap(415, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(489, 489, 489)
                .addComponent(jButton2)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);

    search(jComboBox1.getSelectedIndex(), textff);

}//GEN-LAST:event_jComboBox1ActionPerformed

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);

    search(jComboBox1.getSelectedIndex(), textff);

}//GEN-LAST:event_jTextField1KeyReleased

private void seleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seleccionarActionPerformed
// TODO add your handling code here:
    try {
        cliente = consulta.verClientePorCedula("" + tabla.getValueAt(tabla.getSelectedRow(), 0)).get(0);
        auxiliar.dispose();
        label.setText(cliente.getNombre() + " " + cliente.getApellido());
        bu1.setVisible(false);
        bu2.hide();

    } catch (Exception e) {
    }
}//GEN-LAST:event_seleccionarActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    try {
        PedConsultaDetallesD pdetallesd;
        pdetallesd = new PedConsultaDetallesD(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());

        pdetallesd.setVisible(true);
        pdetallesd.setLocationRelativeTo(null);
    } catch (Exception ex) {
        Logger.getLogger(ReserConsultaUI.class.getName()).log(Level.SEVERE, null, ex);
        System.out.print("No genera ventana de ver detalles de reserva");
        JOptionPane.showMessageDialog(this, "No ha seleccionado un pedido, intente de nuevo");
    }

}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
        PedConsultaDetallesD pdetallesd;
        pdetallesd = new PedConsultaDetallesD(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
        pdetallesd.setLocationRelativeTo(null);
        pdetallesd.setVisible(true);
        
    } catch (Exception ex) {
        Logger.getLogger(ReserConsultaUI.class.getName()).log(Level.SEVERE, null, ex);
        System.out.print("No genera ventana de ver detalles de reserva");
        JOptionPane.showMessageDialog(this, "No ha seleccionado un pedido, intente de nuevo");
    }// TODO add your handling code here:
}//GEN-LAST:event_jButton2ActionPerformed

    private void search(int index, String textf) {


        switch (index) {

            case 0: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verPedidosPorPendienteDePagoODeEntregaYCedula(textf));
                    validate();
                    this.updateUI();
                    System.out.println("codigo Consulta nuevo " + consulta.obtenerCodigoPedido());

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 1: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verPedidosPorPendienteDePagoODeEntregaYIdPedido(textf));
                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 2: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verPedidosPorPendienteDePagoODeEntregaYUsuario(textf));
                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 3: {
                try {

                    validate();
                    eliminarTabla();
//                    verTabla(consulta.verPedidosPorRangoDeFechaYCodigoPedido(jDateChooser2.getDate(), jDateChooser1.getDate(), textf));
                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 4: {
                try {

                    validate();
                    eliminarTabla();
//                    verTabla(consulta.verPedidosPorRangoDeFechaYUsuario(jDateChooser2.getDate(), jDateChooser1.getDate(), textf));
                    validate();
                    this.updateUI();

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
//    private final JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
