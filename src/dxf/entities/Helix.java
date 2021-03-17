package dxf.entities;

import dxf.Entity;

public class Helix extends Entity {
    public Helix() {
        super("HELIX","0");
    }

    public Helix(String name, String layer) {
        super("HELIX", layer);
    }
    private final String AcDbHelix = "AcDbHelix";
    private String basePointX; //10
    private String basePointY; //20
    private String basePointZ; //30
    private String startPointX; //11
    private String startPointY; //21
    private String startPointZ; //31
    private String vectorPointX; //12
    private String vectorPointY; //22
    private String vectorPointZ; //32
    private String radius; //40
    private String round; //41
    private String roundHeight; //42
    private String helixType; //290 左右手习惯;0 = 左手，1 = 右手
    private String limitType; //280 约束类型; 0 = 约束圈高, 1 = 约束圈数, 2 = 约束高度

    public String getAcDbHelix() {
        return AcDbHelix;
    }

    public String getBasePointX() {
        return basePointX;
    }

    public void setBasePointX(String basePointX) {
        this.basePointX = basePointX;
    }

    public String getBasePointY() {
        return basePointY;
    }

    public void setBasePointY(String basePointY) {
        this.basePointY = basePointY;
    }

    public String getBasePointZ() {
        return basePointZ;
    }

    public void setBasePointZ(String basePointZ) {
        this.basePointZ = basePointZ;
    }

    public String getStartPointX() {
        return startPointX;
    }

    public void setStartPointX(String startPointX) {
        this.startPointX = startPointX;
    }

    public String getStartPointY() {
        return startPointY;
    }

    public void setStartPointY(String startPointY) {
        this.startPointY = startPointY;
    }

    public String getStartPointZ() {
        return startPointZ;
    }

    public void setStartPointZ(String startPointZ) {
        this.startPointZ = startPointZ;
    }

    public String getVectorPointX() {
        return vectorPointX;
    }

    public void setVectorPointX(String vectorPointX) {
        this.vectorPointX = vectorPointX;
    }

    public String getVectorPointY() {
        return vectorPointY;
    }

    public void setVectorPointY(String vectorPointY) {
        this.vectorPointY = vectorPointY;
    }

    public String getVectorPointZ() {
        return vectorPointZ;
    }

    public void setVectorPointZ(String vectorPointZ) {
        this.vectorPointZ = vectorPointZ;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public String getRoundHeight() {
        return roundHeight;
    }

    public void setRoundHeight(String roundHeight) {
        this.roundHeight = roundHeight;
    }

    public String getHelixType() {
        return helixType;
    }

    public void setHelixType(String helixType) {
        this.helixType = helixType;
    }

    public String getLimitType() {
        return limitType;
    }

    public void setLimitType(String limitType) {
        this.limitType = limitType;
    }
}
