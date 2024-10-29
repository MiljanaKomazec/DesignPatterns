package command_pattern;

import geometry.Shape;
import mvc.Model;

public class ToFrontCommand implements Command {
	private int i;
	private Shape toReplace;
	private Model model;
	
	public ToFrontCommand(int i, Shape toReplace, Model model) {
		this.i = i;
		this.toReplace = toReplace;
		this.model = model;
	}
	public void execute() {
		Shape nextOne = model.getAllShapes().get(i + 1);
		model.getAllShapes().set(i, nextOne);
		model.getAllShapes().set(i + 1, toReplace);
	}
	
	public void unexecute() {
		Shape tempShape1 = model.getAllShapes().get(i);
		Shape tempShape2 = model.getAllShapes().get(i + 1);
		model.getAllShapes().set(i, tempShape2);
		model.getAllShapes().set(i + 1, tempShape1);
	}
	
	public Shape getShape() {
		return toReplace;
	}
}
