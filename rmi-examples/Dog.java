import java.util.Random;

public class Dog implements Pet {
	private final String name;
	private int age;
	private final Random rand = new Random();
	private final int NUM_TIMES = 5; // max random numeber.
	private final String BARK_SOUND = "woof ";

	public Dog(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String makeNoise() {
		StringBuilder s = new StringBuilder();
		int n = rand.nextInt(NUM_TIMES) + 1;
		for(int i = 1; i <= n; ++i)
			s.append(BARK_SOUND);

		return s.toString();
	}
}
