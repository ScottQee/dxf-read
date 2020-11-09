package dxf.entities;

import dxf.Entity;

public class Line extends Entity {
    public Line(String name, String layer) {
        super("LINE", layer);
    }

    public Line() {
    }
    private final String AcDbLine = "AcDbLine";

    public String getAcDbLine() {
        return AcDbLine;
    }
    private String startX; //10
    private String startY; //20
    private String startZ; //30
    private String endX; //11
    private String endY; //21
    private String endZ; //31

    @Override
    public String toString() {
        return "Line{" +
                "startX='" + startX + '\'' +
                ", startY='" + startY + '\'' +
                ", startZ='" + startZ + '\'' +
                ", endX='" + endX + '\'' +
                ", endY='" + endY + '\'' +
                ", endZ='" + endZ + '\'' +
                ", handle='" + handle + '\'' +
                ", layer='" + layer + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getStartX() {
        return startX;
    }

    public void setStartX(String startX) {
        this.startX = startX;
    }

    public String getStartY() {
        return startY;
    }

    public void setStartY(String startY) {
        this.startY = startY;
    }

    public String getStartZ() {
        return startZ;
    }

    public void setStartZ(String startZ) {
        this.startZ = startZ;
    }

    public String getEndX() {
        return endX;
    }

    public void setEndX(String endX) {
        this.endX = endX;
    }

    public String getEndY() {
        return endY;
    }

    public void setEndY(String endY) {
        this.endY = endY;
    }

    public String getEndZ() {
        return endZ;
    }

    public void setEndZ(String endZ) {
        this.endZ = endZ;
    }
}
