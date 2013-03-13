package wordfeud;

public class Square {
	private Tile tile;
	private SquarePosition position;
	
	public Square(SquarePosition position)
	{
		this.position = position;
	}
	
	public SquarePosition getPosition()
	{
		return position;
	}
	
	public void placeTile(Tile t) throws Exception
	{
		if(hasTile())
			throw new Exception("Tile already exists");
		tile = t;
	}
	
	public boolean hasTile()
	{
		if(tile == null)
			return false;
		else
			return true;
	}
	
	public Tile getTile()
	{
		return tile;
	}
	
	public WordScore updateScore(WordScore score)
	{
		score.addToTotal(tile.getValue());
		return score;
	}
	
	
	public WordScore updateScore(WordScore score, Tile speculativeTile)
	{
		score.addToTotal(speculativeTile.getValue());
		score.incrementNewLettersCount();
		return score;
	}

	public void deleteTile() {
		tile = null;
		
	}

}
