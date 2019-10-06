package objectRepository;

import org.openqa.selenium.By;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by F4852214 on 11-02-2015.
 Modified by F47390000 on  17-07-2015
 */
public class PageObjects {

    public static  Document doc = null;
    private Node xmlElement = null;
    private Map<String, String> mapElement = new HashMap<String, String>();

    public String getXmlElementNode() {
        return xmlElementNode;
    }

    public void setXmlElementNode(String xmlElementNode) {
        this.xmlElementNode = xmlElementNode;
    }

    private String xmlElementNode = null;

    public PageObjects(){};

    public Node getXmlElement() {
        return xmlElement;
    }

    public Map<String, String> getMapElement() {
        return mapElement;
    }

    public void getDOM(String xmlFileName)throws Exception{
            File fXmlFile = new File(xmlFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

    }

    public NodeList getAllElementsInModule(String strModuleName, String xmlPageNode) {

        NodeList nodeModules = null;
        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            ////module[@name='']//*
            String strXpath = "//" + xmlPageNode + "[@name='" + strModuleName + "']//*";
            XPathExpression expr = xpath.compile(strXpath);
            Object result = expr.evaluate(doc, XPathConstants.NODESET);
            nodeModules = (NodeList) result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return nodeModules;
    }

    //Return xml element xpath
    public void getXMLElement(String strElementName,String xmlElementNode) {

        String strXpath;

        try {
            XPathFactory factory = XPathFactory.newInstance();
            XPath xpath = factory.newXPath();

            if (strElementName.contains(".")) {
                String strPage = strElementName.split("\\.")[0];
                String strElement = strElementName.split("\\.")[1];
                //module[@name='']/element[@name='']
                strXpath = "//" + xmlElement + "[@name='" + strPage + "']/element[@name='" + strElement + "']";
            } else {
                ////element[@name='']
                strXpath = "//" + xmlElementNode + "[@name='" + strElementName + "']";
            }

            XPathExpression expr = xpath.compile(strXpath);
            Object result = expr.evaluate(doc, XPathConstants.NODE);
            xmlElement = (Node) result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return xmlElement;
    }

    public void getElementProperties() {


        try {
            //Node xmlElement = getXMLElement(strElementName,xmlElementNode);
            if (xmlElement != null) {
                mapElement.put("name", xmlElement.getAttributes().getNamedItem("name").getNodeValue());
                mapElement.put("by", xmlElement.getAttributes().getNamedItem("by").getNodeValue());
                mapElement.put("identifier", xmlElement.getAttributes().getNamedItem("identifier").getNodeValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return mapElement;
    }


    /* Demo */
    public static void main(String[] args) {
        PageObjects pageObjects = new PageObjects();
        String fileName="Resources/PageObjects.xml";
      //  String xmlPageNode="page";
      //  String xmlPageName="login";
        String strElementName="Username";
        String xmlElementNode="element";

       // pageObjects.getDOM(fileName);
        //  pageObjects.getAllElementsInModule(xmlPageNode,xmlPageName);
        // pageObjects.getAllElementsInModule(xmlPageName,xmlPageNode);
      //  pageObjects.getXMLElement(strElementName,xmlElementNode);
       // pageObjects.getElementProperties();

       // pageObjects.getByElement(strElementName,xmlElementNode);
       // System.out.println(pageObjects.getByElement().toString());
        //System.out.println(pageObjects.getMapElement().get("identifier"));

        //  System.out.println(objR.getElementProperties("customer.btnSwiftApplication"));
    }

    public By getByElement(String strElementName,String xmlElementNode) {

        By by = null;
        getXMLElement(strElementName,xmlElementNode);
        getElementProperties();

        if (!mapElement.isEmpty()) {

            String strBy = mapElement.get("by");
            String strIdentifier = mapElement.get("identifier");

            if (strBy.equalsIgnoreCase("css")) {
                by = By.cssSelector(strIdentifier);
            } else if (strBy.equalsIgnoreCase("xpath")) {
                by = By.xpath(strIdentifier);
            } else if (strBy.equalsIgnoreCase("name")) {
                by = By.name(strIdentifier);
            } else if (strBy.equalsIgnoreCase("linkText")) {
                by = By.linkText(strIdentifier);
            } else if (strBy.equalsIgnoreCase("id")) {
                by = By.id(strIdentifier);
            } else if (strBy.equalsIgnoreCase("partialLinkText")) {
                by = By.partialLinkText(strIdentifier);
            }else if (strBy.equalsIgnoreCase("tagName")) {
                by = By.tagName(strIdentifier);
            }else if (strBy.equalsIgnoreCase("className")) {
                by = By.className(strIdentifier);
            }

        }


        return by;
    }

    public By getByElement(String strElementName) {

        By by = null;
        getXMLElement(strElementName,xmlElementNode);
        getElementProperties();

        if (!mapElement.isEmpty()) {

            String strBy = mapElement.get("by");
            String strIdentifier = mapElement.get("identifier");

            if (strBy.equalsIgnoreCase("css")) {
                by = By.cssSelector(strIdentifier);
            } else if (strBy.equalsIgnoreCase("xpath")) {
                by = By.xpath(strIdentifier);
            } else if (strBy.equalsIgnoreCase("name")) {
                by = By.name(strIdentifier);
            } else if (strBy.equalsIgnoreCase("linkText")) {
                by = By.linkText(strIdentifier);
            } else if (strBy.equalsIgnoreCase("id")) {
                by = By.id(strIdentifier);
            } else if (strBy.equalsIgnoreCase("partialLinkText")) {
                by = By.partialLinkText(strIdentifier);
            }else if (strBy.equalsIgnoreCase("tagName")) {
                by = By.tagName(strIdentifier);
            }else if (strBy.equalsIgnoreCase("className")) {
                by = By.className(strIdentifier);
            }

        }
        mapElement.clear();
        return by;
    }


}
