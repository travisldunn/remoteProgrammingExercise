package build;

import xml.Tagable;

public class Word
    implements Tagable {
	protected String word;
    private boolean Entity;


    public Word(String word) {
        this.word = word;
        Entity = false;
    }
    
    public boolean entity() {
        return Entity;
    }

    public void setEntity(boolean value){
        this.Entity = value;
    }

    public String getWord(){
        return this.word;
    }

    @Override
    public String getTagableXML() {
        return "Word";
    }
}
