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
		dictionary = new Factory().getWordfeudDictionary();
	}
	
	@Test
	public void populatedWithSampleDictionaryTest() throws Exception
	{
		int dictionarySize = dictionary.size();
		Assert.assertTrue(dictionarySize > 20);
	}
	
	@Test
	public void populatedWithFullDictionaryTest() throws Exception {
		int dictionarySize = dictionary.size();
		Assert.assertTrue(dictionarySize > 10000);
	}
	
	@Test
	public void lookupFullWordTest() throws Exception
	{
		Assert.assertFalse(dictionary.quickContains(""));
		Assert.assertFalse(dictionary.quickContains(" "));
		Assert.assertFalse(dictionary.quickContains("tundr"));
		Assert.assertTrue(dictionary.quickContains("tundra"));		
	}
	
	@Test
	public void lookupPartialWordTest() throws Exception
	{
		Assert.assertTrue(dictionary.quickContains("waterfal*"));
		Assert.assertTrue(dictionary.quickContains("*lbatros*"));
		Assert.assertTrue(dictionary.quickContains("*ndra"));
		Assert.assertTrue(dictionary.quickContains("*n*"));
		Assert.assertFalse(dictionary.quickContains("*zjg*"));
		Assert.assertFalse(dictionary.quickContains("jb*"));
		Assert.assertFalse(dictionary.quickContains("*vdq"));
	}
	
	public boolean lookupWord(WordfeudDictionary dict, String word)
	{
		return dict.quickContains(word);
	}

}
