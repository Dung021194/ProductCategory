import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public interface CRUD <E>{
    E create(Scanner var);

    void save(E var);

    void edit(Scanner var);

    void deleteById(int var);

    E findById(int var);

    void display(ArrayList<E> var);
}

