import javax.swing.*;

public class DescriptionCircle extends ParentCircle {

    private JTextArea descriptionArea = new JTextArea(100,100);

    private JScrollPane scrollPane = new JScrollPane(descriptionArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    public DescriptionCircle(int x_clicked, int y_clicked, int diameter){
        this.x_clicked = x_clicked;
        this.y_clicked = y_clicked;
        this.diameter = diameter;
        this.x_center = calculateX_Center();
        this.y_center = calculateY_Center();
        LabelTextFieldPair descriptionLabel = new LabelTextFieldPair("");
        this.getLabeTextAreaPairList().add(descriptionLabel);
        this.getShapeName().setText("Description");
        this.setXmlTagName("description");
        this.getShapeName().setEditable(false);
    }

    public int calculateX_Center() {
        return x_clicked + diameter/2;
    }

    public int calculateY_Center() {
        return y_clicked + diameter/2;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getX_clicked() {
        return x_clicked;
    }

    public int getY_clicked() {
        return y_clicked;
    }
}
