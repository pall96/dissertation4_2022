import java.util.Arrays;
import java.util.List;

public class EventRectangle extends ParentRectangle {

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

    EventRectangle(int x_clicked, int y_clicked) {
        this.x_clicked = x_clicked;
        this.y_clicked = y_clicked;
        this.getShapeName().setText("Event");
        LabelTextFieldPair name = new LabelTextFieldPair("Event Name");
        LabelTextFieldPair ID = new LabelTextFieldPair("Event ID");
        this.getLabeTextAreaPairList().addAll(Arrays.asList(name, ID));
        this.getAllowedShapeNameList().addAll(List.of("File"));
        this.setXmlTagName("event");
        this.getShapeName().setEditable(false);
    }
}
