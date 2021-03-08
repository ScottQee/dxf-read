package dxf.entities;

import dxf.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 多段线
 * 端点数(pointCount)
 * 点集合(points)
 */
public class LwPolyLine extends Entity {
    /**
     * 多段线
     * 端点数(pointCount)
     * 点集合(points)
     * @param layer 所在图层
     */
    public LwPolyLine( String layer) {
        super("LWPOLYLINE", layer);
    }
    /**
     * 多段线
     * 端点数(pointCount)
     * 点集合(points)
     */
    public LwPolyLine() {
        super("LWPOLYLINE","0");
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

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder(super.toDxfString());
        s.append("100\nAcDbPolyline\n");
        s.append("90\n").append(pointCount).append("\n");
        s.append("70\n").append(0).append("\n");
        for (int i = 0 ; i < points.size(); i++){
            s.append("10\n").append(points.get(i)[0]).append("\n");
            s.append("20\n").append(points.get(i)[1]).append("\n");
        }
        return s.toString();
    }
}
