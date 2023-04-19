import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
//TODO Poner rango del 1 al 8 y actualizarlo cada vez que algún jugador se desconecte de el lobby (función síncrona), los jugdores del 1 al 4 son rojos y del 5 al 8 azules
public class LobbyManager implements Runnable{
    private Socket clientSocket;
    private String clientAddress;
    private boolean closed;
    Controller controller;
    static ArrayList<Player> names = new ArrayList<>();
//TODO cuando un jugador se desconecta, todos los id's que estén por delante de éste se les resta uno (1,2,3x,4,5,6,7,8) --> (1,2,3,4,5,6,7)
    public LobbyManager(Socket clientSocket, String clientAddress, Controller controller) {
        this.clientSocket = clientSocket;
        this.clientAddress = clientAddress;
        this.controller = controller;
        System.out.println("Client connection from " + clientAddress);
    }
   public synchronized void updateView(){
        new Thread(controller.getView()).start();
   }

   public synchronized void updateId(Player id){
       names.add(id);
       updateView();
   }

    public void run() {
        //TODO el out no es necesario elminar en producción
        closed = false;
        Player player = new Player(controller.getModel().getClients());
       // String id="jugador nº"+controller.getModel().getClients()+"\n";
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
        while (!closed){
            try {
               String inputLine = in.readLine();
                if (inputLine.toLowerCase().equals("bye")){
                    System.out.println("Client (" + clientAddress + ") connection closed\n");
                    closed= true;
                }
                out.println("escribe bye para salir");

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        for (int i = player.getId(); i < names.size(); i++) {
            names.get(i).setId(names.get(i).getId()-1);
        }
        names.remove(player);
        //TODO if Lobby is completed (8 players) send players to next screen
        try {
            clientSocket.close();
            updateView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}