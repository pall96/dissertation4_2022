import java.util.Arrays;

public class MobileComponentRectangle extends ParentRectangle{
    private final LabelTextFieldPair deployedNodeName = new LabelTextFieldPair("Name");
    private final LabelTextFieldPair deployedNodeID = new LabelTextFieldPair("ID");

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

    MobileComponentRectangle(int x_clicked, int y_clicked) {
        this.x_clicked = x_clicked;
        this.y_clicked = y_clicked;
        this.getLabeTextAreaPairList().addAll(Arrays.asList(deployedNodeName, deployedNodeID));
        this.getShapeName().setText("Mobile Component");
        this.setXmlTagName("mobileComponent");
        this.getAllowedShapeNameList().addAll(Arrays.asList("SubDirectory", "File"));
        this.getShapeName().setEditable(false);
    }
}
