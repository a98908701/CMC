package tool.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;  
import java.io.Serializable;  
  
  
//������  
public abstract class Shape implements Serializable{  
      
    public  int x1,y1,x2,y2;//����ͼ�ε�����  
    public Color color;
    //�����Draw����  
    public abstract void Draw(Graphics2D g);  
  
}  
