package dxf;


import dxf.entities.*;
import javafx.beans.DefaultProperty;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Document {
    private DxfHeader header;
    private Section classes;
    private Section tables;
    private Section blocks;
    private Entities entities;
    private Section objects;
    private DxfHeader dxfheader;
    private Table lTypes;
    private Table layers;
    private Table vPort;
    private Table style;
    private Table view;
    private Table ucs;
    private Table appId;
    private DimStyleTable dimStyle;
    private Table block_record;

    public Entities getEntities() {
        return entities;
    }

    public void setEntities(Entities entities) {
        this.entities = entities;
    }

    public Table getlTypes() {
        return lTypes;
    }

    public void setlTypes(Table lTypes) {
        this.lTypes = lTypes;
    }

    public Table getLayers() {
        return layers;
    }

    public void setLayers(Table layers) {
        this.layers = layers;
    }

    /**
     * dxf输出样式模板
     * @throws IOException
     */
    public Document() throws IOException {
        this.header = new DxfHeader();
        this.classes = new Section("CLASSES");
        this.tables = new Section("TABLES");
        this.blocks = new Section("BLOCKS");
        this.entities = new Entities();
        this.objects = new Section("OBJECTS");
        HeadUnit headUnit = new HeadUnit();
        headUnit.name = "$ACADVER";
        headUnit.code = "1";
        headUnit.value = "AC1021";
        header.getPairs().add(headUnit);
        lTypes = new Table();
        lTypes.addData(2,"LTYPE");
        layers = new Table();
        layers.addData(2,"LAYER");
        vPort = new Table();
        vPort.addData(2,"VPORT");
        style = new Table();
        style.addData(2,"STYLE");
        view = new Table();
        view.addData(2,"VIEW");
        ucs = new Table();
        ucs.addData(2,"UCS");
        appId = new Table();
        appId.addData(2,"APPID");
        dimStyle = new DimStyleTable();
        dimStyle.addData(2,"DIMSTYLE");
        block_record = new Table();
        block_record.addData(2,"BLOCK_RECORD");
        tables.addElement(vPort);
        tables.addElement(lTypes);
        tables.addElement(layers);
        tables.addElement(style);
        tables.addElement(view);
        tables.addElement(ucs);
        tables.addElement(appId);
        tables.addElement(dimStyle);
        tables.addElement(block_record);
        Viewport viewport = new Viewport("*Active",1000);
        vPort.addElement(viewport);
        LType byBlock = new LType("ByBlock");
        lTypes.addElement(byBlock);
        LType byLayer  = new LType("ByLayer");
        lTypes.addElement(byLayer);
        LType continuous = new LType("Continuous");
        continuous.setDescription("Solid line");
        lTypes.addElement(continuous);
        Layer zero = new Layer("0","0","Continuous");
        layers.addElement(zero);
        AppID ACAD = new AppID("ACAD");
        appId.addElement(ACAD);
        Block_record model_space = new Block_record("*Model_Space");
        block_record.addElement(model_space);
        Block_record paper_space = new Block_record("*Paper_Space");
        block_record.addElement(paper_space);
        Block Bmodel_Space = new Block();
        Bmodel_Space.setBlockName("*Model_Space");
        Bmodel_Space.setBl3ckName("*Model_Space");
        blocks.addElement(Bmodel_Space);
        Block Bpaper_Space = new Block();
        Bpaper_Space.setBlockName("*Paper_Space");
        Bpaper_Space.setBl3ckName("*Paper_Space");
        blocks.addElement(Bpaper_Space);
        Dictionary dictionary = new Dictionary("",0);
        this.objects.addElement(dictionary);
        Dictionary childDictionary = new Dictionary("ACAD_GROUP", dictionary.getHandle());
        dictionary.addChildren(childDictionary);
    }
    public void addLType(LType lType){
        lTypes.addElement(lType);
    }
    public void addLayer(Layer layer){
        layers.addElement(layer);
    }
    public void addEntity(Entity entity){
        entities.addEntity(entity);
    }
    public void write(String path) throws IOException {
        StringBuilder s = new StringBuilder();
        s.append(header.toString());
        s.append(classes.toDxfString());
        s.append(tables.toDxfString());
        s.append(blocks.toDxfString());
        s.append(entities.toDxfString());
        s.append(objects.toDxfString());
        s.append("0\nEOF\n");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
        writer.write(s.toString());
        writer.flush();
        writer.close();
    }

    public void drawLine(double[] point1,double[] point2, String layer,String color) throws Exception {
            Line line = new Line(parse(point1),parse(point2),layer);
            line.setColor(color);
            addEntity(line);
    }
    public void drawLine(double[] point1,double[] point2, String layer) throws Exception {
        drawLine(point1,point2,layer,"0");
    }
    public void drawLine(double[] point1,double[] point2) throws Exception {
        drawLine(point1,point2,"0","0");
    }
    public void drawCircle(double[] point, double radius, String layer,String color) throws Exception {
        Circle circle = new Circle(parse(point), String.valueOf(radius), layer);
        circle.setColor(color);
        addEntity(circle);
    }
    public void drawCircle(double[] point, double radius,String layer) throws Exception {
        drawCircle(point, radius, layer,"0");
    }
    public void drawCircle(double[] point, double radius) throws Exception {
        drawCircle(point, radius,"0","0");
    }
    public void drawArc(double[] point, double radius, double start_Angle, double end_Angle,String layer, String color) throws Exception {
        Arc arc = new Arc(parse(point), String.valueOf(radius), String.valueOf(start_Angle), String.valueOf(end_Angle), layer);
        arc.setColor(color);
        addEntity(arc);
    }
    public void drawPolylineS(List<String[]> points,String layer, String color){
        LwPolyLine lwPolyLine = new LwPolyLine(layer);
        lwPolyLine.setPoints(points);
        lwPolyLine.setPointCount(String.valueOf(points.size()));
        lwPolyLine.setColor(color);
        addEntity(lwPolyLine);
    }
    public void drawPolyline(List<double[]> points,String layer,String color){
        List<String[]> list = new ArrayList<>();
        for (int i = 0 ; i < points.size();i++){
            double[] doubles = points.get(i);
            list.add(new String[]{String.valueOf(doubles[0]),String.valueOf(doubles[1])});
        }
        drawPolylineS(list,layer,color);
    }
    public void drawPolyline(List<double[]> points,String layer){
        drawPolyline(points,layer,"0");
    }
    public void drawPolyline(List<double[]> points){
        drawPolyline(points,"0","0");
    }
    public void drawPoint(double[] points,String layer,String color) throws Exception {
        Point point = new Point(parse(points),layer);
        point.setColor(color);
        addEntity(point);
    }
    public void drawPoint(double[] points,String layer) throws Exception {
       drawPoint(points, layer,"0");
    }
    public void drawPoint(double[] points) throws Exception {
        drawPoint(points,"0","0");
    }
    public void drawText(String value, double[] point,double angle,String layer,String color) throws Exception {
        Text text = new Text(parse(point), value, String.valueOf(angle), layer);
        text.setColor(color);
        addEntity(text);
    }
    public void drawText(String value, double[] point,double angle,String layer) throws Exception {
        drawText(value, point, angle, layer,"0");
    }
    public void drawText(String value, double[] point,double angle) throws Exception {
        drawText(value, point, angle,"0","0");
    }
    public void drawText(String value, double[] point) throws Exception {
        drawText(value, point, 0.0,"0","0");
    }
    private static String[] parse(double[] points){
        String[] result = new String[points.length];
        for (int i = 0 ; i < points.length; i++){
            result[i] = String.valueOf(points[i]);
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        Document document = new Document();
        document.drawCircle(new double[]{1.2,2.3},4.2);
        document.drawPoint(new double[]{1.0,3.0},"0",ACI_Color.Blue);
        document.drawLine(new double[]{1.2,3.5},new double[]{4.4,6.1});
        document.drawText("就服你123456",new double[]{1.0,1.0},90.0);
        List list = new ArrayList();
        list.add(new double[]{10.0,12.0});
        list.add(new double[]{11.0,13.0});
        list.add(new double[]{17.0,13.0});
        list.add(new double[]{10.0,12.0});
        document.drawPolyline(list);
        document.write("D:/new1.dxf");
    }

}
