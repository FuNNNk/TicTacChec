import java.util.ArrayList;
import java.util.List;

public class Tower extends Piece {

	Tower(int color) {
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
		while(newX<4)
		{
			if(Transition.isValidTransition(s, piece, X, Y, newX, Y))
			{	possibleTransitions.add(Transition.transition(s, piece, X, Y, newX, Y));
			if(s.board[newX][Y]!=0)
				break;
			}
			else
				break;
			newX++;
		}
		
		newX=X-1;
		while(newX>=0)
		{
			if(Transition.isValidTransition(s, piece, X, Y, newX, Y))
				{
			possibleTransitions.add(Transition.transition(s, piece, X, Y, newX, Y));
			if(s.board[newX][Y]!=0)
				break;
			}
			else
				break;
			newX--;
		}
		
		newY=Y+1;
		while(newY<4)
		{
			if(Transition.isValidTransition(s, piece, X, Y, X, newY))
			{	possibleTransitions.add(Transition.transition(s, piece, X, Y, X, newY));
			if(s.board[X][newY]!=0)
				break;
			}
			else
				break;
			newY++;
		}
		
		newY=Y-1;
		while(newY>=0)
		{
			if(Transition.isValidTransition(s, piece, X, Y, X, newY))
			{	possibleTransitions.add(Transition.transition(s, piece, X, Y, X, newY));
			if(s.board[X][newY]!=0)
				break;
			}
			else
				break;
			newY--;
		}
		return possibleTransitions;
				
		// TODO Auto-generated method stub
		
	}
	public int getNumeric() {
		// TODO Auto-generated method stub
		return 4+4*color;
	}
}
