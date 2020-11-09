package dxf;

public class RGB{
    public int r; //red
    public int g; //green
    public int b; //blue

    public RGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public String toString() {
        return "[" + r +"," + g +"," + b +"]";
    }
}
