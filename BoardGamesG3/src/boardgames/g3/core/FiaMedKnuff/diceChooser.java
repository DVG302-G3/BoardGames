package boardgames.g3.core.FiaMedKnuff;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;

public class diceChooser extends JCheckBox {

 private Random diceRandom = new Random();
 private int diceNumber;

 
 public diceChooser(){
  returnDice();
 }
 
 public void returnDice(){
  diceNumber = diceRandom.nextInt(6);
  
  
  switch(diceNumber){
   case 0: this.setIcon(new ImageIcon("src\\boardgames\\img\\dice1.png"));
    System.out.println("1");
    break;
    
   case 1: this.setIcon(new ImageIcon("src\\boardgames\\img\\dice2.png"));
   System.out.println("2");
    break;
    
   case 2: this.setIcon(new ImageIcon("src\\boardgames\\img\\dice3.png"));
   System.out.println("3");
    break;
    
   case 3: this.setIcon(new ImageIcon("src\\boardgames\\img\\dice4.png"));
   System.out.println("4");
    break;
    
   case 4: this.setIcon(new ImageIcon("src\\boardgames\\img\\dice5.png"));
   System.out.println("5");
    break;
    
   case 5: this.setIcon(new ImageIcon("src\\boardgames\\img\\dice6.png"));
   System.out.println("6");
    break;
   
   
  }
  
  
 }
 
}
