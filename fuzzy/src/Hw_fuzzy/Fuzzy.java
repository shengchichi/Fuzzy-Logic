package Hw_fuzzy;

import java.math.BigDecimal;
/*
 * rule:
 * 		1.if |d2-d3|<=c --> theta = 0
 * 		2.if d1 is large and d2-d3>0 	--> theta = -15
 * 		3.if d1 is large and d2-d3<0 	--> theta = 15
 * 		4.if d1 is small and d2-d3>0	--> theta = -40
 * 		5.if d1 is small and d2-d3<0 	--> theta = 40
 * 
 */
public class Fuzzy {
	double y1,y2;
	double relation[][];
	double alpha1;
	double alpha2;
	double alpha3;
	double alpha4;
	double theta = 0;
	String s;
	Fuzzy(double d1, double d2, double d3)
	{
		d1_mu(d1);
		double temp = Math.abs(d2-d3);
		d2_d3_mu(temp);
		//四捨五入到第3位
		y1 = new BigDecimal(y1).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		y2 = new BigDecimal(y2).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
		//rule1
		alpha1 = min(y1,y2);
		if(d2>d3)
			theta = -40*alpha1;
		else
			theta = 40*alpha1;
	}
	public void d1_mu(double x)
	{
		if(x>23)
			y1 = 1;
		else if(x>=10&&x<=23)
			y1 = (x-10)/13;
		else if(x<10&&x>=8)
			y1 = 0;
		else if(x<8)
			y1 = 1;
			
	}
	public void d2_d3_mu(double x)
	{
		if(x>14){
			y2 = 1;
		}
		else if(x>=3&&x<=14){
			y2 = (x-3)/11;
		}
		else if(x<3)
			y2 = 0;
	}
	public double min(double a ,double b){
		if(a<b)
			return a;
		else
			return b;
	}
	public double max(double a ,double b){
		if(a>b)
			return a;
		else
			return b;
	}
	
}
