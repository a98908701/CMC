package message;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class registerinfo implements Serializable{
	private static final long	serialVersionUID	= 1L;
	public String id;
	public String pwd;
	public String pass;
	public String name;
	public String sex;
	public String birthday;
	public String problem;
	public String answer;
	public String headS;
	public  registerinfo(String id,String pass,String name,String sex,String birthday,String problem,String answer,String headS) {
		this.id=id;
		this.pass=pass;
		this.name=name;
		this.sex=sex;
		this.birthday=birthday;
		this.problem=problem;
		this.answer=answer;
		this.headS=headS;
	}
}
