/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import javax.print.PrintService;
import sun.print.PSPrinterJob;

/**
 *
 * @author Wilson Salamanca
 */
public class Impresora {
    
    
    public Impresora(){
        
    }
    
    public void imprimir(Printable factura){
        PrinterJob pJ = new PSPrinterJob();
        pJ.setPrintable(factura);
        try {
            if(pJ.printDialog()){
                pJ.print();
            }
        } catch (Exception e) {
        }
    }
}
