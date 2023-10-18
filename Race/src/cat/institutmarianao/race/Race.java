package cat.institutmarianao.race;

public class Race {
	public static final int DISTANCE = 1500;

	private static final String[] RUNNERS = { "Usain BOLT", "Yohan BLAKE", "Christian COLEMAN", "Trayvon BROMELL",
			"Nesta CARTER" };
	private static Thread[] runners;

	public static void main(String[] args) throws Exception {

		// Preparamos el marcador
		Score score = new Score(RUNNERS.length);

		// Preparamos los corredores
		runners = new Thread[RUNNERS.length];

		for (int i = 0; i < RUNNERS.length; i++) {
			runners[i] = new Thread(new Runner(RUNNERS[i], score));
			runners[i].setName(RUNNERS[i]);
		}

		// A correr
		for (Thread runner : runners) {
			runner.start();
		}

		// A esperar
		for (Thread runner : runners) {
			runner.join();
		}

		// Aquí han llegado todos
		score.printResults();
	}

}
