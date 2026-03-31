import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 09.09.2019
 * @author Florian Denk
 */

public class ZiehStappel extends JButton{
  
  // Anfang Attribute
  private ZiehKarte[] stappelZumZiehen;
  private ZiehKarte[] unsortierterStappel;
  public Spielkarte spielkarte01;
  private int counter = 0;
  private int lastindexOfZiehKarte = 0;
  
  // Ende Attribute
  
  // Anfang Methoden
  public ZiehStappel(int pGroesse, Spielkarte pSpielkarte){
    super();
    spielkarte01 = pSpielkarte;
         unsortierterStappel        = new ZiehKarte[pGroesse];
         stappelZumZiehen           = new ZiehKarte[pGroesse];
    
         for (int i = 0; i < stappelZumZiehen.length-1; i++) {
         stappelZumZiehen[i] = new ZiehKarte();
           
         }
    setBounds(0,0,100,100);
    this.setBackground(Color.RED);
    this.setVisible(true);
    this.addActionListener(new ZiehkartenActionListener(this));
    }
  public ZiehKarte zieheKarteVomStappel(){
    int index =  (int)((Math.random()) * stappelZumZiehen.length -1 );
    
//    while (lastindexOfZiehKarte == index) { 
//       index =  (int)((Math.random()) * stappelZumZiehen.length -1 );
//    } // end of while
      
    
    return  stappelZumZiehen[index];
    
  }
  
  private ZiehKarte[] tauscheKarten(ZiehKarte[] pZiehKarte, int pIndexKarte1, int pIndexKarte2){
    ZiehKarte pMerkeZiehKarte = pZiehKarte[pIndexKarte1];
    pZiehKarte[pIndexKarte1] = pZiehKarte[pIndexKarte2];
    pZiehKarte[pIndexKarte2] = pMerkeZiehKarte;
    return pZiehKarte; 
    }
  private ZiehKarte[] listeRunterRutschen(ZiehKarte[] pZiehkartenStappel){
  
    int a = pZiehkartenStappel.length;
    for (int i = 0; i < a; i++) {
      pZiehkartenStappel[i-1] = pZiehkartenStappel[i];
    }
    
    return pZiehkartenStappel;
    }
  
  public void paintComponent (Graphics g)                 //Grafische Veränderung
    {
        super.paintComponent(g);
        this.paintImage(g);     
    }
  
  private void paintImage(Graphics g){     //Grafische Veränderung
      this.setBounds(0,0,80,108);
      this.setLocation(20,500);
      
      this.setBackground(Color.green);           
      
      Image meinBild = null;
    
      String fileOrder ="C:\\Users\\Asus\\Java-Projekte 1\\LottiKarotti\\Kartenstapel.png";
    
    try {                
               meinBild = ImageIO.read(new File(fileOrder));     
            } catch (IOException ex) {
                   
            }
            
            
            g.drawImage(meinBild, 0, 0, this);
    
                  
            this.setVisible(true);
    }
  // Ende Methoden
} // end of ZiehStappel

