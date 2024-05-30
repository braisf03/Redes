package es.udc.redes.tutorial.udp.server;

import java.net.*;

/**
 * Implements a UDP echo server.
 */
public class UdpServer {

    public static void main(String argv[]) {
        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.udp.server.UdpServer <port_number>");
            System.exit(-1);
        }

        DatagramSocket dataSocket = null;

        int port = Integer.parseInt(argv[0]);

        try {
            // Create a server socket
            dataSocket = new DatagramSocket(port);
            // Set maximum timeout to 300 secs
            dataSocket.setSoTimeout(300000000);
            byte[] paquete = new byte[1024];

            while (true) {
                // Prepare datagram for reception
                DatagramPacket rdatapacket = new DatagramPacket(paquete, paquete.length);
                // Receive the message
                dataSocket.receive(rdatapacket);
                String mensaje = new String(rdatapacket.getData());
                System.out.println("SERVER: Received " + mensaje + " from " + rdatapacket.getSocketAddress());
                // Prepare datagram to send response
                DatagramPacket sdatapacket = new DatagramPacket(paquete, paquete.length, rdatapacket.getAddress(), rdatapacket.getPort());
                // Send response
                dataSocket.send(sdatapacket);
                System.out.println("SERVER: Sending " + mensaje + " to " + rdatapacket.getSocketAddress());
            }

            // Uncomment next catch clause after implementing the logic
        } catch (SocketTimeoutException e) {
            System.err.println("No requests received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (dataSocket != null) {
                dataSocket.close();
            }
        }
    }
}
