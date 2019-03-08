package tool.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;  
import java.io.Serializable;  
  
public class Line extends Shape implements Serializable{  
	public  int x1,y1,x2,y2;//绘制图形的坐标  
	  //抽象的Draw方法  
    //子类构造函数  
    public Line(int x1,int y1,int x2,int y2,Color color){  
        this.x1=x1;  
        this.y1=y1;  
        this.x2=x2;  
        this.y2=y2;  
        this.color=color;
          
        
    }  
  //重写父类的Draw方法，实现直线的绘制  
    public void Draw(Graphics2D g) {  
    	g.setColor(color);
        g.drawLine(x1, y1, x2, y2);  
    }



  
}  