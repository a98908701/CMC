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
			//若十秒后对面没有接受，说明对方拒绝，则关闭服务端			
			Socket s = ss.accept();
			byte[]buf = new byte[100];
             OutputStream os=null;
             FileInputStream fins=null;
             try{
                      os= s.getOutputStream();
                      //文件路径
                      
                      //文件名
                      
                      System.out.println("将文件名:"+path+"传输过去");
                      //先将文件名传输过去
                      os.write(path.getBytes());
                      System.out.println("开始传输文件");
                      //将文件传输过去 
                      //获取文件
                      fins= new FileInputStream(path);
                      int data;
                      //通过fins读取文件，并通过os将文件传输
                      while(-1!=(data= fins.read()))
                      {
                              os.write(data);
                      }
                      System.out.println("文件传输结束");
		
		
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
