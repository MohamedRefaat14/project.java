package decorator;

public abstract class CourseDecorator implements Course {
    protected Course decoratedCourse;

    public CourseDecorator(Course decoratedCourse) {
        this.decoratedCourse = decoratedCourse;
    }

    @Override
    public String getDescription() {
        return decoratedCourse.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCourse.getCost();
    }
}
