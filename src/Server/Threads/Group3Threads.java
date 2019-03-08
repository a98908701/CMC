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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import message.forgetinfo;
import message.yn;
import tool.ConnUtil;
import tool.Group;
public class Group3Threads extends Thread {
public ServerSocket server;
	Socket	client;
	ObjectOutputStream out;
	
	public Group3Threads() throws IOException {
		server=new ServerSocket(5102);
		start();
	}
	public void run() {
		
		try {
			while(true) {
			client=server.accept();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
			String msg = in.readLine();	
			
			
			
			
			BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			PrintWriter pw = new PrintWriter(out,true); 
			pw.println(Group.getlist(msg));  
            pw.flush();
			
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

