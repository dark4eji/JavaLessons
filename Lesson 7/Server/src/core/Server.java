package core;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server
{
  private List<ClientHandler> clientHandlers;
  private AuthService authService;

  public AuthService getAuthService() {
    return authService;
  }

  public Server() {
    authService = new BaseAuthService();
    authService.start();
    clientHandlers =  new ArrayList<>();

    ServerSocket serverSocket = null;
    Socket clientSocket = null;
    try
    {
      serverSocket = new ServerSocket(8888);
      System.out.println("Server launched");

      while (true)
      {
        System.out.println("Waiting for client connection...");
        clientSocket = serverSocket.accept();
        ClientHandler client = new ClientHandler(clientSocket, this);
        clientHandlers.add(client);
        new Thread(client).start();
        System.out.println("Client connected!");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        serverSocket.close();
        clientSocket.close();
        System.out.println("Server stopped");
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  public void notifyAllClients(String msg)
  {
    for (ClientHandler clientHandler : clientHandlers)
    {
      clientHandler.sendMessage(msg);
    }
  }

  public void sendPrivateMessage(String msg, String nickname) {
    for (ClientHandler clientHandler : clientHandlers) {
      if (clientHandler.getName().equals(nickname)) {
        clientHandler.sendMessage(msg);
      }
    }
  }

  public void removeClient(ClientHandler clientHandler)
  {
    clientHandlers.remove(clientHandler);
  }


  public synchronized boolean isNickBusy(String nick) {
    for (ClientHandler o : clientHandlers) {
      if (o.getName().equals(nick)) {
        return true;
      }
    }
    return false;
  }

  public synchronized void subscribe(ClientHandler o) {
    clientHandlers.add(o);
  }


}
