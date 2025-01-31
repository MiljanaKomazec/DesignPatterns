package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	private Point startPoint;
	private Point endPoint;
	
	public Line() {
		
	}
	
	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected); 
	}
	
	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		this.setColor(color);
	}
	
	public Line clone() {
		Line cloned = new Line();
		Point startPoint = new Point(this.getStartPoint().getX(), this.getStartPoint().getY());
		cloned.setStartPoint(startPoint);
		Point endPoint = new Point(this.getEndPoint().getX(), this.getEndPoint().getY());
		cloned.setEndPoint(endPoint);
		cloned.setColor(this.getColor());
		cloned.setSelected(this.isSelected());
		
		return cloned;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int)(this.length() - ((Line) o).length());
		}
		return 0;
	}
	 
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}
	
	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point middlePoint = new Point(middleByX, middleByY);
		return middlePoint;
	}
	
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());
	
		if (isSelected()) {
			g.setColor(Color.GREEN);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
			g.drawRect(this.middleOfLine().getX() - 3, this.middleOfLine().getY() - 3, 6, 6);
		}
	
	}
	
	public boolean contains(int x, int y) {
		if (startPoint.distance(x, y) + endPoint.distance(x, y) - length() <= 0.05) {
			return true;
		} else {
			return false;
		}
	}	
	
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line pomocna = (Line) obj;
			if (this.startPoint.equals(pomocna.startPoint) && this.endPoint.equals(pomocna.endPoint)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	public String toString() {
		//(x,y) --> (x,y)
		return startPoint + "-->" + endPoint; //startPoint.toString()
	}
	
}