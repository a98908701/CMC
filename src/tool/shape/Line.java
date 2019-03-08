package tool.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;  
import java.io.Serializable;  
  
public class Line extends Shape implements Serializable{  
	public  int x1,y1,x2,y2;//����ͼ�ε�����  
	  //�����Draw����  
    //���๹�캯��  
    public Line(int x1,int y1,int x2,int y2,Color color){  
        this.x1=x1;  
        this.y1=y1;  
        this.x2=x2;  
        this.y2=y2;  
        this.color=color;
          
        
    }  
  //��д�����Draw������ʵ��ֱ�ߵĻ���  
    public void Draw(Graphics2D g) {  
    	g.setColor(color);
        g.drawLine(x1, y1, x2, y2);  
    }



  
}  