package build;

import xml.Tagable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Sentence implements Tagable {
    private String sentence;
    private List<Word> words;
    private static final Pattern Regex = Pattern.compile("\\W");

    public Sentence(String sentence) {
        this.sentence = sentence;
        words = new ArrayList<>();
        breakSentence();
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public List<Word> getWords() {
        return this.words;
    }

//    Parsing the Sentence into Words.
    
    public void breakSentence(){
        String sentence = this.sentence; 
        Matcher matcher = Regex.matcher(sentence);
        int start = 0;
        while(matcher.find()){
            if(start != matcher.start()){
                words.add(new Word(sentence.substring(start, matcher.start())));
                start = matcher.end();
            }else{
                start++;
            }
        }
    }

    @Override
    public String getTagableXML() {
        return "Sentence";
    }
}
