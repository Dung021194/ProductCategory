import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.util.ArrayList;
import java.util.Scanner;

public class CategoryManager implements CRUD<Category> {
    private int index = 0;

    public ArrayList<Category> categoryList;

    public CategoryManager() {
        categoryList = new ArrayList<>();

    }


    @Override
    public Category create(Scanner scanner) {
        int id;
        if (categoryList.isEmpty()) {
            id = index + 1;
        } else {
            id = categoryList.size()+1 ;
        }
        System.out.println("Enter name of category " + (index+1));
        String name = scanner.nextLine();
        return new Category(id, name);
    }


    @Override
    public void save(Category category) {
        categoryList.add(category);
        System.out.println("Add success");
        index++;

    }

    @Override
    public void edit(Scanner scanner) {
        System.out.println("Enter ID of category you wanna edit: ");
        try {
            int editId = Integer.parseInt(scanner.nextLine());
            if (editId>0 && editId<=categoryList.size()) {
                for (Category c : categoryList) {
                    if (editId == c.getId()) {
                        System.out.println("Enter new name of category:");
                        String name = scanner.nextLine();
                        if (!name.equals("")) {
                            c.setName(name);
                        }
                    }
                }
            } else {
                System.out.println("Category not exist");
            }
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }

    }

    @Override
    public void deleteById(int delId) {
        for (Category c:categoryList){
            if (delId== c.getId()){
                int i = categoryList.indexOf(c);
                categoryList.remove(i);
                break;
            }
        }
        System.out.println("Delete success");
    }

    @Override

    public Category findById(int searchId) {
        if (searchId > 0 && searchId <= categoryList.size()) {
            for (Category c : categoryList) {
                if (c.getId() == searchId) {
                    return c;
                }
            }
        }
        return null ;
    }
    public Category choiceCategory(Scanner scanner){
        int choiceId = Integer.parseInt(scanner.nextLine());
        Category choiceCategoryId =null;
        if (choiceId==0){
            choiceCategoryId=this.create(scanner);
            save(choiceCategoryId);
        } else if (choiceId>0 && choiceId <=categoryList.size()){
            choiceCategoryId=findById(choiceId);
        }else {
            System.out.println("Out of category list");
            System.out.println("Please create new category");
            choiceCategoryId = this.create(scanner);
            save(choiceCategoryId);
        }
        return choiceCategoryId;

    }

    @Override
    public void display(ArrayList<Category> categoryList) {
        for (Category c : categoryList){
            System.out.println(c);
        }
    }


    public void CategoryManagerMenu(int choice, Scanner scanner, ProductManager productManager){

        do {
            System.out.println("Choose number:");
            System.out.println("1. Add a new category:");
            System.out.println("2. Delete category by ID:");
            System.out.println("3. Display all category:");
            System.out.println("4. Edit category by ID");
            System.out.println("0. Exit");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    this.save(create(scanner));
                    break;
                case 2:
                    System.out.println("Enter ID you wanna delete:");
                    int delId = Integer.parseInt(scanner.nextLine());
                    deleteByCategoryName(this.findById(delId).getName(), productManager);
                    this.deleteById(delId);
                    break;
                case 3:
                    this.display(categoryList);
                    break;
                case 4:
                    this.edit(scanner);
                    break;
            }
        }while (choice!=0);
    }

    public void deleteByCategoryName(String categoryName, ProductManager productManager) {
        if (!productManager.listProduct.isEmpty()) {
            ArrayList<Integer> delList = new ArrayList<>();
            for (Product p : productManager.listProduct) {
                if (p.getCategory().getName().equalsIgnoreCase(categoryName)) {
                    delList.add(p.getId());
                }
            }
            for (int i=0; i< delList.size();i++) {
                int c=delList.get(i);
                productManager.deleteById(c);
            }
        }
    }

}
