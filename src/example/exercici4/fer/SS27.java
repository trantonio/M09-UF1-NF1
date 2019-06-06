/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.exercici4.fer;

import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


/**
 *
 * @author gines
 */

 /*
 MISIL FORMADO POR VARIAS PARTES, LA CENTRAL (14C54) CONTROLARÁ EL LANZAMIENTO DE LAS 3 MIRV's Y SE PONDRÁ 
 COMUNICAR CON CADA UNA DE ELLAS DE MANERA INDEPENDIENTE. 
 HABRÁ 3 HILOS DE LA CLASE MIRV, CADA HILO REPRESENTARÁ A 1 MIRV.
 ESTA CLASE LANZA LAS MIRV EN EL MÉTODO SS27().
 14C54, en el moment del llançament, llançarà els següents fils:
      - 1 fil de la classe ControlDeVolDelSS27 que controlarà el vol del SS-27 fins a colocar-lo en órbita.
      - 3 fils de la classe MIRV que estaran en wait() fins que el SS-27 arribi a la órbita i deixi anar les MIRV.
      - "boolean estaEnOrbita" determinarà si el SS-27 está en órbita i fa un notifyAll() contra les MIRVs perquè
        els seus fils s'activin i comencin a pintar per pantalla.
 14C54 controlarà el vol del SS27 fins que arribi a la órbita amb un fil de la classe ControlDeVolDelSS27.
 14C54, quan estigui en òrbita, deixarà anar les MIRV (llançarà 3 fils de la classe MIRV).
 14C54 es comunicarà amb les MIRV.
 */
class SS27 extends JPanel implements Runnable {
    private int numMIRVs = 3;
    Thread[] filsMIRVs;
    MIRV[] arrayMIRVs;
    
    Thread filControlDeVolDelSS27;
    ControlDeVolDelSS27 controlDeVolDelSS27;
    long controlDeVolDelSS27ID;
    
    Modul14C54 modul14C54Obj;
    
 
 
    public SS27(){
        filsMIRVs = new Thread[numMIRVs];       //Es creen 3 fils.
        arrayMIRVs = new MIRV[numMIRVs];
        int velocitatMIRVs = 10;                //+ velocidad, entonces más lento. El hilo está sleep() + tiempo.
        int velocitatSS27 = 30;                 //+ velocidad, entonces más lento. El hilo está sleep() + tiempo.
        int posX, posY;
        

        
        System.out.println("SS27.INICI");
        
        //Creem un objecte modul14C54Obj de la classe modul14C54Obj que és la que actua de monitor, és a dir,
        //actua d'intermediària entre SS27, els 3 fils MIRV i el fil ControlDeVolDelSS27 per a compartir dades comunes.
        modul14C54Obj = new Modul14C54();
        
        
        // Fil del control del vol del SS-27.
        controlDeVolDelSS27 = new ControlDeVolDelSS27(1, 840, 1, 1, velocitatSS27, modul14C54Obj);
        filControlDeVolDelSS27 = new Thread(this);
        filControlDeVolDelSS27.setName("Fil controlDeVolDelSS27");
        controlDeVolDelSS27ID = filControlDeVolDelSS27.getId();
        
        filControlDeVolDelSS27.start();

        
        // Fils de les MIRVs.
        for (int i = 0; i < filsMIRVs.length; i++){
            filsMIRVs[i] = new Thread(this);
            filsMIRVs[i].setName("Fil MIRV-"+ i);
            
            posX = 430;
            switch (i){
                case 0:
                    posY = 25;
                    break;
                case 1:
                    posY = 50;
                    break;
                case 2:
                    posY = 75;
                    break;
                default:
                    posY = 0;
            }
            
            int dX = 1;
            int dY = 1;
            arrayMIRVs[i] = new MIRV(posX, posY, dX, dY, velocitatMIRVs, i, modul14C54Obj);
        }

        for (int i = 0; i < filsMIRVs.length; ++i){
            filsMIRVs[i].start();
        }
    }
 
 
    public void run(){
        System.out.println("    " + Thread.currentThread().getName() + ".INICI");
        
        if (Thread.currentThread().getId() == controlDeVolDelSS27ID) {
            while (Thread.currentThread().getId() == controlDeVolDelSS27ID) {
                try {
                    if (modul14C54Obj.isImpacteControlDeVolDelSS27() == true) {
                        //System.out.println(Thread.currentThread().getName() + ": impacteControlDeVolDelSS27  = " + modul14C54Obj.isImpacteControlDeVolDelSS27() + ", estaEnOrbita = " + modul14C54Obj.isEstaEnOrbita() );
                        filControlDeVolDelSS27.interrupt();
                    } else {
                        filControlDeVolDelSS27.sleep(controlDeVolDelSS27.velocitat());
                        controlDeVolDelSS27.moure();
                        //System.out.println(Thread.currentThread().getName() + ".moure(g): estaEnOrbita = " + estaEnOrbita + ", impacteControlDeVolDelSS27 = " + impacteControlDeVolDelSS27);
                    }
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(SS27.class.getName()).log(Level.SEVERE, null, ex);
                }
                repaint();
            }
        }
        else {
            for (int i = 0; i < filsMIRVs.length; ++i){
                while (filsMIRVs[i].currentThread() == filsMIRVs[i]) {
                    //System.out.println(Thread.currentThread().getName() + ".moure(g): estaEnOrbita = " + modul14C54Obj.isEstaEnOrbita() + ", impacteMIRV[" + i + "] = " + modul14C54Obj.getImpacteMIRV(i));
                    try {
                        //System.out.println(Thread.currentThread().getName() + ".arrayMIRVs[" + i + "].moure(g): estaEnOrbita = " + estaEnOrbita + ", impacteMIRV[" + i + "] = " + impacteMIRV[i]);
                        if (modul14C54Obj.isImpacteControlDeVolDelSS27() == true) {
                            //System.out.println(Thread.currentThread().getName() + ": impacteControlDeVolDelSS27  = " + modul14C54Obj.isImpacteControlDeVolDelSS27() + ", estaEnOrbita = " + modul14C54Obj.isEstaEnOrbita() );
                            filsMIRVs[i].interrupt();
                        } else {
                            if (modul14C54Obj.isEstaEnOrbita() == true) {
                                filsMIRVs[i].sleep(arrayMIRVs[i].velocitat()); //
                                arrayMIRVs[i].moure();
                                //System.out.println("    " + "SS27.run().arrayMIRVs[" + i + "].moure(g): estaEnOrbita = " + modul14C54Obj.isEstaEnOrbita());
                                repaint();
                            }                            
                        }
                    }catch (InterruptedException e) { }
                }
            } 
        }
    }
 
 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (modul14C54Obj.isEstaEnOrbita() == true) {
            for(int i = 0; i < arrayMIRVs.length; ++i) {
                arrayMIRVs[i].pinta(g);
                //System.out.println(Thread.currentThread().getName() + "SS27.paintComponent().pinta(g): estaEnOrbita = " + estaEnOrbita);
            }
        }
        
        //System.out.println(Thread.currentThread().getName() + "SS27.paintComponent().ControlDeVolDelSS27.pinta(g)");
        controlDeVolDelSS27.pinta(g);
        
        //modul14C54Obj.pinta(g);
        
        if (filControlDeVolDelSS27.isInterrupted()) {
            modul14C54Obj.pinta(g);
        }
    }
}
