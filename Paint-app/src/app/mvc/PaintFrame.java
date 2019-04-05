package app.mvc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import app.Utilities;

import javax.swing.JToggleButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PaintFrame extends JFrame {
	private PaintView view = new PaintView();
	private PaintController controller;
	private Color outlineColor=Color.BLACK;
	private Color insideColor=Color.WHITE;
	private JToggleButton btnSelect;
	private JToggleButton btnPoint;
	private JToggleButton btnLine;
	private JToggleButton btnSquare;
	private JToggleButton btnRectangle;
	private JToggleButton btnCircle;
	private JToggleButton btnHexagon;
	private JButton btnOutline;
	private JButton btnInside;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnBringToBack;
	private JButton btnBringToFront;
	private JButton btnToBack;
	private JButton btnToFront;
	private JLabel lblRgboutline;
	private JLabel lblRgbinside;
	private MouseEvent firstClick;
	private DefaultListModel<String>dlm = new DefaultListModel<String>();
	
	public PaintFrame() {
		Image img = new ImageIcon(PaintFrame.class.getResource("/brush.png")).getImage();
        this.setIconImage(img);
		
		JPanel mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonsPanel = new JPanel();
		mainPanel.add(buttonsPanel, BorderLayout.NORTH);
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel bringToPanel = new JPanel();
		bringToPanel.setMinimumSize(new Dimension(1, 1));
		bringToPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		buttonsPanel.add(bringToPanel);
		GridBagLayout gbl_bringToPanel = new GridBagLayout();
		gbl_bringToPanel.columnWidths = new int[] {32, 1, 32};
		gbl_bringToPanel.rowHeights = new int[] {32, 32};
		gbl_bringToPanel.columnWeights = new double[]{0.0, 0.0, 0.0};
		gbl_bringToPanel.rowWeights = new double[]{0.0, 0.0};
		bringToPanel.setLayout(gbl_bringToPanel);
		
		btnBringToFront = new JButton("",new ImageIcon("images/bringtofront.png"));
		btnBringToFront.setToolTipText("Bring to front");
		btnBringToFront.setMaximumSize(new Dimension(32, 32));
		btnBringToFront.setMargin(new Insets(0, 0, 0, 0));
		btnBringToFront.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnBringToFront.setPreferredSize(new Dimension(32, 32));
		btnBringToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.bringToFront();
			}
		});
		btnBringToFront.setEnabled(false);
		GridBagConstraints gbc_btnBringToFront = new GridBagConstraints();
		gbc_btnBringToFront.insets = new Insets(0, 0, 5, 5);
		gbc_btnBringToFront.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBringToFront.gridx = 0;
		gbc_btnBringToFront.gridy = 0;
		bringToPanel.add(btnBringToFront, gbc_btnBringToFront);
		
		btnBringToBack = new JButton("",new ImageIcon("images/bringtoback.png"));
		btnBringToBack.setToolTipText("Bring to back");
		btnBringToBack.setPreferredSize(new Dimension(32, 32));
		btnBringToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.bringToBack();
			}
		});
		
		btnToFront = new JButton("",new ImageIcon("images/tofront.png"));
		btnToFront.setToolTipText("To front");
		btnToFront.setMaximumSize(new Dimension(32, 32));
		btnToFront.setPreferredSize(new Dimension(32, 32));
		btnToFront.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.toFront();
			}
		});
		btnToFront.setEnabled(false);
		GridBagConstraints gbc_btnToFront = new GridBagConstraints();
		gbc_btnToFront.insets = new Insets(0, 0, 5, 0);
		gbc_btnToFront.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnToFront.gridx = 2;
		gbc_btnToFront.gridy = 0;
		bringToPanel.add(btnToFront, gbc_btnToFront);
		btnBringToBack.setEnabled(false);
		GridBagConstraints gbc_btnBringToBack = new GridBagConstraints();
		gbc_btnBringToBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBringToBack.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBringToBack.gridx = 0;
		gbc_btnBringToBack.gridy = 1;
		bringToPanel.add(btnBringToBack, gbc_btnBringToBack);
		
		btnToBack = new JButton("",new ImageIcon("images/toback.png"));
		btnToBack.setToolTipText("To back");
		btnToBack.setPreferredSize(new Dimension(32, 32));
		btnToBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.toBack();
			}
		});
		btnToBack.setEnabled(false);
		GridBagConstraints gbc_btnToBack = new GridBagConstraints();
		gbc_btnToBack.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnToBack.gridx = 2;
		gbc_btnToBack.gridy = 1;
		bringToPanel.add(btnToBack, gbc_btnToBack);
		
		JPanel editDeletePanel = new JPanel();
		editDeletePanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		buttonsPanel.add(editDeletePanel);
		
		
		btnEdit = new JButton("",new ImageIcon("images/edit.png"));
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnEdit.isEnabled()==true)
				{
					controller.edit();
				}
			}
		});
		btnEdit.setToolTipText("Edit");
		btnEdit.setEnabled(false);
		btnEdit.setPreferredSize(new Dimension(32, 32));
		editDeletePanel.add(btnEdit);
		
		btnDelete = new JButton("",new ImageIcon("images/delete.png"));
		btnDelete.setToolTipText("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnDelete.isEnabled())
				{
					int option=JOptionPane.showConfirmDialog(PaintFrame.this, "Delete selected shape/s?","Delete",JOptionPane.YES_NO_OPTION);
					if(option==JOptionPane.YES_OPTION)
					{
						controller.delete();
					}
				}
			}
		});
		btnDelete.setPreferredSize(new Dimension(32, 32));
		editDeletePanel.add(btnDelete);
		
		btnSelect = new JToggleButton("",new ImageIcon("images/select.png"));
		btnSelect.setSelected(true);
		btnSelect.setToolTipText("Select tool");
		btnSelect.setPreferredSize(new Dimension(32, 32));
		editDeletePanel.add(btnSelect);
		
		JPanel shapesPanel = new JPanel();
		shapesPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		buttonsPanel.add(shapesPanel);
		GridBagLayout gbl_shapesPanel = new GridBagLayout();
		gbl_shapesPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_shapesPanel.rowHeights = new int[]{0, 0, 0};
		gbl_shapesPanel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_shapesPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		shapesPanel.setLayout(gbl_shapesPanel);
		
		btnPoint = new JToggleButton("",new ImageIcon("images/point.png"));
		btnPoint.setToolTipText("Point");
		btnPoint.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
			}
		});
		btnPoint.setPreferredSize(new Dimension(24, 24));
		GridBagConstraints gbc_btnPoint = new GridBagConstraints();
		gbc_btnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_btnPoint.gridx = 0;
		gbc_btnPoint.gridy = 0;
		shapesPanel.add(btnPoint, gbc_btnPoint);
		
		btnLine = new JToggleButton("",new ImageIcon("images/line.png"));
		btnLine.setToolTipText("Line");
		btnLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
			}
		});
		btnLine.setPreferredSize(new Dimension(24, 24));
		GridBagConstraints gbc_btnLine = new GridBagConstraints();
		gbc_btnLine.insets = new Insets(0, 0, 5, 5);
		gbc_btnLine.gridx = 1;
		gbc_btnLine.gridy = 0;
		shapesPanel.add(btnLine, gbc_btnLine);
		
		btnRectangle = new JToggleButton("",new ImageIcon("images/rectangle.png"));
		btnRectangle.setToolTipText("Rectangle");
		btnRectangle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
			}
		});
		btnRectangle.setPreferredSize(new Dimension(24, 24));
		GridBagConstraints gbc_btnRectangle = new GridBagConstraints();
		gbc_btnRectangle.insets = new Insets(0, 0, 5, 0);
		gbc_btnRectangle.gridx = 2;
		gbc_btnRectangle.gridy = 0;
		shapesPanel.add(btnRectangle, gbc_btnRectangle);
		
		btnSquare = new JToggleButton("",new ImageIcon("images/square.png"));
		btnSquare.setToolTipText("Square");
		btnSquare.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
			}
		});
		btnSquare.setPreferredSize(new Dimension(24, 24));
		GridBagConstraints gbc_btnSquare = new GridBagConstraints();
		gbc_btnSquare.insets = new Insets(0, 0, 0, 5);
		gbc_btnSquare.gridx = 0;
		gbc_btnSquare.gridy = 1;
		shapesPanel.add(btnSquare, gbc_btnSquare);
		
		btnCircle = new JToggleButton("",new ImageIcon("images/circle.png"));
		btnCircle.setToolTipText("Circle");
		btnCircle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
			}
		});
		btnCircle.setPreferredSize(new Dimension(24, 24));
		GridBagConstraints gbc_btnCircle = new GridBagConstraints();
		gbc_btnCircle.insets = new Insets(0, 0, 0, 5);
		gbc_btnCircle.gridx = 1;
		gbc_btnCircle.gridy = 1;
		shapesPanel.add(btnCircle, gbc_btnCircle);
		
		btnHexagon = new JToggleButton("",new ImageIcon("images/hexagon.png"));
		btnHexagon.setToolTipText("Hexagon");
		btnHexagon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
			}
		});
		btnHexagon.setPreferredSize(new Dimension(24, 24));
		GridBagConstraints gbc_btnHexagon = new GridBagConstraints();
		gbc_btnHexagon.gridx = 2;
		gbc_btnHexagon.gridy = 1;
		shapesPanel.add(btnHexagon, gbc_btnHexagon);
		
		JPanel fgrBgrPanel = new JPanel();
		fgrBgrPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		buttonsPanel.add(fgrBgrPanel);
		GridBagLayout gbl_fgrBgrPanel = new GridBagLayout();
		gbl_fgrBgrPanel.columnWidths = new int[] {0};
		gbl_fgrBgrPanel.rowHeights = new int[] {0, 0};
		gbl_fgrBgrPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_fgrBgrPanel.rowWeights = new double[]{0.0, 0.0};
		fgrBgrPanel.setLayout(gbl_fgrBgrPanel);
		
		btnOutline = new JButton("");
		btnOutline.setToolTipText("Outline color");
		btnOutline.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				Color previousColor = outlineColor;
				controller.deselectAll();
				JColorChooser jccOutline = new JColorChooser();
				outlineColor = jccOutline.showDialog(null, "Choose outline color", outlineColor);
				if(outlineColor==null)
					outlineColor=previousColor;
				else
					logOutlineColor();
				btnOutline.setBackground(outlineColor);
			}
		});
		btnOutline.setBackground(Color.BLACK);
		btnOutline.setPreferredSize(new Dimension(36, 36));
		GridBagConstraints gbc_btnOutline = new GridBagConstraints();
		gbc_btnOutline.insets = new Insets(2, 2, 5, 5);
		gbc_btnOutline.gridx = 0;
		gbc_btnOutline.gridy = 0;
		fgrBgrPanel.add(btnOutline, gbc_btnOutline);
		
		btnInside = new JButton("");
		btnInside.setToolTipText("Inside color");
		btnInside.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				Color previousColor=insideColor;
				controller.deselectAll();
				JColorChooser jccInside = new JColorChooser();
				insideColor = jccInside.showDialog(null, "Choose inside color", insideColor);
				if(insideColor==null)
					insideColor=previousColor;
				else
					logInsideColor();
				btnInside.setBackground(insideColor);
			}
		});
		btnInside.setPreferredSize(new Dimension(36, 36));
		btnInside.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnInside = new GridBagConstraints();
		gbc_btnInside.insets = new Insets(2, 2, 5, 2);
		gbc_btnInside.gridx = 1;
		gbc_btnInside.gridy = 0;
		fgrBgrPanel.add(btnInside, gbc_btnInside);
		
		lblRgboutline = new JLabel("");
		lblRgboutline.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblRgboutline.setText(Utilities.getRGBText(outlineColor));
		GridBagConstraints gbc_lblRgboutline = new GridBagConstraints();
		gbc_lblRgboutline.insets = new Insets(0, 0, 0, 5);
		gbc_lblRgboutline.gridx = 0;
		gbc_lblRgboutline.gridy = 1;
		fgrBgrPanel.add(lblRgboutline, gbc_lblRgboutline);
		
		lblRgbinside = new JLabel("");
		lblRgbinside.setFont(new Font("Tahoma", Font.PLAIN, 8));
		GridBagConstraints gbc_lblRgbinside = new GridBagConstraints();
		lblRgbinside.setText(Utilities.getRGBText(insideColor));
		gbc_lblRgbinside.gridx = 1;
		gbc_lblRgbinside.gridy = 1;
		fgrBgrPanel.add(lblRgbinside, gbc_lblRgbinside);
		
		JPanel colorsPanel = new JPanel();
		colorsPanel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		buttonsPanel.add(colorsPanel);
		GridBagLayout gbl_colorsPanel = new GridBagLayout();
		gbl_colorsPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_colorsPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_colorsPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_colorsPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		colorsPanel.setLayout(gbl_colorsPanel);
		
		JButton btnColor1 = new JButton("");
		btnColor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor1.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor1.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor1.setBackground(new Color(0, 0, 0));
		btnColor1.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor1 = new GridBagConstraints();
		gbc_btnColor1.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor1.gridx = 0;
		gbc_btnColor1.gridy = 0;
		colorsPanel.add(btnColor1, gbc_btnColor1);
		
		JButton btnColor2 = new JButton("");
		btnColor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor2.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor2.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor2.setBackground(Color.DARK_GRAY);
		btnColor2.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor2 = new GridBagConstraints();
		gbc_btnColor2.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor2.gridx = 1;
		gbc_btnColor2.gridy = 0;
		colorsPanel.add(btnColor2, gbc_btnColor2);
		
		JButton btnColor3 = new JButton("");
		btnColor3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor3.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor3.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor3.setBackground(Color.LIGHT_GRAY);
		btnColor3.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor3 = new GridBagConstraints();
		gbc_btnColor3.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor3.anchor = GridBagConstraints.NORTH;
		gbc_btnColor3.gridx = 2;
		gbc_btnColor3.gridy = 0;
		colorsPanel.add(btnColor3, gbc_btnColor3);
		
		JButton btnColor4 = new JButton("");
		btnColor4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor4.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor4.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor4.setBackground(Color.RED);
		btnColor4.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor4 = new GridBagConstraints();
		gbc_btnColor4.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor4.gridx = 3;
		gbc_btnColor4.gridy = 0;
		colorsPanel.add(btnColor4, gbc_btnColor4);
		
		JButton btnColor5 = new JButton("");
		btnColor5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor5.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor5.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor5.setBackground(Color.PINK);
		btnColor5.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor5 = new GridBagConstraints();
		gbc_btnColor5.insets = new Insets(0, 0, 5, 0);
		gbc_btnColor5.gridx = 4;
		gbc_btnColor5.gridy = 0;
		colorsPanel.add(btnColor5, gbc_btnColor5);
		
		JButton btnColor6 = new JButton("");
		btnColor6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor6.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor6.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor6.setBackground(new Color(255, 165, 0));
		btnColor6.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor6 = new GridBagConstraints();
		gbc_btnColor6.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor6.gridx = 0;
		gbc_btnColor6.gridy = 1;
		colorsPanel.add(btnColor6, gbc_btnColor6);
		
		JButton btnColor7 = new JButton("");
		btnColor7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor7.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor7.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor7.setBackground(Color.YELLOW);
		btnColor7.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor7 = new GridBagConstraints();
		gbc_btnColor7.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor7.gridx = 1;
		gbc_btnColor7.gridy = 1;
		colorsPanel.add(btnColor7, gbc_btnColor7);
		
		JButton btnColor8 = new JButton("");
		btnColor8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor8.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor8.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor8.setBackground(new Color(0, 100, 0));
		btnColor8.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor8 = new GridBagConstraints();
		gbc_btnColor8.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor8.gridx = 2;
		gbc_btnColor8.gridy = 1;
		colorsPanel.add(btnColor8, gbc_btnColor8);
		
		JButton btnColor9 = new JButton("");
		btnColor9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor9.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor9.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor9.setBackground(new Color(0, 255, 0));
		btnColor9.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor9 = new GridBagConstraints();
		gbc_btnColor9.insets = new Insets(0, 0, 5, 5);
		gbc_btnColor9.gridx = 3;
		gbc_btnColor9.gridy = 1;
		colorsPanel.add(btnColor9, gbc_btnColor9);
		
		JButton btnColor10 = new JButton("");
		btnColor10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor10.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor10.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor10.setBackground(new Color(0, 0, 128));
		btnColor10.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor10 = new GridBagConstraints();
		gbc_btnColor10.anchor = GridBagConstraints.NORTH;
		gbc_btnColor10.insets = new Insets(0, 0, 5, 0);
		gbc_btnColor10.gridx = 4;
		gbc_btnColor10.gridy = 1;
		colorsPanel.add(btnColor10, gbc_btnColor10);
		
		JButton btnColor11 = new JButton("");
		btnColor11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor11.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor11.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor11.setBackground(new Color(0, 0, 255));
		btnColor11.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor11 = new GridBagConstraints();
		gbc_btnColor11.insets = new Insets(0, 0, 0, 5);
		gbc_btnColor11.gridx = 0;
		gbc_btnColor11.gridy = 2;
		colorsPanel.add(btnColor11, gbc_btnColor11);
		
		JButton btnColor12 = new JButton("");
		btnColor12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor12.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor12.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor12.setBackground(Color.MAGENTA);
		btnColor12.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor12 = new GridBagConstraints();
		gbc_btnColor12.anchor = GridBagConstraints.SOUTH;
		gbc_btnColor12.insets = new Insets(0, 0, 0, 5);
		gbc_btnColor12.gridx = 1;
		gbc_btnColor12.gridy = 2;
		colorsPanel.add(btnColor12, gbc_btnColor12);
		
		JButton btnColor13 = new JButton("");
		btnColor13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor13.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor13.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor13.setBackground(Color.CYAN);
		btnColor13.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor13 = new GridBagConstraints();
		gbc_btnColor13.insets = new Insets(0, 0, 0, 5);
		gbc_btnColor13.gridx = 2;
		gbc_btnColor13.gridy = 2;
		colorsPanel.add(btnColor13, gbc_btnColor13);
		
		JButton btnColor14 = new JButton("");
		btnColor14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor14.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor14.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor14.setBackground(new Color(128, 0, 0));
		btnColor14.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor14 = new GridBagConstraints();
		gbc_btnColor14.anchor = GridBagConstraints.NORTH;
		gbc_btnColor14.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnColor14.insets = new Insets(0, 0, 0, 5);
		gbc_btnColor14.gridx = 3;
		gbc_btnColor14.gridy = 2;
		colorsPanel.add(btnColor14, gbc_btnColor14);
		
		JButton btnColor15 = new JButton("");
		btnColor15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.deselectAll();
				if(e.getButton() == MouseEvent.BUTTON1) {
		            outlineColor=btnColor15.getBackground();
		            btnOutline.setBackground(outlineColor);
		            logOutlineColor();
		          }
				else if(e.getButton() == MouseEvent.BUTTON3) {
		            insideColor=btnColor15.getBackground();
		            btnInside.setBackground(insideColor);
		            logInsideColor();
		          }
			}
		});
		btnColor15.setBackground(new Color(255, 255, 255));
		btnColor15.setPreferredSize(new Dimension(16, 16));
		GridBagConstraints gbc_btnColor15 = new GridBagConstraints();
		gbc_btnColor15.gridx = 4;
		gbc_btnColor15.gridy = 2;
		colorsPanel.add(btnColor15, gbc_btnColor15);
		
		JPanel logPanel = new JPanel();
		logPanel.setPreferredSize(new Dimension(900, 100));
		mainPanel.add(logPanel, BorderLayout.SOUTH);
		
		JScrollPane spLog = new JScrollPane();
		spLog.setPreferredSize(new Dimension(400, 90));
		logPanel.add(spLog);
		
		JList<String> jlLog = new JList<String>();
		jlLog.setBorder(new TitledBorder(null, "Log", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		spLog.setViewportView(jlLog);
		view.setBackground(new Color(255, 255, 255));
		view.setBorder(new LineBorder(Color.LIGHT_GRAY));
		jlLog.setModel(dlm);
		
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnLine.isSelected())
				{
					if(firstClick==null)
					{
						firstClick=arg0;
					}
					else
					{
						controller.draw(firstClick, arg0, outlineColor, insideColor);
						firstClick=null;
					}
				}
				else if(btnSelect.isSelected())
				{
					controller.select(arg0);
				}
				else
				{
					controller.draw(arg0,firstClick,outlineColor,insideColor);
				}
			}
		});
		mainPanel.add(view, BorderLayout.CENTER); 
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(btnSelect);
		btnGroup.add(btnPoint);
		btnGroup.add(btnLine);
		btnGroup.add(btnSquare);
		btnGroup.add(btnRectangle);
		btnGroup.add(btnCircle);
		btnGroup.add(btnHexagon);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.open();
			}
		});
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				defaultFrame();
				controller.newFile();
			}
		});
		mnFile.add(mntmNew);
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.save();
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(PaintFrame.this, "Error while saving file!", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		mnFile.add(mntmSave);
		
		btnUndo = new JButton("",new ImageIcon("images/undo.png"));
		btnUndo.setToolTipText("Undo");
		btnUndo.setMaximumSize(new Dimension(24, 24));
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnUndo.isEnabled()==true)
				{
					controller.undo();
					controller.deselectAll();
				}
			}
		});
		btnUndo.setEnabled(false);
		btnUndo.setPreferredSize(new Dimension(24, 24));
		menuBar.add(btnUndo);
		
		btnRedo = new JButton("", new ImageIcon("images/redo.png"));
		btnRedo.setToolTipText("Redo");
		btnRedo.setMaximumSize(new Dimension(24, 24));
		btnRedo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				controller.redo();
				controller.deselectAll();
			}
		});
		btnRedo.setPreferredSize(new Dimension(24, 24));
		btnRedo.setEnabled(false);
		menuBar.add(btnRedo);
	}	
	
	public JLabel getLblRgboutline() {
		return lblRgboutline;
	}

	public JLabel getLblRgbinside() {
		return lblRgbinside;
	}

	public JButton getBtnOutline() {
		return btnOutline;
	}

	public JButton getBtnInside() {
		return btnInside;
	}

	public void defaultFrame() {
		this.btnUndo.setEnabled(false);
		this.btnRedo.setEnabled(false);
		this.btnBringToBack.setEnabled(false);
		this.btnBringToFront.setEnabled(false);
		this.btnToFront.setEnabled(false);
		this.btnToBack.setEnabled(false);
		this.btnEdit.setEnabled(false);
		this.btnDelete.setEnabled(false);
		this.outlineColor=Color.BLACK;
		this.insideColor=Color.WHITE;
		this.btnOutline.setBackground(outlineColor);
		this.btnInside.setBackground(insideColor);
		this.btnSelect.setSelected(true);
		this.lblRgboutline.setText(Utilities.getRGBText(outlineColor));
		this.lblRgbinside.setText(Utilities.getRGBText(insideColor));
		this.dlm.clear();
		this.firstClick=null;
	}

	public void logOutlineColor() {
		dlm.addElement("OUTLINE COLOR:"+Utilities.getRGBText(outlineColor));
		this.lblRgboutline.setText(Utilities.getRGBText(outlineColor));
	}
	
	public void logInsideColor() {
		dlm.addElement("INSIDE COLOR:"+Utilities.getRGBText(insideColor));
		this.lblRgbinside.setText(Utilities.getRGBText(insideColor));
	}

	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	public PaintView getView() {
		return view;
	}
	
	public JToggleButton getBtnSelect() {
		return btnSelect;
	}

	public JToggleButton getBtnPoint() {
		return btnPoint;
	}

	public JToggleButton getBtnLine() {
		return btnLine;
	}

	public JToggleButton getBtnSquare() {
		return btnSquare;
	}

	public JToggleButton getBtnRectangle() {
		return btnRectangle;
	}

	public JToggleButton getBtnCircle() {
		return btnCircle;
	}

	public JToggleButton getBtnHexagon() {
		return btnHexagon;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo()
	{
		return btnRedo;
	}
	
	public JButton getBtnEdit() {
		return btnEdit;
	}
	
	public JButton getBtnDelete()
	{
		return btnDelete;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}

	public JToggleButton getSelectedButton()
	{
		if(btnSelect.isSelected())
		{
			return btnSelect;
		}
		else if(btnPoint.isSelected())
		{
			return btnPoint;
		}
		else if(btnLine.isSelected())
		{
			return btnLine;
		}
		else if(btnSquare.isSelected())
		{
			return btnSquare;
		}
		else if(btnRectangle.isSelected())
		{
			return btnRectangle;
		}
		else if(btnCircle.isSelected())
		{
			return btnCircle;
		}
		else if(btnHexagon.isSelected())
		{
			return btnHexagon;
		}
		else
			return null;
	}

	public void setFirstClick(MouseEvent firstClick) {
		this.firstClick = firstClick;
	}

	public void setController(PaintController controller) {
		this.controller=controller;
	}
}
