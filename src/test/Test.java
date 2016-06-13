/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Wilson Salamanca
 */
public class Test {
    public static void main(String args[]){
        Calendar fecha = Calendar.getInstance();
        System.out.println(fecha.getTime());
        java.util.Date fechaActual;
        fechaActual = fecha.getTime();
        Date date = new Date(fechaActual.getTime());
        System.out.println(date);
        fecha.setTime(fechaActual);
        fecha.add(Calendar.DAY_OF_MONTH, 9);
        fechaActual = fecha.getTime();
        date = new Date(fechaActual.getTime());
        System.out.println(date);
    }
}
