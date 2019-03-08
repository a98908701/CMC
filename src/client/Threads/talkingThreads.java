package client.Threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import client.Frame.Login_interface;
import client.Frame.Main_interface;
import client.Frame.Talk_interface;
import client.Frame.receive_interface;
import tool.id_ip;

public class talkingThreads extends Thread{
		 public Socket receive;
		 public String hisname;
		 public Talk_interface t;
		 public String id;
	    	public talkingThreads(String hisname,Talk_interface t,String id) {
	    		this.id=id;
	    		this.hisname=hisname;
	    		this.t=t;
	    		this.start();
	    	}
	    	public   void run() {
				try {
					receive = new Socket(Login_interface.serverIP,8892);
					Main_interface.ff.add(receive);
	 	    		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(receive.getOutputStream()));
	 	    		PrintWriter pw =new PrintWriter(bw,true); 
	                pw.println("a,a,a");  
	                pw.flush();
	 	    		
				} catch (IOException e3) {
					e3.printStackTrace();
				}
	 	    
 	    		while(true) {
					String info = null;
					try {
						//下面这句应该是即使没有连接也可以
						BufferedReader br = new BufferedReader(new InputStreamReader(receive.getInputStream()));
						info = br.readLine();
					  } catch (IOException e1) {break;}
					if(info!=null)
					{
					String[] str = info.split(","); 
					if(str[0].equals(hisname)&&str[1].equals(id)&&str[2].equals("file"))
					{	
						receive_interface jjj=new receive_interface(id,hisname);//用于接受的窗口，需要用户自行输入保存路径

					}
					
					if(str[0].equals(hisname)&&str[1].equals(id)&&str[2].equals("photo"))
					{	
						id_ip ii=new id_ip();
						String ip=null;
						try {
							ip=ii.getip(hisname);
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						String SERVERIP = ip;
						int SERVERPORT = 12345;
						int CLIENTPORT = 54321;
						 byte[]buf = new byte[100];
		                   
		                   Socket s = new Socket();
		                   try{
		                            //建立连接
		                            s.connect(new InetSocketAddress(SERVERIP,SERVERPORT), CLIENTPORT);
		                            InputStream is = s.getInputStream();
		                            //接收传输来的文件名
		                            int len = is.read(buf);
		                            String fileName = new String(buf,0,len);
		                            System.out.println(fileName);    
		                            //随机建一个文件名来接受传来的文件
		                            String[] str2 =fileName.split("\\."); 
		                            char a=(char)('a'+Math.random()*('z'-'a'+1));
		                    		String aa= new Character(a).toString();
		                    		int b=(int)(Math.random()*10000);	
		                     		String file=b+aa+"."+str2[1]; 
		                    		FileOutputStream fos = new FileOutputStream("src\\receive date"+"\\"+file); 
		                            int data;
		                            while(-1!=(data= is.read()))
		                            {
		                               fos.write(data);
		                            }
		                            System.out.println("传输成功");
		                            is.close();
		                            s.close();
		                            Document docs = t.record.getDocument();
		                  			try {
		                  				docs.insertString(docs.getLength(), "\r\n"+hisname+":"+"\r\n",null);
		                			} catch (BadLocationException e1) {
		                				// TODO Auto-generated catch block
		                				e1.printStackTrace();
		                			}
		                  			 Document docs2 = t.record.getDocument();
		                  			 t.record.setCaretPosition(docs2.getLength());
		                  			 ImageIcon img = new ImageIcon("src\\receive date"+"\\"+file);
			                  		t.record.insertIcon(img);
		                   }catch (IOException e1) {
		                            //TODO Auto-generated catch block
		                            e1.printStackTrace();
		                   }

						
						
						
						
					}
					
					
					
					
					
					
					if(str[0].equals(hisname)&&str[1].equals(id)&&!(str[2].equals("file"))&&!(str[2].equals("photo")))
					{	
						Document docs = t.record.getDocument();
						try {
							docs.insertString(docs.getLength(), "\r\n"+str[0]+":"+str[2]+"\r\n",null);
						} catch (BadLocationException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
					}
					
					}
		  			
		  			
		  			
					
					}
					
 	    		
 	    		
 	    		}
 	    		
	    		}
	    	}
	    
