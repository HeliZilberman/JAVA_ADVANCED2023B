import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

//server controller to send to client questions
public class ServerController extends Thread {

    @FXML
    private TextArea msg;
    private DatagramSocket socket;
    private ArrayList <InetAddress> clients; // list of clierts
    private final int PORT_SERVER = 6666;
    private final int PORT_CLIENT = 4445;
    public void initialize() {
    	clients = new ArrayList<InetAddress>();
    	 try {
    		 //group = InetAddress.getByName("230.0.0.1");
			socket = new DatagramSocket(PORT_SERVER);
			System.out.println("Server waiting on port " + PORT_SERVER);
	    	start();
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("initilize.............");
		}
    }

    @FXML
    void sendPressed(ActionEvent event) {
    	byte[] buffer = msg.getText().getBytes();
    	msg.setText("");
    	try {
    		for(InetAddress client : clients) {
    			 DatagramPacket packet = new DatagramPacket(buffer, buffer.length, client, PORT_CLIENT);
    	         socket.send(packet);
    		}
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
    	byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		 while (true) {
		        try {
					socket.receive(packet);
					String request = new String(packet.getData()).substring(0, packet.getLength());
		            InetAddress clientAddress = packet.getAddress();
		            if (request.equals("REGISTER")) {
		            	registerClient(clientAddress);
		            } else if (request.equals("UNREGISTER")) {
		            	unregisterClient(clientAddress);
		            } else
		            	System.out.println(request);
				} catch (IOException e) {
					System.out.println("server run.............");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		 }
        
    }
    private void registerClient(InetAddress clientAddress) {
        if (!clients.contains(clientAddress)) {
            clients.add(clientAddress);
            System.out.println("Client registered: " + clientAddress);
        }
    }

    private void unregisterClient(InetAddress clientAddress) {
        if (clients.contains(clientAddress)) {
            clients.remove(clientAddress);
            System.out.println("Client unregistered: " + clientAddress);
        }
    }
}
