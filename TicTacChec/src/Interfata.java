import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;

public class Interfata {


    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[4][4];
    private Image[] chessPieceImages = new Image[9];
    private JPanel chessBoard;
    private final JLabel message = new JLabel(
            "Chess Champ is ready to play!");
    private static final String COLS = "ABCD";
    public static final int  PAWN = 3, KNIGHT = 2, BISHOP = 1,ROOK = 0;
    public static final int BLACK = 0, WHITE = 1;
    public static final int[] STARTING_ROW = {
            ROOK, KNIGHT, BISHOP, PAWN
    };

    Interfata(State s){
        State new_state=s;
        initializeGUI(new_state);
    }

    //initializare tabla de sah
    public void initializeChessBoard(){
        chessBoard = new JPanel(new GridLayout(0, 5)) {

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(
                new EmptyBorder(8,8,8,8),
                new LineBorder(Color.BLACK)
        ));
        Color ochre = new Color(204,119,34);
        chessBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);
    }

    public final void initializeGUI(State new_state) {
        createImages();


        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("New") {

            @Override
            public void actionPerformed(ActionEvent e) {
                setupNewGame(new_state);
            }
        };
        tools.add(newGameAction);
        tools.addSeparator();
        tools.addSeparator();
        tools.add(message);

        gui.add(new JLabel("?"), BorderLayout.LINE_START);

        initializeChessBoard();


        //create the chess board squares
        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }
        chessBoard.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 4; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 4; ii++) {
            for (int jj = 0; jj < 4; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (5 - (ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }

//    public void clearBoard(){
//        chessBoard.validate();
//        chessBoard.repaint();
//        chessBoard.validate();
//    }

//    private void setupNewGame() {
//        message.setText("Make your move!");
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][0].setIcon(new ImageIcon(
//                    chessPieceImages[BLACK][STARTING_ROW[ii]]));
//        }
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][1].setIcon(new ImageIcon(
//                    chessPieceImages[BLACK][PAWN]));
//        }
//        // set up the white pieces
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][2].setIcon(new ImageIcon(
//                    chessPieceImages[WHITE][PAWN]));
//        }
//        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
//            chessBoardSquares[ii][3].setIcon(new ImageIcon(
//                    chessPieceImages[WHITE][STARTING_ROW[ii]]));
//        }
//    }

    public void setupNewGame(State s) {
        message.setText("Make your move!");
        for(int i=0;i<4;i++)
            for(int j=0;j<4;j++)
                if(s.board[i][j]!=0)
                    chessBoardSquares[j][i].setIcon(new ImageIcon(chessPieceImages[s.board[i][j]]));
    }

    private void createImages() {
        try {
            int kk=1;
            URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int ii = 1; ii >= 0; ii--) {
                for (int jj = 3; jj >= 0; jj--) {
                    chessPieceImages[kk++] = bi.getSubimage(
                            (jj+2) * 64, ii * 64, 64, 64);
                }
            }
            Image aux = chessPieceImages[3];
            chessPieceImages[3]=chessPieceImages[2];
            chessPieceImages[2]=aux;

            aux = chessPieceImages[7];
            chessPieceImages[7]=chessPieceImages[6];
            chessPieceImages[6]=aux;


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public final JComponent getGui(){
        return gui;
    }

    public void setPiece(MouseEvent event){

    }

    public void movePiece(MouseEvent event){

    }
}
