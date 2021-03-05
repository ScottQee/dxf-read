package dxfRead;

public class Entities extends Section{
    public Entities() {
        super("ENTITIES");
    }
    public void addEntity(Entity e){
        this.addElement(e);
    }
}
