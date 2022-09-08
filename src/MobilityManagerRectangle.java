import java.util.Arrays;

public class MobilityManagerRectangle extends ParentRectangle {

    private final LabelTextFieldPair name = new LabelTextFieldPair("Name");
    private final LabelTextFieldPair ID = new LabelTextFieldPair("ID");
    private final LabelTextFieldPair deployedNodeName = new LabelTextFieldPair("Deployed Node Name");
    private final LabelTextFieldPair deployedNodeID = new LabelTextFieldPair("Deployed Node ID");

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

    MobilityManagerRectangle(int x_clicked, int y_clicked) {
        this.x_clicked = x_clicked;
        this.y_clicked = y_clicked;
        this.getLabeTextAreaPairList().addAll(Arrays.asList(name, ID, deployedNodeName, deployedNodeID));
        this.getShapeName().setText("Mobility Manager");
        this.getAllowedShapeNameList().addAll(Arrays.asList("SubDirectory", "File"));
        this.setXmlTagName("mobilityManager");
        this.getShapeName().setEditable(false);
    }
}
