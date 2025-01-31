package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {

	private Color innerColor;

	public abstract boolean contains(Point p);
	public abstract void fill(Graphics g);
	public abstract Shape clone();
	
	public Color getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}
	
}