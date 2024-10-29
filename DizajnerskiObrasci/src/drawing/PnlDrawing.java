package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.MouseAdapter;
import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import geometry.Point;
import geometry.Line;
import geometry.Rectangle;
import geometry.Circle;
import geometry.Donut;
import geometry.Shape;
import mvc.Model;

public class PnlDrawing extends JPanel {

	private Model model;
	
	public PnlDrawing(Model model) 
	{
		this.model = model;
	}

	
//__________ Paint ____________

	public void paint(Graphics g) 
	{
		super.paint(g);
		Iterator<Shape> it = this.model.getAllShapes().iterator();
		while (it.hasNext()) 
		{
			it.next().draw(g);
		}
	}
}
