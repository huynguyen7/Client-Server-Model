import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPMulticastServer {
	public static void main(String[] args) throws IOException {
		System.setProperty("java.net.preferIPv4Stack", "true");

		System.out.println("\t\tUDP-Multicast-Server");
		Scanner scnr = new Scanner(System.in);

		// Get server's ip address and port.
		System.out.print("*ENTER IP ADDRESS: ");
		final String ip = scnr.nextLine();
		System.out.print("*ENTER PORT: ");
		final int port = scnr.nextInt();

		System.out.println("> INITIALIZING SERVER...");
		UDPMulticastServer server = new UDPMulticastServer(ip, port);
		server.start();
		scnr.close();
	}

	private final String ip;
	private final int port;
	private InetAddress group;
	private DatagramSocket socket;
	//private final Scanner scnr;

	public UDPMulticastServer(final String ip, final int port) {
		this.ip = ip;
		this.port = port;

		try {
			socket = new DatagramSocket();
			group = InetAddress.getByName(ip);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		System.out.println("> SERVER STARTED.");
		Scanner scnr = new Scanner(System.in);
		while(true) {
			System.out.print("> ");
			String message = scnr.nextLine();
			try {
				sendMessage(message);
				if(message.compareTo("exit") == 0)
					break;
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		socket.close();
		scnr.close();
	}

	private void sendMessage(String message) throws IOException {
		byte[] msgInBytes = message.getBytes();
		DatagramPacket packet = new DatagramPacket(msgInBytes, msgInBytes.length, group, port);
		socket.send(packet);
	}
	
	private void setUp() {
		
	}
}
