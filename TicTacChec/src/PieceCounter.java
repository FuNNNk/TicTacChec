
public class PieceCounter {

	public static int countWhitePiecesInGame(State s)
	{
		int count=0;
		for(int i=0;i<4;i++)
			if(s.inGamePieces[i]==1)
				count++;
		return count;
			
	}
	
	public static int countWhitePiecesNotInGame(State s)
	{
		int count=0;
		for(int i=0;i<4;i++)
			if(s.notInGamePieces[i]==1)
				count++;
		return count;
			
	}
	
	public static int countBlackPiecesInGame(State s)
	{
		int count=0;
		for(int i=4;i<8;i++)
			if(s.inGamePieces[i]==1)
				count++;
		return count;
			
	}
	
	public static int countBlackPiecesNotInGame(State s)
	{
		int count=0;
		for(int i=4;i<8;i++)
			if(s.notInGamePieces[i]==1)
				count++;
		return count;
			
	}
}
