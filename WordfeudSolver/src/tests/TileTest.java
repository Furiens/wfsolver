package tests;

import static org.junit.Assert.*;

import junit.framework.Assert;

import org.junit.Test;
import wordfeud.Tile;
import wordfeud.TileHandler;

public class TileTest {
	
	@Test
	public void boardSquaresTest() throws Exception
	{
		String filename = "tileValues.txt";
		TileHandler tileHandler = new TileHandler(filename);
		String stringWord1 = "apple";
		Tile[] tileWord1 = tileHandler.getTiles(stringWord1);
		Assert.assertTrue(checkEquals(tileWord1, stringWord1));
		
		String stringWord2 = "a$ple"; //$ for as 'blank' tile
		Tile[] tileWord2 = tileHandler.getTiles(stringWord2);
		Assert.assertTrue(checkEquals(tileWord2, stringWord2));
		
		try
		{
			tileHandler.getTiles("Æpple"); //Should fail, Æ not found in tileValues
			Assert.fail();
		}
		catch (Exception ex)
		{
			;
		}
	}
	
	private boolean checkEquals(Tile[] tilesWord, String stringWord)
	{
		if(tilesWord.length != stringWord.length())
			return false;
		try
		{
			for(int i = 0; i < tilesWord.length; i++)
			{
				Assert.assertEquals(tilesWord[i].getLetter(), stringWord.charAt(i));
			}
		}
		catch (Exception ex)
		{
			return false;
		}
		return true;
	}

}
