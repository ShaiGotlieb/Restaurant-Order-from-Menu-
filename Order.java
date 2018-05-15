
import java.util.ArrayList;

//class Order represent the order of a user
public class Order {
	//Variable 
	private ArrayList<ProductOrder> products;

	//constructor
	public Order() {
		products = new ArrayList<>();
	}

	//method to add product to the arrayList of the final order
	public void addProduct(Product product, int quantity) {
		products.add(new ProductOrder(product, quantity));
	}

	// make the last bill of the order and return it to show in the dialog box
	@Override
	public String toString() {
		String reservationDetails = "";
		//price of order
		double total = 0;
		for (ProductOrder product : products) {
			reservationDetails += product.getQuantity() + " " + product.getDescription() + " = "
					+ product.getQuantity() * product.getPrice() + "\r\n";
			total += product.getQuantity() * product.getPrice();

		}

		reservationDetails += "total price: " + total;
		return reservationDetails;
	}
}//end of class Order