package f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.*;
import java.util.Map;
import java.util.*;
import java.net.*;


//建立五个线程 分别为UDP发送 UDP接收 心跳发送 心跳接收 迪杰斯特拉
public class Route {
	public static int[][] graph= new int[6][6];
	public static String filePath=null;//配置文件路径字符串
	public static long aliveTime;//心跳包间隔时间
	public static long pathUpdateTime;//路径更新间隔时间
	public static long shortOutputTime;//最短路径输出间隔时间
	public static Map<Integer, String> map = new HashMap<Integer, String>();//map容器（二维数组下标（k）和节点名称（v）对应）
//	public static String filename=args[2];//配置文件名
	public static String node_id;//本节点的名称
	public static Integer node_port;//本节点的端口号
	public static ArrayList<Integer> nearnode = new ArrayList<>();//直接相邻端口号
	public static ArrayList<Seq> seq=new ArrayList<>();
	public static int[] liveNum = {0,0,0,0,0,0};//下标对应节点，每过一定的间隔+1，当超过3时，删除对应节点
	public static DatagramSocket socket;
	//map还没有初始化
	//初始化各个成员变量
	public static void inital(String[] args) throws Exception {
//		synchronized (filePath) {
			filePath = args[2];
//		}
//		synchronized (node_id) {
			node_id=args[0];
//		}
		node_port = Integer.parseInt(args[1]);
		
//		FileInputStream in = new FileInputStream("assignment.txt");
		synchronized (Route.graph) {
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					if(i==j)
						graph[i][j]=0;
					else
					graph[i][j] = 100;
				}
			}
			TxtConfiguration tx=new TxtConfiguration();
			byte[]b=tx.read(filePath);
			tx.Initialize(b);
		}
		socket=new DatagramSocket(node_port);
		FileReader m=new FileReader("assignment.txt");
		BufferedReader reader=new BufferedReader(m);
		aliveTime = Long.parseLong(reader.readLine());
		pathUpdateTime = Long.parseLong(reader.readLine());
		shortOutputTime = Long.parseLong(reader.readLine());
	}

	public static void main(String[] args) throws Exception{
		inital(args);//初始化各个成员变量
		Runnable r1=new UsThread();//UDP发送
		Thread t1=new Thread(r1);
		Runnable r2=new UaThread();//UDP接收
		Thread t2=new Thread(r2);
		Runnable r3=new DJThread();//迪杰斯特拉
		Thread t3=new Thread(r3);
//		Runnable r4=new AAThread();//心跳包接收
//		Thread t4=new Thread(r4);
		Runnable r5=new ASThread();//心跳包发送
		Thread t5=new Thread(r5);
		t1.start();
		t2.start();
		t3.start();
//		t4.run();
		t5.run();
	}

//		aliveTime = 1;
//		System.out.println(aliveTime);
//		AAThread  a = new AAThread();
//		new AAThread().start();
//		a.start();
//		for(int i = 0; i < 10000000;i++);		
//		System.out.println(aliveTime);
//		Runtime run = Runtime.getRuntime();        //打开记事本

		// 这个程序可以运行多次，并调整循环次数，可以看到线程的运行情况
		// 依赖于线程的调度，这个程序多次运行的结果可能不同。
	}
