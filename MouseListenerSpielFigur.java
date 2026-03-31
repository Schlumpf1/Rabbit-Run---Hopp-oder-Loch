import java.awt.event.*;

public class MouseListenerSpielFigur implements MouseListener{
  Spielkarte spielkarte01;
  boolean aufDemPanel = false;
  SpielFigur mySpielFigur;
  
  public MouseListenerSpielFigur(Spielkarte pSpielKarte, SpielFigur pSpielFigur){
    super();
    spielkarte01 = pSpielKarte;
    mySpielFigur = pSpielFigur;
    }
  
  @Override
    public void mouseClicked(MouseEvent e) {
    if(aufDemPanel){
      spielkarte01.mausGeklickt(mySpielFigur);
      System.out.println("Spielfigur geklickt");
      }    
    }
                                                
    @Override
    public void mousePressed(MouseEvent e) {
              
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
           aufDemPanel = true;    
    }

    @Override
    public void mouseExited(MouseEvent e) {
           aufDemPanel = false;    
    }
  }
