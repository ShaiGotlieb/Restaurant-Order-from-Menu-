import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * Author: Shai Gotlieb
 * Date: 11/5/2018
 */
//This class represent the frame of the application (UI)
public class MenuView extends JFrame {
	//ArrayList to hold user product includes components: CheckBox and ComboBox
	private ArrayList<ProductUI> productsUI;
	//path of file to read the menu
	private String path = "C:/Users/Shai Gotlieb/Documents/";
	
//constructor to initialize layout,size of frame and arrayList - the constructor calls other method that make the panel
	public MenuView(Menu menu) {
		setLayout(new BorderLayout());
		setSize(300, 700);
		productsUI = new ArrayList<>(menu.numOfProducts());
		initLowerPanel();
		setCenterLayout(menu);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
	}

	//method to make the center panel
	private void setCenterLayout(Menu menu) {
		//create panel
		JPanel centerPanel = new JPanel();
		
		//using grid layout for showing each section (category) of the menu
		centerPanel.setLayout(new GridLayout(4, 1));
		
		//calling methods to make panels of product category and add them to the center panel
		centerPanel.add(generateCategoryPanel(menu.getProductsForCategory(ProductCategory.Starter), ProductCategory.Starter));
		centerPanel.add(generateCategoryPanel(menu.getProductsForCategory(ProductCategory.Main), ProductCategory.Main));
		centerPanel.add(generateCategoryPanel(menu.getProductsForCategory(ProductCategory.Desert), ProductCategory.Desert));
		centerPanel.add(generateCategoryPanel(menu.getProductsForCategory(ProductCategory.Drink), ProductCategory.Drink));
		
		//add panel to the frame
		add(centerPanel);
	}
	

   //method that return panel for each category - the method get the ArrayList of products and the right category and create the panels
	private JPanel generateCategoryPanel(ArrayList<Product> products, ProductCategory category) {
		JPanel categoryPanel = new JPanel();
		
		//set and add the layout as grid for number of products in the specific category (+ 1 is for the label)
		categoryPanel.setLayout(new GridLayout(products.size() + 1, 1));
		
		//add the label with the name of the category that given as a parameter 
		categoryPanel.add(new JLabel(category.toString() + ":"));
		
		//foreach loop: add the panels to the right category
		for (Product product : products) {
			categoryPanel.add(generateProductPanel(product));
		}

		return categoryPanel;
	}

	//method the create panel with the right layout for every category with the same components
	private JPanel generateProductPanel(Product product) {
		JPanel productPanel = new JPanel();
		
		//the components will be as flow - checkBox , product description, price , comboBox
		productPanel.setLayout(new FlowLayout());
		
		//create the checkBox and label
		JCheckBox checkBox = new JCheckBox(product.getDescription());
		JLabel priceLabel = new JLabel("" + product.getPrice());
		
		//comboBox with numbers that represent the number of items the user want from the same product
		JComboBox<Integer> quantityOfItem = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		
		//add components to the panel
		productPanel.add(checkBox);
		productPanel.add(priceLabel);
		productPanel.add(quantityOfItem);
		
		//add to the interface the components
		productsUI.add(new ProductUI(product, checkBox, quantityOfItem));
		
		return productPanel;
	}

	//method to create the lower panel (in south) with order button
	private void initLowerPanel() {
		JButton orderButton = new JButton("Order");
		this.add(orderButton, BorderLayout.SOUTH);
		
		//listener for the order button - anonymous class
		orderButton.addActionListener(new ActionListener() {

			//method that will happen when we click the order button
			@Override
			public void actionPerformed(ActionEvent e) {
				//the text that will appear in buttons in the dialog box
				String[] buttonOptions = { "Confirm", "Edit", "Cancel" };
				//create dialog box
				int userChoice = JOptionPane.showOptionDialog(null, generateOrder().toString(), "Your Order is:",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, buttonOptions, buttonOptions[0]);
				//switch for the selected button that user chose - no need for case 1 because in default it will close the dialog and go back to the program
				switch (userChoice) {
				case 0:
					showConfirmationScreen();
				case 2:
					resetMainScreen();
					break;
				}
			}
		});
	}

	//method to reset all checkboxs and comboBoxs
	protected void resetMainScreen() {
		for (ProductUI productUI : productsUI) {
			productUI.getCheckBox().setSelected(false);
			productUI.getComboBox().setSelectedIndex(0);
		}
	}

	protected void showConfirmationScreen() {
		String userDetails = JOptionPane.showInputDialog("Please enter name and ID:");
		saveIntoFile(userDetails + ".txt");
	}

	//save details of users order to a file
	private void saveIntoFile(String fileName) {
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			String fullPath = path  + fileName;
			fw = new FileWriter(fullPath);
			bw = new BufferedWriter(fw);
			bw.write(generateOrder().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

		}
	}
	
	//creating an order after 'order' button was pressed
	protected Order generateOrder() {
		Order order = new Order();
		
		//foreach all the products - check what product checked and how many items selected by user and add them to the final arrayList
		for (ProductUI productUI : productsUI) {
			//only if product was selected - we check how many times
			if (productUI.getCheckBox().isSelected()) {
				//we cast to integer because we know that the comboBox hold only Integers - that represent the number of items
				order.addProduct(productUI.getProduct(), (Integer)productUI.getComboBox().getSelectedItem());
			}
		}

		return order;
	}
}//end of class MenuView