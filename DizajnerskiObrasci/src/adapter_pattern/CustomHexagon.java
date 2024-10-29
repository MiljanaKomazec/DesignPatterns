package adapter_pattern;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Circle;
import geometry.Point;
import geometry.SurfaceShape;
import hexagon.Hexagon;

public class CustomHexagon extends SurfaceShape {

	private Hexagon hex = new Hexagon(0, 0, 0);
	
	public CustomHexagon(int x, int y, int r) {
		this.hex.setX(x);
		this.hex.setY(y);
		this.hex.setR(r);
	}
	
	public CustomHexagon(int x, int y, int r, boolean selected) {
		this(x, y, r);
		this.hex.setSelected(selected);
	}
	
	public CustomHexagon(int x, int y, int r, boolean selected, Color color) {
		this(x, y, r, selected);
		this.hex.setBorderColor(color);
	}
	
	public CustomHexagon(int x, int y, int r, boolean selected, Color color, Color innerColor) {
		this(x, y, r, selected,color);
		this.hex.setAreaColor(innerColor);
	}
	
	public CustomHexagon clone() {
        CustomHexagon cloned = new CustomHexagon(
            this.hex.getX(),
            this.hex.getY(),
            this.hex.getR(),
            this.hex.isSelected(),
            this.hex.getBorderColor(),
            this.hex.getAreaColor()
        );

        return cloned;
    }
	
	@Override
	public void moveBy(int byX, int byY) {
		this.hex.setX(this.hex.getX() + byX);
        this.hex.setY(this.hex.getY() + byY);
	}

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public boolean contains(Point p) {
		return hex.doesContain(p.getX(), p.getY());
	}

	@Override
	public void fill(Graphics g) {	
	}

	@Override
	public boolean contains(int x, int y) {
		return hex.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hex.paint(g);
		
		if (isSelected()) {
			g.setColor(Color.GREEN);
			
			int centerX = hex.getX();
            int centerY = hex.getY();
            int radius = hex.getR();

            int[] xPoints = new int[6];
            int[] yPoints = new int[6];

            for (int i = 0; i < 6; i++) {
                xPoints[i] = (int) (centerX + radius * Math.cos(Math.toRadians(60 * i)));
                yPoints[i] = (int) (centerY + radius * Math.sin(Math.toRadians(60 * i)));
            }

            for (int i = 0; i < 6; i++) {
                g.drawRect(xPoints[i] - 3, yPoints[i] - 3, 6, 6);
            }
			
		}
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof CustomHexagon) {
			CustomHexagon pomocna = (CustomHexagon) obj;
			if (this.getX()==pomocna.getX() && this.getY()==pomocna.getY() && this.getRadius() == pomocna.getRadius()) {
				return true;
			} else {
				return false;
			}
		}
		else {
				return false;
		}
	}
	
	public String toString() {
		return "X=" + this.hex.getX() + ", Y=" + this.hex.getY() + ", radius=" + this.hex.getR();
 	}
	
	public int getRadius() {
		return hex.getR();
	}
	
	public int getX() {
		return hex.getX();
	}
	
	public int getY() {
		return hex.getY();
	}
	
	public void setX(int x) {
        this.hex.setX(x);
    }

    public void setY(int y) {
        this.hex.setY(y);
    }

    public void setRadius(int r) {
        this.hex.setR(r);
    }
    
    public Color getBorderColor() {
        return hex.getBorderColor();
    }

    public void setBorderColor(Color color) {
        this.hex.setBorderColor(color);
    }

    public Color getAreaColor() {
        return hex.getAreaColor();
    }

    public void setAreaColor(Color color) {
        this.hex.setAreaColor(color);
    }

}
