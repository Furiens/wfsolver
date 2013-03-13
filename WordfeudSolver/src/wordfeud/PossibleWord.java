package wordfeud;

public class PossibleWord {
	private StringBuilder s;
	private SquarePosition position;
	Direction direction;
	public PossibleWord(String str, SquarePosition position, Direction direction)
	{
		this.s = new StringBuilder("*"+str+"*");
		this.position = position;
		this.direction = direction;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public SquarePosition getPosition()
	{
		return position;
	}
	public StringBuilder getStringBuilder()
	{
		return s;
	}
	
	public void growFront(char ch)
	{
		s.insert(1, ch);
		if(direction==Direction.Horizontal)
		{
			position.x -= 1;
		}
		else
			position.y -= 1;
	}
	
	public void stopFront()
	{
		s.deleteCharAt(0);
	}
	
	public void growBack(char ch)
	{
		s.insert(s.length() - 1, ch);
	}
	
	public void stopBack()
	{
		s.deleteCharAt(s.length()-1);
	}
	
	
	
}
