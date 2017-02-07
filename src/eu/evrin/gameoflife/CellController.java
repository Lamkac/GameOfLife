package eu.evrin.gameoflife;

import java.util.ArrayList;
import java.util.List;

public class CellController {
	private Cell[][] cells;
	private int maxX, maxY;
	private List<CellsListener> listeners = new ArrayList<CellsListener>();

	public CellController(int x, int y){
		setCellSize(x,y);
	}
	
    public void addListener(CellsListener toAdd) {
        listeners.add(toAdd);
    }
	
	public void setCellSize(int x,int y){
		maxX=x;
		maxY=y;
		cells = new Cell[x][y];
		reset();
	}
	public void reload(){
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				cells[i][j].reload();
			}
		}
		cellsChange();
	}
	public void cellsChange(){
		for (CellsListener listener : listeners)
			listener.onCellsChange();
	}
	
	public void reset(){
		for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				cells[i][j] = new Cell();
			}
		}
		/*for(int i=0;i<maxX;i++){
			for(int j=0;j<maxY;j++){
				if(i==0||j==0||i==maxX-1||j==maxY-1||(i%10)==0&&(j%10)==0)
					cells[i][j].live=true;
			}
		}*/
		cellsChange();
	}
	
	public int getSizeX(){
		return maxX;
	}
	
	public int getSizeY(){
		return maxY;
	}
	
	public boolean isAlive(int x, int y){
		if(x<0||x>=maxX||y<0||y>=maxY)
			return false;
		return cells[x][y].live;
	}
	
	public boolean isAliveRelative(int x, int y){
		while(x<0||x>=maxX){
			x=(x<0)?x+maxX:x-maxX;
		}
		while(y<0||y>=maxY){
			y=(y<0)?y+maxY:y-maxY;
		}
		return cells[x][y].live;
	}
	
	public void setCell(int x, int y, boolean stat){
		if(x<0||x>=maxX||y<0||y>=maxY)
			return;
		cells[x][y].live=stat;
	}

	public void setNextCell(int x, int y, boolean stat) {
		if(x<0||x>=maxX||y<0||y>=maxY)
			return;
		cells[x][y].nextLive=stat;
	}

	public void sameNextCell(int x, int y) {
		if(x<0||x>=maxX||y<0||y>=maxY)
			return;
		cells[x][y].nextLive=cells[x][y].live;
	}
}
