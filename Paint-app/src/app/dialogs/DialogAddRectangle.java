package app.dialogs;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import app.mvc.PaintFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class DialogAddRectangle extends JDialog {
	
	private JTextField tfHeight;
	private JTextField tfWidth;	
	private int h;
	private int w;
	
	public DialogAddRectangle()
	{
		setSize(188, 120);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Draw - Rectangle");
		setResizable(false);
		Image img = new ImageIcon(PaintFrame.class.getResource("/rectangle.png")).getImage();
        this.setIconImage(img);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]{0, 0, 0};
		gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblHeight = new JLabel("Height:");
		GridBagConstraints gbc_lblHeight = new GridBagConstraints();
		gbc_lblHeight.anchor = GridBagConstraints.EAST;
		gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeight.gridx = 0;
		gbc_lblHeight.gridy = 0;
		mainPanel.add(lblHeight, gbc_lblHeight);
		
		tfHeight = new JTextField();
		GridBagConstraints gbc_tfHeight = new GridBagConstraints();
		gbc_tfHeight.insets = new Insets(0, 0, 5, 0);
		gbc_tfHeight.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfHeight.gridx = 1;
		gbc_tfHeight.gridy = 0;
		mainPanel.add(tfHeight, gbc_tfHeight);
		tfHeight.setColumns(10);
		
		JLabel lblWidth = new JLabel("Width:");
		GridBagConstraints gbc_lblWidth = new GridBagConstraints();
		gbc_lblWidth.anchor = GridBagConstraints.EAST;
		gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
		gbc_lblWidth.gridx = 0;
		gbc_lblWidth.gridy = 1;
		mainPanel.add(lblWidth, gbc_lblWidth);
		
		tfWidth = new JTextField();
		GridBagConstraints gbc_tfWidth = new GridBagConstraints();
		gbc_tfWidth.insets = new Insets(0, 0, 5, 0);
		gbc_tfWidth.anchor = GridBagConstraints.NORTH;
		gbc_tfWidth.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfWidth.gridx = 1;
		gbc_tfWidth.gridy = 1;
		mainPanel.add(tfWidth, gbc_tfWidth);
		tfWidth.setColumns(10);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					h=Integer.parseInt(tfHeight.getText());
					w=Integer.parseInt(tfWidth.getText());
					if(h<1||h>300||w<1||w>300)
					{
						throw new IllegalArgumentException();
					}
					dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(DialogAddRectangle.this, "Only numbers 1-300 allowed!","Error",JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.gridx = 1;
		gbc_btnAccept.gridy = 2;
		mainPanel.add(btnAccept, gbc_btnAccept);
	}

	public int getH() {
		return h;
	}

	public int getW() {
		return w;
	}

}
