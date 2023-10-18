package cat.institutmarianao.race;

public class Score {
	private Runner[] runners;
	private int last;

	public Score(int numRunners) {
		runners = new Runner[numRunners];
		last = 0;
	}

	// Setter
	public synchronized void addRunner(Runner runner) {
		runners[last++] = runner;
	}

	// Getter
	public synchronized Runner[] getRunners() {
		return runners;
	}

	public synchronized void printResults() {
		System.out.println("Results:");
		for (Runner r : runners) {
			System.out.println(r);
		}
	}
}
