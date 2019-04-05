package geometry;

import java.awt.Color;
import java.awt.Graphics;

import app.Utilities;
import hexagon.Hexagon;

@SuppressWarnings("serial")
public class HexagonAdapter extends AreaShape implements Movable {
	private Hexagon h;
	
	public HexagonAdapter(Hexagon h)
	{
		this.h=h;
	}

	@Override
	public double girth() {
		return 6*this.h.getR();
	}
	
	@Override
	public double area() {
		double surface = (3*Math.sqrt(3)*this.h.getR()*this.h.getR())/2;
		return surface;
	}
	
	@Override
	public void moveTo(int x, int y) {
		this.h.setX(x);
		this.h.setY(y);
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.h.setX(this.h.getX()+byX);
		this.h.setY(this.h.getY()+byY);
	}
	
	
	@Override
	public boolean contains(int x, int y) {
		return h.doesContain(x, y);
	}
	
	@Override
	public void selected(Graphics g) {
		//TODO
	}
	
	public boolean equals(Object second) {
		if (second instanceof HexagonAdapter) {
			HexagonAdapter secondHexagon= (HexagonAdapter) second;
			if (this.getX() == secondHexagon.getX() && this.getY()==secondHexagon.getY() && this.getR() == secondHexagon.getR())
				return true;
			else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		this.h.paint(g);
	}
	
	@Override
	public void fill(Graphics g) {
		this.h.paint(g);
	}
	
	@Override
	public String toString() {
		return "Hexagon,center"+Utilities.getCoordinatesText(h.getX(), h.getY()) +",side("+this.h.getR()+"),colorout:"+Utilities.getRGBText(getOutlineColor())+",colorin:"+Utilities.getRGBText(getInsideColor());
	}
	
	@Override
	public Shape clone() {
		Hexagon hex = new Hexagon(this.getX(), this.getY(), this.getR());
		HexagonAdapter hexagonClone = new HexagonAdapter(hex);
		hexagonClone.setInsideColor(this.getInsideColor());
		hexagonClone.setOutlineColor(this.getOutlineColor());
		return hexagonClone;
	}
	
	public int getX()
	{
		return h.getX();
	}
	
	public int getY()
	{
		return h.getY();
	}
	
	public int getR()
	{
		return h.getR();
	}
	
	public void setOutlineColor(Color outlineColor)
	{
		h.setBorderColor(outlineColor);
	}
	
	public void setInsideColor(Color insideColor)
	{
		h.setAreaColor(insideColor);
	}
	
	public Color getOutlineColor()
	{
		return h.getBorderColor();
	}
	
	public Color getInsideColor()
	{
		return h.getAreaColor();
	}
	
	public void setSelected(boolean b)
	{
		this.h.setSelected(b);
		this.selected=b;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof HexagonAdapter) {
			HexagonAdapter forwarded = (HexagonAdapter) o;
			return this.getR() - forwarded.getR();
		} else {
			return 0;
		}
	}
}
