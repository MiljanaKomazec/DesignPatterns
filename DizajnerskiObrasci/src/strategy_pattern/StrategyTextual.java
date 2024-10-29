package strategy_pattern;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class StrategyTextual implements Strategy {
	
	private String text;
	
	public void save(String filePath) {
		filePath += ".txt";
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
			writer.write(text);
			writer.close();
			JOptionPane.showMessageDialog(null, "Successfully saved", "File saved", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Failed to save", "Saving failed", JOptionPane.ERROR_MESSAGE);
		}
	    
	    
	}
	
	public void setText(String text) {
		this.text = text;
	}

}
