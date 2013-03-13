package wordfeud;

public class TripleLetterSquare extends Square{
	
	public TripleLetterSquare(SquarePosition position) {
		super(position);
	}

	@Override
	public WordScore updateScore(WordScore score, Tile speculativeTile)
	{
		score.addToTotal(speculativeTile.getValue() * 3);
		score.incrementNewLettersCount();
		return score;
	}	

}
