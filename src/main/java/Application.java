import javax.swing.*;
import java.awt.*;

/**
 * Created by stefanlederer on 19.05.17.
 */
public class Application {

    JFrame mainFrame;

    int mainFrameWidth = 600;
    int mainFrameHeight = 400;

    private Application() {

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension( mainFrameWidth, mainFrameHeight));
        mainFrame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2 - mainFrameWidth/2), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2 - mainFrameHeight/2));

        JPanel menuPanel = new JPanel();
        mainFrame.add(menuPanel);
        mainFrame.getContentPane().add(menuPanel, BorderLayout.LINE_START);


        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String [] args) {
        new Application();
    }

}
