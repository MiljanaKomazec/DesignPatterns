package strategy_pattern;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import mvc.Model;

public class StrategySerialized implements Strategy {
	
	private Model model;
	
	public void save(String filePath) {
		FileOutputStream fileStream;
		ObjectOutputStream objStream;
		try {
			fileStream = new FileOutputStream(filePath);
			objStream = new ObjectOutputStream(fileStream);
			objStream.writeObject(model.getAllShapes());
			objStream.close();
			fileStream.close();
			JOptionPane.showMessageDialog(null, "Successfully saved", "File saved", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save", "Saving failed", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save", "Saving failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setModel(Model model ) {
		this.model = model;
	}
}