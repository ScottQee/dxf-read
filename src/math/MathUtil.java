package math;

import dxfRead.entities.Point;

import java.util.List;

public class MathUtil {
    /**
     * 判断多边形是否包含某个点
     * @param x 点X
     * @param y 点Y
     * @param pointList 多边形点集合
     * @return
     */
    public static boolean contains(String x , String y , List<String[]> pointList){

        double testX = Double.parseDouble(x);
        double testY = Double.parseDouble(y);

        return contains(testX,testY,pointList);
    }
    /**
     * 判断多边形是否包含某个点
     * @param test 点
     * @param pointList 多边形点集合
     * @return
     */
    public static boolean contains(Point test, List<String[]> pointList)
    {
        double testX = Double.parseDouble(test.getPointX());
        double testY = Double.parseDouble(test.getPointY());

        return contains(testX,testY,pointList);
    }
    /**
     * 判断多边形是否包含某个点
     * @param testX 点X
     * @param testY 点Y
     * @param pointList 多边形点集合
     * @return
     */
    public static boolean contains(double testX, double testY, List<String[]> pointList){
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = pointList.size() - 1; i < pointList.size(); j = i++)
        {
            if ((Double.parseDouble(pointList.get(i)[1]) > testY) != (Double.parseDouble(pointList.get(j)[1]) > testY) &&
                    (testX < (Double.parseDouble(pointList.get(j)[0]) - Double.parseDouble(pointList.get(i)[0])) * (testY - Double.parseDouble(pointList.get(i)[1])) / (Double.parseDouble(pointList.get(j)[1]) - Double.parseDouble(pointList.get(i)[1])) + Double.parseDouble(pointList.get(i)[0])))
            {
                result = !result;
            }
        }
        return result;
    }
    /**
     * 判断多边形是否包含某个点
     * @param testX 点X
     * @param testY 点Y
     * @param pointList 多边形点集合
     * @return
     */
    public static boolean contain(double testX, double testY, List<double[]> pointList){
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = pointList.size() - 1; i < pointList.size(); j = i++)
        {
            if ((pointList.get(i)[1] > testY) != (pointList.get(j)[1] > testY) &&
                    (testX < (pointList.get(j)[0] - pointList.get(i)[0]) * (testY - pointList.get(i)[1]) / (pointList.get(j)[1] - pointList.get(i)[1]) + pointList.get(i)[0]))
            {
                result = !result;
            }
        }
        return result;
    }
}
