package wordfeud;

public class DoubleLetterSquare extends Square{

	public DoubleLetterSquare(SquarePosition position) {
		super(position);
	}

	@Override
	public WordScore updateScore(WordScore score, Tile speculativeTile)
	{
		score.addToTotal(speculativeTile.getValue() * 2);
		score.incrementNewLettersCount();
		return score;
	}	
	
	

}
