package network.configuration;

import network.handler.DataFlowHandler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Thread {
    private final DataFlowHandler dataFlowHandler = DataFlowHandler.getInstance();
    private Socket socket;

    public void clientSocketConfiguration() {
        try {
            var port = 9090;
            var host = "localhost";
            this.socket = new Socket(host, port);
            System.out.println("Conectado....");
            this.start();
            var outputStream = new DataOutputStream(socket.getOutputStream());
            dataFlowHandler.sendMessage(outputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        DataInputStream inputStream;
        try {
            inputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataFlowHandler.receiveMessage(inputStream);
    }
}
