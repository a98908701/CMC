package client.Threads;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SendfileThreads extends Thread {
	String path;
	public SendfileThreads(String path){
	this.path=path;	
	start();
	}
	public void run() {
		int MONITORPORT  = 12345;
		try {
			ServerSocket ss = new ServerSocket(MONITORPORT);
			//��ʮ������û�н��ܣ�˵���Է��ܾ�����رշ����			
			Socket s = ss.accept();
			byte[]buf = new byte[100];
             OutputStream os=null;
             FileInputStream fins=null;
             try{
                      os= s.getOutputStream();
                      //�ļ�·��
                      
                      //�ļ���
                      
                      System.out.println("���ļ���:"+path+"�����ȥ");
                      //�Ƚ��ļ��������ȥ
                      os.write(path.getBytes());
                      System.out.println("��ʼ�����ļ�");
                      //���ļ������ȥ 
                      //��ȡ�ļ�
                      fins= new FileInputStream(path);
                      int data;
                      //ͨ��fins��ȡ�ļ�����ͨ��os���ļ�����
                      while(-1!=(data= fins.read()))
                      {
                              os.write(data);
                      }
                      System.out.println("�ļ��������");
		
		
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}finally
 {
          try{
                   if(fins!=null)fins.close();
                   if(os!=null)     os.close();
                   s.close();
                   ss.close();
          }catch (IOException e1) {
                   e1.printStackTrace();
          }
          
 }

		}catch (IOException e1) {
			
			e1.printStackTrace();
		}
	    
		System.out.print(path);
	
	}
}
