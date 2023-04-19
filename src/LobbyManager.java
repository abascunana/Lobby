import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

//TODO Poner rango del 1 al 8.
public class LobbyManager implements Runnable {
    private Socket clientSocket;
    private String clientAddress;
    private boolean closed;
    Controller controller;
    static ArrayList<Player> names = new ArrayList<>();

    public LobbyManager(Socket clientSocket, String clientAddress, Controller controller) {
        this.clientSocket = clientSocket;
        this.clientAddress = clientAddress;
        this.controller = controller;
        System.out.println("Client connection from " + clientAddress);
    }

    public synchronized void updateView() {
        new Thread(controller.getView()).start();
    }

    public synchronized void updateId(Player id) {
        names.add(id);
        updateView();
    }

    public synchronized void removePlayer(Player player) {
        for (int i = player.getId(); i < names.size(); i++) {
            names.get(i).setId(names.get(i).getId() - 1);
        }
        names.remove(player);
        controller.getModel().clients = controller.getModel().clients - 1;
    }

    public void run() {
        closed = false;
        Player player = new Player(controller.getModel().getClients());
        updateId(player);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//Cambiar forma de cerrado
        while (!closed) {
            try {
                out.println("eres jugador" + player.getId());
                String inputLine = in.readLine();
                if (inputLine.toLowerCase().equals("bye")) {
                    System.out.println("Client (" + clientAddress + ") connection closed\n");
                    closed = true;
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        }
        removePlayer(player);
        //TODO if Lobby is completed (8 players) out the player id (print) to the player and send them to next screen (project)
        try {
            clientSocket.close();
            updateView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}