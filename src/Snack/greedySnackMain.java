package Snack;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;


public class greedySnackMain extends JFrame {
    snackWin snackwin;
    static final int Width = 800 , Height = 600 , LocX = 200 , LocY = 80;
    public greedySnackMain() {
        super("GreedySncak_SL");
        snackwin = new snackWin();
        add(snackwin);
        snackwin.quitButton.addMouseListener(new MouseAdapter() {
      		public void mouseClicked(MouseEvent e) {
      			dispose();	
      		}});
        
        this.setSize(Width, Height);
        this.setVisible(true);
        this.setLocation(LocX, LocY);
        //snackwin.requestFocus();
    }
    public static void main(String[] args) {
        new greedySnackMain();
    }
}