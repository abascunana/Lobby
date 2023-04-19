import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Runnable {
    JTextArea nicks;
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
        myLabel.add(addComponents(), BorderLayout.WEST);
        return myLabel;
    }

    private JPanel addComponents() {
        JPanel panel = new JPanel();
        nicks = new JTextArea();
        nicks.setOpaque(false);
        nicks.setSize(500, 500);
        panel.add(nicks);
        Font font = new Font("Times New Roman", Font.BOLD, 50);
        nicks.setFont(font);
        nicks.setForeground(Color.white);
        nicks.setEditable(false);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void run() {
        if (controller.getModel().getTc() != null) {
            nicks.setText("");
            for (int i = 0; i < controller.getModel().getTc().names.size(); i++) {
                nicks.append("jugador nº" + controller.getModel().getTc().names.get(i).getId() + "\n");
            }
        }
    }
}
