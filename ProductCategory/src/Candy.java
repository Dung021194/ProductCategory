import java.io.Serializable;

public class Candy extends Product implements Serializable {
    private static final long serialVersionUID = -3326426625597282442L;
    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Candy(double weight) {
        this.weight = weight;
    }

    public Candy(int id, String name, double price, int quantity, Category category, double weight) {
        super(id, name, price, quantity, category);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Candy{" +super.toString()+
                "weight=" + weight +
                '}';
    }
}
