/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.galeria;


import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Galeria {
    //para almacenar las imagene
    private ArrayList<ImageIcon> fotos = new ArrayList<ImageIcon>();
    private ImageIcon nofoto = null;//el nombre lo dice todo

    public Galeria(){
        //se cargan las fotos
        //nofoto = new javax.swing.ImageIcon(getClass().getResource("nofoto.gif"));
        for(int i=1;i<56;i++){
        fotos.add(new javax.swing.ImageIcon("images/ima ("+i +").jpeg"));
        }
    }

    /* devuelve una imagen de tamaño 100x100 VISTA PREVIA */
    public Icon getPreview(int num){
        if( num>=0 & num<fotos.size() )
        {
            Image mini = fotos.get(num).getImage().getScaledInstance(104, 104, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(mini);
        }
        else
        {
            Image mini = nofoto.getImage().getScaledInstance(104, 104, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(mini);
        }
    }

   /* devuelve la foto original, pero si el tamaño es mayor al contenedor, lo redimensiona */
   public Icon getFoto(int num, Dimension d){
        if( num>=0 & num<fotos.size() )
        {
            //si la foto es mas grande que el contendor -> redimensionar
            if(fotos.get(num).getIconWidth()>d.getWidth()){
                float v = getValor(fotos.get(num).getIconWidth(),d.width-10);
                return Disminuir(fotos.get(num),v);
            }else if(fotos.get(num).getIconHeight()>d.getHeight()){
                float v = getValor(fotos.get(num).getIconHeight(),d.height-10);
                return Disminuir(fotos.get(num),v);
            }else{
                return fotos.get(num);
            }
        }
        else
        {
            Image mini = nofoto.getImage().getScaledInstance(400, 400, Image.SCALE_AREA_AVERAGING);
            return new ImageIcon(mini);
        }
    }

   /* redimensiona la imagen en para que ingrese al contendor pero manteniendo sus proporciones*/
    private ImageIcon Disminuir(ImageIcon i, float v){
        int valEscalaX = (int) (i.getIconWidth() * v );
        int valEscalaY = (int) (i.getIconHeight() * v );
        Image mini = i.getImage().getScaledInstance(valEscalaX, valEscalaY, Image.SCALE_AREA_AVERAGING);
        return new ImageIcon(mini);
     }

    /* devuelve el valor de escala para redimensionar la imagen*/
    private float getValor(int a, int b){
        return Math.abs((a/new Float(b))-2f);
    }
}