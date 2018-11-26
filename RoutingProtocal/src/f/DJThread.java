package f;

public class DJThread implements Runnable{
	public void run()
	{

		while(true) {
			try {
				Thread.sleep(5000);
		Shortest s=new Shortest();
		Route route=new Route();
		int start=route.node_port-2000;
//		for(int i=0;i<6;i++)
//		{
//			for(int j=0;j<6;j++)
//			{
//				System.out.print(Route.graph[i][j]);
//			}
//			System.out.println();
//		}
		s.Dijkstra(start);
		Thread.sleep(Route.shortOutputTime);
		}
		catch(Exception e)
			{
			e.printStackTrace();
			}
	}}
}
