package math;

import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Cohen-Sutherland算法
 * 截取线段在矩形的部分
 */
public final class Clipping {
    private static final int INSIDE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 4;
    private static final int TOP = 8;

    private double xMin;
    private double yMin;
    private double xMax;
    private double yMax;

    /**
     * 初始化，放入矩形对象
     * @param rect
     */
    public Clipping(List<double[]> rect) {
        xMin = rect.get(0)[0];
        yMin = rect.get(0)[1];
        xMax = rect.get(2)[0];
        yMax = rect.get(2)[1];
    }
    /**
     * 初始化，放入矩形对象
     * @param clip
     */
    public Clipping(Rectangle2D clip) {
        setClip(clip);
    }
    /**
     * 设置矩形对象
     * @param clip
     */
    public void setClip(Rectangle2D clip) {
        xMin = clip.getX();
        xMax = xMin + clip.getWidth();
        yMin = clip.getY();
        yMax = yMin + clip.getHeight();
    }

    private final int getRegionCode(double x, double y) {
        int xcode = x < xMin ? LEFT : x > xMax ? RIGHT : INSIDE;
        int ycode = y < yMin ? BOTTOM : y > yMax ? TOP : INSIDE;
        return xcode | ycode;
    }

    /**
     * 放入线段进行裁切
     * @param line
     * @return true则裁切成功，line对象内变为已裁切的线段两点，false则线段不与矩形相交无法裁切
     */
    public boolean clip(Line2D.Double line) {
        double p1x = line.getX1(), p1y = line.getY1();
        double p2x = line.getX2(), p2y = line.getY2();
        double qx = 0d, qy = 0d;
        boolean vertical = p1x == p2x;
        double slope = vertical ? 0d : (p2y - p1y) / (p2x - p1x);

        int code1 = getRegionCode(p1x, p1y);
        int code2 = getRegionCode(p2x, p2y);

        while (true) {
            if(code1 == INSIDE & code2 == INSIDE){
                break;
            }

            if ((code1 & code2) != INSIDE){
                return false;
            }

            int codeout = code1 == INSIDE ? code2 : code1;

            if ((codeout & LEFT) != INSIDE) {
                qx = xMin;
                qy = (qx - p1x) * slope + p1y;
            } else if ((codeout & RIGHT) != INSIDE) {
                qx = xMax;
                qy = (qx - p1x) * slope + p1y;
            } else if ((codeout & BOTTOM) != INSIDE) {
                qy = yMin;
                qx = vertical ? p1x : (qy - p1y) / slope + p1x;
            } else if ((codeout & TOP) != INSIDE) {
                qy = yMax;
                qx = vertical ? p1x : (qy - p1y) / slope + p1x;
            }

            if (codeout == code1) {
                p1x = qx;
                p1y = qy;
                code1 = getRegionCode(p1x, p1y);
            } else {
                p2x = qx;
                p2y = qy;
                code2 = getRegionCode(p2x, p2y);
            }
        }
        line.setLine(p1x, p1y, p2x, p2y);
        return true;
    }
}
