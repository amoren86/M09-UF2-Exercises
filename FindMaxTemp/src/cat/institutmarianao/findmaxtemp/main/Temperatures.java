package cat.institutmarianao.findmaxtemp.main;

public class Temperatures {

	private static final int SPRING = 1;
	private static final int SPRING_START = 82;
	private static final int SPRING_END = 173;
	private static final int SPRING_MIN = 10;
	private static final int SPRING_MAX = 25;

	private static final int SUMMER = 2;
	private static final int SUMMER_START = 174;
	private static final int SUMMER_END = 264;
	private static final int SUMMER_MIN = 20;
	private static final int SUMMER_MAX = 40;

	private static final int FALL = 3;
	private static final int FALL_START = 265;
	private static final int FALL_END = 356;
	private static final int FALL_MIN = 10;
	private static final int FALL_MAX = 25;

	private static final int WINTER = 4;
	private static final int WINTER_START = 356;
	private static final int WINTER_END = 81;
	private static final int WINTER_MIN = -5;
	private static final int WINTER_MAX = 15;

	public static int[] generateRandomTemperatures(int maxTemperatures) {
		int[] temperatures = new int[maxTemperatures];

		for (int i = 0; i < temperatures.length; i++) {
			int dayOfYear = (i + 1) % 365;

			switch (season(dayOfYear)) {
			case SPRING:
				temperatures[i] = getRandomInt(SPRING_MIN, SPRING_MAX);
				break;
			case SUMMER:
				temperatures[i] = getRandomInt(SUMMER_MIN, SUMMER_MAX);
				break;
			case FALL:
				temperatures[i] = getRandomInt(FALL_MIN, FALL_MAX);
				break;
			case WINTER:
				temperatures[i] = getRandomInt(WINTER_MIN, WINTER_MAX);
				break;
			default:
				throw new RuntimeException("Can not classify the day of the year in any season: " + dayOfYear);
			}
//			System.out.println("i = " + i + ", dayOfYear=" + dayOfYear + ", season=" + season(dayOfYear) + ", temp="
//					+ temperatures[i]);
		}
		return temperatures;
	}

	private static int season(int dayOfYear) {
		if (dayOfYear >= SPRING_START && dayOfYear <= SPRING_END) {
			return SPRING;
		}
		if (dayOfYear >= SUMMER_START && dayOfYear <= SUMMER_END) {
			return SUMMER;
		}
		if (dayOfYear >= FALL_START && dayOfYear <= FALL_END) {
			return FALL;
		}
		if (dayOfYear >= WINTER_START || dayOfYear <= WINTER_END) {
			return WINTER;
		}
		return 0;
	}

	private static int getRandomInt(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}
}
