package dxf.entities;

import dxf.Entity;

public class Point extends Entity {
    public Point(String name, String layer) {
        super("POINT", layer);
    }

    public Point() {
    }
    private final String AcDbPoint = "AcDbPoint";

    public String getAcDbPoint() {
        return AcDbPoint;
    }
    private String pointX;
    private String pointY;
    private String pointZ;

    public String getPointX() {
        return pointX;
    }

    public void setPointX(String pointX) {
        this.pointX = pointX;
    }

    public String getPointY() {
        return pointY;
    }

    public void setPointY(String pointY) {
        this.pointY = pointY;
    }

    public String getPointZ() {
        return pointZ;
    }

    public void setPointZ(String pointZ) {
        this.pointZ = pointZ;
    }

    @Override
    public String toString() {
        return "Point{" +
                "pointX='" + pointX + '\'' +
                ", pointY='" + pointY + '\'' +
                ", pointZ='" + pointZ + '\'' +
                ", handle='" + handle + '\'' +
                ", layer='" + layer + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
