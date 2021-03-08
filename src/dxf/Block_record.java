package dxf;

public class Block_record extends Table{
    private String name;

    public Block_record(String name) {
        super();
        startTag = new Data(0,"BLOCK_RECORD");
        this.name = name;
    }
    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());
        s.append("5\n").append(super.toHandleString()).append("\n");
        s.append("100\nAcDbSymbolTableRecord\n").append("100\nAcDbBlockTableRecord\n");
        s.append("2\n").append(name).append("\n");
        s.append("70\n0\n").append("280\n1\n").append("281\n0\n");
        return s.toString();
    }
}
