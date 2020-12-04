import java.rmi.Remote;
import java.rmi.RemoteException;

/*
	Any class implements this interface can use the methods remotely.
 **/

public interface Pet extends Remote {
	String makeNoise() throws RemoteException;
}
