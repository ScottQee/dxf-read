package dxf;

import dxf.Pair;

import java.util.ArrayList;
import java.util.List;

public class DxfHeader {
    private List<Pair> pairs = new ArrayList<>();

    public List<Pair> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public DxfHeader() {
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("0\nSECTION\n2\nHEADER\n");
        for(Pair pair : pairs){
            result.append(pair.toString());
        }
        result.append("0\nENDSEC\n");
        return result.toString();
    }
}
