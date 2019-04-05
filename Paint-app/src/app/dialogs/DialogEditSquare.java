package app.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.mvc.PaintFrame;
import geometry.Square;

@SuppressWarnings("serial")
public class DialogEditSquare extends JDialog {

	private JTextField tfY;
	private JTextField tfX;
	private JTextField tfSide;
	private Color chosenOutlineColor;
	private Color chosenInsideColor;
	private int newX;
	private int newY;
	private int newSide;
	
	public DialogEditSquare(Square s)
	{
		setSize(203, 159);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Edit square");
		setResizable(false);
		Image img = new ImageIcon(PaintFrame.class.getResource("/square.png")).getImage();
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
		tfX.setPreferredSize(new Dimension(20, 20));
		tfX.setText(String.valueOf(s.getUpperLeft().getX()));
		tfX.setMinimumSize(new Dimension(20, 20));
		tfX.setMaximumSize(new Dimension(20, 2));
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
		tfY.setMinimumSize(new Dimension(20, 20));
		tfY.setPreferredSize(new Dimension(20, 20));
		tfY.setText(String.valueOf(s.getUpperLeft().getY()));
		GridBagConstraints gbc_tfY = new GridBagConstraints();
		gbc_tfY.insets = new Insets(0, 0, 5, 5);
		gbc_tfY.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfY.gridx = 3;
		gbc_tfY.gridy = 0;
		mainPanel.add(tfY, gbc_tfY);
		tfY.setColumns(10);
		
		JLabel lblParenth = new JLabel(")");
		GridBagConstraints gbc_lblParenth = new GridBagConstraints();
		gbc_lblParenth.insets = new Insets(0, 0, 5, 5);
		gbc_lblParenth.gridx = 4;
		gbc_lblParenth.gridy = 0;
		mainPanel.add(lblParenth, gbc_lblParenth);
		
		JLabel lblHeight = new JLabel("Side:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 1;
		mainPanel.add(lblHeight, gbc_lblHeight);
		
		tfSide = new JTextField();
		tfSide.setText(String.valueOf(s.getSide()));
		tfSide.setMaximumSize(new Dimension(6, 2147483647));
		GridBagConstraints gbc_tfSide = new GridBagConstraints();
		gbc_tfSide.gridwidth = 3;
		gbc_tfSide.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSide.insets = new Insets(0, 0, 5, 5);
		gbc_tfSide.gridx = 1;
		gbc_tfSide.gridy = 1;
		mainPanel.add(tfSide, gbc_tfSide);
		tfSide.setColumns(10);
		
		JButton btnOutlineColor = new JButton("");
		btnOutlineColor.setBackground(s.getOutlineColor());
		btnOutlineColor.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				JColorChooser jccOutline = new JColorChooser();
				chosenOutlineColor=jccOutline.showDialog(null, "Choose outline color", s.getOutlineColor());

				if(chosenOutlineColor==null)
					chosenOutlineColor=s.getOutlineColor();
				btnOutlineColor.setBackground(chosenOutlineColor);
			}
		});
		btnOutlineColor.setMinimumSize(new Dimension(32, 32));
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.gridwidth = 2;
		gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOutlineColor.gridx = 0;
		gbc_btnOutlineColor.gridy = 2;
		mainPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		
		JButton btnInsideColor = new JButton("");
		btnInsideColor.setBackground(s.getInsideColor());
		btnInsideColor.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				JColorChooser jccInside = new JColorChooser();
				chosenInsideColor=jccInside.showDialog(null, "Choose inside color", s.getInsideColor());

				if(chosenInsideColor==null)
					chosenInsideColor=s.getInsideColor();
				btnInsideColor.setBackground(chosenInsideColor);
			}
		});
		btnInsideColor.setMinimumSize(new Dimension(32, 32));
		GridBagConstraints gbc_btnInsideColor = new GridBagConstraints();
		gbc_btnInsideColor.gridwidth = 4;
		gbc_btnInsideColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInsideColor.gridx = 2;
		gbc_btnInsideColor.gridy = 2;
		mainPanel.add(btnInsideColor, gbc_btnInsideColor);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newX = Integer.parseInt(tfX.getText());
					newY = Integer.parseInt(tfY.getText());
					newSide=Integer.parseInt(tfSide.getText());
					if(chosenOutlineColor==null)
					{
						chosenOutlineColor=s.getOutlineColor();
					}
					if(chosenInsideColor==null)
					{
						chosenInsideColor=s.getInsideColor();
					}
					if(newX<1||newX>890 || newY<1 || newY >460 || newSide<1 || newSide>300)
					{
						throw new IllegalArgumentException();
					}
					dispose();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(DialogEditSquare.this, "Only numbers 1-890 for X, 1-460 for Y and 1-300 for Side allowed!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.insets = new Insets(0, 0, 5, 0);
		gbc_btnAccept.gridwidth = 6;
		gbc_btnAccept.gridx = 0;
		gbc_btnAccept.gridy = 3;
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

	public int getNewSide() {
		return newSide;
	}

}
