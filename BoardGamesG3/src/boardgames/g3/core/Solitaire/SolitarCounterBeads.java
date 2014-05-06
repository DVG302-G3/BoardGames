package boardgames.g3.core.Solitaire;

public class SolitarCounterBeads  {

	int beadsTotal;
	int beadsTaken;
	int beadsLeft;

	public SolitarCounterBeads(int total) {
		this.beadsTotal = total;
	}

	public void update(){
		beadsTaken++;
		beadsLeft = (getBeadsTotal() - beadsTaken);
	}
	
	public String getBeadsTaken(){
		return Integer.toString(beadsTaken);
	}
	
	public int getBeadsTotal(){
		return beadsTotal;
	}
	
	public String getBeadsLeft(){
		return Integer.toString(beadsLeft);
	}

}
