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
class MIRV {
    private int x, y;
    private int dsx, dsy, v; //desplaçaments (sempre valen 1), v = velocitat
    private int tx = 65; //marge x de la imatge de la MIRV
    private int ty = 25; //marge y de la imatge de la MIRV
    private int numMIRV;
    private String img = "large_icbm_mirv_warhead_reentry_vehicle_3d_model_4.png";
    private Image image;
    private Modul14C54 modul14C54Obj;
    
 
 
 
    public MIRV(int x, int y, int dsx, int dsy, int v, int numMIRV, Modul14C54 modul14C54Obj){
        this.x = x;
        this.y = y;
        this.dsx = dsx;     //Val 1 sempre.
        this.dsy = dsy;     //Val 1 sempre.
        this.v = v;
        this.numMIRV = numMIRV;
        image = new ImageIcon(MIRV.class.getResource(img)).getImage();
        this.modul14C54Obj = modul14C54Obj;
    }
 
    
    public int velocitat (){ //sleep en milisegons
        return v;
    }
 
    
    public void moure (){
        x = x + dsy;
        switch (numMIRV){
            case 0:
                y = y + dsy;
                //x = x + dsx * numMIRV;
                break;
            case 1:
                if (x <= 500){
                    y = y;
                }else{
                    y = y + 2*dsy;
                }
                break;
            case 2:
                if (x <= 500){
                    y = y + dsy;
                }else{
                    y = y + 2*dsy;
                }
                y = y + dsy;
                break;
        }
        
        if ( x >= 1400 - tx || x <= 1) {
            dsx = -dsx;
        }
        
        if ( y >= 880 - ty || y <= 1 ) {
            //dsy = -dsy;
            dsy = 0;
            v = 10000;                                    //+ velocidad, entonces más lento. El hilo está sleep() + tiempo.
            modul14C54Obj.setImpacteMIRV(numMIRV, true);
            img = "explosio_nuclear.png";
            image = new ImageIcon(MIRV.class.getResource(img)).getImage();
            y = 795;
        }
    }


    public void pinta (Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.image, x, y, null);
    }
 
}
