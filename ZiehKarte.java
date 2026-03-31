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

public class ZiehKarte extends JPanel{
  
  // Anfang Attribute
  private int wert;
 
  // Ende Attribute
  
  // Anfang Methoden
  public int getWert() {
    return wert;
  }
  public ZiehKarte(){
    super();
    
    wert = (int)((Math.random()) * 3 + 1);
    
    this.setSize(100,150);
    this.setBounds(0,0,100,150);
    this.setBackground(Color.white);
    
    this.setVisible(true);
    }
  
  
  public ZiehKarte(int pWert){
    super();
    
    wert = pWert;
    
    this.setSize(100,150);
    this.setBounds(0,0,100,150);
    this.setBackground(Color.white);
    
    this.setVisible(true);
    }
  
  
  
  public void paintComponent (Graphics g)                 //Grafische Veränderung
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,100,6);
        g.fillRect(0,0,6,150); 
        g.fillRect(94,0,6,150);
        g.fillRect(0,144,100,6);
        g.setFont( new Font("SansSerif",Font.PLAIN,70));
        g.drawString(""+wert,35,100);    
    }

  // Ende Methoden
} // end of ZiehKarte

