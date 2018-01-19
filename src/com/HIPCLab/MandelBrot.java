package  com.HIPCLab;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MandelBrot {
    public static void main(String args[]) throws IOException {
        int m = 500, n = 500;
        double count[][] = new double[m][n];
        int r[][] = new int[m][n];
        int g[][] = new int[m][n];
        int b[][] = new int[m][n];
        int count_max = 190;
        int i, j, k;
        double xArray[][] = {{-1.07,0.07,1.24,-1.07,0.07,1.24,-1.07,0.07,1.24},{-2.25,-1.07,0.07,-2.25,-1.07,0.07,-2.25,-1.07,0.07}};
        double yArray[][] = {{-0.58,-0.58,-0.58,0.58,0.58,0.58,1.75,1.75,1.75},{-1.75,-1.75,-1.75,-0.58,-0.58,-0.58,0.58,0.58,0.58}};
        double x_max = -10,x_min = -10;  //give inital value randomly
        double y_max = -10,y_min = -10;  //give inital value randomly
        int flag = -1;
        if(args.length>0){
            try{
                flag = Integer.parseInt(args[0]);
                x_max = xArray[0][flag-1];
                x_min = xArray[1][flag-1];
                y_max = yArray[0][flag-1];
                y_min = yArray[1][flag-1];
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        double x,y;
        for(i = 0 ; i < m ; i++){
            for(j = 0 ; j < n; j++){
                x = ( (j-1) * x_max + (n-j) * x_min )/(n-1);
                y = ( (i-1) * y_max + (m-i) * y_min )/(m-1);
                double x1 = x;
                double y1 = y;
                count[i][j] = 0;
                for(k = 1; k<= count_max; k = k + 1){
                    double x2 = x1 * x1 - y1 * y1 + x;
                    double y2 = 2 * x1 * y1 + y;
                    if((x2 < -2) | (2 < x2) | (y2 < -2) | (2 < y2)){
                        count[i][j] = k;
                                break;
                    }
                    x1 = x2;
                    y1 = y2;
                }
                if((count[i][j]%2) == 1){
                    r[i][j] = 255;
                    g[i][j] = 255;
                    b[i][j] = 255;
                }else{
                    double count_rel = count[i][j]/count_max;

                    double c = 255 * Math.sqrt(Math.sqrt(Math.sqrt(count_rel)));
                    r[i][j] = (int)(3 * c / 5);
                    g[i][j] = (int)(3 * c /5);
                    b[i][j] = (int)c;
                }
            }
        }

        String path = "test" + flag + ".ppm";
        File file = new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter pt = new FileWriter(file);
        pt.write("P3\n");
        pt.write("500 500\n");
        pt.write("255\n");
        for(i = 0 ; i < m ; i++){
            for(j = 0 ; j < n ; j++){
                pt.write(" " + r[i][j] + " " + g[i][j] + " " + b[i][j] + " ");

            }
        }
        pt.flush();
        pt.close();

    }

}
