package dxf;

import java.util.ArrayList;
import java.util.List;

public  class Element extends DatabaseObject{
    public Data startTag = new Data(-10, 0);
    public Data endTag = new Data(-10, 0);
    protected List data;
    protected List elements;
    protected List dataAcceptanceList;
    protected List elementAcceptanceList;
    public String s;

    protected Element() {
        super();
        data = new ArrayList();
        elements = new ArrayList();
        dataAcceptanceList = new ArrayList();
        elementAcceptanceList = new ArrayList();
    }
//    public Element(String s)  // Used for createing an element with user dxf code
//    {
//        this.s = s;
//    }

    public void addElement(Element e) {
        this.elements.add(e);
    }

    public void insertElement(int index, Element e) {
        this.elements.add(index, e);
    }

    public void removeElementAt(int index) {
        this.elements.remove(index);
    }

    public Element getElement(int index) {
        return (Element) this.elements.get(index);
    }

    public int elementCount() {
        return this.elements.size();
    }

    public void addData(Data a) {
        if (isCorrectData(a))
            this.data.add(a);
    }

    public void insertData(int index, Data a) {
        if(isCorrectData(a))
            this.data.add(index, a);
    }

    public void removeDataAt(int index) {
        this.data.remove(index);
    }

    public Data getData(int index) {
        return (Data) this.data.get(index);
    }

    public int getIndexFor(int code) {
        Data d = null;
        for (Object datum : this.data) {
            d = (Data) datum;
            if (d.getCode() == code)
                return this.data.indexOf(d);
        }
        return -1;
    }

    public Data getDataFor(int code) {
        Data d = null;
        for (Object datum : this.data) {
            d = (Data) datum;
            if (d.getCode() == code)
                return d;
        }
        return new Data(-10, 0);
    }

    public int dataCount() {
        return this.data.size();
    }

    public String getName() {
        return ((Data) this.data.get(0)).getData().toString();
    }

    public void addReplace(int cod, Object o) {
        int ind = this.getIndexFor(cod);
        if (ind == -1) this.addData(new Data(cod, o));
        else {
            this.data.remove(ind);
            this.insertData(ind, new Data(cod, o));
        }
    }

    public void addData(int cod, Object o) {
        this.addData(new Data(cod, o));
    }
    public boolean isAccepted(Element e){
        return true;
        //return elementAcceptanceList.contains(e);
    }
    public boolean isAccepted(Data d){
        return dataAcceptanceList.contains(d.getCode()) && this.isCorrectData(d);
    }
    public boolean isCorrectData(Data d){
        //            if (between(d.getCode(),0,9) || between(d.getCode(),1000,1009))
        //                return d.getData() instanceof String;
        //            if(between(d.getCode(),10,59) || between(d.getCode(),110,149) || between(d.getCode(),210,239) || between(d.getCode(),1010,1059))
        //                return d.getData() instanceof Double;
        //            if(between(d.getCode(),60,79)||between(d.getCode(),270,289) || between(d.getCode(),370,389) || between(d.getCode(),170,179) || between(d.getCode(),1060,1070))
        //                return d.getData() instanceof Short;
        return d.getCode() >= 0;
    }
//    public boolean between(int x,int a,int b){
//        if(x <= b && x >= a) return true;
//        return false;
//    }
public String toDxfString(){
    StringBuilder s = new StringBuilder();
    s.append(startTag.getCode_Date());
    if (data.size() > 0){
        for (int i = 0 ; i < data.size(); i++){
            Data data = (Data) this.data.get(i);
            s.append(data.getCode_Date());
        }
    }
    if (elements.size()>0){
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

