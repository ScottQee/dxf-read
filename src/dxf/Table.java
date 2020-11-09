package dxf;

public class Table extends Element{


    protected String handle; //5

    public Table(String handle) {
        super();
        this.handle = handle;
    }

    public Table() {
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

}
