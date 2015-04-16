package P3;

public class IO {
	private Queue ioQueue;
	private Statistics statistics;
	private long clock = 0;
	private boolean idle = true;
	private Process currentProcess;
	
	public IO(Queue ioQueue, Statistics statistics) {
		this.ioQueue = ioQueue;
		this.statistics = statistics;
	}
	
	public void insertProcess(Process p){
		ioQueue.insert(p);
	}
	
	public Process checkIO(long clock) {
		if(!ioQueue.isEmpty()) {
			Process nextProcess = (Process)ioQueue.getNext();
		}
		return null;
		
	}
	
    public void setCurrentProcess(Process p){
    	currentProcess = p;
    	p.enterIo(clock);
    }
    
    public Process removeCurrentProcess(){
    	Process removedProcess = (Process) currentProcess;
    	currentProcess = null;
    	return removedProcess;
    }
    
	public boolean isIdle(){
		if(idle) return true;
		return false;
	}
	
	public void timePassed(long timePassed) {
		if (ioQueue.getQueueLength() > statistics.memoryQueueLargestLength) {
			statistics.memoryQueueLargestLength = ioQueue.getQueueLength();
		clock += timePassed;
		}
	}
	
	
}
