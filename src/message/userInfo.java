package message;
//�¼ӵ�
import java.io.Serializable;

public class userInfo implements Serializable{
	public String id;
	public String name;
	public String sex;
	public String birthday;
	public String headSculpture;
	public String[] friendsList;
	public userInfo(String id,String name,String sex,String birthday,String headSculpture) {
		this.id=id;
		this.name=name;
		this.sex=sex;
		this.birthday=birthday;
		this.headSculpture=headSculpture;
	}
	//���������洫�������Ϣ
	public userInfo(String[] friendsList) {
		this.friendsList=friendsList;
	}

}
