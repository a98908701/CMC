package Server.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import message.forgetinfo;
import message.yn;
import tool.ConnUtil;
import tool.Group;
public class Group2Threads extends Thread {
public ServerSocket server;
	Socket	client;
	ObjectOutputStream out;
	
	public Group2Threads() throws IOException {
		server=new ServerSocket(5101);
		start();
	}
	public void run() {
		
		try {
			while(true) {
			client=server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
			String msg = in.readLine();		
			String[]str = msg.split(",");     
			//0表示是加入还是退出，1是port，2是昵称
			
			
			
			if(str[0].equals("join"))
			Group.listadd(str[1], str[2]);
			
			if(str[0].equals("quit"))
			Group.listdelect(str[1], str[2]);	
			client.close();
			}		

		}catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
