package f;
//�߳� ����ÿn�뷢���Լ�����Ϣ
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
