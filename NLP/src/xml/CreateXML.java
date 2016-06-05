package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import build.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.Collection;

//I tagged all of my objects for xml, 
//and here is where I will convert and create a file out.

public class CreateXML {
    private final String file;
    private final Collection<Paragraph> paragraphs;
    private final DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
    private DocumentBuilder docBuild;
    private Document document;

    public CreateXML(String file, Collection<Paragraph> paragraphs){
        this.file = file;
        this.paragraphs = paragraphs;
        try {
        	docBuild = dBF.newDocumentBuilder();
            document = docBuild.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void writeFile() throws TransformerException {
        if(document == null) return;
        final Element rootElement = document.createElement("File");
        document.appendChild(rootElement);
        this.paragraphs.forEach(paragraph -> paragraphXml(rootElement, paragraph));
        createOutputDoc(document);
    }

    private final void paragraphXml(Element root, Paragraph para){
        Element paragraphElement = document.createElement(para.getTagableXML());
        root.appendChild(paragraphElement);
        para.getSentences().forEach(sentence -> sentenceXMl(paragraphElement, sentence));
    }
    private final void sentenceXMl(Element parent, Sentence sent){
        Element sentenceElement = document.createElement(sent.getTagableXML());
        parent.appendChild(sentenceElement);
        sent.getWords().forEach(word -> wordXML(sentenceElement, word));
    }

    private final void wordXML(Element parent, Word word){
        Element wordElement = document.createElement(word.getTagableXML());
            wordElement.appendChild(document.createTextNode(word.getWord()));
        parent.appendChild(wordElement);
    }

    private final void createOutputDoc (Document document) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(this.file);
        transformer.transform(source, result);
        StreamResult resultSysOut = new StreamResult(System.out);
        transformer.transform(source, resultSysOut);
    }
}
