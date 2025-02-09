package dxf.entities;

import dxf.Entity;


/**
 * 线段
 * 起点(startX,startY,startZ)，终点(endX,endY,endZ)
 */
public class Line extends Entity {
    /**
     * 线段
     * 起点(startX,startY,startZ)，终点(endX,endY,endZ)
     * @param layer 所在图层
     */
    public Line(String layer) {
        super("LINE", layer);
    }
    /**
     * 线段
     * 起点(startX,startY,startZ)，终点(endX,endY,endZ)
     */
    public Line() {
        super("LINE","0");
    }
    public Line(String[] point1,String[] point2,String layer) throws Exception {
        super("LINE",layer);
        if ((point1.length > 3 || point1.length < 2) || (point2.length > 3 || point2.length < 2)) {
            throw new Exception("数据出错");
        }
        startX = point1[0];startY = point1[1];
        endX = point2[0]; endY = point2[1];
        if (point1.length == 3){
            startZ = point1[2];
        } else {
            startZ = "0.0";
        }
        if (point2.length == 3){
            endZ = point2[2];
        } else {
            endZ = "0.0";
        }
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

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder(super.toDxfString());
        s.append("100\nAcDbLine\n");
        s.append("10\n").append(startX).append("\n");
        s.append("20\n").append(startY).append("\n");
        s.append("30\n").append(startZ).append("\n");
        s.append("11\n").append(endX).append("\n");
        s.append("21\n").append(endY).append("\n");
        s.append("31\n").append(endZ).append("\n");
        return s.toString();
    }
}
