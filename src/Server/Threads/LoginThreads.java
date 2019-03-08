package Server.Threads;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//改过了
import tool.ConnUtil;
import message.loginInfo;

public class LoginThreads extends Thread{
	public ServerSocket server;
	Socket	client;
	ResultSet res=null;
	ObjectOutputStream out;
	Statement st;
	Connection conn;
	ObjectInputStream in;
	public LoginThreads() throws IOException {
		server = new ServerSocket(5002);
		start();
	}
	public void run() {
			try {
				while(true) {
				conn=ConnUtil.getConnection();
				client=server.accept();
				InetAddress ips=client.getInetAddress();
				String ip=ips.toString();
				in = new ObjectInputStream(client.getInputStream());
				loginInfo l =(loginInfo)in.readObject();
				String sql ="Select * from userinfo where id='"+l.id+"'and pass='"+l.pwd+"'";
				out =new ObjectOutputStream(client.getOutputStream());
				st = conn.createStatement();
				res = st.executeQuery(sql);
				String id = null;
				boolean i=false;
				if(res.next()){
					id=res.getString(3);
					loginInfo r =new loginInfo(res.getString(3),res.getString(8),1,res.getString(9));
					out.writeObject(r);
					out.flush();
					i=true;
				}
				else {
					//服务器回传判定：需要在加上用户不能重复登录的判断
					loginInfo r =new loginInfo("","",2,"");
					out.writeObject(r);
					out.flush();
				}
				
				//去改变另一个ip地址中替换为当前用户当前ip
				if(i) {					
					System.out.println(ip.replace("/",""));
					PreparedStatement psql;
                    psql = conn.prepareStatement("update ip set ip = ? where id = ?");
                    psql.setString(2, id);
                    psql.setString(1, ip.replace("/",""));
                    psql.executeUpdate();
                    psql.close();	
					
					
					
				}
				
				
				
				
				client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
			
		
	}

}
