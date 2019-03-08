package Server.Threads;
//改过了
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import tool.ConnUtil;
import message.yn;
import message.forgetinfo;

public class ForgetThreads extends Thread{
	public ServerSocket server;
	Socket	client;
	ResultSet res=null;
	ObjectOutputStream out;
	Statement st;
	Connection conn;
	ObjectInputStream in;
	public ForgetThreads() throws IOException {
		server=new ServerSocket(5001);
		start();
	}
	public void run() {
			
			try {
				while(true) {
				client=server.accept();
				in = new ObjectInputStream(client.getInputStream());
				out =new ObjectOutputStream(client.getOutputStream());
				forgetinfo r =(forgetinfo)in.readObject();											
				String sql ="Select * from userinfo where id='"+r.id+"'";							
				conn=ConnUtil.getConnection();
				st = conn.createStatement();
				res = st.executeQuery(sql);				
			   //如果找到写入的ID。进行数据对比，对则改，不对则提醒 
				boolean ii=false;
				if(res.next()) { 
					 try {
						 conn=ConnUtil.getConnection(); 			               				               					               		               					               
			               	String iid = res.getString(1);
			        		String iproblem = res.getString(6);
			        		String ianswer = res.getString(7);		
			        		if((r.id.equals(iid))&&(r.problem.equals(iproblem))&&(r.answer.equals(ianswer)))
			        		{yn re2 = new yn("yes");out.writeObject(re2);out.flush(); 
			        		PreparedStatement stmt1;
			        		PreparedStatement psql;
		                    psql = conn.prepareStatement("update userinfo set pass = ? where id = ?");
		                    psql.setString(2, r.id);
		                    psql.setString(1, r.pass);
		                    psql.executeUpdate();
		                    psql.close();
			        		
			        		}
			        		else {yn re2 = new yn("no");out.writeObject(re2);out.flush();}
					 
			        		conn.close();	
					     } catch (SQLException e1) {System.out.println("数据库连接失败");  }
			   
				}
				else  {yn re2 = new yn("else");out.writeObject(re2);out.flush();}
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