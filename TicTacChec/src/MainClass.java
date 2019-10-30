import java.util.List;
import java.util.Scanner;

public class MainClass {

	public static void printMatrix(State s)
	{
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
				System.out.print(s.board[i][j]);
			System.out.println();

		}
		for(int i=0;i<8;i++)
		System.out.println(s.notInGamePieces[i]+" "+s.inGamePieces[i]);
		System.out.println();
		
	}
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);
		int humanMovePiece;
		int humanMoveX;
		int humanMoveY;
		State s=State.getInitialState();

		while(!State.isFinal(s))
		{
			System.out.print("Piesa care va fi mutata:");
			humanMovePiece=keyboard.nextInt();
			System.out.print("Linia:");
			humanMoveX=keyboard.nextInt();
			System.out.print("Coloana:");
			humanMoveY=keyboard.nextInt();
			if(Transition.isValidTransition(s, humanMovePiece, PieceFinder.findPieceX(humanMovePiece, s), PieceFinder.findPieceY(humanMovePiece, s), humanMoveX, humanMoveY))
			s=Transition.transition(s, humanMovePiece, PieceFinder.findPieceX(humanMovePiece, s), PieceFinder.findPieceY(humanMovePiece, s), humanMoveX, humanMoveY);
			printMatrix(s);
			if(State.isFinal(s))
				break;
				s=MinMax.MiniMaxWhite(s);
			printMatrix(s);

		}


//		Runnable r=new Runnable() {
//			@Override
//			public void run() {
//				Interfata inf=new Interfata();
//				JFrame f = new JFrame("ChessChamp");
//				f.add(inf.getGui());
//				// Ensures JVM closes after frame(s) closed and
//				// all non-daemon threads are finished
//				f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				// See https://stackoverflow.com/a/7143398/418556 for demo.
//				f.setLocationByPlatform(true);
//
//				// ensures the frame is the minimum size it needs to be
//				// in order display the components within it
//				f.pack();
//				// ensures the minimum size is enforced.
//				f.setMinimumSize(f.getSize());
//				f.setVisible(true);
//			}
//		};
//		SwingUtilities.invokeLater(r);


			
	}
}
