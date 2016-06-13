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
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import negocio.Cliente;
import negocio.Consultas;
import negocio.Inserciones;
import negocio.Producto;
import negocio.Reserva;
import negocio.ReservaProducto;
import util.GestorTabla;
import java.sql.Date;
import java.util.Calendar;
import javax.swing.JDialog;
import javax.swing.JLabel;
import negocio.Control;
import negocio.SingleControl;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import util.CompReserva;
import util.Comprobante;
import util.Factura;

/**
 *
 * @author Ivan C
 */
public class ReserNewD extends javax.swing.JDialog {

    /**
     * Creates new form ReserNewD
     */
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
    private Consultas consulta;
    private Inserciones insercion;
    private Control control;
    SingleControl single;
    JScrollPane pane;

    public ReserNewD() {
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        single = new SingleControl();
        control = single.sesion();

        product = new Producto();
        products = new ArrayList<Producto>();
        cliente = new Cliente();
        anchocolumna = new int[8];
        nombrecolumna = new String[8];

        anchocolumna[0] = 60;
        anchocolumna[1] = 120;
        anchocolumna[2] = 60;
        anchocolumna[3] = 100;
        anchocolumna[4] = 120;
        anchocolumna[5] = 100;
        anchocolumna[6] = 60;
        anchocolumna[7] = 60;

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
        row = new ArrayList<String>();
        rows = new ArrayList<ArrayList>();
        insercion = new Inserciones();
        verTabla(arraylocal);
        initComponents();

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



        pane.setSize(500, 400);
        //jScrollPane1 = new JScrollPane(tabla,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        //JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setViewportView(tabla);

        pane.setBounds(35, 130, 658, 240);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        selBoton = new javax.swing.JButton();
        nuevoBoton = new javax.swing.JButton();
        clienteLabel = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dias = new javax.swing.JComboBox();
        jTextAbono = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabeltotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Reserva Nueva");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Cliente");

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

        clienteLabel.setText(" ");

        botonGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        botonGuardar.setText("Guardar");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });

        jButton1.setText("Eliminar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Fecha Reserva");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Abono:");

        jLabel3.setText("Tiempo reserva:");

        jLabel4.setText("Días");

        dias.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3" }));

        jTextAbono.setText("0");
        jTextAbono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextAbonoActionPerformed(evt);
            }
        });
        jTextAbono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextAbonoFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("TOTAL: ");

        jLabeltotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabeltotal.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(clienteLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(selBoton)
                                .addGap(18, 18, 18)
                                .addComponent(nuevoBoton)))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabeltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextAbono, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selBoton)
                            .addComponent(jLabel7)
                            .addComponent(nuevoBoton)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(clienteLabel)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(dias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jButton2)
                            .addComponent(jButton1)
                            .addComponent(jTextAbono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabeltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(botonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jDateChooser1.setDateFormatString("   dd / MMMMM / yyyy ");

        DateFormat dateFormat = new SimpleDateFormat("   dd / MMMMM / yyyy ");
        java.util.Date fechaActual = new java.util.Date();
        jDateChooser1.setDate(cambiarFecha(fechaActual, 1, 0, 0));
    }// </editor-fold>//GEN-END:initComponents

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

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
        ReserNewAddD wprodadd = new ReserNewAddD(this.product, this.products, this);
        wprodadd.setLocationRelativeTo(null);
        wprodadd.setVisible(true);

    } catch (Exception ex) {
        Logger.getLogger(ReserNewD.class.getName()).log(Level.SEVERE, null, ex);
        System.out.print("No genera ventana de agregar producto");
    }



}//GEN-LAST:event_jButton2ActionPerformed

private void selBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selBotonActionPerformed
// TODO add your handling code here:
    ClientesConsultaUI wres;
    JDialog aux = new JDialog();
    boolean actualizado = false;
    try {
        wres = new ClientesConsultaUI(0, "", aux, this.clienteLabel, null, this);
        wres.setSize(400, 400);
        //wres.setVisible(true);

        aux.add(wres);
        aux.setMinimumSize(new Dimension(971, 710));
        aux.setLocationRelativeTo(null);
        aux.setVisible(true);

        aux.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        this.update(this.getGraphics());

    } catch (Exception ex) {
        Logger.getLogger(ReserNewD.class.getName()).log(Level.SEVERE, null, ex);
    }

}//GEN-LAST:event_selBotonActionPerformed

private void nuevoBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoBotonActionPerformed
// TODO add your handling code here:
    ClientNewD newCliente = new ClientNewD(clienteLabel, null, this);
    newCliente.setVisible(true);
}//GEN-LAST:event_nuevoBotonActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
    if (tabla.getSelectedRowCount() >= 1) {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este producto de la reserva?");
        if (opcion == JOptionPane.YES_OPTION) {
            product = new Producto();
            for (Iterator<Producto> it = products.iterator(); it.hasNext();) {
                Producto producto = it.next();
                if (tabla.getValueAt(tabla.getSelectedRow(), 0).equals(producto.getCod_individual())) {
                    product = producto;
                }
            }
            products.remove(product);
            eliminarTabla();
            verTabla(products);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Ningún elemento seleccionado");
    }
}//GEN-LAST:event_jButton1ActionPerformed

private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed

    ReservaProducto reprod;
    ArrayList<ReservaProducto> resprods = new ArrayList<ReservaProducto>();
    Reserva res = new Reserva();
    int suma = 0;
    String cod;

    try {
        if (cliente.getCedula() != null) {
            if (products.size() > 0) {
                cod = consulta.obtenerCodigoReserva() + "";

                for (Iterator<Producto> it = products.iterator(); it.hasNext();) {
                    Producto produc = it.next();

                    //crear y agregar en array Reservas de Productos
                    reprod = new ReservaProducto();
                    reprod.setIdProducto(produc.getCod_individual());
                    reprod.setIdReserva(cod);
                    resprods.add(reprod);
                    //suma de precios para total
                    suma = suma + produc.getPrecio();

                }

                //Agregar Datos a Reserva
                res.setIdCliente(cliente.getCedula());
                res.setId(cod);
                res.setTotal(suma);
                res.setAbono(Double.parseDouble(jTextAbono.getText()));
                res.setPendiente(suma - (Double.parseDouble(jTextAbono.getText())));

                res.setUsuario(control.getUsuario().getUser());
                Calendar fecha = Calendar.getInstance();
                fecha.setTime(new Date(jDateChooser1.getDate().getTime()));
                fecha.add(Calendar.DAY_OF_MONTH, Integer.parseInt(dias.getSelectedItem().toString()));
                java.util.Date rango = fecha.getTime();
                Date fechaLimite = new Date(rango.getTime());
                res.setDiasReserva(fechaLimite);
                res.setActivo(1);
                Date sqlfechare = new Date(jDateChooser1.getDate().getTime());
                res.setFechaReservaini(sqlfechare);

                System.out.println(jTextAbono.getText() + " codREserva: " + cod);

                java.util.Date fechaActual = new java.util.Date();
                Date sqlfechaActual = new Date(fechaActual.getTime());
                res.setFechaRealizada(sqlfechaActual);
                res.setFechaReservafin(new Date(0, 0, 0));
                res.setFechamod(sqlfechaActual);


                ArrayList<Producto> conReserva = new ArrayList<Producto>();

                for (Iterator<Producto> it = products.iterator(); it.hasNext();) {
                    Producto producto = it.next();
                    System.out.println("id: " + producto.getCod_individual());
                    ArrayList<Reserva> resvs = new ArrayList<Reserva>();
                    resvs = consulta.verReservaPorProducto(producto.getCod_individual());
                    for (Iterator<Reserva> it1 = resvs.iterator(); it1.hasNext();) {
                        Reserva reserva = it1.next();
                        if (reserva.getActivo() == 1) {
                            if (reserva.getFechaReservaIni().equals(sqlfechare)) {
                                conReserva.add(producto);
                            }
                            if (reserva.getDiasReserva().equals(sqlfechare)) {
                                conReserva.add(producto);
                            }
                            if (reserva.getFechaReservaIni().before(sqlfechare)
                                    && reserva.getDiasReserva().after(sqlfechare)) {
                                conReserva.add(producto);
                            }
                            if (reserva.getFechaReservaIni().equals(fechaLimite)) {
                                conReserva.add(producto);
                            }
                            if (reserva.getDiasReserva().equals(fechaLimite)) {
                                conReserva.add(producto);
                            }
                            if (reserva.getFechaReservaIni().before(fechaLimite)
                                    && reserva.getDiasReserva().after(fechaLimite)) {
                                conReserva.add(producto);
                            }
                            if (reserva.getFechaReservaIni().before(fechaLimite)
                                    && reserva.getFechaReservaIni().after(sqlfechare)) {
                                conReserva.add(producto);
                            }
                            if (reserva.getDiasReserva().before(fechaLimite)
                                    && reserva.getDiasReserva().after(sqlfechare)) {
                                conReserva.add(producto);
                            }
                        }
                    }
                }

                if (conReserva.size() > 0) {
                    JOptionPane.showMessageDialog(this, "Algunos productos ya tienen reserva para este día");
                } else {
                    control.registrarReserva(res, products);
//                    CompReserva datasource = new CompReserva();
//                    datasource.setProductos(products);
//                    datasource.setCliente(cliente);
//                    datasource.setPedido(res);
//                    net.sf.jasperreports.engine.JasperReport reporte = JasperCompileManager.compileReport("Facturas/comproban.jrxml");
//                    net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, datasource);
//
//                    File archivo = new File("Reserva" + res.getId() + ".pdf");
//                    JRExporter exporter = new JRPdfExporter();
//                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
////                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reporte2PDF_2.pdf"));
//                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(archivo.getName()));
//                    exporter.exportReport();
//                    
//                    try {
//                        File path = new File("Reserva" + res.getId() + ".pdf");
//                        Desktop.getDesktop().open(path);
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
                    
                    JOptionPane.showMessageDialog(this, "Datos Guardados Correctamente");
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se han agregado productos a la reserva");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Ningún cliente seleccionado");
        }



//        insercion.insertarReserva(res);
//        for (Iterator<ReservaProducto> it = resprods.iterator(); it.hasNext();) {
//            ReservaProducto pr = it.next();
//            insercion.insertarReservaProducto(pr);
//
//        }




    } catch (Exception ex) {
        Logger.getLogger(ReserNewD.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("No genera Codigo de reserva nuevo");
    }



}//GEN-LAST:event_botonGuardarActionPerformed

    private void jTextAbonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextAbonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextAbonoActionPerformed

    private void jTextAbonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextAbonoFocusLost
        double sumatmp = 0;
        for (Iterator<Producto> it = products.iterator(); it.hasNext();) {
            Producto produc = it.next();

            //crear y agregar en array Reservas de Productos

            //suma de precios para total
            sumatmp = sumatmp + produc.getPrecio();

        }

        if (Double.parseDouble(jTextAbono.getText()) > sumatmp) {
            jTextAbono.setText("0");
            JOptionPane.showMessageDialog(null, "El abono no puede ser mayor al total del pedido, "
                    + "\npor favor digite el abono despues de\nagregar los productos");
        }
    }//GEN-LAST:event_jTextAbonoFocusLost

    private int buscarFila(String id) {
        int fila = 0;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            if (tabla.getValueAt(i, 0).equals(id)) {
                fila = i;
            }
        }
        return fila;
    }

    public Date getFechaReserva() {
        Date sqlfechare = new Date(jDateChooser1.getDate().getTime());
        return sqlfechare;
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
    
    public void setTotal( double tot){
        this.jLabeltotal.setText(tot+"");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel clienteLabel;
    private javax.swing.JComboBox dias;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabeltotal;
    private javax.swing.JTextField jTextAbono;
    private javax.swing.JButton nuevoBoton;
    private javax.swing.JButton selBoton;
    // End of variables declaration//GEN-END:variables
}
