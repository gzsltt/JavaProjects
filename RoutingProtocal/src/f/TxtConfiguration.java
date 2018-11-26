package f;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class TxtConfiguration{
	//读时间配置文件 赋值给三个时间long类型
	
	
	//测试正确
	//函数read：读取配置文件   传入参数：配置文件名和byte数组，   
	//传出参数：byte数组（直接是在UDP包中的格式）（利用函数String.getbyte（））
	public static byte[] read(String filename) throws Exception{
		 byte[]buffer=new byte[1024];
		 try {  
			
			/*Scanner sc=new Scanner(System.in);
			String name=sc.next();//读入node_id
			String filename=sc.next();//配置文件名*/
			// 创建文件输入流对象 
			 String content=null;
			 int size1=0;
			 char[] buffer1=new char[1024];
			 FileReader fis=new FileReader(filename);
			 //循环来读取该文件中的数据
			 while((size1=fis.read(buffer1))!=-1){
				 content=new String(buffer1, 0, size1);}
            //读取该文件中的数据
            //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
            buffer=content.getBytes();
			fis.close();
			// 关闭输入流 
			} 
			catch (Exception e) {
				 e.printStackTrace();
            }  
			return buffer;
	}
	
	//已测试，正确
	//用来进行IO操作 读配置文件 并读入初始路径信息数据 知道到它所连接的节点的信息，其余节点的路径信息并不知道
	//初始化graph数组,near_node
	public static void Initialize(byte data[]) 
	{/**
		 * 处理后的Byte
		 * 2
		 * B 5 2001
		 * C 7 2002
		 * **/
		Route route = new Route();
		String s=new String(data);
		String[] arr=s.split("\r\n");
//		Integer hostport=0;//发出包的结点端口
		Integer num;//发出包的序号
		int nodenum=0;//判断连接的结点数目
		for(int i=0; i<1; i++){
			if(i==0)
			{
				nodenum=Integer.valueOf(arr[i]).intValue();
			}
		}
			String nodeid;
			Integer port=0;
			int weight=0;//用于存放所连接结点的下标，node_id和名字
			//更新图数组
			String []info=new String[nodenum];//每个数组存放配置文件中关于连接信息的每一行
			for(int i=1; i<arr.length; i++){
				info[i-1]=arr[i];
			}
			for(int j=0;j<nodenum;j++)//再对每一行的信息进行拆分 分隔符为空格
			{
				String[] arr2=info[j].split(" ");
				for(int k=0;k<3;k++)
				{
					if(k==0)
					{
						nodeid=arr2[k];//结点id
					}
					if(k==1)
					{
						weight=Integer.parseInt(arr2[k]);//结点权重
					}
					if(k==2)
					{
						port=Integer.parseInt(arr2[k]);//结点端口
					}
				}
//				System.out.println(route.node_port);
//				System.out.println(port);
				synchronized (Route.graph) {
					route.graph[route.node_port-2000][port-2000]=weight;//改图
					route.graph[port-2000][route.node_port-2000]=weight;//改图
				}
				}
				synchronized (Route.nearnode) { 
					route.nearnode.add(port);//更改相邻节点
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