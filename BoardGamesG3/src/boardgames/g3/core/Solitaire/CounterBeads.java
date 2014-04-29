package boardgames.g3.core.Solitaire;

import boardgames.g3.GUI.BoardGamesSolitarGUI;

public class CounterBeads {
	
	int beadsTaken;
	int beadsLeft;
	
	
	
	public void setBeadsTaken(int beadTaken){
		this.beadsTaken = beadTaken;	
	}
	
	public void setBeadsLeft(int beadLeft){
		this.beadsLeft = beadLeft;
	}
	
	public String getBeadsTaken(){
		return Integer.toString(beadsTaken);
	}
	
	public String getBeadsLeft(){
		return Integer.toString(beadsLeft);
	}
}
