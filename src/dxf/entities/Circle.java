package dxf.entities;

import dxf.Entity;

public class Circle extends Entity {
    public Circle(){}

    public Circle(String name, String layer) {
        super("CIRCLE", layer);
    }
    private final String AcDbCircle = "AcDbCircle";

    public String getAcDbCircle() {
        return AcDbCircle;
    }

    private String centerX; //10
    private String centerY; //20
    private String centerZ; //30
    private String radius;

    @Override
    public String toString() {
        return "Circle{" +
                "centerX='" + centerX + '\'' +
                ", centerY='" + centerY + '\'' +
                ", centerZ='" + centerZ + '\'' +
                ", radius='" + radius + '\'' +
                ", handle='" + handle + '\'' +
                ", layer='" + layer + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getCenterX() {
        return centerX;
    }

    public void setCenterX(String centerX) {
        this.centerX = centerX;
    }

    public String getCenterY() {
        return centerY;
    }

    public void setCenterY(String centerY) {
        this.centerY = centerY;
    }

    public String getCenterZ() {
        return centerZ;
    }

    public void setCenterZ(String centerZ) {
        this.centerZ = centerZ;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
