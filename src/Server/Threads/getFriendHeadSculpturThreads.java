package Server.Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tool.ConnUtil;

public class getFriendHeadSculpturThreads extends Thread{
	public ServerSocket server;
	Socket	client;
	ResultSet res=null;
	BufferedWriter bw;
	PrintWriter pw;
	BufferedReader br;
	Statement st;
	Connection conn;
	
	public getFriendHeadSculpturThreads() throws IOException {
		server=new ServerSocket(5496);
		start();
	}
	public void run() {
		try {
			client=server.accept();
			br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String name=br.readLine();
			String sql ="Select * from userinfo where name='"+name+"'";
			conn=ConnUtil.getConnection();
			st = conn.createStatement();
			res = st.executeQuery(sql);	
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())); 
			pw = new PrintWriter(bw,true);
			if(res.next()) {
				pw.println(res.getString(8));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
