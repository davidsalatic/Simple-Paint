package app.command;

import java.awt.Color;

import app.mvc.PaintModel;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class CmdEditRectangle implements Command {
	
	private PaintModel model;
	private Rectangle r;
	private Rectangle newRectangle;
	
	public CmdEditRectangle(PaintModel model , Rectangle r,int newX,int newY,int newHeight, int newWidth,Color newOutlineColor,Color newInsideColor)
	{
		this.model=model;
		this.r=r;
		this.newRectangle = r.clone();
		this.newRectangle.setUpperLeft(new Point(newX,newY));
		this.newRectangle.setHeight(newHeight);
		this.newRectangle.setSide(newWidth);
		this.newRectangle.setOutlineColor(newOutlineColor);
		this.newRectangle.setInsideColor(newInsideColor);
	}

	@Override
	public void execute() {
		this.model.add(newRectangle);
		this.model.remove(r);
	}

	@Override
	public void unexecute() {
		this.model.remove(newRectangle);
		this.model.add(r);
	}

	@Override
	public Shape getShape() {
		return this.r;
	}

	public String toString()
	{
		return "EDIT:"+r.toString()+",to:"+ newRectangle.toString();
	}
	
}
