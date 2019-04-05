package app.command;

import java.awt.Color;

import app.mvc.PaintModel;
import geometry.Line;
import geometry.Shape;

public class CmdEditLine implements Command {

	private PaintModel model;
	private Line l;
	private Line newLine;
	
	public CmdEditLine(PaintModel model , Line l, int newX1,int newY1,int newX2,int newY2,Color newColor)
	{
		this.model=model;
		this.l=l;
		this.newLine=l.clone();
		newLine.getStart().setX(newX1);
		newLine.getStart().setY(newY1);
		newLine.getEnd().setX(newX2);
		newLine.getEnd().setY(newY2);
		newLine.setOutlineColor(newColor);
	}
	
	@Override
	public void execute() {
		this.model.add(newLine);
		this.model.remove(l);
	}

	@Override
	public void unexecute() {
		this.model.remove(newLine);
		this.model.add(l);
	}

	@Override
	public Shape getShape() {
		return this.l;
	}
	
	public String toString()
	{
		return "EDIT:"+l.toString()+",to:"+ newLine.toString();
	}

}
