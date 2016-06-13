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

import java.awt.Dialog;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import negocio.Cliente;
import negocio.Consultas;
import negocio.Producto;
import negocio.Reserva;
import util.GestorTabla;

/**
 *
 * @author Ivan C
 */
public class ReserNewAddD extends javax.swing.JDialog {

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
    JScrollPane pane;
    Producto produ;
    ArrayList<Producto> produs;
    ReserNewD auxRes;
    ReserConsultaDetallesD auxDetalle;

    public ReserNewAddD(Producto producto, ArrayList productos, ReserConsultaDetallesD aux) {

        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        produ = producto;
        produs = productos;
        auxDetalle = aux;

        widthtable = 3;
        anchocolumna = new int[11];
        nombrecolumna = new String[11];

        anchocolumna[0] = 50;
        anchocolumna[1] = 50;
        anchocolumna[2] = 70;
        anchocolumna[3] = 70;
        anchocolumna[4] = 100;
        anchocolumna[5] = 120;
        anchocolumna[6] = 70;
        anchocolumna[7] = 80;
        anchocolumna[8] = 80;
        anchocolumna[9] = 80;
        anchocolumna[10] = 60;

        for (int i = 0; i < anchocolumna.length; i++) {
            widthtable = widthtable + anchocolumna[i];
        }


        nombrecolumna[0] = "Codigo";
        nombrecolumna[1] = "Cod Bolsa";
        nombrecolumna[2] = "Nombre";
        nombrecolumna[3] = "Color";
        nombrecolumna[4] = "Textura";
        nombrecolumna[5] = "Caracteristicas";
        nombrecolumna[6] = "Estado";
        nombrecolumna[7] = "Talla";
        nombrecolumna[8] = "Sexo";
        nombrecolumna[9] = "Pais";
        nombrecolumna[10] = "Precio";


        consulta = new Consultas();
        search(0, "");
        initComponents();
        setLocationRelativeTo(null);
    }

    public ReserNewAddD(Producto producto, ArrayList productos, ReserNewD aux) throws Exception {

        produ = producto;
        produs = productos;
        auxRes = aux;




        widthtable = 3;
        anchocolumna = new int[11];
        nombrecolumna = new String[11];

        anchocolumna[0] = 60;
        anchocolumna[1] = 120;
        anchocolumna[2] = 60;
        anchocolumna[3] = 120;
        anchocolumna[4] = 70;
        anchocolumna[5] = 80;
        anchocolumna[6] = 100;
        anchocolumna[7] = 120;
        anchocolumna[8] = 100;
        anchocolumna[9] = 60;
        anchocolumna[10] = 60;

        for (int i = 0; i < anchocolumna.length; i++) {
            widthtable = widthtable + anchocolumna[i];
        }


        nombrecolumna[0] = "Codigo";
        nombrecolumna[1] = "Cod Bolsa";
        nombrecolumna[2] = "Nombre";
        nombrecolumna[3] = "Color";
        nombrecolumna[4] = "Textura";
        nombrecolumna[5] = "Caracteristicas";
        nombrecolumna[6] = "Estado";
        nombrecolumna[7] = "Talla";
        nombrecolumna[8] = "Sexo";
        nombrecolumna[9] = "Pais";
        nombrecolumna[10] = "Precio";


        consulta = new Consultas();
        search(0, "");
        initComponents();


    }

    /*
     * Método reemplazo del que falló que por cierto está sin borrar
     */
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

        for (Iterator<Producto> it = cons.iterator(); it.hasNext();) {
            Producto prod = it.next();
            row = new ArrayList<String>();
            row.add(prod.getCod_individual());
            row.add(prod.getCod_bolsa());
            row.add(prod.getNombre());
            row.add(prod.getColor());
            row.add(prod.getTextura());
            row.add(prod.getCaract());
            row.add(prod.getEstado());
            row.add(prod.getTalla());
            row.add(prod.getSexo());
            row.add(prod.getPais());
            row.add("" + prod.getPrecio());

            rows.add(row);
        }

        gTabla.agregarFilas(rows);

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//permite seleccionar solo una fila
        tabla.getTableHeader().setReorderingAllowed(false); //Evito el cambio de columnas
        tabla.getTableHeader().setResizingAllowed(false); //Evito la redimensión de las columnas por el usuario
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

            pane.setBounds(35, 120, widthtable, 450);
        } else {
            pane.setSize(980, 470);
            pane.setViewportView(tabla);

            pane.setBounds(35, 120, 880, 450);
        }
        //jScrollPane1 = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(971, 710));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Buscar Por:");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setText("Productos");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cod Producto", "Cod Bolsa", "Nombre", "Pais", "Disponibilidad", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(290, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 418, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);
    search(jComboBox1.getSelectedIndex(), textff);
}//GEN-LAST:event_jTextField1KeyReleased

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    Boolean exis = false;

    //ArrayList<RefCliente> r = control.consultarReferenciaPorCedula(cedula.getText());

    for (Iterator<Producto> it = produs.iterator(); it.hasNext();) {
        Producto prod = it.next();
        if (prod.getCod_individual().equals("" + tabla.getValueAt(tabla.getSelectedRow(), 0))) {
            exis = true;
        }
    }


    if (!exis) {
        try {
            ArrayList<Reserva> reservas = new ArrayList<Reserva>();
            produ = new Producto();
            produ = consulta.verProductoPorIDUnico(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
            reservas = consulta.verReservaPorProducto(produ.getCod_individual());


            for (Iterator<Reserva> it = reservas.iterator(); it.hasNext();) {
                Reserva reserva = it.next();
                if (reserva.getActivo() == 1) {
                    if (reserva.getFechaReservaIni().equals(auxRes.getFechaReserva())) {
                        produ.setDisponibilidad("RESERVADO");
                    }
                    if (reserva.getDiasReserva().equals(auxRes.getFechaReserva())) {
                        produ.setDisponibilidad("RESERVADO");
                    }
                    if (reserva.getFechaReservaIni().before(auxRes.getFechaReserva())
                            && reserva.getDiasReserva().after(auxRes.getFechaReserva())) {
                        produ.setDisponibilidad("RESERVADO");
                    }
                }

            }
            if (produ.getDisponibilidad().equals("RESERVADO")) {
                produ.setDisponibilidad("DISPONIBLE");
                JOptionPane.showMessageDialog(this, "El producto ya tiene una reserva para esa fecha");
            } else if (produ.getDisponibilidad().equals("ALQUILADO")) {
                JOptionPane.showMessageDialog(this, "El producto se encuentra actualmente alquilado");
            } else {

                produs.add(produ);
                if (auxRes == null) {
                    auxDetalle.eliminarTabla();
                    auxDetalle.verTabla(produs);
                }
                if (auxDetalle == null) {
                    auxRes.eliminarTabla();
                    auxRes.verTabla(produs);
                }
                double sumatmp = 0;
                for (Iterator<Producto> it = produs.iterator(); it.hasNext();) {
                    Producto produc = it.next();

                    //crear y agregar en array Reservas de Productos

                    //suma de precios para total
                    sumatmp = sumatmp + produc.getPrecio();

                }
                auxRes.setTotal(sumatmp);
                this.dispose();
            }
        } catch (Exception ex) {
            Logger.getLogger(ReserNewAddD.class.getName()).log(Level.SEVERE, null, ex);
            //  System.out.print("no selecciona objeto");
        }

    } else {
        JOptionPane.showMessageDialog(this, "El producto ya está asociado a esta reserva, por favor intente de nuevo");
        this.dispose();
    }


    // TODO add your handling code here:
}//GEN-LAST:event_jButton1ActionPerformed

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);
    search(jComboBox1.getSelectedIndex(), textff);





// TODO add your handling code here:
}//GEN-LAST:event_jComboBox1ActionPerformed

// <editor-fold defaultstate="collapsed" desc="SEARCH METOD"> 
    private void search(int index, String textf) {


        switch (index) {

            case 0: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verProductoPorID(textf));
                    validate();
                    this.repaint();

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
                    verTabla(consulta.verProductoPorCodBolsa(textf));
                    validate();
                    this.repaint();

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
                    verTabla(consulta.verProductosPorNombre(textf));
                    validate();
                    this.repaint();

                    /*for (Iterator<Producto> it = consulta.verProductoPorID(textf).iterator(); it.hasNext();) {
                     Producto prod = it.next();
                     System.out.print(""+prod.getCod_individual()+"\n");
                     }*/
                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            case 3: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verProductosPorPais(textf));
                    validate();
                    this.repaint();


                } catch (Exception ex) {
                    System.out.print("no cambia");
                }

                break;
            }

            /* case 4: {
             try {
            
             validate();
             eliminarTabla();
             verTabla(consulta.verProductoPorDisponibilidad(textf));
             validate();
             this.repaint();
            
            
             } catch (Exception ex) {
             Logger.getLogger(ProductosUI.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             break;
             }*/




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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
