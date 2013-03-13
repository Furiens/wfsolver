package tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import wordfeud.WordfeudDictionary;

public class DictionaryTest {
	
	WordfeudDictionary dictionary;
	
	@Before
	public void constructDictionary()
	{
		dictionary = Factory.getWordfeudDictionary();
	}
	
	@Test
	public void populateTest() throws Exception {
		int dictionarySize = dictionary.size();
		Assert.assertTrue(dictionarySize > 1000);
	}
	
	@Test
	public void lookupFullWordTest() throws Exception
	{
		Assert.assertFalse(lookupWord(dictionary, ""));
		Assert.assertFalse(lookupWord(dictionary, " "));
		Assert.assertFalse(lookupWord(dictionary, "tundr"));
		Assert.assertTrue(lookupWord(dictionary, "tundra"));		
	}
	
	@Test
	public void lookupPartialWordTest() throws Exception
	{
		Assert.assertTrue(lookupWord(dictionary, "waterfal*"));
		Assert.assertTrue(lookupWord(dictionary, "*lbatros*"));
		Assert.assertTrue(lookupWord(dictionary, "*ndra"));
		Assert.assertTrue(lookupWord(dictionary, "*n*"));
		Assert.assertFalse(lookupWord(dictionary, "*zjg*"));
		Assert.assertFalse(lookupWord(dictionary, "jb*"));
		Assert.assertFalse(lookupWord(dictionary, "*vdq"));
	}
	
	public boolean lookupWord(WordfeudDictionary dict, String word)
	{
		return dict.contains(word);
	}

}
