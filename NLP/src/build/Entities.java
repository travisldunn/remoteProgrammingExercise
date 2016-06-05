package build;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

//Here I am creating a Named Entity Class that reads from NER.txt to find 
//and store Entities for later comparisons 

public class Entities {
	
	  private List<String> entities;
	    private static Entities instance;

	    private Entities(){
	        try {
	            entities = Files.lines(Paths.get("NER.txt"))
	                    .filter(line -> !line.trim().isEmpty())
	                    .collect(Collectors.toList());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static Entities getInstance(){
	        if(instance == null){
	            instance = new Entities();
	        }
	        return instance;
	    }

	    public List<String> getEntityList(){
	        return this.entities;
	    }

}
