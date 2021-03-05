package dxfRead.service;

import dxfRead.*;
import dxfRead.entities.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DxfMultiRead {
    public List<Point> pointList;
    public List<Circle> circleList;
    public List<Line> lineList;
    public List<LwPolyLine> lwPolylineList;
    public List<Text> textList;
    public List<Polyline> polylineList;
    public List<Insert> insertList;
    public List<LType> lTypeList;
    public List<Layer> layerList;
    public List<Block> blockList;
    public List<Hatch> hatchList;
    private BufferedReader br;
    private String[] code_data = new String[2];

    /**
     * 统计dxf各实体类型，暂不需要使用，供含有大量数据的dxf文件使用
     * @param f .dxf文件位置
     */
    public DxfMultiRead(File f) {
        pointList = new ArrayList<>();
        circleList = new ArrayList<>();
        lineList = new ArrayList<>();
        lwPolylineList = new ArrayList<>();
        textList = new ArrayList<>();
        polylineList = new ArrayList<>();
        insertList = new ArrayList<>();
        lTypeList = new ArrayList<>();
        layerList = new ArrayList<>();
        blockList = new ArrayList<>();
        hatchList = new ArrayList<>();

        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(f), Charset.forName("GBK"));
            this.br = new BufferedReader(isr);
            readToTable();
            readTables();
            readBlocks();
            readEntities();
            isr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
//    public void readToEntities(){
//        try{
//            String temp = br.readLine();
//            while (!temp.equals("ENTITIES")){
//                temp = br.readLine();
//            }
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    private String[] readPair(){
        String[] result = null;
        try {
            String code = br.readLine().trim();
            String code_data = br.readLine().trim();
            result = new String[]{code,code_data};
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }
    private void readToTable(){
        try{
            String temp = br.readLine();
            while (!temp.equals("TABLES")){
                temp = br.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    //Table段开始
    private void readTables(){
        code_data = readPair();
        while (!code_data[1].equals("BLOCKS")){
            if (code_data[0].equals("0")){
                if (code_data[1].equals("LTYPE")){
                    readLType();
                    continue;
                }
                if (code_data[1].equals("LAYER")){
                    readLayer();
                    continue;
                }
            }
            code_data = readPair();
        }
    }

    private void readLayer() {
        Layer layer = new Layer();
        do {
            code_data = readPair();
            if (code_data[0].equals("5")){
                layer.setHandle(code_data[1]);
            }
            if (code_data[0].equals("2")){
                layer.setValue(code_data[1]);
            }
            if (code_data[0].equals("62")){
                int i = Integer.parseInt(code_data[1].trim());
                if (i < 0){
                    continue;
                }
                RGB color = ACI_Color.CadColor[i];
                layer.setColor(color.toString());
            }
            if (code_data[0].equals("6")){
                layer.setLTypeName(code_data[1]);
            }
        }while (!code_data[0].equals("0"));
        layerList.add(layer);
    }

    private void readLType() {
        LType lType = new LType();
        do {
            code_data = readPair();
            if (code_data[0].equals("5")) {
                lType.setHandle(code_data[1]);
            }
            if (code_data[0].equals("2")){
                lType.setValue(code_data[1]);
            }
        }while (!code_data[0].equals("0"));
        lTypeList.add(lType);
    }
    private void readBlocks(){
        code_data = readPair();
        while (!code_data[1].equals("ENTITIES")){
            if(code_data[0].equals("0")){
                if (code_data[1].equals("BLOCK")){
                    readBlock();
                    continue;
                }
            }
            code_data = readPair();
        }
    }

    private void readBlock() {
        Block block = new Block();
        String[] basePoint = new String[3];
        List<Entity> entities = new ArrayList<>();
        code_data = readPair();
        while(!(code_data[0].equals("0") && code_data[1].equals("ENDBLK"))){
            if (code_data[0].equals("5")){
                block.setHandle(code_data[1]);
            }
            if (code_data[0].equals("10")){
                basePoint[0] = code_data[1];
            }
            if (code_data[0].equals("20")){
                basePoint[1] = code_data[1];
            }
            if (code_data[0].equals("30")){
                basePoint[2] = code_data[1];
            }
            if (code_data[0].equals("2")){
                block.setBlockName(code_data[1]);
            }
            if (code_data[0].equals("3")){
                block.setBl3ckName(code_data[1]);
            }
            if (code_data[0].equals("0")) {

                if (code_data[1].equals("LWPOLYLINE")) {
                    LwPolyLine lwPolyLine = readLwPolyline();
                    entities.add(lwPolyLine);
                    continue;
                }
                if (code_data[1].equals("POINT")) {
                    Point point = readPoint();
                    entities.add(point);
                    continue;
                }
                if (code_data[1].equals("TEXT")) {
                    Text text = readText();
                    entities.add(text);
                    continue;
                }
                if (code_data[1].equals("LINE")) {
                    Line line = readLine();
                    entities.add(line);
                    continue;
                }
                if (code_data[1].equals("POLYLINE")) {
                    Polyline polyline = readPolyline();
                    entities.add(polyline);
                    continue;
                }
                if (code_data[1].equals("INSERT")) {
                    Insert insert = readInsert();
                    entities.add(insert);
                    continue;
                }
                if (code_data[1].equals("CIRCLE")) {
                    Circle circle = readCircle();
                    entities.add(circle);
                    continue;
                }
                if (code_data[1].equals("HATCH")){
                    Hatch hatch = readHatch();
                    entities.add(hatch);
                    continue;
                }
            }
            code_data = readPair();
        }
        block.setEntities(entities);
        block.setBasePoint(basePoint);
        blockList.add(block);
    }

    //Entity段开始
    private void readEntities(){
        code_data = readPair();
        while (!code_data[1].equals("ENDSEC")) {
            if (code_data[0].equals("0")) {
                if (code_data[1].equals("LWPOLYLINE")) {
                    lwPolylineList.add(readLwPolyline());
                    continue;
                }
                if (code_data[1].equals("POINT")) {
                    pointList.add(readPoint());
                    continue;
                }
                if (code_data[1].equals("TEXT")) {
                    textList.add(readText());
                    continue;
                }
                if (code_data[1].equals("LINE")) {
                    lineList.add(readLine());
                    continue;
                }
                if (code_data[1].equals("POLYLINE")) {
                    polylineList.add(readPolyline());
                    continue;
                }
                if (code_data[1].equals("INSERT")){
                    insertList.add(readInsert());
                    continue;
                }
                if (code_data[1].equals("CIRCLE")){
                    circleList.add(readCircle());
                    continue;
                }
                if (code_data[1].equals("HATCH")){
                    hatchList.add(readHatch());
                    continue;
                }
            }
            code_data = readPair();
        }
    }
    private LwPolyLine readLwPolyline(){
        LwPolyLine lwPolyLine = new LwPolyLine();
        int count = 2;
        String[] end = new String[2];
        do{
            code_data = readPair();
            if (code_data[0].equals("5")){
                lwPolyLine.setHandle(code_data[1]);
            }
            if (code_data[0].equals("8")){
                lwPolyLine.setLayer(code_data[1]);
                for(Layer l : layerList){
                    if (l.getValue().equals(code_data[1].trim())){
                        lwPolyLine.setColor(l.getColor());
                    }
                }
            }
            if (code_data[0].equals("6")){
                lwPolyLine.setLineType(code_data[1]);
            }
            if (code_data[0].equals("90")){
                lwPolyLine.setPointCount(code_data[1]);
                count = Integer.parseInt(code_data[1]);
            }
            if (code_data[0].equals("10")){
//                String[] Xs = new String[count + 1];
//                String[] Ys = new String[count + 1];

                for (int i = 0 ; i < count; i++){
//                    Xs[i] = code_data[1];
//                    code_data = readPair();
//                    Ys[i] = code_data[1];
//                    code_data = readPair();
                    String[] XY = new String[2];
                    XY[0] = code_data[1];
                    code_data = readPair();
                    XY[1] = code_data[1];
                    if(i == 0){
                        end = XY;
                    }
                    code_data = readPair();
                    if (code_data[0].equals("40")){
                        code_data = readPair();
                    }
                    lwPolyLine.getPoints().add(XY);
                }
//                lwPolyLine.setPointX(Xs);
//                lwPolyLine.setPointY(Ys);

                if(code_data[0].equals("0")){
                    break;
                }
            }
            lwPolyLine.addData(new Data(Integer.parseInt(code_data[0]),code_data[1]));
        }while (!code_data[0].equals("0"));
        if (lwPolyLine.getLayer().equals("居民地") || lwPolyLine.getLayer().equals("JZD")){
            lwPolyLine.getPoints().add(end);
        }
        return lwPolyLine;
    }
    private Point readPoint(){
        Point point = new Point();
        do {
            code_data = readPair();
            if (code_data[0].equals("5")){
                point.setHandle(code_data[1]);
            }
            if(code_data[0].equals("8")){
                point.setLayer(code_data[1]);
                for(Layer l : layerList){
                    if (l.getValue().equals(code_data[1].trim())){
                        point.setColor(l.getColor());
                    }
                }
            }
            if (code_data[0].equals("10")){
                point.setPointX(code_data[1]);
            }
            if (code_data[0].equals("20")){
                point.setPointY(code_data[1]);
            }
            if (code_data[0].equals("30")){
                point.setPointZ(code_data[1]);
            }
            point.addData(new Data(Integer.parseInt(code_data[0]),code_data[1]));
        }while (!code_data[0].equals("0"));
        return point;
    }
    private Circle readCircle(){
        Circle circle = new Circle();
        do {
            code_data = readPair();
            if (code_data[0].equals("5")){
                circle.setHandle((code_data[1]));
            }
            if(code_data[0].equals("8")){
                circle.setLayer(code_data[1]);
                for(Layer l : layerList){
                    if (l.getValue().equals(code_data[1].trim())){
                        circle.setColor(l.getColor());
                    }
                }
            }
            if (code_data[0].equals("6")){
                circle.setLineType(code_data[1]);
            }
            if (code_data[0].equals("10")){
                circle.setCenterX(code_data[1]);
            }
            if (code_data[0].equals("20")){
                circle.setCenterY(code_data[1]);
            }
            if (code_data[0].equals("30")){
                circle.setCenterZ(code_data[1]);
            }
            if (code_data[0].equals("40")){
                circle.setRadius(code_data[1]);
            }
        }while (!code_data[0].equals("0"));
        return circle;
    }
    private Text readText(){
        Text text = new Text();
        do {
            code_data = readPair();
            if(code_data[0].equals("5")){
                text.setHandle(code_data[1]);
            }
            if(code_data[0].equals("8")){
                text.setLayer(code_data[1]);
                for(Layer l : layerList){
                    if (l.getValue().equals(code_data[1].trim())){
                        text.setColor(l.getColor());
                    }
                }
            }
            if (code_data[0].equals("10")){
                text.setPointX(code_data[1]);
            }
            if (code_data[0].equals("20")){
                text.setPointY(code_data[1]);
            }
            if (code_data[0].equals("30")){
                text.setPointZ(code_data[1]);
            }
            if (code_data[0].equals("1")){
                text.setValue(code_data[1]);
            }
            if (code_data[0].equals("11")){
                text.setSecondPointX(code_data[1]);
            }
            if (code_data[0].equals("21")){
                text.setSecondPointY(code_data[1]);
            }
            if (code_data[0].equals("31")){
                text.setSecondPointZ(code_data[1]);
            }
            text.addData(new Data(Integer.parseInt(code_data[0]),code_data[1]));
        }while (!code_data[0].equals("0"));
        return text;
    }
    private Line readLine(){
        Line line = new Line();
        do {
            code_data = readPair();
            if(code_data[0].equals("5")){
                line.setHandle(code_data[1]);
            }
            if(code_data[0].equals("8")){
                line.setLayer(code_data[1]);
                for(Layer l : layerList){
                    if (l.getValue().equals(code_data[1].trim())){
                        line.setColor(l.getColor());
                    }
                }
            }
            if(code_data[0].equals("6")){
                line.setLineType(code_data[1]);
            }
            if (code_data[0].equals("10")){
                line.setStartX(code_data[1]);
            }
            if (code_data[0].equals("20")){
                line.setStartY(code_data[1]);
            }
            if (code_data[0].equals("30")){
                line.setStartZ(code_data[1]);
            }
            if (code_data[0].equals("11")){
                line.setEndX(code_data[1]);
            }
            if (code_data[0].equals("21")){
                line.setEndY(code_data[1]);
            }
            if (code_data[0].equals("31")){
                line.setEndZ(code_data[1]);
            }
            line.addData(new Data(Integer.parseInt(code_data[0]),code_data[1]));
        }while (!code_data[0].equals("0"));
        return line;
    }
    private Polyline readPolyline(){
        Polyline polyline = new Polyline();
        do {
            code_data = readPair();
            if (code_data[0].equals("5")){
                polyline.setHandle(code_data[1]);
            }
            if (code_data[0].equals("8")){
                polyline.setLayer(code_data[1]);
                for(Layer l : layerList){
                    if (l.getValue().equals(code_data[1].trim())){
                        polyline.setColor(l.getColor());
                    }
                }
            }
            if(code_data[0].equals("6")){
                polyline.setLineType(code_data[1]);
            }
        }while (!code_data[0].equals("0"));
        while (code_data[1].equals("VERTEX")){
            Vertex vertex = new Vertex();
            do {
                code_data = readPair();
                if(code_data[0].equals("5")){
                    vertex.setHandle(code_data[1]);
                }
                if(code_data[0].equals("8")){
                    vertex.setLayer(code_data[1]);
                }
                if (code_data[0].equals("10")){
                    vertex.setPointX(code_data[1]);
                }
                if (code_data[0].equals("20")){
                    vertex.setPointY(code_data[1]);
                }
                if (code_data[0].equals("30")){
                    vertex.setPointZ(code_data[1]);
                }
                vertex.addData(new Data(Integer.parseInt(code_data[0]),code_data[1]));
            }while (!code_data[0].equals("0"));
            polyline.getVPointList().add(vertex);
            if (polyline.getLayer().equals("居民地") || polyline.getLayer().equals("JZD")){
                polyline.getPointList().add(polyline.getPointList().get(0));
            }
        }
        return polyline;
    }
    private Insert readInsert(){
        Insert insert = new Insert();
        do {
            code_data = readPair();
            if(code_data[0].equals("5")){
                insert.setHandle(code_data[1]);
            }
            if (code_data[0].equals("2")){
                String blockName = code_data[1];
                insert.setBlockName(blockName);
                for (Block block : blockList){
                    if (block.getBlockName().equals(blockName)){
                        insert.setEntities(block.getEntities());
                    }
                }
            }
            if(code_data[0].equals("8")){
                insert.setLayer(code_data[1]);
            }
            if (code_data[0].equals("10")){
                insert.setPointX(code_data[1]);
            }
            if (code_data[0].equals("20")){
                insert.setPointY(code_data[1]);
            }
            if (code_data[0].equals("30")){
                insert.setPointZ(code_data[1]);
            }
            if (code_data[0].equals("41")){
                insert.setPointX_Scale(code_data[1]);
            }
            if (code_data[0].equals("42")){
                insert.setPointY_Scale(code_data[1]);
            }
            if (code_data[0].equals("43")){
                insert.setPointZ_Scale(code_data[1]);
            }
            insert.addData(new Data(Integer.parseInt(code_data[0]),code_data[1]));
        }while (!code_data[0].equals("0"));
        return insert;
    }
    private Hatch readHatch(){
        Hatch hatch = new Hatch();
        hatch.setList(new ArrayList<>());
        do {
            code_data = readPair();
            if (code_data[0].equals("5")){
                hatch.setHandle(code_data[1]);
            }
            if (code_data[0].equals("8")){
                hatch.setLayer(code_data[1]);
            }
            if (code_data[0].equals("10")){
                hatch.setBaseX(code_data[1]);
            }
            if (code_data[0].equals("20")){
                hatch.setBaseY(code_data[1]);
            }
            if (code_data[0].equals("30")){
                hatch.setBaseZ(code_data[1]);
            }
            if (code_data[0].equals("2")){
                hatch.setHatchName(code_data[1]);
            }
            if (code_data[0].equals("91")){
                hatch.setPathNum(code_data[1]);
            }
            if (code_data[0].equals("73")){
                PathLwpolyline pathLwpolyline  = new PathLwpolyline();
                pathLwpolyline.isClose = code_data[1];
                code_data = readPair();
                pathLwpolyline.pointNum = code_data[1];
                code_data = readPair();
                for (int i = 0; i < Integer.parseInt(pathLwpolyline.pointNum); i++){
                    String[] point = new String[2];
                    point[0] = code_data[1];
                    code_data = readPair();
                    point[1] = code_data[1];
                    code_data = readPair();
                    pathLwpolyline.points.add(point);
                    if (code_data[0].equals("42")){
                        code_data = readPair();
                    }
                }
                hatch.getList().add(pathLwpolyline);
            }
        }while (!code_data[0].equals("0"));
        return hatch;
    }

}
