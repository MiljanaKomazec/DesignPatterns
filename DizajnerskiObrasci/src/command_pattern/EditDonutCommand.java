package command_pattern;

import geometry.Donut;

public class EditDonutCommand implements Command {
	
	private Donut toModify;
	private Donut fromDialog;
	private Donut savedState;
	
	public EditDonutCommand(Donut toModify, Donut fromDialog) {
		this.toModify = toModify;
		this.savedState = toModify.clone();
		this.fromDialog = fromDialog;
	}

	@Override
	public void execute() {
		try {
			toModify.setRadius(fromDialog.getRadius());
			toModify.setInnerRadius(fromDialog.getInnerRadius());
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
			toModify.setInnerRadius(savedState.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		toModify.getCenter().setX(savedState.getCenter().getX());
		toModify.getCenter().setY(savedState.getCenter().getY());
		toModify.setColor(savedState.getColor());
		toModify.setInnerColor(savedState.getInnerColor());
	}
	
	public Donut getSavedState() {
		return savedState;
	}
	
	public Donut getStateFromDialog() {
		return fromDialog;
	}

}
