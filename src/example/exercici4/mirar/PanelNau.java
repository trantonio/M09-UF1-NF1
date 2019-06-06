/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.exercici4.mirar;

import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author gines
 */
class PanelNau extends JPanel implements Runnable {
    private int numNaus=30;
    Thread[] filsNau;
    Nau[] nau;
 
 
    public PanelNau(){
        filsNau = new Thread[numNaus]; //Creo dos hilos
        nau = new Nau[numNaus];


        for (int i = 0; i < filsNau.length; i++){
            filsNau[i] = new Thread(this);
            filsNau[i].setName("Fil Nau-"+ i);

            Random rand = new Random();
            //int velocitat = rand.nextInt(50);
            int velocitat = 20;
            //int posX = rand.nextInt(100) + 30;
            //int posY = rand.nextInt(100) + 30;
            int posX = rand.nextInt(400) + 30;
            int posY = rand.nextInt(400) + 30;
            //int dX = rand.nextInt(3) + 1;
            //int dY = rand.nextInt(3) + 1;
            int dX = 1;
            int dY = 1;
            nau[i] = new Nau(posX, posY, dX, dY, velocitat);
        }

        for (int i = 0; i < filsNau.length; ++i)
            filsNau[i].start();
    }
 
 
    public void run(){
        for (int i = 0; i < filsNau.length; ++i){
            while (filsNau[i].currentThread() == filsNau[i]) {
                try {
                    filsNau[i].sleep(nau[i].velocitat()); //
                    nau[i].moure();
                }catch (InterruptedException e) { }
                repaint();
                
            }
        }
    }
 
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < nau.length; ++i)
            nau[i].pinta(g);
    }
}
