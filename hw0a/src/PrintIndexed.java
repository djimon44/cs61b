public class PrintIndexed {
    static void Indexed(String s) {
        StringBuilder output = new StringBuilder();
        int sLength = s.length();
        for (int i=0; i < sLength; i++) {
            output.append(s.charAt(i));
            output.append(sLength - 1 - i);
        }
        System.out.println(output);
    }

    public static void main(String[] args) {
        Indexed("hello");
    }
}