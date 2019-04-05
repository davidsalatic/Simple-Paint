package app.dialogs;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.mvc.PaintFrame;

@SuppressWarnings("serial")
public class DialogAddCircle extends JDialog{
	
	private JTextField tfRadius;
	private int radius;
	
	public DialogAddCircle()
	{
		setSize(188, 120);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Draw - Circle");
		setResizable(false);
		Image img = new ImageIcon(PaintFrame.class.getResource("/circle.png")).getImage();
        this.setIconImage(img);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblRadius = new JLabel("Radius:");
		GridBagConstraints gbc_lblRadius = new GridBagConstraints();
		gbc_lblRadius.anchor = GridBagConstraints.EAST;
		gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
		gbc_lblRadius.gridx = 0;
		gbc_lblRadius.gridy = 0;
		mainPanel.add(lblRadius, gbc_lblRadius);
		
		tfRadius = new JTextField();
		GridBagConstraints gbc_tfRadius = new GridBagConstraints();
		gbc_tfRadius.insets = new Insets(0, 0, 5, 0);
		gbc_tfRadius.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfRadius.gridx = 1;
		gbc_tfRadius.gridy = 0;
		mainPanel.add(tfRadius, gbc_tfRadius);
		tfRadius.setColumns(10);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					radius=Integer.parseInt(tfRadius.getText());
					if(radius<1||radius>300)
					{
						throw new IllegalArgumentException();
					}
					dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(DialogAddCircle.this, "Only numbers 1-300 allowed!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.gridx = 1;
		gbc_btnAccept.gridy = 2;
		mainPanel.add(btnAccept, gbc_btnAccept);
	}

	public int getRadius() {
		return radius;
	}
}
