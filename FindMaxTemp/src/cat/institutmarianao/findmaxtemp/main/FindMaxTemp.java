package cat.institutmarianao.findmaxtemp.main;

import cat.institutmarianao.findmaxtemp.monitors.MaxTemp;
import cat.institutmarianao.findmaxtemp.threads.MaxTempFinder;

public class FindMaxTemp {
	private static final int MAX_TEMPERATURES = 3650;
	private static final int MAX_THREADS = 365;
	private static final int INTERVAL = MAX_TEMPERATURES / MAX_THREADS;
	private static final int REST = MAX_TEMPERATURES % MAX_THREADS;

	public static void main(String[] args) throws InterruptedException {
		MaxTemp maxTemp = new MaxTemp(); // Shared mem
		Thread[] finders = new MaxTempFinder[MAX_THREADS]; // Threads
		int[] temperatures = Temperatures.generateRandomTemperatures(MAX_TEMPERATURES); // Data

		// XXX Trick to test if its well done
		// temperatures[MAX_TEMPERATURES / 2] = 48;

		// Create and start the threads
		for (int i = 0, rest = REST; i < finders.length; i++, rest--) {
			int from = calculateFrom(i, rest);
			int to = calculateTo(i, rest);
			finders[i] = new MaxTempFinder(maxTemp, temperatures, from, to);
			finders[i].start();
		}

		// Join threads
		for (Thread finder : finders) {
			finder.join();
		}

		System.out.println("Max temp found is " + maxTemp.getMaxTemp());

	}

	//@formatter:off
	/*
	 * Suppose MAX_TEMPERATURES = 14, MAX_THREADS = 4:
	 * INTERVAL=3, REST=2 (INTERVAL*MAX_THREADS+REST=MAX_TEMPERATURES-->3*4+2=14)
	 *
	 * THREAD-0 --> from 0 to 3 (3 of the interval +1 for reduce rest)
	 * THREAD-1 --> from 4 to 7 (3 of the interval +1 for reduce rest)
	 * THREAD-2 --> from 8 to 10 (3 of the interval)
	 * THREAD-3 --> from 11 to 13 (3 of the interval)
	 */
	//@formatter:on
	private static int calculateFrom(int i, int rest) {
		if (rest > 0) {
			return i * (INTERVAL + 1);
		}
		return i * INTERVAL + REST;
	}

	private static int calculateTo(int i, int rest) {
		if (rest > 0) {
			return (i + 1) * (INTERVAL + 1);
		}
		return (i + 1) * INTERVAL + REST;
	}

}
