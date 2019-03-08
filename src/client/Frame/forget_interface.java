package client.Frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import message.forgetinfo;
import message.registerinfo;
import message.yn;

public class forget_interface extends JFrame{
	private Socket socket = null;
	private ObjectOutputStream out = null;
	private ObjectInputStream in =null;
	
	String serverIP =Login_interface.serverIP;
	int port = 5001;
	
	public forget_interface() {
		//��JFrame��������
				this.setTitle("�һ�����");
				this.setBounds(0,0,490,430);
				this.setLocationRelativeTo(null);
				this.setLayout(null);
				this.setUndecorated(true);
				Container container =this.getContentPane();
				container.setLayout(null);
				
				//�����齨�Ĵ���
				JLabel addImage = new JLabel();
				JLabel account = new JLabel("�������˺�");
				JTextField field_acc=new JTextField(20);
				JLabel problem = new JLabel("�������ܱ�����");
				JComboBox com_problem = new JComboBox();
				JTextField field_problem = new JTextField(20);
				JLabel pwd =new JLabel("������");
				JLabel pwd1 = new JLabel("ȷ������");
				JPasswordField field_pwd=new JPasswordField(20);
				JPasswordField field_pwd1=new JPasswordField(20);
				JButton confirm = new JButton(new ImageIcon("src\\ui material\\button_image_confirm.png"));
				JButton cancel = new JButton(new ImageIcon("src\\ui material\\button_image_cancel.png"));
				//����ͼƬ
				addImage.setBounds(0, 0, 490, 430);
				Icon icon = new ImageIcon("src\\ui material\\forget_interface_background.png");
				addImage.setIcon(icon);
				addImage.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
				//�˺�
				account.setFont(new java.awt.Font("����",1,18));
				account.setForeground(Color.white);
				account.setBounds(90,70,100,30);
				field_acc.setBounds(190,70,150,30);
				field_acc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
				field_acc.setBackground (new Color(18,25,32));
				field_acc.setForeground(Color.white);
				//�ܱ�����
				com_problem.addItem("���ĸ�׵������ǣ�");
				com_problem.addItem("��ĸ��׵������ǣ�");
				com_problem.addItem("���Сѧ�����ε������ǣ�");
				com_problem.addItem("��ĳ���ѧУ��ʲô��");
				com_problem.addItem("��ĳ������ǣ�");
				com_problem.addItem("���ѧ���ǣ�");
				com_problem.addItem("�����ż�������ǣ�");
				problem.setFont(new java.awt.Font("����",1,18));
				problem.setForeground(Color.white);
				problem.setBounds(50,120,150,30);
				com_problem.setBounds(190,120,150,30);
				field_problem.setBounds(350,120,120,30);
				field_problem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
				field_problem.setBackground (new Color(18,25,32));
				field_problem.setForeground(Color.white);
				//������
				pwd.setFont(new java.awt.Font("����",1,18));
				pwd.setForeground(Color.white);
				pwd.setBounds(130,170,60,30);
				field_pwd.setBounds(190,170,150,30);
				field_pwd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
				field_pwd.setBackground (new Color(18,25,32));
				field_pwd.setForeground(Color.white);
				//ȷ������
				pwd1.setFont(new java.awt.Font("����",1,18));
				pwd1.setForeground(Color.white);
				pwd1.setBounds(110,220,100,30);
				field_pwd1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
				field_pwd1.setBackground (new Color(18,25,32));
				field_pwd1.setBounds(190,220,150,30);
				field_pwd1.setForeground(Color.white);
				//ȷ�Ϻ�ȡ��
				confirm.setBounds(80,300,120,30);
				cancel.setBounds(300,300,120,30);
				confirm.setBorderPainted(false);
				confirm.setContentAreaFilled(false);
				cancel.setBorderPainted(false);
				cancel.setContentAreaFilled(false);
				//���ּ���
				addImage.add(account);
				addImage.add(field_acc);
				addImage.add(pwd);
				addImage.add(field_pwd);
				addImage.add(pwd1);
				addImage.add(field_pwd1);
				addImage.add(confirm);
				addImage.add(cancel);
				addImage.add(problem);
				addImage.add(com_problem);
				addImage.add(field_problem);
				container.add(addImage);
		//ȡ����ť���������
		cancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
			
		});
		confirm.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent event) {	
				new Thread() {public void run() {
					if((field_acc.getText()==null)||field_acc.getText().equals(""))  JOptionPane.showMessageDialog(null, "     �û�������Ϊ��");
					else if((String.valueOf(field_pwd.getPassword())==null)||(String.valueOf(field_pwd.getPassword()).equals("")))  JOptionPane.showMessageDialog(null, "     ���벻��Ϊ��");
					else if(!(String.valueOf(field_pwd.getPassword()).equals(String.valueOf(field_pwd1.getPassword())))) JOptionPane.showMessageDialog(null, "     �������벻��ͬ");
					else if(field_problem.getText().equals("")) JOptionPane.showMessageDialog(null, "     ����𰸲���Ϊ��");
					//�����ҽ��� 
					else {
						String account=field_acc.getText();	
			        	String password=String.valueOf(field_pwd.getPassword());	       
		        	    String answer=field_problem.getText();  
		        	    String problem=String.valueOf(com_problem.getSelectedItem());
		        	    try {
						socket = new Socket(serverIP,port);
				   		out=new ObjectOutputStream(socket.getOutputStream());
				   		forgetinfo re=new forgetinfo(account,password,problem,answer);					   		
				   		out.writeObject(re);
						out.flush();
				   		in = new ObjectInputStream(socket.getInputStream());   	
				   		yn re2=(yn)in.readObject();	
				   		if(re2.dir.equals("yes"))  JOptionPane.showMessageDialog(null, "     �޸�����ɹ�");
				   		else if(re2.dir.equals("else"))  JOptionPane.showMessageDialog(null, "     �û���������");								
				   		else if(re2.dir.equals("no"))  JOptionPane.showMessageDialog(null, "     ����𰸴���");
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
		
		
		
		
		this.setVisible(true);

	}
	
	public static void main(String[] args) {
		forget_interface L = new forget_interface();
	}
	

}
