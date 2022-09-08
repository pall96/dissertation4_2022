import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing a rectangle
 */
public class NodeRectangle extends ParentRectangle {

	private final LabelTextFieldPair nodeName = new LabelTextFieldPair("Node_Name:");
	private final LabelTextFieldPair nodeID = new LabelTextFieldPair("Node_ID:");
	private final LabelTextFieldPair nodeType = new LabelTextFieldPair("Node_Type:");
	private final LabelTextFieldPair nodeIP = new LabelTextFieldPair("Node_IP");
	private final LabelTextFieldPair nodeLatitude = new LabelTextFieldPair("Node_Latitude:");
	private final LabelTextFieldPair nodeLongitude = new LabelTextFieldPair("Node_Longitude:");


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

	NodeRectangle(int x_clicked, int y_clicked) {
		this.x_clicked = x_clicked;
		this.y_clicked = y_clicked;
		this.getShapeName().setText("Node");
		this.setXmlTagName("Node");
		this.getLabeTextAreaPairList().addAll(Arrays.asList(nodeName, nodeID, nodeType, nodeIP, nodeLatitude, nodeLongitude));
		this.getAllowedShapeNameList().addAll(List.of("Service"));
		this.getShapeName().setEditable(false);
	}
}

