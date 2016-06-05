package build;

import xml.Tagable;

public class Word
    implements Tagable {
	protected String word;

    public Word(String word) {
        this.word = word;
    }

    public String getWord(){
        return this.word;
    }

    @Override
    public String getTagableXML() {
        return "Word";
    }
}
