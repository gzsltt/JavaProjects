package f;
import java.util.*;

public class Seq {
	public int port;//�˿ں�
	public int num;//���
	
	public Seq(int port, int num) {
		this.port = port;
		this.num = num;
	}
	//�ж��Ƿ���չ�
	public boolean IsReceive (Seq sq) {
		for(Seq itsq:Route.seq) {
			if(sq.port == itsq.port && sq.num == itsq.num) {
				return true;
			}				
		}
		return false;
	}
}
