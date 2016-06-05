package build;

import xml.Tagable;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

//Breaking the paragraphs into sentences

public class Paragraph implements Tagable {
    private String para;
    private List<Sentence> sentences;


    public Paragraph(String para){
        this.para = para;
        sentences = breakParagraph(this.para);
    }

    public String getParagraph() {
        return para;
    }

    public void setParagraph(String paragraph) {
        this.para = paragraph;
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentence(String paragraph) {
        this.para = paragraph;
        this.sentences = breakParagraph(this.para);
    }

//   I was going to make a long list of regex, but this seemed much easier to read and understand. 
    
    
    public static List<Sentence> breakParagraph(String para){
        if(para == null || para.isEmpty()) return Collections.emptyList();
        List<Sentence> sentences = new ArrayList<Sentence>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.getDefault());
        iterator.setText(para);
        int endBoundary = iterator.first();
        while(endBoundary != BreakIterator.DONE){
            int startBoundary = endBoundary;
            endBoundary = iterator.next();
            if(endBoundary != BreakIterator.DONE){
                String sentence = para.substring(startBoundary, endBoundary);
                sentences.add(new Sentence(sentence));
            }
        }
        return sentences.isEmpty() ? Collections.emptyList() : sentences;
    }

    @Override
    public String getTagableXML() {
        return "Paragraph";
    }
}
