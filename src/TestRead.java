import dxf.Entity;
import dxf.Layer;
import dxf.entities.*;
import dxf.service.DxfMultiRead;
import dxf.service.MultiRead;
import dxf.service.ReadForLongshan;
import math.MathUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, Layer> layerMap1 = testRead.sortByLayer(r1);
        Map<String, Layer> layerMap2 = testRead.sortByLayer(r2);
        Map<String, Layer> LayerMap3 = testRead.sortByLayer(r3);
        Map<String, Layer> LayerMap4 = testRead.sortByLayer(r4);
        Map<String, Layer> LayerMap5 = testRead.sortByLayer(r5);
        System.err.println("整理完成");
        Text text = (Text) layerMap1.get("JMD").getEntities().get("text3");
        LwPolyLine lwPolyLine1 = (LwPolyLine) layerMap1.get("JMD").getEntities().get("lwPolyline1");
        LwPolyLine lwPolyLine2 = (LwPolyLine) layerMap1.get("JMD").getEntities().get("lwPolyline2");



        System.out.println(MathUtil.contains(text.getPointX(),text.getPointY(), lwPolyLine1.getPoints()));
        System.out.println(MathUtil.contains(text.getPointX(),text.getPointY(), lwPolyLine2.getPoints()));

    }
    Map<String,Layer> sortByLayer(DxfMultiRead read){
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
            for (int i = 0, j = 1; i < circleList.size();i++){
                Circle circle = circleList.get(i);
                if (circle.getLayer().equals(layerName)){
                    String key = "circle" + j;
                    layer.getEntities().put(key,circle);
                    j++;
                }
            }
            for (int i = 0, j = 1; i < insertList.size(); i++){
                Insert insert = insertList.get(i);
                if (insert.getLayer().equals(layerName)){
                    String key = "insert" + j;
                    layer.getEntities().put(key,insert);
                    j++;
                }
            }
            for (int i = 0, j = 1; i < lineList.size();i++){
                Line line = lineList.get(i);
                if (line.getLayer().equals(layerName)){
                    String key = "line" + j;
                    layer.getEntities().put(key,line);
                    j++;
                }
            }
            for (int i = 0, j = 1; i < pointList.size(); i++){
                Point point = pointList.get(i);
                if (point.getLayer().equals(layerName)){
                    String key = "point" + j;
                    layer.getEntities().put(key,point);
                    j++;
                }
            }
            for (int i = 0, j = 1; i < textList.size();i++){
                Text text = textList.get(i);
                if (text.getLayer().equals(layerName)){
                    String key = "text" + j;
                    layer.getEntities().put(key,text);
                    j++;
                }
            }
            for (int i = 0, j = 1; i < lwPolylineList.size(); i++){
                LwPolyLine lwPolyLine = lwPolylineList.get(i);
                if (lwPolyLine.getLayer().equals(layerName)){
                    String key = "lwPolyline" + j;
                    layer.getEntities().put(key,lwPolyLine);
                    j++;
                }
            }
            for (int i = 0, j = 1; i < polylineList.size(); i++){
                Polyline polyline = polylineList.get(i);
                if (polyline.getLayer().equals(layerName)){
                    String key = "polyline" + j;
                    layer.getEntities().put(key,polyline);
                    j++;
                }
            }
        }
        Map<String,Layer> results = new HashMap<>();
        for (Layer layer : layerList){
            String key = layer.getValue();
            results.put(key,layer);
        }
        return results;
    }

    public void calBorder(Map<String,Layer> layerMap){
        List<Double[]> border = new ArrayList<>();
        double[] max = new double[2];
        double[] min = new double[2];
        for (Map.Entry<String,Layer> entry: layerMap.entrySet()){
            String key = entry.getKey();
            Map<String, Entity> entities = entry.getValue().getEntities();
            if (key.equals("JMD") || key.equals("JZD") || key.equals("ZRZ") || key.equals("JZP")){
                for (Entity entity : entities.values()){
                    if (entity instanceof Text){
                        double x = Double.parseDouble(((Text) entity).getPointX());
                        double y = Double.parseDouble(((Text) entity).getPointY());
                        upgradeData(min,max,new double[]{x,y});
                    }
                    if (entity instanceof Point){
                        double x = Double.parseDouble(((Point) entity).getPointX());
                        double y = Double.parseDouble(((Point) entity).getPointY());
                        upgradeData(min,max,new double[]{x,y});
                    }
                    if (entity instanceof Circle){
                        double x = Double.parseDouble(((Circle) entity).getCenterX());
                        double y = Double.parseDouble(((Circle) entity).getCenterY());
                        double radius = Double.parseDouble(((Circle) entity).getRadius());
                        double[] circleMin = new double[]{x - radius, y - radius};
                        double[] circleMax = new double[]{x + radius, y + radius};
                        upgradeData(min,max,circleMin,circleMax);
                    }
                    if (entity instanceof Line){
                        double[] start = new double[]{Double.parseDouble(((Line) entity).getStartX()),Double.parseDouble(((Line) entity).getStartY())};
                        double[] end = new double[]{Double.parseDouble(((Line) entity).getEndX()),Double.parseDouble(((Line) entity).getEndY())};
                        upgradeData(min,max,start,end);
                    }
                    if (entity instanceof Insert){
                        double x = Double.parseDouble(((Insert) entity).getPointX());
                        double y = Double.parseDouble(((Insert) entity).getPointY());
                        upgradeData(min,max,new double[]{x,y});
                    }
                    if (entity instanceof LwPolyLine){
                        List<String[]> points = ((LwPolyLine) entity).getPoints();
                        upgradeData(min,max,points);
                    }
                    if (entity instanceof Polyline){
                        List<String[]> pointList = ((Polyline) entity).getPointList();
                        upgradeData(min,max,pointList);
                    }
                }
            }
        }
    }

    /**
     * 更新最大点和最小点方便确定矩形范围
     * @param min
     * @param max
     * @param target
     */
    public void upgradeData(double[] min, double[] max , double[] target){

    }

    /**
     * 更新最大点和最小点方便确定矩形范围
     * @param min
     * @param max
     * @param targetMin
     * @param targetMax
     */
    public void upgradeData(double[] min, double[] max , double[] targetMin,double[] targetMax){

    }

    /**
     * 更新最大点和最小点方便确定矩形范围
     * @param min
     * @param max
     * @param points
     */
    public void upgradeData(double[] min, double[] max, List<String[]> points){
        for (String[] point : points){
            double x = Double.parseDouble(point[0]);
            double y = Double.parseDouble(point[1]);
            upgradeData( min, max, new double[]{x,y});
        }
    }
}
