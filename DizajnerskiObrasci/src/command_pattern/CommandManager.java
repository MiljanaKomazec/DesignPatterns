package command_pattern;

import java.util.ArrayList;

import geometry.Shape;

public class CommandManager {

	private ArrayList<Command> commandsUndo = new ArrayList<>();
	private ArrayList<Command> commandsRedo = new ArrayList<>();
	
	public void addCommand(Command command) {
		commandsUndo.add(command);
		commandsRedo = new ArrayList<>();
	}

	public void undo() {
		Command lastExecutedCommand = commandsUndo.get(commandsUndo.size() - 1);
		lastExecutedCommand.unexecute();
		commandsUndo.remove(commandsUndo.size() - 1);
		commandsRedo.add(lastExecutedCommand);
	}

	public void redo() {
		Command command = commandsRedo.get(commandsRedo.size() - 1);
		command.execute();
		commandsRedo.remove(commandsRedo.size() - 1);
		commandsUndo.add(command);
	}
	
	public ArrayList<Command> getUndoList() {
		return commandsUndo;
	}
	
	public ArrayList<Command> getRedoList() {
		return commandsRedo;
	}
}
