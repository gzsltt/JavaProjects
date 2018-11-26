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
//1���������հ�   ����������byte[]��
//2�����������������ø��¶�ά���麯�����ж��Ƿ��Ѿ����չ��İ������û�н��չ������ͼ���飬ת�����������ڽڵ㣩   
//������������յ���byte[]���顢route���ͼ������
//�������������ĺ��ͼ������
//******3������ת�����յ��İ������ã�UDPsend��
//txtConfiguration���byte[](�õ�ת����Ŀ�Ķ˿ں�)��������void
import java.util.Map;


public class UDPaccept {//1���������հ�   ����������byte[]��
//	DatagramSocket socket;
	public int sendnodeport;
	public byte[] accept() throws IOException
	{
		byte[] buffer = new byte[1024];
//		socket= new DatagramSocket(Route.node_port);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		Route.socket.receive(packet);
		sendnodeport=packet.getPort();
		byte data[] = packet.getData();// ���յ�����
		return data;
	}
	
	
	public void AnalyPack(byte[] data) throws IOException	
	//2�����������������ø��¶�ά���麯�����ж��Ƿ��Ѿ����չ��İ������û�н��չ������ͼ���飬ת�����������ڽڵ㣩   
	//������������յ���byte[]���顢route���ͼ������
	//�������������ĺ��ͼ������
	{
		/**
		 * ������Byte
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
		int hostport=0;//�������Ľ��˿�
		int num=0;//�����������
		int nodenum=0;//�ж����ӵĽ����Ŀ
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
		//���ø��¶�ά���麯�����ж��Ƿ��Ѿ����չ��İ������û�н��չ������ͼ���飬ת�����������ڽڵ�
		synchronized (Route.seq) { 
			Seq qq=new Seq(hostport,num);	
			//���û�н��չ�
			if(qq.IsReceive(qq) == false)
			{
				Route.seq.add(qq);
				UDPsend ud=new UDPsend();
				/**---------���ڽ����Ϣ----------**/
				ud.send(data);
				String nodeid;
				int port=0;
				int weight=0;//���ڴ�������ӽ����±꣬node_id������
				//����ͼ����
				String []info=new String[nodenum];//ÿ�������������ļ��й���������Ϣ��ÿһ��
				for(int k=3; k<3+nodenum; k++){
					info[k-3]=arr[k];}
				for(int e=0;e<nodenum;e++)//�ٶ�ÿһ�е���Ϣ���в�� �ָ���Ϊ�ո�
				{
					String[] arr2=info[e].split(" ");
							nodeid=arr2[0];//���id
							weight=Integer.parseInt(arr2[1]);//���Ȩ��
							port=Integer.parseInt(arr2[2].trim());//���˿�
				synchronized (Route.graph) {
					Route.graph[hostport-2000][port-2000]=weight;
					Route.graph[port-2000][hostport-2000]=weight;
				}
			}
			//ת�����������ڽڵ�
			}
		}
		//������չ� ��������
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


