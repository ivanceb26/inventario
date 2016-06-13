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

import gui.InicioSesion;
import java.awt.Dialog;
import java.awt.Dimension;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import negocio.Cliente;
import negocio.Consultas;
import negocio.Control;
import negocio.Producto;
import negocio.Reserva;
import negocio.ReservaProducto;
import negocio.SingleControl;
import util.GestorTabla;

/**
 *
 * @author Ivan C
 */
public class ReserConsultaDetallesD extends javax.swing.JDialog {

    /** Creates new form Inicio */
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
    private String codigoreserva;
    private String cedulacliente;
    private String fechareserva;
    private String fecharealizada;
    private String usuariovendedor;
    private String nomCliente;
    private int viewwidthtable;
    JScrollPane pane;
    Producto produ;
    ArrayList<Producto> produs;
    PedNewD auxFrame;
    Control control;
    SingleControl single;
    Reserva reserva;

    public ReserConsultaDetallesD(String codres) throws Exception {
        initComponents();
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        single = new SingleControl();
        control = single.sesion();
        reserva = control.consultarReservaPorID(codres).get(0);
        codResLabel.setText(reserva.getId());
        codigoreserva = codres;
        cedulacliente = reserva.getIdCliente();
        fechareserva = reserva.getFechaReservaIni().toString();
        fecharealizada = reserva.getFechaRealizada().toString();
        usuariovendedor = control.consultarUsuarioPorUser(reserva.getUsuario()).get(0).getNombre() + ""
                + " " + control.consultarUsuarioPorUser(reserva.getUsuario()).get(0).getApellido();
        nomCliente = control.consultarClientePorCedula(reserva.getIdCliente()).get(0).getNombre() + ""
                + " " + control.consultarClientePorCedula(reserva.getIdCliente()).get(0).getApellido();


        viewwidthtable = 880;
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

        jLaCedula.setText(cedulacliente);
        codResLabel.setText(codigoreserva);
        jLafechareserva.setText(fechareserva);
        jLafecharealiza.setText(fecharealizada);
        jLaususariovende.setText(usuariovendedor);
        nomClienteLabel.setText(nomCliente);
        jLafechamod.setText(reserva.getFechamod().toString());
        produs = control.consultarPorductosReserva(codres);
        this.eliminarTabla();
        this.verTabla(produs);

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

                pane.setBounds(38, 220, widthtable, 450);
            } else {
                pane.setSize(viewwidthtable, 470);
                pane.setViewportView(tabla);

                pane.setBounds(38, 220, viewwidthtable, 450);
            }

        } else {
            pane.setSize(viewwidthtable, 470);
            pane.setViewportView(tabla);

            pane.setBounds(38, 220, viewwidthtable, 450);


        }
//

        pane.setBorder(BorderFactory.createEmptyBorder());
        //jScrollPane1 = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        add(pane);

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

        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nomClienteLabel = new javax.swing.JLabel();
        jLaCedula = new javax.swing.JLabel();
        jLafechareserva = new javax.swing.JLabel();
        jLaususariovende = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLafechamod = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        codResLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLafecharealiza = new javax.swing.JLabel();
        pedidoBoton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(971, 710));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setText("Productos de Reserva");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Cliente>");

        jLabel4.setText("Nombre: ");

        jLabel5.setText("Cedula: ");

        nomClienteLabel.setText("|");

        jLaCedula.setText("|");

        jLafechareserva.setText("|");

        jLaususariovende.setText("|");

        jLabel10.setText("Vendedor: ");

        jLabel11.setText("Fecha: ");

        jLabel12.setText("Fecha Modificado:");

        jLafechamod.setText("|");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Reserva>");

        codResLabel.setText("|");

        jLabel16.setText("Codigo: ");

        jLabel17.setText("Fecha Realizada: ");

        jLafecharealiza.setText("|");

        pedidoBoton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pedidoBoton.setText("Realizar pedido");
        pedidoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidoBotonActionPerformed(evt);
            }
        });

        jButton2.setText(" Guardar Cambios");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar Reserva");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel16)))
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLafechareserva, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLafechamod, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jLabel10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLaususariovende, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(pedidoBoton)
                                            .addComponent(codResLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLaCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nomClienteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(204, 204, 204)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jLafecharealiza, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel2))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(nomClienteLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLaCedula))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLafechamod)
                            .addComponent(jLabel11)
                            .addComponent(jLabel17)
                            .addComponent(jLafecharealiza)
                            .addComponent(jLafechareserva, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(codResLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jLaususariovende)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 398, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(pedidoBoton)
                    .addComponent(jButton3))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (reserva.getActivo() == 1) {
        try {
            ReserNewAddD wprodadd = new ReserNewAddD(this.produ, this.produs, this);
            wprodadd.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(ReserNewD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.print("No genera ventana de agregar producto");
        }
    } else {
        JOptionPane.showMessageDialog(this, "La reserva no se encuentra activa");
    }

    // TODO add your handling code here:
}//GEN-LAST:event_jButton1ActionPerformed

private void pedidoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidoBotonActionPerformed
// TODO add your handling code here:
    if (reserva.getActivo() == 1) {
        try {
            PedNewD ped = new PedNewD(reserva);
            ped.setSize(805, 600);
            ped.setLocationRelativeTo(null);
            ped.setVisible(true);
            
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(ReserConsultaDetallesD.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        JOptionPane.showMessageDialog(this, "La reserva no se encuentra activa");
    }
}//GEN-LAST:event_pedidoBotonActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    if (reserva.getActivo() == 1) {
        try {
            // TODO add your handling code here:
            ReservaProducto reprod;
            ArrayList<ReservaProducto> resprods = new ArrayList<ReservaProducto>();

            int suma = 0;
            for (Iterator<Producto> it = produs.iterator(); it.hasNext();) {
                Producto produc = it.next();

                //crear y agregar en array Reservas de Productos
                reprod = new ReservaProducto();
                reprod.setIdProducto(produc.getCod_individual());
                reprod.setIdReserva(reserva.getId());
                resprods.add(reprod);
                System.out.println("Holaaaa" + produc.getCod_individual());
                //suma de precios para total
                suma = suma + produc.getPrecio();
            }

            reserva.setTotal(suma);
            reserva.setAbono(0);

            java.util.Date fechaActual = new java.util.Date();
            Date sqlfechaActual = new Date(fechaActual.getTime());

            reserva.setFechamod(sqlfechaActual);

            control.modificarReserva(reserva, produs);
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(ReserConsultaDetallesD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ReserConsultaDetallesD.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        JOptionPane.showMessageDialog(this, "La reserva no se encuentra activa");
    }
}//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            // TODO add your handling code here:
            int entregado = JOptionPane.showConfirmDialog(this, "¿Seguro que desea cancelar la reserva?");
            switch(entregado){
               case 0:
                   control.desactivarReserva(reserva);
                   JOptionPane.showMessageDialog(this, "Recerva cancelada correctamente");
                   this.dispose();
                   break;
               case 2:
                   break;    
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReserConsultaDetallesD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

// <editor-fold defaultstate="collapsed" desc="SEARCH METOD"> 
    private void search(int index, String textf) {


        switch (index) {

            case 0: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verProductosPorReserva(textf));
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

            default: {
                System.out.print("no cambia * no hay opcion");
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel codResLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLaCedula;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLafechamod;
    private javax.swing.JLabel jLafecharealiza;
    private javax.swing.JLabel jLafechareserva;
    private javax.swing.JLabel jLaususariovende;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel nomClienteLabel;
    private javax.swing.JButton pedidoBoton;
    // End of variables declaration//GEN-END:variables
}
