import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Graphics.*;
import javax.swing.*;
import javax.swing.JTextArea;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 09.09.2019
 * @author Florian Denk
 */

public class ZiehKarte extends JPanel implements MouseListener{
  
  // Anfang Attribute
  private int wert;   // 4 = drehen
  Spielfenster spielfenster;
  
  // Ende Attribute
  
  // Anfang Methoden
  
  public ZiehKarte(Spielfenster pSpielfenster, int pWert){
    super();
    
    wert = pWert;
    spielfenster = pSpielfenster;
    this.addMouseListener(this);
    this.setSize(100,150);
    
    this.setBackground(new Color(0,0,0,0));
    
    this.setVisible(true);
  }
  
  public int getZiehkartenwert(){
    return wert;
  }
  
  public void paintComponent (Graphics g){
    super.paintComponent(g);          //Grafische Veraenderung
//    g.setColor(Color.WHITE);
//
//    g.fillRoundRect(0,0,this.getWidth(),this.getHeight(),5,5);
//    
//    g.setColor(Color.black);
//    g.fillRect(0,0,2,this.getHeight());
//    g.fillRect(this.getWidth()-2,0,2,this.getHeight());
//    g.fillRect(0,0,this.getWidth(),2);
//    g.fillRect(0,this.getHeight()-2,this.getWidth(),this.getHeight());
    
    g.setColor(Color.black);
    g.fillRoundRect(0,0,this.getWidth(),this.getHeight(),5,5);
    g.setColor(Color.WHITE);
    g.fillRoundRect(2,2,this.getWidth()-4,this.getHeight()-4,4,4);
    g.setColor(Color.black);
       
    if(wert == 4){
      g.setFont( new Font("SansSerif",Font.PLAIN,20));
      g.drawString("DREHEN",8,100); 
    }
    else{
      g.setFont( new Font("SansSerif",Font.PLAIN,70));
      g.drawString(""+wert,35,100);
    }
  }
  
  public void mouseExited(MouseEvent e){}
  
  public void mouseEntered(MouseEvent e){}
  
  public void mouseReleased(MouseEvent e){}
  
  public void mousePressed(MouseEvent e){}
  
  public void mouseClicked(MouseEvent e){
    spielfenster.karteWurdeGeklickt(this);
    this.println("Ziehkarte geklickt");
  }
  
  public static void main(String[] args) {
    Spielfenster.main(args);
  } // end of main
  
  public static void println(String pMsg){
    System.out.println("Ziehkarte.class: "+pMsg);
  }
  
  // Ende Methoden
} // end of ZiehKarte

