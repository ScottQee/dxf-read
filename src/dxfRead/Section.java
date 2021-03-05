package dxfRead;

import java.util.ArrayList;

public class Section extends Element{
    public Section(String s){
        super();
        startTag = new Data(0,"SECTION");
        endTag = new Data(0,"ENDSEC");
        data = new ArrayList();
        elements = new ArrayList();
        data.add(new Data(2,s));
    }
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
}
