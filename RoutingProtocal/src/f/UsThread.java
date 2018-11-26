package f;
//线程 用来每n秒发送自己的信息
public class UsThread implements Runnable{
	public void run()
	{
		Route route=new Route();
		UDPsend us=new UDPsend();
		while(true)
		{
			try {
			byte[] b;
			b=us.packbag(route.filePath, route.node_port);
			us.send(b);
			Thread.sleep(route.pathUpdateTime);}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}		
	}
}
