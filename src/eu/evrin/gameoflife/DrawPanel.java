package eu.evrin.gameoflife;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener, ComponentListener, CellsListener {
	
	public boolean grid = true;
	public int size = 10;
	private CellController cc;
	
	private static final long serialVersionUID = 1L;

	public DrawPanel(CellController cc) {
		this.cc=cc;
		setBackground(Color.WHITE);
		cc.addListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addComponentListener(this);
	}
	
	public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
		int height = cc.getSizeY()*size;
		int width = cc.getSizeX()*size;
        g.setColor(Color.BLACK);
		for(int i=0;i<width;i+=size){
			for(int j=0;j<height;j+=size){
				if(cc.isAlive(i/size, j/size))
					g.fillRect(i, j, size, size);
			}
		}
		if(grid&&size>5){
	        g.setColor(Color.LIGHT_GRAY);
			for(int i=0;i<=width;i+=size){
				g.drawLine(i, 0, i, height);
			}
			for(int i=0;i<=height;i+=size){
				g.drawLine(0, i, width, i);
			}
		}
    }
	
	public void setCell(MouseEvent e){
		if(SwingUtilities.isLeftMouseButton(e))
			cc.setCell(e.getX()/size, e.getY()/size, true);
		if(SwingUtilities.isRightMouseButton(e))
			cc.setCell(e.getX()/size, e.getY()/size, false);
		this.repaint();
	}
	
	public void changeSize(){
		cc.setCellSize(this.getWidth()/this.size, this.getHeight()/this.size);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		setCell(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setCell(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		changeSize();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCellsChange() {
		this.repaint();
	}

}
