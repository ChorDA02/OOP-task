public class Main {
    public static void main(String[] args) {
        System.out.println(converter(5));// 1
        System.out.println(points(13, 12));// 2
        System.out.println(footballPoints(3, 4, 2));// 3
        System.out.println(divisibleByFive(5));// 4
        System.out.println(and(true, false));// 5
        System.out.println(howManyWalls(54, 1, 43));// 6
        System.out.println(squared(5));// 7
        System.out.println(profitableGamble(0.2, 50, 9));// 8
        System.out.println(frames(1,1));// 9
        System.out.println(mod(5, 2));// 10
    }
    public static int converter(int time) {
        return time * 60 * 60 * 24 * 365;
    }
    public static int points(int a, int b) {
        return a*2+b*3;
    }
    public static int footballPoints(int win, int tie, int lose) {
        return win*3+tie;
    }
    public static boolean divisibleByFive(int chislo) {
        return chislo%5==0;
    }
    public static boolean and(boolean a, boolean b) {
        return a&&b;
    }
    public static int howManyWalls(int n, int w, int h) {
        return n / (w*h);
    }
    public static int squared(int a) {
        return a*a;
    }
    public static boolean profitableGamble(double prob, int prize, int pay) {
        return prob*prize>pay;
    }
    public static int frames(int frame, int min) {
        return frame*60*min;
    }
    public static int mod(int a, int b) {
        return a - b*(a/b);
    }
}
