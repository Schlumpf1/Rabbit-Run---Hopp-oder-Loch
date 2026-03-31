import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics.*;
import java.awt.Rectangle;

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

public class SpielFigur extends JPanel{
  
  // Anfang Attribute
  public Spielfeld aktuellesSpielFeld;
  public boolean zuhause = false;
  private Spielkarte spielKarte;
  private Team meinTeam;
  private String farbe;
  public int spielFigurNummer;
  
  // Ende Attribute
  
/**
 *
 *
 *
 */
  public SpielFigur( Team meinTeam, String farbe, Spielkarte spielKarte, int pSpielFigurNummer) {
    super(null);
    this.setBounds(0,0,23,32);
    this.setLocation(8,3);
    
    
    this.aktuellesSpielFeld = null;
    this.spielKarte = spielKarte;
    this.meinTeam = meinTeam;
    this.farbe = farbe;
    zuhause = true;
    this.spielFigurNummer = pSpielFigurNummer;
    this.addMouseListener(new MouseListenerSpielFigur(spielKarte,this));
    
    
    this.setBackground(Color.yellow);                //Grafische Veränderung
    //spielKarte.addJPanel(this);                      //Grafische Veränderung
                           
    System.out.println("Spielfigur erzeugt    x:"+ this.getX() +"   y:"+this.getY());                         
    this.setVisible(true);                           //Grafische Veränderung
    
  }
  
//    public SpielFigur(Spielkarte spielKarte) {
//      super();
//      this.aktuellesSpielFeld = null;
//      this.spielKarte = spielKarte;
//      this.meinTeam = null;
//      this.farbe = "violett";
//      zuhause = false;
//      
//      this.setBackground(new Color(0, 200, 0));               //Grafische Veränderung
//      
//      this.addMouseListener(new MouseListenerSpielFigur(spielKarte,this));
//       
//    
//      this.setVisible(true);                                  //Grafische Veränderung
//      
//    }

  // Anfang Methoden
  public Spielfeld getAktuellesSpielFeld() {
    return aktuellesSpielFeld;
  }

  public Team getMeinTeam() {
    return meinTeam;
  }
  public String getZuhause(){
    if(zuhause){
       return "zuhause";  
      }
  
  else {
    return "nicht zuhause";
  } // end of if-else
    }

  public String getFarbe() {
    return farbe;
  }
  
  
  
  public void paintComponent (Graphics g)                 //Grafische Veränderung
    {
        super.paintComponent(g);
          
        this.paintImage(g, getFarbe());     
    }
  
  private void paintImage(Graphics g, String pFarbe){     //Grafische Veränderung
       
                       

      Image meinBild = null;
    
      String fileOrder = "C:\\Users\\Asus\\Java-Projekte 1\\LottiKarotti\\VioletterHase.png";
    
      if(pFarbe == "violett") {
                fileOrder = "C:\\Users\\Asus\\Java-Projekte 1\\LottiKarotti\\VioletterHase.png"; 
      } // end of if
      else if (pFarbe == "gruen") {
           fileOrder = "C:\\Users\\Asus\\Java-Projekte 1\\LottiKarotti\\GruenerHase.png";
      }
      else if (pFarbe == "orange" ) {
           fileOrder = "C:\\Users\\Asus\\Java-Projekte 1\\LottiKarotti\\OrangerHase.png";
      }
    
       try {                
               meinBild = ImageIO.read(new File(fileOrder));     
            } catch (IOException ex) {
                   
            }
            
            
            g.drawImage(meinBild, 0, 0, this);
            //this.setBounds(0,0,23,32);
            g.drawString(""+spielFigurNummer, 8,26);
                  
            this.setVisible(true); 
                 
            }
  
  public void setToStartPositionTeam(){
    
    meinTeam.addSpielFigurToThis(this);
    
    
      
    }   
   
  // Ende Methoden
} // end of SpielFigur

