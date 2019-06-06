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
import javax.swing.JPanel;

/**
 *
 * @author gmartinez
 * 
 * Actua de Monitor entre SS27, els 3 fils MIRV i el fil ControlDeVolDelSS27 per a compartir dades comunes a les quals
 * accediran amb synchronized (setters i getters sincronitzats).
 */


public class Modul14C54 {
    private boolean estaEnOrbita = false;                   //Variable compartida entre SS27, els 3 fils MIRV i el fil ControlDeVolDelSS27.
    private boolean[] impacteMIRV = {false, false, false};  //Variable compartida entre SS27, els 3 fils MIRV i el fil ControlDeVolDelSS27.
    private boolean impacteControlDeVolDelSS27 = false;     //Variable compartida entre SS27 i el fil ControlDeVolDelSS27.
    private String img = "fi_del_mon.png";
    private Image image;
    
    
    public Modul14C54(){
        image = new ImageIcon(Modul14C54.class.getResource(img)).getImage();
    }
    
    
    //Aquest constructor no fa falta perquè ja inizialitzem les variables en la propia classe.
    public Modul14C54(boolean estaEnOrbita, boolean[] impacteMIRV, boolean impacteControlDeVolDelSS27 ){
        this.estaEnOrbita = estaEnOrbita;
        this.impacteMIRV = impacteMIRV;
        this.impacteControlDeVolDelSS27 = impacteControlDeVolDelSS27;
        image = new ImageIcon(Modul14C54.class.getResource(img)).getImage();
    }
    
    
    public synchronized boolean isEstaEnOrbita() {
        return estaEnOrbita;
    }

    
    public synchronized boolean[] getImpacteMIRV() {
        return impacteMIRV;
    }
    
    
    public synchronized boolean getImpacteMIRV(int numMIRV) {
        return impacteMIRV[numMIRV];
    }

    
    public synchronized boolean isImpacteControlDeVolDelSS27() {
        return impacteControlDeVolDelSS27;
    }

    
    public synchronized void setEstaEnOrbita(boolean estaEnOrbita) {
        this.estaEnOrbita = estaEnOrbita;
    }

    
    public synchronized void setImpacteMIRV(boolean[] impacteMIRV) {
        this.impacteMIRV = impacteMIRV;
    }
    
    
    public synchronized void setImpacteMIRV(int numMIRV, boolean impacteMIRV) {
        this.impacteMIRV[numMIRV] = impacteMIRV;
    }

    
    public synchronized void setImpacteControlDeVolDelSS27(boolean impacteControlDeVolDelSS27) {
        this.impacteControlDeVolDelSS27 = impacteControlDeVolDelSS27;
    }
    

    public void engegaModul14C54 (){
        //Métode que no fem servir per a res. El podem esborrar.
    }
    
    
    public void pinta (Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(this.image, 0, 0, null);
    }

}
