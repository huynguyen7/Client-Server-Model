import java.net.*;
import java.io.*;
import java.util.Scanner;

public class GetRandomNumberClient {
	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) throws IOException {
		Scanner scnr = new Scanner(System.in);
		System.out.print("SERVER PORT: ");
		int port = scnr.nextInt();
		
		try {
			// Connect to server (running on localhost).
			Socket socket = new Socket(InetAddress.getByName(HOST_NAME), port);

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.printf("CONNECTED TO THE SERVER ON PORT %d.\n", port);

			{
				String inputLine;
				//while((inputLine = stdIn.readLine()) != null) {
				while(true) {
					System.out.print("INPUT = ");
					inputLine = stdIn.readLine();

					if(inputLine == null) break;
					else if(inputLine.compareTo("exit") == 0) break;
					else {
						out.println(inputLine);
						System.out.println("-> SERVER RESPONSE: " + in.readLine());
					}
				}
			}

			stdIn.close();
			socket.close();
			scnr.close();
			out.close();
			in.close();
		} catch(UnknownHostException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
