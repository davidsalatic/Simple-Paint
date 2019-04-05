package app.command;

import java.awt.Color;

import app.mvc.PaintModel;
import geometry.Circle;
import geometry.Point;
import geometry.Shape;

public class CmdEditCircle implements Command{

	private PaintModel model;
	private Circle c;
	private Circle newCircle;
	
	public CmdEditCircle(PaintModel model , Circle c,int newX,int newY,int newRadius,Color newOutlineColor,Color newInsideColor)
	{
		this.model=model;
		this.c=c;
		this.newCircle=c.clone();
		this.newCircle.setCenter(new Point(newX,newY));
		this.newCircle.setRadius(newRadius);
		this.newCircle.setOutlineColor(newOutlineColor);
		this.newCircle.setInsideColor(newInsideColor);
	}

	@Override
	public void execute() {
		this.model.add(newCircle);
		this.model.remove(c);
	}

	@Override
	public void unexecute() {
		this.model.remove(newCircle);
		this.model.add(c);
	}

	@Override
	public Shape getShape() {
		return this.c;
	}

	public String toString()
	{
		return "EDIT:"+c.toString()+",to:"+ newCircle.toString();
	}
}
