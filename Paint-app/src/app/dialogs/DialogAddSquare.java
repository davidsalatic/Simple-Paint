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
public class DialogAddSquare extends JDialog{

	private JTextField tfSide;
	private int h;
	
	public DialogAddSquare()
	{
		setSize(188, 120);
		setLocationRelativeTo(null);
		setModal(true);
		setTitle("Draw - Square");
		setResizable(false);
		Image img = new ImageIcon(PaintFrame.class.getResource("/square.png")).getImage();
        this.setIconImage(img);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.rowHeights = new int[] {0, 0, 30, 0};
		gbl_mainPanel.columnWidths = new int[] {0, 0, 0};
		gbl_mainPanel.columnWeights = new double[]{0.0, 1.0};
		gbl_mainPanel.rowWeights = new double[]{0.0, 0.0};
		mainPanel.setLayout(gbl_mainPanel);
		
		JLabel lblSide = new JLabel("Side:");
		GridBagConstraints gbc_lblSide = new GridBagConstraints();
		gbc_lblSide.anchor = GridBagConstraints.EAST;
		gbc_lblSide.insets = new Insets(0, 0, 5, 5);
		gbc_lblSide.gridx = 0;
		gbc_lblSide.gridy = 0;
		mainPanel.add(lblSide, gbc_lblSide);
		
		tfSide = new JTextField();
		GridBagConstraints gbc_tfSide = new GridBagConstraints();
		gbc_tfSide.insets = new Insets(0, 0, 5, 0);
		gbc_tfSide.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfSide.gridx = 1;
		gbc_tfSide.gridy = 0;
		mainPanel.add(tfSide, gbc_tfSide);
		tfSide.setColumns(10);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					h=Integer.parseInt(tfSide.getText());
					if(h<1||h>300)
					{
						throw new IllegalArgumentException();
					}
					dispose();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(DialogAddSquare.this, "Only numbers 1-300 allowed!","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		GridBagConstraints gbc_btnAccept = new GridBagConstraints();
		gbc_btnAccept.insets = new Insets(0, 0, 5, 0);
		gbc_btnAccept.gridx = 1;
		gbc_btnAccept.gridy = 1;
		mainPanel.add(btnAccept, gbc_btnAccept);
	}

	public int getH() {
		return h;
	}
}
