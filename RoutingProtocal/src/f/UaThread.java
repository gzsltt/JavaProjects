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
				System.out.println("不是心跳包");
				Thread.sleep(10000);
				//如果不是心跳包（即是UDP包）
				ua.AnalyPack(b);//更新图数组
//				for(int i=0;i<6;i++)
//				{
//					for(int j=0;j<6;j++)
//					{
//						System.out.print(Route.graph[i][j]);
//					}
//					System.out.println();
//				}
			}
			
			else//如果是心跳包，就调用更新心跳包参数的函数
			{
				System.out.println("是心跳包");
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
