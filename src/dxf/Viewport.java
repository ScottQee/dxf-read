package dxf;

public class Viewport extends Table{
    private String name; //2
    private int viewportHeight; //40

    public Viewport(String name, int viewportHeight) {
        super();
        this.name = name;
        this.viewportHeight = viewportHeight;
        startTag = new Data(0,"VPORT");
    }

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());
        s.append("5\n").append(super.toHandleString()).append("\n");
        s.append("100\nAcDbSymbolTableRecord\n").append("100\nAcDbViewportTableRecord\n");
        s.append("2\n").append(this.name).append("\n");
        s.append("40\n").append(this.viewportHeight).append("\n");
        s.append("70\n0\n");
        return s.toString();
    }
}
