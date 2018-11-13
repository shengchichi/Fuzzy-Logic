package Hw_fuzzy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;




import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;


public class Map extends JFrame implements KeyListener, ActionListener {
	double x=0;
	double y=0;
	double x_d = 0;//direct
	double y_d = 22;
	double x_l = -6;//left
	double y_l = 6;
	double x_r = 6;//right
	double y_r = 6;
	double x_c = 0;//check
	double y_c = 6;
	double degree = 0;//旋轉角度
	double fi = 90;//與水平角度
	double d1=22;//直線距離
	double d2=8.4852814;//left distance
	double d3=8.4852814;//right distance
	double all[][] = new double[10000][2];
	int i =0;
	JTextArea deg = new JTextArea();
	JLabel label = new JLabel("旋轉角度(測試用)");
	JLabel err = new JLabel();
	JButton enter = new JButton("輸入");
	JButton start = new JButton("開始");
	Line2D line_bot = new Line2D.Double(-400, 0, 400, 0);
    Line2D line_top = new Line2D.Double(-400, -500, 400, -500);
    Line2D line_end = new Line2D.Double(180, -370, 300, -370);
    Line2D line1 = new Line2D.Double(-60, 0, -60, -220);
    Line2D line2 = new Line2D.Double(60, 0, 60, -100);
    Line2D line3 = new Line2D.Double(-60, -220, 180, -220);
    Line2D line4 = new Line2D.Double(60, -100, 300, -100);
    Line2D line5 = new Line2D.Double(180, -220, 180, -500);
    Line2D line6 = new Line2D.Double(300, -100, 300, -500);
    
    
	Map()
	{
		
		super("MAP");
	    this.setSize(800,800);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setResizable(false);
	    this.setLocationRelativeTo(null);
	    this.setLayout(null);
	    deg.addKeyListener(this);
	    this.add(deg);
	    this.add(label);
	    this.add(enter);
	    this.add(err);
	    //this.add(start);
	    deg.setBounds(0, 100, 100, 20);
	    label.setBounds(0,40,240,80);
	    enter.setBounds(110, 100, 60, 25);
	    err.setBounds(0,120,250,60);
	    start.setBounds(180, 100, 60, 25);
	    enter.addActionListener(this);
	    start.addActionListener(this);
	    label.setFont(new Font("標楷體", Font.BOLD, 26));
	    err.setFont(new Font("標楷體", Font.ITALIC, 17));
	    this.setVisible(true);
	    
	    
	    
	    
	    
	}

    public void update(Graphics g) { 
    	
        this.paint(g);  // 單純呼叫paint() 
    } 
    public void paint(Graphics g) { 
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g.translate(400, 770);
        g2.draw(line_top);
        g2.draw(line_bot);
        g2.draw(line1);
        g2.draw(line2);
        g2.draw(line3);
        g2.draw(line4);
        g2.draw(line5);
        g2.draw(line6);
        all[i][0] = x;
        all[i][1] = y;
        g2.setColor(Color.CYAN);
        for(int j=0;j<i;j++)
        {
        	g2.draw(new Ellipse2D.Double(10*(all[j][0]-3), -10*(all[j][1]+3), 60,60 ));
        }
        g2.setColor(Color.RED);
        g2.draw(line_end);
        g2.draw(new Ellipse2D.Double(10*(x-3), -10*(y+3), 60,60 ));
        g2.setColor(Color.BLUE);
        g2.draw(new Line2D.Double(x*10, -y*10, 10*x_d, -10*y_d));
        g2.draw(new Line2D.Double(x*10, -y*10, 10*x_l, -10*y_l));
        g2.draw(new Line2D.Double(x*10, -y*10, 10*x_r, -10*y_r));
        g.fillOval(237, -373, 6, 6);//END
        // 繪圖動作 
        i++;
    } 
    public void keyTyped(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) 
    {
    	int key1 = e.getKeyCode();
    	switch (key1) {
    	 case KeyEvent.VK_ENTER:
    		 enter.doClick();
    	
    	}
    }
    public void keyReleased(KeyEvent e) {
        
    }
	@Override
	public void actionPerformed(ActionEvent c) {
		// TODO Auto-generated method stub
		if(c.getSource()==enter)
		{
			degree = Double.parseDouble(deg.getText());
			
			if(degree>40||degree<-40)
			{
				err.setText("旋轉角度必須介於-40°~40° ");
			}
			else
			{
				cal();
				System.out.println(d1+" "+d3+" "+d2);
				this.repaint();
			}
		}
		else if(c.getSource()==start)
		{
			
			
			
		}
	}
	public double dis(double x1,double y1,double x2,double y2)
	{
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
	public void cal()
	{
		fi = fi - Math.toDegrees(Math.asin(2*Math.sin(Math.toRadians(degree))/3));
		x = x + Math.cos(Math.toRadians(degree+fi)) + Math.sin(Math.toRadians(degree))*Math.sin(Math.toRadians(fi));
		x = new BigDecimal(x).setScale(7, BigDecimal.ROUND_HALF_UP).doubleValue();
		y = y + Math.sin(Math.toRadians(degree+fi)) - Math.sin(Math.toRadians(degree))*Math.cos(Math.toRadians(fi));
		y = new BigDecimal(y).setScale(7, BigDecimal.ROUND_HALF_UP).doubleValue();
		x_c = x + 6*Math.cos(Math.toRadians(fi));
		y_c = y + 6*Math.sin(Math.toRadians(fi));
		if(fi==90)
		{
			if(x<=18&&x>-6)
			{
				x_d = x;
				y_d = 22;
			}
			else if(x>18&&x<30)
			{
				x_d = x;
				y_d = 30;
			}
			d1 = 22 - y;
		}
		else if(fi > 90)
		{
			
			double temp = Math.tan(Math.toRadians(fi)) * (-10 - x) + y;
			double min = 1000; 
			Line2D check = new Line2D.Double(10*x, -y*10, -100, -temp*10);
			boolean result1 = line1.intersectsLine(check);
			boolean result3 = line3.intersectsLine(check);
			boolean result5 = line5.intersectsLine(check);
			boolean resultt = line_top.intersectsLine(check);
			//System.out.println(x+" "+y+" "+temp);
			if(result1 == true)
			{
				temp = Math.tan(Math.toRadians(fi)) * (-6 - x) + y;
				if(dis(x,y,-6,temp) < min){
					min = dis(x,y,-6,temp);
					x_d =-6;
					y_d = temp;
				}
			}
			
			if(result3 == true)
			{
				temp = (22-y) / Math.tan(Math.toRadians(fi)) +x;
				if(dis(x,y,temp,22) < min){
					min = dis(x,y,temp,22);
					x_d =temp;
					y_d = 22;
				}
			}
			
			if(result5 == true)
			{
				temp = Math.tan(Math.toRadians(fi)) * (18 - x) + y;
				if(dis(x,y,18,temp) < min){
					min = dis(x,y,18,temp);
					x_d = 18;
					y_d = temp;
				}
			}
			if(resultt == true)
			{
				temp = (37-y) / Math.tan(Math.toRadians(fi)) + x;
				if(dis(x,y,temp,37) < min){
					min = dis(x,y,temp,37);
					x_d =temp;
					y_d = 37;
				}
			}
			d1 = min;
		}
		else
		{
			double temp = Math.tan(Math.toRadians(fi)) * (35 - x) + y;
			double min=1000; 
			Line2D check = new Line2D.Double(10*x, -y*10, 350, -temp*10);
			boolean result2 = line2.intersectsLine(check);
			boolean result3 = line3.intersectsLine(check);
			boolean result4 = line4.intersectsLine(check);
			boolean result6 = line6.intersectsLine(check);
			boolean resultt = line_top.intersectsLine(check);
			if(result2 == true)
			{
				temp = Math.tan(Math.toRadians(fi)) * (6 - x) + y;
				if(dis(x,y,6,temp) < min){
					min = dis(x,y,6,temp);
					x_d = 6;
					y_d = temp;
				}
			}
			if(result3 == true)
			{
				temp = (22-y) / Math.tan(Math.toRadians(fi)) + x;
				if(dis(x,y,temp,22) < min){
					min = dis(x,y,temp,22);
					x_d =temp;
					y_d = 22;
				}
			}
			if(result4 == true)
			{
				temp = (10-y) / Math.tan(Math.toRadians(fi)) + x;
				if(dis(x,y,temp,10) < min){
					min = dis(x,y,temp,10);
					x_d =temp;
					y_d = 10;
				}
			}
			if(result6 == true)
			{
				temp = Math.tan(Math.toRadians(fi)) * (30 - x) + y;
				if(dis(x,y,30,temp) < min){
					min = dis(x,y,30,temp);
					x_d = 30;
					y_d = temp;
				}
			}
			if(resultt == true)
			{
				temp = (37-y) / Math.tan(Math.toRadians(fi)) + x;
				if(dis(x,y,temp,37) < min){
					min = dis(x,y,temp,37);
					x_d =temp;
					y_d = 37;
				}
			}
			d1 = min;
			
			
		}
		if(fi==45)
		{
			
		}
		else if(fi>45)
		{
			double temp = Math.tan(Math.toRadians(fi+45)) * (-10 - x) + y;
			double min = 1000; 
			Line2D check = new Line2D.Double(10*x, -y*10, -100, -temp*10);
			boolean result1 = line1.intersectsLine(check);
			boolean result3 = line3.intersectsLine(check);
			boolean result5 = line5.intersectsLine(check);
			//System.out.println(x+" "+y+" "+temp);
			if(result1 == true)
			{
				temp = Math.tan(Math.toRadians(fi+45)) * (-6 - x) + y;
				if(dis(x,y,-6,temp) < min){
					min = dis(x,y,-6,temp);
					x_l =-6;
					y_l = temp;
				}
			}
			
			if(result3 == true)
			{
				temp = (22-y) / Math.tan(Math.toRadians(fi+45)) +x;
				if(dis(x,y,temp,22) < min){
					min = dis(x,y,temp,22);
					x_l =temp;
					y_l = 22;
				}
			}
			
			if(result5 == true)
			{
				temp = Math.tan(Math.toRadians(fi+45)) * (18 - x) + y;
				if(dis(x,y,18,temp) < min){
					min = dis(x,y,18,temp);
					x_l = 18;
					y_l = temp;
				}
			}
			d2 = min;
		}
		else
		{
			double temp = Math.tan(Math.toRadians(fi+45)) * (35 - x) + y;
			double min=1000; 
			Line2D check = new Line2D.Double(10*x, -y*10, 350, -temp*10);
			boolean result2 = line2.intersectsLine(check);
			boolean result3 = line3.intersectsLine(check);
			boolean result4 = line4.intersectsLine(check);
			boolean result6 = line6.intersectsLine(check);
			boolean resultt = line_top.intersectsLine(check);
			if(result2 == true)
			{
				temp = Math.tan(Math.toRadians(fi+45)) * (6 - x) + y;
				if(dis(x,y,6,temp) < min){
					min = dis(x,y,6,temp);
					x_l = 6;
					y_l = temp;
				}
			}
			if(result3 == true)
			{
				temp = (22-y) / Math.tan(Math.toRadians(fi+45)) + x;
				if(dis(x,y,temp,22) < min){
					min = dis(x,y,temp,22);
					x_l =temp;
					y_l = 22;
				}
			}
			if(result4 == true)
			{
				temp = (10-y) / Math.tan(Math.toRadians(fi+45)) + x;
				if(dis(x,y,temp,10) < min){
					min = dis(x,y,temp,10);
					x_l =temp;
					y_l = 10;
				}
			}
			if(result6 == true)
			{
				temp = Math.tan(Math.toRadians(fi+45)) * (30 - x) + y;
				if(dis(x,y,30,temp) < min){
					min = dis(x,y,30,temp);
					x_l = 30;
					y_l = temp;
				}
			}
			if(resultt == true)
			{
				temp = (37-y) / Math.tan(Math.toRadians(fi+45)) + x;
				if(dis(x,y,temp,37) < min){
					min = dis(x,y,temp,37);
					x_l =temp;
					y_l = 37;
				}
			}
			d2 = min;
		}
		if(fi == 135)
		{
			
		}
		else if(fi >135)
		{
			double temp = Math.tan(Math.toRadians(fi-45)) * (-10 - x) + y;
			double min = 1000; 
			Line2D check = new Line2D.Double(10*x, -y*10, -100, -temp*10);
			boolean result1 = line1.intersectsLine(check);
			boolean result3 = line3.intersectsLine(check);
			boolean result5 = line5.intersectsLine(check);
			//System.out.println(x+" "+y+" "+temp);
			if(result1 == true)
			{
				temp = Math.tan(Math.toRadians(fi-45)) * (-6 - x) + y;
				if(dis(x,y,-6,temp) < min){
					min = dis(x,y,-6,temp);
					x_r =-6;
					y_r = temp;
				}
			}
			
			if(result3 == true)
			{
				temp = (22-y) / Math.tan(Math.toRadians(fi-45)) +x;
				if(dis(x,y,temp,22) < min){
					min = dis(x,y,temp,22);
					x_r =temp;
					y_r = 22;
				}
			}
			
			if(result5 == true)
			{
				temp = Math.tan(Math.toRadians(fi-45)) * (18 - x) + y;
				if(dis(x,y,18,temp) < min){
					min = dis(x,y,18,temp);
					x_r = 18;
					y_r = temp;
				}
			}
			d3 = min;
		}
		else
		{
			double temp = Math.tan(Math.toRadians(fi-45)) * (35 - x) + y;
			double min=1000; 
			Line2D check = new Line2D.Double(10*x, -y*10, 350, -temp*10);
			boolean result2 = line2.intersectsLine(check);
			boolean result3 = line3.intersectsLine(check);
			boolean result4 = line4.intersectsLine(check);
			boolean result6 = line6.intersectsLine(check);
			boolean resultt = line_top.intersectsLine(check);
			if(result2 == true)
			{
				temp = Math.tan(Math.toRadians(fi-45)) * (6 - x) + y;
				if(dis(x,y,6,temp) < min){
					min = dis(x,y,6,temp);
					x_r = 6;
					y_r = temp;
				}
			}
			if(result3 == true)
			{
				temp = (22-y) / Math.tan(Math.toRadians(fi-45)) + x;
				if(dis(x,y,temp,22) < min){
					min = dis(x,y,temp,22);
					x_r =temp;
					y_r = 22;
				}
			}
			if(result4 == true)
			{
				temp = (10-y) / Math.tan(Math.toRadians(fi-45)) + x;
				if(dis(x,y,temp,10) < min){
					min = dis(x,y,temp,10);
					x_r =temp;
					y_r = 10;
				}
			}
			if(result6 == true)
			{
				temp = Math.tan(Math.toRadians(fi-45)) * (30 - x) + y;
				if(dis(x,y,30,temp) < min){
					min = dis(x,y,30,temp);
					x_r = 30;
					y_r = temp;
				}
			}
			if(resultt == true)
			{
				temp = (37-y) / Math.tan(Math.toRadians(fi-45)) + x;
				if(dis(x,y,temp,37) < min){
					min = dis(x,y,temp,37);
					x_r =temp;
					y_r = 37;
				}
			}
			d3 = min;
		}
		
		
	}

	

}
