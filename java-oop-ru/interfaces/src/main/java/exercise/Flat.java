package exercise;

// BEGIN
public final class Flat implements Home {

    private final double area;
    private final double balconyArea;
    private final int floor;

    public Flat(double areaVal, double balconyAreaVal, int floorNum) {
        this.area = areaVal;
        this.balconyArea = balconyAreaVal;
        this.floor = floorNum;
    }

    @Override
    public double getArea() {
        return area + balconyArea;
    }

    @Override
    public String toString() {
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }
}
// END
