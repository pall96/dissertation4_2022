import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
* AppDisplayView class for displaying the UI
* of the application.
*/
public class AppDisplayView extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
	private final AppDisplayController controller = new AppDisplayController(this);
	private Graphics g = null;
	private final JFrame mainFrame = new JFrame();
	private final JPanel commonButtonPanel = new JPanel();
	JTextArea xmlArea = new JTextArea();
	private final JPanel buttonPanel = new JPanel();
	JPanel secondaryButtonPanel = new JPanel();
	private final JButton distributionView = new JButton("Distribution View");
	private final JButton deploymentView = new JButton("Logical Mobility : Deployment View");
	private final JButton mobilityActionsView = new JButton("Mobility Actions View");
	private final JButton eventView = new JButton("Event View");

	public void setMouseClicked(boolean mouseClicked) {
		isMouseClicked = mouseClicked;
	}

	private JButton pressedViewButton = null;

	private JButton pressedButton = null;
	private JScrollPane scrollPane = null;
	ArrayList<JButton> buttonList = null;
	private boolean isMouseClicked;
	private int x_clicked;
	private int y_clicked;
	private boolean isMouseDragged;

	public AppDisplayView() {
		buttonPanel.add(distributionView);
		buttonPanel.add(deploymentView);
		buttonPanel.add(mobilityActionsView);
		buttonPanel.add(eventView);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		distributionView.addActionListener(this);
		deploymentView.addActionListener(this);
		mobilityActionsView.addActionListener(this);
		eventView.addActionListener(this);
		setPreferredSize(new Dimension(9800, 800));
		setBackground(new Color(255,182,193));
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setTitle("ArchStar");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(new Dimension(1000, 1800));
		//mainFrame.setSize(900,900);
		mainFrame.setLocation(300,300);
		buttonPanel.setBackground(new Color(255, 255, 255));
		commonButtonPanel.setLayout(new BorderLayout());
		commonButtonPanel.add(buttonPanel, BorderLayout.NORTH);
		commonButtonPanel.add(secondaryButtonPanel, BorderLayout.CENTER);
		commonButtonPanel.setBackground(new Color(255, 255, 255));
		mainFrame.add(commonButtonPanel, BorderLayout.NORTH);
		mainFrame.setVisible(true);
		//mainFrame.add(this, BorderLayout.CENTER);

		scrollPane = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainFrame.add(scrollPane, BorderLayout.CENTER);

	}

	/**
	 * This is the overriden paint method of JPanel.
	 * @param g the <code>Graphics</code> object to protect
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.g = g;
		controller.chooseAndIterateShapeStack(g);
		isMouseClicked = false;
	}

	/**
	 * This is the implementation of the method
	 * actionPerformed in ActionListener interface.
	 * @param e the event to be processed
	 */
	public void actionPerformed(ActionEvent e) {
		pressedButton = (JButton) e.getSource();
		resetButtonColour();
		pressedButton.setBackground(Color.GRAY);
		ArrayList<String> buttonTextList = null;

		if(pressedButton == distributionView) {
            //controller.getDistributionViewStack().clear();
			repaint();
			this.removeAll();
			pressedViewButton = distributionView;
			secondaryButtonPanel.removeAll();
			//commonButtonPanel.remove(secondaryButtonPanel);
			buttonTextList = new ArrayList<>(Arrays.asList("Node", "Component", "Interface", "Link", "Delete Shape", "Clear Screen","Undo", "Redo",
					"Generate XML", "Export XML", "Export Drawing"));
		} else if (pressedButton == deploymentView) {
			repaint();
			this.removeAll();
			pressedViewButton = deploymentView;
			secondaryButtonPanel.removeAll();
			buttonTextList = new ArrayList<>(Arrays.asList("Mobile Component", "Current Deployment Node", "Allowed Deployment Node",
					"Link", "Delete Shape", "Clear Screen", "Undo", "Redo", "Generate XML", "Export XML", "Export Drawing"));
		} else if (pressedButton == mobilityActionsView) {
			repaint();
			this.removeAll();
			secondaryButtonPanel.removeAll();
			pressedViewButton = mobilityActionsView;
			buttonTextList = new ArrayList<String>(Arrays.asList("Mobility Actions", "Physical Mobility : After Mobility Activity",
					"Logical Mobility : Pre-Mobility Activity", "Logical Mobility : Mobility Activity",
					"Logical Mobility : Post-Mobility Activity", "Function", "Description","Link", "Delete Shape", "Clear Screen", "Generate XML",
					"Export XML", "Export Drawing"));
		} else if (pressedButton == eventView) {
			repaint();
			this.removeAll();
			secondaryButtonPanel.removeAll();
			pressedViewButton = eventView;
			buttonTextList = new ArrayList<String>(Arrays.asList("Mobility Manager", "Link", "Event" , "Description",
					"Abstract Component", "Delete Shape", "Clear Screen", "Undo", "Redo", "Generate XML", "Export XML", "Export Drawing"));
		}

		if (buttonTextList != null) {
			buttonList = createButtons(buttonTextList);
			addListenerToButton(buttonList);
			addButtonsToPanel(secondaryButtonPanel, buttonList);
		}
		secondaryButtonPanel.revalidate();
		secondaryButtonPanel.repaint();
		secondaryButtonPanel.setLayout(new FlowLayout());
		controller.controlDisplay(e);

	}

	public ArrayList<JButton> createButtons(ArrayList<String> buttonText) {
		ArrayList<JButton> buttonList = new ArrayList<>();
		for (String text : buttonText) {
			buttonList.add(new JButton(text));
		}
		return buttonList;
	}

	public void addListenerToButton(ArrayList<JButton> buttonList) {
		for (JButton button : buttonList) {
			button.addActionListener(this);
		}
	}



	public void addButtonsToPanel(JPanel buttonPanel, ArrayList<JButton> buttonList) {
		for (JButton button : buttonList) {
            buttonPanel.add(button);
		}
	}

	public void mouseClicked(MouseEvent e) {
		isMouseClicked = true;
		x_clicked = e.getX();
		y_clicked = e.getY();
		repaint();
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e){
          isMouseDragged = false;
	}

	public void mouseEntered(MouseEvent e) {


	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}

	public void mouseDragged(MouseEvent e) {

		/*x_clicked = e.getX();
		y_clicked = e.getY();
        Object poly = controller.findClickedShape(x_clicked, y_clicked);
		if (poly instanceof NodeRectangle) {
			NodeRectangle rect = (NodeRectangle)poly;
			rect.setX_clicked(x_clicked);
			rect.setY_clicked(y_clicked);
		}

		if (poly instanceof SoftwareComponent) {
			SoftwareComponent t = (SoftwareComponent)poly;
			t.setX_clicked(x_clicked);
			t.setY_clicked(y_clicked);
		}

		repaint();
		SwingUtilities.updateComponentTreeUI(this);*/
	}

	public void drawShape(Graphics g, Object shape) {
		if (g != null) {
			if (shape instanceof NodeRectangle) {

				NodeRectangle rectangle = (NodeRectangle) shape;
				int x_clicked = ((NodeRectangle) shape).getX_clicked();
				int y_clicked = rectangle.getY_clicked();


				g.drawRect(x_clicked, y_clicked, 230, 140);
				rectangle.setWidth(230);
				rectangle.setHeight(140);

				JTextField shapeName = rectangle.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 1, y_clicked + 1, 228, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);

				ArrayList<LabelTextFieldPair> pairList = rectangle.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked + 1, y_clicked + 18, 100, 100);
				setComponentPairFormatting(pairList);

			} else if (shape instanceof SoftwareComponent) {
				SoftwareComponent triangle = (SoftwareComponent) shape;
				int x_clicked = triangle.getX_clicked();
				int y_clicked = triangle.getY_clicked();

				g.drawLine(x_clicked, y_clicked, x_clicked + 125, y_clicked + 100);
				g.drawLine(x_clicked + 125, y_clicked + 100, x_clicked - 125, y_clicked + 100);
				g.drawLine(x_clicked - 125, y_clicked + 100, x_clicked, y_clicked);

				int[] x_points = {x_clicked, x_clicked + 125, x_clicked - 125};
				int[] y_points = {y_clicked, y_clicked + 100, y_clicked + 100};
				triangle.setX_points(x_points);
				triangle.setY_points(y_points);

				JTextField triangleTextField = triangle.getShapeName();
				addTextField(triangleTextField, "Component");
				triangleTextField.setBounds(x_clicked - 50, y_clicked + 40, 80, 15);

				ArrayList<LabelTextFieldPair> pairList = triangle.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked - 80, y_clicked + 60, 60, 90);
				setComponentPairFormatting(pairList);
			} else if (shape instanceof MobilityManagerRectangle) {
				MobilityManagerRectangle rectangle = (MobilityManagerRectangle)shape;
				int x_clicked = rectangle.getX_clicked();
				int y_clicked = rectangle.getY_clicked();

				g.drawRect(x_clicked, y_clicked, 250, 116);
				g.drawLine(x_clicked, y_clicked + 17, x_clicked + 250, y_clicked + 17);

				rectangle.setWidth(250);
				rectangle.setHeight(116);

				JTextField shapeName = rectangle.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 1, y_clicked + 1, 228, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);

				ArrayList<LabelTextFieldPair> pairList = rectangle.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked + 1, y_clicked + 18, 130, 110);
				setComponentPairFormatting(pairList);

			} else if (shape instanceof MobileComponentRectangle) {
				MobileComponentRectangle rectangle = (MobileComponentRectangle) shape;
				int x_clicked = rectangle.getX_clicked();
				int y_clicked = rectangle.getY_clicked();

				g.drawRect(x_clicked, y_clicked, 250, 70);
				g.drawLine(x_clicked, y_clicked + 17, x_clicked + 250, y_clicked + 17);

				rectangle.setWidth(250);
				rectangle.setHeight(70);

				JTextField shapeName = rectangle.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 1, y_clicked + 1, 228, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);

				ArrayList<LabelTextFieldPair> pairList = rectangle.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked + 1, y_clicked + 18, 130, 110);
				setComponentPairFormatting(pairList);

			}
			else if (shape instanceof InterfaceRectangle) {
				InterfaceRectangle rectangle = (InterfaceRectangle) shape;
				int x_clicked = rectangle.getX_clicked();
				int y_clicked = rectangle.getY_clicked();

				g.drawRect(x_clicked, y_clicked, 150, 80);
				g.drawLine(x_clicked, y_clicked + 17, x_clicked + 150, y_clicked + 17);

				rectangle.setWidth(150);
				rectangle.setHeight(80);

				JTextField shapeName = rectangle.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 1, y_clicked + 1, 148, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);

				ArrayList<LabelTextFieldPair> pairList = rectangle.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked + 1, y_clicked + 18, 40, 100);
				setComponentPairFormatting(pairList);

			}
			else if (shape instanceof MobilityFunctionRectangle) {
				MobilityFunctionRectangle rectangle = (MobilityFunctionRectangle) shape;
				int x_clicked = rectangle.getX_clicked();
				int y_clicked = rectangle.getY_clicked();

				g.drawRect(x_clicked, y_clicked, 230, 40);
				g.drawLine(x_clicked, y_clicked + 17, x_clicked + 230, y_clicked + 17);

				rectangle.setWidth(150);
				rectangle.setHeight(60);

				JTextField shapeName = rectangle.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 1, y_clicked + 1, 228, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);

				ArrayList<LabelTextFieldPair> pairList = rectangle.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked + 1, y_clicked + 18, 110, 110);
				setComponentPairFormatting(pairList);

			} else if (shape instanceof MobilityActionsRectangle) {
				MobilityActionsRectangle rectangle = (MobilityActionsRectangle) shape;
				int x_clicked = rectangle.getX_clicked();
				int y_clicked = rectangle.getY_clicked();

				g.drawRect(x_clicked, y_clicked, 150, 70);
				g.drawLine(x_clicked, y_clicked + 17, x_clicked + 150, y_clicked + 17);

				rectangle.setWidth(150);
				rectangle.setHeight(70);

				JTextField shapeName = rectangle.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 1, y_clicked + 1, 98, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);

				ArrayList<LabelTextFieldPair> pairList = rectangle.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked + 1, y_clicked + 18, 70, 70);
				setComponentPairFormatting(pairList);
			} else if (shape instanceof AbstractNodeRectangle) {
				AbstractNodeRectangle subDirectorySquare = (AbstractNodeRectangle) shape;
				int x_clicked = subDirectorySquare.getX_clicked();
				int y_clicked = subDirectorySquare.getY_clicked();

				g.drawRect(x_clicked, y_clicked, 200, 70);
				g.drawLine(x_clicked, y_clicked + 17, x_clicked + 200, y_clicked + 17);

				subDirectorySquare.setWidth(200);
				subDirectorySquare.setHeight(70);

				JTextField shapeName = subDirectorySquare.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 1, y_clicked + 1, 198, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);

				ArrayList<LabelTextFieldPair> pairList = subDirectorySquare.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked + 1, y_clicked + 18, 70, 120);
				setComponentPairFormatting(pairList);
			} else if (shape instanceof EventRectangle) {
				EventRectangle subDirectorySquare = (EventRectangle) shape;
				int x_clicked = subDirectorySquare.getX_clicked();
				int y_clicked = subDirectorySquare.getY_clicked();

				g.drawRect(x_clicked, y_clicked, 150, 70);
				g.drawLine(x_clicked, y_clicked + 17, x_clicked + 150, y_clicked + 17);
				subDirectorySquare.setWidth(150);
				subDirectorySquare.setHeight(70);

				JTextField shapeName = subDirectorySquare.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 1, y_clicked + 1, 98, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);

				ArrayList<LabelTextFieldPair> pairList = subDirectorySquare.getLabeTextAreaPairList();
				addComponentsToView(pairList);
				setComponentPairSize(pairList, x_clicked + 1, y_clicked + 18, 70, 70);
				setComponentPairFormatting(pairList);

			} else if (shape instanceof MobilityActivityTypeRectangle) {
				MobilityActivityTypeRectangle subDirectorySquare = (MobilityActivityTypeRectangle) shape;
				int x_clicked = subDirectorySquare.getX_clicked();
				int y_clicked = subDirectorySquare.getY_clicked();

				g.drawRect(x_clicked, y_clicked, 210, 20);
				subDirectorySquare.setWidth(210);
				subDirectorySquare.setHeight(20);

				JTextField shapeName = subDirectorySquare.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 5, y_clicked + 1, 198, 18);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());
				shapeName.setHorizontalAlignment(JTextField.CENTER);
			} else if (shape instanceof DescriptionCircle) {
				DescriptionCircle circle = (DescriptionCircle) shape;
				int x_clicked = circle.getX_clicked();
				int y_clicked = circle.getY_clicked();

				g.drawOval(x_clicked, y_clicked, circle.getDiameter(), circle.getDiameter());

				JTextField shapeName = circle.getShapeName();
				this.add(shapeName);
				shapeName.setBounds(x_clicked + 20, y_clicked + 20, 85, 16);
				shapeName.setBackground(new Color(255, 182, 193));
				shapeName.setBorder(BorderFactory.createEmptyBorder());

				System.out.println(34);

				JTextArea descriptionField = new JTextArea(5,20);
				//JScrollPane scrollPane = new JScrollPane(descriptionField);
				//scrollPane.setBounds(x_clicked + 8, y_clicked + 40, 300, 55);

				descriptionField.setBounds(x_clicked + 8, y_clicked + 40, 120,55);
				this.add(descriptionField);
			} else if (shape instanceof Link) {
				//JTextField arrowText = arrow.getArrowText();
				//this.add(arrowText);
				//int arrowTextFieldWidth = Math.abs(arrow.getX_start() - arrow.getX_end());
				//arrowText.setBounds(arrow.getX_start(), arrow.getY_start(), arrowTextFieldWidth, 15);
			}
		}
	}

	public void addTextField(JTextField textField, String defaultText) {
		this.add(textField);
		textField.setBackground(new Color(255,182,193));
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBorder(BorderFactory.createEmptyBorder());
		if (textField.getText().equals("")) {
			textField.setText(defaultText);
		}
	}

   public int getX_clicked() {
		return x_clicked;
	}

	public int getY_clicked() {
		return y_clicked;
	}

	public boolean getIsMouseClicked() {
		return isMouseClicked;
	}


	public void addTextAreaOnButtonPress(String xmlGenerated) {
		xmlArea.setTabSize(2);
		xmlArea.setText(xmlGenerated);

		xmlArea.setSize(400, 800);
		JScrollPane scrollPane = new JScrollPane(xmlArea);
		scrollPane.setSize(400,800);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(scrollPane, BorderLayout.EAST);
		scrollPane.setVisible(true);

		//xmlArea.setBorder(BorderFactory.createLineBorder(Color.black));
		xmlArea.setEnabled(false);
	}

	public void addComponentsToView(ArrayList<LabelTextFieldPair> labelTextFieldPairList) {
		for (LabelTextFieldPair pair : labelTextFieldPairList) {
			this.add(pair.getLabel());
			this.add(pair.getTextField());
		}
	}

	public void displayError(String errorMessage){
		JOptionPane.showMessageDialog(null, errorMessage);
	}

	public void setComponentPairSize(ArrayList<LabelTextFieldPair> labelTextFieldPairList, int x_start, int y_start, int labelWidth, int textAreaWidth) {
		x_start += 5;
		y_start += 5;
		if (labelTextFieldPairList.size() != 0) {
			for (LabelTextFieldPair pair : labelTextFieldPairList) {
				pair.getLabel().setBounds(x_start, y_start, labelWidth, 15);
				pair.getTextField().setBounds(x_start + labelWidth, y_start, textAreaWidth, 15);
				y_start += 20;
			}
		}
	}

	public void setComponentPairFormatting(ArrayList<LabelTextFieldPair> labelTextFieldPairList){
		for (LabelTextFieldPair pair : labelTextFieldPairList) {
			pair.getLabel().setBackground(new Color(255,182,193));
			pair.getLabel().setBorder(BorderFactory.createEmptyBorder());
			pair.getTextField().setBackground(new Color(255,0,0));
			pair.getTextField().setBorder(BorderFactory.createEmptyBorder());
		}
	}

	public void resetButtonColour() {
		Color defaultColor = new JButton().getBackground();
		if(pressedButton != distributionView && pressedButton != deploymentView && pressedButton != eventView && pressedButton != mobilityActionsView){
			if (buttonList != null) {
				for (JButton button : buttonList) {
					button.setBackground(defaultColor);
				}
			}
		}else {
			distributionView.setBackground(defaultColor);
			deploymentView.setBackground(defaultColor);
			mobilityActionsView.setBackground(defaultColor);
			eventView.setBackground(defaultColor);
		}


	}

	public JButton getPressedViewButton() {
		return pressedViewButton;
	}

	public void removeSwingComponents(Shape shape){
		this.remove(shape.getShapeName());
		for(LabelTextFieldPair pair : shape.getLabeTextAreaPairList()){
			this.remove(pair.getLabel());
			this.remove(pair.getTextField());
		}
	}
}