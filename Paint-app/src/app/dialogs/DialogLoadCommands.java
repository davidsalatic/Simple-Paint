package app.dialogs;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import app.Utilities;
import app.command.CmdAddShape;
import app.command.CmdEditCircle;
import app.command.CmdEditHexagon;
import app.command.CmdEditLine;
import app.command.CmdEditPoint;
import app.command.CmdEditRectangle;
import app.command.CmdEditSquare;
import app.command.CmdRemoveShapes;
import app.command.Command;
import app.mvc.PaintController;
import app.mvc.PaintFrame;
import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;
import hexagon.Hexagon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DialogLoadCommands extends JDialog {
	
	private PaintController controller;
	private ArrayList<Command> commands;
	
	public DialogLoadCommands(BufferedReader bufferedReader, ArrayList<Command> commands,PaintController controller)
	{
		setSize(309, 110);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Load commands");
		setResizable(false);
		Image img = new ImageIcon(PaintFrame.class.getResource("/load.png")).getImage();
        this.setIconImage(img);
	
		this.controller=controller;
		this.commands=commands;
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnLoadNext = new JButton("Load next command");
		btnLoadNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String line=bufferedReader.readLine();
					if(line!=null)
					{
						readLine(line,controller);
					}
					else
						dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(DialogLoadCommands.this, "Error reading from file", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		mainPanel.add(btnLoadNext, BorderLayout.NORTH);
		
		JButton btnLoadAll = new JButton("Load all commands");
		btnLoadAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String line;
					while((line=bufferedReader.readLine())!=null)
					{
						readLine(line,controller);
					}
					dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(DialogLoadCommands.this, "Error reading from file", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		mainPanel.add(btnLoadAll, BorderLayout.SOUTH);
		
		DialogLoadCommands.this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); 
		DialogLoadCommands.this.addWindowListener(new java.awt.event.WindowAdapter() {
			
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				try {
					String line;
					while((line=bufferedReader.readLine())!=null)
					{
						readLine(line,controller);
					}
					dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(DialogLoadCommands.this, "Error reading from file", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
	}
	
	private void readLine(String line,PaintController controller)
	{
		String originalLine=line;
		line=line.replace(':', ',');
		String [] commandCheck = line.split(",");
		String valuesLine= line.replaceAll("[^0-9:,.]", ""); 
		for(int i=0;i<valuesLine.length();i++)
		{
			if(valuesLine.charAt(0)==',')
			{
				valuesLine=valuesLine.substring(1);
			}
			else if(valuesLine.charAt(i)==',' && valuesLine.charAt(i-1)==',')
			{
				valuesLine = valuesLine.substring(0, i-1)+valuesLine.substring(i);
			}
		}
		String [] values = valuesLine.split(",");
		
		if(commandCheck[0].equals("create".toUpperCase()))
		{
			create(commandCheck,values);
		}
		else if(commandCheck[0].equals("delete".toUpperCase()))
		{
			deleteSingle(commandCheck,values);
		}
		else if(commandCheck[0].equals("DELETE*"))
		{
			deleteMultiple(commandCheck);
		}
		else if(commandCheck[0].equals("undo".toLowerCase()))
		{
			for(int i=0;i<commands.size();i++)
			{
				if(originalLine.substring(5).equals(commands.get(i).toString()))
				{
					commands.get(i).unexecute();
					controller.getFrame().getDlm().addElement("undo:"+commands.get(i).toString());
					controller.setCurrentCommandIndex(controller.getCurrentCommandIndex()-1);
					if(controller.getCurrentCommandIndex()<0)
						controller.getFrame().getBtnUndo().setEnabled(false);
				}
			}
		}
		else if(commandCheck[0].equals("redo".toLowerCase()))
		{
			for(int i=0;i<commands.size();i++)
			{
				if(originalLine.substring(5).equals(commands.get(i).toString()))
				{
					commands.get(i).execute();
					controller.setCurrentCommandIndex(controller.getCurrentCommandIndex()+1);
					controller.getFrame().getDlm().addElement("redo:"+commands.get(i).toString());
					if(controller.getCurrentCommandIndex()==commands.size()-1) 
					{
						controller.getFrame().getBtnRedo().setEnabled(false);
					}
					controller.getFrame().getBtnUndo().setEnabled(true);
				}
			}
		}
		else if(commandCheck[0].equals("outline color".toUpperCase()))
		{	
			controller.getFrame().setOutlineColor(generateColor(values[0]));
			controller.getFrame().getBtnOutline().setBackground(generateColor(values[0]));
			controller.getFrame().getLblRgboutline().setText(Utilities.getRGBText(generateColor(values[0])));
			controller.getFrame().getDlm().addElement(line);
		}
		else if(commandCheck[0].equals("inside color".toUpperCase()))
		{	
			controller.getFrame().setInsideColor(generateColor(values[0]));
			controller.getFrame().getBtnInside().setBackground(generateColor(values[0]));
			controller.getFrame().getLblRgbinside().setText(Utilities.getRGBText(generateColor(values[0])));
			controller.getFrame().getDlm().addElement(line);
		}
		else if(commandCheck[0].equals("edit".toUpperCase()))
		{
			edit(commandCheck,values);
		}
	}

	private void create(String[] commandCheck, String[] values) {
		if(commandCheck[1].equals("Point"))
		{
			Point p = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]),generateColor(values[2]));
			this.commands.add(new CmdAddShape(controller.getModel(), p));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Line"))
		{
			Point start = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			Point end = new Point(Integer.parseInt(values[2]),Integer.parseInt(values[3]));
			this.commands.add(new CmdAddShape(controller.getModel(), new Line(start,end,generateColor(values[4]))));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Rectangle"))
		{
			Point upperLeft = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			this.commands.add(new CmdAddShape(controller.getModel(), new Rectangle(upperLeft, Integer.parseInt(values[2]), Integer.parseInt(values[3]), generateColor(values[4]), generateColor(values[5]))));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Square"))
		{
			Point upperLeft = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			this.commands.add(new CmdAddShape(controller.getModel(), new Square(upperLeft, Integer.parseInt(values[2]), generateColor(values[3]), generateColor(values[4]))));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Circle"))
		{
			Point center = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			this.commands.add(new CmdAddShape(controller.getModel(), new Circle(center,Integer.parseInt(values[2]),generateColor(values[3]), generateColor(values[4]))));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Hexagon"))
		{
			Hexagon hex=new Hexagon(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
			hex.setBorderColor(generateColor(values[3]));
			hex.setAreaColor(generateColor(values[4]));
			this.commands.add(new CmdAddShape(controller.getModel(), new HexagonAdapter(hex)));
			controller.executeCurentCommand();
		}
		
	}

	private void edit(String[] commandCheck, String[] values) {
		if(commandCheck[1].equals("Point"))
		{
			Point oldPoint=new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]),generateColor(values[2]));
			commands.add(new CmdEditPoint(controller.getModel(), oldPoint, Integer.parseInt(values[4]), Integer.parseInt(values[5]),generateColor(values[6])));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Line"))
		{
			Point start=new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			Point end = new Point(Integer.parseInt(values[2]),Integer.parseInt(values[3]));
			Line oldLine =  new Line(start,end,generateColor(values[4]));
			commands.add(new CmdEditLine(controller.getModel(),oldLine,Integer.parseInt(values[6]),Integer.parseInt(values[7]),Integer.parseInt(values[8]),Integer.parseInt(values[9]),generateColor(values[10])));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Rectangle"))
		{
			Point upperLeft=new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			Rectangle oldRectangle = new Rectangle(upperLeft,Integer.parseInt(values[2]),Integer.parseInt(values[3]),generateColor(values[4]),generateColor(values[5]));
			commands.add(new CmdEditRectangle(controller.getModel(),oldRectangle,Integer.parseInt(values[7]),Integer.parseInt(values[8]),Integer.parseInt(values[9]),Integer.parseInt(values[10]),generateColor(values[11]),generateColor(values[12])));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Square"))
		{
			Point upperLeft=new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			Square oldSquare = new Square(upperLeft,Integer.parseInt(values[2]),generateColor(values[3]),generateColor(values[4]));
			commands.add(new CmdEditSquare(controller.getModel(),oldSquare,Integer.parseInt(values[6]),Integer.parseInt(values[7]),Integer.parseInt(values[8]),generateColor(values[9]),generateColor(values[10])));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Circle"))
		{
			Point center = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			Circle oldCircle = new Circle(center,Integer.parseInt(values[2]),generateColor(values[3]),generateColor(values[4]));
			commands.add(new CmdEditCircle(controller.getModel(),oldCircle,Integer.parseInt(values[6]),Integer.parseInt(values[7]),Integer.parseInt(values[8]),generateColor(values[9]),generateColor(values[10])));
			controller.executeCurentCommand();
		}
		else if(commandCheck[1].equals("Hexagon"))
		{
			HexagonAdapter oldHexagon= new HexagonAdapter(new Hexagon(Integer.parseInt(values[0]),Integer.parseInt(values[1]),Integer.parseInt(values[2])));
			oldHexagon.setOutlineColor(generateColor(values[3]));
			oldHexagon.setInsideColor(generateColor(values[4]));
			commands.add(new CmdEditHexagon(controller.getModel(),oldHexagon,Integer.parseInt(values[6]),Integer.parseInt(values[7]),Integer.parseInt(values[8]),generateColor(values[9]),generateColor(values[10])));
			controller.executeCurentCommand();
		}
	}

	private void deleteSingle(String[] commandCheck, String[] values) {
		ArrayList<Shape> shapes = new ArrayList<Shape>(); 
		if(commandCheck[1].equals("Point"))
		{
			shapes.add(new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]),generateColor(values[2])));
		}
		else if(commandCheck[1].equals("Line"))
		{
			Point start = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			Point end = new Point(Integer.parseInt(values[2]),Integer.parseInt(values[3]));
			shapes.add(new Line(start,end,generateColor(values[4])));
		}
		else if(commandCheck[1].equals("Rectangle"))
		{
			Point upperLeft = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			shapes.add(new Rectangle(upperLeft,Integer.parseInt(values[2]),Integer.parseInt(values[3]),generateColor(values[4]),generateColor(values[5])));
		}
		else if(commandCheck[1].equals("Square"))
		{
			Point upperLeft = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			shapes.add(new Square(upperLeft, Integer.parseInt(values[2]), generateColor(values[3]), generateColor(values[4])));
		}
		else if(commandCheck[1].equals("Circle"))
		{
			Point center = new Point(Integer.parseInt(values[0]),Integer.parseInt(values[1]));
			shapes.add(new Circle(center,Integer.parseInt(values[2]),generateColor(values[3]), generateColor(values[4])));
		}
		else if(commandCheck[1].equals("Hexagon"))
		{
			Hexagon hex=new Hexagon(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]));
			hex.setBorderColor(generateColor(values[3]));
			hex.setAreaColor(generateColor(values[4]));
			shapes.add(new HexagonAdapter(hex));
		}
		this.commands.add(new CmdRemoveShapes(controller.getModel(), shapes));
		this.controller.executeCurentCommand();
	}

	private void deleteMultiple(String[] commandCheck) {
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for(int i=1;i<commandCheck.length;i++)
		{
			if(commandCheck[i].equals("Point"))
			{
				int x=Integer.parseInt(commandCheck[i+1].substring(1));
				int y=Integer.parseInt(commandCheck[i+2].substring(0, commandCheck[i+2].length()-1));
				shapes.add(new Point(x,y,generateColor(commandCheck[i+4])));
			}
			else if(commandCheck[i].equals("Line"))
			{
				Point start=new Point(Integer.parseInt(commandCheck[i+1].substring(6)),Integer.parseInt(commandCheck[i+2].substring(0, commandCheck[i+2].length()-1)));
				Point end = new Point(Integer.parseInt(commandCheck[i+3].substring(4)),Integer.parseInt(commandCheck[i+4].substring(0, commandCheck[i+4].length()-1)));
				shapes.add(new Line(start,end,generateColor(commandCheck[i+6])));
			}
			else if(commandCheck[i].equals("Rectangle"))
			{
				Point upperLeft = new Point(Integer.parseInt(commandCheck[i+1].substring(11)),Integer.parseInt(commandCheck[i+2].substring(0, commandCheck[i+2].length()-1)));
				shapes.add(new Rectangle(upperLeft,Integer.parseInt(commandCheck[i+3].substring(7, commandCheck[i+3].length()-1)),Integer.parseInt(commandCheck[i+4].substring(6, commandCheck[i+4].length()-1)),generateColor(commandCheck[i+6]),generateColor(commandCheck[i+8])));
			}
			else if(commandCheck[i].equals("Square"))
			{
				Point upperLeft = new Point(Integer.parseInt(commandCheck[i+1].substring(11)),Integer.parseInt(commandCheck[i+2].substring(0, commandCheck[i+2].length()-1)));
				shapes.add(new Square(upperLeft,Integer.parseInt(commandCheck[i+3].substring(5, commandCheck[i+3].length()-1)),generateColor(commandCheck[i+5]),generateColor(commandCheck[i+7])));
			}
			else if(commandCheck[i].equals("Circle"))
			{
				Point center = new Point(Integer.parseInt(commandCheck[i+1].substring(7)),Integer.parseInt(commandCheck[i+2].substring(0, commandCheck[i+2].length()-1)));
				shapes.add(new Circle(center,Integer.parseInt(commandCheck[i+3].substring(7, commandCheck[i+3].length()-1)),generateColor(commandCheck[i+5]),generateColor(commandCheck[i+7])));
			}
			else if(commandCheck[i].equals("Hexagon"))
			{
				Hexagon hex = new Hexagon(Integer.parseInt(commandCheck[i+1].substring(7)),Integer.parseInt(commandCheck[i+2].substring(0, commandCheck[i+2].length()-1)), Integer.parseInt(commandCheck[i+3].substring(5, commandCheck[i+3].length()-1)));
				hex.setBorderColor(generateColor(commandCheck[i+5]));
				hex.setAreaColor(generateColor(commandCheck[i+7]));
				shapes.add(new HexagonAdapter(hex));
			}
		}
		this.commands.add(new CmdRemoveShapes(controller.getModel(), shapes));
		this.controller.executeCurentCommand();
	}

	private Color generateColor(String text)
	{
		String [] rgb = text.split("\\.");
		
		return new Color(Integer.parseInt(rgb[0]),Integer.parseInt(rgb[1]),Integer.parseInt(rgb[2]));
	}
}
