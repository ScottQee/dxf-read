import dxfRead.Entity;
import dxfRead.Layer;
import dxfRead.entities.*;
import dxfRead.service.DxfMultiRead;
import math.Clipping;
import math.MathUtil;
import model.ResultModel;

import java.awt.geom.Line2D;
import java.io.File;
import java.util.*;

public class TestRead {
    public static void main(String[] args) {
        TestRead testRead = new TestRead();
        Map<String, Map<String, ResultModel>> result = testRead.getResult("E:\\guiDXF\\3.dxf");
        System.err.println("整理完成");
    }
    public Map<String, Map<String,ResultModel>> getResult(String path){
        File file = new File(path);
        DxfMultiRead read = new DxfMultiRead(file);
        Map<String, Layer> layerMap = sortByLayer(read);
        calBorder(layerMap);
        return sortOut(layerMap);
    }
    private Map<String, Map<String,ResultModel>> sortOut(Map<String, Layer> layerMap){
        Map<String, Map<String,ResultModel>> result = new HashMap<>();
        for(Map.Entry<String,Layer> entry : layerMap.entrySet()) {
            String key = entry.getKey();
            Map<String, Entity> entities = entry.getValue().getEntities();
            Map<String, ResultModel> modelMap = new HashMap<>();
            if (key.equals("border")){
                List<double[]> border = entry.getValue().border;
                ResultModel resultModel = new ResultModel();
                resultModel.setType("border");
                resultModel.setPoints(border);
                modelMap.put(key,resultModel);
            }else {
                for (Map.Entry<String, Entity> entity : entities.entrySet()) {
                    String name = entity.getKey();
                    Entity value = entity.getValue();
                    ResultModel resultModel = new ResultModel();
                    if (name.contains("text") && value instanceof Text) {
                        resultModel.setType("text");
                        resultModel.setText(((Text) value).getValue());
                        double pointX = Double.parseDouble(((Text) value).getPointX());
                        double pointY = Double.parseDouble(((Text) value).getPointY());
                        resultModel.setPoint(new double[]{pointX, pointY});
                    }
                    if (name.contains("point") && value instanceof Point) {
                        resultModel.setType("point");
                        double pointX = Double.parseDouble(((Point) value).getPointX());
                        double pointY = Double.parseDouble(((Point) value).getPointY());
                        resultModel.setPoint(new double[]{pointX, pointY});
                    }
                    if (name.contains("circle") && value instanceof Circle) {
                        resultModel.setType("circle");
                        double x = Double.parseDouble(((Circle) value).getCenterX());
                        double y = Double.parseDouble(((Circle) value).getCenterY());
                        double radius = Double.parseDouble(((Circle) value).getRadius());
                        resultModel.setPoint(new double[]{x, y});
                        resultModel.setRadius(radius);
                    }
                    if (name.contains("line") && value instanceof Line) {
                        resultModel.setType("line");
                        List<double[]> points = new ArrayList<>();
                        points.add(new double[]{Double.parseDouble(((Line) value).getStartX()), Double.parseDouble(((Line) value).getStartY())});
                        points.add(new double[]{Double.parseDouble(((Line) value).getEndX()), Double.parseDouble(((Line) value).getEndY())});
                        resultModel.setPoints(points);
                    }
                    if (name.contains("lwPolyline") && value instanceof LwPolyLine) {
                        resultModel.setType("lwPolyline");
                        List<double[]> points = new ArrayList<>();
                        List<String[]> list = ((LwPolyLine) value).getPoints();
                        for (String[] strings : list) {
                            points.add(new double[]{Double.parseDouble(strings[0]), Double.parseDouble(strings[1])});
                        }
                        resultModel.setPoints(points);
                    }
                    if (name.contains("polyline") && value instanceof Polyline) {
                        resultModel.setType("lwPolyline");
                        List<double[]> points = new ArrayList<>();
                        List<String[]> list = ((Polyline) value).getPoints();
                        for (String[] strings : list) {
                            points.add(new double[]{Double.parseDouble(strings[0]), Double.parseDouble(strings[1])});
                        }
                        resultModel.setPoints(points);
                    }
                    if (name.contains("insert") && value instanceof Insert) {
                        resultModel.setType("block");
                        List<ResultModel> children = new ArrayList<>();
                        double baseX = Double.parseDouble(((Insert) value).getPointX());
                        double baseY = Double.parseDouble(((Insert) value).getPointY());
                        List<Entity> list = ((Insert) value).getEntities();
                        for (Entity child : list) {
                            if (child instanceof Circle) {
                                ResultModel model = new ResultModel();
                                model.setType("circle");
                                double x = Double.parseDouble(((Circle) child).getCenterX());
                                double y = Double.parseDouble(((Circle) child).getCenterY());
                                double radius = Double.parseDouble(((Circle) child).getRadius());
                                model.setPoint(new double[]{x, y});
                                model.setRadius(radius);
                                children.add(model);
                            }
                            if (child instanceof Text) {
                                ResultModel model = new ResultModel();
                                model.setType("text");
                                model.setText(((Text) child).getValue());
                                double pointX = Double.parseDouble(((Text) child).getPointX());
                                double pointY = Double.parseDouble(((Text) child).getPointY());
                                model.setPoint(new double[]{pointX, pointY});
                                children.add(model);
                            }
                            if (child instanceof Point) {
                                ResultModel model = new ResultModel();
                                model.setType("point");
                                double pointX = Double.parseDouble(((Point) child).getPointX());
                                double pointY = Double.parseDouble(((Point) child).getPointY());
                                model.setPoint(new double[]{pointX, pointY});
                                children.add(model);
                            }
                            if (child instanceof Line) {
                                ResultModel model = new ResultModel();
                                model.setType("line");
                                List<double[]> points = new ArrayList<>();
                                points.add(new double[]{Double.parseDouble(((Line) child).getStartX()), Double.parseDouble(((Line) child).getStartY())});
                                points.add(new double[]{Double.parseDouble(((Line) child).getEndX()), Double.parseDouble(((Line) child).getEndY())});
                                model.setPoints(points);
                                children.add(model);
                            }
                            if (child instanceof LwPolyLine) {
                                ResultModel model = new ResultModel();
                                model.setType("lwPolyline");
                                List<double[]> points = new ArrayList<>();
                                List<String[]> lists = ((LwPolyLine) child).getPoints();
                                for (String[] strings : lists) {
                                    points.add(new double[]{Double.parseDouble(strings[0]), Double.parseDouble(strings[1])});
                                }
                                model.setPoints(points);
                                children.add(model);
                            }
                            if (child instanceof Polyline) {
                                ResultModel model = new ResultModel();
                                model.setType("lwPolyline");
                                List<double[]> points = new ArrayList<>();
                                List<String[]> lists = ((Polyline) child).getPoints();
                                for (String[] strings : lists) {
                                    points.add(new double[]{Double.parseDouble(strings[0]), Double.parseDouble(strings[1])});
                                }
                                model.setPoints(points);
                                children.add(model);
                            }
                        }
                        resultModel.setPoint(new double[]{baseX, baseY});
                        resultModel.setChildren(children);
                    }
                    modelMap.put(name, resultModel);
                }
            }

            result.put(key, modelMap);
        }
        return result;
    }
    private Map<String,Layer> sortByLayer(DxfMultiRead read){
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

    private void calBorder(Map<String,Layer> layerMap){
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
        min = new double[]{min[0] - 10, min[1] - 10};
        max = new double[]{max[0] + 10, max[1] + 10};
        List<double[]> rectangle = new ArrayList<>();
        rectangle.add(min);
        rectangle.add(new double[]{max[0],min[1]});
        rectangle.add(max);
        rectangle.add(new double[]{min[0],max[1]});
        Clipping clipping = new Clipping(rectangle);
        for (Map.Entry<String,Layer> map: layerMap.entrySet()){
            Map<String, Entity> entities = map.getValue().getEntities();
            Iterator<Map.Entry<String, Entity>> iterator = entities.entrySet().iterator();
            while (iterator.hasNext()){
                //计算是否与图形关系：在图形内，相交，在图外
                //如果是point，text，circle，根据点坐标判断在矩形内外
                Map.Entry<String, Entity> next = iterator.next();
                String key = next.getKey();
                Entity value = next.getValue();
                if (key.contains("text") || key.contains("point") || key.contains("circle") || key.contains("insert")){
                    if (value instanceof Text){
                        double x = Double.parseDouble(((Text) value).getPointX());
                        double y = Double.parseDouble(((Text) value).getPointY());
                        boolean contain = MathUtil.contain(x, y, rectangle);
                        if (!contain){
                            iterator.remove();
                        }
                    }
                    if (value instanceof Point){
                        double x = Double.parseDouble(((Point) value).getPointX());
                        double y = Double.parseDouble(((Point) value).getPointY());
                        boolean contain = MathUtil.contain(x, y, rectangle);
                        if (!contain){
                            iterator.remove();
                        }
                    }
                    if (value instanceof Circle){
                        double x = Double.parseDouble(((Circle) value).getCenterX());
                        double y = Double.parseDouble(((Circle) value).getCenterY());
                        boolean contain = MathUtil.contain(x, y, rectangle);
                        if (!contain){
                            iterator.remove();
                        }
                    }
                    if (value instanceof Insert){
                       double x = Double.parseDouble(((Insert) value).getPointX());
                       double y = Double.parseDouble(((Insert) value).getPointY());
                       boolean contain = MathUtil.contain(x,y,rectangle);
                       if (!contain){
                           iterator.remove();
                       }
                    }
                }
                //如果是polyline和line，将每个点都进行判断，全在内部，全在外部，部分在内部
                //部分在内部需要二次处理
                if (key.contains("line") || key.contains("polyline") || key.contains("lwPolyline")){
                    if (value instanceof Line){
                        double startX = Double.parseDouble(((Line) value).getStartX());
                        double startY = Double.parseDouble(((Line) value).getStartY());
                        double endX = Double.parseDouble(((Line) value).getEndX());
                        double endY = Double.parseDouble(((Line) value).getEndY());
                        boolean start = MathUtil.contain(startX, startY, rectangle);
                        boolean end = MathUtil.contain(endX, endY, rectangle);
                        Line2D.Double line = new Line2D.Double(startX, startY, endX, endY);
                        if (clipping.clip(line)){
                            ((Line) value).setStartX(String.valueOf(line.x1));
                            ((Line) value).setStartY(String.valueOf(line.y1));
                            ((Line) value).setEndX(String.valueOf(line.x2));
                            ((Line) value).setEndY(String.valueOf(line.y2));
                        } else {
                            iterator.remove();
                        }
                    }
                    if (value instanceof Polyline){
                        List<String[]> pointList = ((Polyline) value).getPointList();
                        Iterator<String[]> points = pointList.iterator();
                        String[] first = points.next();
                        List<String[]> result = new ArrayList<>();
                        double firstX = Double.parseDouble(first[0]);
                        double firstY = Double.parseDouble(first[1]);
                        double lastX = 0;
                        double lastY = 0;
                        while (points.hasNext()){
                            String[] point = points.next();
                            double x = Double.parseDouble(point[0]);
                            double y = Double.parseDouble(point[1]);
                            Line2D.Double line = new Line2D.Double(firstX,firstY,x,y);

                            if (clipping.clip(line)) {
                                result.add(new String[]{String.valueOf(line.x1), String.valueOf(line.y1)});
                                lastX = line.x2;
                                lastY = line.y2;
                            }
                            firstX = line.x2;
                            firstY = line.y2;
                        }
                        if (lastX != 0 && lastY != 0){
                            result.add(new String[]{String.valueOf(lastX),String.valueOf(lastY)});
                        }
                        ((Polyline) value).setPointList(result);
                    }
                    if (value instanceof LwPolyLine){
                        List<String[]> pointList = ((LwPolyLine) value).getPoints();
                        Iterator<String[]> points = pointList.iterator();
                        String[] first = points.next();
                        List<String[]> result = new ArrayList<>();
                        double firstX = Double.parseDouble(first[0]);
                        double firstY = Double.parseDouble(first[1]);
                        double lastX = 0;
                        double lastY = 0;
                        while (points.hasNext()){
                            String[] point = points.next();
                            double x = Double.parseDouble(point[0]);
                            double y = Double.parseDouble(point[1]);
                            Line2D.Double line = new Line2D.Double(firstX,firstY,x,y);

                            if (clipping.clip(line)) {
                                result.add(new String[]{String.valueOf(line.x1), String.valueOf(line.y1)});
                                lastX = line.x2;
                                lastY = line.y2;
                            }
                            firstX = line.x2;
                            firstY = line.y2;
                        }
                        if (lastX != 0 && lastY != 0){
                            result.add(new String[]{String.valueOf(lastX),String.valueOf(lastY)});
                        }
                        ((LwPolyLine) value).setPoints(result);
                    }
                }
            }
        }
        Layer layer = new Layer();
        layer.border = rectangle;
        layerMap.put("border",layer);
    }

    /**
     * 更新最大点和最小点方便确定矩形范围
     * @param min
     * @param max
     * @param target
     */
    public void upgradeData(double[] min, double[] max , double[] target){
        double x = target[0], y = target[1];
        double minx = min[0], miny = min[1];
        double maxx = max[0], maxy = max[1];
        if (minx == 0 ){
            min[0] = x;
        }
        if (miny == 0){
            min[1] = y;
        }
        if(x < minx && x < maxx){
            if (x != 0 && min[0] != 0){
                min[0] = x;
            }
        }else if (x > maxx && x > minx){
            max[0] = x;
        }
        if (y < miny && y < maxy){
            if (y != 0 && min[1] != 0){
                min[1] = y;
            }
        }else if (y > maxy && y > miny){
            max[1] = y;
        }

    }

    /**
     * 更新最大点和最小点方便确定矩形范围
     * @param min
     * @param max
     * @param targetMin
     * @param targetMax
     */
    public void upgradeData(double[] min, double[] max , double[] targetMin,double[] targetMax){
        upgradeData(min,max,targetMin);
        upgradeData(min,max,targetMax);
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
