package dxf.entities;

import dxf.Entity;

import java.util.ArrayList;
import java.util.List;

public class Insert extends Entity {
    public Insert() {
    }

    public Insert(String name, String layer) {
        super("INSERT", layer);
    }
    private final String AcDbBlockReference = "AcDbBlockReference";

    public String getAcDbBlockReference() {
        return AcDbBlockReference;
    }
    private String blockName;
    private String pointX;
    private String pointY;
    private String pointZ;
    private String pointX_Scale = "1";
    private String pointY_Scale = "1";
    private String pointZ_Scale = "1";
    private List<Entity> entities = new ArrayList<>();

    @Override
    public String toString() {
        return "Insert{" +
                "blockName='" + blockName + '\'' +
                ", pointX='" + pointX + '\'' +
                ", pointY='" + pointY + '\'' +
                ", pointZ='" + pointZ + '\'' +
                ", pointX_Scale='" + pointX_Scale + '\'' +
                ", pointY_Scale='" + pointY_Scale + '\'' +
                ", pointZ_Scale='" + pointZ_Scale + '\'' +
                ", entities=" + entities +
                ", layer='" + layer + '\'' +
                ", lineType='" + lineType + '\'' +
                ", color='" + color + '\'' +
                ", lineScale='" + lineScale + '\'' +
                '}';
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

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

    public String getPointX_Scale() {
        return pointX_Scale;
    }

    public void setPointX_Scale(String pointX_Scale) {
        this.pointX_Scale = pointX_Scale;
    }

    public String getPointY_Scale() {
        return pointY_Scale;
    }

    public void setPointY_Scale(String pointY_Scale) {
        this.pointY_Scale = pointY_Scale;
    }

    public String getPointZ_Scale() {
        return pointZ_Scale;
    }

    public void setPointZ_Scale(String pointZ_Scale) {
        this.pointZ_Scale = pointZ_Scale;
    }
}
