import javax.swing.JFrame;

public class SnakeRunner {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Snake s = new Snake();
        frame.add(s);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(798, 587);
    }
}