package app.command;

import java.awt.Color;

import app.mvc.PaintModel;
import geometry.Point;
import geometry.Shape;
import geometry.Square;

public class CmdEditSquare implements Command {

	private PaintModel model;
	private Square s;
	private Square newSquare;
	
	public CmdEditSquare(PaintModel model , Square s,int newX,int newY,int newSide,Color newOutlineColor,Color newInsideColor)
	{
		this.model=model;
		this.s=s;
		this.newSquare=(Square) s.clone();
		this.newSquare.setUpperLeft(new Point(newX,newY));
		this.newSquare.setSide(newSide);
		this.newSquare.setOutlineColor(newOutlineColor);
		this.newSquare.setInsideColor(newInsideColor);
	}

	@Override
	public void execute() {
		this.model.add(newSquare);
		this.model.remove(s);
	}

	@Override
	public void unexecute() {
		this.model.remove(newSquare);
		this.model.add(s);
	}

	@Override
	public Shape getShape() {
		return this.s;
	}

	public String toString()
	{
		return "EDIT:"+s.toString()+",to:"+ newSquare.toString();
	}

}
