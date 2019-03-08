package client.Threads;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import client.Frame.Login_interface;
import client.Frame.Main_interface;
import client.Frame.Talk_interface;

public class addFriendThreads extends Thread {
	public String hisname;
	public String id;
	public Socket getFriendhs;
	public addFriendThreads(String a,String id) {
		hisname=a;
		this.id=id;
		start();
	}
	public void run() {
		try {
			getFriendhs = new Socket(Login_interface.serverIP,5496);
			 BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(getFriendhs.getOutputStream())); 
			 PrintWriter pw1 = new PrintWriter(bw1,true);
	    	 pw1.println(hisname);	           
	    	 BufferedReader br1 = new BufferedReader(new InputStreamReader(getFriendhs.getInputStream()));
	    	 String hishs =br1.readLine();
	    	 JLabel f1=new JLabel();
			 Image img1 = new ImageIcon(hishs).getImage();
			 f1.setIcon(new ImageIcon(img1));
			 JLabel f2=new JLabel(hisname);
			 f1.setBounds(0,Main_interface.numoffriends*100,100,100);
			 f2.setBounds(110,Main_interface.numoffriends*100+20,100,20);
			 Main_interface.numoffriends++;
			 f2.setFont(new java.awt.Font("宋体",1,18));
			 f2.setForeground(Color.white);
			 Main_interface.friends.add(f1);
			 Main_interface.friends.add(f2);
			 Talk_interface t= new Talk_interface(id,hisname,"1");
	 	    	t.setTitle("正在和"+hisname+"聊天");
	 	    	f1.addMouseListener(new MouseAdapter() {
	 	    	public void mouseClicked(MouseEvent event) {
	 	    		t.setVisible(true);
	 	    		}
	 	    		});
	 	    	
	 	    	//进行创建客户端接收信息，写在在界面吗
	 	    	
	 	    	talkingThreads ttt= new talkingThreads(hisname,t,id);
	 	    	Main_interface.friendbyName.add(hisname);
	 	     pw1.close();
	 	     bw1.close();
	 	     br1.close();
	 	     getFriendhs.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
    	
	}

}
