import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class IndexNode  {

	// The word for this entry
	String word;
	// The number of occurrences for this word
	int occurences;
	// A list of line numbers for this word.
	List<Integer> list = new ArrayList<>();
	
	IndexNode left;
	IndexNode right;
	
	
	
	
	public IndexNode() {
		this.word = null;
		occurences = 1;
	}
	
	public IndexNode(String word, int lineNum) {
		this.occurences = 1;
		this.word = word;
		this.list = new ArrayList<Integer>();
		this.list.add(lineNum);
		
		
	}
	
	
	
	
	
	
	
	// returns the word, the number of occurrences, and the lines it appears on.
	public String toString(){
		String output = "Lines it appears in: ";
		for(int i = 0; i < list.size(); i++) {
			if(i == list.size()-1) {
				output+= list.get(i);
			}
			else {
				output+= list.get(i)+", ";
			}
		}
		return this.word+"/ # of Ocurrences: "+occurences+" "+output;
	}
	
	
	
}
