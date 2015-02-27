package P2;

/**
 * This class implements the barber's part of the
 * Barbershop thread synchronization example.
 */
//--------------------CONSUMER---------------------
public class Barber extends Thread{
	/**
	 * Creates a new barber.
	 * @param queue		The customer queue.
	 * @param gui		The GUI.
	 * @param pos		The position of this barber's chair
	 */
	private int barberID;
	private boolean isRunning;
	private Gui gui;
	private CustomerQueue q;
	
	public Barber(CustomerQueue queue, Gui gui, int pos) { 
		this.barberID = pos;
		this.q = queue;
		this.gui = gui;
	}

	/**
	 * Starts the barber running as a separate thread.
	 */
	public void startThread() {
		isRunning = true;
		start();
	}

	/**
	 * Stops the barber thread.
	 */
	public void stopThread() {
		isRunning = false;
	}

	@Override
	public void run() {
		while(isRunning){
			try {
				gui.barberIsSleeping(barberID);
				gui.println("barber #" + barberID +" WAITING");
				sleep((long) (Globals.barberSleep*Math.random()));
				gui.barberIsAwake(barberID);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			gui.fillBarberChair(barberID, q.getNextCustomer());
			
			try {
				sleep((long) (Globals.barberWork*Math.random()));
				gui.println("barber #" + barberID + " DONE");
			} catch (InterruptedException e3) {
				e3.printStackTrace();
			}
			gui.emptyBarberChair(barberID);
		}
		
	}

	// Add more methods as needed
}

