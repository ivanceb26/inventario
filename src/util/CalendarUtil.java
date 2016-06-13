/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan C
 */
public class CalendarUtil {
    public static Date getPrimerDiaDelMes(Date dateact ) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateact);

        cal.set(cal.get(Calendar.YEAR),

                cal.get(Calendar.MONTH),

                cal.getActualMinimum(Calendar.DAY_OF_MONTH),

                cal.getMinimum(Calendar.HOUR_OF_DAY),

                cal.getMinimum(Calendar.MINUTE),

                cal.getMinimum(Calendar.SECOND));

        return cal.getTime();

    }

 

    public static Date getUltimoDiaDelMes(Date dateact) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateact);

        cal.set(cal.get(Calendar.YEAR),

                cal.get(Calendar.MONTH),

                cal.getActualMaximum(Calendar.DAY_OF_MONTH),

                cal.getMaximum(Calendar.HOUR_OF_DAY),

                cal.getMaximum(Calendar.MINUTE),

                cal.getMaximum(Calendar.SECOND));

        return cal.getTime();

    }
    
    public static java.util.Date cambiarFecha(java.util.Date tmpdate, int dias, int meses, int anos) {

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
            Logger.getLogger(CalendarUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        


        return fechanueva;

    }

    
}
