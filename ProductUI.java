
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

//class ProductUI hold product, checkBox if chosen and comboBox represent the number of items was chosen
public class ProductUI {
	//variables for productUI
	private Product product;
	private JCheckBox checkBox;
	private JComboBox<Integer> comboBox;

	//constructor
	public ProductUI(Product product, JCheckBox checkBox, JComboBox<Integer> comboBox) {
		this.product = product;
		this.checkBox = checkBox;
		this.comboBox = comboBox;
	}

	//getters and setters 
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public JCheckBox getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(JCheckBox checkBox) {
		this.checkBox = checkBox;
	}

	public JComboBox<Integer> getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox<Integer> comboBox) {
		this.comboBox = comboBox;
	}
}//end of class ProductUI