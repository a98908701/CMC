package client.Frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;

import client.Threads.addFriendThreads;
import client.Threads.talkingThreads;
import message.userInfo;
import message.userInfoList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main_interface extends JFrame{
	private JLabel name;
	private JLabel headSculpture;	
	private JButton qqzone = new JButton(new ImageIcon("src\\ui material\\button_game.png"));
	private JButton quit =new JButton(new ImageIcon("src\\ui material\\button_image_quit.png"));
	private JButton Group=new JButton(new ImageIcon("src\\ui material\\button_image_joingroup.png"));
	private JButton searchFriend=new JButton(new ImageIcon("src\\ui material\\button_image_searchfriends.png"));
	private JButton browser =new JButton(new ImageIcon("src\\ui material\\button_liulan.png"));
	private JLabel background=new JLabel();
	private JLabel quitL = new JLabel();
	static public JScrollPane friends =new JScrollPane();
	private Socket socket;
	private String head;
	private String id;
	private  String friend;
	private ServerSocket server;
	private String serverIP=Login_interface.serverIP;
	static public ArrayList<String> friendbyName = new ArrayList<String>(); 
	static public int numoffriends=0;
	static public ArrayList<Socket> ff = new ArrayList<Socket>();
	
	
	class FrienddealThreads extends Thread{
		public Socket request;
		public FrienddealThreads()  {
			try {
			server = new ServerSocket(6000);
			//System.out.println("好友处理服务器启动!");
			}catch(Exception e) {}
			start();
			}
		
		public synchronized void run() {
			
			try {
				while(true) {
				request=server.accept();
				if(request!=null) {
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream())); 
				String hisname =br.readLine();
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(request.getOutputStream()));   
				PrintWriter pw = new PrintWriter(bw,true); 
				int t = JOptionPane.showConfirmDialog(null, hisname+"想添加你为好友", "同意", JOptionPane.OK_CANCEL_OPTION);
		             if (t == JOptionPane.OK_OPTION) {
		            	 pw.println("yes");
		            	 addFriendThreads aft = new addFriendThreads(hisname,id);
		            	 
		            	 JOptionPane.showMessageDialog(null,"添加成功");
		             }
		             if(t== JOptionPane.CANCEL_OPTION) {
		            	 pw.println("no");
		             }
		             br.close(); 
		             pw.close();
		             bw.close();
				}
				request.close();
				
				}
			} catch (IOException e) {
				e.printStackTrace();
			}catch(Exception e) {
				//e.printStackTrace();
			}
			
			
		}
	}
	
	
	
	public Main_interface(String id,String headSculpture,String friends) {
		super("主界面");
		this.id=id;
		this.friend=friends;
		head =headSculpture;
		creatFrame();
		//退出按钮指令
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(socket!=null) {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if(server!=null) {
            		try {
						server.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
            	}
				dispose();
			}
			
		});
		//加入群聊的指令
		Group.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String[] xx=head.split("\\.");
			String xxx=xx[0].substring(37, xx[0].length());
			jointalk_interface join =new jointalk_interface(id,xxx);
				
			}
			
		});
		quitL.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent event) {
				 int t = JOptionPane.showConfirmDialog(null, "确认要退出吗？", "确认退出", JOptionPane.OK_CANCEL_OPTION);
	                if (t == JOptionPane.OK_OPTION)
	                {	
	                	if(server!=null) {
	                		try {
								server.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
	                	}
	                    System.exit(0);
	                }
			 }});
		qqzone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new game_interface();	
				
				
				
			}
			
		});
		searchFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Search_interface i=new Search_interface(id,friend,friendbyName);
				
				
				
			}
			
		});
		
		
		//窗口退出方式
		 addWindowListener(new WindowAdapter()
	        {
	            public void windowClosing(WindowEvent e)
	            {
	                int t = JOptionPane.showConfirmDialog(null, "确认要退出吗？", "确认退出", JOptionPane.OK_CANCEL_OPTION);
	                if (t == JOptionPane.OK_OPTION)
	                {
	                	if(socket!=null) {
	                		try {
								socket.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                	}
	                	for (int i = 0; i < ff.size(); i++) {
	                	      try {
								ff.get(i).close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                	      
	                	    }

	                	
	                	
	                	
	                	
	                	
	                	
	                	
	                	
	                	System.exit(0);
	                }
	            }
	        });
	}
	public void creatFrame() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定窗口装饰风格
		this.setLayout(null);
		this.setBounds(0,0,369,700);
		this.setBackground(Color.black);
		this.setLocation(1400,100);//使得登录界面位于右侧
		this.setResizable(false);//窗口大小无法改变
		this.setUndecorated(true);
		Container container = this.getContentPane();
		container.setBackground(Color.WHITE);
		container.setLayout(null);
		
		Image img = new ImageIcon("src\\ui material\\main_interface_background.png").getImage();
		background.setIcon(new ImageIcon(img));
		background.setBounds(0,0,369,700);
		quitL.setBounds(330,37, 50, 20);
		name=new JLabel(id);
		name.setBounds(130,50,100,50);
		name.setFont(new java.awt.Font("宋体",1,18));
		name.setForeground(Color.white);
		headSculpture=new JLabel();
		headSculpture.setBounds(10,30,100,100);
		Image imgx = new ImageIcon(head).getImage();
		headSculpture.setIcon(new ImageIcon(imgx));
		browser.setBounds(150,95,100,23);
		qqzone.setBounds(260,95,90, 20);
		qqzone.setBorderPainted(false);
		qqzone.setContentAreaFilled(false);
		
		friends.setBounds(11,132,346,520);
		friends.setOpaque(false);  
		friends.getViewport().setOpaque(false);  

	    
	    //将好友列表显示出来
	    friends.setLayout(null);
	    if(!friend.equals("")) {
	    	try {
				socket=new Socket(serverIP,5004);
				ObjectOutputStream out =new ObjectOutputStream(socket.getOutputStream());
				String[] tt=friend.split("\\s+");
				userInfo ui=new userInfo(tt);
				out.writeObject(ui);
				out.flush();
				ObjectInputStream in =new ObjectInputStream(socket.getInputStream());
				userInfoList ul =(userInfoList)in.readObject();
				numoffriends=ul.length;
				for(int i=0;i<ul.length;i++) {
					
					JLabel f1=new JLabel();
					Image img1 = new ImageIcon(ul.userInfo[i].headSculpture).getImage();
					f1.setIcon(new ImageIcon(img1));
					JLabel f2=new JLabel(ul.userInfo[i].name);
					f1.setBounds(0,i*100,100,100);
					f2.setBounds(110,i*100+20,100,20);
					f2.setFont(new java.awt.Font("宋体",1,18));
					f2.setForeground(Color.white);
					friends.add(f1);
		 	    	friends.add(f2);
		 	    	String hisname=ul.userInfo[i].name;
		 	    	String hisid = ul.userInfo[i].id;
		 	    	
		 	    	Talk_interface t= new Talk_interface(id,hisname,hisid);
		 	    	t.setTitle("正在和"+hisname+"聊天");
		 	    	f1.addMouseListener(new MouseAdapter() {
		 	    	public void mouseClicked(MouseEvent event) {
		 	    		t.setVisible(true);
		 	    		}
		 	    		});
		 	    	
		 	    	//进行创建客户端接收信息，写在在界面吗
		 	    
		 	    	talkingThreads ttt= new talkingThreads(hisname,t,id);
		 	    	friendbyName.add(hisname);
					}
				}
	    		
	    	catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	    
	    }
	   
	    
	 	 
	    Group.setBounds(20, 660, 90, 30);
	    searchFriend.setBounds(140, 660, 90, 30);
	    quit.setBounds(260,660, 90, 30);
	    Group.setBorderPainted(false);
		Group.setContentAreaFilled(false);
		searchFriend.setBorderPainted(false);
		searchFriend.setContentAreaFilled(false);
		quit.setBorderPainted(false);
		quit.setContentAreaFilled(false);
		browser.setBorderPainted(false);
		browser.setContentAreaFilled(false);
		background.add(browser);
	    background.add(quitL);
	    background.add(name);
	    background.add(headSculpture);
	    background.add(qqzone);
	    background.add(quit);
	    background.add(searchFriend);
	    background.add(Group);
	    background.add(friends);
		container.add(background);
		FrienddealThreads fff=new FrienddealThreads();
		this.setVisible(true);
	}
	
}
