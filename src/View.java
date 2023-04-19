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

    View(){
        setTitle("Lobby");
        setSize(new Dimension(1920,1080));
        setLocationRelativeTo(null);
        add(crearFondo());
        setVisible(true);
    }
    private JLabel crearFondo(){
        JLabel myLabel = new JLabel(new ImageIcon("src/assets/space.jpg"));
        myLabel.setLayout(new BorderLayout());
        myLabel.add(crearComponentes(), BorderLayout.WEST);
        return myLabel;
    }
    private JPanel crearComponentes(){
        JPanel panel= new JPanel();
        nicks = new JTextArea();
        nicks.setOpaque(false);
        nicks.setSize(500,500);
       panel.add(nicks);
        Font font = new Font("Arial", Font.PLAIN, 80);
        nicks.setFont(font);
        nicks.setForeground(Color.white);
        nicks.setEditable(false);
        panel.setOpaque(false);
        return panel;
    }

    @Override
    public void run() {
            if (controller.getModel().getTc()!= null){
                nicks.setText("");
                for (int i = 0; i < controller.getModel().getTc().names.size(); i++) {
                    nicks.append(controller.getModel().getTc().names.get(i));
                }
            }
        }
}
