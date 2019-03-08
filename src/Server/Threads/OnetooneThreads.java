package Server.Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import message.registerinfo;
import message.yn;
import tool.members;

public class OnetooneThreads extends Thread{
	List<members> list = new ArrayList<members>();   
    @SuppressWarnings("resource") 
	public ServerSocket serverSocket;
    Socket socket;
    public OnetooneThreads() throws IOException {		
    	serverSocket = new ServerSocket(8892);
    	start();
	}
	public void run() {
		 
	      
		try {    
	    while (true) {  
	    socket = serverSocket.accept();  
	    members user = new members(socket);  
	    list.add(user);	
	    new Thread() {public void run() {
	
	    	BufferedReader bw=user.getBr();	
            String msg = null;
			try {
				msg = bw.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
            if(msg!=null) {	
            for (members user2 : list) {	
            	//ÅÐ¶ÏSocketÊÇ·ñ¹Ø±Õ
            	
            		PrintWriter pw =user2.getPw(); 
                    pw.println(msg);  
                    pw.flush();
            }  }	      	
         		  
			  }}.start();
		

	}
		}catch (IOException e) {
			e.printStackTrace();
		}
		}
}