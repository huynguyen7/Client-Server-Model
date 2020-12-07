import java.net.*;
import java.util.*;
import java.io.*;

/*
 
	School acts as a 'server' in this example.
 
 **/

public class School {
	private static final int POINT_FOR_CORRECT_ANSWER = 2;

	public static void main(String[] args) {
		System.out.print("SERVER PORT: ");
		Scanner scnr = new Scanner(System.in);
		final int port = scnr.nextInt();
		System.out.print("NUM STUDENT: ");
		final int maxNumStudents = scnr.nextInt();

		ServerSocket serverSocket = null;
		try {
			// Create and open a server socket on a specified port.
			serverSocket = new ServerSocket(port);
		} catch(IOException e) {}
		
		int numStudents = 0;
		while(numStudents++ < maxNumStudents) {
			try {

				// Wait for the student request.
				Socket clientSocket = serverSocket.accept();

				Thread thread = new Thread(() -> {
					try {

						System.out.printf("STUDENT HAS CONNECTED THROUGH PORT %d.\n\n", port);

						// Get the client output stream printer.
						PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

						// Create input stream reader. 
						BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						
						{
							String inputLine;
							while(true) {
								inputLine = in.readLine();
								if(inputLine == null) break;
								else if(inputLine.compareTo("exit") == 0) {
									break;
								} else if(inputLine.compareTo("start") == 0) {
									System.out.println("SENDING HOMEWORK TO STUDENT..");

									// Create new homework.
									Homework hw = new Homework();

									// Get the client output stream object.
									ObjectOutputStream streamToClient = new ObjectOutputStream(clientSocket.getOutputStream());

									// Get the server output stream object.
									ObjectInputStream streamFromClient = new ObjectInputStream(clientSocket.getInputStream());


									// Send the homework to the student.
									streamToClient.writeObject(hw);

									Homework finishedHw = (Homework) streamFromClient.readObject();
									int grade = getGrade(finishedHw);
									out.println("YOUR GRADE IS: " + grade);
								} else {
									out.println("INVALID INPUT.");
								}
							}
							
							clientSocket.close();
						}
					} catch(Exception e) {}
				});
				thread.start();

			} catch(IOException e) {}
		}
		
		try {
			serverSocket.close();
		} catch(IOException e) {}
	}

	private static int getGrade(Homework hw) {
		int grade = 0;
		ArrayList<Serializable> studentAnswers = hw.getAnswers();
		HashMap<Serializable, Serializable[]> questions = hw.getQuestions();

		for(int i = 0; i < hw.getNumQuestions(); ++i) {
			int studentAns = (int) studentAnswers.get(i);
			String[] question = (String[]) questions.get(i);

			int ans = 0;
			if(question[1].compareTo("+") == 0) {
				ans = Integer.parseInt(question[0]) + Integer.parseInt(question[2]);
			} else if(question[1].compareTo("-") == 0) {
				ans = Integer.parseInt(question[0]) - Integer.parseInt(question[2]);
			} else if(question[1].compareTo("*") == 0) {
				ans = Integer.parseInt(question[0]) * Integer.parseInt(question[2]);
			} else { // "/"
				ans = Integer.parseInt(question[0]) / Integer.parseInt(question[2]);
			}

			if(ans == studentAns) grade += POINT_FOR_CORRECT_ANSWER;
		}

		return grade;
	}
}
