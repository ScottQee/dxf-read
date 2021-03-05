package dxfRead;

import java.util.ArrayList;
import java.util.List;

public class Dictionary extends Element{
    public String name;
    private int ownerHandle;
    private DatabaseObject myDatabaseObject;
    private List<Dictionary> children;
    public Dictionary(String name, int ownerHandle){
        this.name = name;
        this.ownerHandle = ownerHandle;
        this.myDatabaseObject = new DatabaseObject();
        children = new ArrayList<>();
    }

    public void addChildren(Dictionary dictionary){
        this.children.add(dictionary);
    }
    public String getName() {
        return this.name;
    }

    int getHandle() {
        return this.myDatabaseObject.getHandle();
    }

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append("0\nDICTIONARY\n");
        s.append("5\n").append(this.myDatabaseObject.toHandleString()).append("\n");
        s.append("330\n").append(Integer.toHexString(this.ownerHandle)).append("\n");
        s.append("100\nAcDbDictionary\n").append("281\n1\n");

        int i;
        for(i = 0; i < this.children.size(); ++i) {
            s.append("3\n").append(this.children.get(i).getName()).append("\n");
            s.append("350\n").append(Integer.toHexString(this.children.get(i).getHandle())).append("\n");
        }

        for(i = 0; i < this.children.size(); ++i) {
            s.append(this.children.get(i).toDxfString());
        }
        return s.toString();
    }
}
