package dxf;

public class AppID extends Table{
    private String name;
    public AppID(String name){
        super();
        this.name = name;
        startTag = new Data(0,"APPID");
    }

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());
        s.append("5\n").append(super.toHandleString()).append("\n");
        s.append("100\nAcDbSymbolTableRecord\n").append("100\nAcDbRegAppTableRecord\n");
        s.append("2\n").append(name).append("\n");
        s.append("70\n0\n");
        return s.toString();
    }
}
