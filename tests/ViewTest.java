import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.awt.*;

public class ViewTest {
    @Test
    public void testDrawShape(){
        AppDisplayView view = new AppDisplayView();
        Graphics g = Mockito.mock(Graphics.class);

        NodeRectangle rectangle = new NodeRectangle(30,40);
        view.drawShape(g,rectangle);
        Mockito.verify(g).drawRect(rectangle.getX_clicked(), rectangle.getY_clicked(), 230, 140);

        int x_clicked = 67;
        int y_clicked = 40;
        SoftwareComponent component = new SoftwareComponent(x_clicked,y_clicked, "Component");
        view.drawShape(g,component);
        Mockito.verify(g).drawLine(x_clicked, y_clicked, x_clicked + 125, y_clicked + 100);
        Mockito.verify(g).drawLine(x_clicked + 125, y_clicked + 100, x_clicked - 125, y_clicked + 100);
        Mockito.verify(g).drawLine(x_clicked - 125, y_clicked + 100, x_clicked, y_clicked);
        Mockito.verify(g).drawLine(x_clicked - 62, y_clicked + 50, x_clicked - 112, y_clicked + 50);
        Mockito.verify(g).drawLine(x_clicked + 62, y_clicked + 50, x_clicked + 112, y_clicked + 50);
        Mockito.verify(g).drawOval(x_clicked + 112, y_clicked + 40, 20, 20);
        Mockito.verify(g).drawOval(x_clicked - 132, y_clicked + 40, 20, 20);
        Mockito.verify(g).fillOval(x_clicked - 132, y_clicked + 40, 20, 20);


        x_clicked = 23;
        y_clicked = 98;
        component = new SoftwareComponent(x_clicked,y_clicked, "Abstract Component");
        view.drawShape(g,component);
        Mockito.verify(g).drawLine(x_clicked, y_clicked, x_clicked + 125, y_clicked + 100);
        Mockito.verify(g).drawLine(x_clicked + 125, y_clicked + 100, x_clicked - 125, y_clicked + 100);
        Mockito.verify(g).drawLine(x_clicked - 125, y_clicked + 100, x_clicked, y_clicked);

        x_clicked = 56;
        y_clicked = 68;
        MobilityManagerRectangle mobilityManagerRectangle = new MobilityManagerRectangle(x_clicked,y_clicked);
        view.drawShape(g,mobilityManagerRectangle);
        Mockito.verify(g).drawRect(rectangle.getX_clicked(), rectangle.getY_clicked(), 250, 116);
        Mockito.verify(g).drawLine(x_clicked, y_clicked + 17, x_clicked + 250, y_clicked + 17);

        x_clicked = 59;
        y_clicked = 24;
        MobileComponentRectangle mobilityComponentRectangle = new MobileComponentRectangle(x_clicked,y_clicked);
        view.drawShape(g,mobilityComponentRectangle);
        Mockito.verify(g).drawRect(x_clicked, y_clicked, 250, 70);
        Mockito.verify(g).drawLine(x_clicked, y_clicked + 17, x_clicked + 250, y_clicked + 17);

        x_clicked = 90;
        y_clicked = 68;
        MobilityFunctionRectangle mobilityFunctionRectangle = new MobilityFunctionRectangle(x_clicked,y_clicked);
        view.drawShape(g,mobilityFunctionRectangle);
        Mockito.verify(g).drawRect(x_clicked, y_clicked, 230, 40);
        Mockito.verify(g).drawLine(x_clicked, y_clicked + 17, x_clicked + 230, y_clicked + 17);

        x_clicked = 80;
        y_clicked = 48;
        MobilityActionsRectangle mobilityActionsRectangle = new MobilityActionsRectangle(x_clicked,y_clicked);
        view.drawShape(g,mobilityActionsRectangle);
        Mockito.verify(g).drawRect(x_clicked, y_clicked, 150, 70);
        Mockito.verify(g).drawLine(x_clicked, y_clicked + 17, x_clicked + 150, y_clicked + 17);

        x_clicked = 90;
        y_clicked = 34;
        AbstractNodeRectangle abstractNodeRectangle = new AbstractNodeRectangle(x_clicked,y_clicked, "Abstract Node");
        view.drawShape(g,abstractNodeRectangle);
        Mockito.verify(g).drawRect(x_clicked, y_clicked, 200, 70);
        Mockito.verify(g).drawLine(x_clicked, y_clicked + 17, x_clicked + 200, y_clicked + 17);

        x_clicked = 87;
        y_clicked = 76;
        EventRectangle eventRectangle = new EventRectangle(x_clicked,y_clicked);
        view.drawShape(g,eventRectangle);
        Mockito.verify(g).drawRect(x_clicked, y_clicked, 150, 70);
        Mockito.verify(g).drawLine(x_clicked, y_clicked + 17, x_clicked + 150, y_clicked + 17);

        x_clicked = 89;
        y_clicked = 43;
        MobilityActivityTypeRectangle mobilityActivityTypeRectangle = new MobilityActivityTypeRectangle(x_clicked,y_clicked, "abcd");
        view.drawShape(g,mobilityActivityTypeRectangle);
        Mockito.verify(g).drawRect(x_clicked, y_clicked, 210, 20);

        x_clicked = 120;
        y_clicked = 98;
        DescriptionCircle circle = new DescriptionCircle(x_clicked, y_clicked, 100);
        view.drawShape(g, circle);
        Mockito.verify(g).drawOval(x_clicked, y_clicked, circle.getDiameter(), circle.getDiameter());
    }

}
