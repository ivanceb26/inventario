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
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import negocio.Contabilidad;

/**
 *
 * @author Ivan C
 */
public class ContReservaUI extends javax.swing.JPanel {

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
    private Date dateactual;
    private DateFormat dateFormat2;
    private Double stotal;
    private Double ssubtotal;
    private Double smultas;
    private Double sdescuentos;
    private int snumproductos;
    private int snumpedidos;

    public ContReservaUI() throws Exception {
        // indice = ind;
        //   parameter = cotxt;

        viewwidthtable = 880;
        widthtable = 3;
        anchocolumna = new int[8];
        nombrecolumna = new String[8];

        anchocolumna[0] = 240;
        anchocolumna[1] = 170;
        anchocolumna[2] = 170;
        anchocolumna[3] = 150;
        anchocolumna[4] = 130;
        anchocolumna[5] = 130;
        anchocolumna[6] = 130;
        anchocolumna[7] = 130;

        for (int i = 0; i < anchocolumna.length; i++) {
            widthtable = widthtable + anchocolumna[i];
        }


        nombrecolumna[0] = "Fecha";


        nombrecolumna[2] = "No Productos";
        nombrecolumna[3] = "Subtotal";
        nombrecolumna[4] = "Multas";
        nombrecolumna[5] = "Descuento";
        nombrecolumna[6] = "TOTAL";
        nombrecolumna[7] = "Pendiente";


        initComponents();

        if (jComboBox1.getSelectedIndex() == 0) {
            nombrecolumna[1] = "Cod Pedido";
        } else {
            nombrecolumna[1] = "No Pedidos";
        }

        consulta = new Consultas();
        search(0);


    }

    /*
     * Método reemplazo del que falló que por cierto está sin borrar
     */
    public void verTabla(ArrayList<Contabilidad> cons) {

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

        //variables para sumas
        sdescuentos = 0.0;
        ssubtotal = 0.0;
        smultas = 0.0;
        stotal = 0.0;
        snumpedidos = 0;
        snumproductos = 0;




        for (Iterator<Contabilidad> it = cons.iterator(); it.hasNext();) {
            Contabilidad cont = it.next();
            row = new ArrayList<String>();
            stotal = stotal + cont.getTotal();
            ssubtotal = ssubtotal + cont.getSubtotal();
            sdescuentos = sdescuentos + cont.getDescuentos();
            smultas = smultas + cont.getMultas();
            snumproductos = snumproductos + cont.getNum_productos();

            if (jComboBox1.getSelectedIndex() == 0) {
                dateFormat2 = new SimpleDateFormat(" dd / MMM/yy", new Locale("es_ES"));
                snumpedidos = snumpedidos + 1;

            }
            if (jComboBox1.getSelectedIndex() == 1) {
                dateFormat2 = new SimpleDateFormat(" dd / MMM/yyyy", new Locale("es_ES"));
                snumpedidos = snumpedidos + cont.getNum_pedidos();
            }
            if (jComboBox1.getSelectedIndex() == 2) {
                dateFormat2 = new SimpleDateFormat(" MMMM /yyyy", new Locale("es_ES"));
                snumpedidos = snumpedidos + cont.getNum_pedidos();
            }
            if (jComboBox1.getSelectedIndex() == 3) {
                dateFormat2 = new SimpleDateFormat(" yyyy ", new Locale("es_ES"));
                snumpedidos = snumpedidos + cont.getNum_pedidos();
            }

            row.add(dateFormat2.format(cont.getFecha()) + " ");

            if (jComboBox1.getSelectedIndex() == 0) {
                row.add(cont.getIdop() + "");
            } else {
                row.add(cont.getNum_pedidos() + "");
            }
            row.add(cont.getNum_productos() + "");
            row.add(cont.getSubtotal() + " ");
            row.add(cont.getMultas() + "");
            row.add(cont.getDescuentos() + "");
            row.add(cont.getTotal() + "");
            row.add(cont.getPendiente() + "");
            //System.out.println(cont.getNum_pedidos() + " "+cont.getFecha()+ " ");

            rows.add(row);
        }


        //asigna a label las distintas sumas
        jLabelDescuento.setText(sdescuentos + "");
        jLabelSubtotal.setText(ssubtotal + "");
        jLabelMultas.setText(smultas + "");
        jLabelTotal.setText(stotal + "");
        jLabelPedidosnum.setText(snumpedidos + "");
        jLabelProdnum.setText(snumproductos + "");




        //
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
                pane.setSize(widthtable, 420);
                pane.setViewportView(tabla);

                pane.setBounds(90, 210, widthtable, 370);
            } else {
                pane.setSize(viewwidthtable, 380);
                pane.setViewportView(tabla);

                pane.setBounds(90, 210, viewwidthtable, 370);
            }

        } else {
            pane.setSize(viewwidthtable, 380);
            pane.setViewportView(tabla);

            pane.setBounds(90, 210, viewwidthtable, 370);

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

        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        seleccionar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jLabelTotaltxt = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jLabelSubtotaltxt = new javax.swing.JLabel();
        jLabelSubtotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabelMultas = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelDescuento = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelPedidosnum = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelProdnum = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(971, 710));
        setMinimumSize(new java.awt.Dimension(971, 710));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Este Dia", "Este Mes", "Este Año", "Ver años" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel1.setText("Contabilidad:");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setText("Contabilidad - Pedidos");

        seleccionar.setText("Seleccionar");
        seleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seleccionarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Ver Detalles");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCalendar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendar1PropertyChange(evt);
            }
        });

        jLabelTotaltxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTotaltxt.setText("TOTAL :");

        jLabelTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelTotal.setText("--------------");

        jLabelSubtotaltxt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelSubtotaltxt.setText("Subtotal :");

        jLabelSubtotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelSubtotal.setText("----------------");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Multas :");

        jLabelMultas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelMultas.setText("--------------------");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Descuento:");

        jLabelDescuento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelDescuento.setText("--------------------");

        jLabel5.setText("No. Pedidos :");

        jLabelPedidosnum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelPedidosnum.setText("------------------");

        jLabel6.setText("No. Producto:");

        jLabelProdnum.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabelProdnum.setText("--------------------");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(seleccionar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(207, 207, 207)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(180, 180, 180)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(40, 40, 40)
                        .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(748, 748, 748)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTotaltxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPedidosnum, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelProdnum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabelSubtotaltxt))
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelMultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelDescuento)))))
                .addGap(435, 435, 435))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendar1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSubtotaltxt)
                    .addComponent(jLabelSubtotal))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelMultas))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDescuento)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelProdnum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPedidosnum))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTotaltxt)
                    .addComponent(jLabelTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(seleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );

        jComboBox1.setSelectedIndex(indice);
        dateactual = jCalendar1.getDate();
    }// </editor-fold>//GEN-END:initComponents

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    search(jComboBox1.getSelectedIndex());

}//GEN-LAST:event_jComboBox1ActionPerformed

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

    private void jCalendar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendar1PropertyChange
        dateactual = jCalendar1.getDate();
        search(jComboBox1.getSelectedIndex());

        //System.out.print("como que si funciona" + jCalendar1.getDate());
        // TODO add your handling code here:
    }//GEN-LAST:event_jCalendar1PropertyChange

    private void search(int index) {


        switch (index) {

            //consulta Dia
            case 0: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verContabilidadPedidosPorUnicoDia(dateactual));
                    validate();
                    this.updateUI();


                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }
            //consulta Dias del mes
            case 1: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verContabilidadPedidosPorRangoDeFechaDia(dateactual));
                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }
            //consulta meses del año
            case 2: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verContabilidadPedidosPorRangoDeFechaMes(dateactual));
                    validate();
                    this.updateUI();

                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            //consulta años
            case 3: {
                validate();
                eliminarTabla();
                try {


                    verTabla(consulta.verContabilidadPedidosPorRangoDeFechaAno(dateactual));
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
    /*  private void agregarOyente() {
     jCalendar1.getDayChooser().addPropertyChangeListener(
     new java.beans.PropertyChangeListener() {
 
     @Override
     public void propertyChange(java.beans.PropertyChangeEvent evt) {
                        
     System.out.println("hola");
                        
     }
     });
     }*/
//    private final JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelDescuento;
    private javax.swing.JLabel jLabelMultas;
    private javax.swing.JLabel jLabelPedidosnum;
    private javax.swing.JLabel jLabelProdnum;
    private javax.swing.JLabel jLabelSubtotal;
    private javax.swing.JLabel jLabelSubtotaltxt;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTotaltxt;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton seleccionar;
    // End of variables declaration//GEN-END:variables
}
