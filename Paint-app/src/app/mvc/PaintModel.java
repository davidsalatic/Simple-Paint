package app.mvc;

import java.util.ArrayList;

import geometry.Shape;

public class PaintModel {

	private ArrayList<Shape> shapes= new ArrayList<Shape>();
	private int selectedCount;
	
	public ArrayList<Shape> getAll() {
		return shapes;
	}

	public Shape get(int i) {
		return shapes.get(i);
	}

	public void add(Shape s) {
		shapes.add(s);
	}

	public void remove(Shape s) {
		shapes.remove(s);
	}
	
	public void setAll(ArrayList<Shape> shapes)
	{
		this.shapes=shapes;
	}
	
	public int getSelectedCount()
	{
		int count=0;
		for(int i=0;i<shapes.size();i++)
		{
			if(shapes.get(i).isSelected()==true)
			{
				count++;
			}
		}
		this.selectedCount=count;
		return this.selectedCount;
	}
}
