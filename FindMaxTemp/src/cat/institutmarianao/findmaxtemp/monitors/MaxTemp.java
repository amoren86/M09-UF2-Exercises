package cat.institutmarianao.findmaxtemp.monitors;

public class MaxTemp {
	private int maxTemp = Integer.MIN_VALUE; // Shared value

	public synchronized int getMaxTemp() {
		return maxTemp;
	}

	public synchronized void setMaxTemp(int maxTemp) {
		if (this.maxTemp < maxTemp) {
			this.maxTemp = maxTemp;
		}
	}
}
