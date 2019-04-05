package geometry;

import java.awt.Color;
import java.awt.Graphics;

import app.Utilities;

@SuppressWarnings("serial")
public class Square extends AreaShape implements Movable {

	protected Point upperLeft;
	private int side;

	public Square() {

	}

	public Square(Point upperLeft, int side) {
		this.upperLeft = upperLeft;
		this.side = side;
	}
	
	public Square(Point upperLeft, int side, Color outlineColor, Color insideColor) {
		this.upperLeft = upperLeft;
		this.side= side;
		super.setOutlineColor(outlineColor);
		super.setInsideColor(insideColor);
	}

	@Override
	public double girth() {
		return 4 * this.side;
	}
	
	@Override
	public double area() {
		return this.side* this.side;
	}
	
	public Line diagonal() {
		Point upperRight= new Point(this.upperLeft.getX() + this.side, upperLeft.getY());
		Point lowerLeft = new Point(upperLeft.getX(), this.upperLeft.getY() + this.side);

		Line diagonal = new Line(upperRight, lowerLeft);

		return diagonal;
	}
	
	@Override
	public void moveTo(int x, int y) {
		this.upperLeft.moveTo(x, y);
	}

	@Override
	public void moveBy(int x, int y) {
		this.upperLeft.moveBy(x, y);
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof Square) {
			Square forwarded = (Square) o;
			return this.side - forwarded.side;
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		if (this.upperLeft.getX() <= x && x <= this.upperLeft.getX() + this.side&& this.upperLeft.getY() <= y
				&& y <= this.upperLeft.getY() + this.side) {
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
		Point lowerRight = new Point(upperLeft.getX() + side, upperLeft.getY() + side);
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
		if (second instanceof Square) {
			Square secondSquare= (Square) second;
			if (this.upperLeft.equals(secondSquare.getUpperLeft()) && this.getSide()==secondSquare.getSide())
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
		g.drawRect(this.upperLeft.getX(), this.upperLeft.getY(), this.side, this.side);

		if (this.isSelected()) {
			this.selected(g);
		}
	}
	
	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInsideColor());
		g.fillRect(upperLeft.getX(), upperLeft.getY(), this.side, this.side);
	}
	
	@Override
	public String toString() {
		return "Square,startpoint"+Utilities.getCoordinatesText(upperLeft)+",side("+this.getSide()+"),colorout:"+Utilities.getRGBText(getOutlineColor())+",colorin:"+Utilities.getRGBText(getInsideColor());
	}
	
	@Override
	public Shape clone() {
		Square squareClone = new Square();
		squareClone.setUpperLeft(new Point(this.getUpperLeft().getX(),this.getUpperLeft().getY()));
		squareClone.setSide(this.getSide());
		squareClone.setOutlineColor(this.getOutlineColor());
		squareClone.setInsideColor(this.getInsideColor());
		return squareClone;
	}
	
	public Point getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
}
