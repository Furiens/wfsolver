package wordfeud;

public class SquarePosition {
	int x, y;
	public SquarePosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object s)
	{
		if(s instanceof SquarePosition)
			return ((SquarePosition)s).x == this.x && ((SquarePosition)s).y == this.y;
		else
			return false;
	}
	

}
