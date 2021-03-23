package dxf.entities;

import dxf.Entity;

/**
 * 点
 * 点坐标(pointX,pointY,pointZ)
 */
public class Point extends Entity {
    /**
     * 点
     * 点坐标(pointX,pointY,pointZ)
     * @param layer 所在图层
     */
    public Point(String layer) {
        super("POINT", layer);
    }
    /**
     * 点
     * 点坐标(pointX,pointY,pointZ)
     */
    public Point() {
        super("POINT","0");
    }
    public Point(String[] point,String layer) throws Exception {
        super("POINT",layer);
        if (point.length > 3 || point.length < 2){
            throw new Exception("数据出错");
        }
        pointX = point[0];
        pointY = point[1];
        if (point.length == 3){
            pointZ = point[2];
        } else {
            pointZ = "0";
        }
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

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder(super.toDxfString());
        s.append("100\nAcDbPoint\n");
        s.append("10\n").append(pointX).append("\n");
        s.append("20\n").append(pointY).append("\n");
        s.append("30\n").append(pointZ).append("\n");
        return s.toString();
    }
}
