
//使用范围：1、注册功能
package message;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class yn implements Serializable {
	private static final long	serialVersionUID	= 1L;
	public String dir;
	
	public yn(String dir) {
		this.dir=dir;
	
	}
}
