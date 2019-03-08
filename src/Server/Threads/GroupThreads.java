package Server.Threads;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import tool.members;
public class GroupThreads extends Thread{
	List<members> list = new ArrayList<members>();   
    @SuppressWarnings("resource") 
    public ServerSocket serverSocket;
    Socket socket;
    public GroupThreads() throws IOException {		
    	serverSocket = new ServerSocket(8878);
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
            if(msg.equals("")) {}
            else{//String[] str = msg.split(",");  
             // String hap=str[0]+":"+str[1]+"\r\n"+"\r\n";	
              for (members user2 : list) {	
              		PrintWriter pw =user2.getPw(); 
                    pw.println(msg);  
                    pw.flush();}	      	
         		  
			  }
            }}.start();
		
	    }
	    }
		catch (IOException e) {
			e.printStackTrace();
		}
		
}
}
