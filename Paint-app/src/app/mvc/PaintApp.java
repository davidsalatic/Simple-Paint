package app.mvc;

import javax.swing.JFrame;

public class PaintApp {
	public static void main(String []args)
	{
		PaintModel model = new PaintModel();
		PaintFrame frame = new PaintFrame();
		frame.getView().setModel(model);
	
		PaintController controller = new PaintController(model, frame);
		frame.setController(controller);
		
		frame.setSize(900, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Paint");
		frame.setResizable(false);
	}
}
