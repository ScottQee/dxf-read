package dxf.entities;

import dxf.Entity;

public class Arc extends Entity {
    public Arc(String name, String layer) {
        super("ARC", layer);
    }
    public Arc() {

    }
    private final String AcDbCircle = "AcDbCircle";
    private String centerX; //10
    private String centerY; //20
    private String centerZ; //30
    private String radius;
    private String start_Angle;
    private String end_Angle;

    public String getAcDbCircle() {
        return AcDbCircle;
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

    public String getStart_Angle() {
        return start_Angle;
    }

    public void setStart_Angle(String start_Angle) {
        this.start_Angle = start_Angle;
    }

    public String getEnd_Angle() {
        return end_Angle;
    }

    public void setEnd_Angle(String end_Angle) {
        this.end_Angle = end_Angle;
    }
}
