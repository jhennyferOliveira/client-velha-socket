package network.configuration;

import network.handler.ReceivedMessageHandler;

import java.rmi.RemoteException;


public class Client implements DataHandlerInterface {

    public Client() throws RemoteException {
        super();

    }
    public static DataHandlerInterface remoteObject;
    private final ReceivedMessageHandler receivedMessageHandler = new ReceivedMessageHandler();


    /**
     * Message received from the server
     */
    @Override
    public void sendMessage(String message) throws RemoteException {
        receivedMessageHandler.callService(message);
    }
}