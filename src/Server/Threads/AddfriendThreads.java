package Server.Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//改了
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tool.ConnUtil;




public class AddfriendThreads extends Thread {
	public ServerSocket server;
	Socket	client;
	ResultSet res=null;
	Statement st;
	Connection conn;
	String hisid;
	BufferedReader br;
	BufferedWriter bw;
	
	public AddfriendThreads() throws IOException {
		server=new ServerSocket(5011);
		start();
	}	
	
	public void run() {
		
		try {
			
			while(true) {
				client=server.accept();
				br = new BufferedReader(new InputStreamReader(client.getInputStream()));  
			    bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));  
			    PrintWriter pw =new PrintWriter(bw,true);  
			    String msg = br.readLine();
			    String[] str =msg.split(","); 
			    conn=ConnUtil.getConnection();
			    
			    String sqlss ="Select * from userinfo where name='"+str[1]+"'";
				st = conn.createStatement();
				res = st.executeQuery(sqlss);
				if(res.next()) {hisid=res.getString(1);}
                //将添加方好友列表里加上被添加方
			    String id=null;
				String sql ="Select * from userinfo where name='"+str[0]+"'";
				st = conn.createStatement();
				res = st.executeQuery(sql);
				if(res.next()) {
					id=res.getString(1);
					PreparedStatement psql;
                    psql = conn.prepareStatement("update userinfo set friendlist = ? where id = ?");
                    psql.setString(2, id);
                    psql.setString(1, res.getString(9)+" "+hisid);
                    psql.executeUpdate();
                    psql.close();		
				}
				//将被添加方好友列表里加上添加方
				String sqls ="Select * from userinfo where name='"+str[1]+"'";
				st = conn.createStatement();
				res = st.executeQuery(sqls);
				if(res.next()) {
					pw.println(res.getString(8));
					pw.flush();
					PreparedStatement psql;
	        		psql = conn.prepareStatement("update userinfo set friendlist = ? where id = ?");
                    psql.setString(2, hisid);
                    psql.setString(1, res.getString(9)+" "+id);
                    psql.executeUpdate();
                    psql.close();
				
				}
			    
			
			
			
			
			}
		
		}catch (IOException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	
}
