package f;
import java.util.*;
import java.applet.*;

public class ASThread implements Runnable {//���������߳�
	public void run() 
	{
		Route route=new Route();
		AliveSend as=new AliveSend();
		UDPsend us=new UDPsend();//����UDPsend�з��Ͱ�����
		while(true) {
			try {
				byte[]b=new byte[1];
				b=as.packAlive();
				as.sendAlive(b);
				Thread.sleep(route.aliveTime);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
