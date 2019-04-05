package geometry;

import java.awt.Color;
import java.awt.Graphics;
import app.Utilities;


@SuppressWarnings("serial")
public class Line extends Shape implements Movable {
	private Point start, end;

	public Line() {

	}

	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}
	
	public Line(Point start, Point end, Color outlineColor) {
		this.start = start;
		this.end = end;
		super.setOutlineColor(outlineColor);
	}
	
	public double length() {
		return this.start.distance(this.end);
	}

	public Point lineCenter() {
		return new Point((this.start.getX() + this.end.getX()) / 2,
				(this.start.getY() + this.end.getY()) / 2);
	}

	@Override
	public void moveTo(int x, int y) {
		this.start.moveTo(x, y);
		this.end.moveBy(x - this.start.getX(), y - this.start.getY());
	}

	@Override
	public void moveBy(int x, int y) {
		this.start.moveBy(x, y);
		this.end.moveBy(x, y);
	}
	
	public int compareTo(Object second) {
		if (second instanceof Line) {
			Line secondLine = (Line) second;
			return (int) (this.length() - secondLine.length());
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean contains(int x, int y) {
		Point click = new Point(x, y);
		double distanceStart= this.start.distance(click);
		double distanceEnd = this.end.distance(click);

		if (distanceStart+ distanceEnd<= this.length() + 0.05) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void selected(Graphics g) {
		start.selected(g);
		this.lineCenter().selected(g);
		end.selected(g);
	}
	
	public boolean equals(Object second) {
		if (second instanceof Line) {
			Line secondLine= (Line) second;
			if (this.start.equals(secondLine.getStart()) && this.end.equals(secondLine.getEnd()))
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
		g.setColor(this.getOutlineColor());
		g.drawLine(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());

		if (this.isSelected()) {
			this.selected(g);
		}
	}
	
	@Override
	public String toString() {
		return "Line,start"+Utilities.getCoordinatesText(start)+",end"+Utilities.getCoordinatesText(end)+",color:"+Utilities.getRGBText(getOutlineColor());
	}
	
	@Override
	public Line clone() {
		Line lineClone= new Line();
		lineClone.setStart(
				new Point(this.getStart().getX(), this.getStart().getY()));
		lineClone.setEnd(new Point(this.getEnd().getX(), this.getEnd().getY()));
		lineClone.setOutlineColor(this.getOutlineColor());
		return lineClone;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}
}
