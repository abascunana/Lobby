import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class View extends JFrame implements Runnable {
    JTextArea players;
    Controller controller;

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    View() {
        setTitle("Lobby- Waiting for players...");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        //TRUE FULLSCREEN
        //setUndecorated(true);
        setLocationRelativeTo(null);
        add(addBack());
        setVisible(true);
    }

    private JLabel addBack() {
        JLabel myLabel = new JLabel(new ImageIcon("src/assets/space.jpg"));
        myLabel.setLayout(new BorderLayout());
        myLabel.add(addComponentB(), BorderLayout.CENTER);
        return myLabel;
    }

    private JPanel addComponentB() {
        JPanel panel = new JPanel();
        players = new JTextArea();
        players.setOpaque(false);
        players.setSize(500, 500);
        panel.add(players);
        try {
            String fontPath = "assets/poly.ttf";
            String absoluteFontPath = getClass().getClassLoader().getResource(fontPath).getPath();
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(absoluteFontPath));
            customFont = customFont.deriveFont(Font.BOLD, 100);
            players.setFont(customFont);

        } catch (Exception e) {
            // If the font file cannot be loaded, use a fallback font
            Font fallbackFont = new Font("Arial", Font.BOLD, 100);
            players.setFont(fallbackFont);
        }
        players.setForeground(Color.white);
        players.setEditable(false);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void run() {
            if (controller.getModel().getTc() != null) {
                players.setText("Total de jugadores:"+controller.getModel().clients);
            }
                }
        }



