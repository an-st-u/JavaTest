import java.util.List;

public class NOD implements Result{
    int a, b;

    NOD(String first) {
        String[] s = first.split(",");
        a = Integer.parseInt(s[0], 10);
        b = Integer.parseInt(s[1], 10);
    }

    NOD(Double ... c) {
        double buf;
        buf = c[0];
        a = (int) buf;
        buf = c[1];
        b = (int) buf;
    }

    NOD(List<Double> c) {
        String[] s;
        s = (String[]) c.toArray();
        a = Integer.parseInt(s[0], 10);
        b = Integer.parseInt(s[1], 10);
    }

    public int getResult() {
        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }
        return a;
    }

}
