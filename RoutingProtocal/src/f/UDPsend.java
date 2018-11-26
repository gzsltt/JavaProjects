package f;
import java.net.*;
import java.io.*;
import java.util.*;

//怎么判断是自己的包还是转发的包
//怎么转发
//1函数：发送自己的UDP包，传入参数：处理后的byte[]，传出参数：void 
//2函数：打包UDP包的内容。 
//传入参数：txtConfiguration里的byte[]。
//传出参数：处理后的byte[]（加入序列号（由发出者的端口号和序号组成），以防止广播洪泛）

/**
 * 处理后的Byte
 * 2000
 * 0
 * 2
 * B 5 2001
 * C 7 2002
 * **/

//已测试，正确
//打包UDP包（加上序列号）
public class UDPsend { 
	static int num=0;//序列号
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
	
	
//发送UDP包
	public void send(byte[]buf) {
			//发送端
		try {
			// 创建发送方的套接字 对象 采用传入的参数 端口号
//			socket = new DatagramSocket(Route.node_port);
			// 发送的内容
			// 构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。
			for(int i = 0; i < Route.nearnode.size();i++) {
				DatagramPacket packet = new DatagramPacket(buf, buf.length,
				InetAddress.getLocalHost(), Route.nearnode.get(i));
				// 从此套接字发送数据报包
				Route.socket.send(packet);
				// 接收，接收者返回的数据
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
