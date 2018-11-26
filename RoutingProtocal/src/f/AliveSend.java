package f;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;

//函数1：打包心跳包，调用UDPsend里的发送心跳包函数   传入UDPsend函数参数->（心跳包byte[], 
//线程中配置文件中相邻节点信息）
//
public class AliveSend {
	public byte[] packAlive() throws IOException {
		byte[]buffer=new byte[1024];
			/*Scanner sc=new Scanner(System.in);
			String name=sc.next();//读入node_id
			String filename=sc.next();//配置文件名*/
			// 创建文件输入流对象 
			 String content=null;
			 int size1=0;
			 char[] buffer1=new char[1024];
			 FileReader fis=new FileReader("beat.txt");
			 //循环来读取该文件中的数据
			 while((size1=fis.read(buffer1))!=-1){
				 content=new String(buffer1, 0, size1);}
           //读取该文件中的数据
           //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
           buffer=content.getBytes();
           return buffer;
	}
	public void sendAlive(byte buf[])
	{
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
}
