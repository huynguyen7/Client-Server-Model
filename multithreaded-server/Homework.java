import java.io.Serializable;
import java.util.Random;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Homework implements Serializable {
	private boolean isDone;
	private final int NUM_QUESTIONS = 5;
	private HashMap<Serializable, Serializable[]> questions;
	private ArrayList<Serializable> answers;

	// TRANSIENT - variable that does not need to be serialized.
	private final transient int MAX_NUM = 1000; // MAX RANDOM NUMBER TO GENERATE FOR QUESTIONS.
	private final transient String[] operations = {"+","-","*","/"};

	public Homework() {
		questions = generateQuestions();
		isDone = false;
		answers = new ArrayList<>();
	}

	// Answer 5 questions, each true answer will be 2 points added to the grade.
	public void start() {
		if(isDone) {
			System.out.println("You have done this homework..");
			return;
		}

		Scanner scnr = new Scanner(System.in);

		for(int i = 0; i < NUM_QUESTIONS; ++i) {
			String[] template = (String[]) questions.get(i);
			System.out.printf("#%d: %s %s %s = ", i, template[0], template[1], template[2]);
			int studentAnswer = scnr.nextInt();
			answers.add(studentAnswer);
		}
		
		// Finish the homework.
		isDone = true;
		scnr.close();
	}

	public boolean isDone() {
		return isDone;
	}

	public int getNumQuestions() {
		return NUM_QUESTIONS;
	}

	public ArrayList<Serializable> getAnswers() {
		return answers;
	}

	public HashMap<Serializable, Serializable[]> getQuestions() {
		return questions;
	}

	private HashMap<Serializable, Serializable[]> generateQuestions() {
		Random rand = new Random();
		HashMap<Serializable, Serializable[]> questions = new HashMap<>();
		for(int i = 0; i < NUM_QUESTIONS; ++i) {
			String[] template = new String[3];
			template[0] = String.valueOf(rand.nextInt(MAX_NUM));
			template[1] = operations[rand.nextInt(4)];
			template[2] = String.valueOf(rand.nextInt(MAX_NUM));
			questions.put(i, template);
		}

		return questions;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("-----------------\n");
		s.append("IS DONE: " + isDone + "\n\n");

		for(int i = 0; i < NUM_QUESTIONS; ++i) {
			String[] template = (String[]) questions.get(i);
			s.append("#" + i + ": ");
			for(String a: template)
				s.append(a + " ");
			s.append("\n");
		}

		s.append("\n-----------------\n");
		return s.toString();
	}
}
