package wordfeud;

import java.util.Map;

public class TileHandler {

	private Map<Character, Integer> tileMap;
	public TileHandler(String filename) throws Exception
	{
		boolean correctTileValuesFormatting = true;
		
			//TODO Auto-generated method stub
			correctTileValuesFormatting = false;
			if(!correctTileValuesFormatting)
				throw new Exception("Incorrect tile values format(letter value\\n)");
	}

	/**
	 * 
	 * @param string Word expressed as a string
	 * @return Word expressed as an array of tiles with the characters of the word
	 * @throws Exception
	 */
	public Tile[] getTiles(String string) throws Exception {
		Tile[] tiles = new Tile[string.length()];
		for(int i = 0; i < tiles.length; i++)
		{
			tiles[i] = getTile(string.charAt(i));
		}
		return tiles;
	}
	
	public String getString(Tile[] tiles)
	{
		StringBuilder s = new StringBuilder();
		for(Tile tile: tiles)
		{
			s.append(tile.getLetter());
		}
		return s.toString();
	}
	
	private Tile getTile(char ch) throws Exception
	{
		Integer value = tileMap.get(ch);
		if(value == null)
			throw new Exception("Character "+ch+" not found in tileValues file.");
		return new Tile(value.intValue(), ch);
	}

}
