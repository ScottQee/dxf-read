package dxf;


import dxf.entities.*;

import java.io.*;
import java.util.ArrayList;
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
        Layer zero = new Layer("0","1","Continuous");
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
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path),"GBK"));
        writer.write(s.toString());
        writer.flush();
        writer.close();
    }
    public static void main(String[] args) throws IOException {
        Document document = new Document();
        Circle circle = new Circle("0");
        circle.setCenterX("1.0");
        circle.setCenterY("3.0");
        circle.setCenterZ("0.0");
        circle.setRadius("5.5");
        document.addEntity(circle);
        Text text = new Text("0");
        text.setPointX("1.0");
        text.setPointY("3.0");
        text.setPointZ("0.0");
        text.setValue("123456");
        document.addEntity(text);
        LwPolyLine lwPolyline = new LwPolyLine();
        List<String[]> points = new ArrayList<>();
        points.add(new String[]{"4.0","10.0"});
        points.add(new String[]{"20.0","1.0"});
        points.add(new String[]{"3.0","8.0"});
        points.add(new String[]{"4.0","10.0"});
        lwPolyline.setPointCount(String.valueOf(points.size()));
        lwPolyline.setPoints(points);
        document.addEntity(lwPolyline);
        document.write("D:/new.dxf");
    }

}
