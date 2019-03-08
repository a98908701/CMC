package cn.tedu.game;

import javax.swing.JFrame;

public class BirdThread extends Thread{
	JFrame frame;
	BridGame game=null;
	
	public BirdThread() throws Exception{
		 frame = new JFrame();
		 game = new BridGame();
			frame.add(game);
			frame.setSize(440,670);//���ÿ��
			frame.setLocationRelativeTo(null);//���ھ���
			
			frame.setVisible(true);//���ô���ɼ���
		
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
