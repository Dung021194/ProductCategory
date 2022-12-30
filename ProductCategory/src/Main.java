import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String pathProduct = "C:\\Users\\DUNGHUYEN\\Desktop\\New folder (6)\\ProductCategory\\src\\listProduct";
        String pathCategory = "C:\\Users\\DUNGHUYEN\\Desktop\\New folder (6)\\ProductCategory\\src\\categoryList";
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        productManager.categoryManager.categoryList =productManager.categoryManager.read(pathCategory);
        productManager.listProduct = productManager.read(pathProduct);


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
                        productManager.categoryManager.CategoryManagerMenu(scanner, productManager);
                        productManager.categoryManager.writeList(pathCategory,productManager.categoryManager.categoryList);

                        break;
                    case 2:
                        productManager.displayMenu(scanner);
                        break;
                    case 3:
                        productManager.save(productManager.create(scanner));
                        productManager.writeList(pathProduct,productManager.listProduct);
                        productManager.categoryManager.writeList(pathCategory,productManager.categoryManager.categoryList);
                        break;
                    case 4:
                        productManager.display(productManager.listProduct);
                        System.out.println("Enter ID of product you wanna delete:");
                        int delId = Integer.parseInt(scanner.nextLine());
                        productManager.deleteById(delId);
                        productManager.display(productManager.listProduct);
                        productManager.writeList(pathProduct,productManager.listProduct);
                        break;
                    case 5:
                        productManager.searchProductByName(scanner);
                        break;
                    case 6:
                        productManager.searchProductByPrice(scanner);

                        break;
                    case 7:
                        productManager.edit(scanner);
                        productManager.writeList(pathProduct,productManager.listProduct);
                        productManager.categoryManager.writeList(pathCategory,productManager.categoryManager.categoryList);

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