package com.pluralsight;

import java.util.Scanner;
// * represents a calendar date
import java.time.LocalDate;
// * defines the format we expect - MM/dd/yyyy
import java.time.format.DateTimeFormatter;
// * represents an error that can occur when Java can't interpret the given date.
import java.time.format.DateTimeParseException;
// * used in formatter to tell Java to validate dates strictly. We're using Strict vs Smart so that all dates inputted
// * are real valid dates and will alert the user if they input an impossible date, instead of quietly fixing.
import java.time.format.ResolverStyle;
public class TheaterReservations {

    // ! public static Scanner that any method can call and use
    public static Scanner sc = new Scanner(System.in);

    // ! public static DateTimeFormatter that any method can call and use
    public static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MM/dd/uuuu") // I used yyyy here at first but learned with resolverstyle strict
                    // that it creates some era error thing I don't understand with calendars. so I googled and it said
                    // to use uuuu
                    .withResolverStyle(ResolverStyle.STRICT);

    public static void main(String[] args) {

        // TODO - Prompt the user for their full name
        System.out.print("""
        Welcome to the Theater Reservations Counter! Please enter your first and last name (e.g. James Smith)
        """);
        String fullNameAnswer = sc.nextLine();
        String fullNameTrimmed = fullNameAnswer.trim();
        String[] fullNameParts = fullNameTrimmed.split("\\s+");

        if (fullNameParts.length != 2) {
            System.out.println("Please enter your first and last name in the given format (e.g. James Smith)");
            return;
        }
        // TODO - Prompt the user for the date of the show
        System.out.println("Please enter the date of the showing you would like to reserve for (e.g. MM/dd/yyyy): ");
        String reservationDate = sc.nextLine();

// *  if statement for date format but didn't use date formatter so not going forward with this
//      if (!reservationDate.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
//          System.out.println("Please enter a date using the given format (e.g. MM/dd/yyyy | 01/25/2027): ");
//         return;
//      }

        // * My first try statement! It tells Java to execute the code inside its curly braces, and if something errors
        // * then execute our plan for handling it.
        // * LocalDate = the data type for a calendar date. parsedDate = the variable where we'll store the
        // * converted date. = assigns result from right side of = to variable on the left
        // * LocalDate.parse() Attempts to convert text into a real Java date. reservationDate is the user's
        // * original date input that we stored as a String variable. The formatter tells Java how to read
        // * the date's format, such as MM/dd/uuuu.
        try {
            LocalDate parsedDate = LocalDate.parse(reservationDate, formatter);
        } catch  (DateTimeParseException e) {
            System.out.println("Please enter a valid date in the format MM/dd/yyyy (e.g. 01/25/2027)");
            return;
        }

        // TODO - Prompt the user for the number of tickets they want
        System.out.println("Please enter how many tickets you want in numerical form (e.g. 2): ");
        String numberOfTickets = sc.nextLine();
        // * if statement for if the number of tickets requested was not an integer or whole number (abc)
        // * the + after 0-9 means that pattern must occur at least one time, and can occur multiple times.
        if (!numberOfTickets.matches("[0-9]+")) {
            System.out.println("Please enter a number (e.g. 2)");
            return;
        }

        // * converting numberOfTickets from a String to an integer to perform calculations with it.
        int ticketCount  = Integer.parseInt(numberOfTickets);

        // * if statement for if they request 0 or less tickets.
        if (ticketCount <= 0) {
            System.out.println("Please reserve at least one ticket.");
            return;
        }

        // * if statement to check if ticketCount equates to one
        if (ticketCount == 1) {
            //Print confirmation for one ticket
            System.out.printf(
                    "%d ticket reserved for %s under the name %s, %s%n",
                    ticketCount, reservationDate, fullNameParts[1], fullNameParts[0]);
        } else {
            //Print confirmation for multiple tickets
            System.out.printf(
                    "%d tickets reserved for %s under the name %s, %s%n",
                    ticketCount, reservationDate, fullNameParts[1], fullNameParts[0]);
        }
        //TODO - Make their reservation given their input

        //TODO - Display a confirmation message to the user in this format:
        // ticket(s) reserved for (date) under (LastName, FirstName)
        // if # of tickets is 1, don't display their confirmation with an s at the end of ticket

    } // ! main method cBracket

} // ! main class cBracket
