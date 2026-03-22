import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 28.09.2020
 * @author 
 */

public class Clientsaver {
  
  // Anfang Attribute
  private int port = -1;
  private String IP = "";
  private String teamfarbe = "null";
  int[] spielfigurenPosition;
  // Ende Attribute
  
  public Clientsaver(String IP,int port) {
    this.port = port;
    this.IP = IP;
  }

  // Anfang Methoden
  public int getPort() {
    return port;
  }

  public void setPort(int portNeu) {
    port = portNeu;
  }

  public String getIP() {
    return IP;
  }

  public void setIP(String IPNeu) {
    IP = IPNeu;
  }

  public String getTeamfarbe() {
    return teamfarbe;
  }

  public void setTeamfarbe(String teamfarbeNeu) {
    teamfarbe = teamfarbeNeu;
  }

  // Ende Methoden
} // end of Clientsaver

