package Server.Threads;
//改了
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import tool.ConnUtil;
import message.loginInfo;
import message.registerinfo;
import message.yn;

public class RegisterThreads extends Thread{
	public ServerSocket server;
	Socket		 client;
	ResultSet res=null;
	ObjectOutputStream out;
	Statement st;
	Connection conn;
	ObjectInputStream in;
	public RegisterThreads() throws IOException {
		server = new ServerSocket(5003);
		start();
	}
	public void run() {
			try {
				while(true) {
				conn=ConnUtil.getConnection();
				client=server.accept();
				in = new ObjectInputStream(client.getInputStream());
				registerinfo r =(registerinfo)in.readObject();
				
				int num=0;
				String sql ="Select * from userinfo where id='"+r.id+"'";
				st = conn.createStatement();
				res = st.executeQuery(sql);
				if(res.next()) num++;
				
				String sql3 ="Select * from userinfo where name='"+r.name+"'";
				st = conn.createStatement();
				res = st.executeQuery(sql3);
				if(res.next()) num++;
				
				
				
				
				out =new ObjectOutputStream(client.getOutputStream());
				if(num!=0) { yn re2 = new yn("no");out.writeObject(re2);out.flush();}
				else 	       { yn re2 = new yn("yes");out.writeObject(re2);out.flush();
				
				
				
				
				
				
				//写入数据库
				String a="'"+r.id+"'";
	            String b="'"+r.pass+"'";
	            String c="'"+r.name+"'";
	            String d="'"+r.sex+"'";				           
	            String f="'"+r.birthday+"'";
	            String g="'"+r.problem+"'";
	            String h="'"+r.answer+"'";
				String i="'"+r.headS+"'";
				String j="'"+1+"'";
				try {
					 conn=ConnUtil.getConnection(); 
					 String sqls="insert into userinfo(id,pass,name,sex,birthday,problem,answer,headSculpture,friendlist)values("+a+","+b+","+c+","+d+","+f+","+g+","+h+","+i+","+j+")";        
		             Statement stmt1=(Statement) conn.createStatement();//创建一个Statement对象
		             //stmt.executeUpdate("insert into a values ('"+xieru+")"');
		             stmt1.executeUpdate(sqls);//执行sql语句				               				                	
		            } catch (SQLException e1) {System.out.println("数据库连接失败");  }
				 
				 try {
					 conn=ConnUtil.getConnection(); 
					 String sql2="insert into ip(id,ip)values("+c+","+c+")";        
		             Statement stmt2=(Statement) conn.createStatement();//创建一个Statement对象
		             //stmt.executeUpdate("insert into a values ('"+xieru+")"');
		             stmt2.executeUpdate(sql2);//执行sql语句				               				                	
		            } catch (SQLException e1) {e1.printStackTrace();  }
				
				 
				 
				 
				
				
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