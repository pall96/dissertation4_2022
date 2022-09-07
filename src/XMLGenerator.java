import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class XMLGenerator {

    private String generatedXML;
    private int counter = 0;
    private ArrayList<String> elementOrder = new ArrayList<>();
    HashMap<Shape, ArrayList<Shape>> hashMap = new HashMap<>();

    public String getGeneratedXML() {
        return generatedXML;
    }

    /**
     * Struct class to store String representations of XML elements
     */
    private class XMLElement {
        public String open; // eg. <A class=a>
        public String close; // eg. </A>
        public ArrayList<XMLElement> children = new ArrayList<>();
    }

    public String generateEntireXML(Stack shapeStack) {
        HashSet<XMLElement> roots = buildElementTree(shapeStack);
        StringBuilder builder = new StringBuilder();
        for (XMLElement current : roots) {
            builder.append(generateXML(current, 1)).append("\n");
        }
        generatedXML = builder.toString();
        return builder.toString();
    }

    public String generateXML(XMLElement element, int depth) {
        if (element.children.isEmpty()) return element.open + element.close;
        StringBuilder builder = new StringBuilder();
        builder.append(element.open);
        depth++;
        for (XMLElement child : element.children) {
            builder.append("\n").append("\t".repeat(depth)).append(generateXML(child, depth)).append("\n");
        }
        builder.append(element.close);
        return builder.toString();
    }



    public void exportToFile(String pressedViewButton){
        String fileName = createFile(pressedViewButton);
        writeToFile(fileName);
    }

    public void writeToFile(String fileName){
        Path path = Paths.get(fileName);
        try {
            Files.writeString(path, generatedXML, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Exception occurred");
        }
    }

    public String createFile(String pressedViewButtonText) {
        String home = System.getProperty("user.home");
        LocalDateTime localDateTime = LocalDateTime.now();
        String fileName = pressedViewButtonText + localDateTime;
        String modifiedFileName = fileName.replaceAll("\\s+|-|:|[.]","_");
        String pathName = home + "\\Downloads\\" + modifiedFileName + ".txt";
        File myFile = new File(modifiedFileName);
        try {
            myFile.createNewFile();
        } catch (IOException e) {
            System.out.println("File already exists");
        }
        return pathName;
    }

    private String[] getXMLElementString(Shape shape) {
        return new String[]{"<" + shape.getXmlTagName() + " " + generateAttributeString(shape) + ">", "</" + shape.getXmlTagName() + ">"};
    }

    public HashSet<XMLElement> buildElementTree(Stack shapeStack) {
        System.out.println(shapeStack);
        HashSet<XMLElement> roots = new HashSet<>();
        HashMap<Object, XMLElement> visitedElements = new HashMap<>();
        for (Object o : shapeStack) {
            if (o instanceof Link) {
                Link arrow = (Link) o;
                Shape start = arrow.getStartShape();
                Shape end = arrow.getEndShape();
                if (!visitedElements.containsKey(end)) {
                    XMLElement endXML = new XMLElement();
                    String[] XMLstr = getXMLElementString(end); // Gets XML element, eg [<A>, </A>]
                    endXML.open = XMLstr[0];
                    endXML.close = XMLstr[1];
                    visitedElements.put(end, endXML);
                } else {
                    roots.remove(visitedElements.get(end));
                }

                if (!visitedElements.containsKey(start)) {
                    XMLElement startXML = new XMLElement();
                    String[] XMLstr = getXMLElementString(start); // Gets XML element, eg [<A>, </A>]
                    startXML.open = XMLstr[0];
                    startXML.close = XMLstr[1];
                    visitedElements.put(start, startXML);
                    roots.add(startXML);
                }

                XMLElement startXML = visitedElements.get(start);
                startXML.children.add(visitedElements.get(end));
            }
        }
        return roots;
    }







    public String generateAttributeString(Shape key) {
        ArrayList<LabelTextFieldPair> pairList = key.getLabeTextAreaPairList();
        StringBuilder attributeString = new StringBuilder();
        if (pairList.size() != 0) {
            for (LabelTextFieldPair labelTextFieldPair : pairList) {
                attributeString.append(" ")
                        .append(labelTextFieldPair.getLabel().getText().replaceAll(" ", "_").toLowerCase())
                        .append(": = ").append("\"").append(labelTextFieldPair.getTextField().getText()).append("\"");
            }
        }
        return attributeString.toString();
    }

    public String addCounter(int counter) {
        return "\t" + "\t".repeat(Math.max(0, counter));
    }

    public void setElementOrder(ArrayList<String> elementOrder) {
        this.elementOrder = elementOrder;
    }
}