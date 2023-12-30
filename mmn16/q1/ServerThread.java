import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
//server threads sends questions to client
public class ServerThread extends Thread{
	private Socket socket;
	private OutputStream outputStream;
	private ObjectOutputStream objOutputStream;
	private BufferedReader reader;
	private ServerTrivia trivia;
	public ServerThread(Socket socket, ServerTrivia trivia) {
		try {
			this.socket = socket;
			this.trivia = trivia;
			outputStream = this.socket.getOutputStream();
			objOutputStream = new ObjectOutputStream(outputStream);  
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch(IOException e) {
			closeResources();
		}
	}
	@Override
    public void run() {
		int index = 0;
		while (socketIsAlive() && trivia.canAskQuestion(index)) {
			try {
				objOutputStream.writeObject(trivia.getQuestion(index));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			index++;
        }
		closeResources();
    }
	private void closeResources() {
		try {
            if (socket != null) {
                socket.close();
            }
            if (objOutputStream != null) {
            	objOutputStream.close();
            }
            if (outputStream != null) {
            	outputStream.close();
            }
            if (reader != null) {
            	reader.close();
            }
        } catch (IOException e) {
            System.out.println("Failed to close resources");
        }
	}
	   public boolean socketIsAlive() {
	        return socket.isConnected() && !socket.isClosed();
	    }
	   private String receiveAnswer(int timeoutSeconds) throws IOException {
		    try {
		        socket.setSoTimeout(timeoutSeconds * 1000);
		        return reader.readLine();
		        
		    } catch (SocketTimeoutException e) {
		        return null;
		    }
		}
}
