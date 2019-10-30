import java.util.List;

public abstract class Piece {

	public int positionRow;
	public int positionColumn;
	public int color;
	Piece(int color)
	{
		this.positionRow=-1;
		this.positionColumn=-1;
		this.color=color;
	}
	
	public abstract int getNumeric();
	public static boolean isSameColor(int piece,int X,int Y,State s)
	{
		if((piece>0 && piece<=4 && s.board[X][Y]>0 && s.board[X][Y]<=4) || (piece>4 && piece<=8 && s.board[X][Y]>4 && s.board[X][Y]<=8) )
			return true;
		return false;
		
		
	}
}
