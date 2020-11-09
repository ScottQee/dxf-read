package dxf.entities;

import dxf.Entity;

public class Hatch extends Entity {
    public Hatch(String name, String layer) {
        super("HATCH", layer);
    }

    public Hatch() {
    }
    private final String AcDbHatch = "AcDbHatch";
}
