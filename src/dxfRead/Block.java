package dxfRead;

import java.util.Arrays;
import java.util.List;

public class Block extends Element{
    private String handle;
    private String[] basePoint = new String[3]; //10,20,30
    private String blockName; //2
    private List<Entity> entities;
    private String bl3ckName;

    public Block() {
        startTag = new Data(0,"BLOCK");
    }

    public String getBl3ckName() {
        return bl3ckName;
    }

    public void setBl3ckName(String bl3ckName) {
        this.bl3ckName = bl3ckName;
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

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());
        s.append("5\n").append(super.toHandleString()).append("\n");
        s.append("100\nAcDbEntity\n").append("8\n0\n");
        s.append("100\nAcDbBlockBegin\n");
        s.append("2\n").append(blockName).append("\n");
        s.append("70\n0\n10\n0.0\n20\n0.0\n30\n0.0\n");
        s.append("3\n").append(bl3ckName).append("\n").append("1\n \n");
        DatabaseObject x = new DatabaseObject();
        s.append("0\nENDBLK\n").append("5\n").append(x.toHandleString()).append("\n");
        s.append("100\nAcDbEntity\n").append("8\n0\n");
        s.append("100\nAcDbBlockEnd\n");
        return s.toString();
    }
}
