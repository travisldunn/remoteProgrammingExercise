package build;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import xml.Tagable;


public class Sentence implements Tagable {
    private String sentence;
    private List<Word> words;
    private static final Pattern Regex = Pattern.compile("\\W");
    private static final String NEW_ENTITY = String.valueOf("NEW_ENTITY".hashCode());

    public Sentence(String sentence) {
        this.sentence = sentence;
        words = new ArrayList<>();
        findEntities();
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

//    Looking for Entities
    
    public void findEntities(){
        String s = this.sentence;
        List<String> entities = Entities.getInstance().getEntityList();
        List<Word> entityWords = new LinkedList<>();
        for(String entity : entities){
            if(foundE(s, entity)){
                int startIndex = s.indexOf(entity);
                Word word = new Word(s.substring(startIndex, startIndex + entity.length()));
                word.setEntity(true);
                entityWords.add(word);
                s = s.replaceFirst(entity, NEW_ENTITY);
            }
        }
        breakSentence(s, entityWords);
    }
    
    private boolean foundE(String source, String term){
        final Pattern p = Pattern.compile("\\b"+term+"\\b");
        final Matcher m = p.matcher(source);
        return m.find();
    }
    
//  Parsing the Sentence into Words.
    
    public void breakSentence(String s, List<Word> entities){
        String sentence = this.sentence; 
        Matcher matcher = Regex.matcher(sentence);
        int start = 0;
        while(matcher.find()){
            if(start != matcher.start()){
                String word = sentence.substring(start,matcher.start());

            	   if(word.equals(NEW_ENTITY)){
                       words.add(entities.remove(0));
                   }else{
                       words.add(new Word(word));
                   }
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
