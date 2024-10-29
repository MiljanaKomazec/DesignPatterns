package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;
import net.miginfocom.swing.MigLayout;
import observer_pattern.Observer;
import mvc.Controller;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import javax.swing.JSeparator;
import javax.swing.JColorChooser;
import java.awt.SystemColor;

public class FrmDrawing extends JFrame implements Observer {

	private JPanel contentPane;
	int state = 0;
	public DefaultListModel<String> dlm = new DefaultListModel<String>();
	
	private PnlDrawing pnlDrawing;
	private Controller controller;
	
	private Color color = Color.BLACK;
	private Color innerColor = Color.WHITE;
	
	private JToggleButton tglModify;
	private JToggleButton tglDelete;
	private JToggleButton tglSelect;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnSaveSer;
	private JButton btnSaveTextual;
	private JButton btnParseFileLine;

	
	public FrmDrawing(PnlDrawing pnlDrawing, Controller controller) {
		this.pnlDrawing = pnlDrawing;
		this.controller = controller;
		
		setTitle("Miljana Komazec IT60/2020");
		pnlDrawing.setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ButtonGroup group = new ButtonGroup();
		
//__________ North _____________
		
		JPanel pnlNorth = new JPanel();
		pnlNorth.setForeground(new Color(0, 0, 0));
		pnlNorth.setBackground(new Color(51, 204, 0));
		contentPane.add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblDrawing = new JLabel("Drawing");
		lblDrawing.setForeground(new Color(0, 0, 0));
		lblDrawing.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDrawing.setBackground(new Color(51, 255, 51));
		lblDrawing.setHorizontalAlignment(SwingConstants.CENTER);
		pnlNorth.add(lblDrawing);
		
//__________ West ____________
		
		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(new Color(51, 255, 51));
		contentPane.add(pnlWest, BorderLayout.WEST);
		//----------------
		JRadioButton rdbPoint = new JRadioButton("Point");
		rdbPoint.setForeground(new Color(0, 0, 0));
		rdbPoint.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbPoint.setBackground(new Color(51, 255, 51));
		group.add(rdbPoint);
		rdbPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				state = 1;
			}
		});
		pnlWest.setLayout(new MigLayout("", "[126px,grow]", "[22px][23px][23px][23px][23px][][23px,grow][23px][23px][][][][][][][]"));
		pnlWest.add(rdbPoint, "cell 0 0,alignx left,aligny center");
		//----------------
		JRadioButton rdbLine = new JRadioButton("Line");
		rdbLine.setForeground(new Color(0, 0, 0));
		rdbLine.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbLine.setBackground(new Color(51, 255, 51));
		group.add(rdbLine);
		rdbLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				state = 2;
			}
		});
		pnlWest.add(rdbLine, "cell 0 1,alignx left,aligny center");
		//----------------
		JRadioButton rdbRectangle = new JRadioButton("Rectangle");
		rdbRectangle.setForeground(new Color(0, 0, 0));
		rdbRectangle.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbRectangle.setBackground(new Color(51, 255, 51));
		group.add(rdbRectangle);
		rdbRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				state = 3;
			}
		});
		pnlWest.add(rdbRectangle, "cell 0 2,alignx left,aligny center");
		//----------------
		JRadioButton rdbCircle = new JRadioButton("Circle");
		rdbCircle.setForeground(new Color(0, 0, 0));
		rdbCircle.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbCircle.setBackground(new Color(51, 255, 51));
		group.add(rdbCircle);
		rdbCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				state = 4;
			}
		});
		pnlWest.add(rdbCircle, "cell 0 3,alignx left,aligny center");
		//----------------
		JRadioButton rdbDonut = new JRadioButton("Donut");
		rdbDonut.setForeground(new Color(0, 0, 0));
		rdbDonut.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbDonut.setBackground(new Color(51, 255, 51));
		group.add(rdbDonut);
		rdbDonut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				state = 5;
			}
		});
		pnlWest.add(rdbDonut, "cell 0 4,alignx left,aligny center");
		//----------------
		JRadioButton rdbHexagon = new JRadioButton("Hexagon");
		rdbHexagon.setForeground(new Color(0, 0, 0));
		rdbHexagon.setFont(new Font("Times New Roman", Font.BOLD, 12));
		rdbHexagon.setBackground(new Color(51, 255, 51));
		group.add(rdbHexagon);
		rdbHexagon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				state = 6;
			}
		});
		pnlWest.add(rdbHexagon, "cell 0 5,alignx left,aligny center");
		//----------------
		JScrollPane scrlListShape = new JScrollPane();
		pnlWest.add(scrlListShape, "cell 0 6 1 7,grow");

		JList listShapes = new JList();
		listShapes.setBackground(new Color(0, 204, 0));
		listShapes.setForeground(new Color(0, 0, 0));
		listShapes.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		listShapes.setModel(dlm);
		scrlListShape.setViewportView(listShapes);
		//----------------
		tglSelect = new JToggleButton("Select");
		tglSelect.setForeground(new Color(0, 0, 0));
		tglSelect.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tglSelect.setBackground(new Color(230, 230, 250));
		tglSelect.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		group.add(tglSelect);
		tglSelect.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				state = 7;
			}
		});
		pnlWest.add(tglSelect, "cell 0 14,growx,aligny center");
		//----------------
		tglModify = new JToggleButton("Modify");
		tglModify.setForeground(new Color(0, 0, 0));
		tglModify.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tglModify.setBackground(new Color(230, 230, 250));
		tglModify.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		group.add(tglModify);
		tglModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.modifyShape();
			}
		});
		pnlWest.add(tglModify, "flowy,cell 0 15,growx,aligny center");
		//-----------------
		tglDelete = new JToggleButton("Delete");
		tglDelete.setForeground(new Color(0, 0, 0));
		tglDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tglDelete.setBackground(new Color(230, 230, 250));
		tglDelete.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		group.add(tglDelete);
		tglDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				state = 0;
				controller.delete();
			}
		});
		pnlWest.add(tglDelete, "cell 0 15,growx");
		

//__________ East _______________
		
		JPanel pnlEast = new JPanel();
		contentPane.add(pnlEast, BorderLayout.EAST);
		pnlEast.setBackground(new Color(51, 255, 51));
		pnlEast.setLayout(new MigLayout("", "[126px,grow]", "[22px][23px][23px][23px][23px][23px][23px][23px][23px][23px][23px][23px][23px][23px][23px][23px][23px][23px]"));
		
		//----------------
		
		btnUndo = new JButton("Undo");
		btnUndo.setForeground(new Color(0, 0, 0));
		btnUndo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnUndo.setBackground(new Color(222, 184, 135));
		btnUndo.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				controller.undo();
			}
		});
		
		btnRedo = new JButton("Redo");
		btnRedo.setForeground(new Color(0, 0, 0));
		btnRedo.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnRedo.setBackground(new Color(222, 184, 135));
		btnRedo.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				controller.redo();
			}
		});
		
		pnlEast.add(btnUndo, "cell 0 0,growx,aligny center");
		pnlEast.add(btnRedo, "cell 0 1,growx,aligny center");
		
		JSeparator separator = new JSeparator();
		pnlEast.add(separator, "cell 0 2");
		
		btnToFront = new JButton("To Front");
		btnToFront.setForeground(new Color(0, 0, 0));
		btnToFront.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnToFront.setBackground(new Color(135, 206, 250));
		btnToFront.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		pnlEast.add(btnToFront, "cell 0 3,growx,aligny center");
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toFront();
			}
		});
		
		btnToBack = new JButton("To Back");
		btnToBack.setForeground(new Color(0, 0, 0));
		btnToBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnToBack.setBackground(new Color(135, 206, 250));
		btnToBack.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.toBack();
			}
		});
		pnlEast.add(btnToBack, "cell 0 4,growx,aligny center");
		
		
		btnBringToFront  = new JButton("Bring To Front");
		btnBringToFront.setForeground(new Color(0, 0, 0));
		btnBringToFront.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBringToFront.setBackground(new Color(255, 255, 128));
		btnBringToFront.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToFront();
			}
		});
		pnlEast.add(btnBringToFront, "cell 0 5,growx,aligny center");
		
		btnBringToBack = new JButton("Bring To Back");
		btnBringToBack.setForeground(new Color(0, 0, 0));
		btnBringToBack.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBringToBack.setBackground(new Color(255, 255, 128));
		btnBringToBack.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bringToBack();
			}
		});
		pnlEast.add(btnBringToBack, "cell 0 6,growx,aligny center");
		
		JButton btnColor = new JButton("Border color");
		btnColor.setForeground(new Color(255, 255, 255));
		btnColor.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnColor.setBackground(new Color(0, 0, 0));
		btnColor.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color tempColor = JColorChooser.showDialog(null, "Choose a color", color);
				if (tempColor != null) {
					btnColor.setBackground(tempColor);
					color = tempColor;
				}
			}
		});
		pnlEast.add(btnColor, "cell 0 8,growx,aligny center");
		
		JButton btnInnerColor = new JButton("Inner color");
		btnInnerColor.setForeground(new Color(0, 0, 0));
		btnInnerColor.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnInnerColor.setBackground(new Color(255, 255, 255));
		btnInnerColor.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color tempColor = JColorChooser.showDialog(null, "Choose a inner color", innerColor);
				if (tempColor != null) {
					btnInnerColor.setBackground(tempColor);
					innerColor = tempColor;
				}
			}
		});
		pnlEast.add(btnInnerColor, "cell 0 9,growx,aligny center");
		
		btnSaveSer = new JButton("Save serialized");
		btnSaveSer.setForeground(new Color(0, 0, 0));
		btnSaveSer.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSaveSer.setBackground(new Color(211, 211, 211));
		btnSaveSer.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnSaveSer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveSerialized();
			}
		});
		pnlEast.add(btnSaveSer, "cell 0 11,growx,aligny center");
		
		btnSaveTextual = new JButton("Save textual");
		btnSaveTextual.setForeground(new Color(0, 0, 0));
		btnSaveTextual.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnSaveTextual.setBackground(new Color(211, 211, 211));
		btnSaveTextual.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnSaveTextual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveTextual();
			}
		});
		pnlEast.add(btnSaveTextual, "cell 0 12,growx,aligny center");
		
		JButton btnReadSerialized = new JButton("Read serialized");
		btnReadSerialized.setForeground(new Color(0, 0, 0));
		btnReadSerialized.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnReadSerialized.setBackground(SystemColor.control);
		btnReadSerialized.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnReadSerialized.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.readSerialized();
			}
		});
		pnlEast.add(btnReadSerialized, "cell 0 14,growx,aligny center");
		
		JButton btnReadTextual = new JButton("Read textual");
		btnReadTextual.setForeground(new Color(0, 0, 0));
		btnReadTextual.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnReadTextual.setBackground(SystemColor.control);
		btnReadTextual.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnReadTextual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.readTextual();
			}
		});
		pnlEast.add(btnReadTextual, "cell 0 15,growx,aligny center");
		
		btnParseFileLine = new JButton("Parse next line");
		btnParseFileLine.setForeground(new Color(0, 0, 0));
		btnParseFileLine.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnParseFileLine.setBackground(new Color(216, 191, 216));
		btnParseFileLine.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, new Color(0, 0, 0)));
		btnParseFileLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.parseNextLog();
			}
		});
		pnlEast.add(btnParseFileLine, "cell 0 17,growx,aligny center");
		
		//----------------
		
//__________ Center _____________
		
		JPanel pnlCenter = new JPanel();
		contentPane.add(pnlCenter, BorderLayout.CENTER);
		
		pnlDrawing.setSize(new Dimension(20,40));
		pnlDrawing.setPreferredSize(new Dimension(200,400));
		contentPane.add(pnlDrawing);
		
		pnlDrawing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				onMouseClicked(me);
			}
		});
	}
	
//_______ Private methods
	
	private void onMouseClicked(MouseEvent me) 
	{
		if (getState() == 1) 
		{
			controller.pointDraw(me, color);
		} 
		else if (getState() == 2) 
		{
			controller.lineDraw(me, color);
		} 
		else if (getState() == 3) 
		{
			controller.rectangleDraw(me, color, innerColor);
		}
		else if (getState() == 4) 
		{
			controller.circleDraw(me, color, innerColor);
		} 
		else if (getState() == 5) 
		{
			controller.donutDraw(me, color, innerColor);
		}
		else if (getState() == 6) 
		{
			controller.hexagonDraw(me, color, innerColor);
		}
		else if (getState() == 7) 
		{
			controller.selectShape(me);
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "You have to pick shape first!");	
		}
		repaint();
	}
	

	//________ Update State __________	
	
	public void updateState(int selectedShapesNumber) {
		tglModify.setEnabled(selectedShapesNumber == 1);
		tglDelete.setEnabled(selectedShapesNumber>=1);
	}
	
	//________ Enable buttons __________
	
	public void setIsBtnUndoEnabled(boolean isEnabled) {
		btnUndo.setEnabled(isEnabled);
	}
	
	public void setIsBtnRedoEnabled(boolean isEnabled) {
		btnRedo.setEnabled(isEnabled);
	}
	
	public void setIsBtnSelectEnabled(boolean isEnabled) {
		tglSelect.setEnabled(isEnabled);
	}
	
	public void setIsBtnToFrontEnabled(boolean isEnabled) {
		btnToFront.setEnabled(isEnabled);
	}
	
	public void setIsBtnToBackEnabled(boolean isEnabled) {
		btnToBack.setEnabled(isEnabled);
	}
	
	public void setIsBtnBringToFrontEnabled(boolean isEnabled) {
		btnBringToFront.setEnabled(isEnabled);
	}
	
	public void setIsBtnBringToBackEnabled(boolean isEnabled) {
		btnBringToBack.setEnabled(isEnabled);
	}
	
	public void setIsBtnSaveSerEnabled(boolean isEnabled) {
		btnSaveSer.setEnabled(isEnabled);
	}
	
	public void setIsBtnSaveTextualEnabled(boolean isEnabled) {
		btnSaveTextual.setEnabled(isEnabled);
	}
	
	public void setIsBtnParseFileLineEnabled(boolean isEnabled) {
		btnParseFileLine.setEnabled(isEnabled);
	}

//________ Getters and Setters __________
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public DefaultListModel<String> getDlm() {
		return dlm;
	}

	public void setDlm(DefaultListModel<String> dlm) {
		this.dlm = dlm;
	}
}