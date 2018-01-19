import java.util.Comparator;

public class BiSeCo {
/*
    private static Comparator<Integer> c = new Comparator<Integer>() {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
    };
    private static Integer[] iary = {1,2,3,4,5,6,7,8};
    private static Integer w = 9;
*/
    public BiSeCo() {

    }

    /**
     * Methode, nach der in Aufagbe H11-2 gefragt wird:
     * @param c
     * @param l
     * @param w
     * @param i
     * @param j
     * @param <T>
     * @return
     */
    public static <T> boolean sucheVonBisC(Comparator<T> c, T[] l, T w, int i, int j){
        if (i > j) return false;
        if (i == j) return 0 == c.compare(l[i], w);
        int m = (i+j) / 2; T wm = l[m];
        int comp = c.compare(wm, w);
        if (comp == 0) return true;
        if (comp < 0) // wm < w
            return sucheVonBisC(c,l,w,m+1,j);
        else
            return sucheVonBisC(c,l,w,i,m-1);
    }
/*
    public static void main(String[] args) {
        if (sucheVonBisC(c, iary, w, 0, 7)){
            System.out.println("Is in array");
        }else System.out.println("Is not in array");
    }
*/
}
