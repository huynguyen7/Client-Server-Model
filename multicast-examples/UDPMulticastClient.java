import java.net.*;
import java.util.Scanner;
import java.util.Enumeration;
import java.io.IOException;

public class UDPMulticastClient implements Runnable {
	public static void main(String[] args) {
		System.out.println("\t\tUDP-Multicast-Client");
		Scanner scnr = new Scanner(System.in);

		// Get server's ip address and port to connect.
		System.out.print("*ENTER IP ADDRESS: ");
		final String ip = scnr.nextLine();
		System.out.print("*ENTER PORT: ");
		final int port = scnr.nextInt();

		UDPMulticastClient client = new UDPMulticastClient(ip, port);
		Thread t = new Thread(client);
		t.start();

		try {
			t.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}

		scnr.close();
	}

	private final String ip;
	private final int port;
	private final int BUFFER_SIZE = 1024;
	private MulticastSocket socket;
	private InetAddress group;
	
	public UDPMulticastClient(final String ip, final int port) {
		this.ip = ip;
		this.port = port;

		try {
			group = InetAddress.getByName(ip);
			socket = new MulticastSocket(port);
			setUp();
			socket.joinGroup(group); // join the group to receive message from server.
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("Waiting for message..");
				String message = receiveAndParseMessage();
				if(message.compareTo("exit") == 0) break;
				System.out.println("> " + message);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

		try {
			socket.leaveGroup(group); // leave the group.
		} catch(IOException e) {
			e.printStackTrace();
		}

		socket.close(); // close the socket.
	}

	private String receiveAndParseMessage() throws IOException {
		DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
		socket.receive(packet);
		String message = new String(packet.getData(), packet.getOffset(), packet.getLength());
		return message;
	}

	private void setUp() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
			while(networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces.nextElement();
				Enumeration<InetAddress> addressesFromNetworkInterface = networkInterface.getInetAddresses();
				while (addressesFromNetworkInterface.hasMoreElements()) {
					InetAddress inetAddress = addressesFromNetworkInterface.nextElement();
					if(inetAddress.isSiteLocalAddress()
						&& !inetAddress.isAnyLocalAddress()
						&& !inetAddress.isLinkLocalAddress()
						&& !inetAddress.isLoopbackAddress()
						&& !inetAddress.isMulticastAddress()) {
						socket.setNetworkInterface(NetworkInterface.getByName(networkInterface.getName()));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
