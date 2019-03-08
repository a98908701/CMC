package client.Frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import message.loginInfo;
import message.registerinfo;
import message.yn;

import javax.swing.JComboBox;

public class register_interface extends JFrame{
	private JLabel head1=new JLabel("头像");
	private JLabel headS=new JLabel();
	private JComboBox com_head = new JComboBox();
	private JLabel addImage = new JLabel();
	private JLabel name = new JLabel("昵称");
	private JLabel account = new JLabel("账号");
	private JLabel pwd = new JLabel("密码");
	private JLabel pwd1 = new JLabel("确认密码");
	private JLabel sex = new JLabel("性别");
	private JLabel birthday = new JLabel("生日");
	private JLabel year = new JLabel("年");
	private JLabel month = new JLabel("月");
	private JLabel day = new JLabel("日");
	private JLabel problem = new JLabel("密保问题");
	private JTextField field_name=new JTextField(20);
	private JTextField field_acc=new JTextField(20);
	private JPasswordField field_pwd=new JPasswordField(20);
	private JPasswordField field_pwd1=new JPasswordField(20);
	private JComboBox com_sex = new JComboBox();
	private JComboBox com_problem = new JComboBox();
	private JTextField field_problem = new JTextField(20);
	private JButton confirm = new JButton(new ImageIcon("src\\ui material\\button_image_confirm.png"));
	private JButton cancel = new JButton(new ImageIcon("src\\ui material\\button_image_cancel.png"));
	private JComboBox com_year = new JComboBox();
	private JComboBox com_month = new JComboBox();
	private JComboBox com_day = new JComboBox();
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in =null;
	String serverIP = Login_interface.serverIP;
	int port = 5003;
	public register_interface() {
		createFrame();

} 
	private void createFrame() {
		//对JFrame进行设置
		this.setTitle("注册界面");
		this.setBounds(0,0,500,587);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setUndecorated(true);//不出现最大化最小化窗口退出
		this.setResizable(false);
		Container container =this.getContentPane();
		container.setLayout(null);
		
		//年选择框的创建
		for(int i=0;i<39;i++) {
			com_year.addItem(i+1980);
		}
		
		//月选择框的创建
		for(int i=0;i<12;i++) {
			com_month.addItem(i+1);
		}
		//日选择框的创建
		for(int i=1;i<=31;i++) {
			com_day.addItem(i);
		}
		com_year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox cb = (JComboBox)evt.getSource();
				Object newItem = cb.getSelectedItem();
				int days = getDay(Integer.parseInt(newItem.toString()),Integer.parseInt(com_month.getSelectedItem().toString()));
				com_day.removeAllItems();
				for(int i=1;i<=days;i++) {
					com_day.addItem(i);
				}
			}
		});
		com_month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JComboBox cb = (JComboBox)evt.getSource();
				Object newItem = cb.getSelectedItem();
				int days = getDay(Integer.parseInt(com_year.getSelectedItem().toString()),Integer.parseInt(newItem.toString()));
				com_day.removeAllItems();
				for(int i=1;i<=days;i++) {
					com_day.addItem(i);
				}
			}
		});
		//加入图片
		Icon icon = new ImageIcon("src\\ui material\\register_interface_background.jpg");
		addImage.setIcon(icon);
		addImage.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		//头像添加
		head1.setBounds(130,80,50,20);
		head1.setFont(new java.awt.Font("Termiral",1,16));
		head1.setForeground(Color.white);
		for(int i=1;i<=13;i++) {
			com_head.addItem(i);
		}
		com_head.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String head2="src\\cloHeadSculpture\\cloHeadSculpture"+String.valueOf(com_head.getSelectedItem())+".png";
				Icon icon_head = new ImageIcon(head2);
				headS.setIcon(icon_head);
			}
			
		});
		headS.setBounds(180,30,100,100);
		com_head.setBounds(300,80,60,20);
		//账号
		account.setBounds(130,130,50,20);
		field_acc.setBounds(170,130,150,20);
		field_acc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
		field_acc.setBackground (new Color(18,25,32));
		field_acc.setForeground(Color.white);
		account.setFont(new java.awt.Font("Termiral",1,16));
		account.setForeground(Color.white);
		//密码
		pwd.setBounds(130,170,50,20);
		field_pwd.setBounds(170,170,150,20);
		field_pwd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
		field_pwd.setBackground (new Color(18,25,32));
		field_pwd.setForeground(Color.white);
		pwd.setFont(new java.awt.Font("Termiral",1,16));
		pwd.setForeground(Color.white);
		//确认密码
		pwd1.setBounds(100,210,70,20);
		field_pwd1.setBounds(170,210,150,20);
		field_pwd1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
		field_pwd1.setBackground (new Color(18,25,32));
		field_pwd1.setForeground(Color.white);
		pwd1.setFont(new java.awt.Font("Termiral",1,16));
		pwd1.setForeground(Color.white);
		//昵称
		name.setBounds(130,250,50,20);
		field_name.setBounds(170,250,150,20);
		field_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
		field_name.setBackground (new Color(18,25,32));
		field_name.setForeground(Color.white);
		name.setFont(new java.awt.Font("Termiral",1,16));
		name.setForeground(Color.white);
		//性别
		sex.setBounds(130,290,50,20);
		sex.setFont(new java.awt.Font("Termiral",1,16));
		sex.setForeground(Color.white);
		com_sex.addItem("男");
		com_sex.addItem("女");
		com_sex.setBounds(170,290,60,20);
		//生日
		birthday.setBounds(130,330,50,20);
		birthday.setFont(new java.awt.Font("Termiral",1,16));
		birthday.setForeground(Color.white);
		com_year.setBounds(170,330,60,20);
		year.setBounds(240,330,50,20);
		year.setFont(new java.awt.Font("Termiral",1,16));
		year.setForeground(Color.white);
		com_month.setBounds(260,330,50,20);
		month.setBounds(320,330,50,20);
		month.setFont(new java.awt.Font("Termiral",1,16));
		month.setForeground(Color.white);
		com_day.setBounds(340,330,50,20);
		day.setBounds(400,330,50,20);
		//密保问题
		problem.setBounds(100,370,70,20);
		problem.setFont(new java.awt.Font("Termiral",1,16));
		problem.setForeground(Color.white);
		com_problem.addItem("你的母亲的姓名是？");
		com_problem.addItem("你的父亲的姓名是？");
		com_problem.addItem("你的小学班主任的姓名是？");
		com_problem.addItem("你的初中学校叫什么？");
		com_problem.addItem("你的出生地是？");
		com_problem.addItem("你的学号是？");
		com_problem.addItem("你的配偶的姓名是？");
		com_problem.setBounds(170,370,140,20);
		field_problem.setBounds(320,370,150,20);
		field_problem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
		field_problem.setBackground (new Color(18,25,32));
		field_problem.setForeground(Color.white);
		//确认和取消按钮
		confirm.setBounds(110,410,120,30);
		cancel.setBounds(280,410,120,30);
		confirm.setBorderPainted(false);
		confirm.setContentAreaFilled(false);
		cancel.setBorderPainted(false);
		cancel.setContentAreaFilled(false);
		//各种加入
		addImage.add(head1);
		addImage.add(headS);
		addImage.add(com_head);
		addImage.add(account);
		addImage.add(field_acc);
		addImage.add(pwd);
		addImage.add(field_pwd);
		addImage.add(pwd1);
		addImage.add(field_pwd1);
		addImage.add(name);
		addImage.add(field_name);
		addImage.add(sex);
		addImage.add(com_sex);
		addImage.add(birthday);
		addImage.add(com_year);
		addImage.add(year);
		addImage.add(com_month);
		addImage.add(month);
		addImage.add(com_day);
		addImage.add(day);
		addImage.add(confirm);
		addImage.add(cancel);
		addImage.add(problem);
		addImage.add(com_problem);
		addImage.add(field_problem);
		container.add(addImage);
	//***********************************			//在确认按钮加上监听器
			confirm.addMouseListener(new MouseAdapter() {
				 public void mouseClicked(MouseEvent event) {	
					new Thread() {public void run() {
						if((field_acc.getText()==null)||field_acc.getText().equals(""))  JOptionPane.showMessageDialog(null, "     用户名不能为空");
						else if((String.valueOf(field_pwd.getPassword())==null)||(String.valueOf(field_pwd.getPassword()).equals("")))  JOptionPane.showMessageDialog(null, "     密码不能为空");
						else if(!(String.valueOf(field_pwd.getPassword()).equals(String.valueOf(field_pwd1.getPassword())))) JOptionPane.showMessageDialog(null, "     两次密码不相同");
						else if((field_name.getText()==null)||field_name.getText().equals("")) JOptionPane.showMessageDialog(null, "     昵称不能为空");	  
						//将用户信息导入 
						else {
							String account=field_acc.getText();	
				        	String password=String.valueOf(field_pwd.getPassword());
				        	String sex=String.valueOf(com_sex.getSelectedItem());	
				        	String name=field_name.getText();
				        	String birthday=String.valueOf(com_year.getSelectedItem())+"年"+String.valueOf(com_month.getSelectedItem())+"月"+String.valueOf(com_day.getSelectedItem())+"日";				      				        				    					         
				            String problem=String.valueOf(com_problem.getSelectedItem());
			        	    String answer=field_problem.getText();
			        	    String headS="src/cloHeadSculpture/cloHeadSculpture"+String.valueOf(com_head.getSelectedItem())+".png";
							try {
							socket = new Socket(serverIP,port);
					   		out=new ObjectOutputStream(socket.getOutputStream());
					   		registerinfo re=new registerinfo(account,password,name,sex,birthday,problem,answer,headS);					   		
					   		out.writeObject(re);
							out.flush();
					   		in = new ObjectInputStream(socket.getInputStream());   	
					   		yn re2=(yn)in.readObject();	
					   		if(re2.dir.equals("yes"))  {JOptionPane.showMessageDialog(null, "     注册成功");dispose();}
					   		else JOptionPane.showMessageDialog(null, "     用户名或用户名称已经存在");
					   		socket.close();
							
							}    	
							
							
							catch(IOException e) {
						   		e.printStackTrace();
						   	} catch (ClassNotFoundException e) {
								e.printStackTrace();
								}	
						        	
						        }

							  
						  }}.start();
						}
				});
		//**********************************************		
				//在取消按钮中加入监听器
				cancel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						dispose();
					}
					
				});
				this.setVisible(true);

		} 
	
    //用于修改日期中的选项的方法
	private static int getDay(int year,int month) {
		if(month==2) {
			if((year%4==0&&year%100!=0)||(year%100==0||year%400==0)) {
				return 29;
			}
			else return 28;
		}
		else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
			return 31;
		}
		else return 30;

	}
	public static void main(String[] args) {
		register_interface L = new register_interface();
	}
}