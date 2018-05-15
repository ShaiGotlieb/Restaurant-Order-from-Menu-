//class of product object that represent a product in the menu
public class Product {
	//Variables of products 
	private String description;
	private ProductCategory category;
	private double price;
	
	//constructor that get the variables as parameters and init them
	public Product(String description, ProductCategory category, double price) {
		this.description = description;
		this.category = category;
		this.price = price;
	}

	//constructor that get a product and "parse" it to the right cariables 
	public Product(Product product) {
		this.category = product.category;
		this.description = product.description;
		this.price = product.price;
	}
	
	//getters and setters

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}//end of class Product