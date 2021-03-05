package dxfRead.service;

import dxfRead.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DxfWrite {
    private DxfHeader header;
    private Section classes;
    private Section tables;
    private Section blocks;
    private Entities entities;
    private Section objects;
    private DxfHeader dxfheader;
    private BufferedWriter writer;

    public DxfWrite(File f) throws IOException {
        this.header = new DxfHeader();
        this.classes = new Section("CLASSED");
        this.tables = new Section("TABLES");
        this.blocks = new Section("BLOCKS");
        this.entities = new Entities();
        this.objects = new Section("OBJECTS");
        File file;
        writer = new BufferedWriter(new FileWriter(f));
    }
   private void generateAcadExtras(){
       HeadUnit headUnit = new HeadUnit();
       headUnit.name = "$ACADVER";
       headUnit.code = "1";
       headUnit.value = "AC1021";
       header.getPairs().add(headUnit);
       Table viewportTable = new Table("VPORT");
       tables.addElement(viewportTable);
   }




    public static void main(String[] args) {

    }
}
