package theLegendOfFinn.controller;

import theLegendOfFinn.model.Ticker;

public class ModelManager implements Runnable{
	private Ticker ticker;
	private Thread thread;
	
	public ModelManager(Ticker ticker){
		this.ticker = ticker;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		while(true){ 
			ticker.tick();
			try{
				Thread.sleep(5);
			}catch (Exception e){}
		}
		
	}

	
}
