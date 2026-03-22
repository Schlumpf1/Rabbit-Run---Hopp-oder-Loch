import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Color;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 12.10.2020
 * @author 
 */

public class Startfenster extends JFrame {
  // Anfang Attribute
  private JButton    jButton1     = new JButton();
  private JButton    jButton2     = new JButton();
  private JButton    jButton3     = new JButton();
  private JTextArea  jTextField1  = new JTextArea();   
  private JTextField jTextField2  = new JTextField();  //IP-adresseeingebfeld 
  // Ende Attribute
  
  public Startfenster() { 
    // Frame-Initialisierung
    super();
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    int frameWidth = 945; 
    int frameHeight = 558;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    setTitle("Startfenster");
    setResizable(false);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten
    
    jButton1.setBounds(50, 300, 200, 50);
    jButton1.setText("Spiel erstellen");
    jButton1.setMargin(new Insets(2, 2, 2, 2));
    jButton1.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton1_ActionPerformed(evt);
      }
    });
    cp.add(jButton1);
    
    jButton2.setBounds(300, 300, 200, 50);
    jButton2.setText("Spiel beitreten");
    jButton2.setMargin(new Insets(2, 2, 2, 2));
    jButton2.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton2_ActionPerformed(evt);
      }
    });
    cp.add(jButton2);
    
    jButton3.setBounds(300, 300, 200, 50);
    jButton3.setText("IP-Adresse bestätigen");
    jButton3.setMargin(new Insets(2, 2, 2, 2));
    jButton3.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent evt) { 
        jButton3_ActionPerformed(evt);
      }
    });
    jButton3.setVisible(false);
    cp.add(jButton3);
    
    jTextField1.setBounds(300, 75, 200, 50);
    jTextField1.setVisible(false);
    jTextField1.setBackground(new Color(0,0,0,0));
    jTextField1.setEditable(false);
    jTextField1.setText("IP-Adresse kann wie folgt aussehen  \n 192.168.178.39");
    cp.add(jTextField1);
    
    jTextField2.setBounds(300, 150, 200, 50);
    jTextField2.setVisible(false);
    cp.add(jTextField2);
    // Ende Komponenten
    
    setVisible(true);
  } // end of public Startfenster
  
  // Anfang Methoden
  
  public static void main(String[] args) {
    new Startfenster();
  } // end of main
  
  public void jButton1_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    
  } // end of jButton1_ActionPerformed

  public void jButton2_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
       this.jButton1.setVisible(false);
       this.jButton2.setVisible(false);
    
       jButton3.setVisible(true);
       jTextField1.setVisible(true);
    
       jTextField2.setVisible(true);
    
  } // end of jButton2_ActionPerformed
  
  public void jButton3_ActionPerformed(ActionEvent evt) {
    // TODO hier Quelltext einfügen
    
  } // end of jButton3_ActionPerformed
  
  // Ende Methoden
} // end of class Startfenster

