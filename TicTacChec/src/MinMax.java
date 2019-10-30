import java.util.ArrayList;
import java.util.List;

public class MinMax {

	public static State MiniMaxWhite(State s)
	{
		int maxEval=Integer.MIN_VALUE;
		State minEvalState=s;
		List<State> possibleTransitions=new ArrayList<State>();
		for(int piece=0;piece<4;piece++)
			if(s.notInGamePieces[piece]==1)
				for(int i=0;i<4;i++)
					for(int j=0;j<4;j++)
						if(Transition.isValidTransition(s, piece+1, -1, -1, i, j))
						possibleTransitions.add(Transition.transition(s, piece+1, -1, -1, i, j));
		for(int piece=0;piece<4;piece++)
			if(s.inGamePieces[piece]==1)
				possibleTransitions.addAll(Transition.computeMoves(piece+1, s));
		for(State possibleTransition:possibleTransitions)
		{
			if(State.evaluareStare(possibleTransition)>maxEval)
			{
				maxEval=State.evaluareStare(possibleTransition);
				minEvalState=possibleTransition;
			}
		}
							
		return minEvalState;
	}
}
