public class StarTriangle {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String line = "";
            for (int j = 0; j < (i + 1); j++) {
                line += "*";
            }
            System.out.println(line);
        }
    }
}