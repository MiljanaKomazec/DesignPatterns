package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle { //Circle je parent klasa, a Donut child klasa
									
	private int innerRadius;
	
	public Donut() {
		
	}
	
	public Donut(Point center, int radius, int innerRadius) {
		super(center, radius);
		this.innerRadius = innerRadius;
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected) {
		this(center, radius, innerRadius);
		setSelected(selected); // pre je bilo this.setSelected(selected);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color) {
		this(center, radius, innerRadius, selected);
		this.setColor(color);
	}
	
	public Donut(Point center, int radius, int innerRadius, boolean selected, Color color, Color innerColor) {
		this(center, radius, innerRadius, selected, color);
		this.setInnerColor(innerColor);
	}
	
	public Donut clone() {
		Donut cloned = new Donut();
		Point center = new Point(this.getCenter().getX(), this.getCenter().getY());
		cloned.setCenter(center);
		try {
			cloned.setRadius(this.getRadius());
			cloned.setInnerRadius(this.getInnerRadius());
		} catch (Exception e) {
			e.printStackTrace();
		}
		cloned.setColor(this.getColor());
		cloned.setInnerColor(this.getInnerColor());
		cloned.setSelected(this.isSelected());
		
		return cloned;
	}
	
	public int compareTo(Object o) {
		if (o instanceof Donut) {
			return (int)(this.area() - ((Donut) o).area());
		}
		return 0;
	}
	
	public void fill(Graphics g) {
	}
	
	public void draw(Graphics g) {
		Graphics2D newGraphics = (Graphics2D)g.create();
		int upperLeftX = getCenter().getX() - getRadius();
		int upperLeftY = getCenter().getY() - getRadius();
		int length = 2 * getRadius();
		Shape outElipse = new Ellipse2D.Double(upperLeftX, upperLeftY, length, length);
		
		upperLeftX = getCenter().getX() - innerRadius;
		upperLeftY = getCenter().getY() - innerRadius;
		length = 2 * innerRadius;
		Shape insideElipse = new Ellipse2D.Double(upperLeftX, upperLeftY, length, length);
		
		Area donutArea = new Area(outElipse);
		Area donutInsideArea = new Area(insideElipse);
		
		donutArea.subtract(donutInsideArea);
		
		newGraphics.setColor(getInnerColor());
		newGraphics.fill(donutArea);
		
		newGraphics.setColor(getColor());
		newGraphics.draw(donutArea);
		
		newGraphics.dispose();
		
		if (isSelected()) {
			g.setColor(Color.GREEN);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() + this.getRadius() - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - this.getRadius() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + this.getRadius() - 3, 6, 6);
			
			g.drawRect(this.getCenter().getX() - innerRadius - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() + innerRadius - 3, this.getCenter().getY() - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() - innerRadius - 3, 6, 6);
			g.drawRect(this.getCenter().getX() - 3, this.getCenter().getY() + innerRadius - 3, 6, 6);
		}
	}

	public double area() {
		return super.area() - innerRadius * innerRadius * Math.PI;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut pomocna = (Donut) obj;
			if (this.innerRadius == pomocna.innerRadius && this.getRadius() == pomocna.getRadius() && this.getCenter().equals(pomocna.getCenter())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public boolean contains(int x, int y) {
		double dFromCenter = this.getCenter().distance(x, y);
		return super.contains(x, y) && dFromCenter > innerRadius;
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p.getX(), p.getY());
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	public int getInnerRadius() {
		return innerRadius;
	}
	
	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	public String toString() {
		return super.toString() + ", inner radius=" + innerRadius;
	}
	
}