import java.util.Arrays;

public class InterfaceRectangle extends ParentRectangle{
    private final LabelTextFieldPair name = new LabelTextFieldPair("Name");
    private final LabelTextFieldPair ID = new LabelTextFieldPair("ID");
    private final LabelTextFieldPair type = new LabelTextFieldPair("Type");

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

    InterfaceRectangle(int x_clicked, int y_clicked) {
        this.x_clicked = x_clicked;
        this.y_clicked = y_clicked;
        this.getShapeName().setText("Interface");
        this.setXmlTagName("interfaceInstance");
        this.getLabeTextAreaPairList().addAll(Arrays.asList(name, ID, type));
        this.getAllowedShapeNameList().add("File");
    }
}
