package dxf;

public class Table extends Element{


    protected String handle; //5

    public Table(String handle) {
        super();
        startTag = new Data(0,"TABLE");
        endTag = new Data(0,"ENDTAB");
        this.handle = handle;
    }
    public Table() {
        super();
        startTag = new Data(0,"TABLE");
        endTag = new Data(0,"ENDTAB");
    }



    public void setHandle(String handle) {
        this.handle = handle;
    }

    @Override
    public String toDxfString() {
        this.addData(5,super.toHandleString());
        this.addData(100,"AcDbSymbolTable");
        this.addData(70,this.elements.size());
        StringBuilder s = new StringBuilder();
        s.append(startTag.getCode_Date());

        if (data.size() > 0){
            for (int i = 0 ; i < data.size(); i++){
                Data data = (Data) this.data.get(i);
                s.append(data.getCode_Date());
            }
        }
        if (elements.size() > 0){
            for (int i = 0; i < elements.size(); i++){
                Element element = (Element) elements.get(i);
                s.append(element.toDxfString());
            }
        }
        s.append(endTag.getCode_Date());
        return s.toString();
    }

    @Override
    public String toHandleString() {
        return super.toHandleString();
    }
}
