import org.junit.Test;

import java.awt.*;
import java.util.Stack;

import static org.junit.Assert.*;

public class ModelTest {
    @Test
    public void testAbstractNode() {
        int x_clicked = 20;
        int y_clicked = 30;
        String type = "Component";
        AbstractNodeRectangle rectangle = new AbstractNodeRectangle(x_clicked, y_clicked, type);
        assertEquals(x_clicked, rectangle.getX_clicked());
        assertEquals(y_clicked, rectangle.getY_clicked());
        assertEquals(type, rectangle.getShapeName().getText());
    }

    @Test
    public void testDescriptionCircle() {
        int x_clicked = 10;
        int y_clicked = 50;
        int diameter = 100;
        int x_center = x_clicked + diameter/2;
        int y_center = y_clicked + diameter/2;
        DescriptionCircle circle = new DescriptionCircle(x_clicked,y_clicked, diameter);
        assertEquals(x_clicked, circle.getX_clicked());
        assertEquals(y_clicked, circle.getY_clicked());
        assertEquals(diameter, circle.getDiameter());
        assertEquals(x_center, circle.getX_center());
        assertEquals(y_center, circle.getY_center());

    }

    @Test
    public void testEventRectangle() {
        int x_clicked = 40;
        int y_clicked = 80;
        EventRectangle rectangle = new EventRectangle(x_clicked,y_clicked);
        assertEquals(x_clicked, rectangle.getX_clicked());
        assertEquals(y_clicked, rectangle.getY_clicked());
    }

    @Test
    public void testLabelTextFieldPair() {
        String labelName = "Node ID";
        LabelTextFieldPair labelTextFieldPair = new LabelTextFieldPair(labelName);
        assertEquals(labelName, labelTextFieldPair.getLabel().getText());
        assertNotNull(labelTextFieldPair.getTextField());
    }

    @Test
    public void testLink() {
        int x_start = 40;
        int y_start = 60;
        int x_end = 100;
        int y_end = 120;
        Color arrowColor = new Color(255, 0, 0);
        int arrowThickness = 30;
        String arrowType = "dashed";
        AppDisplayController controller = new AppDisplayController(new AppDisplayView());
        Stack shapeStack = new Stack();
        MobileComponentRectangle rect = new MobileComponentRectangle(30,52 );
        rect.setHeight(40);
        rect.setWidth(60);
        DescriptionCircle circ = new DescriptionCircle(80, 110, 100);
        shapeStack.add(rect);
        shapeStack.add(circ);
        controller.setShapeStack(shapeStack);

        Link link = new Link(x_start, y_start, x_end, y_end, arrowColor, arrowType, arrowThickness, controller);
        assertEquals(x_start, link.getX_start());
        assertEquals(y_start, link.getY_start());
        assertEquals(x_end, link.getX_end());
        assertEquals(y_end, link.getY_end());
        assertEquals(arrowColor, link.getArrowColor());
        assertEquals(arrowType, link.getArrowType());
        assertEquals(arrowThickness, link.getArrowThickness());
        assertEquals(shapeStack, link.getShapeStack());
        assertEquals(rect, link.getStartShape());
        assertEquals(circ, link.getEndShape());

    }

    @Test
    public void testMobileComponentRectangle() {
        int x_clicked = 40;
        int y_clicked = 80;
        int height = 20;
        int width = 40;
        EventRectangle rectangle = new EventRectangle(x_clicked,y_clicked);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        assertEquals(x_clicked, rectangle.getX_clicked());
        assertEquals(y_clicked, rectangle.getY_clicked());
        assertEquals(height, rectangle.getHeight());
        assertEquals(width, rectangle.getWidth());
    }

    @Test
    public void testMobilityActionsRectangle() {
        int x_clicked = 40;
        int y_clicked = 90;
        int height = 50;
        int width = 30;
        MobilityActionsRectangle rectangle = new MobilityActionsRectangle(x_clicked,y_clicked);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        assertEquals(x_clicked, rectangle.getX_clicked());
        assertEquals(y_clicked, rectangle.getY_clicked());
        assertEquals(height, rectangle.getHeight());
        assertEquals(width, rectangle.getWidth());
    }

    @Test
    public void testMobilityActivityTypeRectangle() {
        int x_clicked = 120;
        int y_clicked = 70;
        int height = 60;
        int width = 30;
        String type = "logical mobility";
        MobilityActivityTypeRectangle rectangle = new MobilityActivityTypeRectangle(x_clicked,y_clicked, type);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        assertEquals(x_clicked, rectangle.getX_clicked());
        assertEquals(y_clicked, rectangle.getY_clicked());
        assertEquals(height, rectangle.getHeight());
        assertEquals(width, rectangle.getWidth());
        assertEquals(type, rectangle.getShapeName().getText());
    }

    @Test
    public void testMobilityFunctionRectangle() {
        int x_clicked = 80;
        int y_clicked = 20;
        int height = 190;
        int width = 50;
        String type = "logical mobility";
        MobilityFunctionRectangle rectangle = new MobilityFunctionRectangle(x_clicked,y_clicked);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        assertEquals(x_clicked, rectangle.getX_clicked());
        assertEquals(y_clicked, rectangle.getY_clicked());
        assertEquals(height, rectangle.getHeight());
        assertEquals(width, rectangle.getWidth());
    }

    @Test
    public void testMobilityManagerRectangle() {
        int x_clicked = 30;
        int y_clicked = 40;
        int height = 85;
        int width = 49;
        MobilityManagerRectangle rectangle = new MobilityManagerRectangle(x_clicked,y_clicked);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        assertEquals(x_clicked, rectangle.getX_clicked());
        assertEquals(y_clicked, rectangle.getY_clicked());
        assertEquals(height, rectangle.getHeight());
        assertEquals(width, rectangle.getWidth());
    }

    @Test
    public void testNodeRectangle() {
        int x_clicked = 67;
        int y_clicked = 95;
        int height = 88;
        int width = 99;
        NodeRectangle rectangle = new NodeRectangle(x_clicked,y_clicked);
        rectangle.setHeight(height);
        rectangle.setWidth(width);
        assertEquals(x_clicked, rectangle.getX_clicked());
        assertEquals(y_clicked, rectangle.getY_clicked());
        assertEquals(height, rectangle.getHeight());
        assertEquals(width, rectangle.getWidth());
    }

    @Test
    public void testSoftwareComponent() {
        int x_clicked = 67;
        int y_clicked = 95;
        String type = "Abstract Component";
        SoftwareComponent triangle = new SoftwareComponent(x_clicked,y_clicked, type);
        assertEquals(x_clicked, triangle.getX_clicked());
        assertEquals(y_clicked, triangle.getY_clicked());
        assertEquals(type, triangle.getShapeName().getText());

    }

}