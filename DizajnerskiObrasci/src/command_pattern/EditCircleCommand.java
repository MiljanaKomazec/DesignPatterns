package command_pattern;

import geometry.Circle;

public class EditCircleCommand implements Command {
	
	private Circle toModify;
	private Circle fromDialog;
	private Circle savedState;
	
	public EditCircleCommand(Circle toModify, Circle fromDialog) {
		this.toModify = toModify;
		this.savedState = toModify.clone();
		this.fromDialog = fromDialog;
	}

	@Override
	public void execute() {
		try {
			toModify.setRadius(fromDialog.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		toModify.getCenter().setX(fromDialog.getCenter().getX());
		toModify.getCenter().setY(fromDialog.getCenter().getY());
		toModify.setColor(fromDialog.getColor());
		toModify.setInnerColor(fromDialog.getInnerColor());
	}

	@Override
	public void unexecute() {
		try {
			toModify.setRadius(savedState.getRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		toModify.getCenter().setX(savedState.getCenter().getX());
		toModify.getCenter().setY(savedState.getCenter().getY());
		toModify.setColor(savedState.getColor());
		toModify.setInnerColor(savedState.getInnerColor());
		
	}
	
	public Circle getSavedState() {
		return savedState;
	}
	
	public Circle getStateFromDialog() {
		return fromDialog;
	}
}
