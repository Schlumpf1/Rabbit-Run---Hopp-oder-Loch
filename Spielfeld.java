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

public class Spielfeld extends JPanel{
  
  // Anfang Attribute
  
  private int spielFeldNummer;
  public SpielFigur aktuelleSpielFigur;
  private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();       //Grafische gebrauchte Attribute
  public static  int frameWidth  = (d.width  / 5)*4;                              //Grafische gebrauchte Attribute
  public static  int frameHeight = (d.height / 5)*4;                              //Grafische gebrauchte Attribute
  private static int maxSpielfeld;
  private Point      p;                                                           //Grafische gebrauchte Attribute            
  Spielkarte spielkarte01; 
  
  private boolean hasenFalleOffen;
  // Ende Attribute
  
  // Anfang Methoden
  public Spielfeld(Spielkarte pSpielkarte, int pSpielFeldNummer, int pMaxSpielfelder){
    super();
    
    maxSpielfeld = pMaxSpielfelder;
    spielFeldNummer = pSpielFeldNummer;
    hasenFalleOffen = false;
    spielkarte01 = pSpielkarte;
    
    p = getKreisKoordinate();
    this.setBounds(0,0,40,40);                                   //Grafische Veraenderung
    if(spielFeldNummer == 0){                                    //Grafische Veraenderung
      this.setLocation(frameWidth/2,frameHeight/2);              
      }
    else{
      this.setLocation(p);
      }
    
    //test von Figuren
//    if(spielFeldNummer == 19){
//                       SpielFigur figur1 = new SpielFigur(pSpielkarte);
//                       fuegeFigurNeuHinzu(figur1);
//                       
//      }
    
    //test von Figuren ENDE
    
    
    this.setVisible(true);                  //Grafische Veraenderung
    
    
    }
  
  public boolean getBesetzt() {
    if(aktuelleSpielFigur == null){
      return false;
      }
    else {
      return true;
    } // end of if-else
  }
  

  
  public boolean getHasenFalleOffen() {
    return hasenFalleOffen;
  }
  public int getSpiefeldNummer(){
    return spielFeldNummer;
    }
  
  public boolean platziereSpielfigur(SpielFigur pSpielfigur){
         if(!getBesetzt()){
               
               return true;
      }
    else{
               return false;
      }
    }
  public void entferneSpielFigur(SpielFigur pSpielFigur){
         this.remove(pSpielFigur); //Grafische Veraenderung
    
         aktuelleSpielFigur = null;
         pSpielFigur.aktuellesSpielFeld = null;
         System.out.println("Figur entfernt");
         this.setVisible(true);
    }
  
  public void fuegeFigurNeuHinzu(SpielFigur pSpielFigur){
    this.add(pSpielFigur);         //Grafische Veraenderung
    
    System.out.println("Figur hinzugefügt");
    
    this.aktuelleSpielFigur = pSpielFigur;
    pSpielFigur.aktuellesSpielFeld = this;
    }
  
   
//  public Point getKoordinate(){
//    double exponnent = ((double)spielFeldNummer +100 /( maxSpielfeld) ) ;
//    double radius =  Math.pow(2,exponnent);
//    double xPosition = Math.sin(radius)*120;
//    double yPosition = Math.cos(radius)*120;
//    
//  
//    Point returnPoint = new Point((int) xPosition +100 ,(int)yPosition +100 ); 
//    return returnPoint;
//    }
  
  /**
   * Koordinaten für eine Kreisaehnliche Gestaltung 
   * aller Spielfelder anhand der SpielfeldNummer 
   * wird als Punkt p zurückgegeben
   */
  public Point getKreisKoordinate(){
    double alpha = 610 / maxSpielfeld;
    double winkel = alpha*spielFeldNummer;
    double radWinkel = winkel / 180 * Math.PI;            
    double x_koordinate = Math.cos(radWinkel ) *200 ;  
    double y_koordinate = Math.sin(radWinkel)*200;
    
    
    
    int xkoordinate = (int)(x_koordinate * (Math.sqrt((double) spielFeldNummer  )*0.3));
    xkoordinate = (xkoordinate + (d.width/2) ) - spielFeldNummer *3;
    int ykoordinate = (int)(y_koordinate * (Math.sqrt((double) spielFeldNummer  )*0.3));
    ykoordinate = (ykoordinate +(d.height/2)) - spielFeldNummer *3;
    
    Point returnPoint = new Point(xkoordinate  ,ykoordinate );
    //System.out.println("alpha " + alpha +" winkel " + winkel +" radwinkel "+ radWinkel + "   x "+ xkoordinate +"  y " +ykoordinate );
    return returnPoint;
    }
  
   public void paintComponent (Graphics g)    //Grafische Veraenderung
    {
        super.paintComponent(g);
         this.setBackground(new Color(0, 200, 0));
    if(spielFeldNummer == 0) {
        paintImage(g);
    } 
    else {
      
      g.setColor(Color.orange.brighter());
      
      g.fillOval(0,0,((int)(this.getSize().getWidth())),((int)(this.getSize().getHeight())));
      
    } 
        
    repaint();    
    }
    private void paintImage(Graphics g){      //Grafische Veraenderung
      setImage("C:\\Users\\Asus\\Java-Projekte 1\\LottiKarotti\\Möhre.png", g);             
            
            }
    private void setImage(String pFileOrder, Graphics g){        //Grafische Veraenderung
    Image meinBild = null;
    this.setBounds(0,0,50,50);
      g.setColor(Color.white);
      g.fillOval(0,0,((int)(this.getSize().getWidth())),((int)(this.getSize().getHeight())));
         try {                
               meinBild = ImageIO.read(new File(pFileOrder));     
            } catch (IOException ex) {
                   
            }
            
            g.drawImage(meinBild, 8,8, this);
           
            
            this.setLocation(p);      
            this.setVisible(true);
         } 
  
  

  // Ende Methoden
} // end of Spielfeld

