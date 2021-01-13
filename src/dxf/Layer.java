package dxf;

import java.util.ArrayList;
import java.util.List;

public class Layer extends Table{
    private String value; //2
    private String color; //62
    private String LTypeName; //6
    private List<Entity> entities = new ArrayList<>(); //当前图层下的图形

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public Layer(String handle, String name, String value, String color, String LTypeName) {
        super(handle);
        this.value = value;
        this.color = color;
        this.LTypeName = LTypeName;
    }

    public Layer() {
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
