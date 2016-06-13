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
import gui.dialog.ProdNewD;
import gui.dialog.ReserConsultaUI;
import gui.galeria.InterfazGaleria;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.LayoutManager;
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
import negocio.Consultas;
import negocio.Producto;
import util.GestorTabla;

/**
 *
 * @author Ivan C
 */
public class ProductosUI extends javax.swing.JPanel {

    /**
     * Creates new form Inicio
     */
    private Consultas consulta;
    private DefaultTableModel modelo;
    private JTable tabla;
    private GestorTabla gTabla;
    private ArrayList<Producto> arraylocal;
    private ArrayList<String> cols;
    private ArrayList<String> row;
    private ArrayList<ArrayList> rows;
    private int anchocolumna[];
    private String nombrecolumna[];
    JScrollPane pane;

    public ProductosUI() throws Exception {
        anchocolumna = new int[11];
        nombrecolumna = new String[11];

        anchocolumna[0] = 60;
        anchocolumna[1] = 40;
        anchocolumna[2] = 100;
        anchocolumna[3] = 120;
        anchocolumna[4] = 70;
        anchocolumna[5] = 100;
        anchocolumna[6] = 100;
        anchocolumna[7] = 70;
        anchocolumna[8] = 100;
        anchocolumna[9] = 60;
        anchocolumna[10] = 60;

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
        arraylocal = new ArrayList<Producto>();
        arraylocal = consulta.verProductosTodos();
        initComponents();

        this.jButtonDetalles.setVisible(false);
        this.jButtonModificar.setVisible(false);
//        dibujarTabla();
        verTabla(arraylocal);

    }

    public ProductosUI(boolean mod) throws Exception {
        this();
        if (mod) {
            this.jButtonDetalles.setVisible(false);
            this.jButtonModificar.setVisible(true);
        }
    }


    /*
     * Método reemplazo del que falló que por cierto está sin borrar
     */
    private void verTabla(ArrayList cons) {

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
        tabla.getTableHeader().setResizingAllowed(true); //Evito la redimensión de las columnas por el usuario
        tabla.setBorder(BorderFactory.createEmptyBorder());
        //lo que tenía en el método anterior
        tabla.setRowHeight(30);

        tabla.setMaximumSize(new Dimension(920, 400));
        tabla.setRowMargin(3);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        for (int i = 0; i < anchocolumna.length; i++) {
            if (i == 5) {
                i++;
            }

            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchocolumna[i]);

        }


        
        pane.setSize(tabla.getColumnModel().getTotalColumnWidth()+50, 420);
        //jScrollPane1 = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setViewportView(tabla);

        pane.setBounds(35, 120, tabla.getColumnModel().getTotalColumnWidth()+50, 420);
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
        jButtonDetalles = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(971, 710));
        setMinimumSize(new java.awt.Dimension(971, 710));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cod Producto", "Cod Bolsa", "Nombre", "Pais", "Disponibilidad", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        jLabel1.setText("Buscar Por:");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setText("PRODUCTOS");

        jButtonDetalles.setText("Ver Detalles");
        jButtonDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDetallesActionPerformed(evt);
            }
        });

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Ver Galeria de Imagenes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(290, Short.MAX_VALUE)
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
                        .addGap(241, 241, 241)
                        .addComponent(jButtonDetalles)
                        .addGap(49, 49, 49)
                        .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap(457, Short.MAX_VALUE))
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
                .addGap(469, 469, 469)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDetalles)
                    .addComponent(jButtonModificar)
                    .addComponent(jButton1))
                .addGap(55, 55, 55))
        );
    }// </editor-fold>//GEN-END:initComponents

private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    String textff = jTextField1.getText();
    System.out.print(jComboBox1.getSelectedIndex() + textff);
    search(jComboBox1.getSelectedIndex(), textff);





// TODO add your handling code here:
}//GEN-LAST:event_jComboBox1ActionPerformed

private void jButtonDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDetallesActionPerformed

    System.out.println(tabla.getSelectedRow());
    System.out.println(tabla.getValueAt(tabla.getSelectedRow(), 0));
    // TODO add your handling code here:
}//GEN-LAST:event_jButtonDetallesActionPerformed

private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
    String textff = jTextField1.getText();
    System.out.print("index: " + jComboBox1.getSelectedIndex() + " txt: " + textff);
    search(jComboBox1.getSelectedIndex(), textff);


// TODO add your handling code here:
}//GEN-LAST:event_jTextField1KeyReleased

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        Producto prodtmp = new Producto();
        try {
            prodtmp = consulta.verProductoPorIDUnico(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());

        } catch (Exception ex) {
            Logger.getLogger(ProductosUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "No ha seleccionado un producto, intente de nuevo");
        }
        ProdNewD prodmodD;
        System.out.println(prodtmp.getCod_bolsa()+"");
        prodmodD = new ProdNewD(prodtmp);
        prodmodD.setLocationRelativeTo(null);
        prodmodD.setVisible(true);
        prodmodD.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InterfazGaleria prodmodD;
        prodmodD = new InterfazGaleria();
        prodmodD.setLocationRelativeTo(null);
        prodmodD.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void search(int index, String textf) {


        switch (index) {

            case 0: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verProductoPorID(textf));
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


                validate();
                eliminarTabla();
                try {
                    verTabla(consulta.verProductoPorCodBolsa(textf));
                } catch (Exception ex) {
                    Logger.getLogger(ProductosUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                validate();
                this.updateUI();




                break;
            }

            case 2: {
                try {
                    validate();
                    eliminarTabla();
                    verTabla(consulta.verProductosPorNombre(textf));
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

            case 3: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verProductosPorPais(textf));
                    validate();
                    this.updateUI();


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
             this.updateUI();
            
            
             } catch (Exception ex) {
             Logger.getLogger(ProductosUI.class.getName()).log(Level.SEVERE, null, ex);
             }
            
             break;
             }*/

            /*  case 5: {
             try {

             validate();
             eliminarTabla();
             verTabla(consulta.verprod(textf));
             validate();
             this.updateUI();

             } catch (Exception e) {
             System.out.print("no cambia");
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
    private JScrollPane jScrollPane1;
//    private final JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDetalles;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
