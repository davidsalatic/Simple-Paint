package app.dialogs;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import app.mvc.PaintFrame;
import geometry.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class DialogEditPoint extends JDialog{
	private JTextField tfX;
	private JTextField tfY;
	private Color chosenColor;
	private int newX;
	private int newY;
	
	public DialogEditPoint(Point p)
	{
		setSize(170, 153);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Edit point");
		setResizable(false);
		Image img = new ImageIcon(PaintFrame.class.getResource("/point.png")).getImage();
        this.setIconImage(img);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblX = new JLabel("X:");
		GridBagConstraints gbc_lblX = new GridBagConstraints();
		gbc_lblX.anchor = GridBagConstraints.EAST;
		gbc_lblX.insets = new Insets(0, 0, 5, 5);
		gbc_lblX.gridx = 0;
		gbc_lblX.gridy = 0;
		mainPanel.add(lblX, gbc_lblX);
		
		tfX = new JTextField();
		tfX.setText(String.valueOf(p.getX()));
		GridBagConstraints gbc_tfX = new GridBagConstraints();
		gbc_tfX.insets = new Insets(0, 0, 5, 0);
		gbc_tfX.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfX.gridx = 1;
		gbc_tfX.gridy = 0;
		mainPanel.add(tfX, gbc_tfX);
		tfX.setColumns(10);
		
		JLabel lblY = new JLabel("Y:");
		GridBagConstraints gbc_lblY = new GridBagConstraints();
		gbc_lblY.anchor = GridBagConstraints.EAST;
		gbc_lblY.insets = new Insets(0, 0, 5, 5);
		gbc_lblY.gridx = 0;
		gbc_lblY.gridy = 1;
		mainPanel.add(lblY, gbc_lblY);
		
		tfY = new JTextField();
		tfY.setText(String.valueOf(p.getY()));
		GridBagConstraints gbc_tfY = new GridBagConstraints();
		gbc_tfY.insets = new Insets(0, 0, 5, 0);
		gbc_tfY.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfY.gridx = 1;
		gbc_tfY.gridy = 1;
		mainPanel.add(tfY, gbc_tfY);
		tfY.setColumns(10);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					newX = Integer.parseInt(tfX.getText());
					newY = Integer.parseInt(tfY.getText());
					if(chosenColor==null)
					{
						chosenColor=p.getOutlineColor();
					}
					if(newX<1||newX>890 || newY<1 || newY >460)
					{
						throw new IllegalArgumentException();
					}
					dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(DialogEditPoint.this, "Only numbers 1-890 for X and 1-460 for Y allowed!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnOutlineColor = new JButton("");
		btnOutlineColor.setBackground(p.getOutlineColor());
		btnOutlineColor.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				JColorChooser jccOutline = new JColorChooser();
				chosenColor=jccOutline.showDialog(null, "Choose outline color", p.getOutlineColor());

				if(chosenColor==null)
					chosenColor=p.getOutlineColor();
				btnOutlineColor.setBackground(chosenColor);
			}
		});
		btnOutlineColor.setMinimumSize(new Dimension(32, 32));
		btnOutlineColor.setPreferredSize(new Dimension(32, 32));
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 0);
		gbc_btnOutlineColor.gridx = 1;
		gbc_btnOutlineColor.gridy = 2;
		mainPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.gridx = 1;
		gbc_btnAccept.gridy = 3;
		mainPanel.add(btnAccept, gbc_btnAccept);
	}

	public Color getChosenColor() {
		return chosenColor;
	}
	public int getNewX() {
		return newX;
	}
	public int getNewY() {
		return newY;
	}
}
