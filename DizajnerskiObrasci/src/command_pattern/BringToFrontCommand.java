package command_pattern;

import geometry.Shape;
import mvc.Model;

public class BringToFrontCommand implements Command {
	private int i;
	private Shape toMove;
	private Model model;
	
	public BringToFrontCommand(int i, Shape toMove, Model model) {
		this.i = i;
		this.toMove = toMove;
		this.model = model;
	}
	public void execute() {
		model.getAllShapes().remove(i);
		model.getAllShapes().add(toMove);
	}
	
	public void unexecute() {
		model.getAllShapes().remove(model.getAllShapes().size()-1);
		model.getAllShapes().add(i, toMove);
	}
	
	public Shape getShape() {
		return toMove;
	}
}
