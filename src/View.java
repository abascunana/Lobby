import javax.swing.*;
import java.awt.*;

public class View extends JFrame implements Runnable {
    JTextArea idteamblue;
    JTextArea idteamred;
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
        myLabel.add(addComponentB(), BorderLayout.WEST);
        myLabel.add(addComponentR(), BorderLayout.EAST);
        return myLabel;
    }

    private JPanel addComponentB() {
        JPanel panel = new JPanel();
        idteamblue = new JTextArea();
        idteamblue.setOpaque(false);
        idteamblue.setSize(500, 500);
        panel.add(idteamblue);
        Font font = new Font("Times New Roman", Font.BOLD, 50);
        idteamblue.setFont(font);
        idteamblue.setForeground(Color.blue);
        idteamblue.setEditable(false);
        panel.setOpaque(false);
        return panel;
    }
    private JPanel addComponentR() {
        JPanel panel = new JPanel();
        idteamred = new JTextArea();
        idteamred.setOpaque(false);
        idteamred.setSize(500, 500);
        panel.add(idteamred);
        Font font = new Font("Times New Roman", Font.BOLD, 50);
        idteamred.setFont(font);
        idteamred.setForeground(Color.red);
        idteamred.setEditable(false);
        panel.setOpaque(false);
        return panel;
    }
    @Override
    public void run() {
        if (controller.getModel().getTc() != null) {
            idteamblue.setText("");
            idteamred.setText("");
            for (int i = 0; i < controller.getModel().getTc().names.size(); i++) {
                if (controller.getModel().getTc().getPlayer().getEquipo().equals("rojo")){
                    idteamred.append("jugador nº" + controller.getModel().getTc().names.get(i).getId() + "\n");
                }
                else {
                    idteamblue.append("jugador nº" + controller.getModel().getTc().names.get(i).getId() + "\n");
                }

            }
        }
    }
}
