package dxf;

import java.util.Arrays;
import java.util.List;

public class Block extends Element{
    private String handle;
    private String[] basePoint = new String[3]; //10,20,30
    private String blockName; //2
    private List<Entity> entities;
    private String bl3ckName;

    public Block() {
    }

    public String getBl3ckName() {
        return bl3ckName;
    }

    public void setBl3ckName(String bl3ckName) {
        this.bl3ckName = bl3ckName;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String[] getBasePoint() {
        return basePoint;
    }

    public void setBasePoint(String[] basePoint) {
        this.basePoint = basePoint;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "Block{" +
                "handle='" + handle + '\'' +
                ", layer='" +  + '\'' +
                ", basePoint=" + Arrays.toString(basePoint) +
                ", blockName='" + blockName + '\'' +
                ", entities=" + entities +
                ", bl3ckName='" + bl3ckName + '\'' +
                '}'+"\n";
    }
}
