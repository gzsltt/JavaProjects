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
	
	
	public void deletenode(int port) throws Exception//���յ�
	{
		//1.�޸������ļ�
		Reader r=new FileReader(Route.filePath);//�ļ���
		BufferedReader fi=new BufferedReader(r);
		String p=Integer.toString(port);
		String content=null;
		String s=null;
		while((s=fi.readLine()).contains(p))
		{
			content=content+s;
		}
		fi.close();
		//�ļ�д����
		synchronized (Route.filePath) { 
			FileWriter fw=new FileWriter(Route.filePath);
			fw.write(content);
			fw.close();
		}
		
		//2.�޸�nearnode
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
		//3.�޸Ķ�ά����
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
