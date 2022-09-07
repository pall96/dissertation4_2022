import javax.swing.*;

public class LabelTextFieldPair {
    JLabel label = new JLabel();
    JTextField textField = new JTextField();

    LabelTextFieldPair(String labelText) {
        label.setText(labelText);
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return textField;
    }
}
