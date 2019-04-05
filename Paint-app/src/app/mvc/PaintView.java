package app.mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

@SuppressWarnings("serial")
public class PaintView extends JPanel {

	private PaintModel model;
	
	public void setModel(PaintModel model) {
		this.model=model;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getAll().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
		}
		repaint();
	}
}
