package dxf;

public class ACI_Color {
   public static String Default = "0";
   public static String Red = "1";
   public static String Yellow = "2";
   public static String Green = "3";
   public static String Cyan = "4";
   public static String Blue = "5";
   public static String Magenta = "6";
   public static String White = "7";
   public static String DarkGrey = "8";
   public static String LightGrey = "9";
   public static RGB[] CadColor = new RGB[]{
           new RGB(255,255,255),
           new RGB(255,  0,  0), new RGB(255,255,  0), new RGB(  0,255,  0), new RGB(  0,255,255), new RGB(  0,  0,255),   // 5
           new RGB(255,  0,255), new RGB(255,255,255), new RGB(128,128,128), new RGB(192,192,192), new RGB(255,  0,  0),   // 10
           new RGB(255,127,127), new RGB(204,  0,  0), new RGB(204,102,102), new RGB(153,  0,  0), new RGB(153, 76, 76),   // 15
           new RGB(127,  0,  0), new RGB(127, 63, 63), new RGB( 76,  0,  0), new RGB( 76, 38, 38), new RGB(255, 63,  0),   // 20
           new RGB(255,159,127), new RGB(204, 51,  0), new RGB(204,127,102), new RGB(153, 38,  0), new RGB(153, 95, 76),   // 25
           new RGB(127, 31,  0), new RGB(127, 79, 63), new RGB( 76, 19,  0), new RGB( 76, 47, 38), new RGB(255,127,  0),   // 30
           new RGB(255,191,127), new RGB(204,102,  0), new RGB(204,153,102), new RGB(153, 76,  0), new RGB(153,114, 76),   // 35
           new RGB(127, 63,  0), new RGB(127, 95, 63), new RGB( 76, 38,  0), new RGB( 76, 57, 38), new RGB(255,191,  0),   // 40
           new RGB(255,223,127), new RGB(204,153,  0), new RGB(204,178,102), new RGB(153,114,  0), new RGB(153,133, 76),   // 45
           new RGB(127, 95,  0), new RGB(127,111, 63), new RGB( 76, 57,  0), new RGB( 76, 66, 38), new RGB(255,255,  0),   // 50
           new RGB(255,255,127), new RGB(204,204,  0), new RGB(204,204,102), new RGB(153,153,  0), new RGB(153,153, 76),   // 55
           new RGB(127,127,  0), new RGB(127,127, 63), new RGB( 76, 76,  0), new RGB( 76, 76, 38), new RGB(191,255,  0),   // 60
           new RGB(223,255,127), new RGB(153,204,  0), new RGB(178,204,102), new RGB(114,153,  0), new RGB(133,153, 76),   // 65
           new RGB( 95,127,  0), new RGB(111,127, 63), new RGB( 57, 76,  0), new RGB( 66, 76, 38), new RGB(127,255,  0),   // 70
           new RGB(191,255,127), new RGB(102,204,  0), new RGB(153,204,102), new RGB( 76,153,  0), new RGB(114,153, 76),   // 75
           new RGB( 63,127,  0), new RGB( 95,127, 63), new RGB( 38, 76,  0), new RGB( 57, 76, 38), new RGB( 63,255,  0),   // 80
           new RGB(159,255,127), new RGB( 51,204,  0), new RGB(127,204,102), new RGB( 38,153,  0), new RGB( 95,153, 76),   // 85
           new RGB( 31,127,  0), new RGB( 79,127, 63), new RGB( 19, 76,  0), new RGB( 47, 76, 38), new RGB(  0,255,  0),   // 90
           new RGB(127,255,127), new RGB(  0,204,  0), new RGB(102,204,102), new RGB(  0,153,  0), new RGB( 76,153, 76),   // 95
           new RGB(  0,127,  0), new RGB( 63,127, 63), new RGB(  0, 76,  0), new RGB( 38, 76, 38), new RGB(  0,255, 63),   // 100
           new RGB(127,255,129), new RGB(  0,204, 51), new RGB(102,204,127), new RGB(  0,153, 38), new RGB( 76,153, 95),   // 105
           new RGB(  0,127, 31), new RGB( 63,127, 79), new RGB(  0, 76, 19), new RGB( 38, 76, 47), new RGB(  0,255,127),   // 110
           new RGB(127,255,191), new RGB(  0,204,102), new RGB(102,204,153), new RGB(  0,153, 76), new RGB( 76,153,114),   // 115
           new RGB(  0,127, 63), new RGB( 63,127, 95), new RGB(  0, 76, 38), new RGB( 38, 76, 57), new RGB(  0,255,191),   // 120
           new RGB(127,255,223), new RGB(  0,204,153), new RGB(102,204,178), new RGB(  0,153,114), new RGB( 76,153,133),   // 125
           new RGB(  0,127, 95), new RGB( 63,127,111), new RGB(  0, 76, 57), new RGB( 38, 76, 66), new RGB(  0,255,255),   // 130
           new RGB(127,255,255), new RGB(  0,204,204), new RGB(102,204,204), new RGB(  0,153,153), new RGB( 76,153,153),   // 135
           new RGB(  0,127,127), new RGB( 63,127,127), new RGB(  0, 76, 76), new RGB( 38, 76, 76), new RGB(  0,191,255),   // 140
           new RGB(127,223,255), new RGB(  0,153,204), new RGB(102,178,204), new RGB(  0,114,153), new RGB( 76,133,153),   // 145
           new RGB(  0, 95,127), new RGB( 63,111,127), new RGB(  0, 57, 76), new RGB( 38, 66, 76), new RGB(  0,127,255),   // 150
           new RGB(127,191,255), new RGB(  0,102,204), new RGB(102,153,204), new RGB(  0, 76,153), new RGB( 76,114,153),   // 155
           new RGB(  0, 63,127), new RGB( 63, 95,127), new RGB(  0, 38, 76), new RGB( 38, 57, 76), new RGB(  0, 63,255),   // 160
           new RGB(127,159,255), new RGB(  0, 51,204), new RGB(102,127,204), new RGB(  0, 38,153), new RGB( 76, 95,153),   // 165
           new RGB(  0, 31,127), new RGB( 63, 79,127), new RGB(  0, 19, 76), new RGB( 38, 47, 76), new RGB(  0,  0,255),   // 170
           new RGB(127,127,255), new RGB(  0,  0,204), new RGB(102,102,204), new RGB(  0,  0,153), new RGB( 76, 76,153),   // 175
           new RGB(  0,  0,127), new RGB( 63, 63,127), new RGB(  0,  0, 76), new RGB( 38, 38, 76), new RGB( 63,  0,255),   // 180
           new RGB(159,127,255), new RGB( 51,  0,204), new RGB(127,102,204), new RGB( 38,  0,153), new RGB( 95, 76,153),   // 185
           new RGB( 31,  0,127), new RGB( 79, 63,127), new RGB( 19,  0, 76), new RGB( 47, 38, 76), new RGB(127,  0,255),   // 190
           new RGB(191,127,255), new RGB(102,  0,204), new RGB(153,102,204), new RGB( 76,  0,153), new RGB(114, 76,153),   // 195
           new RGB( 63,  0,127), new RGB( 95, 63,127), new RGB( 38,  0, 76), new RGB( 57, 38, 76), new RGB(191,  0,255),   // 200
           new RGB(223,127,255), new RGB(153,  0,204), new RGB(178,102,204), new RGB(114,  0,153), new RGB(133, 76,153),   // 205
           new RGB( 95,  0,127), new RGB(111, 63,127), new RGB( 57,  0, 76), new RGB( 66, 38, 76), new RGB(255,  0,255),   // 210
           new RGB(255,127,255), new RGB(204,  0,204), new RGB(204,102,204), new RGB(153,  0,153), new RGB(153, 76,153),   // 215
           new RGB(127,  0,127), new RGB(127, 63,127), new RGB( 76,  0, 76), new RGB( 76, 38, 76), new RGB(255,  0,191),   // 220
           new RGB(255,127,223), new RGB(204,  0,153), new RGB(204,102,178), new RGB(153,  0,114), new RGB(153, 76,133),   // 225
           new RGB(127,  0, 95), new RGB(127, 63,111), new RGB( 76,  0, 57), new RGB( 76, 38, 66), new RGB(255,  0,127),   // 230
           new RGB(255,127,191), new RGB(204,  0,102), new RGB(204,102,153), new RGB(153,  0, 76), new RGB(153, 76,114),   // 235
           new RGB(127,  0, 63), new RGB(127, 63, 95), new RGB( 76,  0, 38), new RGB( 76, 38, 57), new RGB(255,  0, 63),   // 240
           new RGB(255,127,159), new RGB(204,  0, 51), new RGB(204,102,127), new RGB(153,  0, 38), new RGB(153, 76, 95),   // 245
           new RGB(127,  0, 31), new RGB(127, 63, 79), new RGB( 76,  0, 19), new RGB( 76, 38, 47), new RGB( 51, 51, 51),   // 250
           new RGB( 91, 91, 91), new RGB(132,132,132), new RGB(173,173,173), new RGB(214,214,214), new RGB(255,255,255)    // 255
   };
   public static String getColorNumber(RGB color){
      for (int i = 0 ;i < CadColor.length; i++){
         RGB rgb = CadColor[i];
         if (rgb.equals(color)){
            return  String.valueOf(i);
         }
      }
      return "0";
   }

}

