package dxf.entities;

import dxf.Entity;

public class Leader extends Entity {
    public Leader(String name, String layer) {
        super("LEADER", layer);
    }

    public Leader() {
        super("LEADER","0");
    }
}
