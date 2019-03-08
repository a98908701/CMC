
	package client.Frame;
	import java.awt.Color;
import java.awt.Container;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import java.awt.event.WindowAdapter;
	import java.awt.event.WindowEvent;
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
	import java.io.OutputStreamWriter;
	import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
	import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.Icon;
	import javax.swing.ImageIcon;
	import javax.swing.JButton;

	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPasswordField;
	import javax.swing.JTextField;



import tool.id_ip;



	public class receive_interface extends JFrame{
		//��������Ĵ���
				JLabel addImage = new JLabel();
				JLabel account = new JLabel("����·��");
				JTextField path=new JTextField(20);
				JButton confirm = new JButton("ȷ�Ͻ���");
				JButton cancel = new JButton("�ܾ�");
				
				Socket socket = null;
				String serverIP =Login_interface.serverIP;		
				String hisname;
				String myname;
			public receive_interface(String myname,String hisname){
			this.myname=myname;
			this.hisname=hisname;
			//��JFrame��������
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("�Է���ͼ���㷢���ļ�");
			this.setBounds(0,0,400,200);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			
			Container container =this.getContentPane();
			container.setLayout(null);
			
			
			//����ͼƬ
			addImage.setBounds(0, 0, 500, 400);
			Icon icon = new ImageIcon("src\\ui material\\little_background2.PNG");
			addImage.setIcon(icon);
			addImage.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
			//�˺�
			account.setFont(new java.awt.Font("����",1,16));
			account.setBounds(70,50,100,20);
			path.setBounds(170,50,150,20);
			account.setForeground(Color.white);
			path.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
			path.setBackground (new Color(18,25,32));
			path.setForeground(Color.white);
			//ȷ�Ϻ�ȡ��
			confirm.setBounds(80,100,100,30);
			cancel.setBounds(230,100,100,30);
			//���ּ���
			addImage.add(account);
			addImage.add(path);
			addImage.add(confirm);
			addImage.add(cancel);
			
			container.add(addImage);
			this.setVisible(true);
			//ȡ����ť���������
			cancel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
				dispose();				
				}
				});
			
			
			confirm.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					
					id_ip ii=new id_ip();
					String ip=null;
					try {
						ip=ii.getip(hisname);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					String SERVERIP = ip;
					int SERVERPORT = 12345;
					int CLIENTPORT = 54321;
					 byte[]buf = new byte[100];
	                   
	                   Socket s = new Socket();
	                   try{
	                            //��������
	                            s.connect(new InetSocketAddress(SERVERIP,SERVERPORT), CLIENTPORT);
	                            InputStream is = s.getInputStream();
	                            //���մ��������ļ���
	                            int len = is.read(buf);
	                            String fileName = new String(buf,0,len);
	                            System.out.println(fileName);
	                            
	                            //�����һ���ļ��������ܴ������ļ�
	                            String[] str =fileName.split("\\."); 
	                            char a=(char)('a'+Math.random()*('z'-'a'+1));
	                    		String aa= new Character(a).toString();
	                    		int b=(int)(Math.random()*10000);
	                    		String file=b+aa+"."+str[1];
	                            FileOutputStream fos = new FileOutputStream(path.getText()+"\\"+file);
	                            System.out.println(path.getText()+file);
	                            int data;
	                            while(-1!=(data= is.read()))
	                            {
	                               fos.write(data);
	                            }
	                            System.out.println("����ɹ�");
	                            is.close();
	                            s.close();
	                            
	                   }catch (IOException e1) {
	                            //TODO Auto-generated catch block
	                            e1.printStackTrace();
	                   }
				dispose();				
				}
				});
			
			
			
		
			
		}
		
		public static void main(String[] args) {
			receive_interface L = new receive_interface("1","2");
		}

		

	}