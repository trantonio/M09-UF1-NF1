/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.exercici4.fer;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author gines
 */
public class ControlDeVolDelSS27 {
    private int x, y;
    private int dsx, dsy, v;    //desplaçaments (sempre valen 1), v = velocitat
    private int tx = 30;        //marge x de la imatge del SS-27
    private int ty = 65;        //marge y de la imatge del SS-27
    private String img = "nau.png";
    private Image image;
    private Modul14C54 modul14C54Obj;
 
 
 
    public ControlDeVolDelSS27(int x, int y, int dsx, int dsy, int v, Modul14C54 modul14C54Obj){
        this.x = x;
        this.y = y;
        this.dsx = dsx;     //Val 1 sempre.
        this.dsy = dsy;     //Val 1 sempre.
        this.v = v;
        image = new ImageIcon(ControlDeVolDelSS27.class.getResource(img)).getImage();
        this.modul14C54Obj = modul14C54Obj;
    }
 
    
    public int velocitat (){ //sleep en milisegons
        return v;
    }
 
    
    public void moure (){
        boolean[] impacteMIRV;
                
        
        impacteMIRV = modul14C54Obj.getImpacteMIRV();
        
        if ((impacteMIRV[0] == false) || (impacteMIRV[1] == false) || (impacteMIRV[2] == false)) {
            x = x + dsx;
            y = y - (dsy*3);
            
            if ( x >= 1400 - tx || x <= 1) {
                dsx = -dsx;
            }

            if ( y <= 100 - ty || y <= 1 ) {
                dsy = 0;
                v = 40;                             //+ velocidad, entonces más lento. El hilo está sleep() + tiempo.
                if (x == 410) {
                    modul14C54Obj.setEstaEnOrbita(true);
                }
            }
        } else {
            dsy = 1;
            v = 10;
            x = x + dsx;
            y = y + (dsy*3);
            
            if (y >= 820) {
                modul14C54Obj.setImpacteControlDeVolDelSS27(true);
            }
        }
    }


    public void pinta (Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.image, x, y, null);
    }
}
