import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int total = 0;
        for (int i=0; i<L.size(); i++) {
            total += L.get(i);
        }
        return total;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> newList = new ArrayList<>();

        for (int i: L) {
            if (i%2==0) {
                newList.add(i);
            }
        }
        return newList;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> newList = new ArrayList<>();

        for (int i: L1) {
            for (int j: L2) {
                if (i==j) {
                    newList.add(i);
                }
            }
        }
        return newList;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String word: words) {
            for (char ch: word.toCharArray()) {
                if (ch == c) {
                    count++;
                }
            }
        }
        return count;
    }
}
