package app.command;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.mvc.PaintModel;
import geometry.Shape;

public class CmdRemoveShapes implements Command{

	private PaintModel model;
	private ArrayList<Shape> shapes=new ArrayList<Shape>();
	
	public CmdRemoveShapes(PaintModel model, ArrayList<Shape> shapes) {
		this.model = model;
		this.shapes = shapes;
	}
	
	@Override
	public void execute() {
		for(int i=0;i<shapes.size();i++)
		{
			for(int j=0;j<model.getAll().size();j++)
			{
				if(shapes.get(i).equals(model.get(j)))
				{
					model.remove(model.get(j));
				}
			}
		}	
	}

	@Override
	public void unexecute() {
		for(int i=0;i<shapes.size();i++)
		{
			this.model.add(shapes.get(i).clone());
		}
		if(shapes.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "No selected shapes to delete!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public Shape getShape() {
		return shapes.get(0);
	}
	
	public String toString()
	{
		if(shapes.size()==1)
		{
			return "DELETE:"+shapes.get(0).toString();
		}
		else
		{
			String text="DELETE*:";
			for(int i=0;i<shapes.size();i++)
			{
				if(i==shapes.size()-1)
					text=text+shapes.get(i).toString();
				else
					text=text+shapes.get(i).toString()+",";
			}
			return text;
		}
		
	}
}
