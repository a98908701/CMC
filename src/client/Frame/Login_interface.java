package client.Frame;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import message.loginInfo;

public class Login_interface extends JFrame{
	private static final long serialVersionUID = 1L;
	private JLabel account=new JLabel("账号");
	private JLabel pwd =new JLabel("密码");
	private JLabel reset =new JLabel("注册账号");
	private JLabel forget = new JLabel("忘记密码");
	private JButton button_login=new JButton(new ImageIcon("src\\ui material\\button_image_login.png"));
	private JTextField field_name=new JTextField(20);
	private JPasswordField field_password=new JPasswordField(20);
	private JLabel bgImage = new JLabel();
	private JLabel userInPut = new JLabel();
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in =null;
	static public  String serverIP = "192.168.1.133";
	int port = 5002;
	
	public Login_interface() {
		
		
		createFrame();
		//注册账号监听器
		reset.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showRegister();
			} });
				
		forget.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				showForget();
			}
		});
		//登录操作监听器
		button_login.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent event) {	
				  new Thread() {public void run() { if((field_name.getText()==null)||field_name.getText().equals(""))  JOptionPane.showMessageDialog(null, "     用户名不能为空");
				   else if((field_password.getText()==null)||field_password.getText().equals(""))   JOptionPane.showMessageDialog(null, "      密码不能为空");
				   else {
				   	try {
				   		socket = new Socket(serverIP,port);
				   		out=new ObjectOutputStream(socket.getOutputStream());
				   		loginInfo l=new loginInfo(field_name.getText(),field_password.getText());
				   		out.writeObject(l);
				   		in = new ObjectInputStream(socket.getInputStream());
				   		loginInfo m=(loginInfo)in.readObject();
				   		if(m.login==1) {
				   			dispose();
			   				Logining_interface ling=new Logining_interface();
							Thread.sleep(1500);
							ling.dispose();
				   			Main_interface a = new Main_interface(m.id,m.head,m.friend);
				   			Point origin = new Point();
					   		 a.addMouseListener(new MouseAdapter() {
			                        public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
			                                origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
			                                origin.y = e.getY();
			                        }
			                });
			                a.addMouseMotionListener(new MouseMotionAdapter() {
			                        public void mouseDragged(MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
			                           
			                                Point p = a.getLocation();  //当鼠标拖动时获取窗口当前位置
			                                //设置窗口的位置
			                                //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
			                                a.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
			                        }
			                });

				   		}
				   		if(m.login==2) {
				   			JOptionPane.showMessageDialog(null, "     密码错误");
				   		}
				   		if(m.login==3) {
				   			JOptionPane.showMessageDialog(null, "    该用户已经登录");
				   		}
				   		socket.close();

				   	}catch(IOException e) {
				   		e.printStackTrace();
				   	} catch (ClassNotFoundException e) {
						e.printStackTrace();
						} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				  }}.start();
				}
		});
		this.setVisible(true);
	//最后加个头像就ok了
	}
	private void createFrame() {
		this.setTitle("qq登录界面");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定窗口装饰风格
		this.setLayout(null);
		this.setBounds(0,0,600,428);
		this.setBackground(Color.black);
		this.setLocationRelativeTo(null);//使得登录界面位于中间
		this.setResizable(false);//窗口大小无法改变
		Image img1 = new ImageIcon("src\\ui material\\DLG_LOGIN_INPUT_BOX.PNG").getImage();
		userInPut.setIcon(new ImageIcon(img1));
		userInPut.setBounds(20,100,512,256);
		Container container = this.getContentPane();
		Image img = new ImageIcon("src\\ui material\\login_interface_background.jpg").getImage();
		bgImage.setIcon(new ImageIcon(img));
		bgImage.setBounds(0,0,600,428);
		field_name.setBounds(149,73,265,30);
		field_name.setForeground(Color.white);
		field_password.setBounds(149,118,265,30);
		field_password.setForeground(Color.white);
		field_name.setBackground(null);
		field_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
		field_name.setBackground (new Color(18,25,32));
		field_password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
		field_password.setBackground (new Color(18,25,32));
		reset.setBounds(430,75,100,20);
		forget.setBounds(430,125,100,20);
		button_login.setBounds(220,170,100,36);
		button_login.setBorderPainted(false);
		button_login.setContentAreaFilled(false);
		reset.setFont(new java.awt.Font("宋体",1,18));
		reset.setForeground(Color.white);
		forget.setFont(new java.awt.Font("宋体",1,18));
		forget.setForeground(Color.white);
		userInPut.add(reset);
		userInPut.add(forget);
		userInPut.add(field_name);
		userInPut.add(field_password);
		userInPut.add(button_login);
		bgImage.add(userInPut);
		container.add(bgImage);
	}
	public void showForget() {
		forget_interface f = new forget_interface();
	}
	public void showRegister() {
		register_interface r = new register_interface();
	}
	
	
	public static void main(String[] args) {
		Login_interface L = new Login_interface();
	}
	

}
