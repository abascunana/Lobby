package lobbyModel;

import lobbyController.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class LobbyManager implements Runnable {
    private Socket clientSocket;
    private String clientAddress;
    private boolean closed;
    Controller controller;





    public LobbyManager(Socket clientSocket, String clientAddress, Controller controller) {
        this.clientSocket = clientSocket;
        this.clientAddress = clientAddress;
        this.controller = controller;
        System.out.println("Client connection from " + clientAddress);
    }

    public void updateView() {
        new Thread(controller.getView()).start();
    }




    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        closed = false;




//TODO Cambiar forma de cerrado
        while (!closed) {
            try {
                out.println("eres jugador" + controller.getModel().clients);
                updateView();
                String inputLine = in.readLine();
                if (inputLine.toLowerCase().equals("bye")) {
                    System.out.println("Client (" + clientAddress + ") connection closed\n");
                    closed = true;
                    controller.getModel().clients = controller.getModel().clients-1;
                }
                //if player is master
                if (inputLine.toLowerCase().equals("up")) {
                    controller.getView().setPos();
                 controller.getView().updateParam();
                }
                if (inputLine.toLowerCase().equals("select")) {
                    controller.getView().selectParam();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        try {
            clientSocket.close();
            updateView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}