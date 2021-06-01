import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));// 1
        System.out.println(collect("intercontinentalisationalism", 6));// 2
        System.out.println(nicoCipher("myworldevolvesinhers", "tesh"));// 3
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15, 3}, 45)));// 4
        System.out.println(Arrays.toString(isExact(6)));// 5
        System.out.println(fractions("0.(6)"));// 6
        System.out.println(pilish_string("33314444"));// 7
        System.out.println(generateNonconsecutive(4));// 8
        System.out.println(isValid("abcdefghhgfedecba"));// 9
        System.out.println(sumsUp(new int[]{1, 6, 5, 4, 8, 2, 3, 7}));// 10
    }
    public static String hiddenAnagram (String str1, String str2) {
        String first = "", second = "";
        str1 = str1.toLowerCase(); // убираем капс
        str2 = str2.toLowerCase();
        for (int i = 0; i < str1.length(); i++) { // убираем всё кроме букв
            if (str1.charAt(i) >= 'a' && str1.charAt(i) <= 'z') {
                first += str1.charAt(i);
            }
        }
        for (int i = 0; i < str2.length(); i++) {
            if (str2.charAt(i) >= 'a' && str2.charAt(i) <= 'z') {
                second += str2.charAt(i);
            }
        }
        String anagram = "";
        for (int i = 0; i < first.length(); i++) { // используя код из 5.3 находим буквы в словах
            for (int a = 0; a < first.length()-i; a++) {
                anagram = first.substring(a, a+i+1);
                String[] arr = new String[] {second};
                int result = 0;
                boolean[] checker = new boolean[anagram.length()];
                next:
                for (String k : arr) {
                    for (int j = 0; j < anagram.length(); j++) {
                        checker[j] = false;
                    }
                    for (int z = 0; z < k.length(); z++) {
                        if (anagram.contains(Character.toString(k.charAt(z))))
                            for (int j = 0; j < anagram.length(); j++) {
                                if (k.charAt(z) == anagram.charAt(j) && !checker[j]) {
                                    checker[j] = true;
                                    break;
                                }
                                if (j == anagram.length() - 1)
                                    continue next;
                            }
                        else
                            continue next;
                    }
                    result += k.length() - 2;
                    if (k.length() == anagram.length()) {
                        result += 50;
                    }
                }
                if (result > 0) return anagram; // если нашли анаграмму - выводим
            }
        }
        return "Error";
    }
    public static List<String> collect(String str,int n) {
        List<String> list= new ArrayList<String>();
        col(list,str,n); // вызываем рекурсивный метод
        Collections.sort(list); // сортируем
        return list;
    }
    public static void col(List<String> list,String str, int n) {
        if(str.length()<n) return; // если строка закончилась - return
        list.add(str.substring(0,n)); // добавляем в list нужное количество символов
        col(list,str.substring(n),n); // выпполняем метод с урезанным стрингом
    }
    public static String nicoCipher(String message, String key) {
        HashMap<Integer, String> hash = new HashMap<>(); // integer - байт-код char-символа из ключа
        char[] chars = message.toCharArray(); // массив символов сообщения
        char[] keychar = key.toCharArray(); // массив символов ключа
        for(int i = 0; i < key.length(); i++) {
            hash.put(Character.getNumericValue(keychar[i]), ""); // обнуляем hashmap
        }
        for(int i = 0; i < message.length(); i++) { // записываем буквы в нужные ячейки
            hash.put(Character.getNumericValue(keychar[i % keychar.length]), hash.get(Character.getNumericValue(keychar[i % keychar.length])) + String.valueOf(chars[i]));
        }
        Arrays.sort(keychar); // сортируем массив символов ключа
        String result = "";
        int j = -1; // параметр для подсчёта номера буквы из ячейки hashmap
        for(int i = 0; i < message.length(); i++) {
            if(i % keychar.length == 0) j++; // если прошлись по всем ячейкам - увеличиваем номер символа
            if(j > keychar.length) j = 0; // если превысили длину - обнуляем
            result = result + String.valueOf(hash.get(Character.getNumericValue(keychar[i % keychar.length])).charAt(j));
        } // добавляем букву из ячейки
        return result;
    }
    public static int[] twoProduct(int[] arr, int val) {
        int first = 0, second = 0;
        int[] answer = new int[2];
        for (int i = arr.length - 1; i > 0; i--) { // вложенным циклом ищем нужное произведение
            second = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                first = arr[j];
                if (first * second == val) {
                    answer[0] = first;
                    answer[1] = second;
                }
            }
        }
        return answer;
    }
    public static int[] isExact(int val) {
        int[] answer = new int[0]; // изначально пустой массив
        int number = isFact(val,2); // рекурсивный метод для определения факториала
        if(number != -1) answer = new int[] {val, number}; // выводим ответ
        return answer;
    }
    public static int isFact(int number, int k) {
        if(number == 1) return k - 1;
        if(number % k != 0) return -1;
        return isFact(number / k,k + 1);
    }
    public static String fractions(String numb) {
        String noSmiles = "", withoutDotOnly = "";
        int power1 = 0, power2 = 0, dotIndex = 0, firstIndex = 0, secondIndex = 0;
        for (int i = 0; i < numb.length(); i++) {
            if (numb.charAt(i) == '.') dotIndex = i; // находим координаты точки и скобок
            else if (numb.charAt(i) == '(') firstIndex = i;
            else if (numb.charAt(i) == ')') secondIndex = i;
            else noSmiles += numb.charAt(i);
        }
        withoutDotOnly = noSmiles.substring(0, firstIndex-1);
        int firstNumber, secondNumber;
        power1 = secondIndex-dotIndex-2;
        power2 = firstIndex-dotIndex-1;
        firstNumber = (int)(Math.pow(10, power1) - Math.pow(10, power2)); // считаем числитель и знаменатель
        secondNumber = Integer.parseInt(noSmiles) - Integer.parseInt(withoutDotOnly);
        int[] pair = divide(secondNumber, firstNumber); // формируем пару чисел для дроби
        return pair[0] + "/" + pair[1];
    }
    public static int[] divide(int a, int b) {
        int k = 2;
        int t = Math.max(a, b);
        while (k<t) {
            if(a%k==0 && b%k==0) {
                a/=k;
                b/=k;
            }
            else k++;
        }
        return new int[] {a,b};
    }
    public static String pilish_string(String str) {
        String st = "314159265358979";
        String res = "";
        int index = 0;
        int len = 0;
        for (int i = 0; i<str.length(); i++) {
            len++;
            res += str.charAt(i); // выводим пока не достигнет величины цифры
            if (Character.getNumericValue(st.charAt(index)) == len) // по достижении ставим пробел
            {
                res += " ";
                len = 0;
                index++;
            }
            if (index == 15) return res; // число пи закончилось, выводим результат
        }
        if (Character.getNumericValue(st.charAt(index)) > len) {
            while (Character.getNumericValue(st.charAt(index)) > len) {
                res += res.charAt(res.length() - 1);
                len++;
            }
        }
        return res;
    }
    public static String generateNonconsecutive(int n) {
        return recurs(n,false,"");
    }
    public static String recurs(int n, boolean wasOne,String text) { // рекурсивно формируем строку
        if (n==1) {
            if (wasOne) return text + "0 ";
            else return text + "0" + " " + text + "1 ";
        }
        if (wasOne) return recurs(n-1,false,text+"0");
        else return recurs(n - 1,false,text + "0") + recurs(n - 1,true,text + "1");
    }
    public static String isValid(String str) {
        int[] arr = new int[100000];
        for (int i = 0; i<arr.length; i++) {
            arr[i] = 0;
        }
        for (int i = 0; i<str.length(); i++) {
            arr[str.charAt(i)]++; // считаем количество цифр, заносим в ячейку с их байт-кодом
        }
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i<arr.length; i++) {
            if (arr[i] != 0)
            {
                if (!map.containsKey(arr[i])) map.put(arr[i], 1);
                else map.put(arr[i], map.get(arr[i]) + 1); // сохраняем в hashmap
            }
        }
        if(map.size() > 2) return "NO";
        int[] arrL = new int[4];
        int z = 0;
        for (Map.Entry i : map.entrySet()) {
            arrL[z] = (int)i.getKey();
            arrL[z+1] = (int)i.getValue();
            z += 2;
        }
        if(map.size() == 1) return "YES";
        if (arrL[0] < arrL[2] && arrL[1] == 1) return "YES";
        else if(arrL[3] == 1) return "YES";
        return "NO";
    }
    public static String sumsUp(int[] arr){
        String list = "";
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) { // вложенным циклом ищем цифры с суммой == 8
                if (arr[i] + arr[j] == 8) list = list + "[" + Math.min(arr[i], arr[j]) + ", " + Math.max(arr[i], arr[j]) + "], ";
            }
        }
        return list.substring(0, list.length() - 2);
    }
}
