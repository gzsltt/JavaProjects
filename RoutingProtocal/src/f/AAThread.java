package f;

public class AAThread implements Runnable {
	public void run() {
		try {
			Thread.sleep(Route.aliveTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (Route.liveNum) { 
			for(int i = 0; i < 6; i++) {
				Route.liveNum[i]++;
			}
		}
	}
}
