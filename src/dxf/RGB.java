package dxf;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGB rgb = (RGB) o;
        return r == rgb.r &&
                g == rgb.g &&
                b == rgb.b;
    }
}
