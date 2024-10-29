package command_pattern;

import geometry.Shape;
import mvc.Model;

public class BringToBackCommand implements Command {
	private int i;
	private Shape toMove;
	private Model model;
	
	public BringToBackCommand(int i, Shape toMove, Model model) {
		this.i = i;
		this.toMove = toMove;
		this.model = model;
	}
	public void execute() {
		model.getAllShapes().remove(i);
		model.getAllShapes().add(0, toMove);
	}
	
	public void unexecute() {
		//int i = 0;
		model.getAllShapes().remove(0);
		model.getAllShapes().add(i, toMove);
	}
	
	public Shape getShape() {
		return toMove;
	}
}
