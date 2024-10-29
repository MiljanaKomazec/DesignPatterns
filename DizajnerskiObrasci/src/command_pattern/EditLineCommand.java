package command_pattern;

import geometry.Line;

public class EditLineCommand implements Command {
	
	private Line toModify;
	private Line fromDialog;
	private Line savedState;
	
	public EditLineCommand(Line toModify, Line fromDialog) {
		this.toModify = toModify;
		this.savedState = toModify.clone();
		this.fromDialog = fromDialog;
	}

	@Override
	public void execute() {
		toModify.getStartPoint().setX(fromDialog.getStartPoint().getX());
		toModify.getStartPoint().setY(fromDialog.getStartPoint().getY());
		toModify.getEndPoint().setX(fromDialog.getEndPoint().getX());
		toModify.getEndPoint().setY(fromDialog.getEndPoint().getY());
		toModify.setColor(fromDialog.getColor());
		
	}

	@Override
	public void unexecute() {
		toModify.getStartPoint().setX(savedState.getStartPoint().getX());
		toModify.getStartPoint().setY(savedState.getStartPoint().getY());
		toModify.getEndPoint().setX(savedState.getEndPoint().getX());
		toModify.getEndPoint().setY(savedState.getEndPoint().getY());
		toModify.setColor(savedState.getColor());
	}
	
	public Line getSavedState() {
		return savedState;
	}
	
	public Line getStateFromDialog() {
		return fromDialog;
	}

}
