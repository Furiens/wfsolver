package wordfeud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordfeudDictionary {

	private Map<Long, ArrayList<String> > dictionary = new HashMap<Long, ArrayList<String> >();
	/**
	 * Create instance of word lookup object.
	 * @param file text file containing list of words
	 * @throws Exception 
	 */
	public WordfeudDictionary(File file) throws Exception {
		
		FileInputStream in = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String word;
		long key;
		while((word = br.readLine()) != null)
		{
			key = hash(word);
			if(dictionary.containsKey(key))
				dictionary.get(key).add(word);
			else
			{
				ArrayList<String> a = new ArrayList<String>();
				a.add(word);
				dictionary.put(key, a);
			}
		}
	}
	
	/**
	 * Implementation of djb2 hash. Supposedly good for English words
	 * @param word
	 * @return
	 */
	private long hash(String word) {
		 long hash = 5381;
		 for(int i = 0; i < word.length(); i++)
		 {
			 hash = ((hash << 5) + hash) + word.charAt(i); /* hash * 33 + c */
		 }
		 return hash;
	}

	/**
	 * Returns number of entries in dictionary. NB. if there are hash collisions, will only count them once.
	 * @return
	 */
	public int size() {
		return dictionary.size();
	}

	/**
	 * Checks for entry with the same hash as given word. Possible rare false positives in case of hash collision.
	 * @param word
	 * @return
	 */
	public boolean quickContains(String word) {
		return dictionary.containsKey(hash(word));
	}
	
	public boolean accurateContains(String word)
	{
		ArrayList<String> a = dictionary.get(hash(word));
		if(a == null)
			return false;
		else
		{
			for(String s : a)
			{
				if(s.equals(word))
					return true;
			}
			return false;
		}
	}
}
