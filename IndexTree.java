import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class IndexTree {

	private IndexNode root;
	
	// Make your constructor
	public IndexTree() {
		this.root = null;
	}
	// complete the methods below
	
	
	// calls your recursive add method
	public void add(String word, int lineNumber){
		 this.root = add(this.root, word, lineNumber);
	}
	
	
	
	//recursive method for add
	private IndexNode add(IndexNode root, String word, int lineNumber){
		if (root == null) {
			IndexNode temp = new IndexNode(word,lineNumber);
            return temp;
        }
		int comparisonResult = word.compareTo(root.word);
		if (comparisonResult == 0) {
			root.occurences++;
			root.list.add(lineNumber);
			return root;
        } else if (comparisonResult < 0) {
            root.left = add(root.left, word,lineNumber);
            return root;
        } else {
            root.right = add(root.right, word,lineNumber);
            return root;
        }

    }
	
	
	
	
	
	// returns true if the word is in the index
	public boolean contains(String word){
		return contains(this.root, word);
	}
	
	public boolean contains(IndexNode root, String word) {
		 if (root == null) {
	            return false;
	        }
	        int comparisonResult = word.compareTo(root.word);
	        if (comparisonResult == 0) {
	            return true;
	        } else if (comparisonResult < 0) {
	            return contains(root.left, word);
	        } else {
	            return contains(root.right, word);
	        }
	}
	
	// calls recursive method
	public void delete(String word){
		this.root = delete(this.root, word);
	}
	
	// removes the word and all the entries for the word
	private IndexNode delete(IndexNode root, String word){
		 if (root == null) {
	            return null;
	        }
	        int comparisonResult = word.compareTo(root.word);
	        if (comparisonResult < 0) {
	            root.left = delete(root.left, word);
	            return root;
	        } else if (comparisonResult > 0) {
	            root.right = delete(root.right, word);
	            return root;
	        } else {  // root is the item we want to delete

	            // case 1, root is leaf
	            if (root.left == null && root.right == null) {
	                return null;
	            } // case 2, root has only left child
	            else if (root.left != null && root.right == null) {
	                return root.left;
	            } else if (root.left == null && root.right != null) {
	                return root.right;
	            } else {
	                IndexNode rootOfLeftSubtree = root.left;
	                IndexNode parentOfPredecessor = null;
	                IndexNode predecessor = null;

	                if (rootOfLeftSubtree.right == null) {
	                    root.word = rootOfLeftSubtree.word;
	                    root.left = rootOfLeftSubtree.left;
	                    return root;
	                } else {
	                    parentOfPredecessor = rootOfLeftSubtree;
	                    IndexNode current = rootOfLeftSubtree.right;
	                    while (current.right != null) {
	                        parentOfPredecessor = current;
	                        current = current.right;
	                    }

	                    predecessor = current;
	                    root.word = predecessor.word;
	                    root.occurences = predecessor.occurences;
	                    root.list = predecessor.list;
	                    parentOfPredecessor.right = predecessor.left;
	                    return root;

	                }
	            }

	        }

	}
	
	
	// prints all the words in the index in inorder order
	// each word and its data gets its own line
	public String printIndex(IndexNode root){
		if(root == null) {
			return "";
		}
		String output = "";
		output += printIndex(root.left);
		output += root.toString()+"\n";
		output += printIndex(root.right);
		return output;
		
	}
	
	public static void main(String[] args){
		IndexTree index = new IndexTree();
		//List<String> list = new ArrayList<>();
		String fileName = "pg100.txt";
		int linecount = 0;
		try {
			Scanner scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()){
				linecount += 1;
				String line = scanner.nextLine();
				String[] words = line.split("\\s+");
				for(String word : words){
					word = word.replaceAll("[:,';-?]", "");
					word = word.replaceAll("-", "");
					word = word.replaceAll("--", "");
					word = word.replace('.', ' ');
					word = word.replace('!', ' ');
					word = word.replaceAll(" ", "");
					index.add(word, linecount);
				}
				
			}
		}
		catch(FileNotFoundException e1){
			e1.printStackTrace();
		}
		
		
		
		index.delete("zounds");
		
		System.out.println(index.printIndex(index.root));
		
		
		
	}
}
