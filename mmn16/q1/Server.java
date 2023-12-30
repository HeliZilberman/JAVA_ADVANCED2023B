
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//server main
public class Server {
	private static final int PORT = 3333;
	private ServerTrivia trivia;
	public Server() {
		trivia = new ServerTrivia();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Trivia Game Server started on port " + PORT);
            
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                new ServerThread(clientSocket,trivia).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		new Server();
	}
}
