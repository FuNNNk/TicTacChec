import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

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
		if(PieceCounter.countWhitePiecesInGame(s)>=3)
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
	public static State getBestMoveWhite(State s,int depth)
	{
		int maxEval=Integer.MIN_VALUE;
		int currentEval;
		State bestMove=s;
		for(State possibleTransition:MinMax.PossibleMovesWhite(s))
		{
			currentEval=MinMax.MiniMax(s, depth, true);
			if(currentEval>maxEval)
			{
				maxEval=currentEval;
				bestMove=possibleTransition;
			}
		}
		return bestMove;
	}
	public static State getBestMoveWhitePrunning(State s,int depth)
	{
		int maxEval=Integer.MIN_VALUE;
		int currentEval;
		State bestMove=s;
		for(State possibleTransition:MinMax.PossibleMovesWhite(s))
		{
			currentEval=MinMax.MiniMaxPrunning(s, depth,Integer.MIN_VALUE,Integer.MIN_VALUE, true);
			if(currentEval>maxEval)
			{
				maxEval=currentEval;
				bestMove=possibleTransition;
			}
		}
		return bestMove;
	}
	public static int MiniMax(State s,int depth,boolean isMaximazingPlayer)
	{
		if(depth==0 || State.isFinal(s))
			return State.evaluareStare2(s);
		if(isMaximazingPlayer)
		{
			int maxEval=Integer.MIN_VALUE;
			int currentEval=0;
			for(State possibleTransition:MinMax.PossibleMovesWhite(s))
			{
				currentEval=MiniMax(possibleTransition,depth-1,false);
				if(currentEval>maxEval)
					maxEval=currentEval;
			}
			return maxEval;
			
		}
		else
		{
			int minEval=Integer.MAX_VALUE;
			int currentEval=0;
			for(State possibleTransition:MinMax.PossibleMovesBlack(s))
			{
				minEval=MiniMax(possibleTransition,depth-1,true);
				if(currentEval<minEval)
					minEval=currentEval;
			}
			return minEval;
		}
		
	}
	
	public static int MiniMaxPrunning(State s,int depth,int alpha,int beta,boolean isMaximazingPlayer)
	{
		if(depth==0 || State.isFinal(s))
			return State.evaluareStare2(s);
		if(isMaximazingPlayer)
		{
			int maxEval=Integer.MIN_VALUE;
			int currentEval=0;
			for(State possibleTransition:MinMax.PossibleMovesWhite(s))
			{
				currentEval=MiniMax(possibleTransition,depth-1,false);
				if(currentEval>maxEval)
					maxEval=currentEval;
				alpha=Math.max(alpha, maxEval);
				if(beta<=alpha)
					break;
			}
			return maxEval;
			
		}
		else
		{
			int minEval=Integer.MAX_VALUE;
			int currentEval=0;
			for(State possibleTransition:MinMax.PossibleMovesBlack(s))
			{
				minEval=MiniMax(possibleTransition,depth-1,true);
				if(currentEval<minEval)
					minEval=currentEval;
				beta=Math.min(beta, minEval);
				if(beta<=alpha)
					break;
			}
			return minEval;
		}
		
	}
	public static List<State> PossibleMovesWhite(State s)
	{
		List<State> possibleTransitions=new ArrayList<State>();
		for(int piece=0;piece<4;piece++)
			if(s.notInGamePieces[piece]==1)
				for(int i=0;i<4;i++)
					for(int j=0;j<4;j++)
						if(Transition.isValidTransition(s, piece+1, -1, -1, i, j))
						possibleTransitions.add(Transition.transition(s, piece+1, -1, -1, i, j));
		if(PieceCounter.countWhitePiecesInGame(s)>=3)
		for(int piece=0;piece<4;piece++)
			if(s.inGamePieces[piece]==1)
				possibleTransitions.addAll(Transition.computeMoves(piece+1, s));
		
							
		return possibleTransitions;
	}
	
	public static List<State> PossibleMovesBlack(State s)
	{
		List<State> possibleTransitions=new ArrayList<State>();
		for(int piece=4;piece<8;piece++)
			if(s.notInGamePieces[piece]==1)
				for(int i=0;i<4;i++)
					for(int j=0;j<4;j++)
						if(Transition.isValidTransition(s, piece+1, -1, -1, i, j))
						possibleTransitions.add(Transition.transition(s, piece+1, -1, -1, i, j));
		if(PieceCounter.countBlackPiecesInGame(s)>=3)
		for(int piece=4;piece<8;piece++)
			if(s.inGamePieces[piece]==1)
				possibleTransitions.addAll(Transition.computeMoves(piece+1, s));
		
							
		return possibleTransitions;
	}
}
