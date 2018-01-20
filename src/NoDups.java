import java.util.ArrayList;
import java.util.Comparator;

public class NoDups {

    private static Comparator<Integer> c = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };
    private static ArrayList<Integer> iary = new ArrayList<>() {{
        add(8);
        add(1);
        add(3);
        add(8);
        add(8);
        add(6);
        add(3);
    }};


    public static void main(String[] args) {
        System.out.println(iary.toString());
        System.out.println(noDups(c, iary).toString());
        System.out.println(noDupsTime(c, iary).toString());
    }

    /**
     * Bitte beachten sie bei der Korrektur: Die Methode noDupsTime ist die endgültiige Methode.
     * Diese ist für ein eventuelles Nachvollziehen des Denkvorgangs meinerseits. Vielen Dank!
     *
     * Laufzeitanalyse dieser Methode: O(n^2), da: Im schlechtesten Fall (keine Duplikate):
     * n Arrayzugriffe im äußeren Loop + Summe(von i=1 bis n) über i = n + (n*n-1)/2 = 0.5*n^2 + 0.5*n element von O(n^2)
     *
     *
     * @param cmp
     * @param l
     * @param <T>
     * @return
     */

    public static <T> ArrayList<T> noDups(Comparator<T> cmp, ArrayList<T> l) {
        ArrayList<T> result = new ArrayList<T>();

        for (T o : l) {
            boolean noDup = true;
            for (T lo : result) {
                if (cmp.compare(lo, o) == 0) {
                    noDup = false;
                    break;
                }
            }
            if (noDup) {
                result.add(o);
            }
        }


        return result;
    }

    /*
    Nach einiger Überarbeitung ist nun diese Methode eine,
    welche dasselbe Ergebnis wie noDups liefert, jedoch nur eine Laufzeit von O(n*log(n)) benötigt.
    Dies ergiebt sich wie folgt:
    (Alle Zugriffsfälle gelten für den jeweiligen worst-case)
    n Zugriffe für den ersten Loop
    n Zugriffe für den Zweiten Loop
    n Zugriffe für den while-loop
    Die result.sort methode hat eine Runtime von O(n*log(n))
    Es ergibt sich 3n + n*log(n) -> O(n*log(n))
     */

    public static <T> ArrayList<T> noDupsTime(Comparator<T> cmp, ArrayList<T> l) {
        ArrayList<T> result = new ArrayList<>();

        for (T o:l){
            result.add(null);
        }

        for (T o: l){

            result.set(l.indexOf(o), o);
        }
        while(result.remove(null));

       result.sort((o1, o2) -> l.indexOf(o1) - l.indexOf(o2));


        return result;
    }
}



