package dxf;

public class Entity extends Element{
    public Entity(String name, String layer){
        super();
        this.startTag = new Data(0,name);
        this.addData(new Data(8,layer));
    }

    public Entity() {

    }

    public String getLayer(){
        return (String) this.getDataFor(8).getData();
    }
    public void setLayer(String s){
        this.layer = s ;
        this.addReplace(8,s);
    }

    protected String handle;
    protected String layer;
    protected String lineType;
    protected String color;
    protected String lineScale;
    protected final String AcDbEntity = "AcDbEntity";

    public String getAcDbEntity() {
        return AcDbEntity;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLineScale() {
        return lineScale;
    }

    public void setLineScale(String lineScale) {
        this.lineScale = lineScale;
    }
}
