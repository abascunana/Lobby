import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Model implements Runnable{

   public static int clients = 0;

   ServerSocket serverSocket = null;
    Controller controller;
    LobbyManager lm;

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
                lm = new LobbyManager(clientSocket, clientAddress,this.controller);
                new Thread(lm).start();
                clients++;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(clients);

        }
    }
}
