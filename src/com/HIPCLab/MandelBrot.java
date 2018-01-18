package  com.HIPCLab;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class MandelBrot {
    public static void main(String args[]) throws IOException {
        File file = new File("test.ppm");
        if(!file.exists()){
            file.createNewFile();
        }
            PrintWriter pt = new PrintWriter(file);

        int m = 500, n = 500;
        int count[][] = new int[m][n];
        int r[][] = new int[m][n];
        int g[][] = new int[m][n];
        int b[][] = new int[m][n];
        int count_max = 190;
        int i, j, k;
        double xArray[][] = {{-1.07,0.07,1.24,-1.07,0.07,1.24,-1.07,0.07,1.24},{-2.25,-1.07,0.07,-2.25,-1.07,0.07,-2.25,-1.07,0.07}};
        double yArray[][] = {{-0.58,-0.58,-0.58,0.58,0.58,0.58,1.75,1.75,1.75},{-1.75,-1.75,-1.75,-0.58,-0.58,-0.58,0.58,0.58,0.58}};
        double x_max = 1.24, x_min = -2.25;
        double y_max = 1.75, y_min = -1.75;
        double x,y;
        for(i = 0 ; i < m ; i++){
            for(j = 0 ; j < n; j++){
                x = ( (j-1) * x_max + (n-j) * x_min )/(n-1);
                y = ( (i-1) * y_max + (m-i) * y_min )/(m-1);
                double x1 = x;
                double y1 = y;

                for(k = 1; k<= count_max; k = k + 1){
                    double x2 = x1 * x1 - y1 * y1 + x;
                    double y2 = 2 * x1 * y1 + y;
                    if((x2 < -2) || (2 < x2) || (y2 < -2) || (2 < y2)){
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
                    System.out.println(count_rel);
                    double c = 255 * Math.sqrt(Math.sqrt(Math.sqrt(count_rel)));
                    r[j][j] = (int)(3 * c / 5);
                    g[i][j] = (int)(3 * c /5);
                    b[i][j] = (int)c;
                    System.out.println(r[i][j] + " ," + g[i][j] + ", " + b[i][j]);
                }
            }
        }

        pt.println("P3");
        pt.println("500 500");
        pt.println("255");
        for(i = 0 ; i < m ; i++){
            int t = 0;
            for(j = 0 ; j < n ; j++){
                if(t <= 10) {
                    pt.print(" " + r[i][j] + " " + g[i][j] + " " + b[i][j] + " ");
                }else{
                    t = 0;
                    pt.println();
                    pt.print(" " + r[i][j] + " " + g[i][j] + " " + b[i][j] + " ");
                }

            }
        }
        pt.flush();
        pt.close();
    }

}
