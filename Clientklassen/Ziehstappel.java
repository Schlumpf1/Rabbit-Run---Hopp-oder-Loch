import java.awt.Color;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 09.10.2020
 * @author 
 */

public class Ziehstappel extends JPanel implements MouseListener{
  
  // Anfang Attribute
  Spielfenster spielfenster01;
  // Ende Attribute
  
  // Anfang Methoden
  public Ziehstappel(Spielfenster pSpielfenster){
    super();
    spielfenster01 = pSpielfenster;
    
    this.addMouseListener(this);
    this.setSize(100,150);
    this.setBounds(0,0,100,150);
    this.setBackground(new Color(0,0,0,0));
    
    this.setVisible(true);
}
  
  public void paintComponent (Graphics g){
    super.paintComponent(g);          //Grafische Veraenderung
    g.setColor(Color.black);
    g.fillRoundRect(0,0,this.getWidth(),this.getHeight(),5,5);
    
    
    
    g.setColor(Color.WHITE);
    g.fillRoundRect(2,2,this.getWidth()-4,this.getHeight()-4,4,4);
    g.setColor(Color.black);
    g.setFont( new Font("SansSerif",Font.PLAIN,20));
    g.drawString("ZIEH",25,60);
    g.drawString("STAPPEL",8,100); 
  }
  
  public void mouseExited(MouseEvent e){}
  
  public void mouseEntered(MouseEvent e){}
  
  public void mouseReleased(MouseEvent e){}
  
  public void mousePressed(MouseEvent e){}
  
  public void mouseClicked(MouseEvent e){
    spielfenster01.karteWurdeGezogen();
    this.println("Ziehstappel geklickt");
  }
  
  public static void main(String[] args) {
    Spielfenster.main(args);
  } // end of main
  
  public static void println(String pMsg){
    System.out.println("Ziehstappel.class: "+pMsg);
  }
  
  
  // Ende Methoden
} // end of Ziehstappel

