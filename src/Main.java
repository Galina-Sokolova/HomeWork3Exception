import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidDate {
        Main main = new Main();
        String[] str = main.dataInput();
        main.validationOfEnteredData(str);
        main.saveToFile(str);
    }

    public static String[] dataInput() {
        Scanner sc = new Scanner(System.in);
        //while (true) {
        //  try {
        System.out.println("Введите следующие данные в одну строку через пробел: ");
        System.out.println("ФИО (без пробелов)");
        System.out.println("дата_рождения - в формате dd.mm.yyyy");
        System.out.println("номер_телефона - без знака '+'");
        System.out.println("пол - f или m");
        String input = sc.nextLine();
        String[] split = input.split(" ");
        for (String word : split) {
            System.out.println(word);
        }
               /* if( number == Float.POSITIVE_INFINITY ){
                    throw new InputMismatchException();
                }
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Ошибка ввода! Попробуйте снова!");
                sc.nextLine();
            }
        }*/
        return split;
    }

    public static void validationOfEnteredData(String[] array) throws InvalidDate {
        if (array.length != 4) {
            throw new RuntimeException("Неверное количество пробелов. Должно быть всего 3 пробела");
        }
        try {
            Integer.parseInt(array[2]);
            //return true;
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод. Номер телефона должен содержать только цифры.");
        }

        if (!dateFormatValidation(array[1])){
            throw new InvalidDate();
        }
        try{
            boolean b = (array[3] != "f" || array[3] != "m");
            //throw new RuntimeException();
        } catch (RuntimeException exc){
            System.out.println("Неверный ввод. Должен быть один из символов: f/m");

        }
    }
    /*private static void writeToFile(String str, File file){
        try(FileWriter writer = new FileWriter(file)){
            for (String i: map.keySet()){
                String s = i + "=" + map.get(i);
                writer.write(s);
                writer.write("\n");
            }
        }catch (IOException e){
            e.getMessage();
        }
    }*/
    private static void saveToFile(String[] split) {
        //String[] split = str.split(".");
        try (FileWriter writer = new FileWriter("HomeWork3Exception/" + split[0] + ".txt", true)) {
            writer.append(split[0] + " " + split[1] + " " + split[2] + " "+ split[3] + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


   /* private static boolean validDate (String data){
       Calendar cal = new GregorianCalendar();
       cal.setLenient(false);
       try {
           String[] split = data.split(".");
           cal.set(Integer.parseInt(split[2]), Integer.parseInt(split[1])-1, Integer.parseInt(split[0]));
           cal.getTime();
           return true;
       } catch (IllegalArgumentException iae) {
           return false;
       }
    }*/

    public static boolean dateFormatValidation(String data) {
        //int dateIndex = -1;
        //int i = 0;
        //for (String item : splittedData) {
        //if (data.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            //dateIndex = i;
            String[] fullDate = data.split("\\.");
            if (Integer.parseInt(fullDate[0]) < 1 || Integer.parseInt(fullDate[0]) > 31
                    || Integer.parseInt(fullDate[1]) < 1 || Integer.parseInt(fullDate[1]) > 12 || Integer.parseInt(fullDate[2]) > 2030
                    || Integer.parseInt(fullDate[2]) < 1920) {
                System.out.println("Введеной даты не существует");
                return false;
            }

            //i += 1;
        /*if (dateIndex == -1) {
            System.out.println("Ошибка: Дата не введена или формат даты не соответствует требованиям");
            return -1;
        }*/
        return true;
    }
}