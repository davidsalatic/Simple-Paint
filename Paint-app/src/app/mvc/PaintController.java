package app.mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import app.command.CmdAddShape;
import app.command.CmdEditCircle;
import app.command.CmdEditHexagon;
import app.command.CmdEditLine;
import app.command.CmdEditPoint;
import app.command.CmdEditRectangle;
import app.command.CmdEditSquare;
import app.command.CmdRemoveShapes;
import app.command.Command;
import app.dialogs.DialogAddCircle;
import app.dialogs.DialogAddHexagon;
import app.dialogs.DialogAddRectangle;
import app.dialogs.DialogAddSquare;
import app.dialogs.DialogEditCircle;
import app.dialogs.DialogEditHexagon;
import app.dialogs.DialogEditLine;
import app.dialogs.DialogEditPoint;
import app.dialogs.DialogEditRectangle;
import app.dialogs.DialogEditSquare;
import app.dialogs.DialogLoadCommands;
import app.observer.SelectedCount;
import app.observer.SelectedCountUpdate;
import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;
import hexagon.Hexagon;

public class PaintController {
	private PaintModel model;
	private PaintFrame frame;
	private int currentCommandIndex=-1; 
	private ArrayList<Command> commands = new ArrayList<Command>();
	private SelectedCount selectedCount;
	
	public PaintController(PaintModel model, PaintFrame frame) {
		this.model=model;
		this.frame=frame;
		this.selectedCount = new SelectedCount(); //observer
		SelectedCountUpdate selectedCountUpdate = new SelectedCountUpdate(frame);
		this.selectedCount.addObserver(selectedCountUpdate);
	}

	public void select(MouseEvent arg0) {
		boolean foundShape=false;
		Collections.reverse(model.getAll());
		
		for (Shape s: model.getAll()) {
			if(s.contains(arg0.getX(), arg0.getY())) {
				s.setSelected(true);
				this.selectedCount.setSelectedCount(model.getSelectedCount()); 
				
				if(model.getAll().size()<=1)
				{	
					frame.getBtnToBack().setEnabled(false);
					frame.getBtnBringToBack().setEnabled(false);
					frame.getBtnBringToFront().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
				}
				if(model.getAll().indexOf(s)==0)
				{	
					frame.getBtnBringToFront().setEnabled(false);
					frame.getBtnToFront().setEnabled(false);
				}
				foundShape=true;
				break;
			}
		}
		if(foundShape==false)
		{
			deselectAll();
		}
		Collections.reverse(model.getAll());
	}

	public void draw(MouseEvent firstClick, MouseEvent secondClick, Color outlineColor, Color insideColor) {
		if(frame.getSelectedButton()==frame.getBtnPoint())
		{	
			commands.add(new CmdAddShape(model, new Point(firstClick.getX(),firstClick.getY(),outlineColor)));
			executeCurentCommand();
		}
		else if(frame.getSelectedButton()==frame.getBtnLine())
		{
			commands.add(new CmdAddShape(model, new Line(new Point(firstClick.getX(),firstClick.getY()),new Point(secondClick.getX(),secondClick.getY()),outlineColor)));
			executeCurentCommand();
		}
		else if(frame.getSelectedButton()==frame.getBtnRectangle())
		{
			DialogAddRectangle dialogAddRectangle = new DialogAddRectangle();
			dialogAddRectangle.setVisible(true);
			if(dialogAddRectangle.getH()>0 && dialogAddRectangle.getH() <=300 && dialogAddRectangle.getW()>0 && dialogAddRectangle.getW()<=300)
			{
				commands.add(new CmdAddShape(model,new Rectangle(new Point(firstClick.getX(),firstClick.getY()),dialogAddRectangle.getH(),dialogAddRectangle.getW(),outlineColor,insideColor))); 
				executeCurentCommand();
			}
			
		}
		else if(frame.getSelectedButton()==frame.getBtnSquare())
		{
			DialogAddSquare dialogAddSquare = new DialogAddSquare();
			dialogAddSquare.setVisible(true);
			if(dialogAddSquare.getH()>0 && dialogAddSquare.getH()<=300)
			{
				commands.add(new CmdAddShape(model,new Square(new Point(firstClick.getX(),firstClick.getY()),dialogAddSquare.getH(),outlineColor,insideColor)));
				executeCurentCommand();
			}
		}
		else if(frame.getSelectedButton()==frame.getBtnCircle())
		{
			DialogAddCircle dialogAddCircle = new DialogAddCircle();
			dialogAddCircle.setVisible(true);
			if(dialogAddCircle.getRadius()>0 && dialogAddCircle.getRadius()<=600)
			{
				commands.add(new CmdAddShape(model, new Circle(new Point(firstClick.getX(),firstClick.getY()),dialogAddCircle.getRadius(),outlineColor,insideColor)));
				executeCurentCommand();
			}
		}
		else if(frame.getSelectedButton()==frame.getBtnHexagon())
		{
			DialogAddHexagon dialogAddHexagon = new DialogAddHexagon();
			dialogAddHexagon.setVisible(true);
			if(dialogAddHexagon.getSide()!=0)
			{
				Hexagon h = new Hexagon(firstClick.getX(),firstClick.getY(), dialogAddHexagon.getSide());
				HexagonAdapter hexagon = new HexagonAdapter(h);
				hexagon.setOutlineColor(outlineColor);
				hexagon.setInsideColor(insideColor);
				commands.add(new CmdAddShape(model, hexagon));
				executeCurentCommand();
			}
		}
		frame.getView().repaint();
	}
	
	public void edit() {
		for(int i=0;i<this.model.getAll().size();i++)
		{
			if(model.get(i).isSelected()==true)
			{
				if(model.get(i) instanceof Point)
				{
					DialogEditPoint dialogEditPoint = new DialogEditPoint((Point)model.get(i));
					dialogEditPoint.setVisible(true);
					
					if(dialogEditPoint.getNewX()>0 && dialogEditPoint.getNewX()<=890 && dialogEditPoint.getNewY()>0 && dialogEditPoint.getNewY()<=460)
					{
						commands.add(new CmdEditPoint(this.model,(Point)model.get(i),dialogEditPoint.getNewX(),dialogEditPoint.getNewY(), dialogEditPoint.getChosenColor()));
						executeCurentCommand();
						deselectAll();
					} 
				}
				else if(model.get(i) instanceof Line)
				{
					DialogEditLine dialogEditLine = new DialogEditLine((Line)model.get(i));
					dialogEditLine.setVisible(true);
					
					if(dialogEditLine.getNewX1()>0 && dialogEditLine.getNewX1()<=890 && dialogEditLine.getNewY1()>0 && dialogEditLine.getNewY1()<=460 && dialogEditLine.getNewX2()>0 && dialogEditLine.getNewX2()<=890 && dialogEditLine.getNewY2()>0 && dialogEditLine.getNewY2()<=460)
					{
						commands.add(new CmdEditLine(this.model,(Line)model.get(i),dialogEditLine.getNewX1(),dialogEditLine.getNewY1(),dialogEditLine.getNewX2(),dialogEditLine.getNewY2() ,dialogEditLine.getChosenColor()));
						executeCurentCommand();
						deselectAll();
					} 
				}
				else if(model.get(i) instanceof Rectangle)
				{
					DialogEditRectangle dialogEditRectangle = new DialogEditRectangle((Rectangle)model.get(i));
					dialogEditRectangle.setVisible(true);
					
					if(dialogEditRectangle.getNewX()>0 && dialogEditRectangle.getNewX()<=890 && dialogEditRectangle.getNewY()>0 && dialogEditRectangle.getNewY()<=460 && dialogEditRectangle.getNewHeight()>0 && dialogEditRectangle.getNewHeight()<=300 && dialogEditRectangle.getNewWidth()>0 && dialogEditRectangle.getNewWidth()<=300)
					{
						commands.add(new CmdEditRectangle(this.model, (Rectangle)model.get(i), dialogEditRectangle.getNewX(),dialogEditRectangle.getNewY(), dialogEditRectangle.getNewHeight(),dialogEditRectangle.getNewWidth(), dialogEditRectangle.getChosenOutlineColor(),dialogEditRectangle.getChosenInsideColor()));
						executeCurentCommand();
						deselectAll();
					}
				}
				else if(model.get(i) instanceof Square)
				{
					DialogEditSquare dialogEditSquare = new DialogEditSquare((Square)model.get(i));
					dialogEditSquare.setVisible(true);
					
					if(dialogEditSquare.getNewX()>0 && dialogEditSquare.getNewX()<=890 && dialogEditSquare.getNewY()>0 && dialogEditSquare.getNewY()<=460 && dialogEditSquare.getNewSide()>0 && dialogEditSquare.getNewSide()<=300)
					{
						commands.add(new CmdEditSquare(this.model, (Square)model.get(i), dialogEditSquare.getNewX(),dialogEditSquare.getNewY(), dialogEditSquare.getNewSide(),dialogEditSquare.getChosenOutlineColor(),dialogEditSquare.getChosenInsideColor()));
						executeCurentCommand();
						deselectAll();
					}
				}
				else if(model.get(i) instanceof Circle)
				{
					DialogEditCircle dialogEditCircle = new DialogEditCircle((Circle)model.get(i));
					dialogEditCircle.setVisible(true);
					
					if(dialogEditCircle.getNewX()>0 && dialogEditCircle.getNewX()<=890 && dialogEditCircle.getNewY()>0 && dialogEditCircle.getNewY()<=460 && dialogEditCircle.getNewRadius()>0 && dialogEditCircle.getNewRadius()<=300)
					{
						commands.add(new CmdEditCircle(this.model, (Circle)model.get(i), dialogEditCircle.getNewX(),dialogEditCircle.getNewY(), dialogEditCircle.getNewRadius(),dialogEditCircle.getChosenOutlineColor(),dialogEditCircle.getChosenInsideColor()));
						executeCurentCommand();
						deselectAll();
					}
				}
				else if(model.get(i) instanceof HexagonAdapter)
				{
					DialogEditHexagon dialogEditHexagon= new DialogEditHexagon((HexagonAdapter)model.get(i));
					dialogEditHexagon.setVisible(true);
					
					if(dialogEditHexagon.getNewX()>0 && dialogEditHexagon.getNewX()<=890 && dialogEditHexagon.getNewY()>0 && dialogEditHexagon.getNewY()<=460 && dialogEditHexagon.getNewRadius()>0 && dialogEditHexagon.getNewRadius()<=300)
					{
						commands.add(new CmdEditHexagon(this.model, (HexagonAdapter)model.get(i), dialogEditHexagon.getNewX(),dialogEditHexagon.getNewY(), dialogEditHexagon.getNewRadius(),dialogEditHexagon.getChosenOutlineColor(),dialogEditHexagon.getChosenInsideColor()));
						executeCurentCommand();
						deselectAll();
					}
				}
				break;
			}
		}
	}

	public void delete() {
		ArrayList<Shape> inSelection=new ArrayList<Shape>();
		for(int i=0;i<model.getAll().size();i++)
		{
			if(model.get(i).isSelected()==true)
				inSelection.add(model.get(i));
		}
		deselectAll();
		commands.add(new CmdRemoveShapes(model, inSelection));
		executeCurentCommand();
	}
	
	public void deselectAll()
	{
		for (Shape s: model.getAll()) {
			s.setSelected(false);
		}
		this.frame.setFirstClick(null);
		this.selectedCount.setSelectedCount(model.getSelectedCount());//updating observer property
	}
	
	public void executeCurentCommand() {
		currentCommandIndex=commands.size()-1;
		commands.get(currentCommandIndex).execute();
		frame.getBtnUndo().setEnabled(true);
		frame.getBtnRedo().setEnabled(false);
		frame.getDlm().addElement(commands.get(currentCommandIndex).toString());
	}
	
	public void undo() {
		if(frame.getBtnUndo().isEnabled()==true)
		{
			try {
				while(!(model.getAll().contains(commands.get(currentCommandIndex).getShape()))  && !(commands.get(currentCommandIndex) instanceof CmdRemoveShapes) && !(commands.get(currentCommandIndex) instanceof CmdEditPoint) && !(commands.get(currentCommandIndex) instanceof CmdEditLine) && !(commands.get(currentCommandIndex) instanceof CmdEditRectangle) && !(commands.get(currentCommandIndex) instanceof CmdEditSquare) && !(commands.get(currentCommandIndex) instanceof CmdEditCircle) && !(commands.get(currentCommandIndex) instanceof CmdEditHexagon))
				{
					currentCommandIndex--; 
				}
				
				commands.get(currentCommandIndex).unexecute();
				frame.getDlm().addElement("undo:"+commands.get(currentCommandIndex).toString());
				currentCommandIndex--;
				if(currentCommandIndex<0)
					frame.getBtnUndo().setEnabled(false);
			}catch(ArrayIndexOutOfBoundsException outofbounds)
			{
					if(currentCommandIndex<0)
				frame.getBtnUndo().setEnabled(false);
			}
			
		}
		if(model.getAll().isEmpty())
		{
			frame.getBtnUndo().setEnabled(false);
		}
		frame.getBtnRedo().setEnabled(true);
	}

	public void redo() {
		if(frame.getBtnRedo().isEnabled()==true)
		{
			currentCommandIndex++;
			commands.get(currentCommandIndex).execute();
			frame.getDlm().addElement("redo:"+commands.get(currentCommandIndex).toString());//dodavanje komande u log
			if(currentCommandIndex==commands.size()-1) 
			{
				frame.getBtnRedo().setEnabled(false);
			}
			frame.getBtnUndo().setEnabled(true);
		}
	}

	public void bringToBack() {
		if(frame.getBtnBringToBack().isEnabled())
		{
			Shape s = getSelectedShape().clone();
			model.remove(getSelectedShape());
			model.getAll().add(0, s);
			s.setSelected(true);
			
			frame.getBtnToBack().setEnabled(false);
			frame.getBtnBringToBack().setEnabled(false);
			frame.getBtnBringToFront().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
		}
	}

	public void bringToFront() {
		if(frame.getBtnBringToFront().isEnabled())
		{
			Shape s = getSelectedShape().clone();
			this.model.remove(getSelectedShape());
			this.model.add(s);
			s.setSelected(true);
			
			frame.getBtnToBack().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(true);
			frame.getBtnBringToFront().setEnabled(false);
			frame.getBtnToFront().setEnabled(false);
		}
	}

	public void toBack() {
		int i = this.model.getAll().indexOf(getSelectedShape());
		if(i!=0 && frame.getBtnToBack().isEnabled())
		{
			Shape s = getSelectedShape().clone();
			this.model.remove(getSelectedShape());
			this.model.getAll().add(i-1, s);
			s.setSelected(true);
			if(i-1==0)
			{
				frame.getBtnToBack().setEnabled(false);
				frame.getBtnBringToBack().setEnabled(false);
			}
			frame.getBtnBringToFront().setEnabled(true);
			frame.getBtnToFront().setEnabled(true);
		}
	}

	public void toFront() {
		int i = this.model.getAll().indexOf(getSelectedShape());
		if(i!=model.getAll().size()-1 && frame.getBtnToFront().isEnabled())
		{
			Shape s = getSelectedShape().clone();
			this.model.remove(getSelectedShape());
			this.model.getAll().add(i+1, s);
			s.setSelected(true);
			if(i+1==model.getAll().size()-1)
			{
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
			}
			frame.getBtnToBack().setEnabled(true);
			frame.getBtnBringToBack().setEnabled(true);
		}
	}
	
	public Shape getSelectedShape()
	{
		for(Shape s : model.getAll())
		{
			if(s.isSelected()==true)
			{
				return s;
			}
		}
		return null;
	}

	public boolean newFile() {
		if(model.getAll().isEmpty())
		{
			deselectAll();
			frame.defaultFrame();
			this.commands.clear();
			this.currentCommandIndex=-1;
			return true;
		}
		else
		{
			int opt = JOptionPane.showConfirmDialog(null, "This will erase your current drawing. Continue?","Warning",JOptionPane.YES_NO_OPTION);
			if(opt==JOptionPane.YES_OPTION)
			{
				model.getAll().clear();
				deselectAll();
				frame.defaultFrame();
				this.commands.clear();
				this.currentCommandIndex=-1;
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	public void save() throws ClassNotFoundException {
		try {
			JFileChooser saveDrawing = new JFileChooser();
			saveDrawing.setDialogTitle("Choose save destination");
			if(saveDrawing.showSaveDialog(this.frame)==JFileChooser.APPROVE_OPTION)
			{	
				File drawing=new File(saveDrawing.getSelectedFile().getAbsolutePath());
				while(drawing.exists())
				{
					JOptionPane.showMessageDialog(this.frame, "Filename allready exists!", "Error", JOptionPane.ERROR_MESSAGE);
					saveDrawing.showSaveDialog(saveDrawing);
					drawing=new File(saveDrawing.getSelectedFile().getAbsolutePath());
				}
				FileOutputStream fos = new FileOutputStream(drawing);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(model.getAll());
				oos.close();
				
				File log = new File(drawing.getAbsolutePath()+"_log.txt");
				PrintWriter pw = new PrintWriter(log);
				for(int i=0;i<frame.getDlm().size();i++)
				{
					pw.println(frame.getDlm().get(i));
				}
				pw.close();
			}
		}
		 catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error while saving!", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void open() {
		try
		{
			JFileChooser fileChooser = new JFileChooser();
			if (fileChooser.showOpenDialog(this.frame) == JFileChooser.APPROVE_OPTION) {
			  File f = fileChooser.getSelectedFile();
			  if(f.getAbsolutePath().endsWith(".txt"))
			  {
				  boolean openFile = newFile();
				  if(openFile==true)
				  {
					  FileReader fileReader;
					  fileReader = new FileReader(f);
					  BufferedReader bufferedReader = new BufferedReader(fileReader);
					  DialogLoadCommands dialogLoadCommands = new DialogLoadCommands(bufferedReader,commands,PaintController.this);
					  dialogLoadCommands.setVisible(true);
				  }
			  }
			  else //drawing file
			  {
				  boolean openFile = newFile();
				  if(openFile==true)
				  {
					  FileInputStream fis = new FileInputStream(f);
					  ObjectInputStream ois = new ObjectInputStream(fis);
					  model.setAll((ArrayList<Shape>)ois.readObject());
					  ois.close();
				  }
			  }
			}
		}catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Error while opening file!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public int getCurrentCommandIndex() {
		return currentCommandIndex;
	}
	
	public void setCurrentCommandIndex(int currentCommandIndex)
	{
		this.currentCommandIndex=currentCommandIndex;
	}

	public PaintModel getModel() {
		return model;
	}

	public PaintFrame getFrame()
	{
		return frame;
	}
}