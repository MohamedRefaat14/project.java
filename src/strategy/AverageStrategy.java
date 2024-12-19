package strategy;

public class AverageStrategy implements EvaluationStrategy {
    @Override
    public double evaluate(double[] grades) {
        double sum = 0.0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.length;
    }
}
