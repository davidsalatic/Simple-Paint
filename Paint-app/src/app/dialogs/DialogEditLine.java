package app.dialogs;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import app.mvc.PaintFrame;
import geometry.Line;

import java.awt.Insets;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class DialogEditLine extends JDialog{
	private JTextField tfX1;
	private JTextField tfY1;
	private JTextField tfX2;
	private JTextField tfY2;
	private Color chosenColor;
	private int newX1;
	private int newY1;
	private int newX2;
	private int newY2;
	
	public DialogEditLine(Line l)
	{
		setSize(213, 148);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Edit line");
		setResizable(false);
		Image img = new ImageIcon(PaintFrame.class.getResource("/line.png")).getImage();
        this.setIconImage(img);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblStartPoint = new JLabel("Start point: (");
		GridBagConstraints gbc_lblStartPoint = new GridBagConstraints();
		gbc_lblStartPoint.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartPoint.anchor = GridBagConstraints.EAST;
		gbc_lblStartPoint.gridx = 0;
		gbc_lblStartPoint.gridy = 0;
		mainPanel.add(lblStartPoint, gbc_lblStartPoint);
		
		tfX1 = new JTextField();
		tfX1.setText(String.valueOf(l.getStart().getX()));
		tfX1.setMinimumSize(new Dimension(35, 20));
		tfX1.setPreferredSize(new Dimension(20, 20));
		tfX1.setMaximumSize(new Dimension(40, 2147483647));
		GridBagConstraints gbc_tfX1 = new GridBagConstraints();
		gbc_tfX1.insets = new Insets(0, 0, 5, 5);
		gbc_tfX1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfX1.gridx = 1;
		gbc_tfX1.gridy = 0;
		mainPanel.add(tfX1, gbc_tfX1);
		tfX1.setColumns(10);
		
		JLabel lblComma = new JLabel(",");
		GridBagConstraints gbc_lblComma = new GridBagConstraints();
		gbc_lblComma.insets = new Insets(0, 0, 5, 5);
		gbc_lblComma.anchor = GridBagConstraints.EAST;
		gbc_lblComma.gridx = 2;
		gbc_lblComma.gridy = 0;
		mainPanel.add(lblComma, gbc_lblComma);
		
		tfY1 = new JTextField();
		tfY1.setText(String.valueOf(l.getStart().getY()));
		tfY1.setMinimumSize(new Dimension(35, 20));
		tfY1.setPreferredSize(new Dimension(20, 20));
		GridBagConstraints gbc_tfY1 = new GridBagConstraints();
		gbc_tfY1.insets = new Insets(0, 0, 5, 5);
		gbc_tfY1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfY1.gridx = 3;
		gbc_tfY1.gridy = 0;
		mainPanel.add(tfY1, gbc_tfY1);
		tfY1.setColumns(10);
		
		JLabel lblParenth = new JLabel(")");
		GridBagConstraints gbc_lblParenth = new GridBagConstraints();
		gbc_lblParenth.insets = new Insets(0, 0, 5, 0);
		gbc_lblParenth.gridx = 4;
		gbc_lblParenth.gridy = 0;
		mainPanel.add(lblParenth, gbc_lblParenth);
		
		JLabel lblEndPoint = new JLabel("End point: (");
		GridBagConstraints gbc_lblEndPoint = new GridBagConstraints();
		gbc_lblEndPoint.anchor = GridBagConstraints.EAST;
		gbc_lblEndPoint.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndPoint.gridx = 0;
		gbc_lblEndPoint.gridy = 1;
		mainPanel.add(lblEndPoint, gbc_lblEndPoint);
		
		tfX2 = new JTextField();
		tfX2.setText(String.valueOf(l.getEnd().getX()));
		tfX2.setPreferredSize(new Dimension(20, 20));
		tfX2.setMinimumSize(new Dimension(35, 20));
		tfX2.setMaximumSize(new Dimension(40, 2147483647));
		tfX2.setColumns(10);
		GridBagConstraints gbc_tfX2 = new GridBagConstraints();
		gbc_tfX2.insets = new Insets(0, 0, 5, 5);
		gbc_tfX2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfX2.gridx = 1;
		gbc_tfX2.gridy = 1;
		mainPanel.add(tfX2, gbc_tfX2);
		
		JLabel lblComma2 = new JLabel(",");
		GridBagConstraints gbc_lblComma2 = new GridBagConstraints();
		gbc_lblComma2.insets = new Insets(0, 0, 5, 5);
		gbc_lblComma2.anchor = GridBagConstraints.EAST;
		gbc_lblComma2.gridx = 2;
		gbc_lblComma2.gridy = 1;
		mainPanel.add(lblComma2, gbc_lblComma2);
		
		tfY2 = new JTextField();
		tfY2.setText(String.valueOf(l.getEnd().getY()));
		tfY2.setPreferredSize(new Dimension(20, 20));
		tfY2.setMinimumSize(new Dimension(35, 20));
		tfY2.setColumns(10);
		GridBagConstraints gbc_tfY2 = new GridBagConstraints();
		gbc_tfY2.insets = new Insets(0, 0, 5, 5);
		gbc_tfY2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfY2.gridx = 3;
		gbc_tfY2.gridy = 1;
		mainPanel.add(tfY2, gbc_tfY2);
		
		JLabel lblParenth2 = new JLabel(")");
		GridBagConstraints gbc_lblParenth2 = new GridBagConstraints();
		gbc_lblParenth2.insets = new Insets(0, 0, 5, 0);
		gbc_lblParenth2.gridx = 4;
		gbc_lblParenth2.gridy = 1;
		mainPanel.add(lblParenth2, gbc_lblParenth2);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					newX1 = Integer.parseInt(tfX1.getText());
					newY1 = Integer.parseInt(tfY1.getText());
					newX2 = Integer.parseInt(tfX2.getText());
					newY2 = Integer.parseInt(tfY2.getText());
					if(chosenColor==null)
					{
						chosenColor=l.getOutlineColor();
					}
					if(newX1<1||newX1>890 || newY1<1 || newY1 >460 || newX2<1||newX2>890 || newY2<1 || newY2 >460)
					{
						throw new IllegalArgumentException();
					}
					dispose();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(DialogEditLine.this, "Only numbers 1-890 for X and 1-460 for Y allowed!","Error",JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		
		JButton btnOutlineColor = new JButton("");
		btnOutlineColor.setBackground(l.getOutlineColor());
		btnOutlineColor.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				JColorChooser jccOutline = new JColorChooser();
				chosenColor=jccOutline.showDialog(null, "Choose outline color", l.getOutlineColor());

				if(chosenColor==null)
					chosenColor=l.getOutlineColor();
				btnOutlineColor.setBackground(chosenColor);
			}
		});
		btnOutlineColor.setMinimumSize(new Dimension(32, 32));
		btnOutlineColor.setPreferredSize(new Dimension(32, 32));
		GridBagConstraints gbc_btnOutlineColor = new GridBagConstraints();
		gbc_btnOutlineColor.gridwidth = 3;
		gbc_btnOutlineColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnOutlineColor.gridx = 1;
		gbc_btnOutlineColor.gridy = 2;
		mainPanel.add(btnOutlineColor, gbc_btnOutlineColor);
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.gridwidth = 3;
		gbc_btnAccept.insets = new Insets(0, 0, 0, 5);
		gbc_btnAccept.gridx = 1;
		gbc_btnAccept.gridy = 3;
		mainPanel.add(btnAccept, gbc_btnAccept);
	}

	public Color getChosenColor() {
		return chosenColor;
	}

	public int getNewX1() {
		return newX1;
	}

	public int getNewY1() {
		return newY1;
	}

	public int getNewX2() {
		return newX2;
	}

	public int getNewY2() {
		return newY2;
	}
}
