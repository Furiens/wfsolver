package wordfeud;

public class Tile {
	
	private int value;
	private char letter;
	
	public Tile(int value, char letter)
	{
		this.value = value;
		this.letter = letter;
	}
	
	public int getValue() {return value;}
	public char getLetter() {return letter;} 

}
