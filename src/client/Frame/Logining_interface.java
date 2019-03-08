package client.Frame;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;

public class Logining_interface extends JFrame{
	private JLabel back=new JLabel();
	private JLabel longing =new JLabel("登录中...");
	public Logining_interface() {
		creatFrame();
	}
	public void creatFrame() {
		this.setTitle("登录中");
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定窗口装饰风格
		this.setLayout(null);
		this.setBounds(0,0,332,232);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);//使得登录界面位于中间
		this.setResizable(false);//窗口大小无法改变
		this.setBackground(Color.blue);
		Container container = this.getContentPane();
		Image img = new ImageIcon("src\\ui material\\logining_interface_background.png").getImage();
		back.setIcon(new ImageIcon(img));
		back.setBounds(0,0,332,232);
		
		longing.setFont(new java.awt.Font("宋体",1,20));
		longing.setForeground(Color.white);
		longing.setBounds(127,100,100,50);
		back.add(longing);
		
		container.add(back);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		Logining_interface L = new Logining_interface();
	}

}
