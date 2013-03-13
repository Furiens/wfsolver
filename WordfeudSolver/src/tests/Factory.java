package tests;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

import wordfeud.Board;
import wordfeud.TileHandler;
import wordfeud.WordfeudDictionary;

public class Factory {
	
	private static String boardFilename = "board.txt";
	private static String tileValuesFilename = "tileValues.txt";
	private static String dictionaryFilename = "dictionary.txt";
	
	@Test
	public void filesExistAndReadable()
	{
		Assert.assertTrue(new File(boardFilename).canRead());
		Assert.assertTrue(new File(tileValuesFilename).canRead());
		Assert.assertTrue(new File(dictionaryFilename).canRead());		
	}
	
	public static TileHandler getTileHandler()
	{
		try
		{
			return new TileHandler(tileValuesFilename);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public static Board getBoard()
	{
		try
		{
			return new Board(boardFilename, tileValuesFilename);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public static WordfeudDictionary getWordfeudDictionary()
	{
		try
		{
			return new WordfeudDictionary(dictionaryFilename);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	

}
