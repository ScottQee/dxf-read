package dxf;

public class DimStyle extends Table {
    private String name;
    public DimStyle(String name){
        super();
        startTag = new Data(0,"DIMSTYLE");
        this.name = name;
    }

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());
        s.append("5\n").append(super.toHandleString()).append("\n");
        s.append("100\nAcDbSymbolTableRecord\n").append("100\nAcDbDimStyleTableRecord\n");
        s.append("2\n").append(name).append("\n");
        s.append("70\n0\n");
        return s.toString();
    }
}
