import javax.swing.*;
import java.util.ArrayList;

public class Shape {

    private JTextField shapeName = new JTextField(8);
    private String xmlTagName = null;
    private final ArrayList<LabelTextFieldPair> labeTextAreaPairList = new ArrayList<LabelTextFieldPair>();
    private final ArrayList<String> allowedShapeNameList = new ArrayList<String>();

    public String getXmlTagName() {
        return xmlTagName;
    }

    public void setXmlTagName(String xmlTagName) {
        this.xmlTagName = xmlTagName;
    }

    public ArrayList<LabelTextFieldPair> getLabeTextAreaPairList() {
        return labeTextAreaPairList;
    }

    public JTextField getShapeName() {
        return shapeName;
    }

    public ArrayList<String> getAllowedShapeNameList() {
        return allowedShapeNameList;
    }

    public void setShapeName(JTextField shapeName) {
        this.shapeName = shapeName;
    }
}
