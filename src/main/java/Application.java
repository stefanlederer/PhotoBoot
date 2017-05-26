import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by stefanlederer on 19.05.17.
 */
public class Application {

    JFrame mainFrame;

    int mainFrameWidth = 600;
    int mainFrameHeight = 400;

    String[][] selectedFiles;

    private Application() {

        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(mainFrameWidth, mainFrameHeight));
        mainFrame.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - mainFrameWidth / 2), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - mainFrameHeight / 2));

        final JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        mainFrame.add(menuPanel, BorderLayout.PAGE_START);

        BufferedImage plusIcon = null;
        try {
            plusIcon = ImageIO.read(ClassLoader.getSystemResource("icons/plus.png"));
        } catch (IOException e) {
            System.out.println("add Image error: " + e);
        }
        JLabel addButton = new JLabel(new ImageIcon(plusIcon));
        addButton.setBorder(BorderFactory.createEmptyBorder());
        addButton.setPreferredSize(new Dimension(20, 20));
        addButton.setLocation(10, 10);
        menuPanel.add(addButton);

        addButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JFileChooser filechooser = new JFileChooser();
                int returnValue = filechooser.showDialog(null, "Auswählen");

                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    System.out.print(filechooser.getSelectedFile().getName());
                    selectedFiles = FileAction.getSelectedFiles(filechooser.getSelectedFile().getName(), filechooser.getSelectedFile().getAbsolutePath());
                }
                for (int a = 0; a < selectedFiles.length; a++) {
                    System.out.println(selectedFiles[a][0]);
                }
            }
        });

        JButton compressButton = new JButton("Komprimieren");
        menuPanel.add(compressButton);

        compressButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("compress Button Clicked");
            }
        });


        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new Application();
    }

}
