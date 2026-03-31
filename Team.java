import java.awt.*;
import  javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
 
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 09.09.2019
 * @author Florian Denk
 */

public class Team extends JPanel implements MouseListener{
  
  // Anfang Attribute
  private static String[] moeglicheFarben = {"orange","gruen","violett"};
  private String teamfarbe;
  private static int anzahlMaxFiguren;
  public SpielFigur[] meineSpielFiguren;
  private Spielkarte spielkarte;
  private int counterOfPlacedFiguren = 0;
  private int teamNumber;
  public SpielFigur[] meineSpielFigurenZuhause;
  
  boolean aufDemPanel = false;
  
  // Ende Attribute
  
/**
 *
 *
 *
 */
  public Team(int pTeamNumber, String teamfarbe, int anzahlMaxFiguren, Spielkarte spielkarte) {
    super();
    
    this.setBounds(0,0,100,40);
    
    this.teamNumber = pTeamNumber;
    
    if(teamNumber == 1){
        this.setLocation(5,5);
      }
    else if(teamNumber == 2){
        this.setLocation((spielkarte.frameWidth-105),5);
      }
    this.setBackground(Color.LIGHT_GRAY);
    
    this.setLayout(new GridLayout());
    
    
    
     
    
    
    
    this.teamfarbe = moeglicheFarben[teamNumber];
    this.anzahlMaxFiguren = anzahlMaxFiguren;
    this.spielkarte = spielkarte;
    
    this.meineSpielFiguren = new SpielFigur[anzahlMaxFiguren];
    meineSpielFigurenZuhause = new SpielFigur[meineSpielFiguren.length+1];
    schaffeTeamFiguren();
    
    spielkarte.addJPanel(this);
    
    this.setVisible(true);
    
    System.out.println(""+teamNumber + "      "+this.getX() + "   " +this.getY()); 
  }

  // Anfang Methoden
  private void schaffeTeamFiguren(){
    for (int i = 0; i < anzahlMaxFiguren; i++) {
      int b = i;
      b++;
      meineSpielFiguren[i] = new SpielFigur(this, teamfarbe,spielkarte, b);
      this.addSpielFigurToThis(meineSpielFiguren[i]);
    }
    
    }
  
  public void entferne(SpielFigur pSpielFigur){
    meineSpielFigurenZuhause[pSpielFigur.spielFigurNummer]  = null;
    pSpielFigur.zuhause = false;
    this.remove(pSpielFigur);
    }
  
  public String getTeamfarbe() {
    return teamfarbe;
  }

  public int getAnzahlMaxFiguren() {
    return anzahlMaxFiguren;
  }
  
  public ZiehKarte zieheKarte(){
    return spielkarte.ziehStappel.zieheKarteVomStappel();
    }
  
  public void paintComponent (Graphics g)                 //Grafische Veränderung
    {
        super.paintComponent(g);
        for (int i = 0; i < meineSpielFigurenZuhause.length; i++) {
      
            if(meineSpielFigurenZuhause[i] != null){
                 this.add(meineSpielFigurenZuhause[i]);
            }
        } 
    }
  public void addSpielFigurToThis(SpielFigur pSpielFigur){
     pSpielFigur.setBounds(0,0,23,32);
     meineSpielFigurenZuhause[pSpielFigur.spielFigurNummer] = pSpielFigur;
      
      pSpielFigur.setVisible(true);
      this.setVisible(true);   
      System.out.println("figur hinzugefügt");
       
      pSpielFigur.zuhause = true;
      this.setVisible(true);
    }
  
  @Override
    public void mouseClicked(MouseEvent e) {
        
    }
                                                
    @Override
    public void mousePressed(MouseEvent e) {
    if(aufDemPanel){
      //spielkarte.mausGeklickt(meineSpielFiguren[counterOfPlacedFiguren]);
       counterOfPlacedFiguren++;
       System.out.println("Team clicked"); 
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
  // Ende Methoden
} // end of Team

