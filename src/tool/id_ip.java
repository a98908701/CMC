package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import client.Frame.Login_interface;

public class id_ip {

private Socket socket = null;	
	
String serverIP =Login_interface.serverIP;
	
public String getip(String name) throws UnknownHostException, IOException {	
	socket=new Socket(serverIP,5010);
	
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));   
	PrintWriter pw=new PrintWriter(bw,true); 
    pw.println(name);  
    pw.flush();

	
    BufferedReader rw = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
	String ip = rw.readLine();
	
	socket.close();
	return ip;
	
}	
}
