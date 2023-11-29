package cat.institutmarianao.threads;

import cat.institutmarianao.Pizzeria;
import cat.institutmarianao.monitor.OrderBoard;

public class Waiter extends Thread {

	private OrderBoard board;
	private int clientes;

	public Waiter(OrderBoard board, int clientes) {
		this.board = board;
		this.clientes = clientes;
	}

	@Override
	public void run() {
		while (clientes > 0) {
			// Esperar un cliente
			waitClient();

			// Tomar nota (generar comanda)
			String order = getOrder();

			// Poner la comanda en el panel
			board.enqueueOrder(order);
			clientes--;
		}
		board.enqueueOrder(Pizzeria.END_OF_DUTY);
		System.out.println("Waiter finished");
	}

	private void waitClient() {
		// TODO Generar un tiempo aleatorio aquí
		int tWaitClient = 1000;
		try {
			sleep(tWaitClient);
		} catch (InterruptedException e) {
		}
		System.out.println("New customer is comming... ");
	}

	private String getOrder() {
		System.out.println("Waiter getting order... ");

		int tGetOrder = 2000;
		try {
			sleep(tGetOrder);
		} catch (InterruptedException e) {
		}
		int pizza = (int) (Math.random() * Pizzeria.PIZZAS.length);
		System.out.println("Waiter gets order " + Pizzeria.PIZZAS[pizza]);

		return Pizzeria.PIZZAS[pizza];
	}
}
