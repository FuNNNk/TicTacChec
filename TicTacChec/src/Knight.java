import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

	Knight(int color) {
		super(color);
	}

	public static List<State> computeMoves(int piece,State s) {
		List<State> possibleTransitions=new ArrayList<State>();
		int X=PieceFinder.findPieceX(piece, s);
		int Y=PieceFinder.findPieceY(piece, s);
		int[] vdX= {-2,-2,2,2,-1,-1,1,1};
		int[] vdY= {-1,1,-1,2,-2,2,-2,2};
		for(int i=0;i<7;i++)
			if(Transition.isValidTransition(s, piece, X, Y, X+vdX[i], Y+vdY[i]))
				possibleTransitions.add(Transition.transition(s, piece, X, Y, X+vdX[i], Y+vdY[i]));
		return possibleTransitions;
		
	}
	
	public int getNumeric() {
		// TODO Auto-generated method stub
		return 2+4*color;
	}
	

}
