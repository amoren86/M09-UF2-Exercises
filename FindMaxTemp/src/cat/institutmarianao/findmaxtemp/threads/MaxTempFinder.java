package cat.institutmarianao.findmaxtemp.threads;

import cat.institutmarianao.findmaxtemp.monitors.MaxTemp;

public class MaxTempFinder extends Thread {
	private MaxTemp maxTemp;
	private int[] temperatures;
	private int from;
	private int to;
	private int max = Integer.MIN_VALUE;

	public MaxTempFinder(MaxTemp maxTemp, int[] temperatures, int from, int to) {
		this.maxTemp = maxTemp;
		this.temperatures = temperatures;
		this.from = from;
		this.to = to;
	}

	@Override
	public void run() {
		for (int i = from; i < to; i++) {
			max = Math.max(max, temperatures[i]);
		}
		System.out.println("Finder (" + from + "," + (to - 1) + ") found max=" + max);
		maxTemp.setMaxTemp(max);
	}
}
