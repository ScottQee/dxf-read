package dxf.entities;

import dxf.Entity;

public class Text extends Entity {
    public Text(String name, String layer) {
        super("AcDbText", layer);
    }

    public Text() {
    }
    private final String AcDbText = "AcDbText";

    public String getAcDbText() {
        return AcDbText;
    }
    private String pointX; //10
    private String pointY; //20
    private String pointZ; //30
    private String height; //40
    private String value; //1
    private String secondPointX; //11
    private String secondPointY; //21
    private String secondPointZ; //31

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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSecondPointX() {
        return secondPointX;
    }

    public void setSecondPointX(String secondPointX) {
        this.secondPointX = secondPointX;
    }

    public String getSecondPointY() {
        return secondPointY;
    }

    public void setSecondPointY(String secondPointY) {
        this.secondPointY = secondPointY;
    }

    public String getSecondPointZ() {
        return secondPointZ;
    }

    public void setSecondPointZ(String secondPointZ) {
        this.secondPointZ = secondPointZ;
    }

    @Override
    public String toString() {
        return "Text{" +
                "pointX='" + pointX + '\'' +
                ", pointY='" + pointY + '\'' +
                ", pointZ='" + pointZ + '\'' +
                ", height='" + height + '\'' +
                ", value='" + value + '\'' +
                ", secondPointX='" + secondPointX + '\'' +
                ", secondPointY='" + secondPointY + '\'' +
                ", secondPointZ='" + secondPointZ + '\'' +
                ", handle='" + handle + '\'' +
                ", layer='" + layer + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
