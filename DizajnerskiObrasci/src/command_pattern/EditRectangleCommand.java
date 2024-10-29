package command_pattern;

import geometry.Rectangle;

public class EditRectangleCommand implements Command {
	
	private Rectangle toModify;
	private Rectangle fromDialog;
	private Rectangle savedState;
	
	public EditRectangleCommand(Rectangle toModify, Rectangle fromDialog) {
		this.toModify = toModify;
		this.savedState = toModify.clone();
		this.fromDialog = fromDialog;
	}
	
	public void execute() {
		toModify.setHeight(fromDialog.getHeight());
		toModify.setWidth(fromDialog.getWidth());
		toModify.getUpperLeftPoint().setX(fromDialog.getUpperLeftPoint().getX());
		toModify.getUpperLeftPoint().setY(fromDialog.getUpperLeftPoint().getY());
		toModify.setColor(fromDialog.getColor());
		toModify.setInnerColor(fromDialog.getInnerColor());
	}
	
	public void unexecute() {
		toModify.setHeight(savedState.getHeight());
		toModify.setWidth(savedState.getWidth());
		toModify.getUpperLeftPoint().setX(savedState.getUpperLeftPoint().getX());
		toModify.getUpperLeftPoint().setY(savedState.getUpperLeftPoint().getY());
		toModify.setColor(savedState.getColor());
		toModify.setInnerColor(savedState.getInnerColor());
	}
	
	public Rectangle getSavedState() {
		return savedState;
	}
	
	public Rectangle getStateFromDialog() {
		return fromDialog;
	}
}
