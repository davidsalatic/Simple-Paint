package geometry;

import java.awt.Color;
import java.awt.Graphics;

import app.Utilities;

@SuppressWarnings("serial")
public class Rectangle extends Square {

	private int height;

	public Rectangle() {

	}

	public Rectangle(Point upperLeft, int height, int width) {
		super(upperLeft, width);
		this.height = height;
	}
	
	public Rectangle(Point upperLeft, int height, int width, Color outlineColor, Color insideColor) {
		this(upperLeft, height, width);
		this.setOutlineColor(outlineColor);
		this.setInsideColor(insideColor);
	}
	
	public double girth() {
		return 2 * height + 2 * super.getSide();
	}

	public double area() {
		return height * super.getSide();
	}
	
	@Override
	public Line diagonal() {
		Point upperRight = new Point(super.upperLeft.getX() + super.getSide(), super.upperLeft.getY());
		Point lowerLeft = new Point(super.upperLeft.getX(), super.upperLeft.getY() + this.height);

		Line diagonal = new Line(upperRight, lowerLeft);

		return diagonal;
	}
	
	@Override
	public boolean contains(int x, int y) {
		if (super.upperLeft.getX() <= x && x <= super.upperLeft.getX() + super.getSide() && super.upperLeft.getY() <= y
				&& y <= super.upperLeft.getY() + height) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void selected(Graphics g) {
		upperLeft.selected(g);
		this.diagonal().getStart().selected(g);
		this.diagonal().getEnd().selected(g);
		Point lowerRight = new Point(upperLeft.getX() + super.getSide(), upperLeft.getY() + height);
		lowerRight.selected(g);
		Line l1 = new Line(upperLeft, this.diagonal().getStart());
		l1.lineCenter().selected(g);
		Line l2 = new Line(this.diagonal().getEnd(), lowerRight);
		l2.lineCenter().selected(g);
		Line l3 = new Line(upperLeft, this.diagonal().getEnd());
		l3.lineCenter().selected(g);
		Line l4 = new Line(this.diagonal().getStart(), lowerRight);
		l4.lineCenter().selected(g);
	}
	
	public boolean equals(Object second) {
		if (second instanceof Rectangle) {
			Rectangle secondRectangle= (Rectangle) second;
			if (this.upperLeft.equals(secondRectangle.getUpperLeft()) && this.height==secondRectangle.getHeight() && this.getSide()==secondRectangle.getSide())
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
		fill(g);
		g.setColor(this.getOutlineColor());
		g.drawRect(upperLeft.getX(), upperLeft.getY(), super.getSide(), this.height);

		if (this.isSelected()) {
			this.selected(g);
		}
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInsideColor());
		g.fillRect(upperLeft.getX(), upperLeft.getY(), super.getSide(), height);
	}
	
	@Override
	public String toString() {
		return "Rectangle,startpoint"+Utilities.getCoordinatesText(upperLeft)+",height("+this.getHeight()+"),width("+this.getSide()+"),colorout:"+Utilities.getRGBText(getOutlineColor())+",colorin:"+Utilities.getRGBText(getInsideColor());
	}
	
	public Rectangle clone() {
		Rectangle rectangleClone = new Rectangle();
		rectangleClone.setUpperLeft(new Point(this.getUpperLeft().getX(),this.getUpperLeft().getY()));
		rectangleClone.setHeight(this.getHeight());
		rectangleClone.setSide(this.getSide());
		rectangleClone.setOutlineColor(this.getOutlineColor());
		rectangleClone.setInsideColor(this.getInsideColor());
		return rectangleClone;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
