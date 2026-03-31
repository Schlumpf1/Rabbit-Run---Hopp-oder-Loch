import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 02.10.2019
 * @author Florian Denk
 */

public class ZiehkartenActionListener implements ActionListener {
  
  // Anfang Attribute
  ZiehStappel ziehStappel01; 
  // Ende Attribute
  
  // Anfang Methoden
  public ZiehkartenActionListener(ZiehStappel pZiehStappel01){
         ziehStappel01 = pZiehStappel01;
    }
  
  public void actionPerformed (ActionEvent ae){
     ziehStappel01.spielkarte01.karteWurdeGezogen(); 
     System.out.println("geklickt");                                 
  }
  // Ende Methoden
} // end of ZiehkartenActionListener

