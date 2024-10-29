package logs;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import adapter_pattern.CustomHexagon;
import command_pattern.AddShapeCommand;
import command_pattern.BringToBackCommand;
import command_pattern.BringToFrontCommand;
import command_pattern.Command;
import command_pattern.DeleteShapesCommand;
import command_pattern.DeselectShapesCommand;
import command_pattern.EditCircleCommand;
import command_pattern.EditDonutCommand;
import command_pattern.EditHexagonCommand;
import command_pattern.EditLineCommand;
import command_pattern.EditPointCommand;
import command_pattern.EditRectangleCommand;
import command_pattern.HelpRedoCommand;
import command_pattern.HelpUndoCommand;
import command_pattern.SelectShapeCommand;
import command_pattern.ToBackCommand;
import command_pattern.ToFrontCommand;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import mvc.Model;

public class LogParser {

	private ArrayList<String> fileLines = new ArrayList<>();
	private Model model;
	
	public void addFileLine(String s) {
		fileLines.add(s);
	}
	
	public Command parseCommand(LogContext logContext) {
		Command command = null;
		
		String line = fileLines.get(0);
		logContext.setLogHolder(line);
		String[] lineParts = line.split("/");
		
		// _________ Add ____________
		if (lineParts[0].equals("Add")) {
			if (lineParts[1].equals("Point")) {
				Point p = parsePoint(lineParts[2]);
				command = new AddShapeCommand(model, p);
			} else if (lineParts[1].equals("Line")) {
				Line l = parseLine(lineParts[2]);
				command = new AddShapeCommand(model, l);
			} else if (lineParts[1].equals("Rectangle")) {
				Rectangle r = parseRectangle(lineParts[2]);
				command = new AddShapeCommand(model, r);
			} else if (lineParts[1].equals("Donut")) {
				Donut d = parseDonut(lineParts[2]);
				command = new AddShapeCommand(model, d);
			} else if (lineParts[1].equals("Circle")) {
				Circle c = parseCircle(lineParts[2]);
				command = new AddShapeCommand(model, c);
			} else if (lineParts[1].equals("Hexagon")) {
				CustomHexagon h = parseHexagon(lineParts[2]);
				command = new AddShapeCommand(model, h);
			}
		}
		
		// _________ Edit ____________
		if (lineParts[0].equals("Edit")) {
			if (lineParts[1].equals("Point")) {
	            Point originalP = parsePoint(lineParts[2]);
	            Point fromModel = (Point)findMatchingShape(originalP);
	            Point newPoint = parsePoint(lineParts[3]);
	            command = new EditPointCommand(fromModel, newPoint);
			} else if (lineParts[1].equals("Line")) {
				Line originalL = parseLine(lineParts[2]);
				Line fromModel = (Line)findMatchingShape(originalL);
				Line newLine = parseLine(lineParts[3]);
	            command = new EditLineCommand(fromModel, newLine);
			} else if (lineParts[1].equals("Rectangle")) {
				Rectangle originalR = parseRectangle(lineParts[2]);
				Rectangle fromModel = (Rectangle)findMatchingShape(originalR);
				Rectangle newRectangle = parseRectangle(lineParts[3]);
	            command = new EditRectangleCommand(fromModel, newRectangle);
			} else if (lineParts[1].equals("Donut")) {
				Donut originalD = parseDonut(lineParts[2]);
				Donut fromModel = (Donut)findMatchingShape(originalD);
				Donut newDonut = parseDonut(lineParts[3]);
	            command = new EditDonutCommand(fromModel, newDonut);
			} else if (lineParts[1].equals("Circle")) {
				Circle originalC = parseCircle(lineParts[2]);
				Circle fromModel = (Circle)findMatchingShape(originalC);
				Circle newCircle = parseCircle(lineParts[3]);
	            command = new EditCircleCommand(fromModel, newCircle);
			} else if (lineParts[1].equals("Hexagon")) {
				CustomHexagon originalH = parseHexagon(lineParts[2]);
				CustomHexagon fromModel = (CustomHexagon)findMatchingShape(originalH);
				CustomHexagon newHexagon = parseHexagon(lineParts[3]);
	            command = new EditHexagonCommand(fromModel, newHexagon);
			}
		}
		
		// _________ Select ____________
		if (lineParts[0].equals("Select")) {
			if (lineParts[1].equals("Point")) {
				Point p = parsePoint(lineParts[2]);
				Point fromModel = (Point)findMatchingShape(p);
				command = new SelectShapeCommand(fromModel);
			} else if (lineParts[1].equals("Line")) {
				Line l = parseLine(lineParts[2]);
				Line fromModel = (Line)findMatchingShape(l);
				command = new SelectShapeCommand(fromModel);
			} else if (lineParts[1].equals("Rectangle")) {
				Rectangle r = parseRectangle(lineParts[2]);
				Rectangle fromModel = (Rectangle)findMatchingShape(r);
				command = new SelectShapeCommand(fromModel);
			} else if (lineParts[1].equals("Donut")) {
				Donut d = parseDonut(lineParts[2]);
				Donut fromModel = (Donut)findMatchingShape(d);
				command = new SelectShapeCommand(fromModel);
			} else if (lineParts[1].equals("Circle")) {
				Circle c = parseCircle(lineParts[2]);
				Circle fromModel = (Circle)findMatchingShape(c);
				command = new SelectShapeCommand(fromModel);
			} else if (lineParts[1].equals("Hexagon")) {
				CustomHexagon h = parseHexagon(lineParts[2]);
				CustomHexagon fromModel = (CustomHexagon)findMatchingShape(h);
				command = new SelectShapeCommand(fromModel);
			}
		}
		
		// _________ Deselect ____________
		if (lineParts[0].equals("Deselect")) {
			int i = 1;
			ArrayList<Shape> shapes = new ArrayList<>();
		    while (i < lineParts.length) {
		        if (lineParts[i].equals("Point")) {
		            Point p = parsePoint(lineParts[i + 1]);
		            Point fromModel = (Point)findMatchingShape(p);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Line")) {
		            Line l = parseLine(lineParts[i + 1]);
		            Line fromModel = (Line)findMatchingShape(l);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Rectangle")) {
		            Rectangle r = parseRectangle(lineParts[i + 1]);
		            Rectangle fromModel = (Rectangle)findMatchingShape(r);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Donut")) {
		            Donut d = parseDonut(lineParts[i + 1]);
		            Donut fromModel = (Donut)findMatchingShape(d);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Circle")) {
		            Circle c = parseCircle(lineParts[i + 1]);
		            Circle fromModel = (Circle)findMatchingShape(c);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Hexagon")) {
		            CustomHexagon h = parseHexagon(lineParts[i + 1]);
		            CustomHexagon fromModel = (CustomHexagon)findMatchingShape(h);
		            shapes.add(fromModel);
		        } else {
		            break;
		        }
		        i+=2;
		    }
		    command = new DeselectShapesCommand(shapes);
		}
		
		// _________ Delete ____________
		if (lineParts[0].equals("Delete")) {
			int i = 1;
			ArrayList<Shape> shapes = new ArrayList<>();
		    while (i < lineParts.length) {
		        if (lineParts[i].equals("Point")) {
		            Point p = parsePoint(lineParts[i + 1]);
		            Point fromModel = (Point)findMatchingShape(p);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Line")) {
		            Line l = parseLine(lineParts[i + 1]);
		            Line fromModel = (Line)findMatchingShape(l);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Rectangle")) {
		            Rectangle r = parseRectangle(lineParts[i + 1]);
		            Rectangle fromModel = (Rectangle)findMatchingShape(r);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Donut")) {
		            Donut d = parseDonut(lineParts[i + 1]);
		            Donut fromModel = (Donut)findMatchingShape(d);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Circle")) {
		            Circle c = parseCircle(lineParts[i + 1]);
		            Circle fromModel = (Circle)findMatchingShape(c);
		            shapes.add(fromModel);
		        } else if (lineParts[i].equals("Hexagon")) {
		            CustomHexagon h = parseHexagon(lineParts[i + 1]);
		            CustomHexagon fromModel = (CustomHexagon)findMatchingShape(h);
		            shapes.add(fromModel);
		        } else {
		            break;
		        }
		        i+=2;
		    }
		    command = new DeleteShapesCommand(model, shapes);
		}
		
		// _________ Move To Front and Back ____________
		if (lineParts[0].equals("ToFront")) {
			if (lineParts[1].equals("Point")) {
				Point p = parsePoint(lineParts[2]);
				Point fromModel = (Point)findMatchingShape(p);
				int index = findIndexOfMatchingShape(p);
				command = new ToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Line")) {
				Line l = parseLine(lineParts[2]);
				Line fromModel = (Line)findMatchingShape(l);
				int index = findIndexOfMatchingShape(l);
				command = new ToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Rectangle")) {
				Rectangle r = parseRectangle(lineParts[2]);
				Rectangle fromModel = (Rectangle)findMatchingShape(r);
				int index = findIndexOfMatchingShape(r);
				command = new ToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Donut")) {
				Donut d = parseDonut(lineParts[2]);
				Donut fromModel = (Donut)findMatchingShape(d);
				int index = findIndexOfMatchingShape(d);
				command = new ToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Circle")) {
				Circle c = parseCircle(lineParts[2]);
				Circle fromModel = (Circle)findMatchingShape(c);
				int index = findIndexOfMatchingShape(c);
				command = new ToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Hexagon")) {
				CustomHexagon h = parseHexagon(lineParts[2]);
				CustomHexagon fromModel = (CustomHexagon)findMatchingShape(h);
				int index = findIndexOfMatchingShape(h);
				command = new ToFrontCommand(index, fromModel, model);
			}
		}
		
		if (lineParts[0].equals("ToBack")) {
			if (lineParts[1].equals("Point")) {
				Point p = parsePoint(lineParts[2]);
				Point fromModel = (Point)findMatchingShape(p);
				int index = findIndexOfMatchingShape(p);
				command = new ToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Line")) {
				Line l = parseLine(lineParts[2]);
				Line fromModel = (Line)findMatchingShape(l);
				int index = findIndexOfMatchingShape(l);
				command = new ToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Rectangle")) {
				Rectangle r = parseRectangle(lineParts[2]);
				Rectangle fromModel = (Rectangle)findMatchingShape(r);
				int index = findIndexOfMatchingShape(r);
				command = new ToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Donut")) {
				Donut d = parseDonut(lineParts[2]);
				Donut fromModel = (Donut)findMatchingShape(d);
				int index = findIndexOfMatchingShape(d);
				command = new ToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Circle")) {
				Circle c = parseCircle(lineParts[2]);
				Circle fromModel = (Circle)findMatchingShape(c);
				int index = findIndexOfMatchingShape(c);
				command = new ToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Hexagon")) {
				CustomHexagon h = parseHexagon(lineParts[2]);
				CustomHexagon fromModel = (CustomHexagon)findMatchingShape(h);
				int index = findIndexOfMatchingShape(h);
				command = new ToBackCommand(index, fromModel, model);
			}
		}
		
		// _________ Bring To Front and Back ____________
		if (lineParts[0].equals("BringToFront")) {
			if (lineParts[1].equals("Point")) {
				Point p = parsePoint(lineParts[2]);
				Point fromModel = (Point)findMatchingShape(p);
				int index = findIndexOfMatchingShape(p);
				command = new BringToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Line")) {
				Line l = parseLine(lineParts[2]);
				Line fromModel = (Line)findMatchingShape(l);
				int index = findIndexOfMatchingShape(l);
				command = new BringToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Rectangle")) {
				Rectangle r = parseRectangle(lineParts[2]);
				Rectangle fromModel = (Rectangle)findMatchingShape(r);
				int index = findIndexOfMatchingShape(r);
				command = new BringToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Donut")) {
				Donut d = parseDonut(lineParts[2]);
				Donut fromModel = (Donut)findMatchingShape(d);
				int index = findIndexOfMatchingShape(d);
				command = new BringToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Circle")) {
				Circle c = parseCircle(lineParts[2]);
				Circle fromModel = (Circle)findMatchingShape(c);
				int index = findIndexOfMatchingShape(c);
				command = new BringToFrontCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Hexagon")) {
				CustomHexagon h = parseHexagon(lineParts[2]);
				CustomHexagon fromModel = (CustomHexagon)findMatchingShape(h);
				int index = findIndexOfMatchingShape(h);
				command = new BringToFrontCommand(index, fromModel, model);
			}
		}
		
		if (lineParts[0].equals("BringToBack")) {
			if (lineParts[1].equals("Point")) {
				Point p = parsePoint(lineParts[2]);
				Point fromModel = (Point)findMatchingShape(p);
				int index = findIndexOfMatchingShape(p);
				command = new BringToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Line")) {
				Line l = parseLine(lineParts[2]);
				Line fromModel = (Line)findMatchingShape(l);
				int index = findIndexOfMatchingShape(l);
				command = new BringToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Rectangle")) {
				Rectangle r = parseRectangle(lineParts[2]);
				Rectangle fromModel = (Rectangle)findMatchingShape(r);
				int index = findIndexOfMatchingShape(r);
				command = new BringToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Donut")) {
				Donut d = parseDonut(lineParts[2]);
				Donut fromModel = (Donut)findMatchingShape(d);
				int index = findIndexOfMatchingShape(d);
				command = new BringToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Circle")) {
				Circle c = parseCircle(lineParts[2]);
				Circle fromModel = (Circle)findMatchingShape(c);
				int index = findIndexOfMatchingShape(c);
				command = new BringToBackCommand(index, fromModel, model);
			} else if (lineParts[1].equals("Hexagon")) {
				CustomHexagon h = parseHexagon(lineParts[2]);
				CustomHexagon fromModel = (CustomHexagon)findMatchingShape(h);
				int index = findIndexOfMatchingShape(h);
				command = new BringToBackCommand(index, fromModel, model);
			}
		}
		// _________ Undo ____________
		if (lineParts[0].equals("Undo")) {
		    command = new HelpUndoCommand();
		}
		
		// _________ Redo ____________
		if (lineParts[0].equals("Redo")) {
			command = new HelpRedoCommand();
		}

		fileLines.remove(0);
		
		return command;
	}
	
	
	// _________ Parse shapes ____________
	
	private Point parsePoint(String pointText) {
		String x = pointText.split(",")[0];
		String y = pointText.split(",")[1];
		String selected = pointText.split(",")[2];
		String color = pointText.split(",")[3];
		
		int xInt = Integer.parseInt(x);
		int yInt = Integer.parseInt(y);
		boolean selectedBool = Boolean.parseBoolean(selected);
		Color colorCol = new Color(Integer.parseInt(color));
		
		Point p = new Point(xInt, yInt, selectedBool, colorCol);
		return p;
	}
	
	private Line parseLine(String lineText) {
		String x = lineText.split(",")[0];
		String y = lineText.split(",")[1];
		String x2 = lineText.split(",")[2];
		String y2 = lineText.split(",")[3];
		String selected = lineText.split(",")[4];
		String color = lineText.split(",")[5];
		
		int xInt = Integer.parseInt(x);
		int yInt = Integer.parseInt(y);
		int x2Int = Integer.parseInt(x2);
		int y2Int = Integer.parseInt(y2);
		boolean selectedBool = Boolean.parseBoolean(selected);
		Color colorCol = new Color(Integer.parseInt(color));
		
		Line l = new Line(new Point(xInt, yInt),new Point(x2Int, y2Int), selectedBool, colorCol);
		return l;
	}
	
	private Rectangle parseRectangle(String rectangleText) {
		String x = rectangleText.split(",")[0];
		String y = rectangleText.split(",")[1];
		String w = rectangleText.split(",")[2];
		String h = rectangleText.split(",")[3];
		String selected = rectangleText.split(",")[4];
		String color = rectangleText.split(",")[5];
		String inColor = rectangleText.split(",")[6];
		
		int xInt = Integer.parseInt(x);
		int yInt = Integer.parseInt(y);
		int wInt = Integer.parseInt(w);
		int hInt = Integer.parseInt(h);
		boolean selectedBool = Boolean.parseBoolean(selected);
		Color colorCol = new Color(Integer.parseInt(color));
		Color inColorCol = new Color(Integer.parseInt(inColor));
		
		Rectangle r = new Rectangle(new Point(xInt,yInt), wInt, hInt, selectedBool, colorCol,inColorCol);
		return r;
	}
	
	private Donut parseDonut(String donutText) {
		String x = donutText.split(",")[0];
		String y = donutText.split(",")[1];
		String r = donutText.split(",")[2];
		String inR = donutText.split(",")[3];
		String selected = donutText.split(",")[4];
		String color = donutText.split(",")[5];
		String inColor = donutText.split(",")[6];
		
		int xInt = Integer.parseInt(x);
		int yInt = Integer.parseInt(y);
		int rInt = Integer.parseInt(r);
		int inRInt = Integer.parseInt(inR);
		boolean selectedBool = Boolean.parseBoolean(selected);
		Color colorCol = new Color(Integer.parseInt(color));
		Color inColorCol = new Color(Integer.parseInt(inColor));
		
		Donut d = new Donut(new Point(xInt, yInt), rInt, inRInt, selectedBool, colorCol, inColorCol);
		return d;
	}
	
	private Circle parseCircle(String circleText) {
		String x = circleText.split(",")[0];
		String y = circleText.split(",")[1];
		String r = circleText.split(",")[2];
		String selected = circleText.split(",")[3];
		String color = circleText.split(",")[4];
		String inColor = circleText.split(",")[5];
		
		int xInt = Integer.parseInt(x);
		int yInt = Integer.parseInt(y);
		int rInt = Integer.parseInt(r);
		boolean selectedBool = Boolean.parseBoolean(selected);
		Color colorCol = new Color(Integer.parseInt(color));
		Color inColorCol = new Color(Integer.parseInt(inColor));
		
		Circle c = new Circle(new Point(xInt, yInt), rInt, selectedBool, colorCol, inColorCol);
		return c;
	}
	
	private CustomHexagon parseHexagon(String hexagonText) {
		String x = hexagonText.split(",")[0];
		String y = hexagonText.split(",")[1];
		String r = hexagonText.split(",")[2];
		String selected = hexagonText.split(",")[3];
		String color = hexagonText.split(",")[4];
		String inColor = hexagonText.split(",")[5];
		
		int xInt = Integer.parseInt(x);
		int yInt = Integer.parseInt(y);
		int rInt = Integer.parseInt(r);
		boolean selectedBool = Boolean.parseBoolean(selected);
		Color colorCol = new Color(Integer.parseInt(color));
		Color inColorCol = new Color(Integer.parseInt(inColor));
		
		CustomHexagon h = new CustomHexagon(xInt, yInt, rInt, selectedBool, colorCol, inColorCol);
		return h;
	}
	
	
	// _________ Find matching shape ____________
	
	private Shape findMatchingShape(Shape shape) {
		Iterator<Shape> it = model.getAllShapes().iterator();
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.equals(shape)) {
				return helpShape;
			}
		}
		
		return null;
	}
	
	// _________ Find index of matching shape ____________
	private int findIndexOfMatchingShape(Shape shape) {
		Iterator<Shape> it = model.getAllShapes().iterator();
		int index = 0;
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.equals(shape)) {
				return index;
			}
			index++;
		}
		
		return 0;
	}
	
	public void setModel(Model model) {
		this.model = model;
	}
}
