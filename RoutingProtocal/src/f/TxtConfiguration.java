package f;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class TxtConfiguration{
	//��ʱ�������ļ� ��ֵ������ʱ��long����
	
	
	//������ȷ
	//����read����ȡ�����ļ�   ��������������ļ�����byte���飬   
	//����������byte���飨ֱ������UDP���еĸ�ʽ�������ú���String.getbyte������
	public static byte[] read(String filename) throws Exception{
		 byte[]buffer=new byte[1024];
		 try {  
			
			/*Scanner sc=new Scanner(System.in);
			String name=sc.next();//����node_id
			String filename=sc.next();//�����ļ���*/
			// �����ļ����������� 
			 String content=null;
			 int size1=0;
			 char[] buffer1=new char[1024];
			 FileReader fis=new FileReader(filename);
			 //ѭ������ȡ���ļ��е�����
			 while((size1=fis.read(buffer1))!=-1){
				 content=new String(buffer1, 0, size1);}
            //��ȡ���ļ��е�����
            //����һ���ֽڻ�����,�û������Ĵ�С������Ҫ������
            buffer=content.getBytes();
			fis.close();
			// �ر������� 
			} 
			catch (Exception e) {
				 e.printStackTrace();
            }  
			return buffer;
	}
	
	//�Ѳ��ԣ���ȷ
	//��������IO���� �������ļ� �������ʼ·����Ϣ���� ֪�����������ӵĽڵ����Ϣ������ڵ��·����Ϣ����֪��
	//��ʼ��graph����,near_node
	public static void Initialize(byte data[]) 
	{/**
		 * ������Byte
		 * 2
		 * B 5 2001
		 * C 7 2002
		 * **/
		Route route = new Route();
		String s=new String(data);
		String[] arr=s.split("\r\n");
//		Integer hostport=0;//�������Ľ��˿�
		Integer num;//�����������
		int nodenum=0;//�ж����ӵĽ����Ŀ
		for(int i=0; i<1; i++){
			if(i==0)
			{
				nodenum=Integer.valueOf(arr[i]).intValue();
			}
		}
			String nodeid;
			Integer port=0;
			int weight=0;//���ڴ�������ӽ����±꣬node_id������
			//����ͼ����
			String []info=new String[nodenum];//ÿ�������������ļ��й���������Ϣ��ÿһ��
			for(int i=1; i<arr.length; i++){
				info[i-1]=arr[i];
			}
			for(int j=0;j<nodenum;j++)//�ٶ�ÿһ�е���Ϣ���в�� �ָ���Ϊ�ո�
			{
				String[] arr2=info[j].split(" ");
				for(int k=0;k<3;k++)
				{
					if(k==0)
					{
						nodeid=arr2[k];//���id
					}
					if(k==1)
					{
						weight=Integer.parseInt(arr2[k]);//���Ȩ��
					}
					if(k==2)
					{
						port=Integer.parseInt(arr2[k]);//���˿�
					}
				}
//				System.out.println(route.node_port);
//				System.out.println(port);
				synchronized (Route.graph) {
					route.graph[route.node_port-2000][port-2000]=weight;//��ͼ
					route.graph[port-2000][route.node_port-2000]=weight;//��ͼ
				}
				}
				synchronized (Route.nearnode) { 
					route.nearnode.add(port);//�������ڽڵ�
				}
			}
	
//	public static void main(String[]argv) throws Exception
//	{
//		Route route = new Route();
//		String[] a = {"A","2000","text.txt"};
//		route.inital(a);
//		TxtConfiguration t=new TxtConfiguration();
//		byte[] b = t.read("test.txt");
//		Initialize(b);
//	
//		
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