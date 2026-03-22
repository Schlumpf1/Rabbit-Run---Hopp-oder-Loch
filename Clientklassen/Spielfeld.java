import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 29.09.2020
 * @author 
 */

public class Spielfeld extends JPanel {
  
  // Anfang Attribute
  private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();       //Grafische gebrauchte Attribute
  public   int frameWidth  = (d.width  / 5)*4;                              //Grafische gebrauchte Attribute
  public   int frameHeight = (d.height / 5)*4;                              //Grafische gebrauchte Attribute
  private  int maxSpielfelder;
  
  private static String fileOrdnerImages = ".\\bilder\\";
  private static final int breiteUndHoehe = 60;
  private int spielfeldnummer;                                                               
  public boolean hasenfalle;
  private Spielfigur hierStehendeSpielfigur = null;
  
  private boolean miniVerbindung;
  
  // Ende Attribute
  
  // Anfang Methoden
  public Spielfeld(int pSpielfeldnummer, int pMaxSpielfelder){
    super();
    
    
    spielfeldnummer = pSpielfeldnummer;
    maxSpielfelder = pMaxSpielfelder;
    
    this.setBackground(new Color(0,0,0,0));
    this.setBounds(0,0,breiteUndHoehe,breiteUndHoehe);                                   
      if(spielfeldnummer == 0){                                    
        this.setLocation(frameWidth/2,frameHeight/2);            
      }
      else{
        this.setLocation(getKreisKoordinate());
      }
           
  }
  /*
  * Konstruktor fuer Spielfeldverbindungen
  */
  public Spielfeld(){
    this.setBackground(new Color(0,0,0,0));
    this.setBounds(0,0,20,20);
  }
  
  public void paintComponent(Graphics g){
    super.paintComponent(g);
    
    if(spielfeldnummer == 0 ){                                    //Grafische Veraenderung
      this.paintImage(g);
    }
    else{
      g.setColor(Color.orange);
      g.fillOval(0,0,breiteUndHoehe,breiteUndHoehe);
      if(hasenfalle){
        g.setColor(Color.black);
        g.fillOval(3,3,breiteUndHoehe-6,breiteUndHoehe-6);
    }
      else {
        g.setColor(Color.black);
        g.drawString(""+spielfeldnummer,breiteUndHoehe/2,breiteUndHoehe/2);
      } // end of if-else
    }
  }
    
  public int getSpielfeldnummer() {
    return spielfeldnummer;
  }

  public boolean getHasenfalle() {
    return hasenfalle;
  }
  
  public boolean getBesetzt() {
    if (hierStehendeSpielfigur== null) {
      return false;
    } else {
      return true;
    } // end of if-else
  }
  
  public Spielfigur entferneSpielFigur(){
    if(!getBesetzt()){
      return null;
    }
    else {
      this.remove(hierStehendeSpielfigur);
      this.repaint();
      
      Spielfigur localSpielfigur= hierStehendeSpielfigur;
      hierStehendeSpielfigur = null;
      return localSpielfigur;
    } // end of if-else
  }
  
  public void fuegeSpielfigurHinzu(Spielfigur pSpielfigur){
    if (getHasenfalle()) {
      this.println("Hasenfalle hat eine Spielfigur von Team: "+pSpielfigur.getMeinTeam().getTeamfarbe()+" gefangen");
    }
    else if(!getBesetzt()) {
      hierStehendeSpielfigur = pSpielfigur;
      pSpielfigur.position = this.spielfeldnummer;
      this.add(pSpielfigur);
    
      this.repaint();
    } 
    else {
      Spielfeld.println("Spielfeld bereits besetzt");
    } // end of if-else
  }
  
  

  public void setHasenfalle(boolean hasenfalleNeu) {
    hasenfalle = hasenfalleNeu;
    this.repaint();
  }

  public void anordnen() {
    // TODO hier Quelltext einfuegen
    
  }
  
  /**
   * Koordinaten fuer eine Kreiaehnliche Gestaltung 
   * aller Spielfelder anhand der SpielfeldNummer 
   * wird als Punkt p zurueckgegeben
   */
  public Point getKreisKoordinate(){
    double alpha = 610 / maxSpielfelder-1;
    double winkel = alpha*spielfeldnummer;
    double radWinkel = winkel / 180 * Math.PI;            
    double x_koordinate = Math.cos(radWinkel ) *200 ;  
    double y_koordinate = Math.sin(radWinkel)*200;
    
    
    
    int xkoordinate = (int)(x_koordinate * (Math.sqrt((double) spielfeldnummer  )*0.35));
    xkoordinate = (xkoordinate + (d.width/3) ) - spielfeldnummer *3;
    int ykoordinate = (int)(y_koordinate * (Math.sqrt((double) spielfeldnummer  )*0.35));
    ykoordinate = (ykoordinate +(d.height/3)) - spielfeldnummer *3;
    
    Point returnPoint = new Point(xkoordinate  ,ykoordinate );
    //System.out.println("alpha " + alpha +" winkel " + winkel +" radwinkel "+ radWinkel + "   x "+ xkoordinate +"  y " +ykoordinate );
    return returnPoint;
  }
  
  private void paintImage(Graphics g){      //Grafische Veraenderung
      setImage("Moehre.png", g);             
  }
  
  private void setImage(String pBildName, Graphics g){        //Grafische Veraenderung
    Image meinBild = null;
    this.setBounds(0,0,50,50);
      g.setColor(Color.white);
      g.fillOval(0,0,((int)(this.getSize().getWidth())),((int)(this.getSize().getHeight())));
         try {                
               meinBild = ImageIO.read(new File(fileOrdnerImages+pBildName));     
            } catch (IOException ex) {
                   
            }
            
            g.drawImage(meinBild, 8,8, this);
           
            
            this.setLocation(getKreisKoordinate());      
            this.setVisible(true);
            
  }
  
  public static void println(String pMsg){
    System.out.println("Spielfeld.class: "+pMsg);
  }
  
  public static void main(String[] args) {
    Spielfenster.main(args);
  } // end of main

  public Spielfigur getHierStehendeSpielfigur() {
    return hierStehendeSpielfigur;
  }

  // Ende Methoden
} // end of Spielfeld

