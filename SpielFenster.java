import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.DelayQueue;
import javax.swing.*;
import javax.swing.event.*;
import java.util.concurrent.DelayQueue;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 14.09.2019
 * @author Florian Denk
 */

public class SpielFenster extends JFrame {
  // Anfang Attribute
  Container cp = getContentPane();                                                //Grafische gebrauchte Attribute
  private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();       //Grafische gebrauchte Attribute
  public static  int frameWidth  = d.width  ;                              //Grafische gebrauchte Attribute
  public static  int frameHeight = d.height ;                              //Grafische gebrauchte Attribute
  private Spielkarte spielkarte01;
  // Ende Attribute
  
  public SpielFenster() { 
    
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);                               //Grafische Veränderung
    setSize(800,600);                                                                         //Grafische Veränderung
    



    setExtendedState(JFrame.MAXIMIZED_BOTH);   //Vollbild                                     //Grafische Veränderung
    setUndecorated(false);                     //normale Randmarkierungen                     //Grafische Veränderung
    
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;                                                //Grafische Veränderung
    setLocation(x, y);                                                                        //Grafische Veränderung
    setTitle("Lotti Karotti");                                                                //Grafische Veränderung
    setResizable(true);                                                                       
    this.setBackground(Color.GREEN);                                                          //Grafische Veränderung
    
    
    // Anfang Komponenten
    
    prepareSpielFeld();                               
    
    // Ende Komponenten
    
    setVisible(true);                                                                         //Grafische Veränderung
    
     
  } // end of public SpielFenster
  
  public void prepareSpielFeld(){
    spielkarte01= new Spielkarte(5,2,20);
    spielkarte01.setBounds(0,0,frameWidth, frameHeight);                            //Grafische Veränderung
    getContentPane().add(spielkarte01);                                                       //Grafische Veränderung
    spielkarte01.setVisible(true);                                 
    }
  
  public static void sleep(int pMilliseconds){
       try{
         Thread.sleep(pMilliseconds);
    }catch(InterruptedException e){}
       }
  
  // Anfang Methoden
  
  public static void main(String[] args) {
     SpielFenster spielfenster01 = new SpielFenster();
     
  } // end of main
  
  // Ende Methoden
} // end of class SpielFenster

