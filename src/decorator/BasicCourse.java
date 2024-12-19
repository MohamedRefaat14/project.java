package decorator;

public class BasicCourse implements Course {
    private String description;
    private double cost;

    public BasicCourse(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getCost() {
        return cost;
    }
}
