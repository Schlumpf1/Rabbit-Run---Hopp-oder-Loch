import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

/**
 *
 * @version 1.0 vom 28.09.2020
 * @author 
 */

public class SpielerKomminikator extends Client {
  
  // Anfang Attribute
  Spielfenster meinSpielfenster01;
  String meineTeamfarbe;
  private String spielerName;
  int spielerAnzahl;
  int spielfigurenAnzahl;
  int spielfelderAnzahl;
  
  boolean amSpielzug = false;
  // Ende Attribute
  
  public SpielerKomminikator(String pServerIP, int pServerPort){
    super(pServerIP,pServerPort);
    if(this.isConnected()){
      System.out.println("Verbindung zum Server wurde erfolgreich hergestellt");
    }
    else{
      System.out.println("Verbindung zum Server konnte nicht hergestellt werden");
      this.close();
      System.exit(0);
    }
  }
  
  public void processMessage(String pMessage){
    if(pMessage.equals("Color:null")){
      System.err.println("Keine Farbe bekommen");
      verbindungTrennen();  
    }
    else if(pMessage.startsWith("Color:")){
      meineTeamfarbe = pMessage.substring(6);
      System.out.println("Meine Farbe: "+meineTeamfarbe);  
    }
    else if(pMessage.equals("Spiel bereits voll")){
      System.out.println("Das Spiel ist bereits voll");
      verbindungTrennen();  
    }
    else if(pMessage.startsWith("SpielerAnzahl:")){
      spielerAnzahl = Integer.parseInt(pMessage.substring(14));
      Spielfenster.anzahlSpielFigurenJeTeam = spielerAnzahl;  
    }
    else if(pMessage.startsWith("Spielfelder:")){
      spielfelderAnzahl = Integer.parseInt(pMessage.substring(12));
         
//      meinSpielfenster01 = new Spielfenster(spielfelderAnzahl); 
    }
    else if(pMessage.equals("Grundlagen uebertragen beendet")){
      meinSpielfenster01 = new Spielfenster(this);
      
      meinSpielfenster01.generiereSpielfelder(spielfelderAnzahl);
      meinSpielfenster01.generiereTeams();
      meinSpielfenster01.kartenWert = -1;  
    }
    else if(pMessage.startsWith("Spielfiguren:")){
      spielfigurenAnzahl = Integer.parseInt(pMessage.substring(13));  
    }
    else if(pMessage.equals("Spielzug erlaubt")){
      amSpielzug = true;
      System.out.println("Ich bin am zug! :"+meineTeamfarbe);    
    }
    else if(pMessage.equals("Spielzug beendet")){
      amSpielzug = false;
      System.out.println("Ich bin nicht mehr am zug! :"+meineTeamfarbe);  
    }
    else if(pMessage.equals("AlteHasenfalle wegmachen")){
      meinSpielfenster01.deaktiviereHasenfalle();  
    }
    else if(pMessage.startsWith("NeueHasenfalle: ")){
      meinSpielfenster01.aktiviereHasenfalle(Integer.parseInt(pMessage.substring(16)));    
    }
                      
    else if(pMessage.startsWith("Kartenwert:")){
      if(amSpielzug){
        karteGezogen(Integer.parseInt(pMessage.substring(11)));
        System.out.println(pMessage);
      }  
    }
    else if(pMessage.startsWith("Gedreht, neues Loch bei:")){
      int spielfeldMitLoch = Integer.parseInt(pMessage.substring(24));
      System.out.println(pMessage);
      meinSpielfenster01.deaktiviereHasenfalle();
      meinSpielfenster01.aktiviereHasenfalle(spielfeldMitLoch);  
    }
    else if(pMessage.startsWith("Figur von Feld:")){
      String[] getrennt = pMessage.split(":");
      int feld1 = Integer.parseInt(getrennt[1]);
      int feld2 = Integer.parseInt(getrennt[3]);
      System.out.println("PMSG FIGURBEWEGUNG: "+pMessage);
      String textPosition = getrennt[getrennt.length-1];
      meinSpielfenster01.setzteSpielfigur(feld1,feld2,textPosition);
      //Infos an Speilfenster weitergeben
    }
    else if(pMessage.startsWith("Spielfarben:")){
      String[] getrennt = pMessage.split(":");
      Spielfenster.teamfarben = new String[getrennt.length-1];
      for (int i = 1; i < getrennt.length; i++) {
        Spielfenster.teamfarben[i-1] = getrennt[i];
        System.out.println(getrennt[i]);                            
      }
    }
    else if(pMessage.startsWith("Das Spiel ist zuende:")){            //SPIEL WURDE GEWONNEN
      String[] getrennt = pMessage.split(":");
      meinSpielfenster01.endscreen(getrennt[1]);
    }
    else if(  pMessage.equals("Server schließt!. Verbindung wird beendet")){            //SPIEL WURDE GEWONNEN
      verbindungTrennen();
    }
                                  
    else{
      System.out.println("Nachricht vom Server nicht verarbeitet!: "+pMessage);
    }
  }
  
  public void verbindungTrennen(){
    System.out.println("Verbindung zum Server wurde getrennt");
    this.close();
    System.exit(0);
  }
  
  /*
  * Soll aufgerufen werden, wenn der Spieler im Spielfenster auf den Ziehstappel klickt
  */
  public void karteZiehWunsch(){
    if(amSpielzug && keineKarteBereitsGezogenWorden()){
      this.send("Karte ziehen");
    }
    else {
      System.out.println("Ziehkartenwert nicht angefordert, weil nicht am Spielzug oder bereits gezogen wurde");
    } // end of if-else
  }
  
  public boolean keineKarteBereitsGezogenWorden(){
    if(meinSpielfenster01.kartenWert ==-1){
      return true;
    }
    else {
      return false;
    } // end of if-else
  }
  
  public void karteGezogen(int pWert){
    meinSpielfenster01.kartenWert = pWert;
    if(spielfigurenAnzahl <= 0 && pWert != 4) {           //Wenn der  Spieler keine moeglickeit hat seine karte zu benutzen
      this.send("Spielzug nicht moeglich");
      System.out.println("Spielzug nicht moeglich!");
    }
    else{
      meinSpielfenster01.ziehkarteZurMitteZiehen(new ZiehKarte(meinSpielfenster01,pWert));
      System.out.println("Neue Ziehkarte erstellt mit dem Wert: "+pWert);    
    }   
  }
  
  public void karteAktiviert(int pSpielfigurAnfangsfeld, int pSpielfigurStoppfeld){
    this.send("Figur von Feld:"+pSpielfigurAnfangsfeld+":zu Feld:"+pSpielfigurStoppfeld);
    //Regeln im Client überprueft, ob eingehalten etc.
    meinSpielfenster01.kartenWert =-1;                                                    //Karte kann ab der naechsten Runde wieder gezogen werden
  }
  
  public void karteAktiviertDurchDrehen(){
    this.send("Drehen");
    meinSpielfenster01.kartenWert =-1;
    //Regeln im Client überprueft, ob eingehalten etc.
  }
  
  public String getSpielerName() {
    return spielerName;
  }
  
  public void letzteSpielfigur(String pFarbe){
    this.send("nur noch eine Spielfigur im Spiel von Team:"+pFarbe);
  }
  
  public static void main(String[] args) {
    System.out.println("Programm gestartet");
    String ipAdresseDesServers = ipAdresseFensterEingabe();
    System.out.println(ipAdresseDesServers);
    System.out.println("Adresse eingegeben");
    new SpielerKomminikator(ipAdresseDesServers,5555);
  }
  
  public static void println(String pMsg){
    System.out.println("SpielerKomminikator.class: "+pMsg);
  }
  
  public static String ipAdresseFensterEingabe(){
   Object antwort = JOptionPane.showInputDialog(null, "Die IP-Adresse des Servers eingeben");
   System.out.println("Eingegeben wurde: "+antwort);
    return antwort.toString();
  }
  

  // Ende Methoden
} // end of SpielerKomminikator

