package dxf.entities;

import dxf.Entity;

public class Vertex extends Entity {
    public Vertex(String name, String layer) {
        super("VERTEX", layer);
    }

    public Vertex() {
    }
    private String pointX; //10
    private String pointY; //20
    private String pointZ; //30
    private String parentHandle; //330

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

    public String getParentHandle() {
        return parentHandle;
    }

    public void setParentHandle(String parentHandle) {
        this.parentHandle = parentHandle;
    }

    @Override
    public String toString() {
        return "[" + pointX + "," + pointY + "," + pointZ + "]";

    }
}
