
public class PieceFinder {

	public static int findPieceX(int p,State s)
	{
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if(s.board[i][j]==p)
					return i;
		return -1;
	}
	
	public static int findPieceY(int p,State s)
	{
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
				if(s.board[i][j]==p)
					return j;
		return -1;
	}
}
