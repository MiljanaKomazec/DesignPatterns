package command_pattern;

import adapter_pattern.CustomHexagon;

public class EditHexagonCommand implements Command {

	private CustomHexagon toModify;
	private CustomHexagon fromDialog;
	private CustomHexagon savedState;
	
	public EditHexagonCommand(CustomHexagon toModify, CustomHexagon fromDialog) {
		this.toModify = toModify;
		this.savedState = toModify.clone();
		this.fromDialog = fromDialog;
	}
	@Override
	public void execute() {
		toModify.setX(fromDialog.getX());
		toModify.setY(fromDialog.getY());
		toModify.setRadius(fromDialog.getRadius());
		toModify.setBorderColor(fromDialog.getBorderColor());
		toModify.setAreaColor(fromDialog.getAreaColor());
	}

	@Override
	public void unexecute() {
		toModify.setX(savedState.getX());
		toModify.setY(savedState.getY());
		toModify.setRadius(savedState.getRadius());
		toModify.setBorderColor(savedState.getBorderColor());
		toModify.setAreaColor(savedState.getAreaColor());
	}
	
	public CustomHexagon getSavedState() {
		return savedState;
	}
	
	public CustomHexagon getStateFromDialog() {
		return fromDialog;
	}

}
