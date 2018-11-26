package f;
//void�������Ͻ�˹�����㷨   ����������洢ͼ�Ķ�ά����(int)������ÿ����㣩
//void�����������ÿ����̽ڵ�����·��  ���������map��������ά�����±꣨k���ͽڵ����ƣ�v����Ӧ��
//��Ա������·�����飨int��

public class Shortest {
//DIJKSTRA���·���㷨
	public static int[] Dijkstra(int start)
	{
		int n = Route.graph.length;
        //��Ŵ�start��������������·��
        int[] shortPath = new int[n];
        //��Ŵ�start��������������·�����ַ�����ʾ
        String[] path=new String[n];
        for(int i=0;i<n;i++)
        {
            path[i] = (char)(65+start) + "-->" + (char)(65+i);
        }
        //��ǵ�ǰ�ö�������·���Ƿ��Ѿ����,1��ʾ�����
        int[] visited = new int[n];
 
        shortPath[start] = 0;
        visited[start] = 1;
        for(int count = 1;count <= n - 1;count++)
        {
            //ѡ��һ�������ʼ����start�����δ��Ƕ���
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
            //����ѡ���Ķ�����Ϊ��������·�����ҵ�start�����·������dmin
            shortPath[k] = dmin;
            visited[k] = 1;
            //��kΪ�м�㣬������start��δ���ʸ���ľ���
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
