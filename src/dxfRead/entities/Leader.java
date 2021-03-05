package dxfRead.entities;

import dxfRead.Entity;

public class Leader extends Entity {
    public Leader(String name, String layer) {
        super("LEADER", layer);
    }

    public Leader() {
    }
}
