package message;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class forgetinfo implements Serializable{
	private static final long	serialVersionUID	= 1L;
	public String id;
	public String pass;
	public String problem;
	public String answer;
	
	
	public forgetinfo(String id,String pass,String problem,String answer) {
		this.id=id;
		this.pass=pass;
		this.problem=problem;
		this.answer=answer;
	}
}
