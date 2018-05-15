
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//Main class
public class Main {
	//path of location of file to read and write
	private static String path = "C:/Users/Shai Gotlieb/Documents/";

	//Initialize program with the objects Menu and MenuView
	public static void main(String[] args) {
		ArrayList<Product> products = readProductsList(path + "menu.txt");
		Menu menu = new Menu(products);
		MenuView menuView = new MenuView(menu);
		
	}//end of main

	//method to read the menu from file and creates new objects of product depends on what items in menu
	private static ArrayList<Product> readProductsList(String filePath) {
		ArrayList<Product> products = new ArrayList<>();
		int rowIndex = 0;
		File f = new File(filePath);//open file
		Scanner r = null;
		try {
			String description = null;
			ProductCategory category = null;
			double price = 0;
			r = new Scanner(f);
			String line;
			while (r.hasNextLine()) {
				line = r.nextLine();
				if (line.length() == 0) {
					continue;
				}
				//first row represent the description of 
				//second row in menu is the category of product
				//third line is the price
				switch (rowIndex++ % 3) {
				case 0:
					description = line;
					break;
				case 1:
					category = Enum.valueOf(ProductCategory.class, line);
					break;
				case 2:
					price = Double.parseDouble(line);
					products.add(new Product(description, category, price));
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (r != null)
				r.close();//close file
		}
		
		return products;
	}
}//end of class Main