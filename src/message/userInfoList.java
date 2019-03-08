package message;
//ÐÂ¼ÓµÄ
import java.io.Serializable;

public class userInfoList implements Serializable{
	public userInfo[] userInfo;
	public int length;
	public userInfoList(int n) {
		userInfo = new userInfo[n];
		length=n;
	}

}
