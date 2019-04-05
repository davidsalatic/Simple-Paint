package app.command;

import java.awt.Color;

import app.mvc.PaintModel;
import geometry.Point;
import geometry.Shape;

public class CmdEditPoint implements Command {

	private PaintModel model;
	private Point p;
	private Point newPoint;
	
	public CmdEditPoint(PaintModel model , Point p,int newX,int newY,Color newColor)
	{
		this.model=model;
		this.p=p;
		this.newPoint=p.clone();
		newPoint.setX(newX);
		newPoint.setY(newY);
		newPoint.setOutlineColor(newColor);
	}
	
	@Override
	public void execute() {
		this.model.add(newPoint);
		this.model.remove(p);
	}

	@Override
	public void unexecute() {
		this.model.remove(newPoint);
		this.model.add(p);
	}

	@Override
	public Shape getShape() {
		return this.p;
	}
	
	public String toString()
	{
		return "EDIT:"+p.toString()+",to:"+ newPoint.toString();
	}

}
