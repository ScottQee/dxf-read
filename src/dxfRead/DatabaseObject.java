package dxfRead;

public class DatabaseObject {
    private static int handleCount = 1;
    public int handle;

    DatabaseObject() {
        this.handle = handleCount++;
    }

    public String toHandleString() {
        return Integer.toHexString(this.handle);
    }

    int getHandle() {
        return this.handle;
    }

    public static int getHandleCount() {
        return handleCount;
    }
}
