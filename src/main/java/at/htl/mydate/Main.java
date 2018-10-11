package at.htl.mydate;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.printf("Date-Manager%n============%n%n");
        int counter = 1;
        String dateString;
        MyDate currentDate;
        MyDate youngestDate = new MyDate("1.1.0");

        System.out.print("1. Datum: ");
        dateString = scanner.next();

        while (!dateString.equals("quit")) {

            currentDate = new MyDate(dateString);

            if (currentDate.getMonth() != 0) {

                if (currentDate.isYoungerThan(youngestDate)) {
                    youngestDate = currentDate;
                }

                System.out.println("Jüngstes Datum: " + youngestDate.formatDate());

                counter++;

            } else {
                System.out.println("Falsche Eingabe");
            }

            System.out.print(counter + ". Datum: ");
            dateString = scanner.next();

        }

    }

}