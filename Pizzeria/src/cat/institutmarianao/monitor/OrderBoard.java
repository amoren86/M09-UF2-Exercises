package cat.institutmarianao.monitor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OrderBoard {

	private static final int MAX_ORDERS = 5;
	private Queue<String> orders; // Shared memory

	public OrderBoard() {
		orders = new LinkedList<>();
	}

	public synchronized String dequeueOrder() {
		// Esperar a que lleguen comandas, si no hay
		waitNotEmpty();

		String order = orders.poll();
		System.out.println("Orders \"" + order + "\" dequeued. Queue contains " + Arrays.toString(orders.toArray()));
		notify();
		return order;
	}

	public synchronized void enqueueOrder(String order) {
		waitNotFull();
		orders.offer(order);
		System.out.println("Orders \"" + order + "\" enqueued. Queue contains " + Arrays.toString(orders.toArray()));

		// Notificar a la cocina
		notify();
	}

	private void waitNotFull() {
		try {
			while (isFull()) {
				System.out.println("Waiter waiting... ");
				wait();
			}
		} catch (InterruptedException e) {
		}
	}

	private void waitNotEmpty() {
		try {
			while (!hasOrders()) {
				System.out.println("Chef waiting for orders... ");
				wait();
			}
		} catch (InterruptedException e) {
		}
	}

	private boolean hasOrders() {
		return !orders.isEmpty();
	}

	private boolean isFull() {
		return orders.size() == MAX_ORDERS;
	}
}