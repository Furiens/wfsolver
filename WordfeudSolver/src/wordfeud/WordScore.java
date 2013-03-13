package wordfeud;

public class WordScore {
	private int wordMultiplier = 1;
	private int total = 0;
	private int newLettersCount = 0;
	
	private final static int tileRackSize = 7;
	private final static int usingCompleteTileRackBonus = 40;
	
	
	public void incrementNewLettersCount() {newLettersCount++;}
	public void addToTotal(int n) {total += n;}
	public void addMultiplier(int n) {wordMultiplier *= n;}
	public int grandTotal()
	{
		if(newLettersCount < tileRackSize)
			return total * wordMultiplier;
		else
			return total * wordMultiplier + usingCompleteTileRackBonus;
	}
	
	

}
