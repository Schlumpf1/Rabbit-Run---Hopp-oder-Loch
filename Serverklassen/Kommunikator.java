import java.util.ArrayList;
import java.net.InetAddress;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetSocketAddress;



/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 28.09.2020
 * @author 
 */

public class Kommunikator extends Server {
  
  // Anfang Attribute
  static int port = 5555;
  public ArrayList<Clientsaver> verbundeneClients = new ArrayList<Clientsaver>();
  public ArrayList<String> farben = new ArrayList<String>();                        //moegliche Farben für die Teams
  
  public static int               spielermaximalAnzahl = 2;
  public static int               spielfelderAnzahl = 20;                                         //Felder die man bis zum Sieg hat inklusive des Zielfeldes
  public static int               spielFiguranzahl = 4;                                           //Startzahl der Figuren jedes Teams
  public static InetAddress       myIpAdressIPV4;
                                                
  public Serveruebersicht konsole;                                                                                  
  
  // Ende Attribute
  
  public Kommunikator() {
    super(port);
    konsole = new Serveruebersicht(this);
    setUpIp();
    konsole.statistInfo();
    konsole.println("Server startet");
    this.initialisiereFarben();
    
    
    konsole.println("-------------------");
    konsole.println("Server bereit - Warte auf Spieler");
    verbundeneClients.trimToSize();
    konsole.println("neue Spieleranzahl: "+ verbundeneClients.size());
  }
  
  public void processNewConnection(String pIP, int pPort){
    if(!spielVoll()) {
      konsole.println("verbindung mit neuem Client: (IP: "+ pIP+ ", Port: "+pPort);
      Clientsaver localClientsaver = new Clientsaver(pIP,pPort);                              //Spieler bekommt Infos zum Spiel
      verbundeneClients.add(localClientsaver);
      verbundeneClients.trimToSize();
      konsole.println("neue Spieleranzahl: "+ verbundeneClients.size());
      this.send(localClientsaver,"Verbindung mit Server hergestellt");
      vergebeFarbe(localClientsaver);
      
      if(spielVoll()) {
        versendeGrundlagenDesSpielFeldsToEveryone();
        
        //SPIEL STARTEN 
        activateSpielzug(getNextSpieler(null));                                           
      }
    } // end of if
    else{
      this.send(pIP,pPort,"Spiel bereits voll");                                              //Verbindung wird beendet, Client wird nicht gespeichert       
    }
  }

  public void processMessage(String pIP, int pPort, String pMessage){
    if(pMessage.equals("Karte ziehen")){
      int ziehwert = zieheKarte();
      send(getClientSaver(pIP,pPort),"Kartenwert:"+Integer.toString(ziehwert));
      konsole.println("Karte wurde angefordert und hat den kartenwert von: "+ziehwert);
    }
    else if(pMessage.equals("Spielzug nicht moeglich")){
        //naechsterSpieler                              //kartenwert nicht gleich 4 und keine Spielfigur mehr vorhanden
        Clientsaver client = getClientSaver(pIP,pPort);
        deactivateSpielzug(client);
        activateSpielzug(getNextSpieler(client));    
    }
    else if(pMessage.startsWith("Figur von Feld:")){   //Figur wurde versetzt
          
          String[] getrennt = pMessage.split(":");
          int feld1 = Integer.parseInt(getrennt[1]);
          int feld2 = Integer.parseInt(getrennt[3]);
          konsole.println("Figur von Feld: "+feld1+" zu Feld: "+feld2+" bewegt");
          bewegeSpielFigur(getClientSaver(pIP,pPort),feld1,feld2);
          
          if(feld2 == 0){
            //Spieler hat gewonnen
            spielZuende(getClientSaver(pIP,pPort));         
          }
          else {
            Clientsaver client = getClientSaver(pIP,pPort);
            deactivateSpielzug(client);
            activateSpielzug(getNextSpieler(client));  
          } // end of if-else
    }
    else if(pMessage.equals("Drehen")){
            //Zufaelliges Loch erkoren
            this.sendToAll("AlteHasenfalle wegmachen");
            this.sendToAll("NeueHasenfalle: "+getRandomNumber(2,spielfelderAnzahl-3));
            //naechsterSpieler                              
            Clientsaver client = getClientSaver(pIP,pPort);
            deactivateSpielzug(client);
            activateSpielzug(getNextSpieler(client));    
    }
    else if(pMessage.startsWith("nur noch eine Spielfigur im Spiel von Team:")){
              String[] getrennt = pMessage.split(":");
            this.spielZuende(getClientSaver(getrennt[1]));    
    }
  
         
  }

  public void processClosingConnection(String pIP, int pPort){
    konsole.println("verbindung mit  Client abgebrochen: (IP: "+ pIP+ ", Port: "+pPort);
    Clientsaver client = getClientSaver(pIP,pPort);
    if(client != null){
      
      if(!client.getTeamfarbe().equals("null")){
        farben.add(0,client.getTeamfarbe());
        konsole.println("Farbe freigeworden: " + client.getTeamfarbe());  
        client.setTeamfarbe("null");  
      }
      
      verbundeneClients.remove(client);
      
    }
    verbundeneClients.trimToSize();
    if(verbundeneClients.size()==1){
      this.spielZuende(verbundeneClients.get(0));
    }
  }
  
  /*
  * Vergibt freie Farben
  */
  public void vergebeFarbe(Clientsaver pClient){
    String farbe = "null";
    farben.trimToSize();
    
    if(!farben.get(0).equals("null")){
      farbe = farben.get(0);
      farben.remove(0);
    }
    this.send(pClient,"Color:"+ farbe);
    pClient.setTeamfarbe(farbe);
  }
  
  public Clientsaver getClientSaver(String pTeamfarbe){
    verbundeneClients.trimToSize();
    for (int i = 0; i < verbundeneClients.size(); i++) {
      if(verbundeneClients.get(i).getTeamfarbe().equals(pTeamfarbe)){
        return verbundeneClients.get(i);
      }
    }
    return null;
  }
  
  public int getClientSaverIndex(String pTeamfarbe){
    verbundeneClients.trimToSize();
    for (int i = 0; i < verbundeneClients.size(); i++) {
      if(verbundeneClients.get(i).getTeamfarbe().equals(pTeamfarbe)){
        return i;
      }
    }
    return -1;
  }
  
  public Clientsaver getClientSaver(String pIP, int pPort){
    verbundeneClients.trimToSize();
    for (int i = 0; i < verbundeneClients.size(); i++) {
      if(verbundeneClients.get(i).getIP().equals(pIP) && verbundeneClients.get(i).getPort() == pPort){
        return verbundeneClients.get(i);
      }
    }
    return null;
  }
  
  /*
  * gibt an ob das Spiel voll ist
  */
  public boolean spielVoll(){
    verbundeneClients.trimToSize();
    if(verbundeneClients.size() == spielermaximalAnzahl) {
      return true;
    } else {
      return false;
    } // end of if-else
  }
  
  /*
  * zufallswert der zurueckgegeben wird (zwischen 1-4)
  */  
  public int zieheKarte(){
    return getRandomNumber(1,5);          //1 = 1 weit gehen, 2 = 2 weit gehen, 3 = 3 weit gehen, 4 = drehen
  }
  
  /**
   * Gibt den naechsten Spieler der am Zug ist zurueck
  */
  public Clientsaver getNextSpieler(Clientsaver zuletztgespielter){
    if(zuletztgespielter == null){                                                                          //Erster Spielzug
       return verbundeneClients.get(0);
    }
    else if (getClientSaverIndex(zuletztgespielter.getTeamfarbe()) == verbundeneClients.size()-1) {         //wieder von vorne
        return verbundeneClients.get(0);
    }
    else {                                                                                                  //naechster 
      return verbundeneClients.get(getClientSaverIndex(zuletztgespielter.getTeamfarbe())+1);  
    } // end of if-else
  }
  
  /*
  * Grundlagen werden versendet, gemeinnt sind Infos zum Spielfenster
  */
  public void versendeGrundlagenDesSpielFelds(Clientsaver pClient){
    verbundeneClients.trimToSize();
    
    //Spielermaximalanzahl
    this.send(pClient,"SpielerAnzahl:"+Integer.toString(spielermaximalAnzahl));
    //Spielfelderanzahl
    this.send(pClient,"Spielfelder:"+Integer.toString(spielfelderAnzahl));
    //maximaleSpielfigurenanzahl
    this.send(pClient,"Spielfiguren:"+Integer.toString(spielFiguranzahl));
    //Teaminfos uebergeben
    String farben = "Spielfarben";
    for (int i = 0; i <verbundeneClients.size(); i++) {
      //this.send(pClient,"neues Team: "+verbundeneClients.get(i).getTeamfarbe());
      farben = farben +":"+verbundeneClients.get(i).getTeamfarbe();
    }
    this.send(pClient,farben);
    this.send(pClient,"Grundlagen uebertragen beendet");
  }
  
  /*
  * Grundlagen werden an alle Spieler versendet, gemeint sind Infos zum Spielfenster
  */
  public void versendeGrundlagenDesSpielFeldsToEveryone(){
    verbundeneClients.trimToSize();
    for (int i = 0; i < verbundeneClients.size(); i++) {
      if(verbundeneClients.get(i) != null){
         versendeGrundlagenDesSpielFelds(verbundeneClients.get(i));
      }
    }
    konsole.println("Grundlagen an Alle versendet");
  }
  
  /*
   *sendet an alle die Message bis auf den angegebenen Client
  */
  public void sendToAllWithout(Clientsaver pClient, String pMsg){
    verbundeneClients.trimToSize();
    for (int i = 0; i < verbundeneClients.size(); i++) {
      if(verbundeneClients.get(i) != pClient){
         this.send(verbundeneClients.get(i),pMsg);
      }
    }       
  }
  
  public void send(String pIP,int pPort,String pMsg){
    super.send(pIP,pPort,pMsg);
    konsole.println("Server an die IP( "+pIP+ "), Port( "+pPort+ "),Farbe("+getClientSaver(pIP,pPort).getTeamfarbe()+": "+pMsg);
  }
    
  public void send(Clientsaver pClient, String pMsg){
    this.send(pClient.getIP(),pClient.getPort(),pMsg);
  }
  
  public void activateSpielzug(Clientsaver pClient){
    this.send(pClient,"Spielzug erlaubt");
  }
  
  public void deactivateSpielzug(Clientsaver pClient){
    this.send(pClient,"Spielzug beendet");
  }
  
  public void bewegeSpielFigur(Clientsaver pClient,int pStartPosition, int pStoppposition){
    konsole.println("Clientfarbe: "+pClient.getTeamfarbe());
    this.sendToAllWithout(pClient,"Figur von Feld:"+pStartPosition+":zu Feld:"+pStoppposition+":"+pClient.getTeamfarbe());
  }
  
  public void neuesLochErdrehen(){
    int feldNummer = (int)((Math.random()) * (spielfelderAnzahl-1) + 1);
    this.sendToAll("Gedreht, neues Loch bei:"+feldNummer); 
  }
  
  public static int getRandomNumber(int pMinimum, int pMaximum){
    return (int)(Math.random() * (pMaximum-pMinimum) + pMinimum);
  }
  
  public void initialisiereFarben(){
    farben.add("BLAU");
    farben.add("GELB");
    farben.add("null");
    String localFarben ="";
    farben.trimToSize();
    for (int i = 0; i < farben.size(); i++) {
      localFarben = localFarben +farben.get(i)+ ";";
    } 
    konsole.println("Farben hinzugefuegt ["+localFarben+"]");
  }
  
  /*
  * pClient gibt den Sieger Client an
  */
  public void spielZuende(Clientsaver pClient){
    if(pClient!=null){
      konsole.println(pClient.getTeamfarbe()+ " hat das Spiel gewonnen");
      sendToAll("Das Spiel ist zuende:"+pClient.getTeamfarbe());
    }
    else {
      konsole.println("Kein Client bekannt, der gewonnen haben koennte. Spiel ist zuende");
      System.exit(0);
      this.close();
    } // end of if-else
  }
  
  public static void main(String[] args) {
    new Kommunikator();
    
    System.out.println("Ip-Addresse des Servers:" +myIpAdressIPV4.getHostAddress());
  } // end of main
  
  public void setUpIp(){
    try {
      myIpAdressIPV4 =  Inet4Address.getLocalHost();
                          
    } catch(Exception e) {
    
    } finally {
    
    } // end of try
    konsole.setTitle("IP-Adresse Server: "+myIpAdressIPV4);
  }
  
  public void close(){
    sendToAll("Server schließt!. Verbindung wird beendet");                                //COUNTDOWN
    super.close();
    System.exit(0);
  }
} // end of kommunikator

