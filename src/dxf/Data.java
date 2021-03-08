package dxf;

public class Data {
    private int code;
    private Object data;

    public Data(int code, Object data) {
        this.code = code;
        this.data = data;
    }
    public String getCode_Date(){
        return code + "\n" + data + "\n";
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }
}
