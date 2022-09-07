import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Stack;

public class Link {

    int x_start;
    private boolean isEndShapeValid;
    private Shape startShape;
    private String arrowType;
    int y_start;
    int x_end;
    int y_end;
    Color arrowColor;
    int arrowThickness;
    Stack shapeStack;
    private Shape endShape;

    public Shape getStartShape() {
        return startShape;
    }

    public Shape getEndShape() {
        return endShape;
    }

    public int getX_start() {
        return x_start;
    }

    public String getArrowType() {
        return arrowType;
    }

    public int getY_start() {
        return y_start;
    }

    public int getX_end() {
        return x_end;
    }

    public int getY_end() {
        return y_end;
    }

    public Color getArrowColor() {
        return arrowColor;
    }

    public int getArrowThickness() {
        return arrowThickness;
    }

    public Stack getShapeStack() {
        return shapeStack;
    }

    public Link(int x_start, int y_start, int x_end, int y_end, Color arrowColor, String arrowType,
                int arrowThickness, AppDisplayController controller) {
        this.x_start = x_start;
        this.y_start = y_start;
        this.x_end = x_end;
        this.y_end = y_end;
        this.arrowColor = arrowColor;
        this.arrowThickness = arrowThickness;
        this.shapeStack = controller.chooseShapeStack();
        this.arrowType = arrowType;
        startShape = AppDisplayController.findEnclosingShape(shapeStack, x_start, y_start);
        endShape = AppDisplayController.findEnclosingShape(shapeStack, x_end, y_end);
        System.out.println(startShape);
        System.out.println(endShape);
        //isEndShapeValid = controller.performValidityActions(startShape, endShape);
    }

    public void paintShape(Graphics g) {
        if (g != null) {
            Graphics2D g2D = (Graphics2D) g.create();
            g2D.setColor(arrowColor);
            if (arrowType.equals("Function Response Arrow")) {
                Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                        0, new float[]{9}, 0);
                g2D.setStroke(dashed);
            }

            g2D.drawLine(x_start, y_start, x_start, y_end);
            g2D.drawLine(x_start, y_end, x_end, y_end);
            drawArrowHead(g2D);
        }
    }

    private void drawArrowHead(Graphics2D g2) {
        AffineTransform tx = g2.getTransform();
        tx.translate(x_end, y_end);
        //tx.rotate(-Math.PI / 2d);
        g2.setTransform(tx);

        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(0, 0);
        if (x_end > x_start) {
            arrowHead.addPoint(-10, -10);
            arrowHead.addPoint(-10, 10);
        } else if (x_end < x_start) {
            arrowHead.addPoint(10, -10);
            arrowHead.addPoint(10, 10);
        } else if (y_end > y_start) {
            arrowHead.addPoint(-10, -10);
            arrowHead.addPoint(10, -10);
        } else if (y_end < y_start) {
            arrowHead.addPoint(-10, 10);
            arrowHead.addPoint(10, 10);
        }
        g2.fill(arrowHead);
    }

    public boolean isEndShapeValid() {
        return isEndShapeValid;
    }


}