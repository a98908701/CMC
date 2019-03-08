package tool.shape;

import java.awt.BasicStroke;  
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;  
import java.io.Serializable;  
  
public class Rect  extends Shape implements Serializable{  
	
    public Rect(){  
          
    }  
    //���εĹ��췽��  
    public Rect(int x1,int y1,int x2,int y2,Color color){  
        this.x1=x1;  
        this.y1=y1;  
        this.x2=x2;  
        this.y2=y2;  
        this.color=color; 
         
    }  
  
    //��д�����Draw������ʵ�־��εĻ���  
    public void Draw(Graphics2D g) {    
    	g.setColor(color);
        g.drawRect(x1, y1, x2, y2);  
    }  
  
}  
