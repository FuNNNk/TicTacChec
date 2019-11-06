import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Runnable r=new Runnable() {
            @Override
            public void run() {
                Interfata inf=new Interfata();
                JFrame f = new JFrame("ChessChamp");
                f.add(inf.getGui());
                // Ensures JVM closes after frame(s) closed and
                // all non-daemon threads are finished
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // See https://stackoverflow.com/a/7143398/418556 for demo.
                f.setLocationByPlatform(true);

                // ensures the frame is the minimum size it needs to be
                // in order display the components within it
                f.pack();
                // ensures the minimum size is enforced.
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
