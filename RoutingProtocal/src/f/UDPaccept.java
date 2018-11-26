package f;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.MalformedInputException;
//1函数：接收包   传出参数：byte[]；
//2函数：解析包（调用更新二维数组函数、判断是否已经接收过的包，如果没有接收过则更新图数组，转发给其他相邻节点）   
//传入参数：接收到的byte[]数组、route里的图的数组
//传出参数：更改后的图的数组
//******3函数：转发接收到的包。调用：UDPsend，
//txtConfiguration里的byte[](得到转发的目的端口号)。传出：void
import java.util.Map;


public class UDPaccept {//1函数：接收包   传出参数：byte[]；
//	DatagramSocket socket;
	public int sendnodeport;
	public byte[] accept() throws IOException
	{
		byte[] buffer = new byte[1024];
//		socket= new DatagramSocket(Route.node_port);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		Route.socket.receive(packet);
		sendnodeport=packet.getPort();
		byte data[] = packet.getData();// 接收的数据
		return data;
	}
	
	
	public void AnalyPack(byte[] data) throws IOException	
	//2函数：解析包（调用更新二维数组函数、判断是否已经接收过的包，如果没有接收过则更新图数组，转发给其他相邻节点）   
	//传入参数：接收到的byte[]数组、route里的图的数组
	//传出参数：更改后的图的数组
	{
		/**
		 * 处理后的Byte
		 * 2000
		 * 0
		 * 2
		 * B 5 2001
		 * C 7 2002
		 * **/
		String s=new String(data);
		FileOutputStream fi=new FileOutputStream("temp.txt");
		fi.write(data);
		Reader fr=new FileReader("temp.txt");
		BufferedReader br=new BufferedReader(fr);
		String first=br.readLine();
		char[]c=s.toCharArray();
		String[] arr=s.split("\r\n");
		int hostport=0;//发出包的结点端口
		int num=0;//发出包的序号
		int nodenum=0;//判断连接的结点数目
//		char[] cc= {0};
//		int i=0,j=0;
//		for(i=0;i<c.length;i++)
//		{
//			System.out.print(c[i]);
//			if(c[i]=='\r')
//				break;
//		}
//		for(j=0;j<i;j++)
//		{
//			System.out.println(c[j]);
//			cc[j]=c[j];
//		}
//		String ss=String.valueOf(cc);
		hostport=Integer.parseInt(first);
//				System.out.println("*"+hostport);
				num=Integer.parseInt(arr[1].trim());
//				System.out.println("*"+num);
				nodenum=Integer.parseInt(arr[2].trim());
//				System.out.println("*"+nodenum);
		//调用更新二维数组函数、判断是否已经接收过的包，如果没有接收过则更新图数组，转发给其他相邻节点
		synchronized (Route.seq) { 
			Seq qq=new Seq(hostport,num);	
			//如果没有接收过
			if(qq.IsReceive(qq) == false)
			{
				Route.seq.add(qq);
				UDPsend ud=new UDPsend();
				/**---------相邻结点信息----------**/
				ud.send(data);
				String nodeid;
				int port=0;
				int weight=0;//用于存放所连接结点的下标，node_id和名字
				//更新图数组
				String []info=new String[nodenum];//每个数组存放配置文件中关于连接信息的每一行
				for(int k=3; k<3+nodenum; k++){
					info[k-3]=arr[k];}
				for(int e=0;e<nodenum;e++)//再对每一行的信息进行拆分 分隔符为空格
				{
					String[] arr2=info[e].split(" ");
							nodeid=arr2[0];//结点id
							weight=Integer.parseInt(arr2[1]);//结点权重
							port=Integer.parseInt(arr2[2].trim());//结点端口
				synchronized (Route.graph) {
					Route.graph[hostport-2000][port-2000]=weight;
					Route.graph[port-2000][hostport-2000]=weight;
				}
			}
			//转发给其他相邻节点
			}
		}
		//如果接收过 不做处理
	}
//	public static void main(String[]argv) throws Exception
//	{
//		Route route = new Route();
//		String[] a = {"A","2000","configA.txt"};
//		route.inital(a);
//		TxtConfiguration t=new TxtConfiguration();
//		byte[] b = t.read("abc.txt");
//		UDPaccept ua=new UDPaccept();
//		ua.AnalyPack(b);
//		for(int i = 0; i < 6; i++) {
//			for(int j = 0; j < 6; j++) {
//				System.out.print(route.graph[i][j] +  "  " );
//				
//			}
//			System.out.println();
//		}
//		for(Integer in:route.nearnode) {
//			System.out.println(in);
//		}
//	}

	}


