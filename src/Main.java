import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws InvalidDate {
        Main main = new Main();
        String[] str = main.dataInput();
        main.validationOfEnteredData(str);
        main.saveToFile(str);
    }

    public static String[] dataInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите следующие данные в одну строку через пробел: ");
        System.out.println("ФИО (через пробелов)");
        System.out.println("дата_рождения - в формате dd.mm.yyyy");
        System.out.println("номер_телефона - без знака '+'");
        System.out.println("пол - f или m");
        String input = sc.nextLine();
        String[] split = input.split(" ");
        for (String word : split) {
            System.out.println(word);
        }
        return split;
    }

    public static void validationOfEnteredData(String[] array) throws InvalidDate {
        if (array.length != 6) {
            throw new RuntimeException("Неверное количество пробелов. Должно быть всего 5 пробелов");
        }
        try {
            isDigit(array[4]);
        }catch (RuntimeException ex) {
            System.out.println(array[4] + "- Неверный ввод. Номер телефона должен содержать только цифры.");
        }
        if (!dateFormatValidation(array[3])){
            throw new InvalidDate();
        }
        try{
            boolean b = (array[5] != "f" || array[5] != "m");
            //throw new RuntimeException();
        } catch (RuntimeException exc){
            System.out.println("Неверный ввод. Должен быть один из символов: f/m");

        }
    }
    private static boolean isDigit(String s) throws NumberFormatException{
        try{
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException ex){
            System.out.println(s+" -Неверный ввод. Номер телефона должен содержать только цифры.");
            return false;
        }
    }
    private static void saveToFile(String[] split) {
        //String[] split = str.split(".");
        try (FileWriter writer = new FileWriter("src\\" + split[0] + ".txt", true)) {
            writer.append(split[0] + " " + split[1] + " " + split[2] + "  "+ split[3] + "  " + split[4] + "  " + split[5] +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static boolean dateFormatValidation(String data) {
            String[] fullDate = data.split("\\.");
            if (Integer.parseInt(fullDate[0]) < 1 || Integer.parseInt(fullDate[0]) > 31
                    || Integer.parseInt(fullDate[1]) < 1 || Integer.parseInt(fullDate[1]) > 12 || Integer.parseInt(fullDate[2]) > 2030
                    || Integer.parseInt(fullDate[2]) < 1920) {
                System.out.println("Введеной даты не существует");
                return false;
            }
        return true;
    }
}