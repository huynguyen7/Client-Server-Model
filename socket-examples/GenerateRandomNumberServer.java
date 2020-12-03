import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.Random;

public class GenerateRandomNumberServer {
	private static Random random = new Random();

	public static void main(String[] args) throws IOException {
		Scanner scnr = new Scanner(System.in);
		System.out.print("SERVER PORT: ");
		int port = scnr.nextInt();
		
		try {
			// Create and open a server socket on a specified port.
			ServerSocket serverSocket = new ServerSocket(port);

			// Wait for the client request.
			Socket clientSocket = serverSocket.accept();
			
			// Get the client output stream
			// then use it to print (to the client side).
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

			// Create input stream.
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.printf("CLIENT HAS CONNECTED ON PORT %d.\n", port);

			{
				String inputLine;
				while((inputLine = in.readLine()) != null) {
					if(inputLine.compareTo("exit") == 0) break;
					else {
						try {
							int n = Integer.parseInt(inputLine);
							int rs = getRandomNumber(n);
							out.println("GENERATED RANDOM NUM " + rs);
						} catch(NumberFormatException e) {
							out.println("NOT A VALID INPUT.");
						}
					}
				}
			}

			clientSocket.close();
			scnr.close(); 
			serverSocket.close();
			in.close();
			out.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static int getRandomNumber(int input) {
		return random.nextInt(input + 1);
	}
}
