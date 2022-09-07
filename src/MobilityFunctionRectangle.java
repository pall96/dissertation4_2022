import java.util.Collections;
import java.util.List;

public class MobilityFunctionRectangle extends ParentRectangle {
    private final LabelTextFieldPair name = new LabelTextFieldPair("Function Name");

    public void setX_clicked(int x_clicked) {
        this.x_clicked = x_clicked;
    }

    public void setY_clicked(int y_clicked) {
        this.y_clicked = y_clicked;
    }

    public int getX_clicked() {
        return x_clicked;
    }

    public int getY_clicked() {
        return y_clicked;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    MobilityFunctionRectangle(int x_clicked, int y_clicked) {
        this.x_clicked = x_clicked;
        this.y_clicked = y_clicked;
        this.getShapeName().setText("Mobility Function");
        this.getLabeTextAreaPairList().addAll(Collections.singletonList(name));
        this.getAllowedShapeNameList().addAll(List.of("File"));
        this.setXmlTagName("function");
    }
}
