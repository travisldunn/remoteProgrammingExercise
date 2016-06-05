package fileReadIn;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import build.Paragraph;
import xml.CreateXML;


public class ReadFile  {

    private String fileName = "nlp_data.txt";
    private List<String> breaks;

    public ReadFile() {
       
    }

//    Breaking the code up by lines using java.util.stream.Collectors
    
    public void breakLines() {
        try{
            breaks = Files.lines(Paths.get(fileName))
                    .filter(p -> !p.trim().isEmpty())
                    .collect(Collectors.toList());
        }catch(IOException e){
            e.printStackTrace();
        }
        
        breakParagraphs();
    }
    
    
//    Using the breaks to identify paragraphs for easier manipulation 
    
    
    public void breakParagraphs() {
        try {

            List<Paragraph> paragraphs = new ArrayList<Paragraph>();
            for(String breaks : breaks){
                paragraphs.add(new Paragraph((breaks)));
            }
            CreateXML xmlBuilder = new CreateXML("theOutputXMLFile.xml", paragraphs);
            xmlBuilder.writeFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
