import java.util.Arrays;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        System.out.println(oppositeHouse(1, 3));// 1
        System.out.println(nameShuffle("Donald Trump"));// 2
        System.out.println(discount(1500, 30));// 3
        int mass[] = {44, 32, 86, 19};
        System.out.println(differenceMinMax(mass));// 4
        System.out.println(equal(3, 4, 3));// 5
        System.out.println(reverse("Hello World"));// 6
        System.out.println(programmers(147, 33, 526));// 7
        System.out.println(getXO("ooxx"));// 8
        System.out.println(bomb("There is a bomb."));// 9
        System.out.println(sameAscii("a", "a"));// 10
    }
    public static int oppositeHouse(int a, int b) {
        return b*2-a+1;
    }
    public static String nameShuffle(String name) {
        String[] reverse = name.split(" ");
        return reverse[1] + " " + reverse[0];
    }
    public static int discount(int price, int percent) {
        return price-(price/100*percent);
    }
    public static int differenceMinMax(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length-1]-arr[0];
    }
    public static int equal(int a, int b, int c) {
        if (a == b && a == c) return 3;
        if (a == c || b == c) return 2;
        return 0;
    }
    public static String reverse(String text) {
        String result = "";
        for (int i = text.length()-1; i >= 0; i--) {
            result = result + text.charAt(i);
        }
        return result;
    }
    public static int programmers(int a, int b, int c) {
        return Math.max(Math.max(a, b), c)-Math.min(Math.min(a,b), c);
    }
    public static boolean getXO(String text) {
        int sum = 0;
        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == 'x') sum++;
            if(text.charAt(i) == 'o') sum--;
        }
        return sum==0;
    }
    public static String bomb(String text) {
        text = text.toLowerCase();
        if(text.contains("bomb")) return "DUCK!";
        return "Relax, there's no bomb.";
    }
    public static boolean sameAscii(String a, String b) {
        return a.chars().sum()==b.chars().sum();
    }
}
