package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Shape implements Comparable, Serializable {

	protected boolean selected;
	private Color outlineColor;

	public Shape() {

	}
	
	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color color) {
		this.outlineColor= color;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public abstract void draw(Graphics g);
	public abstract void selected(Graphics g);
	public abstract boolean contains(int x, int y);
	public abstract Shape clone();
}
