package f;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;

//����1�����������������UDPsend��ķ�������������   ����UDPsend��������->��������byte[], 
//�߳��������ļ������ڽڵ���Ϣ��
//
public class AliveSend {
	public byte[] packAlive() throws IOException {
		byte[]buffer=new byte[1024];
			/*Scanner sc=new Scanner(System.in);
			String name=sc.next();//����node_id
			String filename=sc.next();//�����ļ���*/
			// �����ļ����������� 
			 String content=null;
			 int size1=0;
			 char[] buffer1=new char[1024];
			 FileReader fis=new FileReader("beat.txt");
			 //ѭ������ȡ���ļ��е�����
			 while((size1=fis.read(buffer1))!=-1){
				 content=new String(buffer1, 0, size1);}
           //��ȡ���ļ��е�����
           //����һ���ֽڻ�����,�û������Ĵ�С������Ҫ������
           buffer=content.getBytes();
           return buffer;
	}
	public void sendAlive(byte buf[])
	{
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
}
