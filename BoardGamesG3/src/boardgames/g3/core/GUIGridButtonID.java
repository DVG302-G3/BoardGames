package boardgames.g3.core;

import javax.swing.JButton;

public class GUIGridButtonID extends JButton {

 private String cordinate;
 
 public GUIGridButtonID(String cordinate){
  this.cordinate = cordinate;
 }
 
 public String getStringId(){
  return cordinate;
 }
 
 
}
