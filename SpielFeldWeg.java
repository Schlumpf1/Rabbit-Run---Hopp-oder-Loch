import java.awt.*;
import javax.swing.*;

public class SpielFeldWeg extends JPanel
{
  public Spielfeld spielfeld01;
  public Spielfeld spielfeld02;
  public Color backgroundColor;
  
  public SpielFeldWeg(Spielfeld pSpielfeld01, Spielfeld pSpielfeld02){
    super();
    spielfeld01 = pSpielfeld01;
    spielfeld02 = pSpielfeld02;
    this.setBounds(0,0,200,200);
    this.setBackground(new Color(0, 200, 0));
    }
  
  public void paintComponent (Graphics g)                 
    {
        super.paintComponent(g);
        
        this.paintSpielFeldWeg(g);
        System.out.println("Y");     
    }
  public void paintSpielFeldWeg(Graphics g){
    g.setColor(Color.BLACK);
     
     g.drawLine(0,0,spielfeld02.getX()-spielfeld01.getX(),spielfeld02.getY()-spielfeld01.getY());    
     
     this.setVisible(true);   
   }
  

  }
