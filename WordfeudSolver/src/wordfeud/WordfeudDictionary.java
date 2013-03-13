package wordfeud;

public class WordfeudDictionary {

	/**
	 * Create instance of word lookup object.
	 * @param filename text file containing list of words
	 * @throws Exception 
	 */
	public WordfeudDictionary(String filename) throws Exception {
		
		boolean correctWordlistFormatting = true;
		

		//TODO Auto-generated method stub
		correctWordlistFormatting = false;
		if(!correctWordlistFormatting)
			throw new Exception("Incorrect wordlist format (1 word per line)");
	}
	
	/**
	 * 
	 * @return
	 */
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean contains(String word) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
