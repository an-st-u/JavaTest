import java.util.List;
import java.lang.Math;
import java.lang.String;

import static java.lang.Integer.parseInt;

public class NOD {

    String first;
    Double[] second;
    List <Double> third;
    int a,b;

    NOD(String c){
        first=c;
        String[] s = first.split(",");
        a=Integer.parseInt(s[0],10);
        b=Integer.parseInt(s[1],10);
    }

    NOD(Double[] c){
        second=c;
        double buf;
        buf=c[0];
        a=(int)buf;
        buf=c[1];
        b=(int)buf;
    }

    NOD(List <Double> c){
        third=c;

    }


    int alNOD() {

        while (a!=b) {

            if (a > b) {
                a = a - b;
            }
            else {
                b = b - a;

            }
        }
        return a;
    }
}
