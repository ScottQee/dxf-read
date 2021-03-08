package dxf.entities;

import dxf.Entity;

/**
 * 文本
 * 文本坐标(pointX,pointY,pointZ)
 * 文本内容(value)
 * 旋转角度(angle)
 */
public class Text extends Entity {
    /**
     * 文本
     * 文本坐标(pointX,pointY,pointZ)
     * 文本内容(value)
     * 旋转角度(angle)
     * @param layer 所在图层
     */
    public Text( String layer) {
        super("TEXT", layer);
    }
    /**
     * 文本
     * 文本坐标(pointX,pointY,pointZ)
     * 文本内容(value)
     * 旋转角度(angle)
     */
    public Text() {
    }
    private final String AcDbText = "AcDbText";

    public String getAcDbText() {
        return AcDbText;
    }
    private String pointX; //10
    private String pointY; //20
    private String pointZ; //30
    private String height = "0.1"; //40
    private String value; //1
    private String secondPointX; //11
    private String secondPointY; //21
    private String secondPointZ; //31
    private String angle = "0";//50

    public String getAngle() {
        return angle;
    }

    public void setAngle(String angle) {
        this.angle = angle;
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

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder(super.toDxfString());
        s.append("100\nAcDbText\n");
        s.append("10\n").append(pointX).append("\n");
        s.append("20\n").append(pointY).append("\n");
        s.append("30\n").append(pointZ).append("\n");
        s.append("40\n").append(height).append("\n");
        s.append("1\n").append(value).append("\n");
        s.append("50\n").append(angle).append("\n");
        s.append("100\nAcDbText\n");
        return s.toString();
    }
}
