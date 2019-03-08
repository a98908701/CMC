package Server.Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tool.ConnUtil;



public class getipserver extends Thread {
	public ServerSocket server;
	Socket socket;
	Statement st;
	Connection conn;
	ResultSet res=null;
	public getipserver() throws IOException {
		server=new ServerSocket (5010);
		start();
	}
	
	
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
			System.out.println("Î´ÕÒµ½Çý¶¯³ÌÐò");
		}
		String url = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf8&useSSL=true"; 
		String user="root";
		String password="13559411101lu";
		try {
			return DriverManager.getConnection(url,user,password);
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public void run() {
	
	
	
	
	
	try {
	while(true) {
	
		socket=server.accept();
		BufferedReader rw = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		String id = rw.readLine();
		
		String sql ="Select * from ip where id='"+id+"'";
		conn=ConnUtil.getConnection();
		st = conn.createStatement();
		res = st.executeQuery(sql);	
		String ip=null;
		if(res.next()) { 
		ip=res.getString(2);
		}
		
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));   
		PrintWriter pw=new PrintWriter(bw,true); 
        pw.println(ip);  
        pw.flush();
		
	
	
	
	
	
	
	
	}	
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}

	
	}
}
