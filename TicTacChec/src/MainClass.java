import javax.swing.*;
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
		//for(int i=0;i<8;i++)
		//System.out.println(s.notInGamePieces[i]+" "+s.inGamePieces[i]);
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
			
			s=MinMax.getBestMoveWhitePrunning(s, 3);
			printMatrix(s);
			State finalS = s;
			Runnable r=new Runnable() {
				@Override
				public void run() {
					Interfata inf=new Interfata(finalS);
					JFrame f = new JFrame("ChessChamp");
					f.add(inf.getGui());

					f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					f.setLocationByPlatform(true);

					f.pack();
					f.setMinimumSize(f.getSize());
					f.setVisible(true);
				}
			};
			SwingUtilities.invokeLater(r);

			if(State.isFinal(s))
				break;
			System.out.print("Piesa care va fi mutata:");
			humanMovePiece=keyboard.nextInt();
			System.out.print("Linia:");
			humanMoveX=keyboard.nextInt();
			System.out.print("Coloana:");
			humanMoveY=keyboard.nextInt();
			if(Transition.isValidTransition(s, humanMovePiece, PieceFinder.findPieceX(humanMovePiece, s), PieceFinder.findPieceY(humanMovePiece, s), humanMoveX, humanMoveY))
			s=Transition.transition(s, humanMovePiece, PieceFinder.findPieceX(humanMovePiece, s), PieceFinder.findPieceY(humanMovePiece, s), humanMoveX, humanMoveY);
			printMatrix(s);

		}
		keyboard.close();





			
	}
}
