public class Point {
    private int x;
    private int y;
    private int energy;
    private Double f;

    public Point(int x, int y, int energy){
        this.x = x;
        this.y = y;
        this.energy = energy;
    }

    public int getEnergy() {
        return this.energy;
    }

    public Double getF() {
        return this.f;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + ", energy=" + energy + ", f=" + f + "]";
    }
}
