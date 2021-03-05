package dxfRead;

import java.util.ArrayList;
import java.util.List;

public class DxfHeader {
    private List<HeadUnit> pairs = new ArrayList<>();

    public List<HeadUnit> getPairs() {
        return pairs;
    }

    public void setPairs(List<HeadUnit> pairs) {
        this.pairs = pairs;
    }

    public DxfHeader() {
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("0\nSECTION\n2\nHEADER\n");
        for(HeadUnit pair : pairs){
            result.append(pair.toString());
        }
        result.append("0\nENDSEC\n");
        return result.toString();
    }
}
