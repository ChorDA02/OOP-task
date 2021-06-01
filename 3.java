public class Main {
    public static void main(String[] args) {
        System.out.println(millionsRounding(new String[]{"Moscow", "Saint Petersburg", "Kazan"}, new int[]{18534812, 7574323, 1243928}));// 1
        System.out.println(otherSides(2));// 2
        System.out.println(rps(new String[]{"rock", "paper"}));// 3
        System.out.println(warOfNumbers(new int[]{2, 8, 7, 5}));// 4
        System.out.println(reverseCase("This is any text"));// 5
        System.out.println(inatorInator("Shrink"));// 6
        System.out.println(doesBrickFit(1,2,2,1,1));// 7
        System.out.println(totalDistance(36.1, 8.6, 3, true));// 8
        System.out.println(mean(new double[]{2, 3, 2, 3}));// 9
        System.out.println(parityAnalysis(243));// 10
    }
    public static String millionsRounding(String countryname[], int popul[]) {
        int mil;
        String result = "";
        for(int i = 0; i<countryname.length; i++) {
            mil = popul[i] % 1000000;
            popul[i] = popul[i] / 1000000;
            if (mil >= 500000) popul[i] = popul[i] + 1;
            popul[i] = popul[i] * 1000000;
            result = result + countryname[i] + " " + popul[i] + "\n";
        }
        return result.substring(0, result.length()-1);
    }
    public static String otherSides(int len) {
        double len2, len3;
        len2 = len*2;
        len3 = (double) Math.sqrt(len2*len2-len*len);
        len2 = Math.round(len2*100.0)/100.0;
        len3 = Math.round(len3*100.0)/100.0;
        return len2 + " " + len3;
    }
    public static String rps(String choice[]) {
        if(choice[0] == choice[1]) return "TIE";
        if(choice[1] == "paper" && choice[0] == "rock") return "Player 2 wins";
        if(choice[1] == "rock" && choice[0] == "scissors") return "Player 2 wins";
        if(choice[1] == "scissors" && choice[0] == "paper") return "Player 2 wins";
        return "Player 1 wins";
    }
    public static int warOfNumbers(int num[]) {
        int sumc = 0;
        int sumn = 0;
        for (int i = 0; i < num.length; i++) {
            if(num[i] % 2 == 1) sumn = sumn + num[i];
            else sumc = sumc + num[i];
        }
        return Math.abs(sumc-sumn);
    }
    public static String reverseCase(String text) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(Character.isUpperCase(c)) chars[i] = Character.toLowerCase(c);
            else if(Character.isLowerCase(c)) chars[i] = Character.toUpperCase(c);
        }
        return new String(chars);
    }
    public static String inatorInator(String text) {
        switch(text.charAt(text.length()-1)) {
            case 'a': return text + "-inator " + text.length() + "000";
            case 'e': return text + "-inator " + text.length() + "000";
            case 'i': return text + "-inator " + text.length() + "000";
            case 'o': return text + "-inator " + text.length() + "000";
            case 'u': return text + "-inator " + text.length() + "000";
            case 'y': return text + "-inator " + text.length() + "000";
        }
        return text + "inator " + text.length() + "000";
    }
    public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
        if (h >= a && (w >= b || w >= c)) return true;
        if (h >= b && (w >= a || w >= c)) return true;
        if (h >= c && (w >= a || w >= b)) return true;
        return false;
    }
    public static double totalDistance(double fuel, double rashod, int count, boolean cond) {
        rashod = rashod + rashod * count * 0.05;
        if (cond) rashod = rashod + rashod * 0.1;
        return Math.round((fuel/rashod)*10000.0)/100.0;
    }
    public static double mean(double chislo[]) {
        double sum = 0;
        for (int i = 0; i < chislo.length; i++) {
            sum = sum + chislo[i];
        }
        return Math.round(sum/chislo.length*100.0)/100.0;
    }
    public static boolean parityAnalysis(int chislo) {
        int sum = 0;
        int chislo2 = chislo;
        while (chislo2 != 0) {
            sum = sum + (chislo2 % 10);
            chislo2 = chislo2/10;
        }
        return sum % 2 == chislo2 % 2;
    }
}
