//class ProductOrder represent the order of a product
public class ProductOrder extends Product {
//variable represent number of items after selected by user
	private int quantity;

	//constructor that inheritance from class Product and add the quantity that was given as a parameter when creating this object
	public ProductOrder(String description, ProductCategory category, double price, int quantity) {
		super(description, category, price);
		this.quantity = quantity;
	}

	//constructor that call the other constructor (because here we got product and number of items) - almost like copy constructor
	public ProductOrder(Product product, int quantity) {
		this(product.getDescription(), product.getCategory(), product.getPrice(), quantity);
	}
	
	public int getQuantity() {
		return quantity;
	}
}//end class ProductOrder