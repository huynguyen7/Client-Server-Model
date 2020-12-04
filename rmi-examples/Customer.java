import java.rmi.RemoteException;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/*
	This class acts as a 'client' in the application.
 **/

public class Customer {

	private Customer() {}

	private static final String HOST_NAME = "localhost";

	public static void main(String args[]) {
		Scanner scnr = new Scanner(System.in);
		System.out.print("SERVER PORT: ");
		final int port = scnr.nextInt();

		try {
			// get the registry for connection.
			Registry registry = LocateRegistry.getRegistry(HOST_NAME, port);

			// look up the registry for the remote object on the server side.
			Pet stub = (Pet) registry.lookup("Pet");

			// call the remote method through the remote object.
			String output = stub.makeNoise();
			System.out.println("-> SERVER RESPONSE WITH STRING: " + output);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
