package command_pattern;

import java.util.ArrayList;
import java.util.Iterator;

import geometry.Shape;

public class DeselectShapesCommand implements Command {
	
	private ArrayList<Shape> savedState = new ArrayList<>();
	private ArrayList<Shape> shapes;
	
	public DeselectShapesCommand(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}
	
	public DeselectShapesCommand(Shape shape) {
		this.shapes = new ArrayList<>();
		this.shapes.add(shape);
	}
	
	public void execute() {
		savedState.clear();
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			Shape s =it.next(); 
			savedState.add(s.clone());
			s.setSelected(false);
		}
	}
	
	public void unexecute() {
		Iterator<Shape> it = shapes.iterator();
		while (it.hasNext()) {
			it.next().setSelected(true);
		}
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public ArrayList<Shape> getSavedState() {
		return savedState;
	}
}
