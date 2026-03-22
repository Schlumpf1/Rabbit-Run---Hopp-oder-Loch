import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 11.10.2020
 * @author 
 */

public class Serveruebersicht extends JFrame {
  // Anfang Attribute
  private JTextArea jTextArea1 = new JTextArea("");
    private JScrollPane jTextArea1ScrollPane = new JScrollPane(jTextArea1);
  private JTextArea jTextArea2 = new JTextArea("");
    private JScrollPane jTextArea2ScrollPane = new JScrollPane(jTextArea2);
  private JButton jButton1 = new JButton();
  
  Kommunikator motherStart;
  // Ende Attribute
  
  public Serveruebersicht(Kommunikator pKommunikator) { 
    // Frame-Initialisierung
    super();
    motherStart = pKommunikator;
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 685; 
    int frameHeight = 615;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Serveruebersicht");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jTextArea1ScrollPane.setBounds(14, 5, 592, 52);
    jTextArea1.setEditable(false);
    cp.add(jTextArea1ScrollPane);
    
    jTextArea2ScrollPane.setBounds(17, 73, 592, 364);
    jTextArea2.setEditable(false);
    cp.add(jTextArea2ScrollPane);
    
    jButton1.setBounds(24, 451, 259, 25);
    jButton1.setText("jButton1");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    cp.add(jButton1);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Serveruebersicht
  
  // Anfang Methoden
  
  public void statistInfo(){
    jTextArea1.setText("IP-Adresse:" +motherStart.myIpAdressIPV4.getHostAddress()+"         Port:"+motherStart.port+"\n");  
  }
  
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    motherStart.close();
    
  } // end of jButton1_ActionPerformed
  
  public void println(String pMessage){
    jTextArea2.setText(jTextArea2.getText()+ "\n" +pMessage);
  }

  // Ende Methoden
} // end of class Serveruebersicht

