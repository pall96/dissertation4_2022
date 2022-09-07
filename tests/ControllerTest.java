import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControllerTest {
    @Test
    public void testdrawShape(){
       AppDisplayController controller = new AppDisplayController(new AppDisplayView());
       Stack shapeStack = new Stack<>();
       controller.setShapeStack(shapeStack);
       controller.setPressedButtonText("Node");
       Graphics g = null;
       controller.getView().setMouseClicked(true);
       controller.drawShape(g);
       assertTrue(shapeStack.peek() instanceof NodeRectangle);

       controller.setPressedButtonText("Component");
       controller.getView().setMouseClicked(true);
       controller.drawShape(g);
       assertTrue(shapeStack.peek() instanceof SoftwareComponent);

        controller.setPressedButtonText("Abstract Component");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof SoftwareComponent);

        controller.setPressedButtonText("Mobility Manager");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof MobilityManagerRectangle);

        controller.setPressedButtonText("Mobile Component");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof MobileComponentRectangle);

        controller.setPressedButtonText("Mobility Actions");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof MobilityActionsRectangle);

        controller.setPressedButtonText("Event");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof EventRectangle);

        controller.setPressedButtonText("Description");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof DescriptionCircle);

        controller.setPressedButtonText("Function");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof MobilityFunctionRectangle);

        controller.setPressedButtonText("Logical Mobility : Pre-Mobility Activity");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof MobilityActivityTypeRectangle);

        controller.setPressedButtonText("Logical Mobility : Mobility Activity");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof MobilityActivityTypeRectangle);

        controller.setPressedButtonText("Logical Mobility : Post-Mobility Activity");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof MobilityActivityTypeRectangle);

        controller.setPressedButtonText("Physical Mobility : After Mobility Activity");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof MobilityActivityTypeRectangle);

        controller.setPressedButtonText("Current Deployment Node");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof AbstractNodeRectangle);

        controller.setPressedButtonText("Allowed Deployment Node");
        controller.getView().setMouseClicked(true);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof AbstractNodeRectangle);

        controller.setPressedButtonText("Link");
        controller.setNumMouseClicks(1);
        controller.drawShape(g);
        assertTrue(shapeStack.peek() instanceof Link);

    }

    @Test
    public void testControlDisplay(){
        AppDisplayController controller = new AppDisplayController(new AppDisplayView());
        Stack shapeStack = new Stack<>();
        controller.setShapeStack(shapeStack);
        NodeRectangle rectangle = new NodeRectangle(20,30);
        DescriptionCircle circle = new DescriptionCircle(40, 50,60);
        MobilityFunctionRectangle mobilityFunctionRectangle = new MobilityFunctionRectangle(78, 90);
        SoftwareComponent component = new SoftwareComponent(50,60, "Component");
        shapeStack.add(rectangle);
        shapeStack.add(circle);
        shapeStack.add(mobilityFunctionRectangle);
        shapeStack.add(component);


        controller.performActionsOnButtonClick("Undo");
        assertEquals(3, shapeStack.size());

        controller.performActionsOnButtonClick("Redo");
        assertEquals(4, shapeStack.size());


    }
}
