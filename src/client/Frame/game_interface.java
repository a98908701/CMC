package client.Frame;

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

import Snack.greedySnackMain;
import cn.tedu.game.BirdThread;
import tool.id_ip;



public class game_interface extends JFrame{
	//各种组件的创建
			JLabel addImage = new JLabel();
			
			
			JButton confirm = new JButton(new ImageIcon("src\\\\ui material\\\\button_tanchishe.png"));
			JButton cancel = new JButton(new ImageIcon("src\\ui material\\button_flybird.png"));
			
					
			
		public game_interface(){
		
		//对JFrame进行设置
		this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		this.setTitle("选择小游戏");
		this.setBounds(0,0,504,255);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		Container container =this.getContentPane();
		container.setLayout(null);
		
		
		//加入图片
		addImage.setBounds(0, 0, 484, 216);
		Icon icon = new ImageIcon("src\\\\ui material\\\\little_background1.PNG");
		addImage.setIcon(icon);
		addImage.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
		//账号
		
		

		//确认和取消
		confirm.setBounds(70,90,100,23);
		cancel.setBounds(300,90,100,23);
		confirm.setBorderPainted(false);
		confirm.setContentAreaFilled(false);
		cancel.setBorderPainted(false);
		cancel.setContentAreaFilled(false);
		
		
		
		//各种加入
		
		
		addImage.add(confirm);
		addImage.add(cancel);
		
		container.add(addImage);
		this.setVisible(true);
		//取消按钮加入监听器
		cancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				try {
					BirdThread game1=new BirdThread();
					game1.start();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
			});
		
		
		confirm.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				 new greedySnackMain();
								
			}
			});
		 addWindowListener(new WindowAdapter()
	        {
	            public void windowClosing(WindowEvent e)
	            {
	            dispose();
	            }});
		
		
	
		
	}
	
	public static void main(String[] args) {
		game_interface L = new game_interface();
	}

	

}