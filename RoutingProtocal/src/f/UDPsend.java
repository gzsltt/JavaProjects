package f;
import java.net.*;
import java.io.*;
import java.util.*;

//��ô�ж����Լ��İ�����ת���İ�
//��ôת��
//1�����������Լ���UDP�������������������byte[]������������void 
//2���������UDP�������ݡ� 
//���������txtConfiguration���byte[]��
//����������������byte[]���������кţ��ɷ����ߵĶ˿ںź������ɣ����Է�ֹ�㲥�鷺��

/**
 * ������Byte
 * 2000
 * 0
 * 2
 * B 5 2001
 * C 7 2002
 * **/

//�Ѳ��ԣ���ȷ
//���UDP�����������кţ�
public class UDPsend { 
	static int num=0;//���к�
	public static byte[] packbag(String filename,Integer node_port) throws Exception
	{
		TxtConfiguration t=new TxtConfiguration();
		byte[]b=t.read(filename);
		String s=null;
		s=Integer.toString(node_port)+"\r\n";
		String sn=String.valueOf(num);
		s=s+sn+"\r\n";
		s=s+ new String(b);
		byte[] n=new byte[1024];
		n=s.getBytes();
		num++;
//		System.out.println(n);
		return n;
	}
	
	
//����UDP��
	public void send(byte[]buf) {
			//���Ͷ�
		try {
			// �������ͷ����׽��� ���� ���ô���Ĳ��� �˿ں�
//			socket = new DatagramSocket(Route.node_port);
			// ���͵�����
			// �������ݱ���������������Ϊ length �İ����͵�ָ�������ϵ�ָ���˿ںš�
			for(int i = 0; i < Route.nearnode.size();i++) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length,
				InetAddress.getLocalHost(), Route.nearnode.get(i));
				// �Ӵ��׽��ַ������ݱ���
				Route.socket.send(packet);
				// ���գ������߷��ص�����
			}
		}
		catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
//	public static void main(String[] args) throws SocketException  {
//		System.out.println(Route.node_port);
//		DatagramSocket socket = new DatagramSocket(2000);
//		try{
//			packbag("test.txt", 2000);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
		
	
}
