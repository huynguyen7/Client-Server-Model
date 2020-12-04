import java.rmi.RemoteException;
import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/*
	This class acts as a 'server' in the application.
 **/

public class DogCenter extends Dog {

	public DogCenter(String name, int age) {
		super(name, age);
	}

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.print("SERVER PORT: ");
		final int port = scnr.nextInt();

		try {
			// instantiate the remote object.
			Pet obj = new Dog("Mitsy", 1);

			// export the remote object to the stub.
			Pet stub = (Pet) UnicastRemoteObject.exportObject(obj, 0);
			
			// binding the remote object (stub) to the registry.
			Registry registry = LocateRegistry.getRegistry(port);
			registry.rebind("Pet", stub);

			System.out.println("SERVER STARTED.");

			scnr.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
