package Server.SFrame;
//���
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import Server.Threads.AddfriendThreads;
import Server.Threads.ForgetThreads;
import Server.Threads.Group2Threads;
import Server.Threads.Group3Threads;
import Server.Threads.GroupThreads;
import Server.Threads.LoginThreads;
import Server.Threads.Main_showFriendListThreads;
import Server.Threads.OnetooneThreads;
import Server.Threads.RegisterThreads;
import Server.Threads.getFriendHeadSculpturThreads;
import Server.Threads.getipserver;
public class ServerFrame extends JFrame{
	final long serialVersionUID = 1L;
	final int	port= 5000;
	//ServerSocket server;
	//Socket socket = null;
	private JPanel southPane =new JPanel();
	private JButton start = new JButton("����������");
	private JButton breakoff = new JButton("�رշ�����");
	private JTextArea hintInfo;
    private JTextArea onlineUserInfo;
    private JScrollPane status ;
	private JScrollPane userlist ;
	ForgetThreads f;
	LoginThreads l;
	RegisterThreads r;
	Main_showFriendListThreads msf;
	OnetooneThreads onetoone;
	GroupThreads grp;
	Group2Threads grp2;
	Group3Threads grp3;
	getipserver getip;
	AddfriendThreads add;
	getFriendHeadSculpturThreads gft;
	public ServerFrame() throws IOException {
		
		
		
		hintInfo = new JTextArea("��ǰ״̬��������δ������\n");
		onlineUserInfo = new JTextArea();
		status=new JScrollPane(hintInfo);
		userlist=new JScrollPane(onlineUserInfo);
		creamFrame();
		 addWindowListener(new WindowAdapter()
	        {
	            public void windowClosing(WindowEvent e)
	            {
	                int t = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�˳���������", "ȷ���˳�", JOptionPane.OK_CANCEL_OPTION);
	                if (t == JOptionPane.OK_OPTION)
	                {
	                	close();
	                    System.exit(0);
	                }
	            }
	        });
		 
	}
	public void creamFrame() {
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//����ָ������װ�η��
		this.setLayout(null);
		this.setBounds(0,0,500,500);
		this.setBackground(Color.black);
		this.setLocationRelativeTo(null);//ʹ�õ�¼����λ���м�
		this.setResizable(false);//���ڴ�С�޷��ı�
		Container container = this.getContentPane();
		//�ϲ�jpanel�Ĵ���
		JTabbedPane centerPane = new JTabbedPane(JTabbedPane.TOP);
		centerPane.setBackground(Color.WHITE);
		hintInfo.setBackground(Color.WHITE);
		onlineUserInfo.setBackground(Color.WHITE);
		status.setBackground(Color.WHITE);
		userlist.setBackground(Color.WHITE);
		centerPane.setBounds(0,0,500,390);
		centerPane.add("״̬",status);
		centerPane.add("�û��б�",userlist);
		container.add(centerPane,BorderLayout.NORTH);		
		//�ײ�jpanel������
		southPane.setLayout(null);
		southPane.setBounds(0,300,500,200);
		start.setBounds(90, 110, 100, 30);
		breakoff.setBounds(290,110,100, 30);
		southPane.setBackground(Color.WHITE);
		southPane.add(breakoff);
		southPane.add(start);
		container.add(southPane,BorderLayout.SOUTH);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					f=new ForgetThreads();
					l=new LoginThreads();
					r=new RegisterThreads();
					msf=new Main_showFriendListThreads();
					onetoone=new OnetooneThreads();
					grp=new GroupThreads();
					grp2=new Group2Threads();
					grp3=new Group3Threads();
					getip=new getipserver();
					add=new AddfriendThreads();
					gft=new getFriendHeadSculpturThreads();
					JOptionPane.showMessageDialog(null, "     �����ɹ�");
					hintInfo.append("��ǰ״̬��������������\n");
					InetAddress ia = InetAddress.getLocalHost();
					hintInfo.append("��������"+ia.getHostName()+"\n");
					hintInfo.append("������ip��ַ��"+ia.getHostAddress()+"\n");
					hintInfo.append("��ʼ�����ͻ���....\n");
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "     ����ʧ��,������������","����", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
			
		});
		breakoff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						close();
						JOptionPane.showMessageDialog(null, "     �رճɹ�");
						hintInfo.append("��ǰ״̬��������δ����\n");
			}
});
		
		
		
		
		
		this.setVisible(true);
	}
	public static void main(String[] args) throws IOException   {
		ServerFrame S = new ServerFrame();
	}
	public void close()  {
		try {
			if(f!=null&&l!=null) {

		f.server.close();
		l.server.close();
		r.server.close();
		msf.server.close();
		onetoone.serverSocket.close();
		grp.serverSocket.close();
		getip.server.close();
		add.server.close();
		grp2.server.close();
		grp3.server.close();
		gft.server.close();
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}

