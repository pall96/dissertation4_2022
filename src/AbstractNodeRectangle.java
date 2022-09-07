import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbstractNodeRectangle extends ParentRectangle {
    private final LabelTextFieldPair name = new LabelTextFieldPair("Node Name");
    private final LabelTextFieldPair ID = new LabelTextFieldPair("Node ID");

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

    AbstractNodeRectangle(int x_clicked, int y_clicked, String type) {
        this.x_clicked = x_clicked;
        this.y_clicked = y_clicked;
        this.getShapeName().setText(type);
        this.setXmlTagName("abstractNode");
        this.getLabeTextAreaPairList().addAll(Arrays.asList(name, ID));
        this.getAllowedShapeNameList().add("File");
    }
}
