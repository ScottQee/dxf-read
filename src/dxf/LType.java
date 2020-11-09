package dxf;

public class LType extends Table{
    private String value;

    public LType(String handle, String name, String value) {
        super(handle);
        this.value = value;
    }

    public LType() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
