import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class CarInventory extends JFrame {
    private JPanel carInventory;
    private JTextField yearInput;
    private JTextField companyInput;
    private JTextField styleInput;
    private JTextField colorInput;
    private JTextField mileageInput;
    private JButton createNewXMLFileButton;
    private JButton updateXMLButton;

    public CarInventory(){
        setTitle("Car Inventory");
        setContentPane(carInventory);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Action listener for creating a new XML file
        createNewXMLFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createNewXMLFile();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "New XML file created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (ParserConfigurationException | TransformerException | IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error creating XML file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for updating the existing XML file
        updateXMLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateXMLFile();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "XML file updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating XML file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
        pack();
    }

    /**
     * Method to create a new XML file with car information
     */
    private void createNewXMLFile() throws ParserConfigurationException, TransformerException, IOException {
        // Initialize the XML document
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        // Create root element
        Element root = document.createElement("cars");
        document.appendChild(root);

        // Add car details to the XML
        Element car = document.createElement("car");
        root.appendChild(car);

        createCarElement(document, car, "year", yearInput.getText());
        createCarElement(document, car, "company", companyInput.getText());
        createCarElement(document, car, "style", styleInput.getText());
        createCarElement(document, car, "color", colorInput.getText());
        createCarElement(document, car, "mileage", mileageInput.getText());

        // Create XML file
        saveXMLFile(document, "cars.xml");
    }

    /**
     * Method to update the existing XML file with new car information
     */
    private void updateXMLFile() throws Exception {
        File xmlFile = new File("xml/cars.xml");

        if (!xmlFile.exists()) {
            createNewXMLFile();
            return;
        }

        // Parse the existing XML file
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);

        // Add new car details
        Element root = document.getDocumentElement();

        Element car = document.createElement("car");
        root.appendChild(car);

        createCarElement(document, car, "year", yearInput.getText());
        createCarElement(document, car, "company", companyInput.getText());
        createCarElement(document, car, "style", styleInput.getText());
        createCarElement(document, car, "color", colorInput.getText());
        createCarElement(document, car, "mileage", mileageInput.getText());

        // Save the updated XML file
        saveXMLFile(document, "cars.xml");
    }

    /**
     * Utility method to create an XML element
     */
    private void createCarElement(Document document, Element car, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(value));
        car.appendChild(element);
    }

    /**
     * Utility method to save the XML document to file
     */
    private void saveXMLFile(Document document, String fileName) throws TransformerException, IOException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);

        File directory = new File("xml");
        if (!directory.exists()) {
            directory.mkdir();
        }

        StreamResult streamResult = new StreamResult(new File(directory, fileName));
        transformer.transform(domSource, streamResult);
    }

    /**
     * Utility method to clear input fields
     */
    private void clearFields() {
        yearInput.setText("");
        companyInput.setText("");
        styleInput.setText("");
        colorInput.setText("");
        mileageInput.setText("");
    }

    public static void main(String[] args) {
        new CarInventory();
    }
}
