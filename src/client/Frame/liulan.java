package client.Frame;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



import tool.id_ip;

	public class liulan extends JFrame{
		//��������Ĵ���
				JLabel addImage = new JLabel();
				JLabel account = new JLabel("��ַ");
				JTextField path=new JTextField(20);
				JButton confirm = new JButton(new ImageIcon("src\\ui material\\button_conAdd.png"));
				JButton cancel = new JButton(new ImageIcon("src\\ui material\\button_exit.png"));
				
				Socket socket = null;
				String serverIP =Login_interface.serverIP;		
				String hisname;
				String myname;
			public liulan(){
			this.myname=myname;
			this.hisname=hisname;
			//��JFrame��������
			this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
			this.setTitle("������վ");
			this.setBounds(0,0,400,200);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			
			Container container =this.getContentPane();
			container.setLayout(null);
			
			
			//����ͼƬ
			addImage.setBounds(0, 0, 500, 400);
			Icon icon = new ImageIcon("src\\\\ui material\\\\little_background2.PNG");
			addImage.setIcon(icon);
			addImage.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
			//�˺�
			account.setFont(new java.awt.Font("����",1,16));
			account.setBounds(70,50,100,20);
			account.setFont(new java.awt.Font("����",1,18));
			account.setForeground(Color.white);
			path.setBounds(170,50,150,20);
			path.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 123, 134)));
			path.setBackground (new Color(18,25,32));
			path.setForeground(Color.white);//*****
			//ȷ�Ϻ�ȡ��
			confirm.setBounds(80,100,100,25);
			cancel.setBounds(230,100,100,25);
			confirm.setBorderPainted(false);
			confirm.setContentAreaFilled(false);
			cancel.setBorderPainted(false);
			cancel.setContentAreaFilled(false);
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
				
					if (java.awt.Desktop.isDesktopSupported()) {  
			            try {  
			                // ����һ��URIʵ��  
			                java.net.URI uri = java.net.URI.create(path.getText());  
			                // ��ȡ��ǰϵͳ������չ  
			                java.awt.Desktop dp = java.awt.Desktop.getDesktop();  
			                // �ж�ϵͳ�����Ƿ�֧��Ҫִ�еĹ���  
			                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {  
			                    // ��ȡϵͳĬ�������������  
			                    dp.browse(uri);  
			                }  
			  
			            } catch (Exception e1) {  
			                e1.printStackTrace();  
			            }  
			        } 
					
					
					
					
					
					
					
					
					
					
				dispose();				
				}
				});
			
			
			
		
			
		}
		
		public static void main(String[] args) {
			liulan L = new liulan();
		}

		

	}