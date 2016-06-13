/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Ivan C
 */
public class Opciones {

    public static int PORCENTAJE_MULTA;
    public static int DIAS_MULTA;

    public static void Opciones() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("config/opciones.ini");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int count = 0;
            while ((linea = br.readLine()) != null) {
                if (count == 0) {
                    Opciones.PORCENTAJE_MULTA = Integer.parseInt(linea);
                }
                if (count == 1) {
                    Opciones.DIAS_MULTA = Integer.parseInt(linea);
                }
                count++;
                System.out.println("dias multa: " + Opciones.DIAS_MULTA + " / porcentaje multa: "
                        + Opciones.PORCENTAJE_MULTA + "   count" + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    public static int getPorcentajemulta() {
        return PORCENTAJE_MULTA;
    }

    public static void setPorcentajemulta(int porcentajemulta) {
        Opciones.PORCENTAJE_MULTA = porcentajemulta;
        System.out.println("porcentaje "+Opciones.PORCENTAJE_MULTA);

        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("config/opciones.ini");
            pw = new PrintWriter(fichero);


            pw.println("" + porcentajemulta);
            pw.println("" + Opciones.DIAS_MULTA);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                JOptionPane.showMessageDialog(null, "Los Cambio no han sido guardados");
            }
        }
    }

    public static void actualizar() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File("config/opciones.ini");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int count = 0;
            while ((linea = br.readLine()) != null) {
                if (count == 0) {
                    Opciones.PORCENTAJE_MULTA = Integer.parseInt(linea);
                }
                if (count == 1) {
                    Opciones.DIAS_MULTA = Integer.parseInt(linea);
                }
                count++;
                System.out.println("dias multa: " + Opciones.DIAS_MULTA + " / porcentaje multa: "
                        + Opciones.PORCENTAJE_MULTA + "   count" + count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void setDiasmulta(int diasmulta) {
        Opciones.DIAS_MULTA = diasmulta;
        System.out.println("dias "+Opciones.PORCENTAJE_MULTA);
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("config/opciones.ini");
            pw = new PrintWriter(fichero);


            pw.println("" + Opciones.PORCENTAJE_MULTA);
            pw.println("" + diasmulta);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Los Cambio no han sido guardados");
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
