package dxf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Layer extends Table{
    private String value; //2
    private String color; //62
    private String LTypeName; //6
    private Map<String,Entity> entities = new HashMap<>(); //当前图层下的图形
    public List<double[]> border;

    public Map<String,Entity> getEntities() {
        return entities;
    }

    public void setEntities(Map<String,Entity> entities) {
        this.entities = entities;
    }

    public Layer(String handle, String name, String value, String color, String LTypeName) {
        super(handle);
        this.value = value;
        this.color = color;
        this.LTypeName = LTypeName;
        startTag = new Data(0,"LAYER");
    }
    public Layer(String value, String color, String LTypeName){
        super();
        this.value = value;
        this.color = color;
        this.LTypeName = LTypeName;
        startTag = new Data(0,"LAYER");
    }
    public Layer() {
        super();
    }

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());
        s.append("5\n").append(super.toHandleString()).append("\n");
        s.append("100\nAcDbSymbolTableRecord\n");
        s.append("100\nAcDbLayerTableRecord\n");
        s.append("2\n").append(value).append("\n");
        s.append("70\n0\n");
        s.append("63\n").append(color).append("\n");
        s.append("390\n1\n");

        return s.toString();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLTypeName() {
        return LTypeName;
    }

    public void setLTypeName(String LTypeName) {
        this.LTypeName = LTypeName;
    }

}
