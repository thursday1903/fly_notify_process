package springboot.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import springboot.service.entities.GatewayRequest;
import springboot.utils.GsonUltilities;

/**
 * @author vietanh source from https://www.baeldung.com/a-guide-to-java-sockets
 *
 */
public class SocketServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SocketServer socketServer = new SocketServer();
		try {
			socketServer.start(9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ServerSocket serverSocket;

	public void start(int port) throws Exception {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("SERVER LISTENING ON PORT:" + port);
			while (true) {
				new EchoClientHandler(serverSocket.accept()).start();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class EchoClientHandler extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;

		public EchoClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		public void run() {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					if (".".equals(inputLine)) {
						out.println("bye");
						break;
					} else {
						GatewayRequest gwReq = (GatewayRequest) GsonUltilities.fromJson(inputLine,
								GatewayRequest.class);

						if (gwReq != null) {
							
						} else {

						}
						out.println(inputLine);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					in.close();
					out.close();
					clientSocket.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

		}
	}
}
