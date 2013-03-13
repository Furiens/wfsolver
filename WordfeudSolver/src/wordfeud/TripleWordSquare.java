package wordfeud;

public class TripleWordSquare extends Square{
	
	public TripleWordSquare(SquarePosition position) {
		super(position);
	}

	@Override
	public WordScore updateScore(WordScore score, Tile speculativeTile)
	{
		score.addToTotal(speculativeTile.getValue());
		score.addMultiplier(3);
		score.incrementNewLettersCount();
		return score;
	}	

}
