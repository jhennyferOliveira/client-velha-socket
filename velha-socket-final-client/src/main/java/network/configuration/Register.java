package network.configuration;

import view.MainView;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;

public class Register {

    private static Integer port;
    private static String serverIPAddress;
    private static Integer serverPort;

    public static void main(String[] args) {

        setIPAddressAndPort();
        try {
            Client client = new Client();

            // Wraps the object so the client can call the remote method
            DataHandlerInterface stub = (DataHandlerInterface) UnicastRemoteObject.exportObject(client, 0);

            // Create RMI registry
            Registry registry = LocateRegistry.createRegistry(port);

            // Bind the remote object's stub in the registry
            registry.rebind("Client", stub);

            System.out.println("Client/Server ready");

            // Looks for the server
            try {
                Client.remoteObject = (DataHandlerInterface) Naming.lookup("//" + serverIPAddress + ":" + serverPort + "/Server");
                System.out.println("Object Localized!");
            } catch(Exception e){
                System.err.println(e.getMessage());
            }

            // Start the UI
            SwingUtilities.invokeLater(() -> {
                try {
                    MainView.getInstance().setUpFrame();
                } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });


        } catch (Exception e) {
            System.err.println("Client(Server) exception " + e);
            e.printStackTrace();
        }
    }

    private static void setIPAddressAndPort() {
        Properties properties = new Properties();
        try (FileInputStream configFile = new FileInputStream("velha-socket-final-client/src/main/resources/config.properties")) {
            properties.load(configFile);
        } catch (IOException e) {
            System.err.println("Error reading the configuration file: " + e.getMessage());
            System.exit(1);
        }
        port = Integer.parseInt(properties.getProperty("port"));
        serverIPAddress = properties.getProperty("server-ip");
        serverPort = Integer.parseInt(properties.getProperty("server-port"));
    }
}