package f;

public class UaThread implements Runnable{
	public void run()
	{
		UDPaccept ua=new UDPaccept();
		Route route=new Route();
		while(true)
		{
			try { 
			byte[]b=ua.accept();
			AliveAccept aa=new AliveAccept();
			if(aa.isheartpack(b)==false) {
				System.out.println("����������");
				Thread.sleep(10000);
				//�������������������UDP����
				ua.AnalyPack(b);//����ͼ����
//				for(int i=0;i<6;i++)
//				{
//					for(int j=0;j<6;j++)
//					{
//						System.out.print(Route.graph[i][j]);
//					}
//					System.out.println();
//				}
			}
			
			else//��������������͵��ø��������������ĺ���
			{
				System.out.println("��������");
//				aa.datasendport=ua.sendnodeport;
				synchronized (Route.seq) { 
					route.liveNum[ua.sendnodeport - 2000] = 0;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	}
	
	
}
