package dxfRead;

public class LType extends Table{
    private String value; //2
    private String description="";//3
    private String num = "0"; //73
    private String length="0.0"; //40
    private String[] unitLength; //49
    public LType(String handle, String name, String value) {
        super(handle);
        this.value = value;
    }
    public LType(String name){
        super();
        this.value = name;
        startTag = new Data(0,"LTYPE");
    }
    public LType() {
        super();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toDxfString() {
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());
        s.append("5\n").append(super.toHandleString()).append("\n");
        s.append("100\nAcDbSymbolTableRecord\n").append("100\nAcDbLinetypeTableRecord\n");
        s.append("2\n").append(value).append("\n");
        s.append("70\n0\n");
        s.append("3\n").append(description).append("\n");
        s.append("72\n65\n");
        s.append("73\n").append(num).append("\n");
        s.append("40\n").append(length).append("\n");
        if (unitLength !=null && unitLength.length > 0){
            for (int i = 0; i < unitLength.length; i++ ){
                s.append("49\n").append(unitLength[i]).append("\n");
                s.append("74\n0\n");
            }
        }
        return s.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String[] getUnitLength() {
        return unitLength;
    }

    public void setUnitLength(String[] unitLength) {
        this.unitLength = unitLength;
    }
}
