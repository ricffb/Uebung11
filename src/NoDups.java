import java.util.ArrayList;
import java.util.Comparator;

public class NoDups {

    private static Comparator<Integer> c = new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };

    private static Comparator<Integer> c1 = (o1, o2) -> {
        if (o1 > o2) return 1;
        else if (o1 < o2) return -1;
            else return 0;
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
        System.out.println(noDupsTime1(c, iary).toString());
        System.out.println(noDupsTime1(c1, iary).toString());
    }

    /**
     * Bitte beachten sie bei der Korrektur: Die Methode noDupsTime1 ist die endgültiige Methode.
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
    Diese Methode sollte eigentlich eine Lösung sein, die sowohl Laufzeit, als auch Reihenfolge breücksichtigt.
    Leider verwendet sie den gegebenen Comparator nicht, was sie natürlich für die gestellte Aufgabe ungeeignet macht.
    Ich lasse den originalen Kommentar trotzdem stehen.


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

       result.sort(Comparator.comparingInt(o1 -> l.indexOf(o1)));


        return result;
    }
    /*
    Nun zur endgültigen Methode. Obwohl hier gleich 2 mal sortiert wird ist die Laufzeit besser als n^2.
    Da die sort methode der ArrayList eine Optimierung des merge sort algorithm ist hat sie eine Laufzeit von O(n*log(n)).
    Die for-schleife benötigt O(n) zugriffe. Wir können also ohne Beschränkung der Allgemeinheit annehmen, dass die Laufzeit dieser Methode
    2*n*log(n)+n element O(n*log(n))
    ist.
     */

    public static <T> ArrayList<T> noDupsTime1(Comparator<T> cmp, ArrayList<T> l) {
        ArrayList<T> result = new ArrayList<>();
        ArrayList<T> original = new ArrayList<>(l);

        original.sort(cmp);
        result.add(original.get(0));

        for (int i = 1; i < original.size() ; i++) {
            if (cmp.compare(original.get(i-1), original.get(i)) != 0){
                result.add(original.get(i));
            }
        }

        result.sort(Comparator.comparingInt(o1 -> l.indexOf(o1)));

        return result;
    }
}



