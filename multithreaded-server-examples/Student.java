import java.util.*;
import java.net.*;
import java.io.*;

/*
 
	Student act as a 'client' in this example.

 **/

public class Student {
	private static final String HOST_NAME = "localhost";

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.print("SERVER PORT: ");
		int port = scnr.nextInt();

		System.out.print("ENTER USER NAME: ");
		String name = scnr.nextLine();
		Student s = new Student(name, port);
	}

	private final String name;

	public Student(final String name, final int port) {
		this.name = name;
		run(port);
	}
	
	public void run(final int port) {
		try {
			// Connect to server (running on localhost).
			Socket socket = new Socket(InetAddress.getByName(HOST_NAME), port);
			
			// Get server output printer.
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);


			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("CONNECTED TO THE SERVER ON PORT " + port);

			{
				String inputLine;
				while(true) {
					System.out.print("INPUT = ");
					inputLine = stdIn.readLine();

					if(inputLine == null) break;
					else if(inputLine.compareTo("exit") == 0) break;
					else if(inputLine.compareTo("start") == 0) {
						System.out.println("-> Waiting to receive homework..");
						out.println(inputLine);

						// Get input stream from client socket.
						ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
						
						ObjectOutputStream streamToServer = new ObjectOutputStream(socket.getOutputStream());

						// Retrieve Homework from School server.
						Homework hw = (Homework) inputStream.readObject();
						System.out.println("-> Homework received from school.\nStart doing homework..");
						hw.start();
						
						streamToServer.writeObject(hw);
						System.out.println("-> Finished homework.. Sending to school and waiting for grades..");
						System.out.println("-> SERVER RESPONSE: " + in.readLine());
						break;
					} else {
						out.println(inputLine);
						System.out.println("-> SERVER RESPONSE: " + in.readLine());
					}

				}

				socket.close();
			}
		} catch(UnknownHostException e) {
		} catch(IOException e) {
		} catch(ClassNotFoundException e) {
		}
	}
}
