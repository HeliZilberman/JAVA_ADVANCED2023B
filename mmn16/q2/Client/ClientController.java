import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;


public class ClientController {

    @FXML
    private TextArea msgArea;

    @FXML
    private Text serverComp;
    private InetAddress servAdd;
    private String compName;
    private final int PORT_SERVER = 6666;
    private final int PORT_CLIENT = 4445;
    private boolean registered;
    private DatagramSocket socket;
    
    public void initialize() {
    	registered = false;
    	String [] args = Server.getAppArgs();
    	compName = "127.0.0.1";
    	if(args!= null && args.length > 0)
    		compName = args[0];
    	serverComp.setText(compName);
    
    }
    @FXML
    void clearPressed(ActionEvent event) {
    	msgArea.clear();
    }

    @FXML
    void registerPressed(ActionEvent event) {
    	if(!registered) {
    		try {
				servAdd = InetAddress.getByName(compName);
				socket = new DatagramSocket(PORT_CLIENT);
				socket.setSoTimeout(10000);
		        String request = "REGISTER";
		        byte[] buffer = request.getBytes();
		        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, servAdd, PORT_SERVER);
		        socket.send(packet);
		        registered = true;
		        startMessageReceiver();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void unregisterPressed(ActionEvent event) {
    	if(registered) {
	        try {
	        	String request = "UNREGISTER";
		        byte[] buffer = request.getBytes();
		        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, servAdd, PORT_SERVER);
				socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        registered = false;
			
    	}
    }
    //recives massages from server and displays it on client text area
    private void startMessageReceiver() {
    	Runnable r  = () -> {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                while (true ) {
                    try {
                        socket.receive(packet);
                        String message =new String(packet.getData()).substring(0, packet.getLength());
                        String receivedFrom = packet.getAddress().getHostAddress() + ":" + packet.getPort();
                        String timestamp = java.time.LocalDateTime.now().toString();

                        String formattedMessage = String.format("[%s] [%s] %s\n", timestamp, receivedFrom, message);
                        System.out.println(formattedMessage);
                        Platform.runLater(() -> msgArea.appendText(formattedMessage));
                    } catch (SocketTimeoutException e) {
                    	if(!registered)
                    		break;
                    }
                }
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Thread t = new Thread(r);
        t.start();

    }


}