package command_pattern;

import java.util.ArrayList;

import geometry.Shape;
import mvc.Model;

public class DeleteShapesCommand implements Command {

	Model model;

	private ArrayList<Shape> shapes;
	private ArrayList<Shape> savedState = new ArrayList<>();
	private ArrayList<ArrayList<Object>> memory = new ArrayList<>();
    
	public DeleteShapesCommand(Model model, ArrayList<Shape> shapes) {
		this.model = model;
		this.shapes = shapes;
	}
	
	public void execute() {
		memory = new ArrayList<>();
		for (int i = 0; i < model.getAllShapes().size(); i++) {
			if (model.getAllShapes().get(i).isSelected()) {
				ArrayList<Object> temp = new ArrayList<>();
				temp.add(i);
				temp.add(model.getAllShapes().get(i));
				
				memory.add(temp);
			}
		}
		
		for (int i = 0; i < shapes.size(); i++) {
			for (Shape shape : shapes) {
				savedState.add(shape.clone());
				model.getAllShapes().remove(shape);
			}
		}
	}
	
	public void unexecute() {
		for (int i = 0; i < memory.size(); i++) {
			ArrayList<Object> temp = memory.get(i);
			int index = (int)temp.get(0);
			Shape shape = (Shape)temp.get(1);
			model.getAllShapes().add(index, shape);
		}
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
	public ArrayList<Shape> getSavedState() {
		return savedState;
	}
}
