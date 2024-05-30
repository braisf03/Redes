package es.udc.redes.tutorial.tcp.server;

import java.net.*;
import java.io.*;

/**
 * MonoThread TCP echo server.
 */
public class MonoThreadTcpServer {

    public static void main(String[] argv) throws IOException{
        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.tcp.server.MonoThreadTcpServer <port>");
            System.exit(-1);
        }

        BufferedReader breader;
        PrintWriter pwriter;
        ServerSocket serverSocket=null;

        int port = Integer.parseInt(argv[0]);

        try {
            // Create a server socket
            serverSocket = new ServerSocket(port);
            // Set a timeout of 300 secs
            serverSocket.setSoTimeout(300000);

            while (true) {
                // Wait for connections
                Socket socketcliente = serverSocket.accept();
                // Set the input channel
                breader= new BufferedReader(new InputStreamReader(socketcliente.getInputStream()));
                // Set the output channel
                pwriter = new PrintWriter(socketcliente.getOutputStream(),true);
                // Receive the client message
                String mensaje= breader.readLine();
                System.out.println("SERVER: Received " + mensaje
                        + " from " + socketcliente.getInetAddress().toString()
                        + ":" + socketcliente.getPort());

                // Send response to the client
                pwriter.println(mensaje);
                System.out.println("SERVER: Sending " + mensaje +
                        " to " + socketcliente.getInetAddress().toString() +
                        ":" + socketcliente.getPort());

                // Close the streams
                breader.close();
                pwriter.close();

            }

        } catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try{
                if(serverSocket!=null){
                    serverSocket.close();
                }
            }catch(IOException e){
                throw new RuntimeException();
            }
        }
    }
}