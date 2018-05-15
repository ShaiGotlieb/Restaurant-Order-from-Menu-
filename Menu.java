import java.util.ArrayList;

//class Menu represent the menu of the restaurant
public class Menu {
	//variable ArrayList of products
	private ArrayList<Product> products;

	//constructor add products to the ArrayList after creating them
	public Menu(ArrayList<Product> products) {
		this.products = new ArrayList<>();
		for (Product product : products) {
			this.products.add(new Product(product));
		}
	}

	//method that return the number of products in menu
	public int numOfProducts() {
		return products.size();
	}

	//method loop for the arrayList and creates new arrayLists of a specific category
	public ArrayList<Product> getProductsForCategory(ProductCategory category) {
		ArrayList<Product> categoryProducts = new ArrayList<>();
//foreach product in the ArrayList
		for (Product product : products) {
			//if the category of the product equals to the category given as a parameter - add product to the specific ArrayList
			if (product.getCategory() == category) {
				categoryProducts.add(product);
			}
		}

		return categoryProducts;
	}
}