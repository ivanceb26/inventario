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
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import negocio.Pedido;
import negocio.Producto;
import negocio.SingleControl;
import util.GestorTabla;

/**
 *
 * @author Ivan C
 */
public class PedConsultaDetallesD extends javax.swing.JDialog {

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
    private String codigoPed;
    private String cedulacliente;
    private String fecha;
    private String fecharealizada;
    private String usuariovendedor;
    private Pedido pedido;
    JScrollPane pane;
    Producto produ;
    ArrayList<Producto> produs;
    PedNewD auxFrame;
    Control control;
    SingleControl single;
    private int viewwidthtable;
    int dias;

    public PedConsultaDetallesD(String cod) throws Exception {
        initComponents();
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        single = new SingleControl();
        control = single.sesion();

        pedido = control.consultarPedidoPorID(cod).get(0);

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
        search(0, "");

        jLaNombre.setText(control.consultarClientePorCedula(pedido.getIdCliente()).get(0).getNombre() + ""
                + " " + control.consultarClientePorCedula(pedido.getIdCliente()).get(0).getApellido());
        jLaCedula.setText(pedido.getIdCliente());
        jLaFechamod.setText(pedido.getFechamod().toString());
        jLausuariovende.setText(control.consultarUsuarioPorUser(pedido.getUsuario()).get(0).getNombre() + ""
                + " " + control.consultarUsuarioPorUser(pedido.getUsuario()).get(0).getApellido());
        jLacodigopedido.setText(cod);
        jLaFecha.setText("" + pedido.getEmision());
        jLaFechaDev.setText("" + pedido.getFechadevolucion());
        produs = control.consultarProductosPorPedido(cod);
        jSaldo.setText("" + pedido.getSaldo());
        jTotal.setText("" + pedido.getTotal());
        eliminarTabla();
        verTabla(produs);

        java.util.Date hoy = new Date();
        dias = (int) ((hoy.getTime() - pedido.getFechadevolucion().getTime()) / (1000 * 60 * 60 * 24));

        if ((pedido.getSubtotal() * 0.30) * dias <= pedido.getPagado()) {
            pedido.setMulta((pedido.getSubtotal() * 0.30) * dias);
        }

        pedido.setTotal(pedido.getSubtotal() + pedido.getMulta() - pedido.getDescuento());
        control.actualizarPedido(pedido);

        multaLabel.setText(""+pedido.getMulta());

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

        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLaNombre = new javax.swing.JLabel();
        jLaCedula = new javax.swing.JLabel();
        jLaFecha = new javax.swing.JLabel();
        jLausuariovende = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLaFechamod = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLacodigopedido = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLaFechaDev = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSaldo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTotal = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        multaLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(971, 710));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel2.setText("Productos de  Pedido");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Cliente>");

        jLabel4.setText("Nombre: ");

        jLabel5.setText("Cedula: ");

        jLaNombre.setText("|");

        jLaCedula.setText("|");

        jLaFecha.setText("|");

        jLausuariovende.setText("|");

        jLabel10.setText("Vendedor: ");

        jLabel11.setText("Fecha Emisión: ");

        jLabel12.setText("Fecha Modificado:");

        jLaFechamod.setText("|");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Pedido>");

        jLacodigopedido.setText("|");

        jLabel16.setText("Codigo: ");

        jLabel1.setText("Fecha devolución:");

        jLaFechaDev.setText("|");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Saldo");

        jSaldo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jSaldo.setText("|");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Total");

        jTotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTotal.setText("|");

        jButton2.setText("Opciones");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Multa");

        multaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        multaLabel.setText("|");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLaCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(123, 123, 123)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLacodigopedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLaFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLaFechamod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLausuariovende, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLaFechaDev, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2)
                                .addGap(58, 58, 58))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(multaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(133, Short.MAX_VALUE))
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
                                .addComponent(jLaNombre)
                                .addComponent(jLabel4))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(9, 9, 9)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLaCedula)
                            .addComponent(jLabel5))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLaFechamod)
                            .addComponent(jLabel11)
                            .addComponent(jLaFecha)
                            .addComponent(jLabel1)
                            .addComponent(jLaFechaDev))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jSaldo))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(jLacodigopedido, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(jLausuariovende))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(multaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            PedOpciones vent = new PedOpciones(pedido.getId());
            vent.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(PedConsultaDetallesD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

// <editor-fold defaultstate="collapsed" desc="SEARCH METOD"> 
    private void search(int index, String textf) {


        switch (index) {

            case 0: {
                try {

                    validate();
                    eliminarTabla();
                    verTabla(consulta.verProductosDePedido(textf));
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

    private void eliminarTabla() {

        try {
            pane.remove(tabla);
            this.remove(pane);
        } catch (Exception e) {
            System.out.print("no elimina tabla");
        }


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLaCedula;
    private javax.swing.JLabel jLaFecha;
    private javax.swing.JLabel jLaFechaDev;
    private javax.swing.JLabel jLaFechamod;
    private javax.swing.JLabel jLaNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLacodigopedido;
    private javax.swing.JLabel jLausuariovende;
    private javax.swing.JLabel jSaldo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jTotal;
    private javax.swing.JLabel multaLabel;
    // End of variables declaration//GEN-END:variables
}
