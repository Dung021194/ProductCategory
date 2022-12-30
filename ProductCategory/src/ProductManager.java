import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager implements CRUD<Product> {
    private int index = 0;
    ArrayList<Product> listProduct;
    CategoryManager categoryManager;

    public ProductManager() {
        listProduct = new ArrayList<>();
        this.categoryManager = new CategoryManager();
    }

    public Product create(Scanner scanner) {
        boolean flag = true;
        int id;
        if (listProduct.isEmpty()) {
            id = index + 1;
        } else {
            id = listProduct.size() + 1;
        }
        System.out.println("Enter name of product "+(index+1));
        String name = scanner.nextLine();
        System.out.println("Enter price of product "+(index+1));
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter quantity of product "+(index+1));
        int quantity = 0;
        do {
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                flag = false;
            } catch (NumberFormatException e) {
                System.out.println("Enter again!");
            }

        }
        while (flag);
        categoryManager.display(categoryManager.categoryList);
        System.out.println("Choose category of product " +(index+1)+
                "(Press 0 to create new category)");
        Category category = categoryManager.choiceCategory(scanner);
        System.out.println("Choose type of product "+(index+1));
        System.out.println("1. Drink product:");
        System.out.println("2. Candy product:");
        System.out.println("Other number: Normal product");
        int choice = -1;
        do {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                flag = true;
            } catch (NumberFormatException e) {
                System.out.println("Enter again");
            }

        } while (!flag);

        Product choiceProduct;
        switch (choice) {
            case 1:
                System.out.println("Enter volume of product "+(index+1));
                double volume = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter bottleType of product "+(index+1));
                String bottleType = scanner.nextLine();
                choiceProduct = new Drinks(id, name, price, quantity, category, volume, bottleType);
                break;
            case 2:
                System.out.println("Enter weight of product "+(index+1));
                double weight = Double.parseDouble(scanner.nextLine());
                choiceProduct = new Candy(id, name, price, quantity, category, weight);
                break;
            default:
                choiceProduct = new Product(id, name, price, quantity, category);
                break;

        }
        return choiceProduct;
    }

    public void save(Product product) {
        listProduct.add(product);
        System.out.println("Add success");
        index++;
    }

    @Override
    public void edit(Scanner scanner) {
        System.out.println("Enter ID of product you wanna edit");
        int editId = Integer.parseInt(scanner.nextLine());
        Product product = this.findById(editId);
        if (product != null) {
            System.out.println("Enter new product name: ");
            String name = scanner.nextLine();
            if (!name.equals("")) {
                product.setName(name);
            }

            System.out.println("Enter new price of product: ");
            String price = scanner.nextLine();
            if (!price.equals("")) {
                product.setPrice(Double.parseDouble(price));
            }

            System.out.println("Enter new quantity of product:");
            String quantity = scanner.nextLine();
            if (!quantity.equals("")) {
                product.setQuantity(Integer.parseInt(quantity));
            }
            System.out.println("Press Y to change category of product, press any key to skip:");
            String chooseCategory = scanner.nextLine();
            if (chooseCategory.equalsIgnoreCase("y"))
            {
                categoryManager.display(categoryManager.categoryList);
                System.out.println("Choose category of product: " +
                        "(Press 0 to create new category)");
                Category category = categoryManager.choiceCategory(scanner);
                product.setCategory(category);
            }
            if (product instanceof Drinks){
                System.out.println("Enter new volume of product:");
                String volume = scanner.nextLine();
                if (!volume.equals("")) {
                    ((Drinks) product).setVolume(Double.parseDouble(volume));
                }
                System.out.println("Enter new bottle type of product:");
                String bottleType = scanner.nextLine();
                if (!bottleType.equals("")) {
                    ((Drinks) product).setBottleType(bottleType);
                }
            }else if (product instanceof Candy){
                System.out.println("Enter new weight of product:");
                String weight = scanner.nextLine();
                if (!weight.equals("")){
                    ((Candy) product).setWeight(Double.parseDouble(weight));
                }
            }
        }
        else
        {
            System.out.println("Not exist product have this id!");
        }
    }


    @Override
    public void deleteById(int delId) {
        for (Product p:listProduct){
            if (delId== p.getId()){
                listProduct.remove(p);
                break;
            }
        }
        System.out.println("Delete success");

    }

    @Override
    public Product findById(int searchId) {
        Product searchProduct = null;
        if (searchId > 0 && searchId <= listProduct.size()) {
            for (Product p : listProduct) {
                if (p.getId() == searchId) {
                    searchProduct = p;
                    break;
                }
            }
        }
        return searchProduct != null ? searchProduct : this.findById(searchId);
    }

    @Override
    public void display(ArrayList<Product> listProduct) {
        if (!listProduct.isEmpty()){
        for (Product p: listProduct){
            System.out.println(p);
            }
        }
        else {
            System.out.println("No product");
    }
    }
    public void displayDrinksProduct(){
        for (Product p : listProduct){
            if (p instanceof Drinks){
                System.out.println(p);
            }
        }
    }
    public void displayCandyProduct() {
        if (!listProduct.isEmpty()) {
            for (Product p : listProduct) {
                if (p instanceof Candy) {
                    System.out.println(p);
                }
            }
        }else {
            System.out.println("No product ");
        }
    }
    public void displayMinPriceProduct(){
        if (!listProduct.isEmpty()) {
            double minPrice = listProduct.get(0).getPrice();
            for (Product p:listProduct){
                if (p.getPrice()<minPrice){
                    minPrice=p.getPrice();
                }
            }
            for (Product s: listProduct){
                if (s.getPrice()==minPrice){
                    System.out.println(s);
                }
            }
        }
    }
    public void displayMaxPriceProduct(){
        if (!listProduct.isEmpty()) {
            double maxPrice = listProduct.get(0).getPrice();
            for (Product p:listProduct){
                if (p.getPrice()>maxPrice){
                    maxPrice=p.getPrice();
                }
            }
            for (Product s: listProduct){
                if (s.getPrice()==maxPrice){
                    System.out.println(s);
                }
            }
        }
        else {
            System.out.println("No product");
        }
    }
    public void displayMaxQuantity(){
        if (!listProduct.isEmpty()){
            int maxQuantity = listProduct.get(0).getQuantity();
            for (Product p : listProduct){
                if (p.getQuantity()>maxQuantity){
                    maxQuantity = p.getQuantity();
                }
            }
            for (Product s : listProduct){
                if (s.getQuantity() == maxQuantity){
                    System.out.println(s);
                }
            }
        }else {
            System.out.println("No product");
        }
    }
    public void displayMinQuantity(){
        if (!listProduct.isEmpty()){
            int minQuantity = listProduct.get(0).getQuantity();
            for (Product p : listProduct){
                if (p.getQuantity()<minQuantity){
                    minQuantity = p.getQuantity();
                }
            }
            for (Product s : listProduct){
                if (s.getQuantity() == minQuantity){
                    System.out.println(s);
                }
            }
        }else {
            System.out.println("No product");
        }
    }

    public void searchProductByName(Scanner scanner){
        System.out.println("Enter name of product");
        String searchName = scanner.nextLine();
        for (Product p : listProduct){
            if (p.getName().toLowerCase().contains(searchName.toLowerCase())){
                System.out.println(p);
            }
        }
    }
    public void searchProductByPrice(Scanner scanner){
        boolean flag = true;
        double startPrice = 0;
        double endPrice = 0;
        do {
            try {
                System.out.println("Enter start price");
                startPrice = Double.parseDouble(scanner.nextLine());
                System.out.println("Enter end price");
                endPrice = Double.parseDouble(scanner.nextLine());
                flag=false;
            } catch (NumberFormatException e) {
                System.out.println("Enter again");
            }
        }while (flag);
        for (Product p : listProduct){
            if (p.getPrice() >= startPrice && p.getPrice() <= endPrice ){
                System.out.println(p);
            }
        }
    }
    public void displayTypeProduct(Scanner scanner){
        System.out.println("Enter ID of category you wanna display");
        int choiceCategoryId = Integer.parseInt(scanner.nextLine());
        if (choiceCategoryId>0 &&choiceCategoryId <= categoryManager.categoryList.size()) {
            Category choiceCategory = categoryManager.findById(choiceCategoryId);
            for (Product p : listProduct) {
                if (p.getCategory().getName().equals(choiceCategory.getName())) {
                    System.out.println(p);
                }
            }
        }else {
            System.out.println("Out of category list");
        }
    }
    public void displayMenu(Scanner scanner){
        int choice;
        do {
            System.out.println("PRODUCT DISPLAY MENU");
            System.out.println("1. Display all product:");
            System.out.println("2. Display Candy product:");
            System.out.println("3. Display Drinks product:");
            System.out.println("4. Display min price product");
            System.out.println("5. Display max price product");
            System.out.println("6. Display min quantity product");
            System.out.println("7. Display max quantity product");
            System.out.println("8. Display type product");
            System.out.println("0. Exit");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    display(listProduct);
                    break;
                case 2:
                    displayCandyProduct();
                    break;
                case 3:
                    displayDrinksProduct();
                    break;
                case 4:
                    displayMinPriceProduct();
                    break;
                case 5:
                    displayMaxPriceProduct();
                    break;
                case 6:
                    displayMinQuantity();
                    break;
                case 7:
                    displayMaxQuantity();
                    break;
                case 8:
                    categoryManager.display(categoryManager.categoryList);
                    displayTypeProduct(scanner);
            }
        }while (choice !=0);
    }
    public void writeList(String pathProduct,ArrayList<Product> listProduct){
        try{
            FileOutputStream fos = new FileOutputStream(pathProduct);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fos);
            objectOutputStream.writeObject(listProduct);
            objectOutputStream.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Product> read(String pathProduct){
        try {
            File file = new File(pathProduct);
            if (!file.exists()){
                file.createNewFile();
            }
            FileInputStream fileInputStream = new FileInputStream(pathProduct);
            if (fileInputStream.available()>0){
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                listProduct = (ArrayList<Product>) objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.getStackTrace();
        }
        return listProduct;
    }
}
