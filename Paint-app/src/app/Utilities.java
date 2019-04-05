package app;

import java.awt.Color;

import geometry.Point;

public class Utilities {
	
	public static String getRGBText(Color c)
	{
		String red="";
		if(c.getRed()<10)
			red="00"+c.getRed();
		else if(c.getRed()<100)
			red="0"+c.getRed();
		else
			red=String.valueOf(c.getRed());
		
		String green="";
		if(c.getGreen()<10)
			green="00"+c.getGreen();
		else if(c.getGreen()<100)
			green="0"+c.getGreen();
		else
			green=String.valueOf(c.getGreen());
		
		String blue="";
		if(c.getBlue()<10)
			blue="00"+c.getBlue();
		else if(c.getBlue()<100)
			blue="0"+c.getBlue();
		else
			blue=String.valueOf(c.getBlue());
		
		return red+"."+green+"."+blue;
	}
	
	public static String getCoordinatesText(Point p)
	{
		return "("+p.getX()+","+p.getY()+")";
	}
	
	public static String getCoordinatesText(int x,int y)
	{
		return "("+x+","+y+")";
	}
}
