package command_pattern;

import geometry.Point;

public class EditPointCommand implements Command {
	
	private Point toModify;
	private Point fromDialog;
	private Point savedState;

	public EditPointCommand(Point toModify, Point fromDialog) {
		this.toModify = toModify;
		this.savedState = toModify.clone();
		this.fromDialog = fromDialog;
	}
	
	@Override
	public void execute() {
		toModify.setX(fromDialog.getX());
		toModify.setY(fromDialog.getY());
		toModify.setColor(fromDialog.getColor());
		
	}

	@Override
	public void unexecute() {
		toModify.setX(savedState.getX());
		toModify.setY(savedState.getY());
		toModify.setColor(savedState.getColor());
		
	}
	
	public Point getSavedState() {
		return savedState;
	}
	
	public Point getStateFromDialog() {
		return fromDialog;
	}

}
