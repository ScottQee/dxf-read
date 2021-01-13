import dxf.Layer;
import dxf.entities.*;
import dxf.service.DxfMultiRead;
import dxf.service.MultiRead;
import dxf.service.ReadForLongshan;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TestRead {
    public static void main(String[] args) {
        File f1 = new File("E:\\guiDXF\\1.dxf");
        File f2 = new File("E:\\guiDXF\\2.dxf");
        File f3 = new File("E:\\guiDXF\\3.dxf");
        File f4 = new File("E:\\guiDXF\\4.dxf");
        File f5 = new File("E:\\guiDXF\\5.dxf");
        DxfMultiRead r1 = new DxfMultiRead(f1);
        DxfMultiRead r2 = new DxfMultiRead(f2);
        DxfMultiRead r3 = new DxfMultiRead(f3);
        DxfMultiRead r4 = new DxfMultiRead(f4);
        DxfMultiRead r5 = new DxfMultiRead(f5);
        TestRead testRead = new TestRead();
        testRead.sortByLayer(r1);
        testRead.sortByLayer(r2);
        testRead.sortByLayer(r3);
        testRead.sortByLayer(r4);
        testRead.sortByLayer(r5);
        System.err.println("整理完成");
    }
    void sortByLayer(DxfMultiRead read){
        List<Circle> circleList = read.circleList;
        List<Insert> insertList = read.insertList;
        List<Line> lineList = read.lineList;
        List<Point> pointList = read.pointList;
        List<Text> textList = read.textList;
        List<LwPolyLine> lwPolylineList = read.lwPolylineList;
        List<Polyline> polylineList = read.polylineList;
        List<Layer> layerList = read.layerList;
        for (Layer layer : layerList) {
            String layerName = layer.getValue();
            for (Circle circle : circleList){
                if (circle.getLayer().equals(layerName)){
                    layer.getEntities().add(circle);
                }
            }
            for (Insert insert : insertList){
                if (insert.getLayer().equals(layerName)){
                    layer.getEntities().add(insert);
                }
            }
            for (Line line : lineList){
                if (line.getLayer().equals(layerName)){
                    layer.getEntities().add(line);
                }
            }
            for (Point point : pointList){
                if (point.getLayer().equals(layerName)){
                    layer.getEntities().add(point);
                }
            }
            for (Text text : textList){
                if (text.getLayer().equals(layerName)){
                    layer.getEntities().add(text);
                }
            }
            for (LwPolyLine lwPolyLine : lwPolylineList){
                if (lwPolyLine.getLayer().equals(layerName)){
                    layer.getEntities().add(lwPolyLine);
                }
            }
            for (Polyline polyline : polylineList){
                if (polyline.getLayer().equals(layerName)){
                    layer.getEntities().add(polyline);
                }
            }
        }
    }
}
