package dxfRead.entities;

import dxfRead.Entity;

public class Ellipse extends Entity {
    public Ellipse(String name, String layer) {
        super("ELLIPSE", layer);
    }

    public Ellipse() {
    }
    private final String AcDbEllipse = "AcDbEllipse";
    private String centerX; //10
    private String centerY; //20
    private String centerZ; //30
    private String LongX; //11
    private String LongY; //21
    private String LongZ; //31
    private String scale; //40
    private String start = "0.0"; //41
    private String point = Double.toString(Math.PI * 2); // 42

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

    public String getLongX() {
        return LongX;
    }

    public void setLongX(String longX) {
        LongX = longX;
    }

    public String getLongY() {
        return LongY;
    }

    public void setLongY(String longY) {
        LongY = longY;
    }

    public String getLongZ() {
        return LongZ;
    }

    public void setLongZ(String longZ) {
        LongZ = longZ;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getAcDbEllipse() {
        return AcDbEllipse;
    }


}
