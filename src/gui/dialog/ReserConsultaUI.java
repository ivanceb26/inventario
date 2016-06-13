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

import gui.dialog.ReserConsultaDetallesD;
import java.text.ParseException;
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
import negocio.Reserva;
import util.GestorTabla;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import util.CalendarUtil;

/**
 *
 * @author Ivan C
 */
public class ReserConsultaUI extends javax.swing.JPanel {

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

    public ReserConsultaUI(int ind, String cotxt) throws Exception {
        indice = ind;
        parameter = cotxt;

        viewwidthtable = 880;
        widthtable = 3;
        anchocolumna = new int[10];
        nombrecolumna = new String[10];

        anchocolumna[0] = 200;
        anchocolumna[1] = 150;
        anchocolumna[2] = 130;
        anchocolumna[3] = 130;
        anchocolumna[4] = 100;
        anchocolumna[5] = 40;
        anchocolumna[6] = 40;
        anchocolumna[7] = 50;
        anchocolumna[8] = 50;
        anchocolumna[9] = 50;


        for (int i = 0; i < anchocolumna.length; i++) {
            widthtable = widthtable + anchocolumna[i];
        }

        nombrecolumna[0] = "Cod Reserva";
        nombrecolumna[1] = "Ced Cliente";
        nombrecolumna[2] = "Realizada";
        nombrecolumna[3] = "Inicio";
        nombrecolumna[4] = "Fin";
        nombrecolumna[5] = "Revision";
        nombrecolumna[6] = "Usuario";
        nombrecolumna[7] = "Abono";
        nombrecolumna[8] = "Total";
        nombrecolumna[9] = "Pendiente";




        initComponents();
        consulta = new Consultas();

        search(indice, parameter);





    }

    public ReserConsultaUI(int ind, String cotxt, Cliente c, JFrame a, JLabel l, JButton b1, JButton b2) throws Exception {
        this(ind, cotxt);

        seleccionar.setVisible(true);
        cliente = c;
        auxiliar = a;
        label = l;
        bu1 = b1;
        bu2 = b1;

    }

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

        for (Iterator<Reserva> it = cons.iterator(); it.hasNext();) {
            Reserva reserv = it.next();
            row = new ArrayList<String>();

            row.add(reserv.getId() + "");
            row.add(reserv.getIdCliente() + "");
            DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
            row.add(dateFormat2.format(reserv.getFechaRealizada()) + "");
            row.add(dateFormat2.format(reserv.getFechaReservaIni()) + "");
            row.add(dateFormat2.format(reserv.getDiasReserva()) + "");
            row.add(dateFormat2.format(reserv.getFechamod()) + "");
            row.add(reserv.getUsuario() + "");
            row.add(reserv.getAbono() + "");
            row.add(reserv.getTotal() + "");
            row.add(reserv.getPendiente()+ "");

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

            defaultwidth = defaultwidth + tabla.getColumnModel().getColumn(i).getWidth();
        }
        System.out.println(defaultwidth + "  view: " + viewwidthtable + "  widthtable: " + widthtable);
        if (defaultwidth > viewwidthtable) {
            for (int i = 0; i < anchocolumna.length; i++) {

                tabla.getColumnModel().getColumn(i).setPreferredWidth(anchocolumna[i]);
            }

            if (widthtable < viewwidthtable) {
                pane.setSize(widthtable, 470);
                pane.setViewportView(tabla);

                pane.setBounds(38, 140, widthtable, 450);
            } else {
                pane.setSize(viewwidthtable, 470);
                pane.setViewportView(tabla);

                pane.setBounds(38, 140, viewwidthtable, 450);
            }

        } else {
            pane.setSize(viewwidthtable, 470);
            pane.setViewportView(tabla);

            pane.setBounds(38, 140, viewwidthtable, 450);


        }
//
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
        jSeparator1 = new javax.swing.JSeparator();
        seleccionar = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(971, 710));
        setMinimumSize(new java.awt.Dimension(971, 710));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cedula Cliente", "Codigo Reserva", "Usuario" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Buscar Por:");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setText("Reservas");

        seleccionar.setText("Seleccionar");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("DE:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("A ");

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel5.setText("Fecha Por:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fecha Reserva", "Fecha Realizada" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Ver Detalles");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(245, 245, 245)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(seleccionar)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(266, 266, 266))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(493, 493, 493)
                        .addComponent(seleccionar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 469, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(80, 80, 80))))
        );

        jComboBox1.setSelectedIndex(indice);
        jDateChooser1.setDateFormatString("   dd / MMMMM / yyyy ");

        DateFormat dateFormat = new SimpleDateFormat("   dd / MMMMM / yyyy ");
        java.util.Date fechaActual = new java.util.Date();
        jDateChooser1.setDate(CalendarUtil.cambiarFecha(fechaActual, 0, 0, 0));
        jDateChooser2.setDateFormatString("   dd / MMMMM / yyyy ");

        java.util.Date date2 = new java.util.Date();

        date2 = CalendarUtil.cambiarFecha(fechaActual, 0, -2, 0);

        jDateChooser2.setDate(date2);
    }// </editor-fold>//GEN-END:initComponents

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);

    if (jComboBox2.getSelectedIndex() == 1) {
        search(jComboBox1.getSelectedIndex() + jComboBox1.getItemCount(), textff);
    } else {
        search(jComboBox1.getSelectedIndex(), textff);
    }




// TODO add your handling code here:
}//GEN-LAST:event_jComboBox1ActionPerformed

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);

    if (jComboBox2.getSelectedIndex() == 1) {
        search(jComboBox1.getSelectedIndex() + jComboBox1.getItemCount(), textff);
    } else {
        search(jComboBox1.getSelectedIndex(), textff);
    }
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

private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
// TODO add your handling code here:
    String textff = jTextField1.getText();
    //   System.out.print(jComboBox1.getSelectedIndex() + textff);

    if (jComboBox2.getSelectedIndex() == 1) {
        search(jComboBox1.getSelectedIndex() + jComboBox1.getItemCount(), textff);
    } else {
        search(jComboBox1.getSelectedIndex(), textff);
    }
}//GEN-LAST:event_jComboBox2ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    try {
        ReserConsultaDetallesD rdetallesd;
        rdetallesd = new ReserConsultaDetallesD(tabla.getValueAt(tabla.getSelectedRow(), 0) + "");
        rdetallesd.setLocationRelativeTo(null);
        rdetallesd.setVisible(true);

    } catch (Exception ex) {
        Logger.getLogger(ReserConsultaUI.class.getName()).log(Level.SEVERE, null, ex);
        System.out.print("No genera ventana de ver detalles de reserva");
        JOptionPane.showMessageDialog(this, "No ha seleccionado una reserva, intente de nuevo");
    }

}//GEN-LAST:event_jButton1ActionPerformed

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
        String textff = jTextField1.getText();
        //   System.out.print(jComboBox1.getSelectedIndex() + textff);

        if (jComboBox2.getSelectedIndex() == 1) {
            search(jComboBox1.getSelectedIndex() + jComboBox1.getItemCount(), textff);
        } else {
            search(jComboBox1.getSelectedIndex(), textff);
        }
        

        //System.out.println("si escucha date2 + " + jDateChooser2.getDate() + "   index " + (jComboBox1.getSelectedIndex()));
    }//GEN-LAST:event_jDateChooser2PropertyChange

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        String textff = jTextField1.getText();
        //   System.out.print(jComboBox1.getSelectedIndex() + textff);
       
        if (jComboBox2.getSelectedIndex() == 1) {
            search(jComboBox1.getSelectedIndex() + jComboBox1.getItemCount(), textff);
        } else {
            search(jComboBox1.getSelectedIndex(), textff);
        }
        
        //System.out.println("si escucha date1 + " + jDateChooser1.getDate());
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void search(int index, String textf) {

        
        switch (index) {

            case 0: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verReservasPorRangoDeFechaReservaYCedulaCliente(jDateChooser2.getDate(), jDateChooser1.getDate(), textf));

                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 1: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verReservasPorRangoDeFechaReservaYCodigoReserva(jDateChooser2.getDate(), jDateChooser1.getDate(), textf));
                    validate();
                    this.updateUI();
                    ArrayList arrr = consulta.verReservasPorRangoDeFechaReservaYCodigoReserva(jDateChooser2.getDate(), jDateChooser1.getDate(), textf);
                    /*  for (Iterator<Reserva> it = arrr.iterator(); it.hasNext();) {
                     Reserva res = it.next();
                     //    System.out.println("" + res.getId() + " " + res.getFechaReserva());

                     }*/
                    // System.out.println("siiiii");

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 2: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verReservasPorRangoDeFechaReservaYUsuario(jDateChooser2.getDate(), jDateChooser1.getDate(), textf));
                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 3: {

                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verReservasPorRangoDeFechaRealizadaYCedulaCliente(jDateChooser2.getDate(), jDateChooser1.getDate(), textf));
                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 4: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verReservasPorRangoDeFechaRealizadaYCodigoReserva(jDateChooser2.getDate(), jDateChooser1.getDate(), textf));
                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 5: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verReservasPorRangoDeFechaRealizadaYUsuario(jDateChooser2.getDate(), jDateChooser1.getDate(), textf));
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
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton seleccionar;
    // End of variables declaration//GEN-END:variables
}
