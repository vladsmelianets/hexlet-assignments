package exercise;

// BEGIN
public final class Cottage implements Home {

    private final double area;
    private final int floorCount;

    public Cottage(double areaVal, int floorVal) {
        this.area = areaVal;
        this.floorCount = floorVal;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return floorCount + " этажный коттедж площадью " + getArea() + " метров";
    }
}
// END
