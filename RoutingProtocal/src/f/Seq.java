package f;
import java.util.*;

public class Seq {
	public int port;//端口号
	public int num;//序号
	
	public Seq(int port, int num) {
		this.port = port;
		this.num = num;
	}
	//判断是否接收过
	public boolean IsReceive (Seq sq) {
		for(Seq itsq:Route.seq) {
			if(sq.port == itsq.port && sq.num == itsq.num) {
				return true;
			}				
		}
		return false;
	}
}
