package es.udc.redes.tutorial.tcp.server;
import java.net.*;
import java.io.*;

/** Thread that processes an echo server connection. */

public class ServerThread extends Thread {
  private Socket socket;

  public ServerThread(Socket s) {
    // Store the socket s
    this.socket=s;
  }

  public void run() {
    BufferedReader breader;
    PrintWriter pwriter;
    try {
      // Set the input channel
      breader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
      // Set the output channel
      pwriter = new PrintWriter(socket.getOutputStream(),true);
      // Receive the message from the client
      String mensaje= breader.readLine();
      System.out.println("SERVER: Received " + mensaje
              + " from " + socket.getInetAddress().toString()
              + ":" + socket.getPort());
      // Sent the echo message to the client
      pwriter.println(mensaje);
      System.out.println("SERVER: Sending " + mensaje +
              " to " + socket.getInetAddress().toString() +
              ":" + socket.getPort());
      // Close the streams
      breader.close();
      pwriter.close();

    } catch (SocketTimeoutException e) {
      System.err.println("Nothing received in 300 secs");
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    } finally {
      try{
        if (socket!=null){
          socket.close();
        }
      }catch(IOException e){
        throw new RuntimeException(e);
      }
    }
  }
}
