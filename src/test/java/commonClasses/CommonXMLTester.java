/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonClasses;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by f3307530 on 2015/02/17.
 */
public class CommonXMLTester {


    private static Document doc;

    public  CommonXMLTester(){
        doc = null;
    }

    public void getDOM(String xmlString)throws IOException, ParserConfigurationException,SAXException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(xmlString);
        doc.getDocumentElement().normalize();
    }

    public void getDOM(File xmlFileName)throws IOException, ParserConfigurationException,SAXException{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        doc = dBuilder.parse(xmlFileName);
        doc.getDocumentElement().normalize();
    }

    public String getElementTextContent(String tag)throws Exception{

        String value= null;
        Node node =  ((Node)XPathFactory.newInstance().newXPath().compile(tag).evaluate(doc,XPathConstants.NODE));


        if(node != null && node.getNodeType() == Node.ELEMENT_NODE){
            if(!node.getTextContent().isEmpty()) {
                value = node.getTextContent();
            }
        }
        return value;
    }

    public String getNodeTextValue(String tag)throws Exception{

        String value= null;
        Node node =  ((Node)XPathFactory.newInstance().newXPath().compile(tag).evaluate(doc,XPathConstants.NODE));

        if(node != null && node.getNodeType() == Node.ELEMENT_NODE)
            node = node.getFirstChild();


        if(node != null && node.getNodeType() == Node.TEXT_NODE){
            if(!node.getNodeValue().isEmpty()) {
                value = node.getNodeValue();
            }
        }
        return value;
    }

    public void updateTextNodeValue(String tag, String nodeValue)throws XPathException,SAXException {

        Node node = ((Node) XPathFactory.newInstance().newXPath().compile(tag).evaluate(doc, XPathConstants.NODE));
        if (node != null)
            node = node.getFirstChild();

        if (node != null && node.getNodeType() == Node.TEXT_NODE) {
            if (!node.getNodeValue().isEmpty()) {
                node.setNodeValue(nodeValue);
            }else{
                throw new SAXException("XML node is not appendable "+tag);
            }
        }
        if(node.getNodeValue().isEmpty())
            throw new SAXException("Could not get the XML node "+tag);
    }

    public void updateElementValue(String tag, String content) {
        NodeList tagNodes = doc.getElementsByTagName(tag);
        Element element = null;
        for(int i=0; i<tagNodes.getLength();i++){
            element = (Element) tagNodes.item(i);
            Node tags = element.getElementsByTagName("name").item(0).getFirstChild();
            tags.setNodeValue(content);
        }
    }

    public void updateElementNodeValue(String tag, String nodeValue)throws XPathException, SAXException{

        Node node =((Node)XPathFactory.newInstance().newXPath().compile(tag).evaluate(doc,XPathConstants.NODE));
        if(node != null && node.getNodeType() == Node.ELEMENT_NODE){
            node.setTextContent(nodeValue);
        }else{
            throw  new SAXException("Could not get the XML node "+tag);
        }

    }

    public void writeDocToFile(String filePath)throws IOException{
        doc.normalizeDocument();
        OutputFormat format = new OutputFormat(doc);
        format.setIndenting(true);
        if(!filePath.contains("."))
            filePath+=".xml";

        XMLSerializer serializer = new XMLSerializer(new FileOutputStream(new File(filePath)), format);
        serializer.serialize(doc);

       /* Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform
                (new DOMSource(doc), new StreamResult(new File(fileName)));
*/
    }

}
