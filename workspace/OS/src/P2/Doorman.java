package P2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the doorman's part of the
 * Barbershop thread synchronization example.
 */
//--------------------PRODUCER---------------------
public class Doorman extends Thread{
	
	
	private CustomerQueue q;
	private Gui gui;
	private boolean isRunning;
	//private Thread thread;
	//private Map<String, Customer> availSeat; 
	
	/**
	 * Creates a new doorman.
	 * @param queue		The customer queue.
	 * @param gui		A reference to the GUI interface.
	 */
	
	public Doorman(CustomerQueue queue, Gui gui) { 
		this.q = queue;
		this.gui = gui;
		//this.availSeat = new HashMap<String, Customer>();
	}

	/**
	 * Starts the doorman running as a separate thread.
	 */
	public void startThread() {
		isRunning = true;
		start();
		
	}

	/**
	 * Stops the doorman thread.
	 */
	public void stopThread() {
		isRunning = false;
	}

	@Override
	public void run() {
		while(isRunning){
			try {
				// sover i en tid angitt i fra slider UI
				sleep((long) (Globals.doormanSleep*Math.random()));
				gui.println("Henter inn ny kunde til salongen...");
				q.addCustomer();
				gui.println("kunder i sanlogen: " + q.buffer.size());
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			

		}
	}

	// Add more methods as needed
}
