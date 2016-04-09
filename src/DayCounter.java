import java.time.*;
import java.util.*;

/**
 * Created by Melke on 01/04/16.
 */

public class DayCounter {

    private int yearBorn;
    private int monthBorn;
    private int dayBorn;


    public static void main(String[] args) {
       DayCounter dayCounter = new DayCounter();
        dayCounter.countDays();
    }


    private void countDays(){
        System.out.print("What is your date of birth?" );
        int dateBorn = new Scanner(System.in).nextInt();
        System.out.println("");

        yearBorn = dateBorn / 10000;
        monthBorn = dateBorn % 10000 / 100;
        dayBorn = dateBorn % 10000 % 100;

        if (yearBorn > todaysYear()) {
            System.out.println("Error! The date is in the future");
        } else if (yearBorn == todaysYear() && monthBorn > todaysMonth()) {
            System.out.println("Error! The date is in the future");
        } else if (yearBorn == todaysYear() && monthBorn == todaysMonth() && dayBorn > todaysDay()) {
            System.out.println("Error! The date is in the future");
        } else {
            int countedDays = countDaysOld();
            System.out.print("You are " + countedDays + " days old today.");
        }
    }

    //calculates how many days a specific month contains
    private int daysInMonth (int month, int year) {
            if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                return 31;
            } else if (month == 2 && !isLeapYear(year)) {
                return 28;
            } else if (month == 2 && isLeapYear(year)) {
                return + 29;
            } else {
                return 30;
            }
    }

    //calculates if the year is a leap year or not
    private boolean isLeapYear (int year) {
        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
            return true;
        } else {
            return false;
        }
    }

    private int countDaysOld () {
        int daysOld = 0;
        int birthYearMonthCounter = monthBorn;

        //counts all the days for the remaining months for the year the person was born
        if (yearBorn != todaysYear()) {
            while (birthYearMonthCounter < 13) {
                daysOld = daysOld + daysInMonth(birthYearMonthCounter, yearBorn);
                birthYearMonthCounter++;
            }
        }
        //remove the days before the person was born
        daysOld = daysOld - dayBorn;

        //counts all the days for all the years between the birth year and today's year
        int yearCounter = yearBorn + 1;
        while (yearCounter < todaysYear()) {
            int month = 1;
            while (month < 13) {
                daysOld = daysOld + daysInMonth(month, yearCounter);
                month++;
            } yearCounter++;
        }

        //counts the days in current year except for the current month
        int month;
        if (yearBorn != todaysYear()) {
            month = 1;
        } else {
            month = monthBorn;
        }
        while (month < todaysMonth()) {
            daysOld = daysOld + daysInMonth(month, todaysYear());
            month++;
        }

        //counts the days for the current month
        daysOld = daysOld + todaysDay();

        return daysOld;
    }

    private int todaysYear() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        return year;
    }

    private int todaysMonth() {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        return month;
    }

    private int todaysDay() {
        LocalDate date = LocalDate.now();
        int day = date.getDayOfMonth();
        return day;
    }
}
