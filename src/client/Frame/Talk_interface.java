package client.Frame;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
//改过了
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import client.Threads.SendfileThreads;
//import test2.ClientThread;

public class Talk_interface extends JFrame{
		
		JButton  p0=new JButton("关闭");
	    JButton  p1=new JButton("发送");
	    JLabel  p2=new JLabel("涂鸦发送");
	    JLabel  p3=new JLabel("传输文件");
	    JLabel  p4=new JLabel("发送图片");	
	    public JTextPane record=new JTextPane();
	    JTextPane message=new JTextPane();
	    String serverIP =Login_interface.serverIP;
	    Socket socket=null;
	    private JLabel hisname;
	    private JLabel id=new JLabel("ID:");
	    private JLabel hisid;
	   
	    String hisnames;
	    String myname;
	    String hisidS;
	    public Talk_interface(String myname,String hisnameS,String hisidS){
	    	this.hisnames=hisnameS;
	    	this.hisidS=hisidS;
	    	hisname = new JLabel(hisnameS);
	    	hisid = new JLabel(hisidS); 
	    	 try { // 使用Windows的界面风格  
	    		   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");  
	    		  } catch (Exception e) {  
	    		   e.printStackTrace();  
	    		  }  
	    	 addWindowListener(new WindowAdapter()
		        {
		            public void windowClosing(WindowEvent e)
		            {
		                  dispose();
		            }
		        });
	    	  
	    Font font=new Font("宋体",Font.PLAIN,18);	  
	    record.setFont(font);
	    record.setEditable(false);
	    JScrollPane  records=new JScrollPane( record);//新建一个滚动条界面，将文本框传入
	    JScrollPane   messages=new JScrollPane( message);
	    messages.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    	  

	    	

    Container container=getContentPane();
    
    container.setLayout(null);
    JLabel bgImage = new JLabel();
    Image img = new ImageIcon("src\\\\\\\\ui material\\\\\\\\聊天背景.jpg").getImage();
	bgImage.setIcon(new ImageIcon(img));
	bgImage.setBounds(0,-20,700,900);
	container.add(bgImage);
	
	setTitle("Wetalk");
    setSize(620,900);//设窗体的大小     宽和高
    setVisible(false);//设定窗体的可视化
    //设置窗体的关闭方式 
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    
    hisname.setBounds(200, 35, 100, 60);
    id.setBounds(200, 70, 100, 60);
    hisid.setBounds(220, 70, 100, 60);
    
    
    
    
    hisname.setFont(new java.awt.Font("Dialog",1,20));   
    id.setFont(new java.awt.Font("Dialog",1,15));   
    hisid.setFont(new java.awt.Font("Dialog",1,15));   
    
    p0.setBounds(460,824,90,25);
    p1.setBounds(360,824,90,25);
    p2.setBounds(100,662,100,60);
    p3.setBounds(260,662,100,60);
    p4.setBounds(440,662,100,60);
    records.setBounds(28, 130,545,525);
    messages.setBounds(30, 732,541,87);
    
    p0.addMouseListener(new MouseAdapter() {
  		public void mouseClicked(MouseEvent e) {
  			dispose();	
 
  		}});
   //涂鸦发送
    p2.addMouseListener(new MouseAdapter() {
  		public void mouseClicked(MouseEvent e) {
  			
  		
  	    	
  			
  			paint_interface ii=new paint_interface(myname,hisnames);
  		}});
  			
  	    
  	  
  	 
    
    
    
    
    
    
    
    
    
    p3.addMouseListener(new MouseAdapter() {
  		public void mouseClicked(MouseEvent e) {
  			
  		JFileChooser jf =new JFileChooser();
  		jf.setDialogTitle("选择文件");
  		FileNameExtensionFilter filter = new FileNameExtensionFilter("图片文件", "jpg", "png","txt","doc","mp4");
  		jf.setFileFilter(filter);
  		//初始化选择路径
  		FileSystemView fsv = FileSystemView.getFileSystemView();
  		File homeFile =fsv.getHomeDirectory();  //这便是读取桌面路径的方法了
  	    jf.setCurrentDirectory(homeFile);
  		
  		jf.setFileSelectionMode(JFileChooser.FILES_ONLY);//初始化选择模式
  		jf.setMultiSelectionEnabled(false);//不允许多选
  		JLabel bgImage = new JLabel();
  		//打开文件选择器
  	    int i = jf.showDialog(bgImage, "选择");
  	    String path=null;
  	    if(i == JFileChooser.APPROVE_OPTION){
  	    	StringBuilder sb;
  	    	File file = jf.getSelectedFile();
  	    	path =file.getAbsolutePath();		
  	    	SendfileThreads send=new SendfileThreads(path);
  	    }
  	    System.out.print(path);
  	  
  	    
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

			pw.println(myname+","+hisnameS+","+"file");
			
			try {
				socket.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.print(path);
  		}});
   //发送图片
    p4.addMouseListener(new MouseAdapter() {
  		public void mouseClicked(MouseEvent e) {
  			
  		JFileChooser jf =new JFileChooser();
  		jf.setDialogTitle("选择图片");
  		FileNameExtensionFilter filter = new FileNameExtensionFilter("图片文件", "jpg", "png");
  		jf.setFileFilter(filter);
  		//初始化选择路径
  		FileSystemView fsv = FileSystemView.getFileSystemView();
  		File homeFile =fsv.getHomeDirectory();  //这便是读取桌面路径的方法了
  	    jf.setCurrentDirectory(homeFile);
  		
  		jf.setFileSelectionMode(JFileChooser.FILES_ONLY);//初始化选择模式
  		jf.setMultiSelectionEnabled(false);//不允许多选
  		JLabel bgImage = new JLabel();
  		//打开文件选择器
  	    int i = jf.showDialog(bgImage, "选择");
  	    String path=null;
  	    if(i == JFileChooser.APPROVE_OPTION){
  	    	StringBuilder sb;
  	    	File file = jf.getSelectedFile();
  	    	path =file.getAbsolutePath();		
  	    	
  	    	Document docs = record.getDocument();
  			try {
				docs.insertString(docs.getLength(), "\r\n"+myname+":"+"\r\n",null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//向jaMsg中插入一张图片
  			Document docs2 = record.getDocument();
 			record.setCaretPosition(docs2.getLength());
  			
  			ImageIcon img = new ImageIcon(path);
			record.insertIcon(img);

  	    	SendfileThreads send=new SendfileThreads(path);	
  	    }
  	    System.out.print(path);
  	  
  	    
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

			pw.println(myname+","+hisnameS+","+"photo");
			
			try {
				socket.close();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.print(path);
  		}});
    
    
    
    
    
    
    
    p1.addMouseListener(new MouseAdapter() {
  		public void mouseClicked(MouseEvent e) { try {
  			Document docs = record.getDocument();
  			try {
				docs.insertString(docs.getLength(), "\r\n"+myname+":"+message.getText()+"\r\n",null);
			} catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
  			
  			
  		socket = new Socket(serverIP,8892);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	try {
		BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
    BufferedWriter bw = null;
	try {
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	//包含信息发送给服务端，包含接收者的id


				if(!(message.getText().equals(""))) 

				{
			
				PrintWriter pw=new PrintWriter(bw,true); 

    			pw.println(myname+","+hisnameS+","+message.getText());
    			
    			message.setText("");
    			
				}
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
  		}});
    
    
    
    bgImage.add(hisname);
    bgImage.add(id);
    bgImage.add(hisid);
    
    bgImage.add(p0);
    bgImage.add(p1);
    bgImage.add(p2);
    bgImage.add(p3);
    bgImage.add(p4);
    bgImage.add(records);
    bgImage.add(messages);
    
    
    
    
}
	    public static void main(String[] args) {
	    	Talk_interface f = new Talk_interface("谢镇宇","卢世逸","2");
		}	    
}