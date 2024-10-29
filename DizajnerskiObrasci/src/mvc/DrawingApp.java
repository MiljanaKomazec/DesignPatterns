package mvc;

import drawing.FrmDrawing;
import drawing.PnlDrawing;

public class DrawingApp {

	public static void main(String[] args) {
		Model model = new Model();
		PnlDrawing view = new PnlDrawing(model);
		Controller c = new Controller(model);
		
		FrmDrawing frmDrawing = new FrmDrawing(view, c);
		
		c.setFrmDrawing(frmDrawing);
		c.add(frmDrawing);
		
		c.enableDisableButtons();
		c.notifyObservers();
		
		frmDrawing.setVisible(true);
	}

}
