package model;

import java.util.List;

public class ResultModel {
    private String text;
    private double[] point;
    private List<double[]> points;
    private String type;
    private double radius;
    private List<ResultModel> children;

    public List<ResultModel> getChildren() {
        return children;
    }

    public void setChildren(List<ResultModel> children) {
        this.children = children;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public ResultModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double[] getPoint() {
        return point;
    }

    public void setPoint(double[] point) {
        this.point = point;
    }

    public List<double[]> getPoints() {
        return points;
    }

    public void setPoints(List<double[]> points) {
        this.points = points;
    }
}
