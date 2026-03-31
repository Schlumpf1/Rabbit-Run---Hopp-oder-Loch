import java.awt.event.*;

public class MouseListenerSpielKarte implements MouseListener{
  Spielkarte spielkarte01;
  boolean aufDemPanel = false;
  public MouseListenerSpielKarte(Spielkarte pSpielKarte){
    super();
    spielkarte01 = pSpielKarte;
    }
  
  @Override
    public void mouseClicked(MouseEvent e) {
        
    }
                                                
    @Override
    public void mousePressed(MouseEvent e) {
    if(aufDemPanel){
      
           spielkarte01.mausGeklickt();
           }   
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
