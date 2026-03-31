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



public class TrampelWegFeld extends JPanel{
  
  // Anfang Attribute
  
  private int trampelWegFeldNummer;
  private static int anzahlZaehler = 0;
  private Point      p;
  private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();                                                           //Grafische gebrauchte Attribute            
  Spielkarte spielkarte01;
  public int maxSpielfeld;
  private static int alpha = 1;
  private int myPrivateAlpha; 
  
  private boolean hasenFalleOffen;
  // Ende Attribute
  
  // Anfang Methoden
  public TrampelWegFeld(Spielkarte pSpielkarte){
    super();
    
    trampelWegFeldNummer = anzahlZaehler;
    anzahlZaehler++;
    
    spielkarte01 = pSpielkarte;
    
    this.spielkarte01.addJPanel(this);
    p = getKreisKoordinate();
    this.setLocation(p);
    this.setBounds(getX(),getY(),40,40);
    this.setBackground(new Color(0, 200, 0));
    
    this.setVisible(true);  
    }
  
  public Point getKreisKoordinate(){
    myPrivateAlpha = 610 / (trampelWegFeldNummer +1);
    double winkel = myPrivateAlpha*trampelWegFeldNummer;
    double radWinkel = winkel / 180 * Math.PI;            
    double x_koordinate = Math.cos(radWinkel ) *200 ;  
    double y_koordinate = Math.sin(radWinkel)*200;
    
    
    
    int xkoordinate = (int)(x_koordinate * (Math.sqrt((double) trampelWegFeldNummer  )*0.3));
    xkoordinate = (xkoordinate + (d.width/2) ) - trampelWegFeldNummer *3;
    int ykoordinate = (int)(y_koordinate * (Math.sqrt((double) trampelWegFeldNummer )*0.3));
    ykoordinate = (ykoordinate +(d.height/2)) - trampelWegFeldNummer *3;
    
    Point returnPoint = new Point(xkoordinate  ,ykoordinate );
    
    
    
    
    //System.out.println("alpha " + alpha +" winkel " + winkel +" radwinkel "+ radWinkel + "   x "+ xkoordinate +"  y " +ykoordinate );
    return returnPoint;
    }
  
  public void paintComponent (Graphics g)    //Grafische Veränderung
    {
        super.paintComponent(g);
    
        
        g.setColor(Color.BLACK);
        //g.setColor(Color.orange.brighter());
        g.fillOval(0,0,((int)(this.getSize().getWidth())),((int)(this.getSize().getHeight())));
      
    }
  // Ende Methoden
} // end of TrampelWegFeld

