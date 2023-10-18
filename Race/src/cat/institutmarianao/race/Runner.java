package cat.institutmarianao.race;

public class Runner implements Runnable {
	private static final int MIN_SPEED = 5;
	private static final int MAX_SPEED = 9;

	private String name;
	private int speed;
	private int time;
	
	private Score score;


	public Runner(String name, Score score) {
		this.name = name;
		this.score = score;
		speed = (int) (Math.random() * (MAX_SPEED - MIN_SPEED) + MIN_SPEED);
	}

	@Override
	public void run() {
		try {
			// Correr
			time=Race.DISTANCE/speed;
			Thread.sleep(time);

			// Anotar registro
			score.addRunner(this);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public String toString() {
		return "Runner [name=" + name + ", speed=" + speed + ", time=" + time  + "]";
	}
}
