package wordfeud;

public class DoubleWordSquare extends Square {
	
	public DoubleWordSquare(SquarePosition position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public WordScore updateScore(WordScore score, Tile speculativeTile)
	{
		score.addToTotal(speculativeTile.getValue());
		score.addMultiplier(2);
		score.incrementNewLettersCount();
		return score;
	}	

}
