
public class State {

	int[][] board;
	int[] inGamePieces;
	int[] notInGamePieces;

	public State() {
		board = new int[4][4];
		inGamePieces = new int[8];
		notInGamePieces = new int[8];
	}

	public static State getInitialState() {
		State s = new State();
		for (int i = 0; i < 8; i++)
			s.notInGamePieces[i] = 1;
		return s;
	}

	public static boolean isFinal(State s) {
		int whiteHorizontal;
		int blackHorizontal;
		int whiteVertical;
		int blackVertical;

		for (int i = 0; i < 4; i++) {
			whiteHorizontal = 0;
			blackHorizontal = 0;
			whiteVertical = 0;
			blackVertical = 0;
			for (int j = 0; j < 4; j++) {
				if (s.board[i][j] > 0 && s.board[i][j] <= 4)
					whiteHorizontal++;
				else if (s.board[i][j] > 4 && s.board[i][j] <= 8)
					blackHorizontal++;

				if (s.board[j][i] > 0 && s.board[j][i] <= 4)
					whiteVertical++;
				else if (s.board[j][i] > 4 && s.board[j][i] <= 8)
					blackVertical++;
			}
			if (whiteHorizontal == 4 || blackHorizontal == 4 || whiteVertical == 4 || blackVertical == 4)
				return true;
		}
		whiteHorizontal = 0;
		blackHorizontal = 0;
		whiteVertical = 0;
		blackVertical = 0;
		for (int i = 0; i < 4; i++) {
			if (s.board[i][i] > 0 && s.board[i][i] <= 4)
				whiteHorizontal++;
			else if (s.board[i][i] > 4 && s.board[i][i] <= 8)
				blackHorizontal++;

			if (s.board[i][3 - i] > 0 && s.board[i][3 - i] <= 4)
				whiteVertical++;
			else if (s.board[i][3 - i] > 4 && s.board[i][3 - i] <= 8)
				blackVertical++;
		}
		if (whiteHorizontal == 4 || blackHorizontal == 4 || whiteVertical == 4 || blackVertical == 4)
			return true;

		return false;

	}

	public static int evaluareStare(State s) {
		int whiteHorizontal;
		int blackHorizontal;
		int whiteVertical;
		int blackVertical;
		int evaluare = 0;
		for (int i = 0; i < 4; i++) {
			whiteHorizontal = 0;
			blackHorizontal = 0;
			whiteVertical = 0;
			blackVertical = 0;
			for (int j = 0; j < 4; j++) {
				if (s.board[i][j] > 0 && s.board[i][j] <= 4)
					whiteHorizontal++;
				else if (s.board[i][j] > 4 && s.board[i][j] <= 8)
					blackHorizontal++;

				if (s.board[j][i] > 0 && s.board[j][i] <= 4)
					whiteVertical++;
				else if (s.board[j][i] > 4 && s.board[j][i] <= 8)
					blackVertical++;
			}
			if (whiteHorizontal != 0)
				evaluare = (int) (evaluare + Math.pow(10, whiteHorizontal));
			if (whiteVertical != 0)
				evaluare = (int) (evaluare + Math.pow(10, whiteVertical));
			if (blackHorizontal != 0)
				evaluare = (int) (evaluare - Math.pow(10, blackHorizontal));
			if (blackVertical != 0)
				evaluare = (int) (evaluare - Math.pow(10, blackVertical));

		}
		whiteHorizontal = 0;
		blackHorizontal = 0;
		whiteVertical = 0;
		blackVertical = 0;
		for (int i = 0; i < 4; i++) {
			if (s.board[i][i] > 0 && s.board[i][i] <= 4)
				whiteHorizontal++;
			else if (s.board[i][i] > 4 && s.board[i][i] <= 8)
				blackHorizontal++;

			if (s.board[i][3 - i] > 0 && s.board[i][3 - i] <= 4)
				whiteVertical++;
			else if (s.board[i][3 - i] > 4 && s.board[i][3 - i] <= 8)
				blackVertical++;
		}
		if (whiteHorizontal != 0)
			evaluare = (int) (evaluare + Math.pow(10, whiteHorizontal));
		if (whiteVertical != 0)
			evaluare = (int) (evaluare + Math.pow(10, whiteVertical));
		if (blackHorizontal != 0)
			evaluare = (int) (evaluare - Math.pow(10, blackHorizontal));
		if (blackVertical != 0)
			evaluare = (int) (evaluare - Math.pow(10, blackVertical));
		for(int i=0;i<4;i++)
		{
			if(s.notInGamePieces[i]==1)
				evaluare-=100*(i+1);
			if(s.notInGamePieces[i+4]==1)
				evaluare+=100*(i+1);
		}
		return evaluare;

	}
	
	public static int evaluareStare2(State s) {
		int whiteHorizontal;
		int blackHorizontal;
		int whiteVertical;
		int blackVertical;
		int whiteCurrentHorizontal;
		int blackCurrentHorizontal;
		int whiteCurrentVertical;
		int blackCurrentVertical;
		int evaluare = 0;
		for (int i = 0; i < 4; i++) {
			whiteHorizontal = 0;
			blackHorizontal = 0;
			whiteVertical = 0;
			blackVertical = 0;
			whiteCurrentHorizontal=0;
			blackCurrentHorizontal=0;
			whiteCurrentVertical=0;
			blackCurrentVertical=0;
			for (int j = 0; j < 4; j++) {
				if (s.board[i][j] > 0 && s.board[i][j] <= 4)
					{
					whiteCurrentHorizontal++;
					blackCurrentHorizontal=0;
					if(whiteCurrentHorizontal>whiteHorizontal)
						whiteHorizontal=whiteCurrentHorizontal;
					}
				else if (s.board[i][j] > 4 && s.board[i][j] <= 8)
				{
					blackCurrentHorizontal++;
					whiteCurrentHorizontal=0;
					if(blackCurrentHorizontal>blackHorizontal)
						blackHorizontal=blackCurrentHorizontal;
					}

				if (s.board[j][i] > 0 && s.board[j][i] <= 4)
				{
					whiteCurrentVertical++;
					blackCurrentVertical=0;
					if(whiteCurrentVertical>whiteVertical)
						whiteVertical=whiteCurrentVertical;
					}
				else if (s.board[j][i] > 4 && s.board[j][i] <= 8)
				{
					blackCurrentVertical++;
					whiteCurrentVertical=0;
					if(blackCurrentVertical>blackVertical)
						blackVertical=blackCurrentVertical;
					}
			}
			if (whiteHorizontal != 0)
				evaluare = (int) (evaluare + Math.pow(10, whiteHorizontal));
			if (whiteVertical != 0)
				evaluare = (int) (evaluare + Math.pow(10, whiteVertical));
			if (blackHorizontal != 0)
				evaluare = (int) (evaluare - Math.pow(10, blackHorizontal));
			if (blackVertical != 0)
				evaluare = (int) (evaluare - Math.pow(10, blackVertical));

		}
		whiteHorizontal = 0;
		blackHorizontal = 0;
		whiteVertical = 0;
		blackVertical = 0;
		whiteCurrentHorizontal=0;
		blackCurrentHorizontal=0;
		whiteCurrentVertical=0;
		blackCurrentVertical=0;
		for (int i = 0; i < 4; i++) {
			if (s.board[i][i] > 0 && s.board[i][i] <= 4)
			{
				whiteCurrentHorizontal++;
				blackCurrentHorizontal=0;
				if(whiteCurrentHorizontal>whiteHorizontal)
					whiteHorizontal=whiteCurrentHorizontal;
				}
			else if (s.board[i][i] > 4 && s.board[i][i] <= 8)
			{
				blackCurrentHorizontal++;
				whiteCurrentHorizontal=0;
				if(blackCurrentHorizontal>blackHorizontal)
					blackHorizontal=blackCurrentHorizontal;
				}

			if (s.board[i][3 - i] > 0 && s.board[i][3 - i] <= 4)
			{
				whiteCurrentVertical++;
				blackCurrentVertical=0;
				if(whiteCurrentVertical>whiteVertical)
					whiteVertical=whiteCurrentVertical;
				}
			else if (s.board[i][3 - i] > 4 && s.board[i][3 - i] <= 8)
			{
				blackCurrentVertical++;
				whiteCurrentVertical=0;
				if(blackCurrentVertical>blackVertical)
					blackVertical=blackCurrentVertical;
				}
		}
		if (whiteHorizontal != 0 && blackHorizontal==0 )
			evaluare = (int) (evaluare + Math.pow(10, whiteHorizontal));
		if (whiteVertical != 0 && blackVertical==0 )
			evaluare = (int) (evaluare + Math.pow(10, whiteVertical));
		if (blackHorizontal != 0 && whiteHorizontal==0)
			evaluare = (int) (evaluare - Math.pow(10, blackHorizontal));
		if (blackVertical != 0 && whiteVertical==0)
			evaluare = (int) (evaluare - Math.pow(10, blackVertical));
		for(int i=0;i<4;i++)
		{
			if(s.notInGamePieces[i]==1)
				evaluare-=20*(i+1);
			if(s.notInGamePieces[i+4]==1)
				evaluare+=20*(i+1);
		}
		return evaluare;

	}

}
