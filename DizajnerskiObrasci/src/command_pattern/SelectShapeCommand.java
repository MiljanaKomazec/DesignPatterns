package command_pattern;

import geometry.Shape;

public class SelectShapeCommand implements Command {
	private Shape shape;
	private Shape initialState;
	
	public SelectShapeCommand(Shape shape) {
		this.shape = shape;
		this.initialState = shape.clone();
	}
	
	public void execute() {
		shape.setSelected(true);
	}
	
	public void unexecute() {
		shape.setSelected(false);
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public Shape getInitialState() {
        return initialState;
    }
}
