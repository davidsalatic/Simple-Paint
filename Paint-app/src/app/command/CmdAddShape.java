package app.command;

import app.mvc.PaintModel;
import geometry.Shape;

public class CmdAddShape implements Command {
	private PaintModel model;
	private Shape shape;
	
	public CmdAddShape(PaintModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
	}
	
	@Override
	public void execute() {
		this.model.add(this.shape.clone());
	}

	@Override
	public void unexecute() {
		for(int i=0;i<this.model.getAll().size();i++)
		{
			if(model.get(i).equals(this.shape))
			{
				this.model.remove(model.get(i));
			}
		}
	}

	@Override
	public Shape getShape() {
		return this.shape;
	}
	
	public String toString()
	{
		return "CREATE:"+this.shape.toString();
	}
}
