package command_pattern;

import geometry.Shape;
import mvc.Model;

public class AddShapeCommand implements Command {
	
	Model model;
	Shape shape;
	
	private Shape initialState;
	
	public AddShapeCommand(Model model, Shape shape) {
		this.model = model;
		this.shape = shape;
		this.initialState = shape.clone();
	}
	
	public void execute() {
		model.getAllShapes().add(shape);
	}
	
	public void unexecute() {
		model.getAllShapes().remove(shape);
	}

	public Shape getShape() {
		return shape;
	}
	
	public Shape getInitialState() {
        return initialState;
    }

}
