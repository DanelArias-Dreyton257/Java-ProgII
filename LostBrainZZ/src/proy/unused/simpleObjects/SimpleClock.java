package proy.unused.simpleObjects;

public class SimpleClock extends Thread {

	private long periodInMillis;
	public boolean state = false;

	public SimpleClock(long periodInMillis) {
		setPeriodInMillis(periodInMillis);
		setDaemon(true);
	}

	private void setPeriodInMillis(long periodInMillis) {
		this.periodInMillis=periodInMillis;
	}

	public double getFrequency() {
		return 1000/periodInMillis;
	}

	public void setFrequency(double frequency) {
		this.periodInMillis = Math.round(1000/frequency);
	}

	public long getPeriodInMillis() {
		return periodInMillis;
	}

	@Override
	public void run() {
		try {
			while(true) {
				changeState();
				System.out.println(state);
				Thread.sleep(getPeriodInMillis());
			} 
		}
		catch (InterruptedException e) {
			System.err.println("Clock interrupted");
		}
	}

	private void changeState() {
		if (state) state=false;
		else state= true;
	}

	public static void main(String[] args) {
		SimpleClock c = new SimpleClock(100);
		//System.out.println(c.getPeriodInMillis());
		//System.out.println(c.getFrequency());
		c.start();
		try {
			Thread.sleep(10000);
		} catch (Exception e) {}
		c.interrupt();
	}


}
