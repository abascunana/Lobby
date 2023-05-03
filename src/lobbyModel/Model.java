package lobbyModel;

import gameParam.GameRules;
import lobbyController.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Model implements Runnable {

    public static int clients = 0;
    ServerSocket serverSocket = null;
    Controller controller;
    LobbyManager lm;
    GameRules gameRules;

    public LobbyManager getTc() {
        return lm;
    }

    public Controller getController() {
        return controller;
    }

    public static int getClients() {
        return clients;
    }

    public static void setClients(int clients) {
        Model.clients = clients;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Model(GameRules gameRules) {
        this.gameRules = gameRules;
        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
public void comprobarJugadores(Socket clientSocket){
    PrintWriter out = null;
    try {
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    if (clients == gameRules.getNumPlayers()){
        try {
            out.println("You can't connect to this game due to high demand of players, wait until the next round");
            clientSocket.close();
            throw new Exception("limit reached, player cannot connect");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
    @Override
    public void run() {
        while (true) {
            System.out.println("Waiting for a client...");
            try {
                Socket clientSocket;
                String clientAddress;
                clientSocket = serverSocket.accept();
                clientAddress = clientSocket.getInetAddress().getHostAddress();

                lm = new LobbyManager(clientSocket, clientAddress, this.controller);
                comprobarJugadores(clientSocket);
                new Thread(lm).start();
                clients++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(clients);

        }
    }
}
