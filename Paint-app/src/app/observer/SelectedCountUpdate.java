package app.observer;

import app.mvc.PaintFrame;

public class SelectedCountUpdate implements Observer {
	
	private int selectedCount;
	private PaintFrame frame;
	
	public SelectedCountUpdate(PaintFrame frame) {
		this.frame=frame;
	}
	
	@Override
	public void update(int selectedCount) {
		this.selectedCount=selectedCount;
		toggleButtons();
	}

	private void toggleButtons() {
		if(this.selectedCount>0)
		{
			this.frame.getBtnDelete().setEnabled(true);
			this.frame.getBtnEdit().setEnabled(true);
			
			this.frame.getBtnBringToBack().setEnabled(true);
			this.frame.getBtnToBack().setEnabled(true);
			this.frame.getBtnBringToFront().setEnabled(true);
			this.frame.getBtnToFront().setEnabled(true);
			
			if(this.selectedCount>1)
			{
				this.frame.getBtnEdit().setEnabled(false);
				this.frame.getBtnBringToBack().setEnabled(false);
				this.frame.getBtnToBack().setEnabled(false);
				this.frame.getBtnBringToFront().setEnabled(false);
				this.frame.getBtnToFront().setEnabled(false);
			}
		}
		else//ako nista nije selektovano
		{
			this.frame.getBtnDelete().setEnabled(false);
			this.frame.getBtnEdit().setEnabled(false);
			this.frame.getBtnBringToBack().setEnabled(false);
			this.frame.getBtnToBack().setEnabled(false);
			this.frame.getBtnBringToFront().setEnabled(false);
			this.frame.getBtnToFront().setEnabled(false);
		}
	}

}
