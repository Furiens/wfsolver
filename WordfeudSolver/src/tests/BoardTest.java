package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import wordfeud.Board;
import wordfeud.Direction;
import wordfeud.PossibleWord;
import wordfeud.Square;
import wordfeud.SquarePosition;
import wordfeud.TileHandler;

public class BoardTest {
	
	Board board;
	
	@Before
	public void constructBoard()
	{
		board = Factory.getBoard();
	}
	
	
	
	
	@Test
	public void boardConstructorTest() throws Exception
	{
		Assert.assertNotNull(board);
	}
	
	/**
	 * 	Populate board to look like:
	 * 	apple
	 *    e
	 *    r
	 *  also
	 *fin only
	 *    n
	 *    a
	 *    l
	 *    
	 *    
	 *
	 * 					
	 * @throws Exception
	 */
	@Test
	public void boardSquaresTest() throws Exception
	{
		int x = 7, y = 7;
		SquarePosition middleSquare = new SquarePosition(x, y);
		String word1 = "apple";
		SquarePosition word2Square = new SquarePosition(x+2, y);
		String word2 = "person";
		SquarePosition word3Square = new SquarePosition(x, y+3);
		String word3 = "also";
		SquarePosition word4Square = new SquarePosition(x+2, y+4);
		String word4 = "only";
		SquarePosition word5Square = word3Square;
		String word5 = "personal";
		String word6TooLong = "personally";
		SquarePosition word6Square = word3Square;
		String word7 = "fin";
		SquarePosition word7Square = new SquarePosition(x-2, y+4);
		
		
		Assert.assertFalse(board.canPlaceWord(word1, word2Square, Direction.Horizontal)); //must start with centre square
		Assert.assertTrue(board.canPlaceWord(word1, middleSquare, Direction.Horizontal));
		Assert.assertEquals(22, board.scoreToPlace(word1, middleSquare, Direction.Horizontal));
		board.placeWord(word1, middleSquare, Direction.Horizontal); //placed apple
		
		
		Assert.assertFalse(board.canPlaceWord(word2, word3Square, Direction.Horizontal)); //not connected with any tiles already placed
		Assert.assertFalse(board.canPlaceWord(word2, word2Square, Direction.Horizontal)); //overlaps wrongly (ple / per)
		Assert.assertTrue(board.canPlaceWord(word2, word2Square, Direction.Vertical));
		Assert.assertEquals(11, board.scoreToPlace(word2, word2Square, Direction.Vertical));
		board.placeWord(word2, word2Square, Direction.Vertical); //placed person
		
		Assert.assertTrue(board.canPlaceWord(word3, word3Square, Direction.Horizontal));
		Assert.assertEquals(10, board.scoreToPlace(word3, word3Square, Direction.Horizontal)); // Check dl added before dw
		board.placeWord(word3, word3Square, Direction.Horizontal); //placed also
		
		Assert.assertTrue(board.canPlaceWord(word4, word4Square, Direction.Horizontal));
		Assert.assertEquals(11, board.scoreToPlace(word4, word4Square, Direction.Horizontal)); //check score from 'on' added. If 9, it hasn't. If 13, it's wrongly used DW.
		board.placeWord(word4, word4Square, Direction.Horizontal); //placed only
		
		Assert.assertTrue(board.canPlaceWord(word5, word5Square, Direction.Vertical));
		Assert.assertEquals(13, board.scoreToPlace(word5, word5Square, Direction.Vertical));
		board.placeWord(word5, word5Square, Direction.Vertical); //placed personal
		
		Assert.assertFalse(board.canPlaceWord(word6TooLong, word6Square, Direction.Vertical)); //goes off bottom of board.
		
		Assert.assertTrue(board.canPlaceWord(word7, word7Square, Direction.Horizontal));
		Assert.assertEquals(16, board.scoreToPlace(word7, word7Square, Direction.Horizontal)); //check DW scored in both directions
		
		
		Assert.assertEquals(21, countTilesOnBoard());
		
		board.clearBoard();
		
		Assert.assertEquals(0, countTilesOnBoard());	
	}

	
	@Test
	public void possibleWordSeedingTest() throws Exception
	{
		ArrayList<PossibleWord> possiblePlacements = board.getPossiblePlacements();
		Assert.assertEquals(2, possiblePlacements.size());
		Assert.assertEquals("**", (possiblePlacements.get(0)));
		
		int x = 7, y = 7;
		SquarePosition middleSquare = new SquarePosition(x, y);
		String word1 = "apple";
		SquarePosition word2Square = new SquarePosition(x+2, y);
		String word2 = "person";

		
		
		board.placeWord(word1, middleSquare, Direction.Horizontal);
		possiblePlacements = board.getPossiblePlacements();
		Assert.assertTrue(12 < possiblePlacements.size()); //hard to count at this stage.
		
		board.placeWord(word2, word2Square, Direction.Vertical);
		ArrayList<PossibleWord> possiblePlacements2 = board.getPossiblePlacements();
		Assert.assertTrue(possiblePlacements2.size() > possiblePlacements.size());		
	}
	
	private int countTilesOnBoard() {
		int boardSize = 15;
		int count = 0;
		for(int x = 0; x < boardSize; x++)
		{
			for(int y = 0; y < boardSize; y++)
			{
				if(board.getSquare(x, y).hasTile())
					count++;
			}
		}
		return count;
	}
	
	
	
}
