import java.util.List;

public class Transition {
	public static State transition(State oldState, int piece, int pieceInitialPositionX, int pieceInitialPositionY,
			int pieceFuturePositionX, int pieceFuturePositionY) {
		State newState = new State();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				newState.board[i][j] = oldState.board[i][j];
		for (int i = 0; i < 8; i++) {
			newState.inGamePieces[i] = oldState.inGamePieces[i];
			newState.notInGamePieces[i] = oldState.notInGamePieces[i];
		}
		if (pieceInitialPositionX != -1 && pieceInitialPositionY != -1)
			{
			newState.board[pieceInitialPositionX][pieceInitialPositionY] = 0;
			if(newState.board[pieceFuturePositionX][pieceFuturePositionY]!=0)
				{newState.inGamePieces[newState.board[pieceFuturePositionX][pieceFuturePositionY]-1]=0;
				newState.notInGamePieces[newState.board[pieceFuturePositionX][pieceFuturePositionY]-1]=1;
			}
			}
		else {
			newState.notInGamePieces[piece - 1] = 0;
			newState.inGamePieces[piece - 1] = 1;
		}
		newState.board[pieceFuturePositionX][pieceFuturePositionY] = piece;
		return newState;

	}

	public static boolean isValidTransition(State oldState, int piece, int pieceInitialPositionX,
			int pieceInitialPositionY, int pieceFuturePositionX, int pieceFuturePositionY) {
		if(pieceFuturePositionX<0 || pieceFuturePositionX>3)
			return false;
		if(pieceFuturePositionY<0 || pieceFuturePositionY>3)
			return false;
		if (pieceInitialPositionX == -1 && pieceInitialPositionY == -1
				&& oldState.board[pieceFuturePositionX][pieceFuturePositionY] != 0)
			return false;
		if (pieceInitialPositionX == -1 && pieceInitialPositionY == -1 && oldState.notInGamePieces[piece - 1] != 1)
			return false;
		if (Piece.isSameColor(piece, pieceFuturePositionX, pieceFuturePositionY, oldState))
			return false;
		return true;

	}
	
	public static List<State> computeMoves(int piece,State s)
	{
		if(piece==1 || piece==5)
			return Pawn.computeMoves(piece, s);
		else if(piece==2 || piece==6)
			return Knight.computeMoves(piece, s);
		else if(piece==3 || piece==7)
			return Bishop.computeMoves(piece, s);
		else
			return Tower.computeMoves(piece, s);
	}
}
