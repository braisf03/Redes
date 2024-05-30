package es.udc.redes.tutorial.tcp.server;
import java.io.IOException;
import java.net.*;

/** Multithread TCP echo server. */

public class TcpServer {

  public static void main(String[] argv) throws IOException {
    if (argv.length != 1) {
      System.err.println("Format: es.udc.redes.tutorial.tcp.server.TcpServer <port>");
      System.exit(-1);
    }

    ServerSocket serverSocket=null;

    int port = Integer.parseInt(argv[0]);

    try {
      // Create a server socket
      serverSocket= new ServerSocket(port);
      // Set a timeout of 300 secs
      serverSocket.setSoTimeout(300000);

      while (true) {
        // Wait for connections
        Socket socketcliente = serverSocket.accept();
        // Create a ServerThread object, with the new connection as parameter
        ServerThread serverThread = new ServerThread(socketcliente);
        // Initiate thread using the start() method
        serverThread.start();
      }

    } catch (SocketTimeoutException e) {
      System.err.println("Nothing received in 300 secs");
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
      e.printStackTrace();
    } finally{
      try{
        if(serverSocket!=null){
          serverSocket.close();
        }
      }catch(IOException e){
        throw new RuntimeException(e);
      }
    }
  }
}
