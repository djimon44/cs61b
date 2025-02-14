public class Stutter {
    static StringBuilder stutter(String s) {
        StringBuilder output = new StringBuilder();
        int slength = s.length();
        for (int i=0; i < slength; i++) {
            output.append(s.charAt(i));
            output.append(s.charAt(i));
        }
        return output;
    }
}
