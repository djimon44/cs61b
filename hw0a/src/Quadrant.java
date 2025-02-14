public class Quadrant {
    static int quad(int x, int y) {
        if (x==0 || y ==0) {
            return 0;
        } else if (y>0 && x>0) {
            return 1;
        } else if (y>0 && x<0) {
            return 2;
        } else if (y<0 && x<0) {
            return 3;
        } else {
            return 4;
        }
    }
}
