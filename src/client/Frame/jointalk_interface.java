package client.Frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;



public class jointalk_interface extends JFrame{
	//各种组件的创建
			JLabel addImage = new JLabel();
			JLabel account = new JLabel("群聊端口号");
			JTextField field_acc=new JTextField(20);
			JButton confirm = new JButton(new ImageIcon("src\\ui material\\button_image_join.png"));
			JButton cancel = new JButton(new ImageIcon("src\\ui material\\button_exit.png"));
			
			String nickname;
			Socket socket = null;
			String serverIP =Login_interface.serverIP;		
			String myHeadSculptureNum;
	public jointalk_interface(String nickname,String num){
		this.nickname=nickname;
		this.myHeadSculptureNum=num;
		//对JFrame进行设置
		this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		this.setTitle("加入群聊");
		this.setBounds(0,0,400,200);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		Container container =this.getContentPane();
		container.setLayout(null);
		
		
		//加入图片
		addImage.setBounds(0, 0, 500, 400);
		Icon icon = new ImageIcon("src\\ui material\\little_background2.png");
		addImage.setIcon(icon);
		addImage.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		//账号
		account.setFont(new java.awt.Font("宋体",1,16));
		account.setForeground(Color.white);
		account.setBounds(80,50,100,20);
		field_acc.setBounds(170,50,150,20);
	    field_acc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
		field_acc.setBackground (new Color(18,25,32));
		field_acc.setForeground(Color.white);//*******
	
		//确认和取消
		confirm.setBounds(80,100,100,25);
		cancel.setBounds(220,100,100,25);
		
		
		confirm.setBorderPainted(false);
		confirm.setContentAreaFilled(false);
		cancel.setBorderPainted(false);
		cancel.setContentAreaFilled(false);
		//各种加入
		addImage.add(account);
		addImage.add(field_acc);
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
				new Thread() {@SuppressWarnings("null")
				public void run() { 
					int port=Integer.parseInt(field_acc.getText());
					
					//该对象是1，向2发送信息
					
					boolean open=true;
					try {
						socket = new Socket(serverIP,port);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "     该群聊端口号不存在");
						open=false;
					} 
					//向数据库该群组添加该成员
					
					if(open) {
						new Thread() {public void run() {
						try{	
						
						Socket client = new Socket(serverIP,5101);
						
						BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
						PrintWriter pw = new PrintWriter(out,true); 
						
						pw.println("join"+","+port+","+nickname);  
	                    pw.flush();
						client.close();
						
						
						
						
						
						}catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}    
						}}.start();}
					//////////////
					if(open) {
					Grouptalk_interface group=new Grouptalk_interface(field_acc.getText(),nickname,myHeadSculptureNum);
					BufferedReader br = null;
					BufferedWriter bw=null;
					try {
						br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
				    //输出流，向服务端写信息
				    try {
						bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				    dispose();	
				    group.addWindowListener(new WindowAdapter()
			        {
			            public void windowClosing(WindowEvent e)
			            {
			             try {
							socket.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}    
			            }
			        });
					        
				    PrintWriter pw =new PrintWriter(bw,true); 
                    pw.println("");  
                    pw.flush();	
						   
				    
				    
				    
				    	while(true) {
							String info = null;
							try {
								info = br.readLine();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								break;//此处出现的异常是因为另一个线程里客户端已经关闭，因此可以利用这个异常去关闭这个循环，释放空间
							};//接收服务端输入的信息
							String[] str = info.split(",");  
				            String hap=str[0]+":"+str[1]+"\r\n"+"\r\n";	
				            Document docs = group.record.getDocument();
				            ImageIcon img = new ImageIcon("src\\talkingface"+"\\"+str[2]+"_"+str[3]+".png");
				            group.record.setCaretPosition(docs.getLength());
							group.record.insertIcon(img);
							Document docs2 = group.record.getDocument();
							try {
								docs2.insertString(docs2.getLength(),hap,null);
							} catch (BadLocationException e1) {
								e1.printStackTrace();
							}
			
						    }
					}	
				}
				}.start();	

				
				
				
				
				
				
			}});
	
		
	}
	
	

	

}

