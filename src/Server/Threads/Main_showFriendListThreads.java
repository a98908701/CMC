package Server.Threads;
//新加的
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import message.userInfo;
import message.userInfoList;
import tool.ConnUtil;

public class Main_showFriendListThreads extends Thread{
	public ServerSocket server;
	Socket	client;
	ResultSet res=null;
	ObjectOutputStream out;
	Statement st;
	Connection conn;
	ObjectInputStream in;
	public Main_showFriendListThreads() throws IOException {
		server=new ServerSocket(5004);
		start();
	}
	public void run() {
		try {
			while(true) {
				conn=ConnUtil.getConnection();
				client=server.accept();
				in=new ObjectInputStream(client.getInputStream());
				out=new ObjectOutputStream(client.getOutputStream());
				userInfo u=(userInfo)in.readObject();
				userInfoList ul = new userInfoList(u.friendsList.length);
				st=conn.createStatement();
				for(int i=0;i<u.friendsList.length;i++) {
					String sql="Select * from userinfo where id='"+u.friendsList[i]+"'";
					ResultSet res=st.executeQuery(sql);
					if(res.next()) {
						userInfo x=new userInfo(res.getString(1),res.getString(3),res.getString(4),res.getString(5),res.getString(8));
						ul.userInfo[i]=x;
					}
				}
				out.writeObject(ul);
				out.flush();
				client.close();
			}
		}catch (SQLException e1) {System.out.println("数据库连接失败");  } 
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public  static void main(String[] args) throws IOException {
		Main_showFriendListThreads mm=new Main_showFriendListThreads();
	}

}
