package cn.tedu.game;

import javax.swing.JFrame;

public class BirdThread extends Thread{
	JFrame frame;
	BridGame game=null;
	
	public BirdThread() throws Exception{
		 frame = new JFrame();
		 game = new BridGame();
			frame.add(game);
			frame.setSize(440,670);//设置宽高
			frame.setLocationRelativeTo(null);//窗口居中
			
			frame.setVisible(true);//设置窗体可见性
		
	}
	
	public void run() {
		
		try {
			game.action();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
}
