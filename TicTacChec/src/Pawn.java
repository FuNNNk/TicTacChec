import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	Pawn(int color) {
		super(color);
		// TODO Auto-generated constructor stub
	}
	public static List<State> computeMoves(int piece,State s) {
		List<State> possibleTransitions=new ArrayList<State>();
		int X=PieceFinder.findPieceX(piece, s);
		int Y=PieceFinder.findPieceY(piece, s);
		int[] vdX= {-1,-1,1,1};
		int[] vdY= {-1,1,-1,1};
		for(int i=0;i<4;i++)
			if(Transition.isValidTransition(s, piece, X, Y, X+vdX[i], Y+vdY[i]))
				{
				if(s.board[X+vdX[i]][Y+vdY[i]]!=0)
				possibleTransitions.add(Transition.transition(s, piece, X, Y, X+vdX[i], Y+vdY[i]));
				
				}
		if(X+1<4)
			if(s.board[X+1][Y]==0)
			if(Transition.isValidTransition(s, piece, X, Y, X+1, Y))
				possibleTransitions.add(Transition.transition(s, piece, X, Y, X+1, Y));
		if(X-1>0)
			if(s.board[X-1][Y]==0)
			if(Transition.isValidTransition(s, piece, X, Y, X-1, Y))
				possibleTransitions.add(Transition.transition(s, piece, X, Y, X-1, Y));
		return possibleTransitions;
				
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getNumeric() {
		// TODO Auto-generated method stub
		return 1+4*color;
	}


}
