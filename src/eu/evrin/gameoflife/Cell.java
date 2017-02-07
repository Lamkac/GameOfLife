package eu.evrin.gameoflife;

public class Cell {
	public boolean live = false;
	public boolean nextLive = false;
	
	public void reload(){
		live=nextLive;
	}
	
}
