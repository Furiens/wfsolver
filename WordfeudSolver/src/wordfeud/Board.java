package wordfeud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import wordfeud.Square;

public class Board {

	private TileHandler tileHandler;
	
	private Square[][] squares;

	private boolean isEmpty = true;

	public Board(File file, File file2) throws Exception {
		this.tileHandler = new TileHandler(file2);
		
		tileHandler = new TileHandler(file2);
		
		FileInputStream in = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		ArrayList<String> boardStrings = new ArrayList<String>();
		String line;
		while((line = br.readLine()) != null)
		{
			boardStrings.add(line);
		}
		br.close();
		
		int width = boardStrings.get(0).length();
		int height = boardStrings.size();
		squares = new Square[width][height];
		
		for(int y = 0; y < height; y++)
		{
			String row = boardStrings.get(y);
			if(row.length() != width)
				throw new Exception("Board must be rectangular");
			for(int x = 0; x < width; x++)
			{
				squares[x][y] = SquaresFactory.createSquare(x, y, row.charAt(x));
			}
		}
	}

	public Square getSquare(int i, int j) {
		return squares[i][j];
	}

	public boolean canPlaceWord(String word, SquarePosition startSquare,
			Direction direction) {
		try
		{
			return canPlaceWord(tileHandler.getTiles(word), startSquare, direction);
		}
		catch (Exception e) 
		{
			return false;
		}
	}
	
	private boolean canPlaceWord(Tile[] word, SquarePosition startSquare, Direction direction)
	{
		int x = startSquare.x;
		int y = startSquare.y;
		int xSize = squares.length;
		int ySize = squares[0].length;
		int middleX = xSize / 2;
		int middleY = ySize / 2;
		
		if(x < 0 || y < 0 || x >= xSize || y >= ySize)
			return false;
		boolean connected = false; //connected to existing word on board.
		
		if(direction == Direction.Horizontal)
		{
			if(isEmpty)
			{
				if (y != middleY || x > middleX || x + word.length <= middleX)
					return false; //must go through middle square
				else
					connected = true;
			}
			if(word.length + x > squares.length)
				return false;
			for(Tile tile : word)
			{
				if(squares[x][y].hasTile())
				{
					if(squares[x][y].getTile().getLetter() != tile.getLetter())
						return false;
					else
						connected = true;
				}
				else
				{
					if((y > 0 && squares[x][y-1].hasTile()) || (y < squares[x].length - 1 && squares[x][y+1].hasTile()))
						connected = true;
				}
				x++;
			}
		}
		else
		{
			if(isEmpty)
			{
				if (x != middleX || y > middleY || y + word.length <= middleY)
					return false; //must go through middle square
				else
					connected = true;
			}
			if(word.length + y > squares.length)
				return false;
			for(Tile tile : word)
			{
				if(squares[x][y].hasTile())
				{
					if (squares[x][y].getTile().getLetter() != tile.getLetter())
						return false;
					else
						connected = true;
				}
				else
				{
					if((x > 0 && squares[x-1][y].hasTile()) || (x < squares.length - 1 && squares[x+1][y].hasTile()))
						connected = true;
				}
			
				y++;
			}
		}
		return connected;
	}

	public void clearBoard() {
		for(Square[] columns : squares)
		{
			for(Square square : columns)
			{
				square.deleteTile();
			}
		}
		isEmpty = true;
	}

	public void placeWord(String word, SquarePosition startSquare,
			Direction direction) throws Exception
	{
			placeWord(tileHandler.getTiles(word), startSquare, direction);
	}

	private void placeWord(Tile[] tiles, SquarePosition startSquare,
			Direction direction) throws Exception
	{
		int x = startSquare.x, y = startSquare.y;
		for(Tile tile: tiles)
		{
			if(!squares[x][y].hasTile())
				squares[x][y].placeTile(tile);
			else
			{
				if(squares[x][y].getTile().getLetter() != tile.getLetter())
					throw new Exception("Invalid placement. Attempting to overwrite "+
				squares[x][y].getTile().getLetter()+" with "+tile.getLetter()+".");
			}
			if(direction == Direction.Horizontal)
				x++;
			else
				y++;
		}
		isEmpty  = false;
	}

	public int scoreToPlace(String word, SquarePosition startSquare,
			Direction direction) throws Exception {
		int totalScore = 0;
		Tile[] tiles = tileHandler.getTiles(word);
		Square[] mainWord = getSquares(startSquare, direction, word.length());
		WordScore wordScore = new WordScore();
		Direction perpendicularDirection = direction == Direction.Horizontal ? Direction.Vertical : Direction.Horizontal;
				
		for(int i = 0; i < mainWord.length; i++)
		{
			if(mainWord[i].hasTile())
			{
				wordScore = mainWord[i].updateScore(wordScore);
			}
			else
			{
				wordScore = mainWord[i].updateScore(wordScore, tiles[i]);
				
				//Also check possibility that letter forms a word going in perpendicular direction.
				SquarePosition perpendicularWordStart = getWordStartPosition(mainWord[i], perpendicularDirection);
				if(!perpendicularWordStart.equals(mainWord[i].getPosition()))
				{
					WordScore perpendicularScore = new WordScore();
					int length = getWordLength(mainWord[i], perpendicularDirection);				
					Square[] perpendicularWord = getSquares(perpendicularWordStart, perpendicularDirection, length);
					for(int j = 0; j < perpendicularWord.length; j++)
					{
						if(perpendicularWord[j].hasTile())
							perpendicularScore = perpendicularWord[j].updateScore(perpendicularScore);
						else
							perpendicularScore = perpendicularWord[j].updateScore(perpendicularScore, tiles[i]); //there will only be 1 gap in perpendicular word, tiles[i],
					}
					totalScore += perpendicularScore.grandTotal();
				}	
			}
		}
		totalScore += wordScore.grandTotal();
		return totalScore;
	}
	
	/**
	 * Returns length of word given a square that it passes through.
	 * NB. Does not check if tile exists on square given as argument.
	 * This allows method to be used for prospectively placed tile.
	 * @param square
	 * @param perpendicularDirection
	 * @return
	 */
	private int getWordLength(Square square, Direction direction) {
		
		int wordlength = 1;
		int x = square.getPosition().x;
		int y = square.getPosition().y;
		if(direction == Direction.Horizontal)
		{
			while(x > 0)
			{
				if(squares[x-1][y].hasTile())
				{
					x--;
					wordlength++;
				}
				else
					break;
			}
			x = x + wordlength;
			while(x < squares.length - 1)
			{
				if(squares[x+1][y].hasTile())
				{
					x++;
					wordlength++;
				}
				else
					break;
			}
		}
		
		else
		{
			while(y > 0)
			{
				if(squares[x][y-1].hasTile())
					y--;
				else
					break;
			}
			y = y + wordlength;
			while(y < squares[x].length - 1)
			{
				if(squares[x][y+1].hasTile())
				{
					x++;
					wordlength++;
				}
				else
					break;
			}
		}
		return wordlength;
	}

	/**
	 * Reads backwards from square given to find furthermost square with a tile on it.
	 * @param square
	 * @param direction
	 * @return
	 */
	private SquarePosition getWordStartPosition(Square square,
			Direction direction)
	{
		int x = square.getPosition().x;
		int y = square.getPosition().y;
		if(direction == Direction.Horizontal)
		{
			while(x > 0)
			{
				if(squares[x-1][y].hasTile())
					x--;
				else
					break;
			}
		}
		else
		{
			while(y > 0)
			{
				if(squares[x][y-1].hasTile())
					y--;
				else
					break;
			}
		}
		return new SquarePosition(x, y);
	}

	private Square[] getSquares(SquarePosition startSquare, Direction direction,
			int length) {
		Square[] wordSquares = new Square[length];
		if(direction == Direction.Horizontal)
		{
			for(int i = 0; i < length; i++)
			{
				wordSquares[i] = squares[startSquare.x + i][startSquare.y];
			}
		}
		else
		{
			for(int i = 0; i < length; i++)
			{
				wordSquares[i] = squares[startSquare.x][startSquare.y + i];
			}
		}
		return wordSquares;
	}

	public ArrayList<PossibleWord> getPossiblePlacements() {
		// TODO Auto-generated method stub
		return null;
	}
}
