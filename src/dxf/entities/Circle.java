package dxf.entities;

import dxf.Entity;

/**
 *  圆弧图形
 *  圆心（centerX,centerY,centerZ)
 *  半径(radius)
 */
public class Circle extends Entity {
    /**
     *  圆弧图形
     *  圆心（centerX,centerY,centerZ)
     *  半径(radius)
     */
    public Circle(){
        super("CIRCLE","0");
    }

    /**
     *  圆弧图形
     *  圆心（centerX,centerY,centerZ)
     *  半径(radius)
     * @param layer 所在图层
     */
    public Circle( String layer) {
        super("CIRCLE", layer);
    }
    public Circle(String[] point,String radius,String layer) throws Exception {
        super("CIRCLE", layer);
        if (point.length > 3 || point.length < 2){
            throw new Exception("数据出错");
        }
        centerX = point[0]; centerY = point[1];
        if (point.length == 3){
            centerZ = point[2];
        }else {
            centerZ = "0.0";
        }
        this.radius = radius;
    }
    private final String AcDbCircle = "AcDbCircle";

    public String getAcDbCircle() {
        return AcDbCircle;
    }

    private String centerX; //10
    private String centerY; //20
    private String centerZ; //30
    private String radius;

    @Override
    public String toString() {
        return "Circle{" +
                "centerX='" + centerX + '\'' +
                ", centerY='" + centerY + '\'' +
                ", centerZ='" + centerZ + '\'' +
                ", radius='" + radius + '\'' +
                ", handle='" + handle + '\'' +
                ", layer='" + layer + '\'' +
                ", color='" + color + '\'' +
                '}';
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

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder(super.toDxfString());
        s.append("100\nAcDbCircle\n");
        s.append("39\n100\n");
        s.append("10\n").append(centerX).append("\n");
        s.append("20\n").append(centerY).append("\n");
        s.append("30\n").append(centerZ).append("\n");
        s.append("40\n").append(radius).append("\n");
        return s.toString();
    }
}
