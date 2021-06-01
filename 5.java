import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        System.out.println(sameLetterPattern("ABAB", "CDCD"));// 1
        System.out.println(spiderVsFly("H3", "E2"));// 2
        System.out.println(digitsCount(4666));// 3
        System.out.println(totalPoints(new String[]{"cat", "create", "sat"}, "caster"));// 4
        System.out.println(longestRun(new int[]{1, 2, 3, 5, 6, 7, 8, 9}));// 5
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));// 6
        System.out.println(rearrange("Tesh3 th5e 1I lov2e way6 she7 j4ust i8s."));// 7
        System.out.println(maxPossible(8732, 91255));// 8
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));// 9
        System.out.println(isNew(3));// 10
    }

    public static boolean sameLetterPattern(String a, String b) {
        if (a.length() != b.length()) return false;// сравниваем длину
        int j = a.charAt(0) - b.charAt(0); // находим разницу в байт-кодах
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) - b.charAt(i) != j) return false; // сравниваем разницу с первой
        }
        return true; // если все разницы совпали - паттерны одинаковые
    }
    public static String spiderVsFly(String s, String f) {
        char spiderLetter = s.charAt(0); // буква паука
        int spiderLevel = Integer.parseInt(Character.toString(s.charAt(1))); // цифра паука
        char butterFlyLetter = f.charAt(0); // буква мухи
        int butterFlyLevel = Integer.parseInt(Character.toString(f.charAt(1))); // цифра мухи
        String route = spiderLetter + Integer.toString(spiderLevel); // стринг для маршрута
        int distance = spiderLetter - butterFlyLetter; // дистанция между буквами
        int dir; // направление движения
        // определяем кратчайший путь
        if (distance > 0) {
            if (distance < 4) {
                dir = -1;
            }
            else {
                dir = 1;
                distance = 8 - distance;
            }
        }
        else {
            if (distance < -4) {
                distance *= -1;
                dir = 1;
            }
            else {
                dir = -1;
                distance = distance + 8;
            }
        }
        if (spiderLevel == 0) spiderLetter = butterFlyLetter; // если в центре - меняем букву на букву другого существа
        if (butterFlyLevel == 0) butterFlyLetter = spiderLetter;
        if (spiderLevel > butterFlyLevel || distance > 2) {
            while (spiderLevel > butterFlyLevel) { // если цифра паука меньше мухи - уменьшаем её
                spiderLevel--;
                route += "-" + spiderLetter + spiderLevel;
            }
            if (distance > 2) {
                while (spiderLevel != 0) { // идём к центру, по достижении меняем букву на "А"
                    if (spiderLevel == 1) spiderLetter = 'A';
                    spiderLevel--;
                    route += "-" + spiderLetter + spiderLevel;
                }
                spiderLetter = butterFlyLetter; // в центре: букву паука меняем на букву мухи
                while (spiderLevel != butterFlyLevel) { // из центра идём к мухе
                    spiderLevel++;
                    route += "-" + spiderLetter + spiderLevel;
                }
            } else {// если цифры незначительно отличаются
                while (spiderLetter != butterFlyLetter) { // если отличается буква
                    spiderLetter += dir; // идем по определенному ранее направлению
                    if (spiderLetter - 'A' > 7) spiderLetter -= 8;
                    if (spiderLetter - 'A' < 0) spiderLetter += 8;
                    route += "-" + spiderLetter + spiderLevel;
                }
            }
        } else {
            while (spiderLetter != butterFlyLetter) { // если паук ближе к центру чем муха
                spiderLetter += dir; // меняем буквы в сторону мухи
                if (spiderLetter - 'A' > 7) spiderLetter -= 8;
                if (spiderLetter - 'A' < 0) spiderLetter += 8;
                route += "-" + spiderLetter + spiderLevel;
            }
            while (spiderLevel != butterFlyLevel) { // меняем цифру
                spiderLevel++;
                route += "-" + spiderLetter + spiderLevel;
            }
        }
        return route;
    }
    public static int digitsCount(long number) { // рекурсивно считаем число цифр, увеличивая на 1
        if (Math.abs(number) < 10) return 1;
        return digitsCount(number / 10) + 1;
    }
    public static int totalPoints(String[] arr, String word) {
        int result = 0;
        boolean[] checker = new boolean[word.length()]; // для проверки дублируемых букв
        count: // метка для возврата
        for (String i : arr) { // в i записываем слова из arr[]
            for (int j = 0; j < word.length(); j++) {
                checker[j] = false;
            }
            for (int z = 0; z < i.length(); z++) {
                if (word.contains(Character.toString(i.charAt(z)))) // проверяем наличие буквы в слове
                    for (int j = 0; j < word.length(); j++) {
                        if (i.charAt(z) == word.charAt(j) && !checker[j]) {
                            checker[j] = true;
                            break;
                        }
                        if (j == word.length() - 1)
                            continue count; // возврат к метке
                    }
                else
                    continue count;
            }
            result += i.length() - 2;
            if (i.length() == word.length()) { // бонус за разгаданное слово
                result += 50;
            }
        }
        return result;
    }
    public static int longestRun(int[] arr) {
        int current = 1, longest = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - 1 == arr[i - 1] || arr[i] + 1 == arr[i - 1]) { // возрастание или убывание последовательности
                current++;
            }
            else {
                if (current > longest) longest = current; // последовательность оборвалась - сверяем с наибольшей
                current = 1;
            }
        }
        if (current > longest) longest = current;
        return longest;
    }
    public static String takeDownAverage(String[] arr) {
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += Integer.parseInt(arr[i].substring(0, arr[i].length() - 1)); // сумма процентов
        }
        return Integer.toString((int) ((sum / arr.length - 5) * (arr.length + 1) - sum + 0.5)) + "%"; // среднее арифметическое - 5% умножить на новое число участников (arr.length+1) - старая сумма + 0.5 для округления
    }
    public static String rearrange(String str) {
        String number = "";
        String word = "";
        String[] arr = str.split(" "); // слова из старого стринга
        String[] myarr = new String[arr.length]; // пустой массив
        for (int i = 0; i < arr.length; i++) {
            char[] mass = arr[i].toCharArray(); // слово из arr[] по символам
            for (int j = 0; j < mass.length; j++) {
                if (Character.isDigit(mass[j])) { // если цифра - в number, иначе в word
                    number += mass[j];
                } else {
                    word += mass[j];
                }
            }
            myarr[Integer.parseInt(number) - 1] = word; // в myarr под соответствующим номером
            word = "";
            number = "";
        }
        return String.join(" ", myarr); // собираем в один стринг
    }
    public static String maxPossible(int a, int b) {
        char[] chars = Integer.toString(b).toCharArray(); // второе число в char-массив
        Arrays.sort(chars); // сортируем по возрастанию
        String res = "";
        for (int i = 0; i < chars.length; i++) {
            res = chars[i] + res; // инвертируем сортировку (по убыванию)
        }
        String astr = Integer.toString(a);
        int z = 0; // счётчик номера цифры для второго числа
        String result = "";
        for (int i = 0; i < astr.length(); i++) {
            if (z < chars.length && Integer.parseInt(Character.toString(astr.charAt(i))) < Integer.parseInt(Character.toString(res.charAt(z)))) {
                result += Character.toString(res.charAt(z)); // если цифра второго больше - берем её и увеличиваем счётчик
                z++;
            }
            else {
                result += Character.toString(astr.charAt(i)); // иначе берем цифру первого
            }
        }
        return result;
    }
    public static String timeDifference(String name1, String time1, String name2) {
        String[] arr = time1.split(" ");
        String mon = "";
        if (arr[0].equals("December"))
            mon = "12";
        if (arr[0].equals("November"))
            mon = "11";
        if (arr[0].equals("October"))
            mon = "10";
        if (arr[0].equals("September"))
            mon = "9";
        if (arr[0].equals("August"))
            mon = "8";
        if (arr[0].equals("July"))
            mon = "7";
        if (arr[0].equals("June"))
            mon = "6";
        if (arr[0].equals("May"))
            mon = "5";
        if (arr[0].equals("April"))
            mon = "4";
        if (arr[0].equals("March"))
            mon = "3";
        if (arr[0].equals("February"))
            mon = "2";
        if (arr[0].equals("January"))
            mon = "1";
        String days = arr[1].substring(0, arr[1].length() - 1);
        if (mon.length() == 1)
            mon = "0" + mon;
        if (days.length() == 1)
            days = "0" + days;
        String timezone = "";
        int h = 0, m = 0; // записываем разницу таймзоны
        switch (name2) {
            case "Los Angeles": {
                timezone = "-08:00";
                h = -8;
                break;
            }
            case "New York": {
                timezone = "-05:00";
                h = -5;
                break;
            }
            case "Caracas": {
                timezone = "-04:30";
                h = -4;
                m = -30;
                break;
            }
            case "Buenos Aires": {
                h = -3;
                timezone = "-03:00";
                break;
            }
            case "London": {
                timezone = "+00:00";
                break;
            }
            case "Rome": {
                h = 1;
                timezone = "+01:00";
                break;
            }
            case "Moscow": {

                h = 3;
                timezone = "+03:00";
                break;
            }
            case "Tehran": {
                h = 3;
                m = 30;
                timezone = "+03:30";
                break;
            }
            case "New Delphi": {
                h = 5;
                m = 30;
                timezone = "+05:30";
                break;
            }
            case "Beijing": {
                h = 8;
                timezone = "+08:00";
                break;
            }
            case "Canberra": {
                h = 10;
                timezone = "+10:00";
                break;
            }
        }
        switch (name1) {
            case "Los Angeles": {
                timezone = "-08:00";
                h += 8;
                break;
            }
            case "New York": {
                h += 5;
                timezone = "-05:00";
                break;
            }
            case "Caracas": {
                timezone = "-04:30";
                h += 4;
                m += 30;
                break;
            }
            case "Buenos Aires": {
                timezone = "-03:00";
                h += 3;
                break;
            }
            case "London": {
                timezone = "+00:00";
                break;
            }
            case "Rome": {
                timezone = "+01:00";
                h -= 1;
                break;
            }
            case "Moscow": {
                timezone = "+03:00";
                h -= 3;
                break;
            }
            case "Tehran": {
                h -= 3;
                m -= 30;
                timezone = "+03:30";
                break;
            }
            case "New Delphi": {
                timezone = "+05:30";
                h -= 5;
                m -= 30;
                break;
            }
            case "Beijing": {
                h -= 8;
                timezone = "+08:00";
                break;
            }
            case "Canberra": {
                timezone = "+10:00";
                h -= 10;
                break;
            }
        }
        ZonedDateTime a = ZonedDateTime.parse(arr[2] + "-" + mon + "-" + days + "T" + arr[3] + timezone); // текущее время
        int totalMin = h * 60 + m; // итоговая разница в минутах
        if (totalMin < 0) a = a.minusMinutes(-totalMin); // если отрицательная - уменьшаем
        else a = a.plusMinutes(totalMin); // положительная - увеличиваем
        return a.toString().substring(0,a.toString().length()-6).replace("T", " "); // выводим как в примере: без таймзоны и заменив "T" на пробел
    }
    public static boolean isNew(int number) {
        String str = Integer.toString(number);
        int pos = 0;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                pos = i; // находим позицию нуля, т.к. он не может быть ведущим
                break;
            }
        }
        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (j >= pos && str.charAt(j) < str.charAt(i)) return false; // перебираем возможные числа
            }
        }
        return true;
    }
}
