package mvc;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import adapter_pattern.CustomHexagon;
import command_pattern.AddShapeCommand;
import command_pattern.BringToBackCommand;
import command_pattern.BringToFrontCommand;
import command_pattern.Command;
import command_pattern.CommandManager;
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
import drawing.DlgCircle;
import drawing.DlgDelete;
import drawing.DlgDonut;
import drawing.DlgHexagon;
import drawing.DlgLine;
import drawing.DlgPoint;
import drawing.DlgRectangle;
import drawing.FrmDrawing;
import geometry.Circle;
import geometry.Donut;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import logs.LogContext;
import logs.LogFormatter;
import logs.LogParser;
import observer_pattern.Observable;
import observer_pattern.Observer;
import strategy_pattern.Context;
import strategy_pattern.StrategySerialized;
import strategy_pattern.StrategyTextual;

public class Controller implements Observable {
	
	Shape temp;
	Point startPoint;
	Point upperLeftPoint;
	private Color innerColorCircle;
	private Color innerColorHexagon;
	private Color borderColorHexagon;
	private Color innerColorRectangle;
	private Color borderColorRectangle;
	private Color borderColorCircle;
	private Color innerColorDonut;
	private Color borderColorDonut;
	private Color pointColor;
	private Color lineColor;
	
	private Context context = new Context();
	private LogFormatter logFormatter = new LogFormatter();
	private LogParser logParser = new LogParser();
	
	private Model model;
	private FrmDrawing frmDrawing;
	
	private CommandManager commandManager = new CommandManager();
	
	private ArrayList<Command> allExecutedCommands = new ArrayList<>();
	private ArrayList<Observer> registeredObservers = new ArrayList<>();
	
	private int remainingLines;
	
	public Controller(Model model) {
		this.model = model;
		logParser.setModel(model);
	}

	// _________ Modify ____________

	public void modifyShape() {
		for (int i = 0; i < model.getAllShapes().size(); i++) {
			if (model.getAllShapes().get(i) instanceof Point) {
				if (model.getAllShapes().get(i).isSelected()) {
					DlgPoint dlgPoint = new DlgPoint();
					Point point = (Point) model.getAllShapes().get(i);
					dlgPoint.getTxtX().setText(Integer.toString(point.getX()));
					dlgPoint.getTxtY().setText(Integer.toString(point.getY()));
					dlgPoint.setFill(point.getColor());
					dlgPoint.setVisible(true);

					if (dlgPoint.isConfirmation()) {
						int x = Integer.parseInt(dlgPoint.getTxtX().getText());
						int y = Integer.parseInt(dlgPoint.getTxtY().getText());
						if (dlgPoint.isColorConfirmation()) {
							pointColor = dlgPoint.getFill();
						} else {
							pointColor = point.getColor();
						}
						temp = new Point(x, y, false, pointColor);
						EditPointCommand command = new EditPointCommand(point, (Point)temp);
						command.execute();
						allExecutedCommands.add(command);
						commandManager.addCommand(command);
						notifyObservers();
						enableDisableButtons();
						frmDrawing.getDlm().remove(i);
						frmDrawing.getDlm().addElement("Modified point " + command.getSavedState().toString() + " => " + temp.toString());
						frmDrawing.repaint();
					}
				}
			} else if (model.getAllShapes().get(i) instanceof Line) {
				if (model.getAllShapes().get(i).isSelected()) {
					DlgLine dlgLine = new DlgLine();
					Line line = (Line) model.getAllShapes().get(i);
					dlgLine.getTxtStartPointX().setText(Integer.toString(line.getStartPoint().getX()));
					dlgLine.getTxtStartPointY().setText(Integer.toString(line.getStartPoint().getY()));
					dlgLine.getTxtEndPointX().setText(Integer.toString(line.getEndPoint().getX()));
					dlgLine.getTxtEndPointY().setText(Integer.toString(line.getEndPoint().getY()));
					dlgLine.setFill(line.getColor());
					dlgLine.setVisible(true);

					if (dlgLine.isConfirmation()) {
						int startX = Integer.parseInt(dlgLine.getTxtStartPointX().getText());
						int startY = Integer.parseInt(dlgLine.getTxtStartPointY().getText());
						int endX = Integer.parseInt(dlgLine.getTxtEndPointX().getText());
						int endY = Integer.parseInt(dlgLine.getTxtEndPointY().getText());
						if (dlgLine.isColorConfirmation()) {
							lineColor = dlgLine.getFill();
						} else {
							lineColor = line.getColor();
						}
						temp = new Line(new Point(startX, startY), new Point(endX, endY), false, lineColor);
						EditLineCommand command = new EditLineCommand(line, (Line)temp);
						command.execute();
						allExecutedCommands.add(command);
						commandManager.addCommand(command);
						notifyObservers();
						enableDisableButtons();
						frmDrawing.getDlm().removeElementAt(i);
						frmDrawing.getDlm().addElement("Modified line " + command.getSavedState().toString() + " => " + temp.toString());
						frmDrawing.repaint();
					}
				}
			} else if (model.getAllShapes().get(i) instanceof Rectangle) {
				if (model.getAllShapes().get(i).isSelected()) {
					Rectangle r = (Rectangle) model.getAllShapes().get(i);
					DlgRectangle dlgRectangle = new DlgRectangle();
					dlgRectangle.getTxtX().setText(Integer.toString(r.getUpperLeftPoint().getX()));
					dlgRectangle.getTxtY().setText(Integer.toString(r.getUpperLeftPoint().getY()));
					dlgRectangle.getTxtHeight().setText(Integer.toString(r.getHeight()));
					dlgRectangle.getTxtWidth().setText(Integer.toString(r.getWidth()));
					dlgRectangle.setInnerFill(r.getInnerColor());
					dlgRectangle.setBorderFill(r.getColor());
					dlgRectangle.setVisible(true);

					if (dlgRectangle.isConfirmation()) {
						int x = Integer.parseInt(dlgRectangle.getTxtX().getText());
						int y = Integer.parseInt(dlgRectangle.getTxtY().getText());
						int width = Integer.parseInt(dlgRectangle.getTxtWidth().getText());
						int height = Integer.parseInt(dlgRectangle.getTxtHeight().getText());
						if (dlgRectangle.isInnerColorConfirmation()) {
							innerColorRectangle = dlgRectangle.getInnerFill();
						} else {
							innerColorRectangle = r.getInnerColor();
						}
						if (dlgRectangle.isBorderColorConfirmation()) {
							borderColorRectangle = dlgRectangle.getBorderFill();
						} else {
							borderColorRectangle = r.getColor();
						}
						if (width > 0 && height > 0) {
							temp = new Rectangle(new Point(x, y), width, height, false, borderColorRectangle,
									innerColorRectangle);
							EditRectangleCommand command = new EditRectangleCommand(r, (Rectangle)temp);
							command.execute();
							allExecutedCommands.add(command);
							commandManager.addCommand(command);
							notifyObservers();
							enableDisableButtons();
							frmDrawing.getDlm().removeElementAt(i);
							frmDrawing.getDlm().addElement("Modified rectangle " + command.getSavedState().toString() + " => " + temp.toString());
						} else {
							JOptionPane.showMessageDialog(null, "The width and height must be greather than zero!");
						}
						frmDrawing.repaint();
					}
				}
			} else if (model.getAllShapes().get(i) instanceof Donut) {
				if (model.getAllShapes().get(i).isSelected()) {
					Donut donut = (Donut) model.getAllShapes().get(i);
					DlgDonut dlgDonut = new DlgDonut();
					dlgDonut.getTxtX().setText(Integer.toString(donut.getCenter().getX()));
					dlgDonut.getTxtY().setText(Integer.toString(donut.getCenter().getY()));
					dlgDonut.getTxtInnerRadius().setText(Integer.toString(donut.getInnerRadius()));
					dlgDonut.getTxtRadius().setText(Integer.toString(donut.getRadius()));
					dlgDonut.setInnerFill(donut.getInnerColor());
					dlgDonut.setBorderFill(donut.getColor());
					dlgDonut.setVisible(true);

					if (dlgDonut.isConfirmation()) {
						int x = Integer.parseInt(dlgDonut.getTxtX().getText());
						int y = Integer.parseInt(dlgDonut.getTxtY().getText());
						int r = Integer.parseInt(dlgDonut.getTxtRadius().getText());
						int innerR = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
						if (dlgDonut.isInnerColorConfirmation()) {
							innerColorDonut = dlgDonut.getInnerFill();
						} else {
							innerColorDonut = donut.getInnerColor();
						}
						if (dlgDonut.isBorderColorConfirmation()) {
							borderColorDonut = dlgDonut.getBorderFill();
						} else {
							borderColorDonut = donut.getColor();
						}
						if (innerR < r) {
							temp = new Donut(new Point(x, y), r, innerR, false, borderColorDonut, innerColorDonut);
							EditDonutCommand command = new EditDonutCommand(donut, (Donut)temp);
							command.execute();
							allExecutedCommands.add(command);
							commandManager.addCommand(command);
							notifyObservers();
							enableDisableButtons();
							frmDrawing.getDlm().remove(i);
							frmDrawing.getDlm().addElement("Modified donut " + command.getSavedState().toString() + " => " + temp.toString());
						} else {
							JOptionPane.showMessageDialog(null,
									"The radius mast be greather than inner radius or inner radius mast be greather than zero!");
						}
						frmDrawing.repaint();
					}
				}
			} else if (model.getAllShapes().get(i) instanceof Circle) {
				if (model.getAllShapes().get(i).isSelected()) {
					Circle c = (Circle) model.getAllShapes().get(i);
					DlgCircle dlgCircle = new DlgCircle();
					dlgCircle.getTxtX().setText(Integer.toString(c.getCenter().getX()));
					dlgCircle.getTxtY().setText(Integer.toString(c.getCenter().getY()));
					dlgCircle.getTxtRadius().setText(Integer.toString(c.getRadius()));
					dlgCircle.setInnerFill(c.getInnerColor());
					dlgCircle.setBorderFill(c.getColor());
					dlgCircle.setVisible(true);

					if (dlgCircle.isConfirmation()) {
						int x = Integer.parseInt(dlgCircle.getTxtX().getText());
						int y = Integer.parseInt(dlgCircle.getTxtY().getText());
						int r = Integer.parseInt(dlgCircle.getTxtRadius().getText());
						if (dlgCircle.isInnerColorConfirmation()) {
							innerColorCircle = dlgCircle.getInnerFill();
						} else {
							innerColorCircle = c.getInnerColor();
						}
						if (dlgCircle.isBorderColorConfirmation()) {
							borderColorCircle = dlgCircle.getBorderFill();
						} else {
							borderColorCircle = c.getColor();
						}
						if (r > 0) {
							temp = new Circle(new Point(x, y), r, false, borderColorCircle, innerColorCircle);
							EditCircleCommand command = new EditCircleCommand(c, (Circle)temp);
							command.execute();
							allExecutedCommands.add(command);
							commandManager.addCommand(command);
							notifyObservers();
							enableDisableButtons();
							frmDrawing.getDlm().remove(i);
							frmDrawing.getDlm().addElement("Modified circle " + command.getSavedState().toString() + " => " + temp.toString());
						} else {
							JOptionPane.showMessageDialog(null, "The radius must be greater than zero!");
						}
						frmDrawing.repaint();
					}
				}
			} else if (model.getAllShapes().get(i) instanceof CustomHexagon) {
			    if (model.getAllShapes().get(i).isSelected()) {
			        CustomHexagon h = (CustomHexagon) model.getAllShapes().get(i);
			        DlgHexagon dlgHexagon = new DlgHexagon();
			        dlgHexagon.getTxtX().setText(Integer.toString(h.getX()));
			        dlgHexagon.getTxtY().setText(Integer.toString(h.getY()));
			        dlgHexagon.getTxtR().setText(Integer.toString(h.getRadius()));
			        dlgHexagon.setInnerFill(h.getAreaColor()); 
			        dlgHexagon.setBorderFill(h.getBorderColor());
			        dlgHexagon.setVisible(true);

			        if (dlgHexagon.isConfirmation()) {
			            int x = Integer.parseInt(dlgHexagon.getTxtX().getText());
			            int y = Integer.parseInt(dlgHexagon.getTxtY().getText());
			            int r = Integer.parseInt(dlgHexagon.getTxtR().getText());
			            if (dlgHexagon.isInnerColorConfirmation()) {
			                innerColorHexagon = dlgHexagon.getInnerFill();
			            } else {
			            	innerColorHexagon = h.getAreaColor();
			            }
			            
			            if (dlgHexagon.isBorderColorConfirmation()) {
			                borderColorHexagon = dlgHexagon.getBorderFill();
			            } else {
			            	borderColorHexagon = h.getBorderColor();
			            }
			            
			            if (r > 0) {
			                CustomHexagon temp = new CustomHexagon(x, y, r, false, borderColorHexagon, innerColorHexagon);
			                EditHexagonCommand command = new EditHexagonCommand(h, (CustomHexagon)temp);
							command.execute();
							allExecutedCommands.add(command);
							commandManager.addCommand(command);
							notifyObservers();
							enableDisableButtons();
			                frmDrawing.getDlm().remove(i);
			                frmDrawing.getDlm().addElement("Modified hexagon " + command.getSavedState().toString() + " => " + temp.toString());
			            } else {
			                JOptionPane.showMessageDialog(null, "The radius must be greater than zero!");
			            }
			            frmDrawing.repaint();
			        }
			    }
			}
		}
	}

	// _________ Delete ____________

	public void delete() {
		Iterator<Shape> it = model.getAllShapes().iterator();
		ArrayList<Shape> helpSelectedList = new ArrayList<>();
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.isSelected()) {
				helpSelectedList.add(helpShape);
			}
		}
		
		DlgDelete dlgDelete = new DlgDelete();
        dlgDelete.setVisible(true);
        if (dlgDelete.isConfirmation()) {    		
    		DeleteShapesCommand deleteShapesCommand = new DeleteShapesCommand(model, helpSelectedList);
			deleteShapesCommand.execute();
			allExecutedCommands.add(deleteShapesCommand);
			commandManager.addCommand(deleteShapesCommand);
			notifyObservers();
			enableDisableButtons();
			frmDrawing.repaint();
			
			it = helpSelectedList.iterator();
			while (it.hasNext()) {
				Shape helpShape = it.next();
				frmDrawing.getDlm().addElement("Deleted " + helpShape.getClass().getSimpleName() + " " + helpShape.toString());
			}
        }
        
        
	}

	// ________ Draw ____________

	public void pointDraw(MouseEvent me, Color color) {

		{
			temp = new Point(me.getX(), me.getY(), false, color);
			Command addShapeCommand = new AddShapeCommand(model, temp);
			addShapeCommand.execute();
			allExecutedCommands.add(addShapeCommand);
			commandManager.addCommand(addShapeCommand);
			notifyObservers();
			enableDisableButtons();
			
			frmDrawing.getDlm().addElement("Drew point " + temp.toString());
		}
	}

	public void lineDraw(MouseEvent me, Color color) {
		if (startPoint == null) {
			startPoint = new Point(me.getX(), me.getY());
		} else {
			temp = new Line(startPoint, new Point(me.getX(), me.getY()), false, color);
			Command addShapeCommand = new AddShapeCommand(model, temp);
			addShapeCommand.execute();
			allExecutedCommands.add(addShapeCommand);
			commandManager.addCommand(addShapeCommand);
			notifyObservers();
			enableDisableButtons();
			startPoint = null;
			frmDrawing.getDlm().addElement("Drew line " + temp.toString());
		}
	}

	public void rectangleDraw(MouseEvent me, Color color, Color innerColor) {

		upperLeftPoint = new Point(me.getX(), me.getY());
		DlgRectangle dlgRectangle = new DlgRectangle();
		dlgRectangle.getTxtX().setText(Integer.toString(upperLeftPoint.getX()));
		dlgRectangle.getTxtY().setText(Integer.toString(upperLeftPoint.getY()));
		dlgRectangle.setBorderFill(color);
		dlgRectangle.setInnerFill(innerColor);
		dlgRectangle.setVisible(true);

		if (dlgRectangle.isConfirmation()) {
			int x = Integer.parseInt(dlgRectangle.getTxtX().getText());
			int y = Integer.parseInt(dlgRectangle.getTxtY().getText());
			int width = Integer.parseInt(dlgRectangle.getTxtWidth().getText().toString());
			int height = Integer.parseInt(dlgRectangle.getTxtHeight().getText().toString());

			if (dlgRectangle.isInnerColorConfirmation()) {
				innerColorRectangle = dlgRectangle.getInnerFill();
			} else {
				innerColorRectangle = innerColor;
			}
			if (dlgRectangle.isBorderColorConfirmation()) {

				borderColorRectangle = dlgRectangle.getBorderFill();
			} else {
				borderColorRectangle = color;
			}
			if (width > 0 && height > 0) {
				temp = new Rectangle(new Point(x, y), width, height, false, borderColorRectangle, innerColorRectangle);
				Command addShapeCommand = new AddShapeCommand(model, temp);
				addShapeCommand.execute();
				allExecutedCommands.add(addShapeCommand);
				commandManager.addCommand(addShapeCommand);
				notifyObservers();
				enableDisableButtons();
				frmDrawing.getDlm().addElement("Drew rectangle " + temp.toString());
			} else {
				JOptionPane.showMessageDialog(null, "The width and height must be greather than zero!");
			}
		}
	}

	public void circleDraw(MouseEvent me, Color color, Color innerColor) {
		Point center = new Point(me.getX(), me.getY());
		DlgCircle dlgCircle = new DlgCircle();
		dlgCircle.getTxtX().setText(Integer.toString(center.getX()));
		dlgCircle.getTxtY().setText(Integer.toString(center.getY()));
		dlgCircle.setBorderFill(color);
		dlgCircle.setInnerFill(innerColor);
		dlgCircle.setVisible(true);

		if (dlgCircle.isConfirmation()) {
			int x = Integer.parseInt(dlgCircle.getTxtX().getText());
			int y = Integer.parseInt(dlgCircle.getTxtY().getText());
			int r = Integer.parseInt(dlgCircle.getTxtRadius().getText());

			if (dlgCircle.isInnerColorConfirmation()) {
				innerColorCircle = dlgCircle.getInnerFill();
			} else {
				innerColorCircle = innerColor;
			}
			if (dlgCircle.isBorderColorConfirmation()) {

				borderColorCircle = dlgCircle.getBorderFill();
			} else {
				borderColorCircle = color;
			}
			if (r > 0) {
				temp = new Circle(new Point(x, y), r, false, borderColorCircle, innerColorCircle);
				Command addShapeCommand = new AddShapeCommand(model, temp);
				addShapeCommand.execute();
				allExecutedCommands.add(addShapeCommand);
				commandManager.addCommand(addShapeCommand);
				notifyObservers();
				enableDisableButtons();
				frmDrawing.getDlm().addElement("Drew circle " + temp.toString());
			} else {
				JOptionPane.showMessageDialog(null, "The radius must be greater than zero!");
			}
		}
	}

	public void donutDraw(MouseEvent me, Color color, Color innerColor) {
		Point center = new Point(me.getX(), me.getY());
		DlgDonut dlgDonut = new DlgDonut();
		dlgDonut.getTxtX().setText(Integer.toString(center.getX()));
		dlgDonut.getTxtY().setText(Integer.toString(center.getY()));
		dlgDonut.setBorderFill(color);
		dlgDonut.setInnerFill(innerColor);
		dlgDonut.setVisible(true);

		if (dlgDonut.isConfirmation()) {
			int x = Integer.parseInt(dlgDonut.getTxtX().getText());
			int y = Integer.parseInt(dlgDonut.getTxtY().getText());
			int innerRadius = Integer.parseInt(dlgDonut.getTxtInnerRadius().getText());
			int radius = Integer.parseInt(dlgDonut.getTxtRadius().getText());

			if (dlgDonut.isInnerColorConfirmation()) {
				innerColorDonut = dlgDonut.getInnerFill();
			} else {
				innerColorDonut = innerColor;
			}
			if (dlgDonut.isBorderColorConfirmation()) {
				borderColorDonut = dlgDonut.getBorderFill();
			} else {
				borderColorDonut = color;
			}
			if (innerRadius < radius) {
				temp = new Donut(new Point(x, y), radius, innerRadius, false, borderColorDonut, innerColorDonut);
				Command addShapeCommand = new AddShapeCommand(model, temp);
				addShapeCommand.execute();
				allExecutedCommands.add(addShapeCommand);
				commandManager.addCommand(addShapeCommand);
				notifyObservers();
				enableDisableButtons();
				frmDrawing.getDlm().addElement("Drew donut " + temp.toString());
			} else {
				JOptionPane.showMessageDialog(null,
						"The radius mast be greather than inner radius or inner radius mast be greather than zero!");
			}
		}
	}
	
	public void hexagonDraw(MouseEvent me, Color color, Color innerColor) {
	    Point center = new Point(me.getX(), me.getY());
	    DlgHexagon dlgHexagon = new DlgHexagon();
	    dlgHexagon.getTxtX().setText(Integer.toString(center.getX()));
	    dlgHexagon.getTxtY().setText(Integer.toString(center.getY()));
	    dlgHexagon.setBorderFill(color);
	    dlgHexagon.setInnerFill(innerColor);
	    dlgHexagon.setVisible(true);

	    if (dlgHexagon.isConfirmation()) {
	        int x = Integer.parseInt(dlgHexagon.getTxtX().getText());
	        int y = Integer.parseInt(dlgHexagon.getTxtY().getText());
	        int r = Integer.parseInt(dlgHexagon.getTxtR().getText());

	        Color innerColorHexagon;
	        Color borderColorHexagon;

	        if (dlgHexagon.isInnerColorConfirmation()) {
	            innerColorHexagon = dlgHexagon.getInnerFill();
	        } else {
	            innerColorHexagon = innerColor;
	        }

	        if (dlgHexagon.isBorderColorConfirmation()) {
	            borderColorHexagon = dlgHexagon.getBorderFill();
	        } else {
	            borderColorHexagon = color;
	        }

	        if (r > 0) {
	            CustomHexagon temp = new CustomHexagon(x, y, r, false, borderColorHexagon, innerColorHexagon);
	            Command addShapeCommand = new AddShapeCommand(model, temp);
				addShapeCommand.execute();
				allExecutedCommands.add(addShapeCommand);
				commandManager.addCommand(addShapeCommand);
				notifyObservers();
				enableDisableButtons();
	            frmDrawing.getDlm().addElement("Drew hexagon " + temp.toString());
	        } else {
	            JOptionPane.showMessageDialog(null, "The radius must be greater than zero!");
	        }
	    }
	}


	// _________ Select ___________

	public void selectShape(MouseEvent me) {
		
		ArrayList<Shape> helpList = new ArrayList<>();
		Iterator<Shape> it = model.getAllShapes().iterator();
		while (it.hasNext()) {
			helpList.add(it.next());
		}
		
		Collections.reverse(helpList);
		it = helpList.iterator();
		boolean shapeFound = false;
		while (it.hasNext()) {
			Shape shape = it.next();
			
			if (shape.contains(me.getX(), me.getY())) {
				shapeFound = true;
				
				if (shape.isSelected()) {
					DeselectShapesCommand deselectShapesCommand = new DeselectShapesCommand(shape);
					deselectShapesCommand.execute();
					allExecutedCommands.add(deselectShapesCommand);
					commandManager.addCommand(deselectShapesCommand);
					notifyObservers();
					enableDisableButtons();
		            frmDrawing.getDlm().addElement("Deselected " + shape.getClass().getSimpleName() + " " + shape.toString());
				} else {
					SelectShapeCommand command = new SelectShapeCommand(shape);
					command.execute();
					allExecutedCommands.add(command);
					commandManager.addCommand(command);
					notifyObservers();
					enableDisableButtons();
		            frmDrawing.getDlm().addElement("Selected " + shape.getClass().getSimpleName() + " " + shape.toString());
				}
				
				break;
			}
		}
		
		if (shapeFound == false) {
			it = helpList.iterator();
			ArrayList<Shape> helpSelectedList = new ArrayList<>();
			while (it.hasNext()) {
				Shape helpShape = it.next();
				if (helpShape.isSelected()) {
					helpSelectedList.add(helpShape);
				}
			}
			
			DeselectShapesCommand deselectShapesCommand = new DeselectShapesCommand(helpSelectedList);
			deselectShapesCommand.execute();
			allExecutedCommands.add(deselectShapesCommand);
			commandManager.addCommand(deselectShapesCommand);
			notifyObservers();
			enableDisableButtons();
			
			it = helpSelectedList.iterator();
			while (it.hasNext()) {
				Shape helpShape = it.next();
				frmDrawing.getDlm().addElement("Deselected " + helpShape.getClass().getSimpleName() + " " + helpShape.toString());
			}
		}
		
		frmDrawing.repaint();
	}
	
	
	// _________ Z-Axis ___________
	
	public void toFront() {
		Iterator<Shape> it = model.getAllShapes().iterator();
		Shape toReplace = null;
		int i = 0;
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.isSelected()) {
				toReplace = helpShape;
				break;
			}
			i++;
		}
		
		ToFrontCommand command = new ToFrontCommand(i, toReplace, model);
		command.execute();
		allExecutedCommands.add(command);
		commandManager.addCommand(command);
		notifyObservers();
		enableDisableButtons();
		
		frmDrawing.getDlm().addElement("To Front - " + toReplace.getClass().getSimpleName() + " - " + toReplace.toString());
		
		frmDrawing.repaint();
		
	}
	
	public void toBack() {
		Iterator<Shape> it = model.getAllShapes().iterator();
		Shape toReplace = null;
		int i = 0;
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.isSelected()) {
				toReplace = helpShape;
				break;
			}
			i++;
		}
		
		ToBackCommand command = new ToBackCommand(i, toReplace, model);
		command.execute();
		allExecutedCommands.add(command);
		commandManager.addCommand(command);
		notifyObservers();
		enableDisableButtons();
		
		frmDrawing.getDlm().addElement("To Back - " + toReplace.getClass().getSimpleName() + " - " + toReplace.toString());
		
		frmDrawing.repaint();
	}
	
	
	// _________ Bring To Front ___________
	
	public void bringToFront() {
		Iterator<Shape> it = model.getAllShapes().iterator();
		Shape toMove = null;
		int i = 0;
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.isSelected()) {
				toMove = helpShape;
				break;
			}
			i++;
		}
		
		BringToFrontCommand command = new BringToFrontCommand(i, toMove, model);
		command.execute();
		allExecutedCommands.add(command);
		commandManager.addCommand(command);
		notifyObservers();
		enableDisableButtons();
		
		frmDrawing.getDlm().addElement("Bring to Front - " + toMove.getClass().getSimpleName() + " - " + toMove.toString());
		
		frmDrawing.repaint();
		
	}
	
	
	// _________ Bring To Back ___________
	
	public void bringToBack() {
		Iterator<Shape> it = model.getAllShapes().iterator();
		Shape toMove = null;
		int i = 0;
		while (it.hasNext()) {
			Shape helpShape = it.next();
			if (helpShape.isSelected()) {
				toMove = helpShape;
				break;
			}
			i++;
		}
		
		BringToBackCommand command = new BringToBackCommand(i, toMove, model);
		command.execute();
		allExecutedCommands.add(command);
		commandManager.addCommand(command);
		notifyObservers();
		enableDisableButtons();
		
		frmDrawing.getDlm().addElement("Bring to Back - " + toMove.getClass().getSimpleName() + " - " + toMove.toString());
		
		frmDrawing.repaint();
	}
	

	// _________ Undo ___________
	
	public void undo() {
		commandManager.undo();
		frmDrawing.getDlm().addElement("Undo");
		allExecutedCommands.add(new HelpUndoCommand());
		notifyObservers();
		enableDisableButtons();
		frmDrawing.repaint();
	}
	
	// _________ Redo ___________
	public void redo() {
		commandManager.redo();
		frmDrawing.getDlm().addElement("Redo");
		allExecutedCommands.add(new HelpRedoCommand());
		notifyObservers();
		enableDisableButtons();
		frmDrawing.repaint();
	}
	
	// _________ Save serialized _________
	
	public void saveSerialized() {
		StrategySerialized strategySerialized = new StrategySerialized();
		strategySerialized.setModel(model);
		context.setStrategy(strategySerialized);
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			
			context.save(selectedFile.getAbsolutePath());
		}
	}
	
	// ______ Save textual _________
	
	public void saveTextual() {
		StrategyTextual strategyTextual = new StrategyTextual();
		
		Iterator<Command> it = allExecutedCommands.iterator();
		String text = "";
		while (it.hasNext()) {
			Command command = it.next();
			if (command instanceof AddShapeCommand) {
				String formattedString = logFormatter.formatLog((AddShapeCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof EditPointCommand) {
				String formattedString = logFormatter.formatLog((EditPointCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof EditLineCommand) {
				String formattedString = logFormatter.formatLog((EditLineCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof EditRectangleCommand) {
				String formattedString = logFormatter.formatLog((EditRectangleCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof EditDonutCommand) {
				String formattedString = logFormatter.formatLog((EditDonutCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof EditCircleCommand) {
				String formattedString = logFormatter.formatLog((EditCircleCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof EditHexagonCommand) {
				String formattedString = logFormatter.formatLog((EditHexagonCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof SelectShapeCommand) {
				String formattedString = logFormatter.formatLog((SelectShapeCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof ToFrontCommand) {
				String formattedString = logFormatter.formatLog((ToFrontCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof ToBackCommand) {
				String formattedString = logFormatter.formatLog((ToBackCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof BringToFrontCommand) {
				String formattedString = logFormatter.formatLog((BringToFrontCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof BringToBackCommand) {
				String formattedString = logFormatter.formatLog((BringToBackCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof DeselectShapesCommand) {
				String formattedString = logFormatter.formatLog((DeselectShapesCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof DeleteShapesCommand) {
				String formattedString = logFormatter.formatLog((DeleteShapesCommand)command);
				text += formattedString + "\n";
			} else if (command instanceof HelpUndoCommand) {
				text += "Undo\n";
			} else if (command instanceof HelpRedoCommand) {
				text += "Redo\n";
			}
		}
		
		strategyTextual.setText(text);
		context.setStrategy(strategyTextual);
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			
			context.save(selectedFile.getAbsolutePath());
		}
		
	}
	
	// ______ Read serialized _________
	
	public void readSerialized() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			  FileInputStream fis;
			try {
				fis = new FileInputStream(jfc.getSelectedFile());
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object shapesFromFile = ois.readObject();
				
				model.setAllShapes((ArrayList<Shape>)shapesFromFile);
				
				commandManager.getUndoList().clear();
	            commandManager.getRedoList().clear();
	            frmDrawing.getDlm().clear();
	            allExecutedCommands.clear();
	            notifyObservers();
	            enableDisableButtons();
				
				frmDrawing.repaint();
				
				JOptionPane.showMessageDialog(null, "Read successful", "Shapes loaded", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Failed to read", "Read failed", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Failed to read", "Read failed", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Failed to read", "Read failed", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}
	
	// ______ Read textual _________
	
	public void readTextual() {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", new String[] {"txt"});
		jfc.setFileFilter(filter);
		
		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(jfc.getSelectedFile()));
				String line = reader.readLine();

				while (line != null) {
					logParser.addFileLine(line);
					line = reader.readLine();
					remainingLines++;
				}
				
				commandManager.getUndoList().clear();
	            commandManager.getRedoList().clear();
	            frmDrawing.getDlm().clear();
	            allExecutedCommands.clear();
	            model.deleteAllShapes();
	            notifyObservers();
	            enableDisableButtons();
				
				frmDrawing.repaint();

				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// ______ Parse next log ______
	
	public void parseNextLog() {
		LogContext logContext = new LogContext();
		Command command = logParser.parseCommand(logContext);
		
		if (command instanceof HelpUndoCommand) {
			undo();
		} else if (command instanceof HelpRedoCommand) {
			redo();
		} else {
			command.execute();
			allExecutedCommands.add(command);
			commandManager.addCommand(command);
			notifyObservers();
			enableDisableButtons();
			
			frmDrawing.getDlm().addElement(logContext.getLogHolder());
			
			
			frmDrawing.repaint();
		}
		remainingLines--; 
        enableDisableButtons();
	}
	
	// ______ Observer ______
	
	public void add(Observer o) {
		registeredObservers.add(o);
	}

	public void delete(Observer o) {
		registeredObservers.remove(o);
	}

	public void notifyObservers() {
		Iterator<Observer> it = registeredObservers.iterator();
		
		int selectedShapes = 0;
		Iterator<Shape> it2 = model.getAllShapes().iterator();
		while (it2.hasNext()) {
			Shape helpShape = it2.next();
			if (helpShape.isSelected()) {
				selectedShapes++;
			}
		}
		
		while (it.hasNext()) {
			it.next().updateState(selectedShapes);
		}
	}
	
	// ______ Shape position ______
	
	private boolean isSelectedShapeOnTop() {
	    ArrayList<Shape> allShapes = model.getAllShapes();
	    return allShapes.get(allShapes.size() - 1).isSelected();
	}

	private boolean isSelectedShapeOnBottom() {
		ArrayList<Shape> allShapes = model.getAllShapes();
	    return allShapes.get(0).isSelected();
	}
	
	// ______ Enable-Disable buttons ______
	
	public void enableDisableButtons() {
		boolean isShapeSelected = false;
	    boolean isShapeOnTop = false;
	    boolean isShapeOnBottom = false;

	    int selectedShapesCount = 0;
	    Iterator<Shape> it = model.getAllShapes().iterator();
	    while (it.hasNext()) {
	        Shape shape = it.next();
	        if (shape.isSelected()) {
	            selectedShapesCount++;
	            isShapeSelected = true;
	        }
	    }

	    if (selectedShapesCount == 1) {
	        isShapeOnTop = isSelectedShapeOnTop();
	        isShapeOnBottom = isSelectedShapeOnBottom();
	    }
		
		frmDrawing.setIsBtnUndoEnabled(commandManager.getUndoList().isEmpty() == false);
		frmDrawing.setIsBtnRedoEnabled(commandManager.getRedoList().isEmpty() == false);
		frmDrawing.setIsBtnSelectEnabled(model.getAllShapes().size() > 0);
		frmDrawing.setIsBtnToFrontEnabled(isShapeSelected && !isShapeOnTop && selectedShapesCount ==1);
		frmDrawing.setIsBtnToBackEnabled(isShapeSelected && !isShapeOnBottom && selectedShapesCount ==1);
		frmDrawing.setIsBtnBringToFrontEnabled(isShapeSelected && !isShapeOnTop && selectedShapesCount ==1);
		frmDrawing.setIsBtnBringToBackEnabled(isShapeSelected && !isShapeOnBottom && selectedShapesCount ==1);
		frmDrawing.setIsBtnSaveSerEnabled(allExecutedCommands.size() > 0);
		frmDrawing.setIsBtnSaveTextualEnabled(allExecutedCommands.size() > 0);
		frmDrawing.setIsBtnParseFileLineEnabled(remainingLines > 0);
	}
	
	// ______ Geters and Setters _________
	
	public void setFrmDrawing(FrmDrawing frmDrawing) {
		this.frmDrawing = frmDrawing;
	}
}
