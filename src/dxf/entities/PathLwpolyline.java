package dxf.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * for Hatch只为用
 */
public class PathLwpolyline {
    public String isClose; //73
    public String pointNum; //93
    public List<String[]> points = new ArrayList<>();


    @Override
    public String toString() {
        return "PathLwpolyline{" +
                "isClose='" + isClose + '\'' +
                ", pointNum='" + pointNum + '\'' +
                ", points=" + points.toString() +
                '}';
    }
}
