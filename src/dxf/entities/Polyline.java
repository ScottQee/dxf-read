package dxf.entities;

import dxf.Entity;

import java.util.ArrayList;
import java.util.List;

public class Polyline extends Entity {
    public Polyline(String name, String layer) {
        super("POLYLINE", layer);
    }

    public Polyline() {
    }
    private List<Vertex> pointList = new ArrayList<>();
    private List<String[]> points = new ArrayList<>();

    public List<Vertex> getVPointList() {
        return pointList;
    }

    public List<String[]> getPointList() {
        List<String[]> points = new ArrayList<>();
        for (Vertex v : pointList){
            points.add(new String[]{v.getPointX(),v.getPointY()});
        };
        this.points = points;
        return points;
    }
    public List<String[]> getPoints(){
        return points;
    }
    public void setPointList(List<String[]> pointList){
        this.points = pointList;
    }
    public void setVPointList(List<Vertex> pointList) {
        this.pointList = pointList;
    }

    @Override
    public String toString() {
        return "Polyline{" +
                "pointList=" + pointList +
                ", handle='" + handle + '\'' +
                ", layer='" + layer + '\'' +
                ", lineType='" + lineType + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
