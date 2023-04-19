import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadedClient implements Runnable{
    private Socket clientSocket;
    private String clientAddress;
    private boolean closed;
    Controller controller;
    static ArrayList<String> names = new ArrayList<>();
    public ThreadedClient(Socket clientSocket, String clientAddress,Controller controller) {
        this.clientSocket = clientSocket;
        this.clientAddress = clientAddress;
        this.controller = controller;
        System.out.println("Client connection from " + clientAddress);
    }
   public synchronized void actualizarView(){
        new Thread(controller.getView()).start();
   }
    public void run() {
        closed = false;
        String id="jugador nº"+controller.getModel().getClientes()+"\n";
        names.add(id);
        actualizarView();
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
        names.remove(id);
        try {
            clientSocket.close();
            actualizarView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}