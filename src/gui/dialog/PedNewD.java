/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReserNewD.java
 *
 *
 */
package gui.dialog;

import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import negocio.Cliente;
import negocio.Consultas;
import negocio.Control;
import util.Factura;
import negocio.Pedido;
import negocio.PedidoProducto;
import negocio.Producto;
import negocio.Reserva;
import negocio.SingleControl;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import util.GestorTabla;

/**
 *
 * @author Ivan C
 */
public class PedNewD extends javax.swing.JDialog {

    /**
     * Creates new form ReserNewD
     */
    private Consultas consulta;
    private Producto product;
    private ArrayList<Producto> products;
    private Reserva reser;
    private Cliente cliente;
    private DefaultTableModel modelo;
    private JTable tabla;
    private GestorTabla gTabla;
    private ArrayList<Producto> arraylocal;
    private ArrayList<String> cols;
    private ArrayList<String> row;
    private ArrayList<ArrayList> rows;
    private int anchocolumna[];
    private String nombrecolumna[];
    private int widthtable;
    JScrollPane pane;
    private double total;
    private ClientesConsultaUI wres;
    private ArrayList<Cliente> comodinCliente;
    private Factura factura;
    private Pedido pedido;
    private PedidoProducto pedProd;
    private Control control;
    private double subtotal;
    private double descuento;
    private double tmpresabono;
    SingleControl single;
    Reserva reservaPrincipal = new Reserva();

    public PedNewD() {
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        tmpresabono = 0;
        single = new SingleControl();
        control = single.sesion();
        factura = new Factura();
        pedido = new Pedido();
        pedProd = new PedidoProducto();

        anchocolumna = new int[8];
        nombrecolumna = new String[8];
        widthtable = 3;

        anchocolumna[0] = 60;
        anchocolumna[1] = 120;
        anchocolumna[2] = 60;
        anchocolumna[3] = 100;
        anchocolumna[4] = 120;
        anchocolumna[5] = 100;
        anchocolumna[6] = 60;
        anchocolumna[7] = 60;


        for (int i = 0; i < anchocolumna.length; i++) {
            widthtable = widthtable + anchocolumna[i];
        }

        nombrecolumna[0] = "Codigo";
        nombrecolumna[1] = "Nombre";
        nombrecolumna[2] = "Color";
        nombrecolumna[3] = "Estado";
        nombrecolumna[4] = "Talla";
        nombrecolumna[5] = "Sexo";
        nombrecolumna[6] = "Pais";
        nombrecolumna[7] = "Precio";




        consulta = new Consultas();
        arraylocal = new ArrayList<Producto>();

        product = new Producto();
        products = new ArrayList<Producto>();
        cliente = new Cliente();
        initComponents();
        row = new ArrayList<String>();
        rows = new ArrayList<ArrayList>();
        verTabla(arraylocal);

    }

    public PedNewD(Reserva res) throws Exception {
        this();


        selBoton.setVisible(false);
        nuevoBoton.setVisible(false);
        this.reservaPrincipal = res;

        cliente = consulta.verClientePorCedula(reservaPrincipal.getIdCliente()).get(0);
        products = consulta.verProductosPorReserva(reservaPrincipal.getId());
        tmpresabono = reservaPrincipal.getAbono();
        abonoField.setText(tmpresabono + "");
        total = reservaPrincipal.getTotal();
        totalLabel1.setText("" + total);
        clienteLabel.setText(cliente.getNombre() + " " + cliente.getApellido());
        eliminarTabla();
        verTabla(products);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonAdd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        selBoton = new javax.swing.JButton();
        nuevoBoton = new javax.swing.JButton();
        clienteLabel = new javax.swing.JLabel();
        jButtContinuar = new javax.swing.JButton();
        tituloTotal = new javax.swing.JLabel();
        totalLabel1 = new javax.swing.JLabel();
        jButtonDelete = new javax.swing.JButton();
        jDatedevolucion = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextDescuentor = new javax.swing.JTextField();
        tituloTotal1 = new javax.swing.JLabel();
        subtotalLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        abonoField = new javax.swing.JTextField();
        tituloTotal2 = new javax.swing.JLabel();
        jTextFieldPuntos = new javax.swing.JTextField();
        jLabelpuntos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButtonAdd.setText("Agregar");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Nuevo Pedido");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Cliente:");

        selBoton.setText("Seleccionar");
        selBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selBotonActionPerformed(evt);
            }
        });

        nuevoBoton.setText("Nuevo");
        nuevoBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoBotonActionPerformed(evt);
            }
        });

        jButtContinuar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtContinuar.setText("Siguiente");
        jButtContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtContinuarActionPerformed(evt);
            }
        });

        tituloTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tituloTotal.setText("Total");

        jButtonDelete.setText("Eliminar");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Fecha Devolucion:");

        jLabel3.setText("Descuento");

        jTextDescuentor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextDescuentorActionPerformed(evt);
            }
        });
        jTextDescuentor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextDescuentorKeyReleased(evt);
            }
        });

        tituloTotal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tituloTotal1.setText("Subtotal");

        subtotalLabel.setText("$0");

        totalLabel.setText("$0");

        abonoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                abonoFieldFocusLost(evt);
            }
        });
        abonoField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                abonoFieldKeyTyped(evt);
            }
        });

        tituloTotal2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tituloTotal2.setText("Deposito");

        jTextFieldPuntos.setText("0");

        jLabelpuntos.setText("Puntos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addGap(27, 27, 27)
                        .addComponent(jButtonDelete))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(nuevoBoton)
                                            .addComponent(jLabel6)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addComponent(clienteLabel))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(selBoton)
                                .addGap(93, 93, 93)))
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDatedevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtContinuar))
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelpuntos)
                    .addComponent(tituloTotal2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tituloTotal1)
                            .addComponent(tituloTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(subtotalLabel)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextDescuentor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(totalLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldPuntos, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(abonoField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(clienteLabel))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDatedevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(selBoton)
                                .addComponent(nuevoBoton)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(71, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextDescuentor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tituloTotal1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tituloTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelpuntos))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(subtotalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(totalLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(98, 98, 98)
                        .addComponent(tituloTotal2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(abonoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtContinuar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        //jDateEntrega.setDateFormatString("   dd / MMMMM / yyyy ");

        /* DateFormat dateFormat = new SimpleDateFormat("   dd / MMMMM / yyyy ");
        java.util.Date fechaActual = new java.util.Date();
        jDateEntrega.setDate(cambiarFecha(fechaActual, 1, 0, 0));
        */
        jDatedevolucion.setDateFormatString("   dd / MMMMM / yyyy ");
        DateFormat dateFormat2 = new SimpleDateFormat("   dd / MMMMM / yyyy ");
        java.util.Date fechaActual2 = new java.util.Date();
        jDatedevolucion.setDate(cambiarFecha(fechaActual2, 1, 0, 0));
    }// </editor-fold>//GEN-END:initComponents

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
            row.add(prod.getNombre());
            row.add(prod.getColor());
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

        tabla.setMaximumSize(new Dimension(920, 400));
        tabla.setRowMargin(3);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        for (int i = 0; i < anchocolumna.length; i++) {
            if (i == 5) {
                i++;
            }

            tabla.getColumnModel().getColumn(i).setPreferredWidth(anchocolumna[i]);

        }



        //     pane.setSize(450, 400);

        if (widthtable < 700) {
            pane.setViewportView(tabla);
            pane.setSize(widthtable, 400);


            pane.setBounds(25, 140, widthtable, 300);
        } else {
            pane.setViewportView(tabla);
            pane.setSize(700, 400);


            pane.setBounds(25, 140, 700, 300);
        }
        //jScrollPane1 = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //pane.setViewportView(tabla);

        // pane.setBounds(35, 140, 658, 300);
        pane.setBorder(BorderFactory.createEmptyBorder());
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

    private void actualizarCliente(boolean flag) {
        if (cliente != null) {
            clienteLabel.setText(cliente.getNombre() + " " + cliente.getApellido());
            flag = true;
            nuevoBoton.setVisible(false);
            selBoton.setVisible(false);
        }
    }

    public void setCliente(Cliente c) {
        cliente = c;
    }

private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
    try {
        product = new Producto();
        PedNewAddD wprodadd = new PedNewAddD(this.product, this.products, this);
        wprodadd.setLocationRelativeTo(null);
        wprodadd.setVisible(true);

    } catch (Exception ex) {
        Logger.getLogger(PedNewD.class.getName()).log(Level.SEVERE, null, ex);
        System.out.print("No genera ventana de agregar producto");
    }



}//GEN-LAST:event_jButtonAddActionPerformed

private void selBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selBotonActionPerformed
// TODO add your handling code here:
    JDialog aux = new JDialog();
    boolean actualizado = false;

    try {
        wres = new ClientesConsultaUI(0, "", aux, clienteLabel, this, null);
        wres.setSize(400, 400);

        // wres.setVisible(true);
        aux.add(wres);
        aux.setMinimumSize(new Dimension(971, 710));
        aux.setLocationRelativeTo(null);
        aux.setVisible(true);
        aux.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);



        //this.update(this.getGraphics());
    } catch (Exception ex) {
        Logger.getLogger(PedNewD.class.getName()).log(Level.SEVERE, null, ex);
    }

}//GEN-LAST:event_selBotonActionPerformed

    public void pintarTotal() {
        subtotal = 0;
        for (Iterator<Producto> it = products.iterator(); it.hasNext();) {
            Producto producto = it.next();
            subtotal = subtotal + producto.getPrecio();
        }
        subtotalLabel.setText("$ " + subtotal);
        total = subtotal - (subtotal * (descuento * 0.01));
        totalLabel.setText("$ " + total);
    }

    private java.util.Date cambiarFecha(java.util.Date tmpdate, int dias, int meses, int anos) {

        String fechainicial;
        String fechafinal;
        int tmpano;
        int tmpmes;
        int tmpdia;

        //cambiar dia y almacenarlo en tmpdia
        DateFormat dateFormat2 = new SimpleDateFormat("dd");
        fechainicial = dateFormat2.format(tmpdate);
        tmpdia = Integer.parseInt(fechainicial);
        tmpdia = tmpdia + dias;

        //cambiar mes y almacenarlo en tmpmes
        dateFormat2 = new SimpleDateFormat("MM");
        fechainicial = dateFormat2.format(tmpdate);
        tmpmes = Integer.parseInt(fechainicial);
        tmpmes = tmpmes + meses;

        //cambiar ano y almacenarlo en tmpano
        dateFormat2 = new SimpleDateFormat("yyyy");
        fechainicial = dateFormat2.format(tmpdate);
        tmpano = Integer.parseInt(fechainicial);
        tmpano = tmpano + anos;


        //recompilar y devolver fecha completa en formato date
        fechafinal = "" + tmpdia + "/" + tmpmes + "/" + tmpano;
        System.out.print("" + fechafinal);
        dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fechanueva = new java.util.Date();
        try {
            fechanueva = dateFormat2.parse(fechafinal);
        } catch (ParseException ex) {
            Logger.getLogger(ReserConsultaUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, " error al guardar");
        }


        return fechanueva;

    }

    public void setReservaPincipal(Reserva reservaPincipal) {
        this.reservaPrincipal = reservaPincipal;
    }

private void nuevoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoBotonActionPerformed
// TODO add your handling code here:
    ClientNewD newCliente = new ClientNewD(clienteLabel, this, null);
    newCliente.setVisible(true);
}//GEN-LAST:event_nuevoBotonActionPerformed

private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
// TODO add your handling code here:
    if (tabla.getSelectedRowCount() >= 1) {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este producto del pedido?");
        if (opcion == JOptionPane.YES_OPTION) {
            product = new Producto();
            for (Iterator<Producto> it = products.iterator(); it.hasNext();) {
                Producto producto = it.next();
                if (tabla.getValueAt(tabla.getSelectedRow(), 0).equals(producto.getCod_individual())) {
                    product = producto;
                }
            }
            products.remove(product);
            pintarTotal();
            eliminarTabla();
            verTabla(products);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Ningún elemento seleccionado");
    }
}//GEN-LAST:event_jButtonDeleteActionPerformed

private void jButtContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtContinuarActionPerformed
// TODO add your handling code here:
    if (cliente.getCedula() != null) {
        try {
            if (reservaPrincipal != null) {
                control.desactivarReserva(reservaPrincipal);
            }
            String cod;
            cod = consulta.obtenerCodigoPedido() + "";
            System.out.println("user: " + control.getUsuario().getUser());
            pedido.setId(cod);
            pedido.setUsuario(control.getUsuario().getUser());
            pedido.setIdCliente(cliente.getCedula());
            pedido.setNumProductos(products.size());


            java.util.Date fecha = new java.util.Date();
            java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
            pedido.setEmision(sqlfecha);
            pedido.setFechamod(sqlfecha);

            // fecha = jDateEntrega.getDate();
            // sqlfecha = new java.sql.Date(fecha.getTime());
            //pedido.setFechaentrega(sqlfecha);

            fecha = jDatedevolucion.getDate();
            sqlfecha = new java.sql.Date(fecha.getTime());
            pedido.setFechadevolucion(sqlfecha);

            if (jTextDescuentor.getText().equals("")) {
                descuento = 0;
            } else {
                descuento = Double.parseDouble(jTextDescuentor.getText() + "");
            }


            pedido.setSubtotal(subtotal);

            total = subtotal - (subtotal * (descuento * 0.01));
            pedido.setDescuento(subtotal * (descuento * 0.01));

            if (abonoField.getText().equals("")) {
                abonoField.setText("0");
            }

            pedido.setPagado(Double.parseDouble(abonoField.getText()));
            pedido.setSaldo(Double.parseDouble(abonoField.getText()) - (double) total);
            pedido.setTotal(total);

            try {
                pedido.setId(consulta.obtenerCodigoPedido() + "");
                //Registro Productos
                ArrayList<Producto> conReserva = new ArrayList<Producto>();

                for (Iterator<Producto> it = products.iterator(); it.hasNext();) {
                    Producto producto = it.next();
                    System.out.println("id: " + producto.getCod_individual());
                    ArrayList<Reserva> resvs = new ArrayList<Reserva>();
                    resvs = consulta.verReservaPorProducto(producto.getCod_individual());
                    for (Iterator<Reserva> it1 = resvs.iterator(); it1.hasNext();) {
                        Reserva reserva = it1.next();
                        if (reserva.getActivo() == 1) {
                            if (reserva.getFechaReservaIni().equals(pedido.getEmision())) {
                                conReserva.add(producto);
                            }
                            if (reserva.getDiasReserva().equals(pedido.getEmision())) {
                                conReserva.add(producto);
                            }
                            if (reserva.getFechaReservaIni().before(pedido.getEmision())
                                    && reserva.getDiasReserva().after(pedido.getEmision())) {
                                conReserva.add(producto);
                            }
                            if (reserva.getFechaReservaIni().equals(pedido.getFechaentrega())) {
                                conReserva.add(producto);
                            }
                            if (reserva.getDiasReserva().equals(pedido.getFechaentrega())) {
                                conReserva.add(producto);
                            }
                            if (reserva.getFechaReservaIni().before(pedido.getFechaentrega())
                                    && reserva.getDiasReserva().after(pedido.getFechaentrega())) {
                                conReserva.add(producto);
                            }
                            if (reserva.getFechaReservaIni().before(pedido.getFechaentrega())
                                    && reserva.getFechaReservaIni().after(pedido.getEmision())) {
                                conReserva.add(producto);
                            }
                            if (reserva.getDiasReserva().before(pedido.getFechaentrega())
                                    && reserva.getDiasReserva().after(pedido.getEmision())) {
                                conReserva.add(producto);
                            }
                        }
                    }
                }
                if (conReserva.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Algunos productos ya tienen reserva para este día");
                } else {
                    cliente.setPuntos(cliente.getPuntos()+Integer.parseInt(jTextFieldPuntos.getText()));
                    control.registrarPedido(pedido, products,cliente);
                    if (pedido.getSaldo() == 0) {
                        pedido.setPendiente_pago(0);
                    }
                    pedido.setPendiente_entrega(1);
                    control.actualizarPedido(pedido);
                    //----de acá
                    JOptionPane.showMessageDialog(this, "Registro Satisfactorio");

                    //Generar factura
                    Factura datasource = new Factura();
                    datasource.setProductos(products);
                    datasource.setCliente(cliente);
                    datasource.setPedido(pedido);
                    net.sf.jasperreports.engine.JasperReport reporte = JasperCompileManager.compileReport("Facturas/report1.jrxml");
                    net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);

                    File archivo = new File("Factura" + pedido.getId() + ".pdf");
                    JRExporter exporter = new JRPdfExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2PDF_2.pdf"));
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(archivo.getName()));
                    exporter.exportReport();

                    this.dispose();

                    try {
                        File path = new File("Factura" + pedido.getId() + ".pdf");
                        Desktop.getDesktop().open(path);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                Logger.getLogger(PedNewD.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Registro erroneo, intente de nuevo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PedNewD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PedNewD.class.getName()).log(Level.SEVERE, null, ex);
        }

    } else {
        JOptionPane.showMessageDialog(this, "Ningún cliente seleccionado");
    }
}//GEN-LAST:event_jButtContinuarActionPerformed

    public Date getFechaEmision() {
        java.util.Date fecha = new java.util.Date();
        java.sql.Date sqlfecha = new java.sql.Date(fecha.getTime());
        return sqlfecha;
    }

private void jTextDescuentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDescuentoKeyReleased
//ERROR DE FORM by NW
}//GEN-LAST:event_jTextDescuentoKeyReleased

    private void jTextDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
//ERROR DE FORM by NW
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextDescuentorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextDescuentorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextDescuentorActionPerformed

    private void jTextDescuentorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextDescuentorKeyReleased
        if (jTextDescuentor.getText().equals("")) {
            descuento = 0;
        } else {
            descuento = Integer.parseInt(jTextDescuentor.getText() + "");
        }
        //  System.out.println("descuento: " + descuento);
        if (descuento > 99) {
            descuento = 99;
            jTextDescuentor.setText(descuento + "");
        }
        if (descuento < 0) {
            descuento = 0;
            jTextDescuentor.setText(descuento + "");
        }

        pedido.setDescuento(subtotal * (descuento * 0.01));
        pintarTotal();
    }//GEN-LAST:event_jTextDescuentorKeyReleased

private void abonoFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_abonoFieldKeyTyped
// TODO add your handling code here:
    char caracter = evt.getKeyChar();
    if (((caracter < '0')
            || (caracter > '9'))
            && (caracter != evt.VK_BACK_SPACE)) {
        evt.consume();
    }
}//GEN-LAST:event_abonoFieldKeyTyped

    private void abonoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_abonoFieldFocusLost
        if (Double.parseDouble(abonoField.getText()) < tmpresabono) {
            abonoField.setText(tmpresabono + "");
            JOptionPane.showMessageDialog(null, "El abono no puede ser menor que el abono de la reserva convertida");
        }
    }//GEN-LAST:event_abonoFieldFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField abonoField;
    private javax.swing.JLabel clienteLabel;
    private javax.swing.JButton jButtContinuar;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private com.toedter.calendar.JDateChooser jDatedevolucion;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelpuntos;
    private javax.swing.JTextField jTextDescuentor;
    private javax.swing.JTextField jTextFieldPuntos;
    private javax.swing.JButton nuevoBoton;
    private javax.swing.JButton selBoton;
    private javax.swing.JLabel subtotalLabel;
    private javax.swing.JLabel tituloTotal;
    private javax.swing.JLabel tituloTotal1;
    private javax.swing.JLabel tituloTotal2;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel totalLabel1;
    // End of variables declaration//GEN-END:variables
}