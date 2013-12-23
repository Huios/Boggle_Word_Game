import java.util.*;



public class BinarySearchLexicon implements ILexicon {

    private ArrayList<String> myWords;
    
    public BinarySearchLexicon() {
        myWords = new ArrayList<String>();
    }
    
    public void load(Scanner s) {
        myWords.clear();
        while (s.hasNext()){
            myWords.add(s.next().toLowerCase());
        }
        Collections.sort(myWords);
    }

    public void load(ArrayList<String> list) {
        myWords.clear();
        myWords.addAll(list);
        Collections.sort(myWords);
    }

    public LexStatus wordStatus(StringBuilder s) {
        return wordStatus(s.toString());
    }

    Comparator<String> c = new Comparator<String>() {	// my comparative function for ordering
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
    };
    
    public LexStatus wordStatus(String s) {
        
        int n = Collections.binarySearch(myWords, s, c);       
        
        if(n >= 0){	// string is a word in myWords
        	return LexStatus.WORD;
        }
        
        if(n < 0 && -(n+1) < myWords.size()) {
        	
        	if(myWords.get(-(n+1)).startsWith(s)) { 	//string is a prefix of a word in myList
        	
        		return LexStatus.PREFIX;
        	}
        
        else return LexStatus.NOT_WORD;
        	
        	}
        
         return LexStatus.NOT_WORD;		//string is not a word in myList
    }

    public Iterator<String> iterator() {
        return myWords.iterator();
    }

    public int size() {
        return myWords.size();
    }

}
