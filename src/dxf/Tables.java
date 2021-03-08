package dxf;

public class Tables extends Section {
    public Tables(){
        super("TABLES");
    }
    public void addTable(Table e){
        this.addElement(e);
    }
}
