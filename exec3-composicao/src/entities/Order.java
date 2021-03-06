package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enums.OrderStatus;

public class Order {
	private Date moment;
	private OrderStatus status;
	private Client client;
	private List<OrderItem> orders = new ArrayList<>();
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	
	public Order() {}

	public Order(Date moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<OrderItem> getOrders() {
		return orders;
	}

	public void addItem(OrderItem item) {
		orders.add(item);
	}

	public void removeItem(OrderItem item) {
		orders.remove(item);
	}
	
	public double total() {
		double sum = 0.0;
		
		for (OrderItem order : orders) {
			sum += order.subTotal();
		}
		
		return sum;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("ORDER SUMMARY:" + "\n");
		sb.append("Order moment: ");
		sb.append(sdf.format(moment) + "\n");
		sb.append("Order status: ");
		sb.append(status + "\n");
		sb.append("Client: ");
		sb.append(client.getName() + " ");
		sb.append("("+sdf2.format(client.getBirthDate()) + ")");
		sb.append(" - ");
		sb.append(client.getEmail() + "\n");
		sb.append("Order items:" + "\n");
		
		for (OrderItem item : orders) {
			sb.append(item.getProduct().getName() + ", ");
			sb.append(item.getPrice() + ", ");
			sb.append("Quantity: ");
			sb.append(item.getQuantity() + ", ");
			sb.append("Subtotal: $");
			sb.append(String.format("%.2f%n", item.subTotal()));
		}
		
		sb.append("Total price: ");
		sb.append(String.format("%.2f", total()));
		
		return sb.toString();
	}
}
