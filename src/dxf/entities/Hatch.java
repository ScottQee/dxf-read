package dxf.entities;

import dxf.Entity;

import java.util.List;

public class Hatch extends Entity {

    private String baseX; //10
    private String baseY; //20
    private String baseZ; //30
    private String hatchName; //2填充团名
    private String pathNum; //91边界路径环数（简单理解为几个图形）
    private List<PathLwpolyline> list; //

    public Hatch(String name, String layer) {
        super("HATCH", layer);
    }

    public Hatch() {
    }
    private final String AcDbHatch = "AcDbHatch";

    public String getBaseX() {
        return baseX;
    }

    public void setBaseX(String baseX) {
        this.baseX = baseX;
    }

    public String getBaseY() {
        return baseY;
    }

    public void setBaseY(String baseY) {
        this.baseY = baseY;
    }

    public String getBaseZ() {
        return baseZ;
    }

    public void setBaseZ(String baseZ) {
        this.baseZ = baseZ;
    }

    public String getHatchName() {
        return hatchName;
    }

    public void setHatchName(String hatchName) {
        this.hatchName = hatchName;
    }

    public String getPathNum() {
        return pathNum;
    }

    public void setPathNum(String pathNum) {
        this.pathNum = pathNum;
    }

    public List<PathLwpolyline> getList() {
        return list;
    }

    public void setList(List<PathLwpolyline> list) {
        this.list = list;
    }

    public String getAcDbHatch() {
        return AcDbHatch;
    }

    @Override
    public String toString() {
        return "Hatch{" +
                "baseX='" + baseX + '\'' +
                ", baseY='" + baseY + '\'' +
                ", baseZ='" + baseZ + '\'' +
                ", hatchName='" + hatchName + '\'' +
                ", pathNum='" + pathNum + '\'' +
                ", list=" + list +
                ", handle='" + handle + '\'' +
                ", layer='" + layer + '\'' +
                ", lineType='" + lineType + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

