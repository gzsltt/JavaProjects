package f;
//void函数：迪杰斯特拉算法   传入参数：存储图的二维数组(int)（来自每个结点）
//void函数：输出到每个最短节点的最短路径  传入参数：map容器（二维数组下标（k）和节点名称（v）对应）
//成员变量：路径数组（int）

public class Shortest {
//DIJKSTRA最短路径算法
	public static int[] Dijkstra(int start)
	{
		int n = Route.graph.length;
        //存放从start到其他各点的最短路径
        int[] shortPath = new int[n];
        //存放从start到其他各点的最短路径的字符串表示
        String[] path=new String[n];
        for(int i=0;i<n;i++)
        {
            path[i] = (char)(65+start) + "-->" + (char)(65+i);
        }
        //标记当前该顶点的最短路径是否已经求出,1表示已求出
        int[] visited = new int[n];
 
        shortPath[start] = 0;
        visited[start] = 1;
        for(int count = 1;count <= n - 1;count++)
        {
            //选出一个距离初始顶点start最近的未标记顶点
            int k = -1;
            int dmin = 65535;
            for(int i = 0;i < n;i++)
            {
                if(visited[i] == 0 && Route.graph[start][i] < dmin)
                {
                    dmin = Route.graph[start][i];
                    k = i;
                }
            }
            //将新选出的顶点标记为已求出最短路径，且到start的最短路径就是dmin
            shortPath[k] = dmin;
            visited[k] = 1;
            //以k为中间点，修正从start到未访问各点的距离
            for(int i = 0;i < n;i++)
            {
                if(visited[i] == 0 && Route.graph[start][k] + Route.graph[k][i] < Route.graph[start][i])
                {
                	Route.graph[start][i] = Route.graph[start][k] + Route.graph[k][i];
                    path[i]=path[k]+"-->"+(char)(i+65);
                }
            }
        }

        System.out.println("=====================================");
        char startnode=(char)(65+start);
        for(int i=0;i<n;i++)
        {
            System.out.println("least-cost path to node"+startnode+"to"+(char)(i+65)+":"+path[i]+"and the cost is"+shortPath[i]);
        }
        return shortPath;
	}

}
