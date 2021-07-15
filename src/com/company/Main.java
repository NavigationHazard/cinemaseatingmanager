package com.company;

import java.text.DecimalFormat;

import java.util.Scanner;

public class Main {
    // formatting the decimal point for percentageBooked
    private static DecimalFormat df2 = new DecimalFormat("#.00");

    public static void main(String[] args) {
        // Scanner initialization

        SeatMap seatingMap = new SeatMap();
        Scanner scanner = new Scanner(System.in);

        // ask user for gridSize of the Cinema

        System.out.println("Enter the number of rows:");
        int row = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = scanner.nextInt();
        // generate the Grid and load up the menu

        seatingMap.generateGrid(row + 1, cols + 1);
        seatingMap.createMenu();

        // read the keep the menu active until the user choose exit
        while (seatingMap.menuInput != 0) {
            switch (seatingMap.menuInput) {
                case 0:
                    break;
                // print the current layout
                case 1:
                    System.out.println();
                    seatingMap.printMap(seatingMap.seatGrid);
                    System.out.println();
                    seatingMap.createMenu();

                    // Buy a ticket and mark the seat on the map as booked
                case 2:
                    seatingMap.bookSeat();
                    break;

                case 3:
                    // Output number of tickets purchased, current booked percentage and
                    // current/total income if fully booked

                    System.out.println("Number of purchased tickets: " + seatingMap.ticketsAmount);
                    if (seatingMap.percentageBooked == 0) {
                        System.out.println("Percentage: 0.00%");
                    } else {
                        System.out.println("Percentage: " + df2.format(seatingMap.percentageBooked) + "%");
                    }
                    System.out.println("Current income: $" + seatingMap.currentIncome);
                    seatingMap.calcProfit(row, cols);
                    seatingMap.createMenu();
            }
        }
    }
}
