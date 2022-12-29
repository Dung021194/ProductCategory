import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();

        do {
            System.out.println("MENU");
            System.out.println("1. Category manager");
            System.out.println("2. Display products menu");
            System.out.println("3. Create new product");
            System.out.println("4. Delete product by ID");
            System.out.println("5. Search product by name");
            System.out.println("6. Search product by price");
            System.out.println("7. Edit product by ID");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productManager.categoryManager.CategoryManagerMenu(choice,scanner, productManager);
                        break;
                    case 2:
                        productManager.displayMenu(choice,scanner);
                        break;
                    case 3:
                        productManager.save(productManager.create(scanner));
                        break;
                    case 4:
                        System.out.println("Enter ID of product you wanna delete:");
                        int delId = Integer.parseInt(scanner.nextLine());
                        productManager.deleteById(delId);
                        productManager.display(productManager.listProduct);
                        break;
                    case 5:
                        productManager.searchProductByName(scanner);
                        break;
                    case 6:
                        productManager.searchProductByPrice(scanner);

                        break;
                    case 7:
                        productManager.edit(scanner);
                        break;
                    case 0:
                        System.exit(0);
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter choice again");
            }
        } while (true);
    }


}