import java.util.ArrayList;
import java.util.Arrays;

public class BazaS {

    private static ArrayList<String> tabl = new ArrayList<>();


BazaS(String tabl) {

   String[] s = tabl.split("#");
    BazaS.tabl.addAll(Arrays.asList(s).subList(1, 3 + 1));
}

public void setTabl(int x, String name) {

    tabl.get(x);
}

public String getTabl(int x) {
    return tabl.get(x);
}

public void ShowIt() {

        for (int j = 0; j < tabl.size(); j++) {
            System.out.print(tabl.get(j) + " ");
            if ((j+1)%3==0) {System.out.println();}
        }
}


}
