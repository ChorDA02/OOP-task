import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(sevenBoom(new int[]{1, 2, 3, 4, 5, 6, 7}));// 1
        System.out.println(cons(new int[]{5, 3, 4, 2, 1}));// 2
        System.out.println(unmix("hTsii  s aimex dpus rtni.g"));// 3
        System.out.println(noYelling("What were wrong?????????"));// 4
        System.out.println(xPronounce("Inside the box was a xylophone x D"));// 5
        System.out.println(largestGap(new int[]{9, 4, 26, 26, 0, 0, 5, 20, 6, 25, 5}));// 6
        System.out.println(reverseDigit(7977));// 7
        System.out.println(commonLostVowel("Hello World!"));// 8
        System.out.println(memeSum(26, 39));// 9
        System.out.println(unrepeated("teshahsetteshahsetfxteshahset"));// 10
    }
    public static String sevenBoom(int chislo[]) {
        for (int i = 0; i < chislo.length; i++) {
            if (chislo[i] == 7) return "Boom!";
        }
        return "there is no 7 in the array";
    }
    public static boolean cons(int chislo[]) {
        Arrays.sort(chislo);
        for (int i = 1; i < chislo.length; i++) {
            if (chislo[i] != chislo[i-1]+1) return false;
        }
        return true;
    }
    public static String unmix(String text) {
        char[] chars = text.toCharArray();
        char per;
        for (int i = 0; i+1 < chars.length; i = i + 2) {
            per = chars[i+1];
            chars[i+1] = chars[i];
            chars[i] = per;
        }
        return new String(chars);
    }
    public static String noYelling(String text) {
        int i = text.length();
        while (i > 0) {
            if(text.charAt(i-2) != '?' && text.charAt(i-2) != '!') break;
            i--;
        }
        return new String(text.substring(0,i));
    }
    public static String xPronounce(String text) {
        int k = text.length();
        if(text.charAt(0) == 'x' || text.charAt(0) == 'X') text = "z" + text.substring(1, text.length());
        for (int i = 1; i < k; i++) {
            if(text.charAt(i-1) == ' ' && (text.charAt(i) == 'x' || text.charAt(i) == 'X') && text.charAt(i+1) == ' ') {
                text = text.substring(0,i) + "ecks" + text.substring(i+1,k);
                k = k + 3;
            }
            if(text.charAt(i-1) == ' ' && (text.charAt(i) == 'x' || text.charAt(i) == 'X') && text.charAt(i+1) != ' ') {
                text = text.substring(0,i) + "z" + text.substring(i+1,k);
            }
            if(text.charAt(i-1) != ' ' && (text.charAt(i) == 'x' || text.charAt(i) == 'X') && text.charAt(i+1) == ' ') {
                text = text.substring(0,i) + "cks" + text.substring(i+1,k);
                k = k + 2;
            }
        }
        return text;
    }
    public static int largestGap(int chislo[]) {
        Arrays.sort(chislo);
        int larg = 0;
        for (int i = 1; i < chislo.length; i++) {
            if (chislo[i] - chislo[i-1] > larg) larg = chislo[i] - chislo[i-1];
        }
        return larg;
    }
    public static int reverseDigit(int chislo) {
        String text = String.valueOf(chislo);
        String[] mass = text.split("");
        Arrays.sort(mass);
        text = "";
        for (int i = 0; i < mass.length; i++) {
            text = text + mass[i];
        }
        return chislo-Integer.parseInt(text);
    }
    public static String commonLostVowel(String text) {
        int[] count = new int[6];
        for (int i = 0; i < 5; i++) count[i] = 0;
        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) == 'a' && text.charAt(i+1) == ' ') count[0]++;
            if(text.charAt(i) == 'e' && text.charAt(i+1) == ' ') count[1]++;
            if(text.charAt(i) == 'i' && text.charAt(i+1) == ' ') count[2]++;
            if(text.charAt(i) == 'o' && text.charAt(i+1) == ' ') count[3]++;
            if(text.charAt(i) == 'u' && text.charAt(i+1) == ' ') count[4]++;
            if(text.charAt(i) == 'y' && text.charAt(i+1) == ' ') count[5]++;
        }
        int larg = 0;
        int j = -1;
        for (int i = 0; i < 5; i++) {
            if(count[i] > larg) {
                larg = count[i];
                j = i;
            }
        }
        switch(j) {
            case -1: return "No one word ends with vowel";
            case 0: return "a";
            case 1: return "e";
            case 2: return "i";
            case 3: return "o";
            case 4: return "u";
            case 5: return "y";
        }
        return "Error";
    }
    public static String memeSum(int a, int b) {
        int[] sum = new int[9];
        String result = "";
        for (int i = 0; i < sum.length; i++) {
            sum[i] = 0;
        }
        for (int i = sum.length-1; i > 0; i--) {
            sum[i] = (a % 10) + (b % 10);
            a = a / 10;
            b = b / 10;
            if(a == 0 && b == 0) break;
        }
        for (int i = 0; i < sum.length; i++) {
            if(sum[i] != 0) result = result + sum[i];
        }
        return result;
    }
    public static String unrepeated(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < text.length(); j++) {
                if (chars[i] == chars[j] && i != j) chars[j] = (char)0;
            }
        }
        return new String(chars);
    }
}
