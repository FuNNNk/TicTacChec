import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

	Bishop(int color) {
		super(color);
		// TODO Auto-generated constructor stub
	}

	public static List<State> computeMoves(int piece,State s) {
		List<State> possibleTransitions=new ArrayList<State>();
		int X=PieceFinder.findPieceX(piece, s);
		int Y=PieceFinder.findPieceY(piece, s);
		int newX;
		int newY;
		newX=X+1;
		newY=Y+1;
		while(newX<4 && newY<4)
		{
			if(Transition.isValidTransition(s, piece, X, Y, newX, newY))
			{	possibleTransitions.add(Transition.transition(s, piece, X, Y, newX, newY));
			if(s.board[newX][newY]!=0)
				break;
			}
			else
				break;
			newX++;
			newY++;
		}
		
		newX=X-1;
		newY=Y-1;
		while(newX>=0 && newY>=0)
		{
			if(Transition.isValidTransition(s, piece, X, Y, newX, newY))
				{
			possibleTransitions.add(Transition.transition(s, piece, X, Y, newX, newY));
			if(s.board[newX][newY]!=0)
				break;
			}
			else
				break;
			newX--;
			newY--;
		}
		newX=X-1;
		newY=Y+1;
		while(newX>=0 && newY<4)
		{
			if(Transition.isValidTransition(s, piece, X, Y, newX, newY))
			{	possibleTransitions.add(Transition.transition(s, piece, X, Y, newX, newY));
			if(s.board[newX][newY]!=0)
				break;
			}
			else
				break;
			newX--;
			newY++;
		}
		
		newX=X+1;
		newY=Y-1;
		while(newX<4 && newY>=0)
		{
			if(Transition.isValidTransition(s, piece, X, Y, newX, newY))
				{
			possibleTransitions.add(Transition.transition(s, piece, X, Y, newX, newY));
			if(s.board[newX][newY]!=0)
				break;
			}
			else
				break;
			newX++;
			newY--;
		}
		
		return possibleTransitions;
				
		// TODO Auto-generated method stub
		
	}

	public int getNumeric() {
		// TODO Auto-generated method stub
		return 3+4*color;
	}
}
