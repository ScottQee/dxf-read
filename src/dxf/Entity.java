package dxf;

public class Entity extends Element{
    public Entity(String name, String layer){
        super();
        this.startTag = new Data(0,name);
        this.addData(new Data(8,layer));
    }

    public Entity() {
        super();
    }

    public String getLayer(){
        return (String) this.getDataFor(8).getData();
    }
    public void setLayer(String s){
        this.layer = s ;
        this.addReplace(8,s);
    }

    protected String handle;
    protected String layer = "0";
    protected String lineType = "Continuous";
    protected String color = "0";
    protected String lineScale;
    protected final String AcDbEntity = "AcDbEntity";

    public String getAcDbEntity() {
        return AcDbEntity;
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
    @Deprecated
    public void setLineScale(String lineScale) {
        this.lineScale = lineScale;
    }

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());
        s.append("5\n").append(super.toHandleString()).append("\n");
        s.append("100\nAcDbEntity\n");
        s.append("8\n").append(layer).append("\n");
        if (lineType != null && lineType.length() > 0){
            s.append("6\n").append(lineType).append("\n");
        }
        s.append("62\n").append(color).append("\n");
        return s.toString();
    }
}
