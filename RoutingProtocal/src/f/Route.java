package f;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.*;
import java.util.Map;
import java.util.*;
import java.net.*;


//��������߳� �ֱ�ΪUDP���� UDP���� �������� �������� �Ͻ�˹����
public class Route {
	public static int[][] graph= new int[6][6];
	public static String filePath=null;//�����ļ�·���ַ���
	public static long aliveTime;//���������ʱ��
	public static long pathUpdateTime;//·�����¼��ʱ��
	public static long shortOutputTime;//���·��������ʱ��
	public static Map<Integer, String> map = new HashMap<Integer, String>();//map��������ά�����±꣨k���ͽڵ����ƣ�v����Ӧ��
//	public static String filename=args[2];//�����ļ���
	public static String node_id;//���ڵ������
	public static Integer node_port;//���ڵ�Ķ˿ں�
	public static ArrayList<Integer> nearnode = new ArrayList<>();//ֱ�����ڶ˿ں�
	public static ArrayList<Seq> seq=new ArrayList<>();
	public static int[] liveNum = {0,0,0,0,0,0};//�±��Ӧ�ڵ㣬ÿ��һ���ļ��+1��������3ʱ��ɾ����Ӧ�ڵ�
	public static DatagramSocket socket;
	//map��û�г�ʼ��
	//��ʼ��������Ա����
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
		inital(args);//��ʼ��������Ա����
		Runnable r1=new UsThread();//UDP����
		Thread t1=new Thread(r1);
		Runnable r2=new UaThread();//UDP����
		Thread t2=new Thread(r2);
		Runnable r3=new DJThread();//�Ͻ�˹����
		Thread t3=new Thread(r3);
//		Runnable r4=new AAThread();//����������
//		Thread t4=new Thread(r4);
		Runnable r5=new ASThread();//����������
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
//		Runtime run = Runtime.getRuntime();        //�򿪼��±�

		// �������������ж�Σ�������ѭ�����������Կ����̵߳��������
		// �������̵߳ĵ��ȣ�������������еĽ�����ܲ�ͬ��
	}
