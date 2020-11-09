import dxf.entities.*;
import dxf.service.Read;

import java.io.File;

public class TestRead {
    public static void main(String[] args){
        File f = new File("F:\\123.dxf");
        Read read = new Read(f);
        read.readToTable();
        read.readTables();
        read.readBlocks();
        read.readEntities();
        for (Point p : read.pointList){
            System.out.println(p);
        }
        for (Circle p : read.circleList){
            System.out.println(p);
        }
        for (Line p : read.lineList){
            System.out.println(p);
        }
        for (LwPolyLine p : read.lwPolylineList){
            System.out.println(p);
        }
        for (Text t : read.textList){
            System.out.println(t);
        }
        for (Polyline t : read.polylineList){
            System.out.println(t);
        }
        for (Insert t : read.insertList){
            System.out.println(t);
        }

        System.out.println("point:" + read.pointList.size());
        System.out.println("circle:" + read.circleList.size());
        System.out.println("line:" + read.lineList.size());
        System.out.println("lwPolyline:" + read.lwPolylineList.size());
        System.out.println("text:" + read.textList.size());
        System.out.println("polyline:" + read.polylineList.size());
        System.out.println("insert:" + read.insertList.size());

        System.out.println(read.blockList);
//        System.out.println(JSON.toJSONString(read.textList));
    }
}
