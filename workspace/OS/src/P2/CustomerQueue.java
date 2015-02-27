package P2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * This class implements a queue of customers as a circular buffer.
 */
public class CustomerQueue {
	/**
	 * Creates a new customer queue.
	 * @param queueLength	The maximum length of the queue.
	 * @param gui			A reference to the GUI interface.
	 */
	
	//public BlockingQueue<Customer> queue;
	public int queueLength;
	public LinkedList<Customer> buffer;
	public boolean[] availPos;
	public HashMap<Customer, Integer> positionMap;
	public Gui gui;
	
	
    public CustomerQueue(int queueLength, Gui gui) {
    	this.queueLength = queueLength;
    	this.buffer = new LinkedList<Customer>();
    	this.availPos = new boolean[queueLength];
    	this.positionMap = new HashMap<Customer, Integer>();
    	this.gui = gui;
	}


	public synchronized void addCustomer(){
		while(buffer.size() == queueLength){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Customer x = new Customer();
		buffer.add(x);
		//-----------------------
		int pos = findAvailSpot();
		availPos[pos] = true;
		positionMap.put(x, pos);
		gui.fillLoungeChair(pos, x);
		//-----------------------
		notifyAll();
	}
	
	public synchronized Customer getNextCustomer(){
		while(buffer.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Customer next = buffer.removeFirst();
		//-------------------------
		int pos = positionMap.get(next);
		positionMap.remove(next);
		availPos[pos] = false;
		gui.emptyLoungeChair(pos);
		//-------------------------
		notifyAll();
		return next;
		
	}

	
	
	
	
	
	
//-------------------HELPER FUNCTIONS-----------------------
	
	public boolean notFull(LinkedList<Customer> buffer, int queueLength){
		if (buffer.size() < queueLength){
			return true;
		}
		return false;
	}
	public boolean notEmpty(LinkedList<Customer> buffer, int queueLength){
		if(buffer.isEmpty()){
			return false;
		}
		return true;
	}
	
	public int findAvailSpot(){
		int pos = 0;
		for (int i = 0; i < this.availPos.length; i++) {
			if(this.availPos[i] == false){
				pos = i;
				break;
			}
		}
		return pos;
	}
	
	public int generateSeatNumber(){
		return 0 + (int)(Math.random() * ((17 - 0) + 1));
	}
	
}
