package cat.institutmarianao;

import cat.institutmarianao.monitor.OrderBoard;
import cat.institutmarianao.threads.Chef;
import cat.institutmarianao.threads.Waiter;

public class Pizzeria {

	public static final String[] PIZZAS = { "Pepperonni", "Barbacoa", "4 Quesos", "Margueritta", "Fungi" };
	public static final String END_OF_DUTY = "";
	public static final int CLIENTES = 15;

	public static void main(String[] args) {
		OrderBoard board = new OrderBoard();

		Chef chef = new Chef(board);
		Waiter waiter = new Waiter(board, CLIENTES);

		chef.start();
		waiter.start();
	}

}
