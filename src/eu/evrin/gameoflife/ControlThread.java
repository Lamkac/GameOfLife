package eu.evrin.gameoflife;

public class ControlThread implements Runnable  {
	Thread thread;
	public boolean running = false;
	public long sleepTime = 1000;
	private CellController cc;

	public ControlThread(CellController cc) {
		this.cc=cc;
		thread=new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		int neighbours ;
		while(true){
			if(running){
				for(int i=0;i<cc.getSizeX();i++){
					for(int j=0;j<cc.getSizeY();j++){
						neighbours=0;
						for(int k=-1;k<2;k++){
							for(int l=-1;l<2;l++){
								if(!(l==0&&k==0)&&cc.isAliveRelative(i+k, j+l))neighbours++;
							}
						}
						if(neighbours<2||neighbours >3)
							cc.setNextCell(i, j, false);
						else if(neighbours==3)
							cc.setNextCell(i, j, true);
						else
							cc.sameNextCell(i, j);
					}
				}
				cc.reload();
			}
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
