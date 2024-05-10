
package ToeFX;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
//Austin Abro
public class ToeFXGameServer {

    private static final int PORT = 5454;
    private static final Set<ToeFXGameClientHandler> clients = new HashSet<>();
    private static final String[] playerSymbols = {"X", "O"};
    private static int currentPlayerIndex = 0;
    private static boolean isPlayerOneTurn = true;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true && clients.size() < 2) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");

                // Create a new thread to handle the client
                ToeFXGameClientHandler clientHandler = new ToeFXGameClientHandler(clientSocket, playerSymbols[currentPlayerIndex]);
                clients.add(clientHandler);
                new Thread(clientHandler).start();

                // Switch to the next player
                currentPlayerIndex = (currentPlayerIndex + 1) % playerSymbols.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    //----------------------------------------------------------------------//  
    //--------------------------| Handler |---------------------------------//    
    //----------------------------------------------------------------------//    
    private static class ToeFXGameClientHandler implements Runnable {
        private final Socket clientSocket;
        private final String playerSymbol;
        private PrintWriter writer;

        public ToeFXGameClientHandler(Socket clientSocket, String playerSymbol) {
            this.clientSocket = clientSocket;
            this.playerSymbol = playerSymbol;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Notify the client about its assigned mark
                writer.println("You are player " + playerSymbol);

                // Broadcast the assigned mark to all clients
                broadcast(playerSymbol + " has joined the game", this);

                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println("Received message from player " + playerSymbol + ": " + message);
                    synchronized (ToeFXGameServer.class) {
                        // Check if it's the player's turn to send a message
                        if ((playerSymbol.equals("X") && isPlayerOneTurn) || (playerSymbol.equals("O") && !isPlayerOneTurn)) 
                        {
                            broadcast(playerSymbol + ": " + message, this);
                            isPlayerOneTurn = !isPlayerOneTurn; // Switch turn
                        } 
                        else {
                            // It's not the player's turn, notify the client
                            writer.println("Wait for your turn");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Remove client when it disconnects
                clients.remove(this);
                broadcast(playerSymbol + " has left the game", this);
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void broadcast(String message, ToeFXGameClientHandler sender) {
            // Send the message to all connected clients, excluding the sender
            // In our case, this is just whoever the other client is
            for (ToeFXGameClientHandler client : clients) {
                    client.writer.println(message + " " + playerSymbol);
            }
        }
    }
}


