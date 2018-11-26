package f;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.net.*;
import java.util.Iterator;

public class AliveAccept {
//	public static int datasendport;
	
	public boolean isheartpack(byte[]b) {
		String s=new String(b);
		if(s.contains("tata"))
		{
			return true;
		}
		else return false;
}
	
	
	public void deletenode(int port) throws Exception//接收到
	{
		//1.修改配置文件
		Reader r=new FileReader(Route.filePath);//文件读
		BufferedReader fi=new BufferedReader(r);
		String p=Integer.toString(port);
		String content=null;
		String s=null;
		while((s=fi.readLine()).contains(p))
		{
			content=content+s;
		}
		fi.close();
		//文件写覆盖
		synchronized (Route.filePath) { 
			FileWriter fw=new FileWriter(Route.filePath);
			fw.write(content);
			fw.close();
		}
		
		//2.修改nearnode
		synchronized (Route.nearnode) { 
			Iterator<Integer> listiterator=Route.nearnode.iterator();
			while(listiterator.hasNext())
			{
				Integer k=listiterator.next();
				if(k==port)
				{
					listiterator.remove();
				}
			}
		}
		//3.修改二维矩阵
		synchronized (Route.graph) {
			for(int i=0;i<6;i++)
			{
				for(int j=0;j<6;j++)
				{
					if(i==(port-2000)||j==(port-2000))
					{
						Route.graph[i][j]=100;
					}
				}
				
			}
		}
	}
}
