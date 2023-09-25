import network.configuration.Client;
import view.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                MainView.getInstance().setUpFrame();
            } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            new Thread(() -> {
                new Client().clientSocketConfiguration();
            }).start();
        });
    }
}
