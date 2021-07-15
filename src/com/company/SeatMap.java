package com.company;

import java.util.Scanner;

public class SeatMap {
    // init all the fields needed

    private int colAmount;
    private int rowAmount;
    private final int ticketPriceLow = 8;
    private final int ticketPriceHigh = 10;
    public char[][] seatGrid;
    public int menuInput;
    public int ticketsAmount;
    public int currentIncome;
    public double percentageBooked;

    // Init a new Scanner and pass the Input chosen to the main class
    Scanner scanner = new Scanner(System.in);

    public void createMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("1. Show the Seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        System.out.println();
        menuInput = scanner.nextInt();

    }

    // calculate the total profit if every seat is booked
    public void calcProfit(int rows, int cols) {
        // generate a grid without the row and col numbering
        int newGrid = (this.colAmount - 1) * (this.rowAmount - 1);

        // if less then 60 seats the ticket price is 10 dollars
        if (newGrid <= 60) {
            int front1 = (rows * cols) * ticketPriceHigh;
            System.out.println("Total income: $" + front1);

        }
        // if the grid is bigger, then the front rows will cost 10 dollar and the back row 8 dollar
        if (newGrid > 60) {
            if (rows % 2 != 0) {
                int front = (rows / 2);
                int back = ((rows - front) * ticketPriceLow) * cols;
                front = (front * ticketPriceHigh) * cols;
                int sum = front + back;
                System.out.println("Total income: $" + sum);


            } else {
                int front = (rows / 2);
                int back = ((rows - front) * ticketPriceLow) * cols;
                front = (front * ticketPriceHigh) * cols;
                int sum = front + back;
                System.out.println();
                System.out.println("Total income: $" + sum);

            }
        }
    }

    public void calcPriceAtSeat(int cols, int rows) {
        // generate a grid without the row and col numbering
        int newGrid = (this.colAmount - 1) * (this.rowAmount - 1);

        // if less then 60 seats the ticket price is 10 dollars
        if (newGrid <= 60) {
            int front1 = ticketPriceHigh;
            currentIncome = currentIncome + front1;
            System.out.println();
            System.out.println("Ticket price:" + " $" + front1);
        }
        if (newGrid > 60) {
            int half = rowAmount / 2;

            if (rows < half) {
                currentIncome = currentIncome + ticketPriceHigh;
                System.out.println();
                System.out.println("Ticket price:" + " $" + ticketPriceHigh);

            } else if (rows >= half || rows >= 5) {
                currentIncome = currentIncome + ticketPriceLow;
                System.out.println();
                System.out.println("Ticket price:" + " $" + ticketPriceLow);
            }
        }
    }
    // calculate the booked percentage of the cinema room
    public void percentages() {
        int newGrid = (this.colAmount - 1) * (this.rowAmount - 1);
        double gridBooked = newGrid - ticketsAmount;
        percentageBooked = 100.00 - ((gridBooked / newGrid) * 100.00);
    }

    // print out the seatingGrid
    public void printMap(char[][] seatGrid) {
        System.out.println("Cinema:");
        // Loop through all rows
        for (int i = 0; i < seatGrid.length; i++) {
            for (int j = 0; j < seatGrid[i].length; j++) {

                System.out.print(seatGrid[i][j] + " ");
            }
            System.out.println();
        }
    }

    // book a seat and mark the seat as booked in the seatingGrid
    public void bookSeat() {
        // ask user for seat position
        System.out.println("Enter a row number:");
        int seatRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatCol = scanner.nextInt();

        // validate of input is in boundaries of the grid
        if (seatRow > 9 || seatCol > 9) {
            System.out.println();
            System.out.println("Wrong input!");
            bookSeat();

        // check if this seat is already taken
        } else if (this.seatGrid[seatRow][seatCol] == 'B') {
            System.out.println();
            System.out.println("That ticket has already been purchased!");
            bookSeat();
        // book the seat, recalculate percentages and return to menu
        } else {
            this.ticketsAmount++;
            this.seatGrid[seatRow][seatCol] = 'B';
            calcPriceAtSeat(seatCol, seatRow);
            percentages();
            createMenu();
        }
    }

    // generate the seatingGrid by input size from the user and fill with 'S'
    public void generateGrid(int rowAmount, int colAmount) {
        this.colAmount = colAmount;
        this.rowAmount = rowAmount;
        this.seatGrid = new char[rowAmount][colAmount];

        // fill with availability
        for (int row = 0; row < seatGrid.length; row++) {
            for (int col = 0; col < seatGrid[row].length; col++) {
                // dont fill the first spot of the array
                char availability = 'S';
                if (col == 0 && row == 0) {
                    seatGrid[row][col] = ' ';
                }
                // TODO: enable row counting higher the 10 - hex? or rewrite the  0 row and cols with integers
                // fill the first col with numbers of the current row in the loop
                else if (col < 1) {
                    seatGrid[row][col] = (char) (row + '0');
                }
                // fill the first row with numbers of the current column in the loop
                else if (row < 1) {
                    seatGrid[row][col] = (char) (col + '0');
                }
                // fill the first row with the status of availability
                else seatGrid[row][col] = availability;
            }
        }
    }
}
