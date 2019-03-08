package message;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class loginInfo implements Serializable{
	private static final long	serialVersionUID	= 1L;
	public String id;
	public String pwd;
	public String head;
	public String friend;
	public int login;//用来判断是否可以登录 1代表可以登录 2代表密码错误 3代表已经登录
	public loginInfo(String id,String pwd) {
		this.id=id;
		this.pwd=pwd;
	}
	//改构造方法用于登录主界面时显示用户信息
	public loginInfo(String id,String head,int login,String friend) {
		this.id=id;
		this.head=head;
		this.login=login;
		this.friend=friend;
	}
}
