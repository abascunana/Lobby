package lobbyView;

import gameParam.GameRules;
import lobbyController.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class View extends JFrame implements Runnable {
    JTextArea players;
    Controller controller;
    JTextArea parameter;
    JLabel background;
    String[] paramList = new String[]{"back1","back2","back3"};
    String[] backPath=new String[]{"src/gameAssets/1space.jpg","src/gameAssets/2space.jpg","src/gameAssets/3space.jpg"};
    static int position;


    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }




    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public View() {
        setTitle("Lobby- Waiting for players...");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        //TRUE FULLSCREEN
       // setUndecorated(true);
        setLocationRelativeTo(null);
        add(addBack());
        setVisible(true);
    }

    private JLabel addBack() {
        background = new JLabel(new ImageIcon(backPath[0]));
        background.setLayout(new BorderLayout());
        background.add(addPlayerCount(), BorderLayout.CENTER);
        background.add(addParamList(), BorderLayout.WEST);
        return background;
    }

    private JPanel addPlayerCount() {
        JPanel panel = new JPanel();
        players = new JTextArea();
        players.setOpaque(false);
        players.setSize(500, 500);
        panel.add(players);
        try {
            String fontPath = "gameAssets/poly.ttf";
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
    public void selectParam(){
     background.setIcon(new ImageIcon(backPath[position]));
    }
    public void updateParam(){
        parameter.setText("");
        for (int i = 0; i < paramList.length; i++) {
            if (i==position){
                parameter.append("->"+paramList[i]+"\n");
            }
            else {
                parameter.append(paramList[i]+"\n");
            }
        }
    }
    public void setPos(){
        position++;
        if (position > paramList.length-1){
            position=0;
        }
    }
    private JPanel addParamList(){
        JPanel panel = new JPanel();
        parameter = new JTextArea();
        parameter.setOpaque(false);
        parameter.setSize(500, 500);
        updateParam();
        panel.add(parameter);
        try {
            String fontPath = "gameAssets/poly.ttf";
            String absoluteFontPath = getClass().getClassLoader().getResource(fontPath).getPath();
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(absoluteFontPath));
            customFont = customFont.deriveFont(Font.BOLD, 50);
            parameter.setFont(customFont);

        } catch (Exception e) {
            // If the font file cannot be loaded, use a fallback font
            Font fallbackFont = new Font("Arial", Font.BOLD, 100);
            parameter.setFont(fallbackFont);
        }
        parameter.setForeground(Color.white);
        parameter.setEditable(false);
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



