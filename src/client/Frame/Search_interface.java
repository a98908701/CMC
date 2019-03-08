package client.Frame;
import java.awt.Color;
import java.awt.Container;
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
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.Threads.addFriendThreads;
import client.Threads.talkingThreads;
import tool.id_ip;

	public class Search_interface extends JFrame{
		//各种组件的创建
				JLabel addImage = new JLabel();
				JLabel account = new JLabel("对方名字");
				JTextField path=new JTextField(20);
				JButton confirm = new JButton(new ImageIcon("src\\ui material\\button_conAdd.png"));
				JButton cancel = new JButton(new ImageIcon("src\\ui material\\button_exit.png"));
				String friends;
				Socket socket = null;
				String serverIP =Login_interface.serverIP;		
				String hisname;
				String myname;
				List<String> friendbyName1 = new ArrayList<String>(); 
			public Search_interface(String id, String friend, ArrayList<String> friendbyName){
			friends=friend;
			myname=id;
			friendbyName1=friendbyName;
			//对JFrame进行设置
			this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
			this.setTitle("添加好友");
			this.setBounds(0,0,400,200);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			
			Container container =this.getContentPane();
			container.setLayout(null);
			
			
			//加入图片
			addImage.setBounds(0, 0, 500, 400);
			Icon icon = new ImageIcon("src\\\\ui material\\\\little_background2.PNG");
			addImage.setIcon(icon);
			addImage.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
			//账号
			account.setFont(new java.awt.Font("宋体",1,16));
			account.setBounds(70,50,100,20);
			account.setFont(new java.awt.Font("宋体",1,18));
			account.setForeground(Color.white);
			path.setBounds(170,50,150,20);
			path.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
			path.setBackground (new Color(18,25,32));
			path.setForeground(Color.white);//*****
			//确认和取消
			confirm.setBounds(80,100,100,25);
			cancel.setBounds(230,100,100,25);
			confirm.setBorderPainted(false);
			confirm.setContentAreaFilled(false);
			cancel.setBorderPainted(false);
			cancel.setContentAreaFilled(false);
			//各种加入
			addImage.add(account);
			addImage.add(path);
			addImage.add(confirm);
			addImage.add(cancel);
			
			container.add(addImage);
			this.setVisible(true);
			//取消按钮加入监听器
			cancel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				dispose();				
				}
				});
			
			
			confirm.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					new Thread() {public void run() {
						String hisname=path.getText();
						
						boolean a=true;
						for(String a1:friendbyName1) {
							if(hisname.equals(a1)) {JOptionPane.showMessageDialog(null, "    已是您好友了");a=false;break;}
						}
						if(a) {
							try {
								id_ip ii=new id_ip();
								String ip=ii.getip(hisname);
								if(ip.equals(null)) {JOptionPane.showMessageDialog(null, "    不存在该用户");	}
								else {
								Socket toclient = new Socket(ip,6000);
								BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(toclient.getOutputStream()));   
								PrintWriter pw = new PrintWriter(bw,true); 
								pw.println(myname);
								BufferedReader br = new BufferedReader(new InputStreamReader(toclient.getInputStream()));
								String reply =br.readLine();
								if(reply.equals("yes")) {
									Main_interface.friendbyName.add(hisname);
									JOptionPane.showMessageDialog(null,"   对方已同意");
									socket = new Socket(serverIP,5011);
									BufferedReader br1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
									BufferedWriter bw1= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
									PrintWriter pw1 =new PrintWriter(bw1,true);
									pw1.println(myname+","+hisname);
									String receive= br1.readLine();//此为被添加方头像
									int numfr=Main_interface.numoffriends;
									Main_interface.numoffriends++;
									JLabel f1=new JLabel();
									Image img1 = new ImageIcon(receive).getImage();
									f1.setIcon(new ImageIcon(img1));
									JLabel f2=new JLabel(hisname);
									f1.setBounds(0,numfr*100,100,100);
									f2.setBounds(110,numfr*100+20,100,20);
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
									
									dispose();
								}
								if(reply.equals("no")) {
									JOptionPane.showMessageDialog(null,"  对方拒绝添加您为好友");
									dispose();
								}
								pw.close();
								bw.close();
								br.close();
								socket.close();
								}
							} catch (UnknownHostException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
						
						
					}}.start();
			
				}
			
				});
		
		}
		
		










		public static void main(String[] args) {
			ArrayList<String> friendbyName = new ArrayList<String>(); 
			friendbyName.add("11");
			Search_interface L = new Search_interface("1","",friendbyName) ;
		}

		

	}