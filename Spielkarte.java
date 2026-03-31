import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Component;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 09.09.2019
 * @author Florian Denk
 */

public class Spielkarte extends JPanel{
  
  // Anfang Attribute
  public Spielfeld[] spielFelder;
  public Team[] teilnehmendeTeams;
  public ArrayList<SpielFigur> teilnehmendeSpielFiguren = new ArrayList<SpielFigur>(); 
  public ZiehStappel ziehStappel;
  private ArrayList<JPanel> contentList = new ArrayList<JPanel>();                  //Grafische gebrauchte Attribute
  private static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();         //Grafische gebrauchte Attribute
  public static  int frameWidth  = d.width;                                //Grafische gebrauchte Attribute
  public static  int frameHeight = d.height;
  public static  boolean karteIstInDerMitte = false;
  public static ZiehKarte gezogeneKarte;
   
  public ArrayList<TrampelWegFeld> grafischGenutzeSpielFelder = new ArrayList<TrampelWegFeld>();
                                 
  // Ende Attribute
  
  // Anfang Methoden
  public Spielkarte(int kartenStappelGroesse, int anzahlTeams, int pAnzahlSpielFelder){
    super();
     
    ziehStappel = new ZiehStappel(kartenStappelGroesse,this);
    teilnehmendeTeams = new Team[anzahlTeams];
    
    spielFelder = new Spielfeld[pAnzahlSpielFelder];
    schaffeTeams();
    
    this.addMouseListener(new MouseListenerSpielKarte(this));
    
    this.add(ziehStappel);
    this.setComponentZOrder(ziehStappel,0);
    
    
    generiereSpielfelder();
    this.setLayout(null);                               //Grafische Veraenderung
    
    
    
    this.setBackground(new Color(0, 200, 0));           //Grafische Veraenderung
    this.setVisible(true);                              //Grafische Veraenderung
    
   for (int i = 0; i < teilnehmendeSpielFiguren.size(); i++) {
     teilnehmendeSpielFiguren.get(i).setToStartPositionTeam();
    }
    
    schaffeTrampelWege();
    
    this.setVisible(true); 
    }
  
  private void schaffeTeams(){
    int groesseTeams = 3;
    int teamNumberCounter = 1; 
    for (int i = 0; i < teilnehmendeTeams.length; i++) {
      teilnehmendeTeams[i] = new Team(teamNumberCounter,"violett", groesseTeams, this);
      teamNumberCounter++;
      
        
        
      for (int b = 0;b < groesseTeams ;b++) {
         int index = (i* groesseTeams + b);
         teilnehmendeSpielFiguren.add(teilnehmendeTeams[i].meineSpielFiguren[b]);
      } // end of for
    }
    
    }
  
  private void generiereSpielfelder(){
    for (int i = 0; i < spielFelder.length; i++) {
      spielFelder[i] = new Spielfeld(this,i,spielFelder.length);
      addJPanel(spielFelder[i]);                                    //Grafische Veraenderung
      this.setComponentZOrder(spielFelder[i],1);
      
      }
    
    }
  
    
      
  private boolean getZufallsWert(int pHaeufigkeitvonTrue){
    if(pHaeufigkeitvonTrue>=((Math.random()) * 100 + 1)){
      return true;   
     }
    else {
      return false;
    } // end of if-else
    
    }
  
  public void addJPanel(JPanel pJPanel){      //Grafische Veraenderung  & Tests
    
    this.add(pJPanel);
    
    }
  
  public void setzeSpielFigurWeiter(SpielFigur pSpielFigur, ZiehKarte pZiehKarte){
    int neueSpielFeldNummer = pSpielFigur.aktuellesSpielFeld.getSpiefeldNummer();
    
    pSpielFigur.setBounds(0,0,23,32);
    pSpielFigur.setLocation(8,3);
    
    pSpielFigur.aktuellesSpielFeld.entferneSpielFigur(pSpielFigur); //SpielFigur ist nirgendwo
    
    neueSpielFeldNummer = neueSpielFeldNummer -pZiehKarte.getWert();
    
    if(neueSpielFeldNummer < 0){
         neueSpielFeldNummer = 0;
      }
    
    spielFelder[neueSpielFeldNummer].fuegeFigurNeuHinzu(pSpielFigur);
    }
  
  
  public void setzeSpielFigurWeiter(SpielFigur pSpielFigur, int pFelderVor){
    int neueSpielFeldNummer = pSpielFigur.aktuellesSpielFeld.getSpiefeldNummer();
    
    pSpielFigur.setBounds(0,0,23,32);
    pSpielFigur.setLocation(8,3);
    
    pSpielFigur.aktuellesSpielFeld.entferneSpielFigur(pSpielFigur); //SpielFigur ist nirgendwo
    
    neueSpielFeldNummer = neueSpielFeldNummer -pFelderVor;
    
                                            
    if(neueSpielFeldNummer < 0){
         neueSpielFeldNummer = 0;
         
      }
    
    spielFelder[neueSpielFeldNummer].fuegeFigurNeuHinzu(pSpielFigur);
    
    }
  
  
    public void praesentiereKarte(ZiehKarte pZiehKarte){
    this.add(pZiehKarte);
    
    System.out.println("karteWurdeGezogen()");
    pZiehKarte.setLocation((frameWidth/2),(frameHeight/2));
    this.setVisible(true);
    this.setComponentZOrder(pZiehKarte,1);
    
    
    pZiehKarte.setVisible(true);
    
      }
  
  private void zieheKarteAnDenRand(ZiehKarte pZiehKarte){
    
    pZiehKarte.setLocation(50,200);
    
    
    }
  
  public void karteWurdeGezogen(){
        ZiehKarte pZiehKarte = ziehStappel.zieheKarteVomStappel();  
        this.praesentiereKarte(pZiehKarte);
        karteIstInDerMitte = true;
        gezogeneKarte = pZiehKarte;
        
    }
  
  public void mausGeklickt(){
     
     if(karteIstInDerMitte ){
      zieheKarteAnDenRand(gezogeneKarte); 
      karteIstInDerMitte = false;                    
      }
    
    }
  
  public void mausGeklickt(SpielFigur pSpielFigur){
    
    if(!karteIstInDerMitte && gezogeneKarte != null){
        
        if (pSpielFigur.zuhause == true) {
      
           setzeSpielFigur(pSpielFigur, gezogeneKarte);
           pSpielFigur.zuhause = false;
           } // end of if
        
        else {
      
             setzeSpielFigurWeiter(pSpielFigur, gezogeneKarte);
             } // end of if-else
  
        gezogeneKarte = null;
        this.setVisible(true);
        }
  } //Methoden-Ende von mausGeklickt(SpielFigur pSpielFigur)
  
  public void setzeSpielFigur(SpielFigur pSpielFigur, ZiehKarte pZiehKarte){
    Team teamLocal = pSpielFigur.getMeinTeam();
    teamLocal.entferne(pSpielFigur);
    //teamLocal.setVisible(false);                                // macht Team unsichtbar nachdem eine Spielfigur des Teams gegeangen ist
    
    pSpielFigur.setVisible(true);
                                  
    int neueSpielFeldNummer = spielFelder.length -1;
    
    System.out.println("Figur auf SpielFeld gesetzt");
    
    
    if(neueSpielFeldNummer < 0){
         neueSpielFeldNummer = 0;
         
      }
    pSpielFigur.setBounds(0,0,23,32);
    pSpielFigur.setLocation(8,3); 
    spielFelder[neueSpielFeldNummer].fuegeFigurNeuHinzu(pSpielFigur);
    

    System.out.println("team: "+ teamLocal.getTeamfarbe()+"  "+ pSpielFigur.getZuhause());
    }
  
//  public SpielFigur getNearestFigur(Team pTeam, Point pP){
//    int localX = pP.x;
//    int localY = pP.y;
//    
//    SpielFigur[] localFigurenListe = pTeam.meineSpielFiguren;
//    SpielFigur returnedSpielFigur = localFigurenListe[0];
//    
//    for (int i = 1; i < localFigurenListe.length; i++) {
//      if(localFigurenListe[i].getAktuellesSpielFeld() != null) {
//        
//        if (getDistanceTo(localFigurenListe[i],pP) > getDistanceTo(returnedSpielFigur, pP)) {
//          returnedSpielFigur = localFigurenListe[i];
//        }
//         
//      }
//       
//    } 
//    
//    return returnedSpielFigur;
//    
//    }
//  public int getDistanceTo(SpielFigur pSpielFigur, int pX, int pY){
//    int xDistance = pSpielFigur.getX() - pX;
//    int yDistance = pSpielFigur.getY() - pY;
//    return (int)(Math.sqrt(Math.pow(xDistance,2) + Math.pow(yDistance,2)));  
//    }
//  
//  public int getDistanceTo(SpielFigur pSpielFigur, Point pP){
//    int xDistance = pSpielFigur.getX() - pP.x;
//    int yDistance = pSpielFigur.getY() - pP.y;
//    return (int)(Math.sqrt((int)Math.pow(xDistance,2) + Math.pow(yDistance,2)));  
//    }


  public void paintComponent (Graphics g)                 //Grafische Veraenderung
    {
        super.paintComponent(g);
        paintTrampelWege(g);     
    }
  
  public void paintTrampelWege (Graphics g)                 //Grafische Veraenderung
    {
        g.setColor(Color.orange);
        for (int i = 0; i < spielFelder.length-1; i++) {
            //g.drawLine(spielFelder[i].getX()+20,spielFelder[i].getY()+20,spielFelder[i+1].getX()+20, spielFelder[i+1].getY()+20);
            double steigung = (spielFelder[i].getY()+20-spielFelder[i+1].getY()+20)/(spielFelder[i].getX()+20 - spielFelder[i+1].getX()+20 );
            g.drawOval(spielFelder[i].getX()+20 + i,spielFelder[i].getY()+ 20 + ((int)(steigung * i)),5,5);;
        }
            
    }
    public void schaffeTrampelWege(){
    
    
       
           
    
      }    
    
    
    
   
  
  
  
    
    
  
  // Ende Methoden
} // end of Spielkarte

