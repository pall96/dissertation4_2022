import java.util.ArrayList;
import java.util.Arrays;

public class SoftwareComponent extends Shape {

    private int x_clicked;
    private int y_clicked;
    private int[] x_points = new int[3];
    private int[] y_points = new int[3];

    private final ArrayList<String> allowedShapeNameList = new ArrayList<String>(Arrays.asList("Dependency", "Connector"));

    public void setX_clicked(int x_clicked) {
        this.x_clicked = x_clicked;
    }

    public void setY_clicked(int y_clicked) {
        this.y_clicked = y_clicked;
    }

    public int[] getX_points() {
        return x_points;
    }

    public void setX_points(int[] x_points) {
        this.x_points = x_points;
    }

    public int[] getY_points() {
        return y_points;
    }

    public void setY_points(int[] y_points) {
        this.y_points = y_points;
    }

    private final LabelTextFieldPair serviceId = new LabelTextFieldPair("ID");

    private final LabelTextFieldPair componentName = new LabelTextFieldPair("Name");

    public int getX_clicked() {
        return x_clicked;
    }

    public int getY_clicked() {
        return y_clicked;
    }

    SoftwareComponent(int x_clicked, int y_clicked, String type) {
        this.x_clicked = x_clicked;
        this.y_clicked = y_clicked;
        this.getLabeTextAreaPairList().addAll(Arrays.asList(serviceId, componentName));
        this.getShapeName().setText("Component");
        if (type.equals("Component")){
            this.setXmlTagName("componentInstance");
        } else {
            this.setXmlTagName("abstractComponent");
        }
        this.getAllowedShapeNameList().addAll(Arrays.asList("Dependency", "Connector"));
        this.getShapeName().setEditable(false);
    }
}

