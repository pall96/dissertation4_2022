import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
*AppDisplayController class for controlling
*what will be displayed in the UI.
*/
public class AppDisplayController {

	private AppDisplayView view = null;


	String pressedButtonText = null;

	public void setPressedButtonText(String pressedButtonText) {
		this.pressedButtonText = pressedButtonText;
	}

	private int numMouseClicks;

	private XMLGenerator gen = new XMLGenerator();

	private static int x_previous_arrow;
	private static int y_previous_arrow;
	private Color c = null;
	private Stack redoStack = new Stack();

	public Stack getShapeStack() {
		return shapeStack;
	}

	public void setShapeStack(Stack shapeStack) {
		this.shapeStack = shapeStack;
	}

	Stack shapeStack = new Stack();
	Stack eventViewStack = new Stack();

	public Stack getEventViewStack() {
		return eventViewStack;
	}

	public Stack getDistributionViewStack() {
		return distributionViewStack;
	}

	public Stack getDeploymentViewStack() {
		return deploymentViewStack;
	}

	public Stack getMobilityActionsStack() {
		return mobilityActionsStack;
	}

	Stack distributionViewStack = new Stack();
	Stack deploymentViewStack = new Stack();
	Stack mobilityActionsStack = new Stack();

	public AppDisplayController(AppDisplayView view) {
		this.view = view;
	}

	public Stack chooseShapeStack(){
		if(view.getPressedViewButton().getText().equals("Distribution View")){
			return distributionViewStack;
		}
		else if(view.getPressedViewButton().getText().equals("Event View")){
			return eventViewStack;
		}
		else if(view.getPressedViewButton().getText().equals("Mobility Actions View")){
			return mobilityActionsStack;
		}
		else if(view.getPressedViewButton().getText().equals("Logical Mobility : Deployment View")){
			return deploymentViewStack;
		}
		return null;

	}

	/**
	 * this method is used to check which button has been
	 * clicked by the user and appropriate actions are
	 * taken according to the clicked button.
	 *
	 * @param e is an object of type ActionEvent that
	 *          is passed from the AppDisplayView class when a user
	 *          clicks on a button.
	 */
	public void controlDisplay(ActionEvent e) {
		JButton pressedButton = (JButton) e.getSource();
		if (pressedButton != null) {
			pressedButtonText = pressedButton.getText();
			performActionsOnButtonClick(pressedButtonText);
		}
	}

		public void performActionsOnButtonClick (String pressedButtonText){

			if (pressedButtonText.equals("Generate XML")) {
				String xmlGenerated = gen.generateEntireXML(chooseShapeStack());
				view.addTextAreaOnButtonPress(xmlGenerated);
			} else if (pressedButtonText.equals("Export XML")) {
				gen.exportToFile(view.getPressedViewButton().getText());
			} else if (pressedButtonText.equals("Export Drawing")) {
				BufferedImage bi = new BufferedImage(view.getSize().width, view.getSize().height, BufferedImage.TYPE_INT_ARGB);
				Graphics g = bi.createGraphics();
				view.paint(g);  //this == JComponent
				g.dispose();
				try {
					ImageIO.write(bi, "png", new File("C:\\Users\\Pallavi\\Downloads\\test.png"));
				} catch (Exception exception) {
				}
			} else if (pressedButtonText.equals("Undo")) {
				Object poppedObj = chooseShapeStack().pop();
				redoStack.add(poppedObj);
				if(poppedObj instanceof Shape){
					view.removeSwingComponents((Shape)poppedObj);
				}
				view.repaint();
				SwingUtilities.updateComponentTreeUI(view);
			} else if (pressedButtonText.equals("Redo")) {
				Object poppedObj = redoStack.pop();
				shapeStack.add(poppedObj);
				view.repaint();
				SwingUtilities.updateComponentTreeUI(view);
			}
			else if(pressedButtonText.equals("Clear Screen")){
				Stack shapeStack = chooseShapeStack();
				shapeStack.clear();
				view.repaint();
				view.removeAll();
			}

		}

 public boolean isEndShapeValid(Shape startShape, Shape endShape){
	 String errorMessage = null;
	 if(startShape.getAllowedShapeNameList().size() == 0) {
		 return false;
	 }
	 else {
		 for (String shape : startShape.getAllowedShapeNameList()) {
			 if (shape.equals(endShape.getShapeName().getText())) {
				 return true;
			 }
		 }
	 }
	 return false;
 }

 public String createErrorString(Shape startShape, Shape endShape){
	 String errorMessage = null;
	 if(startShape.getAllowedShapeNameList().size() == 0){
		 errorMessage = "You are not allowed to connect any shape to " + startShape.getShapeName().getText() + ".";
	 }
	 else {
		 String allowedEndShapeString = createAllowedEndShapeString(startShape);
		 errorMessage = "A " + startShape.getShapeName().getText() + " can only be connected to a " + allowedEndShapeString + ". "
				 + "You tried to connect a " + startShape.getShapeName().getText() + " to a " + endShape.getShapeName().getText()
				 + " which is not allowed";

	 }
	 return errorMessage;
 }

 public String createAllowedEndShapeString(Shape startShape){
	 String allowedEndShapeString = "";
	 for(String shape : startShape.getAllowedShapeNameList()){
		 allowedEndShapeString = allowedEndShapeString + shape + ", ";
	 }
	 allowedEndShapeString = allowedEndShapeString.substring(0, allowedEndShapeString.length() - 2);
	 return allowedEndShapeString;
 }


	public int getNumMouseClicks() {
		return numMouseClicks;
	}

	/**
 *this method checks which button has
 *been clicked by the user and paints 
 *the UI according to the button clicked
 *by the user.
 *@param g is an object of type Graphics which
 *is passed from the paintComponent method in
 *the AppDisplayView class.
 */
 public void drawShape(Graphics g) {

	 if (pressedButtonText != null) {
		 if (view.getIsMouseClicked() && pressedButtonText.equals("Node")) {
			 NodeRectangle rectangle = new NodeRectangle(view.getX_clicked(), view.getY_clicked());
			 //view.add(rectangle.getUserInputText());
			 view.drawShape(g, rectangle);
			 distributionViewStack.add(rectangle);
			 shapeStack.add(rectangle);


		 }
		 else if (view.getIsMouseClicked() && (pressedButtonText.equals("Component")||pressedButtonText.equals("Abstract Component"))) {
			 SoftwareComponent triangle = new SoftwareComponent(view.getX_clicked(), view.getY_clicked(), pressedButtonText);
			 view.drawShape(g, triangle);
			 chooseShapeStack().add(triangle);
			 //shapeStack.add(triangle);
		 }


		 else if (view.getIsMouseClicked() && pressedButtonText.equals("Mobility Manager")) {
			 MobilityManagerRectangle rectangle = new MobilityManagerRectangle(view.getX_clicked(), view.getY_clicked());
			 view.drawShape(g, rectangle);
			 eventViewStack.add(rectangle);
			 //shapeStack.add(rectangle);
		 }
		 else if (view.getIsMouseClicked() && pressedButtonText.equals("Interface")) {
			 InterfaceRectangle rectangle = new InterfaceRectangle(view.getX_clicked(), view.getY_clicked());
			 view.drawShape(g, rectangle);
			 distributionViewStack.add(rectangle);
			 //shapeStack.add(rectangle);
		 }
		 else if (view.getIsMouseClicked() && pressedButtonText.equals("Mobile Component")) {
			 MobileComponentRectangle rectangle = new MobileComponentRectangle(view.getX_clicked(), view.getY_clicked());
			 view.drawShape(g, rectangle);
			 deploymentViewStack.add(rectangle);
			 //shapeStack.add(rectangle);
		 }
		 else if (view.getIsMouseClicked() && pressedButtonText.equals("Mobility Actions")) {
			 MobilityActionsRectangle rectangle = new MobilityActionsRectangle(view.getX_clicked(), view.getY_clicked());
			 view.drawShape(g, rectangle);
			 mobilityActionsStack.add(rectangle);
			 //shapeStack.add(rectangle);
		 }
		 else if (view.getIsMouseClicked() && pressedButtonText.equals("Event")) {
			 EventRectangle subDirectorySquare = new EventRectangle(view.getX_clicked(), view.getY_clicked());
			 view.drawShape(g, subDirectorySquare);
			 eventViewStack.add(subDirectorySquare);
			 //shapeStack.add(subDirectorySquare);
		 }
		 else if (view.getIsMouseClicked() && pressedButtonText.equals("Function")) {
			 MobilityFunctionRectangle rectangle = new MobilityFunctionRectangle(view.getX_clicked(), view.getY_clicked());
			 view.drawShape(g, rectangle);
			 mobilityActionsStack.add(rectangle);
			 //shapeStack.add(rectangle);
		 }
		 else if (view.getIsMouseClicked() && pressedButtonText.equals("Description")) {
			 System.out.println(56);
			 DescriptionCircle circle = new DescriptionCircle(view.getX_clicked(), view.getY_clicked(), 130);
			 view.drawShape(g, circle);
			 chooseShapeStack().add(circle);

		 }

		 else if (view.getIsMouseClicked() && (pressedButtonText.equals("Link") || pressedButtonText.equals("Function Call Arrow") || pressedButtonText.equals("Function Response Arrow"))) {
			 drawArrow(g, pressedButtonText);

		 }
		 else if (view.getIsMouseClicked() && (pressedButtonText.equals("Logical Mobility : Pre-Mobility Activity") || pressedButtonText.equals("Logical Mobility : Mobility Activity") || pressedButtonText.equals("Logical Mobility : Post-Mobility Activity")||pressedButtonText.equals("Physical Mobility : After Mobility Activity"))) {
			 MobilityActivityTypeRectangle rectangle = new MobilityActivityTypeRectangle(view.getX_clicked(), view.getY_clicked(), pressedButtonText);
			 view.drawShape(g, rectangle);
			 mobilityActionsStack.add(rectangle);


		 }
		 else if (view.getIsMouseClicked() && (pressedButtonText.equals("Current Deployment Node") || pressedButtonText.equals("Allowed Deployment Node"))) {
			 AbstractNodeRectangle rectangle = new AbstractNodeRectangle(view.getX_clicked(), view.getY_clicked(), pressedButtonText);
			 view.drawShape(g, rectangle);
			 deploymentViewStack.add(rectangle);
		 }
		 else if(view.getIsMouseClicked() && pressedButtonText.equals("Delete Shape")){
				 Stack shapeStack = chooseShapeStack();
				 Shape clickedShape = AppDisplayController.findEnclosingShape(shapeStack, view.getX_clicked(), view.getY_clicked());
				 shapeStack.remove(clickedShape);
				 view.removeSwingComponents(clickedShape);
				 view.repaint();

				 }
			 }
		 }


 /**
 *this method checks each object of shapeStack 
 *and paints it accordingly.
 *
 *@param g is an object of type Graphics which
 *is passed from the paintComponent method in
 *the AppDisplayView class.
 */

	public void repaintShape(Object o, Graphics g) {
		if(o instanceof NodeRectangle) {
			view.drawShape(g, (NodeRectangle)o);
		}
		else if(o instanceof MobilityActivityTypeRectangle) {
			view.drawShape(g, (MobilityActivityTypeRectangle)o);
		}
		else if(o instanceof MobileComponentRectangle) {
			view.drawShape(g, (MobileComponentRectangle)o);
		}
		else if(o instanceof MobilityFunctionRectangle) {
			view.drawShape(g, (MobilityFunctionRectangle)o);
		}
		else if(o instanceof MobilityManagerRectangle) {
			view.drawShape(g, (MobilityManagerRectangle)o);
		}
		else if(o instanceof MobilityActionsRectangle) {
			view.drawShape(g, (MobilityActionsRectangle)o);
		}
		else if(o instanceof AbstractNodeRectangle) {
			view.drawShape(g, (AbstractNodeRectangle)o);
		}
		else if(o instanceof InterfaceRectangle) {
			view.drawShape(g, (InterfaceRectangle)o);
		}
		else if(o instanceof EventRectangle) {
			view.drawShape(g, (EventRectangle)o);
		}
		else if(o instanceof DescriptionCircle) {
			view.drawShape(g, (DescriptionCircle)o);
		}

		else if(o instanceof SoftwareComponent) {
			view.drawShape(g, (SoftwareComponent)o);
		}
		else if (o instanceof Link) {
			((Link) o).paintShape(g);
		}
	}

	public void drawArrow(Graphics g, String pressedButtonText){
		numMouseClicks++;
		if(numMouseClicks == 1) {
			x_previous_arrow = view.getX_clicked();
			y_previous_arrow = view.getY_clicked();
		}
		if(numMouseClicks == 2) {
			Link arrow = new Link(x_previous_arrow, y_previous_arrow, view.getX_clicked(), view.getY_clicked(), c, pressedButtonText, 30, this);
				arrow.paintShape(g);
				chooseShapeStack().add(arrow);

				numMouseClicks = 0;
		}
	}

	public void setNumMouseClicks(int numMouseClicks) {
		this.numMouseClicks = numMouseClicks;
	}

	/**
	 *this method iterates over each object
	 *in the stack shapeStack and paints it
	 *by calling the repaintShape() method. 
	 *and paints it accordingly. Next, it 
	 *calls the drawShape() method to paint
	 *the shape whose button was last clicked 
	 *by the user.
	 *@param g is an object of type Graphics which
	 *is passed from the paintComponent method in
	 *the AppDisplayView class.
	 *
	 */

	public void chooseAndIterateShapeStack(Graphics g) {
		if (view.getPressedViewButton() != null) {
			if (view.getPressedViewButton().getText().equals("Distribution View")) {
				iterateShapeStack(g, distributionViewStack);
			} else if (view.getPressedViewButton().getText().equals("Event View")) {
				iterateShapeStack(g, eventViewStack);
			} else if (view.getPressedViewButton().getText().equals("Mobility Actions View")) {
				iterateShapeStack(g, mobilityActionsStack);
			} else if (view.getPressedViewButton().getText().equals("Logical Mobility : Deployment View")) {
				iterateShapeStack(g, deploymentViewStack);
			}
		}
	}
	public void iterateShapeStack(Graphics g, Stack shapeStack) {

			if(shapeStack != null) {
				Iterator<Object> itr = shapeStack.iterator();
				while (itr.hasNext()) {
					Object o = itr.next();
					repaintShape(o, g);
				}
			}
	        drawShape(g);


	
}
   public boolean performValidityActions(Shape startShape, Shape endShape){
	   boolean isEndShapeValid =  isEndShapeValid(startShape, endShape);
	   if(!isEndShapeValid){
		   String errorString = createErrorString(startShape, endShape);
		   view.displayError(errorString);
	   }
	   return isEndShapeValid;
   }











	public String getPressedButtonText() {
		return pressedButtonText;
	}

	public AppDisplayView getView() {
		return view;
	}

	public static boolean isInsideTriangle(Point p1, Point p2, Point p3, Point p) {
		double area = getTriangleArea(p1, p2, p3); //area of triangle ABC
		double area1 = getTriangleArea(p, p2, p3); //area of PBC
		double area2 = getTriangleArea(p1, p, p3); //area of APC
		double area3 = getTriangleArea(p1, p2, p); //area of ABP

		return (area == area1 + area2 + area3); // when three triangles are forming the whole triangle
	}

	private static double getTriangleArea(Point p1, Point p2, Point p3) { // find area of triangle formed by p1, p2 and p3
		return Math.abs((p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y)) / 2.0);
	}

	public static Shape findEnclosingShape(Stack shapeStack, int x_coord, int y_coord) {

		for (Object o : shapeStack) {
			if (o instanceof ParentRectangle) {
				ParentRectangle rect = (ParentRectangle)o;
				if (isInsideRectangle(x_coord, y_coord, rect)) {
					return rect;
				}
			}

			if (o instanceof SoftwareComponent) {
				SoftwareComponent t = (SoftwareComponent) o;
				int[] x_vertices = t.getX_points();
				int[] y_vertices = t.getY_points();

				Point p0 = new Point(x_coord, y_coord);
				Point p1 = new Point(x_vertices[0], y_vertices[0]);
				Point p2 = new Point(x_vertices[1], y_vertices[1]);
				Point p3 = new Point(x_vertices[2], y_vertices[2]);

				if (AppDisplayController.isInsideTriangle(p1, p2, p3, p0)) {
					return t;
				}
			}

			if (o instanceof ParentCircle) {
				ParentCircle circ = (ParentCircle) o;
				if (isInsideCircle(x_coord, y_coord, circ)) {
					return circ;
				}
			}
		}
		return null;
	}

	public static boolean isInsideRectangle(int x_coord, int y_coord, ParentRectangle rect) {
		return x_coord <= rect.x_clicked + rect.width &&
				x_coord >= rect.x_clicked &&
				y_coord >= rect.y_clicked &&
				y_coord <= rect.y_clicked + rect.height;
	}

	public static boolean isInsideCircle(int x_coord, int y_coord, ParentCircle circ) {
		double distanceSquare = Math.pow(x_coord - circ.x_center,2) + Math.pow(y_coord - circ.y_center, 2);
		double radiusSquare = Math.pow(circ.diameter / 2.0, 2);

		return distanceSquare <= radiusSquare;
	}
}

