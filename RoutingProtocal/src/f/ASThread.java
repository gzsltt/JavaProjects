package f;
import java.util.*;
import java.applet.*;

public class ASThread implements Runnable {//心跳接收线程
	public void run() 
	{
		Route route=new Route();
		AliveSend as=new AliveSend();
		UDPsend us=new UDPsend();//传入UDPsend中发送包函数
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
