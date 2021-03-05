package dxfRead.service;

import dxfRead.Data;
import dxfRead.Entity;
import dxfRead.entities.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MultiRead {

    private BufferedReader br;
    private String[] code_data = new String[2];

    public MultiRead(File f) {

        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
            this.br = new BufferedReader(isr);
            readToEntities();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    private void readToEntities(){
        try{
            String temp = br.readLine();
            while (!temp.equals("ENTITIES")){
                temp = br.readLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Entity readEntities(){
        code_data = readPair();
        LwPolyLine lwPolyLine = null;
        while (!code_data[1].equals("OBJECTS")) {
            if (code_data[0].equals("0")) {
                if (code_data[1].equals("LWPOLYLINE")) {
                    lwPolyLine = readLwPolyline();
                    continue;
                }
            }
            code_data = readPair();
        }
        //待定
        return lwPolyLine;
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
//            if (code_data[0].equals("8")){
//                lwPolyLine.setLayer(code_data[1]);
//                for(Layer l : layerList){
//                    if (l.getValue().equals(code_data[1].trim())){
//                        lwPolyLine.setColor(l.getColor());
//                    }
//                }
//            }
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
}
