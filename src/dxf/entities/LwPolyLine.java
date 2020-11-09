package dxf.entities;

import dxf.Entity;

import java.util.ArrayList;
import java.util.List;

public class LwPolyLine extends Entity {
    public LwPolyLine(String name, String layer) {
        super("LWPOLYLINE", layer);
    }

    public LwPolyLine() {
    }
    public final String AcDbPolyline = "AcDbPolyline";

    private String pointCount; //90
    private List<String[]> points = new ArrayList<>();

    public List<String[]> getPoints() {
        return points;
    }

    public void setPoints(List<String[]> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        StringBuilder pointList = new StringBuilder("{");
        for (String[] s : points){
            pointList.append("[").append(s[0]).append(",").append(s[1]).append("],");
        }
        pointList.append("}");
        return "LwPolyLine{" +
                "pointCount='" + pointCount + '\'' +
                ", points=" + pointList.toString() +
                ", handle='" + handle + '\'' +
                ", layer='" + layer + '\'' +
                ", lineType='" + lineType + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public String getPointCount() {
        return pointCount;
    }

    public void setPointCount(String pointCount) {
        this.pointCount = pointCount;
    }

}
