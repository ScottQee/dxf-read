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
    private List<Vertex> pointList = new ArrayList();

    public List<Vertex> getPointList() {
        return pointList;
    }

    public void setPointList(List<Vertex> pointList) {
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
