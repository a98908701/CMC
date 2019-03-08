package client.Frame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
//�Ĺ���
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;


import tool.Group;

//import test2.ClientThread;

public class Grouptalk_interface extends JFrame{

	    JButton  p1=new JButton("����");
	    JLabel  list=new JLabel("��ʾȺ��Ա");
	    JLabel  quit=new JLabel("�˳�Ⱥ��");
	    JLabel  up=new JLabel("ˢ�³�Ա");	
	    JTextPane record=new  JTextPane();
	    JTextArea message=new JTextArea(30,80);
	    JTextArea member=new JTextArea(20,80);
	    JComboBox com_face = new JComboBox();
	    private JLabel order;
	    private JLabel id=new JLabel("�����Ҷ˿ں�:") ;
	    private String name;
	    Socket socket = null;
	    String orders;
	    String serverIP =Login_interface.serverIP;
	    BufferedReader br=null;
	    BufferedWriter bw = null;
	    private String myHeadSculptureNum;
	    private String face="����";
	    public Grouptalk_interface(String orders,String names,String myHeadSculptureNum){
	    	this.myHeadSculptureNum=myHeadSculptureNum;
	    	this.orders=orders;
	    	order = new JLabel(orders);
	    	name=names;
	    	 try { // ʹ��Windows�Ľ�����  
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
	    	  
	    Font font=new Font("����",Font.PLAIN,18);	  
	    record.setFont(font);
	    record.setEditable(false);
	    JScrollPane  records=new JScrollPane(record);//�½�һ�����������棬���ı�����
	    JScrollPane   messages=new JScrollPane(message);
	    JScrollPane  members=new JScrollPane(member);
	    
	    records.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    messages.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    Container container=getContentPane();
    
    container.setLayout(null);
    JLabel bgImage = new JLabel();
    Image img = new ImageIcon("src\\\\ui material\\\\���챳��.jpg").getImage();
	bgImage.setIcon(new ImageIcon(img));
	bgImage.setBounds(0,-20,700,900);
	container.add(bgImage);
	
	setTitle("Ⱥ�Ľ���");
    setSize(620,900);//�贰��Ĵ�С     ��͸�
    
    //���ô���Ĺرշ�ʽ 
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    com_face.addItem("����");	  
    com_face.addItem("����");
    com_face.addItem("����");
    com_face.addItem("�ѹ�");
    com_face.addItem("�±�");
    com_face.addItem("����");
    com_face.setBounds(380,824,70,25);
   
    id.setBounds(170, 55, 100, 60);
    order.setBounds(270, 55, 100, 60);
    
    com_face.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			JComboBox cb = (JComboBox)arg0.getSource();
			face=(String) cb.getSelectedItem();
			System.out.println(face);
		}
    	
    });
    
    
      
    id.setFont(new java.awt.Font("Dialog",1,15));   
    order.setFont(new java.awt.Font("Dialog",1,15));   
    
    
    p1.setBounds(460,824,90,25);
    list.setBounds(100,662,100,60);
    quit.setBounds(260,662,100,60);
    up.setBounds(450,662,55,60);
    
    records.setBounds(28, 130,480,525);
    messages.setBounds(30, 732,541,87);
    members.setBounds(506, 130,65,525);
    
    addWindowListener(new WindowAdapter()
    {
        public void windowClosing(WindowEvent e)
        {
         
          try{	
      		
      		Socket client = new Socket(serverIP,5101);
      		
      		BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
      		PrintWriter pw = new PrintWriter(out,true); 
      		
      		System.out.println("join"+","+orders+","+name);
      		pw.println("quit"+","+orders+","+name);  
              pw.flush();
      		client.close();
          }catch (IOException e1) {
      		// TODO Auto-generated catch block
      		e1.printStackTrace();
      	}	
          dispose();
          
          
        	
        	
        }
    });
    
    
    
    
    
    
    quit.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
    try{	
		
		Socket client = new Socket(serverIP,5101);
		
		BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		PrintWriter pw = new PrintWriter(out,true); 
		
		System.out.println("join"+","+orders+","+name);
		pw.println("quit"+","+orders+","+name);  
        pw.flush();
		client.close();
    }catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
    dispose();
		
		
		}});
		
		
    up.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			String[]str=null;
			try {
			Socket client = new Socket(serverIP,5102);
			
			BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			PrintWriter pw = new PrintWriter(out,true); 
			pw.println(orders);  
            pw.flush();
			
			

			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
			String msg = in.readLine();		
			
			str = msg.split(",");
			
			client.close();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			
		    	 
				member.setText("");
		        for(int i=0;i<str.length;i++) 
		        {
		        	member.append(str[i]);
		        	member.append("\r\n"+"\r\n");
		        }
		       
				
		}
		});	
		
		
    
    list.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			String[]str=null;
			try {
			Socket client = new Socket(serverIP,5102);
			
			BufferedWriter out=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
			PrintWriter pw = new PrintWriter(out,true); 
			pw.println(orders);  
            pw.flush();
			
			

			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));  
			String msg = in.readLine();		
			
			str = msg.split(",");
			
			client.close();
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			
		    	 
				member.setText("");
		        for(int i=0;i<str.length;i++) 
		        {
		        	member.append(" ");
		        	member.append(str[i]);
		        	member.append("\r\n"+"\r\n");
		        }
		       
				
		}
		});
 
    
    
    
    
    
    p1.addMouseListener(new MouseAdapter() {
		
			@SuppressWarnings("resource")
			public void mouseClicked(MouseEvent e) {
		

				//������Ϣ���͸�����ˣ����������ߵ�id
				int port=Integer.parseInt(orders);	
				
				try {
					socket = new Socket(serverIP,port);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				try {
					br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
				try {
					bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 

							if(!(message.getText().equals(""))) 

							{
						
							PrintWriter pw=new PrintWriter(bw,true); 
							
			    			pw.println(name+","+message.getText()+","+myHeadSculptureNum+","+face);
			    			
			    			message.setText("");
			    			
							}
							
							try {
								socket.close();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							

						
				
			} });
			
			
			
			
			
			
			
			
		
    
    
    
    bgImage.add(up);
    bgImage.add(id);
    bgImage.add(order);
    bgImage.add(list);
    bgImage.add(quit);
    bgImage.add(p1);
    bgImage.add(members);
    bgImage.add(records);
    bgImage.add(messages);
    bgImage.add(com_face);
    setVisible(true);//�趨����Ŀ��ӻ�
    
    
}
	    public static void main(String[]args)	{
	    	Grouptalk_interface group=new Grouptalk_interface("1","л����","12");
	    	
	    } 
   
	    
	    
}