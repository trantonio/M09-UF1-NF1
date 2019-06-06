/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.exercici4.mirar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author gines
 */
class Nau {
    private int x, y;
    private int dsx, dsy, v; //desplaçaments (sempre valen 1), v sleep
    //private int tx = 10; //marge x
    //private int ty = 10; //marge y image
    private int tx = 30; //marge x de la imatge de la nau
    private int ty = 50; //marge y de la imatge de la nau

    private String img = "/images/nau.jpg";
    private Image image;
 
 
 
    public Nau(int x, int y, int dsx, int dsy, int v ){
        this.x = x;
        this.y = y;
        this.dsx = dsx;     //Val 1 sempre.
        this.dsy = dsy;     //Val 1 sempre.
        this.v = v;
        image = new ImageIcon(Nau.class.getResource("nau.png")).getImage();
    }
 
    
    public int velocitat (){ //sleep en milisegons
        return v;
    }
 
    
    public void moure (){
        x = x + dsx;
        y = y + dsy;
        // arriva als marges
        //if ( x >= 450-tx || x <= tx)
        //dsx = -dsx;
        //if ( y >= 500- ty || y <= ty )
        //dsy = -dsy;
        
        if ( x >= 800 - tx || x <= 1) {
            dsx = -dsx;
        }
        
        if ( y >= 770 - ty || y <= 1 ) {
            dsy = -dsy;
        }
        
    }


    public void pinta (Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.image, x, y, null);
    }
 
}
