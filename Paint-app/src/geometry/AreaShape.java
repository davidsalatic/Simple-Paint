package geometry;

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public abstract class AreaShape extends Shape{

	private Color insideColor;
	
	public Color insideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}
	
	public Color getInsideColor() {
		return insideColor;
	}

	public abstract double area();
	public abstract double girth();
	public abstract void fill(Graphics g);
	
}
