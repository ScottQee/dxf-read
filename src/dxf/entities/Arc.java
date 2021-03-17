package dxf.entities;

import dxf.Entity;

/**
 *  圆弧图形
 *  圆心（centerX,centerY,centerZ)
 *  半径(radius)
 *  圆弧角度(startAngle,endAngle)
 */
public class Arc extends Entity {
    /**
     *  圆弧图形
     *  圆心（centerX,centerY,centerZ)
     *  半径(radius)
     *  圆弧角度(startAngle,endAngle)
     * @param layer 所在图层
     */
    public Arc( String layer) {
        super("ARC", layer);
    }
    /**
     *  圆弧图形
     *  圆心（centerX,centerY,centerZ)
     *  半径(radius)
     *  圆弧角度(startAngle,endAngle)
     */
    public Arc() {
        super("ARC","0");
    }
    private final String AcDbCircle = "AcDbCircle";
    private String centerX; //10
    private String centerY; //20
    private String centerZ; //30
    private String radius;
    private String start_Angle;
    private String end_Angle;

    public String getAcDbCircle() {
        return AcDbCircle;
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

    public String getStart_Angle() {
        return start_Angle;
    }

    public void setStart_Angle(String start_Angle) {
        this.start_Angle = start_Angle;
    }

    public String getEnd_Angle() {
        return end_Angle;
    }

    public void setEnd_Angle(String end_Angle) {
        this.end_Angle = end_Angle;
    }

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder(super.toDxfString());
        s.append("100\nAcDbCircle\n");
        s.append("10\n").append(centerX).append("\n");
        s.append("20\n").append(centerY).append("\n");
        s.append("30\n").append(centerZ).append("\n");
        s.append("40\n").append(radius).append("\n");
        s.append("50\n").append(start_Angle).append("\n");
        s.append("51\n").append(start_Angle).append("\n");
        return s.toString();
    }
}
