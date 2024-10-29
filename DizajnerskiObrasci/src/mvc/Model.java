package mvc;

import java.util.ArrayList;

import geometry.Shape;

public class Model {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public ArrayList<Shape> getAllShapes() {
		return shapes;
	}
	
	public void setAllShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public void deleteAllShapes() {
		shapes.clear();
	}
}
