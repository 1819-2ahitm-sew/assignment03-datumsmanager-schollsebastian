package at.htl.mydate;

import static processing.core.PApplet.day;
import static processing.core.PApplet.month;
import static processing.core.PApplet.year;

/**
 * Informationen zu Enums: http://tutorials.jenkov.com/java/enums.html
 *               zu split: https://stackoverflow.com/a/3481842/9818338
 *   zur Ermittlung des Wochentages: https://de.wikipedia.org/wiki/Wochentagsberechnung#Programmierung
 */
public class MyDate {

    private int day;
    private int month;
    private int year;
    private Weekday weekday;


    public MyDate(String dateString) {

        if (isValidDate(dateString)) {

            day = Integer.valueOf(dateString.split("\\.")[0]);
            month = Integer.valueOf(dateString.split("\\.")[1]);
            year = Integer.valueOf(dateString.split("\\.")[2]);
            weekday = calculateWeekday();

        }

    }

    private boolean isValidDate(String dateString) {

        boolean isValidDate;

        /*
        2018-11-04-HM: mit einer Array-Variable, müsste split nur 1x aufgerufen werden
        String[] dateElements = dateString.split("\\.");
         */
        if (dateString.split("\\.").length == 3) {

            int day = Integer.valueOf(dateString.split("\\.")[0]);
            int month = Integer.valueOf(dateString.split("\\.")[1]);
            int year = Integer.valueOf(dateString.split("\\.")[2]);

            isValidDate = day >= 1
                    && month >= 1
                    && month <= 12
                    && year >= 0; // 2018-11-04-HM: Das Kalenderjahr 0 existiert nicht

            if (isValidDate) {

                if (year > year()) {
                    isValidDate = false;
                } else if (year == year() && month > month()) {
                    isValidDate = false;
                } else if (year == year() && month == month() && day > day()) {
                    isValidDate = false;
                } else if (month == 1 && day > 31) { // 2018-11-04-HM: ev. switch-Anweisung verwenden
                    isValidDate = false;
                } else if (month == 2 && day > 28) {
                    isValidDate = false;
                } else if (month == 3 && day > 31) {
                    isValidDate = false;
                } else if (month == 4 && day > 30) {
                    isValidDate = false;
                } else if (month == 5 && day > 31) {
                    isValidDate = false;
                } else if (month == 6 && day > 30) {
                    isValidDate = false;
                } else if (month == 7 && day > 31) {
                    isValidDate = false;
                } else if (month == 8 && day > 31) {
                    isValidDate = false;
                } else if (month == 9 && day > 30) {
                    isValidDate = false;
                } else if (month == 10 && day > 31) {
                    isValidDate = false;
                } else if (month == 11 && day > 30) {
                    isValidDate = false;
                } else if (month == 12 && day > 31) {
                    isValidDate = false;
                }

                // 2018-11-04-HM: auch Schaltjahre berücksichtigt => super!
                if (isLeapYear(year) && month == 2 && day == 29) {
                    isValidDate = true;
                }

            }

        } else {
            isValidDate = false;
        }

        return isValidDate;

    }

    private boolean isLeapYear(int year) {

        boolean isLeapYear = false;

        if (year % 4 == 0 && year % 100 != 0) {
            isLeapYear = true;
        } else if (year % 100d == 0 && year % 400d != 0) {
            isLeapYear = true; // 2018-11-04-HM: richtig wäre isLeapYear = false
        } else if (year % 400d == 0) {
            isLeapYear = true;
        }

        return isLeapYear;

    }

    /**
     * Formatierung des Datums
     *
     * @return String, zB Samstag, 29. September 2018
     */
    public String formatDate() {

        String formatedDate;
        String weekdayString = "";
        String monthString = calulateMonthString();

        switch (getWeekday()) {

            case MONDAY:
                weekdayString = "Montag";
                break;
            case TUESDAY:
                weekdayString = "Dienstag";
                break;
            case WEDNESDAY:
                weekdayString = "Mittwoch";
                break;
            case THURSDAY:
                weekdayString = "Donnersatg";
                break;
            case FRIDAY:
                weekdayString = "Freitag";
                break;
            case SATURDAY:
                weekdayString = "Samstag";
                break;
            case SUNDAY:
                weekdayString = "Sonntag";
                break;

        }

        formatedDate = weekdayString + ", " + day + ". " + monthString + " " + year;

        return formatedDate;

    }

    private String calulateMonthString() {

        String monthString = "";

        switch (month) {

            case 1:
                monthString = "Jänner";
                break;

            case 2:
                monthString = "Februar";
                break;

            case 3:
                monthString = "März";
                break;

            case 4:
                monthString = "April";
                break;

            case 5:
                monthString = "Mai";
                break;

            case 6:
                monthString = "Juni";
                break;

            case 7:
                monthString = "Juli";
                break;

            case 8:
                monthString = "August";
                break;

            case 9:
                monthString = "September";
                break;

            case 10:
                monthString = "Oktober";
                break;

            case 11:
                monthString = "November";
                break;

            case 12:
                monthString = "Dezember";
                break;

        }

        return monthString;

    }

    private Weekday calculateWeekday() {

        int weekdayNumber;
        Weekday weekday = Weekday.MONDAY;

        if (month == 1 || month == 2) {

            weekdayNumber = ((day + (int) (2.6 * ((month + 9) % 12 + 1) - 0.2)
                    + (year - 1) % 100 + (int) ((year - 1) % 100 / 4) + (int) ((year - 1) / 400)
                    - 2 * (int) ((year - 1) / 100) - 1) % 7 + 7) % 7 + 1;

        } else {

            weekdayNumber = ((day + (int) (2.6 * ((month + 9) % 12 + 1) - 0.2)
                    + year % 100 + (int) (year % 100 / 4) + (int) (year / 400)
                    - 2 * (int) (year / 100) - 1) % 7 + 7) % 7 + 1;

        }

        switch (weekdayNumber) {

            case 1:
                weekday = Weekday.MONDAY;
                break;

            case 2:
                weekday = Weekday.TUESDAY;
                break;

            case 3:
                weekday = Weekday.WEDNESDAY;
                break;

            case 4:
                weekday = Weekday.THURSDAY;
                break;

            case 5:
                weekday = Weekday.FRIDAY;
                break;

            case 6:
                weekday = Weekday.SATURDAY;
                break;

            case 7:
                weekday = Weekday.SUNDAY;
                break;

        }

        return weekday;

    }

    /**
     *  Überprüfen, ob ein übergebenes Datum (other) jünger oder älter ist
     *
     * @param other
     * @return true, wenn this-Datum jünger als other-Datum ist
     *         false, wenn this-Datum jünger oder gleich other-Datum ist
     */
    public boolean isYoungerThan(MyDate other) {

        boolean isYounger = false;

        if (other.year < year) {
            isYounger = true;
        } else if (other.year == year && other.month < month) {
            isYounger = true;
        } else if (other.year == year && other.month == month && other.day < day) {
            isYounger = true;
        }

        return isYounger;

    }

    //region Getter
    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public Weekday getWeekday() {
        return weekday;
    }
    //endregion

}
