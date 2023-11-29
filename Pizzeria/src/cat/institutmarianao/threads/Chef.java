package cat.institutmarianao.threads;

import cat.institutmarianao.Pizzeria;
import cat.institutmarianao.monitor.OrderBoard;

public class Chef extends Thread {
	private OrderBoard board;

	public Chef(OrderBoard board) {
		this.board = board;
	}

	@Override
	public void run() {
		while (true) {
			// Recoger comanda
			String order = board.dequeueOrder();
			if (Pizzeria.END_OF_DUTY.equals(order)) {
				break;
			}
			// Preparar el plato
			prepareDish(order);
		}
		System.out.println("Chef finished");
	}

	private void prepareDish(String order) {
		System.out.println("Chef preparing " + order);
		int tPrepareDish = 8000;
		try {
			sleep(tPrepareDish);
		} catch (InterruptedException e) {
		}
		System.out.println("Chef finished preparing " + order);
	}
}
