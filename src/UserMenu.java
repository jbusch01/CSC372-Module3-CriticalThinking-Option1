import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UserMenu extends JFrame {
    private JTextArea textArea;

    public UserMenu() {
        setTitle("Main Menu");
        setSize(200, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");

        JMenuItem menuItem1 = new JMenuItem("Show Date & Time");
        JMenuItem menuItem2 = new JMenuItem("Save to Text File");
        JMenuItem menuItem3 = new JMenuItem("Random Shade of Green");
        JMenuItem menuItem4 = new JMenuItem("Exit Program");

        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        menuItem1.addActionListener(e -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            textArea.append("Date/Time: " + now.format(formatter) + "\n");
        });

        menuItem2.addActionListener(e -> {
            try (FileWriter writer = new FileWriter("log.txt")) {
                writer.write(textArea.getText());
                JOptionPane.showMessageDialog(this, "Saved to log.txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        menuItem3.addActionListener(e -> {
            Random rand = new Random();
            int green = 100 + rand.nextInt(156);
            int red = rand.nextInt(100);
            int blue = rand.nextInt(100);
            Color randomGreen = new Color(red, green, blue);

            getContentPane().setBackground(randomGreen);
            textArea.setBackground(randomGreen);
            textArea.append("Green Shade: RGB(" + red + "," + green + "," + blue + ")\n");
        });

        menuItem4.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserMenu().setVisible(true));
    }
}