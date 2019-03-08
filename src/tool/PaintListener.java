package tool;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import tool.shape.*;



public class PaintListener implements MouseListener,ActionListener{
	private Graphics2D g;
	private String cmd = "直线";
	private int x1, x2, y1, y2;
	private Color color=Color.black;
	private JFrame frame;
	public ArrayList list;
	public void setJFrame(JFrame frame) {
        this.frame = frame;
    }
	public void setGraphics(Graphics2D g) {
        this.g = g;
    }
	public void setList(ArrayList list) {
		this.list=list;
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		x1=e.getX();
		y1=e.getY();
		g.setColor(color);
	}
	public void mouseReleased(MouseEvent e) {
		x2=e.getX();
		y2=e.getY();
		if(cmd.equals("直线")) {
			Shape line = new Line(x1, y1, x2, y2,color);
			line.Draw(g);  
            list.add(line);
		}
		else
			if(cmd.equals("矩形")) {
				 Shape rect = new Rect(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),color);
				 rect.Draw(g);
				 list.add(rect);
			}
			else if(cmd.equals("椭圆")) {
				Shape oval = new Oval(Math.min(x2, x1),Math.min(y2, y1), Math.abs(x2-x1),Math.abs(y1-y2),color);  
				oval.Draw(g);
				list.add(oval);
			}
			else if (cmd.equals("三角形")) {
				Shape triangle = new Triangle(x1, y1, x2, y2,color);
				triangle.Draw(g);
				list.add(triangle);
	        }
		
	}
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("")) {
            JButton jbu = (JButton) arg0.getSource();
            color = jbu.getBackground();
        } else {
            cmd = arg0.getActionCommand();
        }
        if (cmd.equals("清空")) {
            frame.repaint();
            list.clear();
            cmd="直线";
        }
	}
	public Graphics2D getG(){
		return g;
	}

}

