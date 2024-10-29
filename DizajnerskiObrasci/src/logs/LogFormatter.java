package logs;

import java.util.Iterator;

import adapter_pattern.CustomHexagon;
import command_pattern.AddShapeCommand;
import command_pattern.BringToBackCommand;
import command_pattern.BringToFrontCommand;
import command_pattern.DeleteShapesCommand;
import command_pattern.DeselectShapesCommand;
import command_pattern.EditCircleCommand;
import command_pattern.EditDonutCommand;
import command_pattern.EditHexagonCommand;
import command_pattern.EditLineCommand;
import command_pattern.EditPointCommand;
import command_pattern.EditRectangleCommand;
import command_pattern.SelectShapeCommand;
import command_pattern.ToBackCommand;
import command_pattern.ToFrontCommand;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

public class LogFormatter {

	// _________ Draw shapes logs  ____________
	
	public String formatLog(AddShapeCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Add/");
		if (command.getInitialState() instanceof Point) {
			builder.append("Point/");
			builder.append(formatPoint((Point)command.getInitialState()));
			return builder.toString();
		} else if (command.getInitialState() instanceof Line) {
			builder.append("Line/");
			builder.append(formatLine((Line)command.getInitialState()));
			return builder.toString();
		} else if (command.getInitialState() instanceof Rectangle) {
			builder.append("Rectangle/");
			builder.append(formatRectangle((Rectangle)command.getInitialState()));
			return builder.toString();
		} else if (command.getInitialState() instanceof Donut) {
			builder.append("Donut/");
			builder.append(formatDonut((Donut)command.getInitialState()));
			return builder.toString();
		} else if (command.getInitialState() instanceof Circle) {
			builder.append("Circle/");
			builder.append(formatCircle((Circle)command.getInitialState()));
			return builder.toString();
		} else if (command.getInitialState() instanceof CustomHexagon) {
			builder.append("Hexagon/");
			builder.append(formatHexagon((CustomHexagon)command.getInitialState()));
			return builder.toString();
		}
		
		return null;
	}
	
	
	// _________ Edit shapes logs  ____________
	
	public String formatLog(EditPointCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Edit/");
		builder.append("Point/");
		builder.append(formatPoint((Point)command.getSavedState()));
		builder.append("/");
		builder.append(formatPoint((Point)command.getStateFromDialog()));
		return builder.toString();
	}
	
	public String formatLog(EditLineCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Edit/");
		builder.append("Line/");
		builder.append(formatLine((Line)command.getSavedState()));
		builder.append("/");
		builder.append(formatLine((Line)command.getStateFromDialog()));
		return builder.toString();
	}
	
	public String formatLog(EditRectangleCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Edit/");
		builder.append("Rectangle/");
		builder.append(formatRectangle((Rectangle)command.getSavedState()));
		builder.append("/");
		builder.append(formatRectangle((Rectangle)command.getStateFromDialog()));
		return builder.toString();
	}
	
	public String formatLog(EditDonutCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Edit/");
		builder.append("Donut/");
		builder.append(formatDonut((Donut)command.getSavedState()));
		builder.append("/");
		builder.append(formatDonut((Donut)command.getStateFromDialog()));
		return builder.toString();
	}
	
	public String formatLog(EditCircleCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Edit/");
		builder.append("Circle/");
		builder.append(formatCircle((Circle)command.getSavedState()));
		builder.append("/");
		builder.append(formatCircle((Circle)command.getStateFromDialog()));
		return builder.toString();
	}
	
	public String formatLog(EditHexagonCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Edit/");
		builder.append("Hexagon/");
		builder.append(formatHexagon((CustomHexagon)command.getSavedState()));
		builder.append("/");
		builder.append(formatHexagon((CustomHexagon)command.getStateFromDialog()));
		return builder.toString();
	}
	
	
	// _________ Select shapes logs  ____________
	
	public String formatLog(SelectShapeCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Select/");
		if (command.getShape() instanceof Point) {
			builder.append("Point/");
			builder.append(formatPoint((Point)command.getInitialState()));
			return builder.toString();
		} else if (command.getShape() instanceof Line) {
			builder.append("Line/");
			builder.append(formatLine((Line)command.getInitialState()));
			return builder.toString();
		} else if (command.getShape() instanceof Rectangle) {
			builder.append("Rectangle/");
			builder.append(formatRectangle((Rectangle)command.getInitialState()));
			return builder.toString();
		} else if (command.getShape() instanceof Donut) {
			builder.append("Donut/");
			builder.append(formatDonut((Donut)command.getInitialState()));
			return builder.toString();
		} else if (command.getShape() instanceof Circle) {
			builder.append("Circle/");
			builder.append(formatCircle((Circle)command.getInitialState()));
			return builder.toString();
		} else if (command.getShape() instanceof CustomHexagon) {
			builder.append("Hexagon/");
			builder.append(formatHexagon((CustomHexagon)command.getInitialState()));
			return builder.toString();
		}
		
		return null;
	}
	
	
	// _________ Deselect shapes logs  ____________
	
	public String formatLog(DeselectShapesCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Deselect/");
		Iterator<Shape> it = command.getSavedState().iterator();
		
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape instanceof Point) {
				builder.append("Point/");
				builder.append(formatPoint((Point)helpShape));
				builder.append("/");
			} else if (helpShape instanceof Line) {
				builder.append("Line/");
				builder.append(formatLine((Line)helpShape));
				builder.append("/");
			} else if (helpShape instanceof Rectangle) {
				builder.append("Rectangle/");
				builder.append(formatRectangle((Rectangle)helpShape));
				builder.append("/");				
			} else if (helpShape instanceof Donut) {
				builder.append("Donut/");
				builder.append(formatDonut((Donut)helpShape));
				builder.append("/");				
			} else if (helpShape instanceof Circle) {
				builder.append("Circle/");
				builder.append(formatCircle((Circle)helpShape));
				builder.append("/");				
			} else if (helpShape instanceof CustomHexagon) {
				builder.append("Hexagon/");
				builder.append(formatHexagon((CustomHexagon)helpShape));
				builder.append("/");				
			}
		}
		return builder.toString(); 
	}
	
	// _________ Delete shapes logs  ____________
	
	public String formatLog(DeleteShapesCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("Delete/");
		Iterator<Shape> it = command.getSavedState().iterator();
		
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape instanceof Point) {
				builder.append("Point/");
				builder.append(formatPoint((Point)helpShape));
				builder.append("/");
			} else if (helpShape instanceof Line) {
				builder.append("Line/");
				builder.append(formatLine((Line)helpShape));
				builder.append("/");
			} else if (helpShape instanceof Rectangle) {
				builder.append("Rectangle/");
				builder.append(formatRectangle((Rectangle)helpShape));
				builder.append("/");				
			} else if (helpShape instanceof Donut) {
				builder.append("Donut/");
				builder.append(formatDonut((Donut)helpShape));
				builder.append("/");				
			} else if (helpShape instanceof Circle) {
				builder.append("Circle/");
				builder.append(formatCircle((Circle)helpShape));
				builder.append("/");				
			} else if (helpShape instanceof CustomHexagon) {
				builder.append("Hexagon/");
				builder.append(formatHexagon((CustomHexagon)helpShape));
				builder.append("/");				
			}
		}
		return builder.toString(); 
	}
	
	
	// _________ Move shapes logs  ____________
	
	public String formatLog(ToFrontCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("ToFront/");
		if (command.getShape() instanceof Point) {
			builder.append("Point/");
			builder.append(formatPoint((Point)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Line) {
			builder.append("Line/");
			builder.append(formatLine((Line)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Rectangle) {
			builder.append("Rectangle/");
			builder.append(formatRectangle((Rectangle)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Donut) {
			builder.append("Donut/");
			builder.append(formatDonut((Donut)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Circle) {
			builder.append("Circle/");
			builder.append(formatCircle((Circle)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof CustomHexagon) {
			builder.append("Hexagon/");
			builder.append(formatHexagon((CustomHexagon)command.getShape()));
			return builder.toString();
		}
		
		return null;
	}
	
	public String formatLog(ToBackCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("ToBack/");
		if (command.getShape() instanceof Point) {
			builder.append("Point/");
			builder.append(formatPoint((Point)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Line) {
			builder.append("Line/");
			builder.append(formatLine((Line)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Rectangle) {
			builder.append("Rectangle/");
			builder.append(formatRectangle((Rectangle)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Donut) {
			builder.append("Donut/");
			builder.append(formatDonut((Donut)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Circle) {
			builder.append("Circle/");
			builder.append(formatCircle((Circle)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof CustomHexagon) {
			builder.append("Hexagon/");
			builder.append(formatHexagon((CustomHexagon)command.getShape()));
			return builder.toString();
		}
		
		return null;
	}
	
	public String formatLog(BringToFrontCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("BringToFront/");
		if (command.getShape() instanceof Point) {
			builder.append("Point/");
			builder.append(formatPoint((Point)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Line) {
			builder.append("Line/");
			builder.append(formatLine((Line)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Rectangle) {
			builder.append("Rectangle/");
			builder.append(formatRectangle((Rectangle)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Donut) {
			builder.append("Donut/");
			builder.append(formatDonut((Donut)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Circle) {
			builder.append("Circle/");
			builder.append(formatCircle((Circle)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof CustomHexagon) {
			builder.append("Hexagon/");
			builder.append(formatHexagon((CustomHexagon)command.getShape()));
			return builder.toString();
		}
		
		return null;
	}
	
	public String formatLog(BringToBackCommand command) {
		StringBuilder builder = new StringBuilder();
		builder.append("BringToBack/");
		if (command.getShape() instanceof Point) {
			builder.append("Point/");
			builder.append(formatPoint((Point)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Line) {
			builder.append("Line/");
			builder.append(formatLine((Line)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Rectangle) {
			builder.append("Rectangle/");
			builder.append(formatRectangle((Rectangle)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Donut) {
			builder.append("Donut/");
			builder.append(formatDonut((Donut)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof Circle) {
			builder.append("Circle/");
			builder.append(formatCircle((Circle)command.getShape()));
			return builder.toString();
		} else if (command.getShape() instanceof CustomHexagon) {
			builder.append("Hexagon/");
			builder.append(formatHexagon((CustomHexagon)command.getShape()));
			return builder.toString();
		}
		
		return null;
	}
	
	
	// _________ Format Shapes ____________
	
	private String formatPoint(Point point) {
		StringBuilder builder = new StringBuilder();
		builder.append(point.getX());
		builder.append(",");
		builder.append(point.getY());
		builder.append(",");
		builder.append(point.isSelected());
		builder.append(",");
		builder.append(point.getColor().getRGB());
		return builder.toString();
	}
	
	private String formatLine(Line line) {
		StringBuilder builder = new StringBuilder();
		builder.append(line.getStartPoint().getX());
		builder.append(",");
		builder.append(line.getStartPoint().getY());
		builder.append(",");
		builder.append(line.getEndPoint().getX());
		builder.append(",");
		builder.append(line.getEndPoint().getY());
		builder.append(",");
		builder.append(line.isSelected());
		builder.append(",");
		builder.append(line.getColor().getRGB());
		return builder.toString();
	}
	
	private String formatRectangle(Rectangle rect) {
		StringBuilder builder = new StringBuilder();
		builder.append(rect.getUpperLeftPoint().getX());
		builder.append(",");
		builder.append(rect.getUpperLeftPoint().getY());
		builder.append(",");
		builder.append(rect.getHeight());
		builder.append(",");
		builder.append(rect.getWidth());
		builder.append(",");
		builder.append(rect.isSelected());
		builder.append(",");
		builder.append(rect.getColor().getRGB());
		builder.append(",");
		builder.append(rect.getInnerColor().getRGB());
		return builder.toString();
	}
	
	private String formatCircle(Circle circle) {
		StringBuilder builder = new StringBuilder();
		builder.append(circle.getCenter().getX());
		builder.append(",");
		builder.append(circle.getCenter().getY());
		builder.append(",");
		builder.append(circle.getRadius());
		builder.append(",");
		builder.append(circle.isSelected());
		builder.append(",");
		builder.append(circle.getColor().getRGB());
		builder.append(",");
		builder.append(circle.getInnerColor().getRGB());
		return builder.toString();
	}
	
	private String formatDonut(Donut donut) {
		StringBuilder builder = new StringBuilder();
		builder.append(donut.getCenter().getX());
		builder.append(",");
		builder.append(donut.getCenter().getY());
		builder.append(",");
		builder.append(donut.getRadius());
		builder.append(",");
		builder.append(donut.getInnerRadius());
		builder.append(",");
		builder.append(donut.isSelected());
		builder.append(",");
		builder.append(donut.getColor().getRGB());
		builder.append(",");
		builder.append(donut.getInnerColor().getRGB());
		return builder.toString();
	}
	
	private String formatHexagon(CustomHexagon hexagon) {
		StringBuilder builder = new StringBuilder();
		builder.append(hexagon.getX());
		builder.append(",");
		builder.append(hexagon.getY());
		builder.append(",");
		builder.append(hexagon.getRadius());
		builder.append(",");
		builder.append(hexagon.isSelected());
		builder.append(",");
		builder.append(hexagon.getBorderColor().getRGB());
		builder.append(",");
		builder.append(hexagon.getAreaColor().getRGB());
		return builder.toString();
	}
}
