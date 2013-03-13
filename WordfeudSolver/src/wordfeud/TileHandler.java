package wordfeud;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TileHandler {

	private Map<Character, Integer> tileMap = new HashMap<Character, Integer>();
	public TileHandler(File file) throws Exception
	{
		FileInputStream in = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		int value;
		String regex = ". [0-9]+"; //eg. a 4 or æ 10
		while((line = br.readLine()) != null)
		{
				
			if(line.matches(regex))
			{
				value = Integer.parseInt(line.substring(2));
				tileMap.put(line.charAt(0), value);
			}
			else
			{
				throw new Exception("Invalid line in "+file+". " +
						"Format must be \"[char] [value]\". Instead found \""+line+"\"");
			}				
		}
		br.close();
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
