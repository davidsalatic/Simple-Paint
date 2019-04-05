package app.command;

import java.awt.Color;

import app.mvc.PaintModel;
import geometry.HexagonAdapter;
import geometry.Shape;
import hexagon.Hexagon;

public class CmdEditHexagon implements Command{

	private PaintModel model;
	private HexagonAdapter h;
	private HexagonAdapter newHexagon;
	
	public CmdEditHexagon(PaintModel model , HexagonAdapter h,int newX,int newY,int newRadius,Color newOutlineColor,Color newInsideColor)
	{
		this.model=model;
		this.h=h;
		Hexagon hex = new Hexagon(newX, newY, newRadius);
		hex.setBorderColor(newOutlineColor);
		hex.setAreaColor(newInsideColor);
		this.newHexagon=new HexagonAdapter(hex);
	}

	@Override
	public void execute() {
		this.model.add(newHexagon);
		this.model.remove(h);
	}

	@Override
	public void unexecute() {
		this.model.remove(newHexagon);
		this.model.add(h);
	}

	@Override
	public Shape getShape() {
		return this.h;
	}

	public String toString()
	{
		return "EDIT:"+h.toString()+",to:"+ newHexagon.toString();
	}

}
