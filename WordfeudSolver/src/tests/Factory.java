package tests;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import junit.framework.Assert;

import org.junit.Test;

import wordfeud.Board;
import wordfeud.TileHandler;
import wordfeud.WordfeudDictionary;

public class Factory {
	
	private static String boardFilename = "board.txt";
	private static String tileValuesFilename = "tileValues.txt";
	private static String dictionaryFilename = "dictionary.txt";
	
	private File getFileInClassPath(String filename) throws Exception
	{
		URL path = getClass().getResource(filename);
		if(path==null) {
		     throw new Exception("Couldn't load "+filename);
		}
		return new File(path.toURI());
	}
	@Test
	public void filesExistAndReadable() throws Exception
	{
		Assert.assertTrue((getFileInClassPath(boardFilename)).canRead());
		Assert.assertTrue((getFileInClassPath(tileValuesFilename)).canRead());
		Assert.assertTrue((getFileInClassPath(dictionaryFilename)).canRead());		
	}
	
	public TileHandler getTileHandler()
	{
		try
		{
			return new TileHandler(getFileInClassPath(tileValuesFilename));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public Board getBoard()
	{
		try
		{
			return new Board(getFileInClassPath(boardFilename), getFileInClassPath(tileValuesFilename));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public WordfeudDictionary getWordfeudDictionary()
	{
		try
		{
			return new WordfeudDictionary(getFileInClassPath(dictionaryFilename));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	

}
