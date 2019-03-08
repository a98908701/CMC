package client.Frame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.Threads.SendfileThreads;
import tool.PaintListener;
import tool.shape.*;

public class paint_interface extends JFrame {
	private JPanel board = new JPanel();
	private JButton clear=new JButton("清空");
	private JButton send=new JButton("发送");
	private PaintListener pp;
	Socket socket=null;
	String serverIP =Login_interface.serverIP;
	Graphics2D g;
	ArrayList<Shape> list = new ArrayList<Shape>();
	String myname;
	String hisname;
	String path=null;
	public paint_interface(String myname,String hisname) {
		this.myname=myname;
		this.hisname=hisname;
		pp=new PaintListener();
		creatFrame();
	}
	public void creatFrame() {
		this.setTitle("画板");
		this.setSize(520, 548);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
	    this.setBackground(new Color(18,25,32));
	    this.setLocationRelativeTo(null);
	 //   this.setResizable(false);
	    this.setLayout(null);
	    String[] buttonkind= {"直线", "矩形", "椭圆", "三角形"};
	    Color[] color = { Color.black, Color.BLUE, Color.GREEN, Color.YELLOW };
	    //背景设置
	    Image im = new ImageIcon("src\\ui material\\paint_interface_background.PNG").getImage();
	    JLabel jla =new JLabel();
        jla.setIcon(new ImageIcon(im));
        jla.setBounds(0, 0, 512, 512);
        this.add(jla);
        //绘图处设置
        board.setBounds(0, 0, 520, 383);
        board.setOpaque(false);
        jla.add(board);
        //第一处按钮的设置
        for (int i = 0; i < buttonkind.length; i++) {
            JButton jbu = new JButton(buttonkind[i]);
            jbu.setBorderPainted(false);
            jbu.setFont(new Font("楷体", 15, 15));
            jbu.setPreferredSize(new Dimension(130, 40));
            jbu.setBackground(new Color(18,25,32));
            jbu.setBounds(94*i,388,80,60);
            jbu.setForeground(Color.white);
            jla.add(jbu);
            jbu.addActionListener(pp);
          //  jbu.addActionListener(m);
        }
        //清空按钮
        clear.setBorderPainted(false);
        clear.setFont(new Font("楷体", 15, 15));
        clear.setBackground(new Color(18,25,32));
        clear.setBounds(376,450,80,60);
        clear.setForeground(Color.white);
        jla.add(clear);
        clear.addActionListener(pp);
        //发送按钮
        send.setBorderPainted(false);
        send.setFont(new Font("楷体", 15, 15));
        send.setBackground(new Color(18,25,32));
        send.setBounds(376,388,80,60);
        send.setForeground(Color.white);
        jla.add(send);
        //第二排按钮
        for (int i = 0; i < color.length; i++) {
            JButton jbu = new JButton();
            jbu.setBorderPainted(false);
            jbu.setBackground(color[i]);
            jbu.setBounds(94*i, 450,80, 60);
            jbu.setPreferredSize(new Dimension(130, 40));
            jla.add(jbu);
            jbu.addActionListener(pp);
        }
        
        
        this.setVisible(true);
        board.addMouseListener(pp);
        g = (Graphics2D)board.getGraphics();
        pp.setGraphics(g);
        pp.setList(list);
        pp.setJFrame(this);
        
        //发送按钮功能的实现
        send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//先将文件保存在本地
				saveFile();
				dispose();
				SendfileThreads send=new SendfileThreads(path);
				  try {
						 socket = new Socket(serverIP,8892);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					 BufferedWriter bw = null;
						try {
							bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						PrintWriter pw=new PrintWriter(bw,true); 

						pw.println(myname+","+hisname+","+"photo");
						
						try {
							socket.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
			  		}});
				
				
			}

	//传送图片到本地
        public void saveFile() {
        	try {
        		BufferedImage targetImg = new BufferedImage(520, 383, BufferedImage.TYPE_INT_RGB);//获得一个image对象
        		Graphics2D g2d = targetImg.createGraphics();
        		//设置为白背景
        		g2d.setColor(Color.WHITE);
        		g2d.fillRect(0, 0, 520, 383);
        	 
        		for(Shape a:list) {
        			a.Draw(g2d);
        		}
        		g2d.dispose();
        		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            	String a = df.format(new Date());
            	path="src\\paint"+"\\"+a+".png";
				FileOutputStream fos = new FileOutputStream("src\\paint"+"\\"+a+".png");
				ImageIO.write(targetImg, "JPEG", fos);
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
        }
	public static void main(String[] args) {
		paint_interface L = new paint_interface("1","2");
	}

}
