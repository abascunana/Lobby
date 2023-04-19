import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Model implements Runnable{


    //Esta clase creará un servidor que recoja nicks y los mande a la vista.
   private static int clientes = 0;
    ServerSocket serverSocket = null;
    Controller controller;


    ThreadedClient tc;


    public ThreadedClient getTc() {
        return tc;
    }

    public Controller getController() {
        return controller;
    }
    public static int getClientes() {
        return clientes;
    }

    public static void setClientes(int clientes) {
        Model.clientes = clientes;
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

  public Model(){
      try {
          serverSocket = new ServerSocket(1234);
      } catch (IOException e) {
          e.printStackTrace();
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
                tc = new ThreadedClient(clientSocket, clientAddress,this.controller);
                new Thread(tc).start();
                clientes++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(clientes);
        }
    }
}
