package message;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class loginInfo implements Serializable{
	private static final long	serialVersionUID	= 1L;
	public String id;
	public String pwd;
	public String head;
	public String friend;
	public int login;//�����ж��Ƿ���Ե�¼ 1������Ե�¼ 2����������� 3�����Ѿ���¼
	public loginInfo(String id,String pwd) {
		this.id=id;
		this.pwd=pwd;
	}
	//�Ĺ��췽�����ڵ�¼������ʱ��ʾ�û���Ϣ
	public loginInfo(String id,String head,int login,String friend) {
		this.id=id;
		this.head=head;
		this.login=login;
		this.friend=friend;
	}
}
