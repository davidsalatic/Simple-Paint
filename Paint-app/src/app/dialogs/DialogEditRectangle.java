package app.dialogs;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JTextField;

import app.mvc.PaintFrame;
import geometry.Rectangle;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Dimension;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DialogEditRectangle extends JDialog{
	

	private Color chosenOutlineColor;
	private Color chosenInsideColor;
	private int newX;
	private int newY;
	private int newHeight;
	private int newWidth;
	private JTextField tfY;
	private JTextField tfX;
	private JTextField tfHeight;
	private JTextField tfWidth;
	
	public DialogEditRectangle(Rectangle r)
	{
		setSize(192, 170);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Edit rectangle");
		setResizable(false);
		Image img = new ImageIcon(PaintFrame.class.getResource("/rectangle.png")).getImage();
        this.setIconImage(img);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblUpperLeft = new JLabel("Upper left: (");
		GridBagConstraints gbc_lblUpperLeft = new GridBagConstraints();
		gbc_lblUpperLeft.anchor = GridBagConstraints.EAST;
		gbc_lblUpperLeft.insets = new Insets(0, 0, 5, 5);
		gbc_lblUpperLeft.gridx = 0;
		gbc_lblUpperLeft.gridy = 0;
		mainPanel.add(lblUpperLeft, gbc_lblUpperLeft);
		
		tfX = new JTextField();
		tfX.setText(String.valueOf(r.getUpperLeft().getX()));
		tfX.setMinimumSize(new Dimension(10, 20));
		tfX.setMaximumSize(new Dimension(12, 2147483647));
		GridBagConstraints gbc_tfX = new GridBagConstraints();
		gbc_tfX.insets = new Insets(0, 0, 5, 5);
		gbc_tfX.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfX.gridx = 1;
		gbc_tfX.gridy = 0;
		mainPanel.add(tfX, gbc_tfX);
		tfX.setColumns(10);
		
		JLabel lblComma = new JLabel(",");
		GridBagConstraints gbc_lblComma = new GridBagConstraints();
		gbc_lblComma.insets = new Insets(0, 0, 5, 5);
		gbc_lblComma.gridx = 2;
		gbc_lblComma.gridy = 0;
		mainPanel.add(lblComma, gbc_lblComma);
		
		tfY = new JTextField();
		tfY.setText(String.valueOf(r.getUpperLeft().getY()));
		GridBagConstraints gbc_tfY = new GridBagConstraints();
		gbc_tfY.insets = new Insets(0, 0, 5, 5);
		gbc_tfY.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfY.gridx = 3;
		gbc_tfY.gridy = 0;
		mainPanel.add(tfY, gbc_tfY);
		tfY.setColumns(10);
		
		JLabel lblParenth = new JLabel(")");
		GridBagConstraints gbc_lblParenth = new GridBagConstraints();
		gbc_lblParenth.insets = new Insets(0, 0, 5, 0);
		gbc_lblParenth.gridx = 4;
		gbc_lblParenth.gridy = 0;
		mainPanel.add(lblParenth, gbc_lblParenth);
		
		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 1;
		mainPanel.add(lblHeight, gbc_lblHeight);
		
		tfHeight = new JTextField();
		tfHeight.setText(String.valueOf(r.getHeight()));
		tfHeight.setMaximumSize(new Dimension(6, 2147483647));
		GridBagConstraints gbc_tfHeight = new GridBagConstraints();
		gbc_tfHeight.gridwidth = 2;
		gbc_tfHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfHeight.insets = new Insets(0, 0, 5, 5);
		gbc_tfHeight.gridx = 1;
		gbc_tfHeight.gridy = 1;
		mainPanel.add(tfHeight, gbc_tfHeight);
		tfHeight.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width:");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.EAST;
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 2;
		mainPanel.add(lblWidth, gbc_lblWidth);
		
		tfWidth = new JTextField();
		tfWidth.setText(String.valueOf(r.getSide()));
		GridBagConstraints gbc_tfWidth = new GridBagConstraints();
		gbc_tfWidth.gridwidth = 2;
		gbc_tfWidth.insets = new Insets(0, 0, 5, 5);
		gbc_tfWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfWidth.gridx = 1;
		gbc_tfWidth.gridy = 2;
		mainPanel.add(tfWidth, gbc_tfWidth);
		tfWidth.setColumns(10);
		
		JButton btnOutlineColor = new JButton("");
		btnOutlineColor.setBackground(r.getOutlineColor());
		btnOutlineColor.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser jccOutline = new JColorChooser();
				chosenOutlineColor=jccOutline.showDialog(null, "Choose outline color", r.getOutlineColor());

				if(chosenOutlineColor==null)
					chosenOutlineColor=r.getOutlineColor();
				btnOutlineColor.setBackground(chosenOutlineColor);
			}
		});
		btnOutlineColor.setMinimumSize(new Dimension(32, 32));
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.gridwidth = 2;
		gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOutlineColor.gridx = 0;
		gbc_btnOutlineColor.gridy = 3;
		mainPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		
		JButton btnInsideColor = new JButton("");
		btnInsideColor.setBackground(r.getInsideColor());
		btnInsideColor.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				JColorChooser jccInside = new JColorChooser();
				chosenInsideColor=jccInside.showDialog(null, "Choose inside color", r.getInsideColor());

				if(chosenInsideColor==null)
					chosenInsideColor=r.getInsideColor();
				btnInsideColor.setBackground(chosenInsideColor);
			}
		});
		btnInsideColor.setMinimumSize(new Dimension(32, 32));
		GridBagConstraints gbc_btnInsideColor = new GridBagConstraints();
		gbc_btnInsideColor.gridwidth = 2;
		gbc_btnInsideColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsideColor.gridx = 2;
		gbc_btnInsideColor.gridy = 3;
		mainPanel.add(btnInsideColor, gbc_btnInsideColor);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newX = Integer.parseInt(tfX.getText());
					newY = Integer.parseInt(tfY.getText());
					newHeight=Integer.parseInt(tfHeight.getText());
					newWidth=Integer.parseInt(tfWidth.getText());
					if(chosenOutlineColor==null)
					{
						chosenOutlineColor=r.getOutlineColor();
					}
					if(chosenInsideColor==null)
					{
						chosenInsideColor=r.getInsideColor();
					}
					if(newX<1||newX>890 || newY<1 || newY >460 || newHeight<1 || newHeight>300 || newWidth<1 || newWidth>300)
					{
						throw new IllegalArgumentException();
					}
					dispose();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(DialogEditRectangle.this, "Only numbers 1-890 for X, 1-460 for Y and 1-300 for Height and Width allowed!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.gridwidth = 7;
		gbc_btnAccept.gridx = 0;
		gbc_btnAccept.gridy = 4;
		mainPanel.add(btnAccept, gbc_btnAccept);
	}

	public Color getChosenOutlineColor() {
		return chosenOutlineColor;
	}

	public Color getChosenInsideColor() {
		return chosenInsideColor;
	}

	public int getNewX() {
		return newX;
	}

	public int getNewY() {
		return newY;
	}

	public int getNewHeight() {
		return newHeight;
	}

	public int getNewWidth() {
		return newWidth;
	}
}
